"use strict";(self.webpackChunksecurosys_docs=self.webpackChunksecurosys_docs||[]).push([[92128],{28453:(e,n,i)=>{i.d(n,{R:()=>d,x:()=>c});var r=i(96540);const s={},t=r.createContext(s);function d(e){const n=r.useContext(t);return r.useMemo((function(){return"function"==typeof e?e(n):{...n,...e}}),[n,e])}function c(e){let n;return n=e.disableParentContext?"function"==typeof e.components?e.components(s):e.components||s:d(e.components),r.createElement(t.Provider,{value:n},e.children)}},85739:(e,n,i)=>{i.r(n),i.d(n,{assets:()=>o,contentTitle:()=>c,default:()=>h,frontMatter:()=>d,metadata:()=>r,toc:()=>l});const r=JSON.parse('{"id":"Tutorial/KSP-utils","title":"CNG/KSP Provider - Testing & Key Management","description":"Key Management Microsoft CNG for Securosys Hardware Security Modules (HSMs)","source":"@site/mscng/Tutorial/KSP-utils.md","sourceDirName":"Tutorial","slug":"/Tutorial/KSP-utils","permalink":"/mscng/Tutorial/KSP-utils","draft":false,"unlisted":false,"tags":[],"version":"current","lastUpdatedAt":1741162132000,"sidebarPosition":2,"frontMatter":{"sidebar_position":2,"title":"CNG/KSP Provider - Testing & Key Management","sidebar_label":"Testing & Key Management","description":"Key Management Microsoft CNG for Securosys Hardware Security Modules (HSMs)","keywords":["hsm","cloud hsm"]},"sidebar":"tutorialSidebar","previous":{"title":"Key Accessibility","permalink":"/mscng/Tutorial/Key-Access"},"next":{"title":"Logging","permalink":"/mscng/Tutorial/Logging"}}');var s=i(74848),t=i(28453);const d={sidebar_position:2,title:"CNG/KSP Provider - Testing & Key Management",sidebar_label:"Testing & Key Management",description:"Key Management Microsoft CNG for Securosys Hardware Security Modules (HSMs)",keywords:["hsm","cloud hsm"]},c=void 0,o={},l=[{value:"Help",id:"help",level:2},{value:"HSM Connection Status and Details",id:"hsm-connection-status-and-details",level:2},{value:"Enumerate CNG Providers",id:"enumerate-cng-providers",level:2},{value:"Enumerate Algorithms",id:"enumerate-algorithms",level:2},{value:"Enumerate Algorithm Operations",id:"enumerate-algorithm-operations",level:2},{value:"Enumerate Keys",id:"enumerate-keys",level:2},{value:"Change Key Owner (Accessibility)",id:"change-key-owner-accessibility",level:2},{value:"Create Key",id:"create-key",level:2},{value:"Delete Key",id:"delete-key",level:2}];function a(e){const n={a:"a",admonition:"admonition",code:"code",h2:"h2",li:"li",ol:"ol",p:"p",pre:"pre",section:"section",strong:"strong",sup:"sup",table:"table",tbody:"tbody",td:"td",th:"th",thead:"thead",tr:"tr",ul:"ul",...(0,t.R)(),...e.components};return(0,s.jsxs)(s.Fragment,{children:[(0,s.jsx)(n.admonition,{title:"Note",type:"info",children:(0,s.jsxs)(n.p,{children:["This tutorial applies to ",(0,s.jsx)(n.code,{children:"ksputilcons.exe"})," ",(0,s.jsx)(n.strong,{children:"V1.50.2 or newer"}),"."]})}),"\n",(0,s.jsxs)(n.p,{children:["In addition to the Microsoft Windows certificate utilities (e.g. CertMgr, CertUtil) which are usefull for testing and managing CNG objects, Securosys has developed ",(0,s.jsx)(n.code,{children:"ksputilcons.exe"}),", a command line utility aiming to provide Securosys Primus CNG/KSP provider users with the possibility to:"]}),"\n",(0,s.jsxs)(n.ul,{children:["\n",(0,s.jsx)(n.li,{children:"Independently test the Securosys CNG/KSP provider,"}),"\n",(0,s.jsx)(n.li,{children:"Create and delete specific keys,"}),"\n",(0,s.jsxs)(n.li,{children:["Resolve ",(0,s.jsx)(n.a,{href:"#change-key-owner-accessibility",children:"Key Accessibility"})," issues, such as upgrade incompatibility or transferring accounts between domains (by changing the key owner)."]}),"\n"]}),"\n",(0,s.jsxs)(n.p,{children:["The ",(0,s.jsx)(n.code,{children:"ksputilcons.exe"})," is automatically installed on the device when the Primus CNG/KSP provider is installed. By default the utility is installed within the ",(0,s.jsx)(n.code,{children:"%ProgramFiles%"})," folder: ",(0,s.jsx)(n.code,{children:'"%ProgramFiles%\\Securosys\\PrimusHsmKsp\\ksputilcons.exe"'})]}),"\n",(0,s.jsxs)(n.p,{children:["See chapter ",(0,s.jsx)(n.a,{href:"/mscng/Installation/installation-GUI",children:"Installation"})," for more information about the Primus CNG/KSP provider installation."]}),"\n",(0,s.jsxs)(n.p,{children:["The utility provides the following commands",(0,s.jsx)(n.sup,{children:(0,s.jsx)(n.a,{href:"#user-content-fn-1",id:"user-content-fnref-1","data-footnote-ref":!0,"aria-describedby":"footnote-label",children:"1"})}),":"]}),"\n",(0,s.jsxs)(n.table,{children:[(0,s.jsx)(n.thead,{children:(0,s.jsxs)(n.tr,{children:[(0,s.jsx)(n.th,{children:"Command"}),(0,s.jsx)(n.th,{children:"Description"})]})}),(0,s.jsxs)(n.tbody,{children:[(0,s.jsxs)(n.tr,{children:[(0,s.jsx)(n.td,{children:(0,s.jsx)(n.a,{href:"#help",children:(0,s.jsx)(n.code,{children:"ksputilcons help"})})}),(0,s.jsx)(n.td,{children:"Display help text"})]}),(0,s.jsxs)(n.tr,{children:[(0,s.jsx)(n.td,{children:(0,s.jsx)(n.a,{href:"#hsm-connection-status-and-details",children:(0,s.jsx)(n.code,{children:"ksputilcons hsmstatus [-v -p <provider>]"})})}),(0,s.jsx)(n.td,{children:"HSM connection status"})]}),(0,s.jsxs)(n.tr,{children:[(0,s.jsx)(n.td,{children:(0,s.jsx)(n.a,{href:"#enumerate-cng-providers",children:(0,s.jsx)(n.code,{children:"ksputilcons enumprovs [-v]"})})}),(0,s.jsx)(n.td,{children:"Enumerate all CNG providers"})]}),(0,s.jsxs)(n.tr,{children:[(0,s.jsx)(n.td,{children:(0,s.jsx)(n.a,{href:"#enumerate-algorithms",children:(0,s.jsx)(n.code,{children:"ksputilcons enumalgs [-v -p <provider>]"})})}),(0,s.jsx)(n.td,{children:"Enumerate algorithms"})]}),(0,s.jsxs)(n.tr,{children:[(0,s.jsx)(n.td,{children:(0,s.jsx)(n.a,{href:"#enumerate-algorithm-operations",children:(0,s.jsx)(n.code,{children:"ksputilcons enumalgops [-v -p <provider>]"})})}),(0,s.jsx)(n.td,{children:"Enumerate algorithm operations"})]}),(0,s.jsxs)(n.tr,{children:[(0,s.jsx)(n.td,{children:(0,s.jsx)(n.a,{href:"#enumerate-keys",children:(0,s.jsx)(n.code,{children:"ksputilcons enumkeys [-v -m -a -p <provider>]"})})}),(0,s.jsx)(n.td,{children:"Enumerate keys"})]}),(0,s.jsxs)(n.tr,{children:[(0,s.jsx)(n.td,{children:(0,s.jsx)(n.a,{href:"#change-key-owner-accessibility",children:(0,s.jsx)(n.code,{children:"ksputilcons chkeysowner <name> <oldowner> <newowner> [-v -p <provider> --passwd <password>]"})})}),(0,s.jsx)(n.td,{children:"Change the key owner (KeyPrefix)"})]}),(0,s.jsxs)(n.tr,{children:[(0,s.jsx)(n.td,{children:(0,s.jsx)(n.a,{href:"#create-key",children:(0,s.jsx)(n.code,{children:"ksputilcons createkey [-v -m -p <provider>]"})})}),(0,s.jsx)(n.td,{children:"Create key"})]}),(0,s.jsxs)(n.tr,{children:[(0,s.jsx)(n.td,{children:(0,s.jsx)(n.a,{href:"#delete-key",children:(0,s.jsx)(n.code,{children:"ksputilcons deletekey[-v -m -p <provider>]"})})}),(0,s.jsx)(n.td,{children:"Delete key"})]})]})]}),"\n",(0,s.jsx)(n.h2,{id:"help",children:"Help"}),"\n",(0,s.jsx)(n.p,{children:"Display the help text and exit."}),"\n",(0,s.jsx)(n.pre,{children:(0,s.jsx)(n.code,{children:"ksputilcons help\n"})}),"\n",(0,s.jsx)(n.h2,{id:"hsm-connection-status-and-details",children:"HSM Connection Status and Details"}),"\n",(0,s.jsxs)(n.p,{children:["The command ",(0,s.jsx)(n.code,{children:"ksputilcons hsmstatus"})," offers a convenient method to verify whether the CNG/KSP configuration is complete, ensure connectivity to the HSMs, and check the HSM firmware version.\nIssue a status report about the connectivity and properties of all configured HSMs of the Primus HSM provider."]}),"\n",(0,s.jsx)(n.pre,{children:(0,s.jsx)(n.code,{children:"ksputilcons hsmstatus [-v -p <provider>]\n"})}),"\n",(0,s.jsx)(n.p,{children:"Where:"}),"\n",(0,s.jsxs)(n.table,{children:[(0,s.jsx)(n.thead,{children:(0,s.jsxs)(n.tr,{children:[(0,s.jsx)(n.th,{children:"Parameter"}),(0,s.jsx)(n.th,{children:"Description"})]})}),(0,s.jsxs)(n.tbody,{children:[(0,s.jsxs)(n.tr,{children:[(0,s.jsx)(n.td,{children:(0,s.jsx)(n.code,{children:"-v"})}),(0,s.jsx)(n.td,{children:"Optional. Increase the verbosity of the command"})]}),(0,s.jsxs)(n.tr,{children:[(0,s.jsx)(n.td,{children:(0,s.jsx)(n.code,{children:"-p <provider>"})}),(0,s.jsxs)(n.td,{children:["Optional. If more than one HSM provider exists, specify it with the ",(0,s.jsx)(n.code,{children:"-p"})," option and replace ",(0,s.jsx)(n.code,{children:"<provider>"})," with the provider name. If only one Primus HSM provider exists, the command refers to it by default."]})]})]})]}),"\n",(0,s.jsx)(n.p,{children:"Example:"}),"\n",(0,s.jsx)(n.pre,{children:(0,s.jsx)(n.code,{children:"ksputilcons hsmstatus\n\nGetting status. Please wait .....\n\n-----------------------------------\nHSM Identifier: DevSystem DEMO-TEST\n----------------------------------\n\n+ Configuration\nIs complete:                                YES\nHost address:                               grimsel.securosys.ch::2320\nDevice name:                                GRIMSEL\nIs sufficient to connect:                   YES\n\n+ HSM info\nDevice name:                                GRIMSEL\nFirmware version:                           RX-3.2.3-T\n\n+ Connection\nSucceeded:                                  YES\nProtocol version:                           2.5\nFIPS compliant login:                       YES\nElliptic curve cryptography:                YES\nHASH/HMAC key derivation functions:         YES\nSP800-56A key derivation function:          YES\n------------------------------------\n\u2026\n"})}),"\n",(0,s.jsx)(n.h2,{id:"enumerate-cng-providers",children:"Enumerate CNG Providers"}),"\n",(0,s.jsx)(n.p,{children:"Enumerate all CNG providers and output available information about each provider."}),"\n",(0,s.jsx)(n.pre,{children:(0,s.jsx)(n.code,{children:"ksputilcons enumprovs [-v]\n"})}),"\n",(0,s.jsx)(n.p,{children:"Where:"}),"\n",(0,s.jsxs)(n.table,{children:[(0,s.jsx)(n.thead,{children:(0,s.jsxs)(n.tr,{children:[(0,s.jsx)(n.th,{children:"Parameter"}),(0,s.jsx)(n.th,{children:"Description"})]})}),(0,s.jsx)(n.tbody,{children:(0,s.jsxs)(n.tr,{children:[(0,s.jsx)(n.td,{children:(0,s.jsx)(n.code,{children:"-v"})}),(0,s.jsx)(n.td,{children:"Optional. Increase the verbosity of the command"})]})})]}),"\n",(0,s.jsx)(n.p,{children:"Example:"}),"\n",(0,s.jsx)(n.pre,{children:(0,s.jsx)(n.code,{children:"ksputilcons enumprovs\n\n5 providers available:\n\n+ Microsoft Software Key Storage Provider\n  Primus HSM provider:          NO\n  Version:                      1.0\n  Implementation:               software based\n  Max Key Name Length:          260\n  Comment:                      ---\n\n+ Securosys Primus HSM Key Storage Provider\n  Primus HSM provider:          YES\n  Version:                      1.50\n  Implementation:               hardware based\n  Max Key Name Length:          16383\n  Comment:                      ---\n...\n"})}),"\n",(0,s.jsx)(n.h2,{id:"enumerate-algorithms",children:"Enumerate Algorithms"}),"\n",(0,s.jsx)(n.p,{children:"Output the available support of the Primus HSM provider for the most common algorithms."}),"\n",(0,s.jsx)(n.pre,{children:(0,s.jsx)(n.code,{children:"ksputilcons enumalgs [-v -p <provider>]\n"})}),"\n",(0,s.jsx)(n.p,{children:"Where:"}),"\n",(0,s.jsxs)(n.table,{children:[(0,s.jsx)(n.thead,{children:(0,s.jsxs)(n.tr,{children:[(0,s.jsx)(n.th,{children:"Parameter"}),(0,s.jsx)(n.th,{children:"Description"})]})}),(0,s.jsxs)(n.tbody,{children:[(0,s.jsxs)(n.tr,{children:[(0,s.jsx)(n.td,{children:(0,s.jsx)(n.code,{children:"-v"})}),(0,s.jsx)(n.td,{children:"Optional. Increase the verbosity of the command"})]}),(0,s.jsxs)(n.tr,{children:[(0,s.jsx)(n.td,{children:(0,s.jsx)(n.code,{children:"-p <provider>"})}),(0,s.jsxs)(n.td,{children:["Optional. If more than one HSM provider exists, specify it with the ",(0,s.jsx)(n.code,{children:"-p"})," option and replace ",(0,s.jsx)(n.code,{children:"<provider>"})," with the provider name. If only one Primus HSM provider exists, the command refers to it by default."]})]})]})]}),"\n",(0,s.jsx)(n.p,{children:"Example:"}),"\n",(0,s.jsx)(n.pre,{children:(0,s.jsx)(n.code,{children:"ksputilcons enumalgs\n\nAlgorithms:\n\n- RSA:                          Supported\n- RSA_SIGN:                     ---\n- DH:                           ---\n- DSA:                          ---\n- RC2:                          ---\n- RC4:                          ---\n- AES:                          Supported\n- DES:                          ---\n- DESX:                         ---\n- 3DES:                         ---\n- 3DES_112:                     ---\n- MD2:                          ---\n- MD4:                          ---\n- MD5:                          ---\n- SHA1:                         ---\n- SHA256:                       ---\n- SHA384:                       ---\n- SHA512:                       ---\n- AES-GMAC:                     Supported\n- AES-CMAC:                     ---\n- ECDSA_P256:                   Supported\n- ECDSA_P384:                   Supported\n- ECDSA_P521:                   Supported\n- ECDH_P256:                    Supported\n- ECDH_P384:                    Supported\n- ECDH_P521:                    Supported\n- RNG:                          ---\n- FIPS186DSARNG:                ---\n- DUALECRNG:                    ---\n- SP800_108_CTR_HMAC:           ---\n- SP800_56A_CONCAT:             Supported\n- PBKDF2:                       ---\n- CAPI_KDF:                     ---\n"})}),"\n",(0,s.jsx)(n.h2,{id:"enumerate-algorithm-operations",children:"Enumerate Algorithm Operations"}),"\n",(0,s.jsx)(n.p,{children:"Output information about all algorithm operations supported by the Primus HSM provider."}),"\n",(0,s.jsx)(n.pre,{children:(0,s.jsx)(n.code,{children:"ksputilcons enumalgops [-v -p <provider>].\n"})}),"\n",(0,s.jsx)(n.p,{children:"Where:"}),"\n",(0,s.jsxs)(n.table,{children:[(0,s.jsx)(n.thead,{children:(0,s.jsxs)(n.tr,{children:[(0,s.jsx)(n.th,{children:"Parameter"}),(0,s.jsx)(n.th,{children:"Description"})]})}),(0,s.jsxs)(n.tbody,{children:[(0,s.jsxs)(n.tr,{children:[(0,s.jsx)(n.td,{children:(0,s.jsx)(n.code,{children:"-v"})}),(0,s.jsx)(n.td,{children:"Optional. Increase the verbosity of the command"})]}),(0,s.jsxs)(n.tr,{children:[(0,s.jsx)(n.td,{children:(0,s.jsx)(n.code,{children:"-p <provider>"})}),(0,s.jsxs)(n.td,{children:["Optional. If more than one HSM provider exists, specify it with the ",(0,s.jsx)(n.code,{children:"-p"})," option and replace ",(0,s.jsx)(n.code,{children:"<provider>"})," with the provider name. If only one Primus HSM provider exists, the command refers to it by default."]})]})]})]}),"\n",(0,s.jsx)(n.p,{children:"Example:"}),"\n",(0,s.jsx)(n.pre,{children:(0,s.jsx)(n.code,{children:"ksputilcons enumalgops \n\n- Cipher (symmetric encryption) algorithms:\n  + Algorithm ID: AES\n     Algorithm class:     symmetric encryption\n     Operational classes: symmetric encryption, signature\n     Flags:               ---\n\n- Hashing algorithms:\n  ---\n\n- Asymmetric encryption algorithms:\n  + Algorithm ID: RSA\n     Algorithm class:     asymmetric encryption\n     Operational classes: asymmetric encryption, signature\n     Flags:               ---\n\n- Secret agreement algorithms:\n  + Algorithm ID: ECDH_P256\n     Algorithm class:     secret agreement (Diffie-Hellman)\n     Operational classes: secret agreement (Diffie-Hellman), signature\n     Flags:               ---\n ...\n"})}),"\n",(0,s.jsx)(n.h2,{id:"enumerate-keys",children:"Enumerate Keys"}),"\n",(0,s.jsx)(n.p,{children:"Enumerate the keys managed by the Primus HSM provider for the current user, the machine or any user. For each key, the available information is output. By default the keys of the current Windows user are enumerated."}),"\n",(0,s.jsx)(n.admonition,{title:"Enumerate All CNG Keys",type:"tip",children:(0,s.jsxs)(n.p,{children:["The command option ",(0,s.jsx)(n.code,{children:"-a"})," enables enumeration of all CNG keys, regardless of the user scope (`option , administrator rights required), as well as key capabilities and flags. See more about the parameters below."]})}),"\n",(0,s.jsx)(n.pre,{children:(0,s.jsx)(n.code,{children:"ksputilcons enumkeys [-v -m -a -p <provider>]\n"})}),"\n",(0,s.jsx)(n.p,{children:"Where:"}),"\n",(0,s.jsxs)(n.table,{children:[(0,s.jsx)(n.thead,{children:(0,s.jsxs)(n.tr,{children:[(0,s.jsx)(n.th,{children:"Parameter"}),(0,s.jsx)(n.th,{children:"Description"})]})}),(0,s.jsxs)(n.tbody,{children:[(0,s.jsxs)(n.tr,{children:[(0,s.jsx)(n.td,{children:(0,s.jsx)(n.code,{children:"-v"})}),(0,s.jsx)(n.td,{children:"Optional. Increase the verbosity of the command"})]}),(0,s.jsxs)(n.tr,{children:[(0,s.jsx)(n.td,{children:(0,s.jsx)(n.code,{children:"-m"})}),(0,s.jsxs)(n.td,{children:["Optional. Requires administrative privileges. The keys of the machine are enumerated. Cannot be used in conjunction with parameter ",(0,s.jsx)(n.code,{children:"-a"}),"."]})]}),(0,s.jsxs)(n.tr,{children:[(0,s.jsx)(n.td,{children:(0,s.jsx)(n.code,{children:"-a"})}),(0,s.jsxs)(n.td,{children:["Optional. Requires administrative privileges. All user keys are enumerated. Cannot be used in conjunction with parameter ",(0,s.jsx)(n.code,{children:"-m"}),"."]})]}),(0,s.jsxs)(n.tr,{children:[(0,s.jsx)(n.td,{children:(0,s.jsx)(n.code,{children:"-p <provider>"})}),(0,s.jsxs)(n.td,{children:["Optional. If more than one HSM provider exists, specify it with the ",(0,s.jsx)(n.code,{children:"-p"})," option and replace ",(0,s.jsx)(n.code,{children:"<provider>"})," with the provider name. If only one Primus HSM provider exists, the command refers to it by default."]})]})]})]}),"\n",(0,s.jsx)(n.p,{children:"Example:"}),"\n",(0,s.jsx)(n.pre,{children:(0,s.jsx)(n.code,{className:"language-sh",children:"ksputilcons enumkeys -a -v\n\nEnumerating. Please wait .................................................\n\n------------------------\nOwner: S-1-5-11 (Shared)\n------------------------\n\n+ Key name:                                 myTestKey.global\n  Algorithm, size, type:                    RSA, 4096 bit, PublicKey\n  Capabilities:                             Verify\n  Access:                                   Modifiable, Copyable, Pkcs11Public\n  Creation date/time:                       2025-02-18  14:37:05\n\n+ Key name:                                 myTestKey.global\n  Algorithm, size, type:                    RSA, 4096 bit, PrivateKey\n  Capabilities:                             Sign\n  Access:                                   Sensitive, Modifiable, Copyable, Pkcs11Private, NeverExtractable, AlwaysSensitive\n  Creation date/time:                       2025-02-18  14:37:05\n\n--------------------------------------------------------------------\nOwner: S-1-5-21-3913189663-3851414020-2755702806-1111 (Current User)\n--------------------------------------------------------------------\n\n+ Key name:                                 ExportCertx2.hsmdemo.test-fbaed98e-2ec2-434f-be62-7aa57bbd40b3\nAlgorithm, size, type:                      RSA, 2048 bit, PublicKey\nCapabilities:                               Encrypt, Verify, Wrap\nAccess:                                     Modifiable, Copyable, Pkcs11Public\nCreation date/time:                         2019-02-19 17:16:14\n\n+ Key name:                                 ExportCertx2.hsmdemo.test-fbaed98e-2ec2-434f-be62-7aa57bbd40b3\nAlgorithm, size, type:                      RSA, 2048 bit, PrivateKey\nCapabilities:                               Decrypt, Sign, Unwrap\nAccess:                                     Extractable, Modifiable, Copyable, Pkcs11Private\nCreation date/time:                         2019-02-19 17:16:14\n\u2026\n\n-----------------------------------------------------\nOwner: S-1-5-21-3913189663-3851414020-2755702806-1112\n-----------------------------------------------------\n\n+ Key name:                                 ExportCertx2.hsmdemo.test-fbaed98e-2ec2-434f-be62-7aa57bbd40b3\n\n\u2026\n"})}),"\n",(0,s.jsx)(n.h2,{id:"change-key-owner-accessibility",children:"Change Key Owner (Accessibility)"}),"\n",(0,s.jsxs)(n.p,{children:["Change the owner of all keys with the given name and owner managed by the Primus HSM provider. An owner is a Windows user or the Windows system (machine). It is specified by an owner identification string that can be retrieved via key enumeration, see more about ",(0,s.jsx)(n.a,{href:"./Key-Access",children:"Key Accessibility"}),"."]}),"\n",(0,s.jsxs)(n.p,{children:["The following command option allows to rename the ",(0,s.jsx)(n.strong,{children:"keyPrefix"})," of a specific key to resolve key accessibility issues (",(0,s.jsx)(n.a,{href:"/mscng/Tutorial/Key-Access",children:"learn more"}),"). This may be necessary due to upgrade incompatibility or moving accounts into or out of domains."]}),"\n",(0,s.jsx)(n.admonition,{type:"note",children:(0,s.jsxs)(n.p,{children:["The command requires administrator privileges. ",(0,s.jsx)("br",{})," Only keys having the flag ",(0,s.jsx)(n.strong,{children:'"Modifiable"'})," set can be renamed."]})}),"\n",(0,s.jsx)(n.p,{children:"The procedure is as follows:"}),"\n",(0,s.jsxs)(n.ol,{children:["\n",(0,s.jsx)(n.li,{children:"Open a command shell (cmd) with administrator rights"}),"\n",(0,s.jsxs)(n.li,{children:["Determine the existing SID (",(0,s.jsx)(n.code,{children:"<oldowner>"}),") of the key, using key enumeration option (see ",(0,s.jsx)(n.a,{href:"#enumerate-keys",children:"Enumerate Keys"}),") and check that the ",(0,s.jsx)(n.code,{children:"Modifiable"})," flag is set: ",(0,s.jsx)("br",{})," ",(0,s.jsx)(n.code,{children:"ksputilcons.exe enumkeys -a -v"})]}),"\n",(0,s.jsxs)(n.li,{children:["Determine the SID (",(0,s.jsx)(n.code,{children:"<newowner>"}),") of the new account, e.g.:"]}),"\n"]}),"\n",(0,s.jsxs)(n.ul,{children:["\n",(0,s.jsxs)(n.li,{children:["of the current user: ",(0,s.jsx)(n.code,{children:"wmic useraccount where name='%username%' get caption,sid"})]}),"\n",(0,s.jsxs)(n.li,{children:["of all users: ",(0,s.jsx)(n.code,{children:" wmic useraccount get caption, sid"})]}),"\n",(0,s.jsxs)(n.li,{children:["of well-known accounts (see chapter ",(0,s.jsx)(n.a,{href:"/mscng/Tutorial/Key-Access",children:"Key Access"})," or ",(0,s.jsx)(n.a,{href:"https://docs.microsoft.com/en-us/windows/security/identity-protection/access-control/security-identifiers",children:"Windows SID documentation"}),")"]}),"\n",(0,s.jsxs)(n.li,{children:["of the machine using psgetsid.exe from SysInternal tools:","\n",(0,s.jsxs)(n.ul,{children:["\n",(0,s.jsxs)(n.li,{children:["of local machine: ",(0,s.jsx)(n.code,{children:"psgetsid"})]}),"\n",(0,s.jsxs)(n.li,{children:["of machine in Active Directory ",(0,s.jsx)(n.code,{children:" psgetsid %computername%$"})]}),"\n"]}),"\n"]}),"\n",(0,s.jsxs)(n.li,{children:["Rename the key prefix with the following command option (see ",(0,s.jsx)(n.strong,{children:"help"})," option for details):"]}),"\n"]}),"\n",(0,s.jsx)(n.pre,{children:(0,s.jsx)(n.code,{children:" ksputilcons chkeysowner <keyname> <oldowner> <newowner> [-v -p <provider> --passwd <password>] \n"})}),"\n",(0,s.jsx)(n.p,{children:"Example:"}),"\n",(0,s.jsx)(n.pre,{children:(0,s.jsx)(n.code,{children:"ksputilcons chkeysowner CNGTestKey S-1-5-21-3913189663-3851414020-2755702806-1111\n S-1-5-21-3913189663-3851414020-2755702806-1104\n"})}),"\n",(0,s.jsx)(n.p,{children:"Where:"}),"\n",(0,s.jsxs)(n.table,{children:[(0,s.jsx)(n.thead,{children:(0,s.jsxs)(n.tr,{children:[(0,s.jsx)(n.th,{children:"parameter"}),(0,s.jsx)(n.th,{children:"description"})]})}),(0,s.jsxs)(n.tbody,{children:[(0,s.jsxs)(n.tr,{children:[(0,s.jsx)(n.td,{children:(0,s.jsx)(n.code,{children:"<keyname>"})}),(0,s.jsx)(n.td,{children:"Specify the name of the key(s)."})]}),(0,s.jsxs)(n.tr,{children:[(0,s.jsx)(n.td,{children:(0,s.jsx)(n.code,{children:"<oldowner>"})}),(0,s.jsx)(n.td,{children:"Identification string of the current owner of the key(s)."})]}),(0,s.jsxs)(n.tr,{children:[(0,s.jsx)(n.td,{children:(0,s.jsx)(n.code,{children:"<newowner>"})}),(0,s.jsx)(n.td,{children:"Identification string of the new owner of the key(s)."})]}),(0,s.jsxs)(n.tr,{children:[(0,s.jsx)(n.td,{children:(0,s.jsx)(n.code,{children:"-v"})}),(0,s.jsx)(n.td,{children:"Optional. Increase the verbosity of the command"})]}),(0,s.jsxs)(n.tr,{children:[(0,s.jsx)(n.td,{children:(0,s.jsx)(n.code,{children:"-p <provider>"})}),(0,s.jsxs)(n.td,{children:["Optional. If more than one HSM provider exists, specify it with the ",(0,s.jsx)(n.code,{children:"-p"})," option and replace ",(0,s.jsx)(n.code,{children:"<provider>"})," with the provider name. If only one Primus HSM provider exists, as the command refers to it by default."]})]}),(0,s.jsxs)(n.tr,{children:[(0,s.jsx)(n.td,{children:(0,s.jsx)(n.code,{children:"--passwd <password>"})}),(0,s.jsxs)(n.td,{children:["Optional. Replace ",(0,s.jsx)(n.code,{children:"<password>"})," with a key password, if required"]})]})]})]}),"\n",(0,s.jsx)(n.h2,{id:"create-key",children:"Create Key"}),"\n",(0,s.jsx)(n.admonition,{type:"note",children:(0,s.jsxs)(n.p,{children:["The ",(0,s.jsx)(n.code,{children:"createkey"})," command is only included in the ",(0,s.jsx)(n.code,{children:"ksputilcons.exe"})," utilities with Primus CNG/KSP provider version 1.50.2 or newer."]})}),"\n",(0,s.jsx)(n.p,{children:"Create a key for the Primus HSM provider. If no key creation parameter is specified then the key will be created with the default values."}),"\n",(0,s.jsx)(n.pre,{children:(0,s.jsx)(n.code,{children:"ksputilcons createkey [--name <keyname> --algorithm <keyAlgorithm> --size <keySize> --exportable --sign --decrypt --agreement -v -m -p <provider>]\n"})}),"\n",(0,s.jsx)(n.p,{children:"Where:"}),"\n",(0,s.jsxs)(n.table,{children:[(0,s.jsx)(n.thead,{children:(0,s.jsxs)(n.tr,{children:[(0,s.jsx)(n.th,{children:"Parameter"}),(0,s.jsx)(n.th,{children:"Description"})]})}),(0,s.jsxs)(n.tbody,{children:[(0,s.jsxs)(n.tr,{children:[(0,s.jsx)(n.td,{children:(0,s.jsx)(n.code,{children:"--name"})}),(0,s.jsx)(n.td,{children:'Optional. Specify the name of the generated key (For global keys add the suffix ".global")'})]}),(0,s.jsxs)(n.tr,{children:[(0,s.jsx)(n.td,{children:(0,s.jsx)(n.code,{children:"--algorithm"})}),(0,s.jsxs)(n.td,{children:["Optional. Specify the key algorithm as either: ",(0,s.jsx)(n.code,{children:"AES"}),", ",(0,s.jsx)(n.code,{children:"ECDSA"}),", ",(0,s.jsx)(n.code,{children:"RSA"})]})]}),(0,s.jsxs)(n.tr,{children:[(0,s.jsx)(n.td,{children:(0,s.jsx)(n.code,{children:"--size"})}),(0,s.jsx)(n.td,{children:"Optional. Specify the size of the key. Supported: AES (128, 192, 256), ECDSA (256, 384, 521, (NIST curves)), RSA (2048, 3072, 4096)"})]}),(0,s.jsxs)(n.tr,{children:[(0,s.jsx)(n.td,{children:(0,s.jsx)(n.code,{children:"--exportable"})}),(0,s.jsx)(n.td,{children:"Optional. Specify if the generated key can be exported, if omitted the key will not be exportable."})]}),(0,s.jsxs)(n.tr,{children:[(0,s.jsx)(n.td,{children:(0,s.jsx)(n.code,{children:"--sign"})}),(0,s.jsx)(n.td,{children:"Optional. Specify if the generated key has sign usage. If no specific usage is defined, all key usages are assigned by default."})]}),(0,s.jsxs)(n.tr,{children:[(0,s.jsx)(n.td,{children:(0,s.jsx)(n.code,{children:"--decrypt"})}),(0,s.jsx)(n.td,{children:"Optional. Specify if the generated key has decrypt usage. If no specific usage is defined, all key usages are assigned by default."})]}),(0,s.jsxs)(n.tr,{children:[(0,s.jsx)(n.td,{children:(0,s.jsx)(n.code,{children:"--agreement"})}),(0,s.jsx)(n.td,{children:"Optional. Specify if the generated key has agreement usage. If no specific usage is defined, all key usages are assigned by default."})]}),(0,s.jsxs)(n.tr,{children:[(0,s.jsx)(n.td,{children:(0,s.jsx)(n.code,{children:"-v"})}),(0,s.jsx)(n.td,{children:"Optional. Increase the verbosity of the command"})]}),(0,s.jsxs)(n.tr,{children:[(0,s.jsx)(n.td,{children:(0,s.jsx)(n.code,{children:"-m"})}),(0,s.jsx)(n.td,{children:"Optional. Specify if the key to be created is a machine key. Requires administrator privileges."})]}),(0,s.jsxs)(n.tr,{children:[(0,s.jsx)(n.td,{children:(0,s.jsx)(n.code,{children:"-p <provider>"})}),(0,s.jsxs)(n.td,{children:["Optional. If more than one HSM provider exists, specify it with the ",(0,s.jsx)(n.code,{children:"-p"})," option and replace ",(0,s.jsx)(n.code,{children:"<provider>"})," with the provider name. If only one Primus HSM provider exists, the command refers to it by default."]})]})]})]}),"\n",(0,s.jsxs)(n.p,{children:["Default Values for command ",(0,s.jsx)(n.code,{children:"ksputilcons createkey"}),":\n",(0,s.jsx)(n.code,{children:"SecurosysPrimusHSM_CNGTestKey, RSA, 4096, not exportable, sign, decrypt, agreement"})]}),"\n",(0,s.jsx)(n.p,{children:"Example to create a key shared by all authenticated device users (globally shared):"}),"\n",(0,s.jsx)(n.pre,{children:(0,s.jsx)(n.code,{children:"ksputilcons createkey --name myTestKey.global --algorithm RSA --size 4096 --sign\n"})}),"\n",(0,s.jsx)(n.pre,{children:(0,s.jsx)(n.code,{children:"ksputilcons enumkeys -v\n\nEnumerating. Please wait .............\n\n------------------------\nOwner: S-1-5-11 (Shared)\n------------------------\n\n+ Key name:                     myTestKey.global\n  Algorithm, size, type:        RSA, 4096 bit, PublicKey\n  Capabilities:                 Verify\n  Access:                       Modifiable, Copyable, Pkcs11Public\n  Creation date/time:           2025-02-18  14:37:05\n\n+ Key name:                     myTestKey.global\n  Algorithm, size, type:        RSA, 4096 bit, PrivateKey\n  Capabilities:                 Sign\n  Access:                       Sensitive, Modifiable, Copyable, Pkcs11Private, NeverExtractable, AlwaysSensitive\n  Creation date/time:           2025-02-18  14:37:05\n...\n"})}),"\n",(0,s.jsx)(n.h2,{id:"delete-key",children:"Delete Key"}),"\n",(0,s.jsx)(n.admonition,{type:"note",children:(0,s.jsxs)(n.p,{children:["The ",(0,s.jsx)(n.code,{children:"deletekey"})," command is only included in the ",(0,s.jsx)(n.code,{children:"ksputilcons.exe"})," utilities with Primus CNG/KSP provider version 1.50.2 or newer."]})}),"\n",(0,s.jsx)(n.p,{children:"Delete a key of the Primus HSM provider."}),"\n",(0,s.jsx)(n.pre,{children:(0,s.jsx)(n.code,{children:"ksputilcons deletekey [--name -v -m -p <provider>]\n"})}),"\n",(0,s.jsx)(n.p,{children:"Where:"}),"\n",(0,s.jsxs)(n.table,{children:[(0,s.jsx)(n.thead,{children:(0,s.jsxs)(n.tr,{children:[(0,s.jsx)(n.th,{children:"Parameter"}),(0,s.jsx)(n.th,{children:"Description"})]})}),(0,s.jsxs)(n.tbody,{children:[(0,s.jsxs)(n.tr,{children:[(0,s.jsx)(n.td,{children:(0,s.jsx)(n.code,{children:"--name"})}),(0,s.jsxs)(n.td,{children:["Specify the name of the key to be deleted. If parameter is omitted the default value will be: ",(0,s.jsx)(n.code,{children:"SecurosysPrimusHSM_CNGTestKey"})]})]}),(0,s.jsxs)(n.tr,{children:[(0,s.jsx)(n.td,{children:(0,s.jsx)(n.code,{children:"-v"})}),(0,s.jsx)(n.td,{children:"Optional. Increase the verbosity of the command"})]}),(0,s.jsxs)(n.tr,{children:[(0,s.jsx)(n.td,{children:(0,s.jsx)(n.code,{children:"-m"})}),(0,s.jsx)(n.td,{children:"Optional. Specify if the key to be deleted is a machine key. Requires administrator privileges."})]}),(0,s.jsxs)(n.tr,{children:[(0,s.jsx)(n.td,{children:(0,s.jsx)(n.code,{children:"-p <provider>"})}),(0,s.jsxs)(n.td,{children:["Optional. If more than one HSM provider exists, specify it with the ",(0,s.jsx)(n.code,{children:"-p"})," option and replace ",(0,s.jsx)(n.code,{children:"<provider>"})," with the provider name. If only one Primus HSM provider exists, the command refers to it by default."]})]})]})]}),"\n","\n",(0,s.jsxs)(n.section,{"data-footnotes":!0,className:"footnotes",children:[(0,s.jsx)(n.h2,{className:"sr-only",id:"footnote-label",children:"Footnotes"}),"\n",(0,s.jsxs)(n.ol,{children:["\n",(0,s.jsxs)(n.li,{id:"user-content-fn-1",children:["\n",(0,s.jsxs)(n.p,{children:["Provider instance parameter [-p <provider>] is required only if multiple instances are installed (CNG V1.40 or newer) ",(0,s.jsx)(n.a,{href:"#user-content-fnref-1","data-footnote-backref":"","aria-label":"Back to reference 1",className:"data-footnote-backref",children:"\u21a9"})]}),"\n"]}),"\n"]}),"\n"]})]})}function h(e={}){const{wrapper:n}={...(0,t.R)(),...e.components};return n?(0,s.jsx)(n,{...e,children:(0,s.jsx)(a,{...e})}):a(e)}}}]);