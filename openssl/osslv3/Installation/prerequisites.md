---
sidebar_position: 1
title: Step-by-Step Guide - Installing OpenSSL v3
sidebar_label: Prerequisites
description: Discover OpenSSL's PKCS11 provider, CLI commands, installation tips, and troubleshooting. Integrate seamlessly with HSM for enhanced security.
keywords: [OpenSSL PKCS11 provider, OpenSSL PKCS11 API, OpenSSL command line utility (CLI), OpenSSL CLI commands, OpenSSL installation guide, OpenSSL installation troubleshooting, OpenSSL troubleshooting tips, OpenSSL certificate management, OpenSSL certificate creation, OpenSSL certificate renewal, OpenSSL configuration file, OpenSSL configuration options, OpenSSL configuration guide, OpenSSL encryption algorithms, OpenSSL decryption methods, OpenSSL digital signatures, OpenSSL SSL/TLS protocols, OpenSSL SSL/TLS configuration, OpenSSL heartbleed vulnerability, OpenSSL security updates]
tags: [OpenSSL,PKCS#11]
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

<!-- KEEP H1 EMPTY TO OVERWRITE THE TITLE WITH NEXT H2 -->
# Prerequisites
:::info
For the OpenSSL integration to work, the <a href="/pkcs/overview" target="_blank" rel="noopener noreferrer">Securosys Primus PKCS#11 Provider API</a> needs to be installed and configured properly.
Verify proper installation with successful execution of the `ppin -t` command. 
:::

## Supported Platforms
-   Linux
    -   RHEL 9/8 x64
    -   Ubuntu 24/22 x64
    -   Debian 12 x64
    -   other OS available upon request

:::warning
Certain LTS versions of distributions do not support upgrades of packages past certain versions (Ubuntu 22.04 for example). Things like OpenSSL and NGINX require versions higher than what the LTS allows.

If you are using such a version, you will need to [Build From Source (Debian/Ubuntu)](/openssl/osslv3/Installation/prerequisites#build-from-source) your OpenSSL and your NGINX.
:::
## Requirements
- OpenSSL 3.0.7 or newer
- NGINX 1.22.0 or newer
- Apache2 v2.4.62 or newer
- A recent version of opensc (pkcs11-tool)
- A recent version of glibc

## OpenSSL v3

OpenSSL should preferably be installed through the systems package manager.
All recent versions of the major distributions offer a packaged version.

<Tabs groupId="install-method">
<TabItem value="package-manager" label="Package Manager" default>

### Installation using the package manager

**Debian**

In Debian 12 and Ubuntu 22.04 or newer OpenSSL 3 is available from the official package archive.

```bash
sudo apt install openssl
```

**Red Hat**

In Red Hat Enterprise Linux 9, CentOs 9, and Fedora 38 or newer OpenSSL 3 is available from the official package archive.
```bash
sudo dnf install openssl
```

**SUSE**
In SUSE-15 SP4 or new OpenSSL 3 is available from the official package archive.
```bash
sudo zypper install openssl-3
```
</TabItem>
<TabItem value="source" label="From Source">
### Build From Source

On some systems you have to build OpenSSL 3 yourself.
<Tabs groupId="os">
<TabItem value="debian" label="Debian/Ubuntu">
#### Install Build Dependency With apt-get
On Debian/Ubuntu based distribution the following dependencies have to be installed

```bash
apt-get -q update;
apt-get -yq install perl autoconf-archive automake libtool make gcc curl tar gzip;
```
</TabItem>
<TabItem value="rhel" label="RHEL">

#### Install Dependencies With yum
On RHEL based distribution the following dependencies have to be installed
 ```bash
yum install -y --skip-broken perl-core autoconf automake libtool make gcc curl tar gzip;
```
</TabItem>
<TabItem value="container" label="Container" default>

It is possible to build OpenSSL 3 for your target system inside a
docker container. Most Linux distributions offer an official docker
image.

You can download the script to build OpenSSL 3 in a docker container here:
- [containerized-openssl-3-builder-debian.sh](../resources/scripts/containerized-openssl3-builder-debian.sh)
- [containerized-openssl-3-builder-rhel.sh](../resources/scripts/containerized-openssl3-builder-rhel.sh)

In the script the code shown in [Build OpenSSL][#build-openssl] below
is executed inside a container. The command used in the script to
create the container is this:

```bash
docker run --rm \
	--env IUSER="$(id -u)" \
	--env IGRP="$(id -g)" \
	--env OPENSSL_VERSION  \
	--volume "$(pwd)/openssl-${OPENSSL_VERSION}-bin:/tmp/out" \
	"${DISTRO_IMAGE_NAME}" bash -ce \
	...
```

A docker container using the image `${DISTRO_IMAGE_NAME}` this
variable is set near the top of the script. This can be changed to use
a container image that fits best your target system. The following
images have been successfully tested (on 2024-02-05):

- With the Debian script:
  "debian:10", "debian:11", "ubuntu:18.04", "ubuntu:20.04"

- With the RHEL script:
  "centos:7" "rockylinux:8" "redhat/ubi8" "fedora:37"

The script creates a new directory
_openssl-\$\{OPENSSL\_VERSION\}-bin_.  It is mounted as a volume
inside the container. The output will be placed in that directory. In
order to fix the ownership of the produced file, the uid and gid of
the current user are passed to the container as well.

</TabItem>
</Tabs>

#### Build OpenSSL

:::warning

The script below creates binaries with their _openssldir_ set to
 _/opt/openssl-$\{OPENSSL\_VERSION\}/ssl_. This means, that the
 default openssl configuration is
 _/opt/openssl-$\{OPENSSL\_VERSION\}/ssl/openssl.cnf_.

 This is necessary to avoid collisions with the OpenSSL version
 distributed by the package manager.

:::

```bash showLineNumbers
OPENSSL_VERSION=3.2.1;

TMPDIR=$(realpath "${TMPDIR:-/tmp}");

echo "-- Downloading openssl-${OPENSSL_VERSION}";
curl -L "https://github.com/openssl/openssl/releases/download/openssl-${OPENSSL_VERSION}/openssl-${OPENSSL_VERSION}.tar.gz" -o "${TMPDIR}/openssl-${OPENSSL_VERSION}.tar.gz";
cd "${TMPDIR}";

echo "-- Extracting openssl-${OPENSSL_VERSION}";
tar xf "openssl-${OPENSSL_VERSION}.tar.gz";
mkdir -p "${TMPDIR}/build" "${TMPDIR}/inst" "${TMPDIR}/out";
cd "${TMPDIR}/build";

echo "-- Configuring openssl-${OPENSSL_VERSION}";
"../openssl-${OPENSSL_VERSION}/Configure" --libdir=lib --prefix="/opt/openssl-${OPENSSL_VERSION}" --openssldir="/opt/openssl-${OPENSSL_VERSION}/ssl";

echo "-- Compiling  openssl-${OPENSSL_VERSION}  (this may take a while)";
p="$(getconf _NPROCESSORS_ONLN)";
make --silent -j "${p}" -l "${p}" build_sw;
make --silent -j "${p}" -l "${p}" test;
make --silent -j "${p}" -l "${p}" install_sw install_ssldirs DESTDIR="${TMPDIR}/inst";

echo "-- Zipping binary openssl-${OPENSSL_VERSION}";
cd "${TMPDIR}/inst/opt/";
tar czf "${TMPDIR}/out/openssl-${OPENSSL_VERSION}.bin.tgz" .;
chown "root:root" "${TMPDIR}/out/openssl-${OPENSSL_VERSION}.bin.tgz";

echo "-- Build complete";
echo "-- To install OpenSSL on this system execute:";
echo "-- Untarring into /opt with sudo";
cd /opt && sudo tar -xf  "${TMPDIR}/out/openssl-${OPENSSL_VERSION}.bin.tgz .";
```
#### Post Build 
Point your `LD_LIBRARY_PATH` to use the library of the sourced OpenSSL. Otherwise the applications try to use the system libraries.

```bash
export LD_LIBRARY_PATH=/opt/openssl-${OPENSSL_VERSION}/lib
```

Expected output when performing `openssl version -a` with OpenSSL v `3.0.15`
```bash {3,8}
openssl version -a

OpenSSL 3.0.2 15 Mar 2022 (Library: OpenSSL 3.0.15 3 Sep 2024)
built on: Wed Oct 30 08:49:28 2024 UTC
platform: linux-x86_64
options:  bn(64,64)
compiler: gcc -fPIC -pthread -m64 -Wa,--noexecstack -Wall -O3 -DOPENSSL_USE_NODELETE -DL_ENDIAN -DOPENSSL_PIC -DOPENSSL_BUILDING_OPENSSL -DNDEBUG
OPENSSLDIR: "/opt/openssl-3.0.15/ssl"
ENGINESDIR: "/opt/openssl-3.0.15/lib/engines-3"
MODULESDIR: "/opt/openssl-3.0.15/lib/ossl-modules"
Seeding source: os-specific
CPUINFO: OPENSSL_ia32cap=0xc2da2203478bffff:0x20842509
```

</TabItem>
</Tabs>

## Further content

- [Installation](/openssl/osslv3/Installation/installation.md)
- [Configuration](/openssl/osslv3/Installation/configuration.md)
- [Generate a key with OpenSSL](/openssl/osslv3/Tutorial/openssl_cli)
