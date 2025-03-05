---
sidebar_position: 1
title: Configure NGINX Server PKCS#11
sidebar_label: NGINX
description: Discover OpenSSL's PKCS11 provider, CLI commands, installation tips, and troubleshooting. Integrate seamlessly with HSM for enhanced security.
keywords: [OpenSSL PKCS11 provider, OpenSSL PKCS11 API, OpenSSL command line utility (CLI), OpenSSL CLI commands, OpenSSL installation guide, OpenSSL installation troubleshooting, OpenSSL troubleshooting tips, OpenSSL certificate management, OpenSSL certificate creation, OpenSSL certificate renewal, OpenSSL configuration file, OpenSSL configuration options, OpenSSL configuration guide, OpenSSL encryption algorithms, OpenSSL decryption methods, OpenSSL digital signatures, OpenSSL SSL/TLS protocols, OpenSSL SSL/TLS configuration, OpenSSL heartbleed vulnerability, OpenSSL security updates]
tags: [OpenSSL,PKCS#11,NGINX]
---
import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';


<!-- KEEP H1 EMPTY TO OVERWRITE THE TITLE WITH NEXT H2 -->
# Nginx Server

The following example shows how to set up NGINX to work with the OpenSSLv3 pkcs11-provider.

:::tip

If you prefer a native set-up instead, the nginx.conf in this section and the section [Preparing the key and
certificate](#preparing-the-key-and-certificate) will be the most relevant for you.

:::


:::note

The support for key references from a PEM file was added to the
latchset pkcs11-provider in version 0.3-22. Please use the release 0.4 or
newer.

:::

<Tabs groupId="scenario">
<TabItem value="nginx-package" label="Debian/Ubuntu" default>

## Install nginx on Debian/Ubuntu
For this example an NGINX server is started as a service in `systemd`.

Update the package list and install Nginx.
```bash
sudo apt update
sudo apt install nginx
```

Configure nginx's systemd to use the correct openssl file.
```bash
sudo nano /usr/lib/systemd/system/nginx.service
```
Under the section `[Service]`, add the line of the environment of where your openssl.cnf file is located. This entry should be before `ExecStartPre`.
```bash
Environment="OPENSSL_CONF=/etc/ssl/openssl.cnf"
```
<!-- Environment="OPENSSL_CONF=/etc/primus/openssl.cnf" -->

### Preparing the key and certificate

For this example a simple self-signed certificate is created. For real
applications you probably want to get your certificate [signed by a
CA](/openssl/osslv3/Use-Cases/self_signed_certificate)
and put the certificate chain into the cert.pem-file.

Again some environment variables are set-up as placeholders.

```bash
export P11_TOKEN=<YOUR_USER_NAME>	# partition name
export P11_PIN=<YOUR_PKCS#11_PIN>	# hsm pkcs11 pin (don't use for production)
export P11_KEY_NAME=TESTING_NGINX_KEY	# name of Key
```

```bash
sudo mkdir -p /etc/nginx/certificates/
```

A RSA-4096 private key is generated

```bash
openssl genpkey -propquery "provider=pkcs11" \
    -algorithm "rsa" -pkeyopt "rsa_keygen_bits:4096" \
    -pkeyopt "pkcs11_uri:pkcs11:token=${P11_TOKEN};object=${P11_KEY_NAME}?pin-value=${P11_PIN}"
```
Given the encoder of the OpenSSL pkcs11-provider is enabled, the key will be outputted as a "PKCS#11 PROVIDER URI" pem file.

Use the key to create a self signed certificate

```bash
sudo openssl req -new -x509 -copy_extensions=copyall \
    -key "pkcs11:type=private;token=${P11_TOKEN};object=${P11_KEY_NAME}?pin-value=${P11_PIN}" \
    -subj "/C=CH/ST=Bern/L=Bern/O=My Example Organisation/OU=IT Department/CN=www.example.com" \
    -addext "subjectAltName = DNS:www.example.com, DNS:*.www.example.com" \
    -sha256 -days 99 -out /etc/nginx/certificates/p11-provider-cert.pem
```
For long-term usage, the PKCS PIN can be stored on the machine running Nginx. For security reasons, the "pin-value" should be obfuscated when used by the Nginx process.

Create a new file `/run/secrets/securosys_p11_pin` to be called instead, via `pin-source=file:/run/secrets/securosys_p11_pin`. The OpenSSL decoder will then try to read the pkcs11-pin from `/run/secrets/securosys_p11_pin`.

:::warning
Each time the VM is rebooted, the file `/run/secrets/securosys_p11_pin` containing the P11 PIN, will be deleted. As a workaround, you can create a persistent file at `/etc/primus/securosys_p11_pin` containing the P11 PIN.

Then, modify the `/usr/lib/systemd/system/nginx.service` file by adding the line `ExecStartPre=cp -p /etc/primus/securosys_p11_pin /run/secrets` under the "[Service]" section, when configuring nginx further in this page.
:::

The [Python script](https://github.com/latchset/pkcs11-provider/blob/1362378ad3d5f40013bae7562cf7e5d79149925e/tools/uri2pem.py)
included in the OpenSSL pkcs11-provider's source can be used. Please note that Securosys does not maintain this script and cannot guarantee it will be available and functional in perpetuity.

As an alternative, in the shell script below, the pem-file is created using tools commonly available on *nix systems. 

```bash
touch generate.sh
chmod +x generate.sh
```
Copy the below lines into the file generate.sh using your preferred text editor.
```bash
#!/bin/bash
make-pkcs11-uri-pem() {
    # helper function to create a PEM file from a pkcs11-uri provided as argument
    export LC_ALL=C
    URI=$1
    URI_HEX=$(printf '%s' "${URI:?}"   | od -An -t x1)
    DESC="PKCS#11 Provider URI v1.0"
    DESC_HEX=$(printf '%s' "${DESC}" | od -An -t x1)
    PEM_HEX=$(printf '30 82 %04x 1a 82 %04x %s 0c 82 %04x %s'  \
		     "$((${#URI} + ${#DESC} + 8))" "${#DESC}" "${DESC_HEX[*]}" \
		     "${#URI}" "${URI_HEX[*]}" \
		  | tr -d '\r\n\t ' | sed -e 's,\(.\{2\}\),\\x\1,g')
    # shellcheck disable=SC2059 # printf should use PEM_HEX as format string
    PEM=$(printf "${PEM_HEX}" | base64)
    printf "%s\n%s\n%s" \
	   "-----BEGIN PKCS#11 PROVIDER URI-----" \
	   "${PEM}" \
	   "-----END PKCS#11 PROVIDER URI-----"
}
# highlight-info-start
make-pkcs11-uri-pem "pkcs11:type=private;token=${P11_TOKEN};object=${P11_KEY_NAME}?pin-source=file:/run/secrets/securosys_p11_pin" \
    > /etc/nginx/certificates/p11-provider-pkey.pem
# highlight-info-end
chmod 644  /etc/nginx/certificates/*
chgrp www-data /etc/nginx/certificates/*
```
Execute this script to generate and update the file's permissions.

The certificates directory is ready, it contains the cert.pem and the pkey.pem referenced in the NGINX configuration. The `pkey.pem` contains the `pkcs11-uri` to the private key, and a file uri to load the pkcs11-pin.

To finilize the configuration of the Nginx server, we need to reference our newly created keys and cert.
```text
sudo nano /etc/nginx/sites-enabled/primus

server {
        listen 8082 ssl http2;
        listen [::]:8082 ssl http2;
        server_name 34.133.134.100;
        root /var/www;
# highlight-info-start
        ssl_certificate /etc/nginx/certificates/p11-provider-cert.pem; # path of the certificate located in the server
        ssl_certificate_key /etc/nginx/certificates/p11-provider-pkey.pem; # path of the PKCS#11 PROVIDER URI
# highlight-info-end

        ssl_session_cache shared:SSL:1m;
        ssl_session_timeout  10m;
        ssl_prefer_server_ciphers on;

        location / {
        root   /usr/share/nginx/html;
        index  index.html index.htm;
    }

        location = /404.html {
        }
}
```
Lastly, we need to allow the nginx process to view the PKCS11 secrets file so that it can use to authenticate to the HSM. To do this, we can simply add the `www-data` user in the `primus` group.
```bash
sudo usermod -a -G primus www-data
```

As we made changes to the `systemd` file of Nginx, we need to reload and run some sanity tests.
```bash
# reload the systemd daemon
sudo systemctl daemon-reload

# test nginx
sudo nginx -t
# nginx: the configuration file /etc/nginx/nginx.conf syntax is ok
# nginx: configuration file /etc/nginx/nginx.conf test is successful

# restart nginx
sudo systemctl restart nginx
```
After that, our nginx server will be running and accessible.

</TabItem>

<TabItem value="nginx-docker" label="Docker swarm">

## Install nginx as docker container
For this example an NGINX server is started
as a docker service. The base image is the Red Hat ubi9/nginx-122
image.

### Preparation
The following project structure is used:

```
├── Dockerfile
├── setup
│   ├── nginx.conf
│   ├── PrimusAPI_OSSLv3-Provider-PKCS11-*-rhel8_amd64.rpm
│   └── PrimusAPI_PKCS11-X-*-rhel8-x86_64.rpm
└── www
    └── index.html
```

The `index.html` file is a placeholder for your project files that are
copied to the image and served by NGINX.

The .rpm files can be obtained from the download sections [OSSL pkcs11-provider](/openssl/osslv3/downloads) and the [Primus PKCS#11 library](/pkcs/downloads).

The most unusual thing in the NGINX configuration is the necessity to declare `env SECUROSYS_PKCS11_CONF;` and `env SECUROSYS_SECRETS_CONF;` at the top. The configuration and the secrets file needed by the Primus pkcs11-provider are passed into the container using docker secrets. Thus, they will not be in their primary default location. Their location is is set using environment variables, the lines in the nginx.conf are necessary to ensure that these variables are included in the environment of the worker processes.

The key and the certificate are mounted as a simple volume to `/certificates`. It is not necessary to add special protections to the pkey.pem file as it will only contain a reference to a key on the HSM.

```bash
# For more information on configuration, see:
#   * Official English Documentation: http://nginx.org/en/docs/
#   * Official Russian Documentation: http://nginx.org/ru/docs/

#highlight-warning-start
#
# IMPORTANT
#
# If the location of the configuration and secrets file is passed by
# environment variable, it is necessary to be declared them here.
# Otherwise they will not be passed to the nginx-worker processes.

env SECUROSYS_PKCS11_CONF;
env SECUROSYS_SECRETS_CONF;
#highlight-warning-end

worker_processes auto;
error_log /var/log/nginx/error.log notice;
pid /run/nginx.pid;

# Load dynamic modules. See /usr/share/doc/nginx/README.dynamic.
include /usr/share/nginx/modules/*.conf;

events {
    worker_connections 1024;
}

http {
    log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
                      '$status $body_bytes_sent "$http_referer" '
                      '"$http_user_agent" "$http_x_forwarded_for"';

    access_log  /var/log/nginx/access.log  main;

    sendfile            on;
    tcp_nopush          on;
    keepalive_timeout   65;
    types_hash_max_size 4096;

    include             /etc/nginx/mime.types;
    default_type        application/octet-stream;

    # Load modular configuration files from the /etc/nginx/conf.d directory.
    # See http://nginx.org/en/docs/ngx_core_module.html#include
    # for more information.
    include /opt/app-root/etc/nginx.d/*.conf;

    server {
        listen       443 ssl http2;
        listen       [::]:443 ssl http2;
        server_name  _;
        root         /opt/app-root/src;

#highlight-warning-start
        #
        # IMPORTANT
        #
        # The ssl_certificate_key is not the actual key. The first
        # line of the PEM file should start with
        # "BEGIN PKCS#11 PROVIDER URI" and contain the key's
        # pkcs11-uri. If OpenSSL is setup correctly, the
        # pkcs11-provider will fetch the key handle on the HSM and
        # redirect all private key operations to the HSM.

        ssl_certificate "/certificates/cert.pem";
        ssl_certificate_key "/certificates/pkey.pem";
#highlight-warning-end

        ssl_session_cache shared:SSL:1m;
        ssl_session_timeout  10m;
        ssl_ciphers PROFILE=SYSTEM;
        ssl_prefer_server_ciphers on;

        # Load configuration files for the default server block.
        include /opt/app-root/etc/nginx.default.d/*.conf;

        location = /404.html {
        }

    }
}
```

The Dockerfile to create the image is fairly simple. The pkcs11-provider in the container is installed and enabled with the three highlighted lines.  The rest consists of switching the user to have the correct permissions and copying the nginx configuration and the website file(s).

```dockerfile
FROM registry.access.redhat.com/ubi9/nginx-122

USER 0
# Install the Primus provider and the OSSL pkcs11-provider
RUN --mount=type=bind,source=setup,target=/data \
#highlight-tip-start
    rpm --install /data/PrimusAPI_OSSLv3-Provider-PKCS11-*-rhel8_amd64.rpm && \
    rpm --install /data/PrimusAPI_PKCS11-X-*-rhel8-x86_64.rpm && \
    echo ".include /etc/primus/openssl.cnf" >> /etc/ssl/openssl.cnf && \
#highlight-tip-end
    cp /data/nginx.conf "${NGINX_CONF_PATH}"

USER 1001
# Copy your web page content into the image
RUN --mount=type=bind,source=www,target=/tmp/src \
    cp /tmp/src/*.html ./

CMD nginx -g "daemon off;"
```

### Building the container image

The environment variable `DOCKER_IMAGE_NAME` is introduced, it will be used later when referring to the built image. Given the brevity of the Dockerfile, the image build should be fairly quick.

```bash
DOCKER_IMAGE_NAME=securosys-nginx-example-img
docker build -t "${DOCKER_IMAGE_NAME}" .
```

To reiterate the conditions for running the resulting image successfully:
- The nginx server expects a certificate and its private key in the   directory `/certificates`
- The image only contains the example Primus configuration. The   configuration and secrets to communicate with the HSM need to be injected when starting the container.

### Preparing the key and certificate

For this example a simple self-signed certificate is created. For real applications you probably want to get your certificate [signed by a CA](http://localhost:3000/openssl/osslv3/Use-Cases/self_signed_certificate) and put the certificate chain into the cert.pem-file.

Again some environment variables are set-up as placeholders.

```bash
P11_TOKEN=<YOUR_USER_NAME>
P11_PIN=<YOUR_PKCS#11_PIN>
P11_KEY_NAME=TESTING_NGINX_KEY
```

For the certificate and the key a `certificates` directory is
created. This directory will later be mounted as a volume.

```bash
mkdir -p certificates
```

A RSA-4096 private key is generated

```bash
openssl genpkey -propquery "provider=pkcs11" \
    -algorithm "rsa" -pkeyopt "rsa_keygen_bits:4096" \
    -pkeyopt "pkcs11_uri:pkcs11:token=${P11_TOKEN};object=${P11_KEY_NAME}?pin-value=${P11_PIN}"
```

and the key is used to create a self signed certificate:

```bash
openssl req -new -x509 -copy_extensions=copyall \
    -key "pkcs11:type=private;token=${P11_TOKEN};object=${P11_KEY_NAME}?pin-value=${P11_PIN}" \
    -subj "/C=CH/ST=Bern/L=Bern/O=My Example Organisation/OU=IT Department/CN=www.example.com" \
    -addext "subjectAltName = DNS:www.example.com, DNS:*.www.example.com" \
    -sha256 -days 99 -out certificates/cert.pem
```

Given the encoder of the OpenSSL pkcs11-provider is enabled, the key generation will output "PKCS#11 PROVIDER URI"-pem-file.  For security reasons the "pin-value" is omitted. Using that file would work, provided that the pkcs11-pin can be provided each time the OpenSSL password callback pops up. This is impractical for server applications. Instead, a new key file is created with the additional query parameter `pin-source=file:/run/secrets/securosys_p11_pin` the OpenSSL decoder will then try to read the pkcs11-pin from `/run/secrets/securosys_p11_pin` which is the path to the securosys\_p11\_pin docker secret.

Please note that unlike the uri provided to generate the key, additionally `type=private` is specified in the uri written to the pkey.pem file.

In the script below, the pem-file is created using tools commonly available on *nix systems. Alternatively, the [Python script](https://github.com/latchset/pkcs11-provider/blob/1362378ad3d5f40013bae7562cf7e5d79149925e/tools/uri2pem.py) included in the OpenSSL pkcs11-provider's source can be used.

```bash
make-pkcs11-uri-pem() {
    # helper function to create a PEM file from a pkcs11-uri provided as argument
    export LC_ALL=C
    URI=$1
    URI_HEX=$(printf '%s' "${URI:?}"   | od -An -t x1)
    DESC="PKCS#11 Provider URI v1.0"
    DESC_HEX=$(printf '%s' "${DESC}" | od -An -t x1)
    PEM_HEX=$(printf '30 82 %04x 1a 82 %04x %s 0c 82 %04x %s'  \
		     "$((${#URI} + ${#DESC} + 8))" "${#DESC}" "${DESC_HEX[*]}" \
		     "${#URI}" "${URI_HEX[*]}" \
		  | tr -d '\r\n\t ' | sed -e 's,\(.\{2\}\),\\x\1,g')
    # shellcheck disable=SC2059 # printf should use PEM_HEX as format string
    PEM=$(printf "${PEM_HEX}" | base64)
    printf "%s\n%s\n%s" \
	   "-----BEGIN PKCS#11 PROVIDER URI-----" \
	   "${PEM}" \
	   "-----END PKCS#11 PROVIDER URI-----"
}

#highlight-warning-start
make-pkcs11-uri-pem "pkcs11:type=private;token=${P11_TOKEN};object=${P11_KEY_NAME}?pin-source=file:/run/secrets/securosys_p11_pin" \
    > certificates/pkey.pem
#highlight-warning-end

chmod 644  certificates/cert.pem certificates/pkey.pem
```

The certificates directory is ready, it contains the cert.pem and the pkey.pem referenced in the NGINX configuration. The pkey.pem contains the pkcs11-uri to the private key and a file uri to load the pkcs11-pin.

### Starting the service

In order to create and start the NGINX service, the docker secrets need to be created first.  The docker secrets are part of the [docker swarm orchestrator](https://docs.docker.com/engine/swarm/secrets/). Make sure to only add secrets to a swarm you trust. It is possible that the local docker instance is not running in swarm mode, in that case `docker swarm init --autolock=true` needs to be run to turn the local instance into a docker swarm and make the process below succeed.

The environment variables `SECUROSYS_PKCS11_CONF` and `SECUROSYS_SECRETS_CONF` should contain the path to the configuration and secret file that should be used in the container. They don't need to be the same as you use on the host system but they should allow accessing the private referenced in the pkey.pem.

```bash
docker secret create securosys_primus_cfg "${SECUROSYS_PKCS11_CONF}"
docker secret create securosys_secrets_cfg "${SECUROSYS_SECRETS_CONF}"
docker secret create securosys_p11_pin - <<< "${P11_PIN}"
```

When starting the service, the certificates directory and the secrets are mounted. The environment variables `SECUROSYS_PKCS11_CONF` and `SECUROSYS_SECRETS_CONF` are set in the container such that the Primus pkcs11-provider reads the configuration and secrets from the mounted docker secrets.

```bash
DOCKER_SERVICE_NAME=securosys-nginx-example-svc
DOCKER_HTTPS_PORT=1443

CERTIFICATES_DIR=$(pwd)/certificates

docker service create \
       --name "${DOCKER_SERVICE_NAME}" \
       -p "${DOCKER_HTTPS_PORT}":443 \
       --mount="type=bind,source=${CERTIFICATES_DIR},target=/certificates" \
       --secret securosys_primus_cfg \
       --secret securosys_secrets_cfg \
       --secret securosys_p11_pin \
       --env SECUROSYS_PKCS11_CONF=/run/secrets/securosys_primus_cfg \
       --env SECUROSYS_SECRETS_CONF=/run/secrets/securosys_secrets_cfg \
       "${DOCKER_IMAGE_NAME}"
```

The service should now serve the web content.
```
curl -vk https://localhost:${DOCKER_HTTPS_PORT}
```

</TabItem>
</Tabs>

## More resources

- [Generate a key with OpenSSL](/openssl/osslv3/Tutorial/openssl_cli)
- [Troubleshooting](../Tutorial/troubleshooting)
- [OpenSSL documentation](https://docs.openssl.org/master/)
- [nginx documentation](https://nginx.org/en/docs/)
