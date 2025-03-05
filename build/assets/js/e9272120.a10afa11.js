"use strict";(self.webpackChunksecurosys_docs=self.webpackChunksecurosys_docs||[]).push([[48409],{28453:(e,n,t)=>{t.d(n,{R:()=>a,x:()=>o});var r=t(96540);const i={},s=r.createContext(i);function a(e){const n=r.useContext(s);return r.useMemo((function(){return"function"==typeof e?e(n):{...n,...e}}),[n,e])}function o(e){let n;return n=e.disableParentContext?"function"==typeof e.components?e.components(i):e.components||i:a(e.components),r.createElement(s.Provider,{value:n},e.children)}},30039:(e,n,t)=>{t.r(n),t.d(n,{assets:()=>l,contentTitle:()=>o,default:()=>d,frontMatter:()=>a,metadata:()=>r,toc:()=>c});const r=JSON.parse('{"id":"osslv3/Use-Cases/apache","title":"Configuring Apache HTTP Server for OpenSSL v3","description":"Discover OpenSSL\'s PKCS11 provider, CLI commands, installation tips, and troubleshooting. Integrate seamlessly with HSM for enhanced security.","source":"@site/openssl/osslv3/Use-Cases/apache.md","sourceDirName":"osslv3/Use-Cases","slug":"/osslv3/Use-Cases/apache","permalink":"/openssl/osslv3/Use-Cases/apache","draft":false,"unlisted":false,"tags":[{"inline":true,"label":"OpenSSL","permalink":"/openssl/tags/open-ssl"},{"inline":true,"label":"PKCS#11","permalink":"/openssl/tags/pkcs-11"},{"inline":true,"label":"Apache","permalink":"/openssl/tags/apache"}],"version":"current","lastUpdatedAt":1741162132000,"sidebarPosition":1,"frontMatter":{"sidebar_position":1,"title":"Configuring Apache HTTP Server for OpenSSL v3","sidebar_label":"Apache HTTP Server","description":"Discover OpenSSL\'s PKCS11 provider, CLI commands, installation tips, and troubleshooting. Integrate seamlessly with HSM for enhanced security.","keywords":["OpenSSL PKCS11 provider","OpenSSL PKCS11 API","OpenSSL command line utility (CLI)","OpenSSL CLI commands","OpenSSL installation guide","OpenSSL installation troubleshooting","OpenSSL troubleshooting tips","OpenSSL certificate management","OpenSSL certificate creation","OpenSSL certificate renewal","OpenSSL configuration file","OpenSSL configuration options","OpenSSL configuration guide","OpenSSL encryption algorithms","OpenSSL decryption methods","OpenSSL digital signatures","OpenSSL SSL/TLS protocols","OpenSSL SSL/TLS configuration","OpenSSL heartbleed vulnerability","OpenSSL security updates"],"tags":["OpenSSL","PKCS#11","Apache"]},"sidebar":"openssl","previous":{"title":"Use Cases","permalink":"/openssl/category/use-cases"},"next":{"title":"NGINX","permalink":"/openssl/osslv3/Use-Cases/nginx"}}');var i=t(74848),s=t(28453);const a={sidebar_position:1,title:"Configuring Apache HTTP Server for OpenSSL v3",sidebar_label:"Apache HTTP Server",description:"Discover OpenSSL's PKCS11 provider, CLI commands, installation tips, and troubleshooting. Integrate seamlessly with HSM for enhanced security.",keywords:["OpenSSL PKCS11 provider","OpenSSL PKCS11 API","OpenSSL command line utility (CLI)","OpenSSL CLI commands","OpenSSL installation guide","OpenSSL installation troubleshooting","OpenSSL troubleshooting tips","OpenSSL certificate management","OpenSSL certificate creation","OpenSSL certificate renewal","OpenSSL configuration file","OpenSSL configuration options","OpenSSL configuration guide","OpenSSL encryption algorithms","OpenSSL decryption methods","OpenSSL digital signatures","OpenSSL SSL/TLS protocols","OpenSSL SSL/TLS configuration","OpenSSL heartbleed vulnerability","OpenSSL security updates"],tags:["OpenSSL","PKCS#11","Apache"]},o="Apache HTTP Server",l={},c=[{value:"Preparation and Prerequisites",id:"preparation-and-prerequisites",level:2},{value:"Install Apache2 (httpd)",id:"install-apache2-httpd",level:2},{value:"Preparing the key and certificate to be used",id:"preparing-the-key-and-certificate-to-be-used",level:3},{value:"Adapt the site configuration to use the key from the HSM",id:"adapt-the-site-configuration-to-use-the-key-from-the-hsm",level:3},{value:"Test the configuration",id:"test-the-configuration",level:3},{value:"Start the apache service",id:"start-the-apache-service",level:3},{value:"Webserver optimization for HSM access",id:"webserver-optimization-for-hsm-access",level:2},{value:"More resources",id:"more-resources",level:2}];function p(e){const n={a:"a",admonition:"admonition",code:"code",em:"em",h1:"h1",h2:"h2",h3:"h3",header:"header",li:"li",mdxAdmonitionTitle:"mdxAdmonitionTitle",ol:"ol",p:"p",pre:"pre",strong:"strong",ul:"ul",...(0,s.R)(),...e.components},{Details:t}=n;return t||function(e,n){throw new Error("Expected "+(n?"component":"object")+" `"+e+"` to be defined: you likely forgot to import, pass, or provide it.")}("Details",!0),(0,i.jsxs)(i.Fragment,{children:[(0,i.jsx)(n.header,{children:(0,i.jsx)(n.h1,{id:"apache-http-server",children:"Apache HTTP Server"})}),"\n",(0,i.jsx)(n.p,{children:"The following example shows how to set up Apache2 (httpd) HTTP server to work with the OpenSSLv3 pkcs11-provider."}),"\n",(0,i.jsx)(n.h2,{id:"preparation-and-prerequisites",children:"Preparation and Prerequisites"}),"\n",(0,i.jsxs)(n.ol,{children:["\n",(0,i.jsxs)(n.li,{children:["\n",(0,i.jsxs)(n.p,{children:["Make sure that Primus HSM PKCS#11 Provider is installed in your system following the ",(0,i.jsx)("a",{href:"/pkcs/Installation/pkcs11_provider_installation",target:"_blank",rel:"noopener noreferrer",children:"PKCS#11 Provider instructions"}),"."]}),"\n",(0,i.jsx)(n.p,{children:"Confirm the successful installation by executing the following test command and checking the output:"}),"\n",(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-bash",metastring:"{9}",children:"ppin -t\n\nLoad config file: '/etc/primus/primus.cfg'\n\nhsm0: Connect to 'grimsel.securosys.ch' on port 2410, firmware: RX-3.1.0-T\n    slot0 (id=0), user=<your partition name>: OK\n\nNumber of tested HSMs: 1 (number of partitions: 1)    \nNumber of failures: 0    \n"})}),"\n"]}),"\n",(0,i.jsxs)(n.li,{children:["\n",(0,i.jsxs)(n.p,{children:["Make sure that the Securosys OpenSSL pkcs11-provider is installed on your system, as it is described on the ",(0,i.jsx)("a",{href:"/openssl/osslv3/quickstart",target:"_blank",rel:"noopener noreferrer",children:"Quickstart"}),"."]}),"\n"]}),"\n",(0,i.jsxs)(n.li,{children:["\n",(0,i.jsxs)(n.p,{children:["Make sure that your OpenSSL version is aligned with the ",(0,i.jsx)("a",{href:"/openssl/osslv3/Installation/prerequisites",target:"_blank",rel:"noopener noreferrer",children:"Prerequisites"}),"."]}),"\n"]}),"\n",(0,i.jsxs)(n.li,{children:["\n",(0,i.jsxs)(n.p,{children:["Configure your OpenSSL according to ",(0,i.jsx)("a",{href:"/openssl/osslv3/Installation/configuration",target:"_blank",rel:"noopener noreferrer",children:"Configuration"}),"."]}),"\n",(0,i.jsxs)(t,{children:[(0,i.jsx)("summary",{children:" Click to see a configuration example"}),(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-bash",metastring:"{19-35}",children:"HOME            = .\n\n# Use this in order to automatically load providers.\nopenssl_conf = openssl_init\n\n# Comment out the next line to ignore configuration errors\nconfig_diagnostics = 1\n\noid_section = new_oids\n\n[ new_oids ]\n\n[openssl_init]\nproviders = provider_sect\n\n[random_sect]\nrandom = PKCS11-RAND\n\n[provider_sect]\ndefault = default_sect\nbase = base_sect\npkcs11 = pkcs11_section\n\n[base_sect]\nactivate = 1\n\n[default_sect]\nactivate = 1\n\n[pkcs11_section]\nmodule = /opt/openssl-3.3.2/lib/ossl-modules/pkcs11.so\npkcs11-module-path = /usr/local/primus/lib/libprimusP11.so\npkcs11-module-load-behavior = early\npkcs11-module-quirks = no-deinit no-operation-state\nactivate = 1\n\n####################################################################\n[ ca ]\ndefault_ca    = CA_default        # The default ca section\n\n####################################################################\n[ CA_default ]\n\ndir        = ./demoCA        # Where everything is kept\ncerts      = $dir/certs      # Where the issued certs are kept\ncrl_dir    = $dir/crl        # Where the issued crl are kept\ndatabase   = $dir/index.txt  # database index file.\n#unique_subject = no          # Set to 'no' to allow creation of\n                             # several certs with same subject.\nnew_certs_dir = $dir/newcerts # default place for new certs.\n\ncertificate  = $dir/cacert.pem   # The CA certificate\nserial       = $dir/serial       # The current serial number\ncrlnumber    = $dir/crlnumber    # the current crl number\n                                # must be commented out to leave a V1 CRL\ncrl          = $dir/crl.pem      # The current CRL\nprivate_key  = $dir/private/cakey.pem # The private key\n\nx509_extensions = usr_cert    # The extensions to add to the cert\n\nname_opt    = ca_default      # Subject Name options\ncert_opt    = ca_default      # Certificate field options\n\ndefault_days    = 365          # how long to certify for\ndefault_crl_days= 30           # how long before next CRL\ndefault_md      = default      # use public key default MD\npreserve        = no           # keep passed DN ordering\n\npolicy      = policy_match\n\n# For the CA policy\n[ policy_match ]\ncountryName             = match\nstateOrProvinceName     = match\norganizationName        = match\norganizationalUnitName  = optional\ncommonName              = supplied\nemailAddress            = optional\n\n# For the 'anything' policy\n# At this point in time, you must list all acceptable 'object'\n# types.\n[ policy_anything ]\ncountryName             = optional\nstateOrProvinceName     = optional\nlocalityName            = optional\norganizationName        = optional\norganizationalUnitName  = optional\ncommonName              = supplied\nemailAddress            = optional\n\n####################################################################\n[ req ]\ndefault_bits        = 2048\ndefault_keyfile     = privkey.pem\ndistinguished_name  = req_distinguished_name\nattributes          = req_attributes\nx509_extensions     = v3_ca   # The extensions to add to the self-signed cert\n\nstring_mask = utf8only\n\n# req_extensions = v3_req # The extensions to add to a certificate request\n\n[ req_distinguished_name ]\ncountryName            = Country Name (2 letter code)\ncountryName_default    = AU\ncountryName_min        = 2\ncountryName_max        = 2\n\nstateOrProvinceName    = State or Province Name (full name)\nstateOrProvinceName_default = Some-State\n\nlocalityName           = Locality Name (eg, city)\n\n0.organizationName     = Organization Name (eg, company)\n0.organizationName_default = Internet Widgits Pty Ltd\n\norganizationalUnitName     = Organizational Unit Name (eg, section)\n#organizationalUnitName_default =\n\ncommonName           = Common Name (e.g. server FQDN or YOUR name)\ncommonName_max       = 64\n\nemailAddress         = Email Address\nemailAddress_max     = 64\n\n# SET-ex3           = SET extension number 3\n\n[ req_attributes ]\nchallengePassword        = A challenge password\nchallengePassword_min    = 4\nchallengePassword_max    = 20\n\nunstructuredName         = An optional company name\n\n[ usr_cert ]\n\nbasicConstraints=CA:FALSE\n\n# PKIX recommendations harmless if included in all certificates.\nsubjectKeyIdentifier=hash\nauthorityKeyIdentifier=keyid,issuer\n\n[ v3_req ]\n\nbasicConstraints = CA:FALSE\nkeyUsage = nonRepudiation, digitalSignature, keyEncipherment\n\n[ v3_ca ]\n\n# Extensions for a typical CA\n\n# PKIX recommendation.\nsubjectKeyIdentifier=hash\nauthorityKeyIdentifier=keyid:always,issuer\nbasicConstraints = critical,CA:true\n\n"})})]}),"\n",(0,i.jsxs)(n.admonition,{type:"note",children:[(0,i.jsx)(n.p,{children:"If you haven't installed natively openssl, you might need to configure the below environment variables:"}),(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-bash",children:"export LD_LIBRARY_PATH=path to the OpenSSL libraries:$LD_LIBRARY_PATH\nexport OPENSSL_DIR=path to the installation directory for OpenSSL\n"})})]}),"\n"]}),"\n"]}),"\n",(0,i.jsx)(n.h2,{id:"install-apache2-httpd",children:"Install Apache2 (httpd)"}),"\n",(0,i.jsx)(n.p,{children:"Update the package list and install Apache2 if not previously installed:"}),"\n",(0,i.jsxs)(n.admonition,{type:"tip",children:[(0,i.jsx)(n.mdxAdmonitionTitle,{}),(0,i.jsx)(n.p,{children:"On RHEL/CentOS the webserver is named httpd instead of apache2.\nThe below command sequences are shown for Ubuntu only."})]}),"\n",(0,i.jsx)(n.p,{children:(0,i.jsx)(n.strong,{children:"Ubuntu/Debian"})}),"\n",(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-bash",children:"sudo apt update\nsudo apt install apache2\n"})}),"\n",(0,i.jsxs)(n.p,{children:["Verify that you have at least Version ",(0,i.jsx)(n.strong,{children:"2.4.62 (or newer)"})," on your system:"]}),"\n",(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-bash",children:"apache2 -v\n"})}),"\n",(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-text",metastring:"{1}",children:"Server version: Apache/2.4.62 (Ubuntu)\nServer built:   2024-08-15T07:32:14\n"})}),"\n",(0,i.jsx)(n.h3,{id:"preparing-the-key-and-certificate-to-be-used",children:"Preparing the key and certificate to be used"}),"\n",(0,i.jsxs)(n.p,{children:["For this example a simple self-signed certificate is created. For real applications you probably want to get your certificate ",(0,i.jsx)("a",{href:"/openssl/osslv3/Use-Cases/self_signed_certificate",target:"_blank",rel:"noopener noreferrer",children:"signed by a CA"})," and put the certificate chain into the cert.pem-file."]}),"\n",(0,i.jsx)(n.p,{children:"Again some environment variables are set-up as placeholders."}),"\n",(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-bash",children:"export P11_TOKEN=<YOUR_USER_NAME>\t# partition name\nexport P11_PIN=<YOUR_PKCS#11_PIN>\t# hsm pkcs11 pin (don't use for production)\nexport P11_KEY_NAME=TESTING_APACHE2_KEY\t# name of Key\n"})}),"\n",(0,i.jsx)(n.p,{children:"A RSA-4096 private key is generated"}),"\n",(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-bash",children:'openssl genpkey -propquery "provider=pkcs11" \\\n    -algorithm "rsa" -pkeyopt "rsa_keygen_bits:4096" \\\n    -pkeyopt "pkcs11_uri:pkcs11:token=${P11_TOKEN};object=${P11_KEY_NAME}?pin-value=${P11_PIN}"\n'})}),"\n",(0,i.jsx)(n.p,{children:'Given the encoder of the OpenSSL pkcs11-provider is enabled, the key will be outputted as a "PKCS#11 PROVIDER URI" pem file.'}),"\n",(0,i.jsx)(n.p,{children:"Use the key to create a self signed certificate"}),"\n",(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-bash",children:'sudo openssl req -new -x509 -copy_extensions=copyall \\\n    -key "pkcs11:type=private;token=${P11_TOKEN};object=${P11_KEY_NAME}?pin-value=${P11_PIN}" \\\n    -subj "/C=CH/ST=Bern/L=Bern/O=My Example Organisation/OU=IT Department/CN=www.example.com" \\\n    -addext "subjectAltName = DNS:www.example.com, DNS:*.www.example.com" \\\n    -sha256 -days 99 -out /etc/apache2/ssl/p11-provider-cert.pem\n'})}),"\n",(0,i.jsx)(n.h3,{id:"adapt-the-site-configuration-to-use-the-key-from-the-hsm",children:"Adapt the site configuration to use the key from the HSM"}),"\n",(0,i.jsx)(n.admonition,{title:"Take care",type:"tip",children:(0,i.jsx)(n.p,{children:"Ensure that your web-site is running well using a local key/certificate from file before changing to private key loaded from the HSM."})}),"\n",(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-bash",children:'sudo vi /etc/apache2/sites-available/<your-site.conf>\n  ...\n  # Certificate from local file\n  SSLCertificateFile      /etc/apache2/ssl/p11-provider-cert.pem\n  # Private key from Primus HSM or CloudsHSM\n  SSLCertificateKeyFile   "pkcs11:token=<your-partition>;pin-value=<pkcs11-pin>;object=<key-label>;type=private"\n  ...\n'})}),"\n",(0,i.jsx)(n.h3,{id:"test-the-configuration",children:"Test the configuration"}),"\n",(0,i.jsx)(n.p,{children:"Perform a configuration test and verify that no errors are reported."}),"\n",(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-bash",children:"apachectl configtest\n"})}),"\n",(0,i.jsx)(n.h3,{id:"start-the-apache-service",children:"Start the apache service"}),"\n",(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-bash",children:"sudo systemctl daemon-reload\nsudo systemctl restart apache2\n"})}),"\n",(0,i.jsx)(n.h2,{id:"webserver-optimization-for-hsm-access",children:"Webserver optimization for HSM access"}),"\n",(0,i.jsxs)(n.admonition,{type:"warning",children:[(0,i.jsx)(n.p,{children:"If the network connection to the HSM has high latency (e.g. CloudsHSM), it is important to parallelize HSM requests."}),(0,i.jsx)(n.p,{children:"Based on our experience, mpm_prefork achieves better results than mpm_event module."}),(0,i.jsx)(n.p,{children:"Note that Apache2 performance tuning requires profound knowledge of the apache webserver, mechanisms and configuration."})]}),"\n",(0,i.jsxs)(t,{children:[(0,i.jsx)("summary",{children:" Click to expand"}),(0,i.jsx)(n.p,{children:(0,i.jsx)(n.em,{children:(0,i.jsx)(n.strong,{children:"Performance optimization for mpm_event:"})})}),(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-bash",children:"vi /etc/apache2/mods-enabled/mpm_event.conf\n\nStartServers            3\nMinSpareThreads         75\nMaxSpareThreads         250\nThreadLimit             64\nThreadsPerChild         25\nMaxRequestWorkers       400\nMaxConnectionsPerChild  0\n"})}),(0,i.jsx)(n.p,{children:(0,i.jsx)(n.em,{children:(0,i.jsx)(n.strong,{children:"Performance optimization for mpm_prefork:"})})}),(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-bash",children:"vi /etc/apache2/mods-enabled/mpm_prefork.conf\n\nStartServers            5\nMinSpareServers         5\nMaxSpareServers         10\nMaxRequestWorkers       150\nMaxConnectionsPerChild  0\n"})}),(0,i.jsx)(n.p,{children:"To change from mpm_event to mpm_prefork:"}),(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-bash",children:"sudo a2dismod mpm_event\nsudo a2dismod mpm_worker\nsudo a2enmod mpm_prefork\nsudo systemctl restart apache2\napache2ctl -V | grep MPM\n"})})]}),"\n",(0,i.jsx)(n.h2,{id:"more-resources",children:"More resources"}),"\n",(0,i.jsxs)(n.ul,{children:["\n",(0,i.jsx)(n.li,{children:(0,i.jsx)(n.a,{href:"/openssl/osslv3/Tutorial/openssl_cli",children:"Generate a key with OpenSSL"})}),"\n",(0,i.jsx)(n.li,{children:(0,i.jsx)(n.a,{href:"../Tutorial/troubleshooting",children:"Troubleshooting"})}),"\n",(0,i.jsx)(n.li,{children:(0,i.jsx)(n.a,{href:"https://docs.openssl.org/master/",children:"OpenSSL documentation"})}),"\n",(0,i.jsx)(n.li,{children:(0,i.jsx)(n.a,{href:"https://httpd.apache.org/docs/",children:"Apache documentation"})}),"\n"]})]})}function d(e={}){const{wrapper:n}={...(0,s.R)(),...e.components};return n?(0,i.jsx)(n,{...e,children:(0,i.jsx)(p,{...e})}):p(e)}}}]);