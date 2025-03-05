"use strict";(self.webpackChunksecurosys_docs=self.webpackChunksecurosys_docs||[]).push([[46006],{13781:(e,t,n)=>{n.r(t),n.d(t,{assets:()=>d,contentTitle:()=>c,default:()=>h,frontMatter:()=>a,metadata:()=>r,toc:()=>p});const r=JSON.parse('{"id":"osslv3/Tutorial/openssl_cli","title":"OpenSSL v3.x Command Line Interface (CLI)","description":"Discover OpenSSL\'s PKCS11 provider, CLI commands, installation tips, and troubleshooting. Integrate seamlessly with HSM for enhanced security.","source":"@site/openssl/osslv3/Tutorial/openssl_cli.md","sourceDirName":"osslv3/Tutorial","slug":"/osslv3/Tutorial/openssl_cli","permalink":"/openssl/osslv3/Tutorial/openssl_cli","draft":false,"unlisted":false,"tags":[{"inline":true,"label":"OpenSSL","permalink":"/openssl/tags/open-ssl"},{"inline":true,"label":"PKCS#11","permalink":"/openssl/tags/pkcs-11"},{"inline":true,"label":"CLI","permalink":"/openssl/tags/cli"}],"version":"current","lastUpdatedAt":1741162132000,"sidebarPosition":3,"frontMatter":{"sidebar_position":3,"title":"OpenSSL v3.x Command Line Interface (CLI)","sidebar_label":"OpenSSL CLI","description":"Discover OpenSSL\'s PKCS11 provider, CLI commands, installation tips, and troubleshooting. Integrate seamlessly with HSM for enhanced security.","keywords":["OpenSSL PKCS11 provider","OpenSSL PKCS11 API","OpenSSL command line utility (CLI)","OpenSSL CLI commands","OpenSSL installation guide","OpenSSL installation troubleshooting","OpenSSL troubleshooting tips","OpenSSL certificate management","OpenSSL certificate creation","OpenSSL certificate renewal","OpenSSL configuration file","OpenSSL configuration options","OpenSSL configuration guide","OpenSSL encryption algorithms","OpenSSL decryption methods","OpenSSL digital signatures","OpenSSL SSL/TLS protocols","OpenSSL SSL/TLS configuration","OpenSSL heartbleed vulnerability","OpenSSL security updates"],"tags":["OpenSSL","PKCS#11","CLI"]},"sidebar":"openssl","previous":{"title":"Tutorial","permalink":"/openssl/category/tutorial"},"next":{"title":"Troubleshooting","permalink":"/openssl/osslv3/Tutorial/troubleshooting"}}');var s=n(74848),i=n(28453),l=n(65537),o=n(79329);const a={sidebar_position:3,title:"OpenSSL v3.x Command Line Interface (CLI)",sidebar_label:"OpenSSL CLI",description:"Discover OpenSSL's PKCS11 provider, CLI commands, installation tips, and troubleshooting. Integrate seamlessly with HSM for enhanced security.",keywords:["OpenSSL PKCS11 provider","OpenSSL PKCS11 API","OpenSSL command line utility (CLI)","OpenSSL CLI commands","OpenSSL installation guide","OpenSSL installation troubleshooting","OpenSSL troubleshooting tips","OpenSSL certificate management","OpenSSL certificate creation","OpenSSL certificate renewal","OpenSSL configuration file","OpenSSL configuration options","OpenSSL configuration guide","OpenSSL encryption algorithms","OpenSSL decryption methods","OpenSSL digital signatures","OpenSSL SSL/TLS protocols","OpenSSL SSL/TLS configuration","OpenSSL heartbleed vulnerability","OpenSSL security updates"],tags:["OpenSSL","PKCS#11","CLI"]},c="Tutorial: OpenSSL v3.x CLI",d={},p=[{value:"Introduction",id:"introduction",level:2},{value:"Generating Keys with pkcs11-tool",id:"generating-keys-with-pkcs11-tool",level:2},{value:"Exporting public keys",id:"exporting-public-keys",level:4},{value:"Creating a self signed certificate",id:"creating-a-self-signed-certificate",level:4},{value:"Generating Keys with OpenSSL",id:"generating-keys-with-openssl",level:2},{value:"Exporting public keys",id:"exporting-public-keys-1",level:4},{value:"Creating a self signed certificate",id:"creating-a-self-signed-certificate-1",level:4},{value:"More resources",id:"more-resources",level:2}];function u(e){const t={a:"a",admonition:"admonition",code:"code",h1:"h1",h2:"h2",h4:"h4",header:"header",li:"li",p:"p",pre:"pre",strong:"strong",table:"table",tbody:"tbody",td:"td",th:"th",thead:"thead",tr:"tr",ul:"ul",...(0,i.R)(),...e.components};return(0,s.jsxs)(s.Fragment,{children:[(0,s.jsx)(t.header,{children:(0,s.jsx)(t.h1,{id:"tutorial-openssl-v3x-cli",children:"Tutorial: OpenSSL v3.x CLI"})}),"\n",(0,s.jsx)(t.h2,{id:"introduction",children:"Introduction"}),"\n",(0,s.jsxs)(t.p,{children:["The OpenSSL command line interface can be used to interact with the HSM using the pkcs11-provider. As of OpenSSL 3, it is possible to limit the selection of different algorithm implementations using a properties based concept. The ",(0,s.jsxs)(t.a,{href:"https://www.openssl.org/docs/man3.0/man7/property.html#Queries",children:[(0,s.jsx)(t.code,{children:"propquery"})," parameter"]})," is available for many of the commands that are accessible through the CLI. When setting ",(0,s.jsx)(t.code,{children:'-propquery "provider=pkcs11"'})," only algorithms that are offered by the pkcs11-provider will be used."]}),"\n",(0,s.jsxs)(t.admonition,{type:"info",children:[(0,s.jsxs)(t.p,{children:["Objects stored on the token (HSM User) are referenced using a ",(0,s.jsx)(t.a,{href:"https://www.rfc-editor.org/rfc/rfc7512",children:"PKCS#11URI"}),"."]}),(0,s.jsxs)(t.p,{children:["For commands where the key is used as an input argument, it's URI can be entered in place of the file name. When generating a key, the URI can be passed as a ",(0,s.jsx)(t.code,{children:"pkeyopt"})," (e.g. ",(0,s.jsx)(t.code,{children:'-pkeyopt pkcs11_uri:"pkcs11:type=private?object=SomeLabel"'}),")."]})]}),"\n",(0,s.jsxs)(t.admonition,{type:"warning",children:[(0,s.jsxs)(t.p,{children:["For generating, importing, and deleting objects directly on the HSM we recommend using ",(0,s.jsx)(t.code,{children:"pkcs11-tool"}),"."]}),(0,s.jsxs)(t.p,{children:["Instructions on how to use ",(0,s.jsx)(t.code,{children:"openssl"})," are also provided."]})]}),"\n",(0,s.jsxs)(l.A,{groupId:"kmgt",children:[(0,s.jsxs)(o.A,{value:"kmgt-pkcs11-tool",label:"pkcs11-tool",default:!0,children:[(0,s.jsx)(t.admonition,{type:"info",children:(0,s.jsx)(t.p,{children:"All commands connect to the HSM and execute there."})}),(0,s.jsx)(t.h2,{id:"generating-keys-with-pkcs11-tool",children:"Generating Keys with pkcs11-tool"}),(0,s.jsx)(t.p,{children:"Define the following variables that will be used throughout the example below"}),(0,s.jsx)(t.pre,{children:(0,s.jsx)(t.code,{className:"language-bash",children:"export P11_TOKEN=<YOUR_USER_NAME>\t\t\t#partition name\nexport P11_PIN=<YOUR_PKCS11_PIN>\t\t\t#hsm pkcs11 pin (don't use for production)\nexport P11_KEY_NAME=<YOUR_PKCS11_KEY_LABEL_NAME>\t#name of Key\nexport P11_LIB=/usr/local/primus/lib/libprimusP11.so\t#library location\n"})}),(0,s.jsx)(t.p,{children:"Generate an extractable private key pair"}),(0,s.jsx)(t.pre,{children:(0,s.jsx)(t.code,{className:"language-bash",children:"# create a key pair of type rsa\npkcs11-tool --module ${P11_LIB} --login --slot 0 \\\n\t    --keypairgen  --key-type rsa:4096 --pin ${P11_PIN} \\\n\t    --extractable --label ${P11_KEY_NAME}\n\n# other parameters to consider\n# --id <1234> to specify an ID; randomized otherwise\n"})}),(0,s.jsx)(t.p,{children:"To view all your objects in the HSM"}),(0,s.jsx)(t.pre,{children:(0,s.jsx)(t.code,{className:"language-bash",children:"pkcs11-tool --module ${P11_LIB} --slot 0 --login --pin ${P11_PIN} --list-objects\n\n# sample output\nPublic Key Object; RSA 4096 bits\n  label:      <YOUR_PKCS11_KEY_LABEL_NAME>\n  ID:         457bac3be2780e5a4ac122f81b601f0e\n  Usage:      encrypt, verify, wrap\n  Access:     local\nPrivate Key Object; RSA \n  label:      <YOUR_PKCS11_KEY_LABEL_NAME>\n  ID:         457bac3be2780e5a4ac122f81b601f0e\n  Usage:      decrypt, sign, unwrap\n  Access:     sensitive, always sensitive, never extractable, local\n  Allowed mechanisms: RSA-X-509,RSA-PKCS,SHA1-RSA-PKCS,SHA224-RSA-PKCS,SHA256-RSA-PKCS,SHA384-RSA-PKCS,SHA384-RSA-PKCS,RSA-PKCS-PSS,SHA1-RSA-PKCS-PSS,SHA224-RSA-PKCS-PSS,SHA256-RSA-PKCS-PSS,SHA384-RSA-PKCS-PSS,SHA512-RSA-PKCS-PSS,RSA-PKCS-OAEP\n\n\n"})}),(0,s.jsx)(t.h4,{id:"exporting-public-keys",children:"Exporting public keys"}),(0,s.jsx)(t.p,{children:"Export the public key locally"}),(0,s.jsx)(t.pre,{children:(0,s.jsx)(t.code,{className:"language-bash",children:"pkcs11-tool --module ${P11_LIB} --login --login-type user \\\n\t    --read-object --type pubkey --id 01 -o rsa01pub.key\n"})}),(0,s.jsx)(t.h4,{id:"creating-a-self-signed-certificate",children:"Creating a self signed certificate"}),(0,s.jsx)(t.p,{children:"pkcs11-tool offers to sign a certificate directly on the HSM:"}),(0,s.jsx)(t.pre,{children:(0,s.jsx)(t.code,{className:"language-bash",children:"# generate a self-signed certificate using pkcs11-tool on the token\npkcs11-tool --module ${P11_LIB} --login --type=cert \\\n\t    --write-object ${P11_KEY_NAME}_crt.pem --label ${P11_KEY_NAME}\n"})})]}),(0,s.jsxs)(o.A,{value:"kmgt-openssl",label:"OpenSSL",children:[(0,s.jsx)(t.admonition,{type:"info",children:(0,s.jsx)(t.p,{children:"All commands connect to the HSM and execute there."})}),(0,s.jsx)(t.h2,{id:"generating-keys-with-openssl",children:"Generating Keys with OpenSSL"}),(0,s.jsx)(t.p,{children:"Define the following variables that will be used throughout the example below"}),(0,s.jsx)(t.pre,{children:(0,s.jsx)(t.code,{className:"language-bash",children:"export P11_TOKEN=<YOUR_USER_NAME>\t\t\t#partition name\nexport P11_PIN=<YOUR_PKCS11_PIN>\t\t\t#hsm pkcs11 pin (don't use for production)\nexport P11_KEY_NAME=<YOUR_PKCS11_KEY_LABEL_NAME>\t#name of Key\nexport P11_LIB=/usr/local/primus/lib/libprimusP11.so\t#library location\nexport ALGORITHM=<PREFERRED_ALGO>\t\t\t#consult table below\nexport ALGORITHM_OPT=<PREFERRED_ALGO_OPTION>\t\t#consult table below\n"})}),(0,s.jsx)(t.p,{children:"The following table lists popular supported algorithms and algorithm options:"}),(0,s.jsxs)(t.table,{children:[(0,s.jsx)(t.thead,{children:(0,s.jsxs)(t.tr,{children:[(0,s.jsx)(t.th,{style:{textAlign:"right"},children:(0,s.jsx)(t.code,{children:"ALGORITHM"})}),(0,s.jsx)(t.th,{style:{textAlign:"left"},children:(0,s.jsx)(t.code,{children:"ALGORITHM_OPT"})})]})}),(0,s.jsxs)(t.tbody,{children:[(0,s.jsxs)(t.tr,{children:[(0,s.jsx)(t.td,{style:{textAlign:"right"},children:(0,s.jsx)(t.code,{children:"rsa"})}),(0,s.jsx)(t.td,{style:{textAlign:"left"},children:(0,s.jsx)(t.code,{children:"rsa_keygen_bits:2048"})})]}),(0,s.jsxs)(t.tr,{children:[(0,s.jsx)(t.td,{style:{textAlign:"right"},children:(0,s.jsx)(t.code,{children:"rsa"})}),(0,s.jsx)(t.td,{style:{textAlign:"left"},children:(0,s.jsx)(t.code,{children:"rsa_keygen_bits:3072"})})]}),(0,s.jsxs)(t.tr,{children:[(0,s.jsx)(t.td,{style:{textAlign:"right"},children:(0,s.jsx)(t.code,{children:"rsa"})}),(0,s.jsx)(t.td,{style:{textAlign:"left"},children:(0,s.jsx)(t.code,{children:"rsa_keygen_bits:4096"})})]}),(0,s.jsxs)(t.tr,{children:[(0,s.jsx)(t.td,{style:{textAlign:"right"},children:(0,s.jsx)(t.code,{children:"EC"})}),(0,s.jsx)(t.td,{style:{textAlign:"left"},children:(0,s.jsx)(t.code,{children:"ec_paramgen_curve:prime256v1"})})]}),(0,s.jsxs)(t.tr,{children:[(0,s.jsx)(t.td,{style:{textAlign:"right"},children:(0,s.jsx)(t.code,{children:"EC"})}),(0,s.jsx)(t.td,{style:{textAlign:"left"},children:(0,s.jsx)(t.code,{children:"ec_paramgen_curve:secp384r1"})})]}),(0,s.jsxs)(t.tr,{children:[(0,s.jsx)(t.td,{style:{textAlign:"right"},children:(0,s.jsx)(t.code,{children:"EC"})}),(0,s.jsx)(t.td,{style:{textAlign:"left"},children:(0,s.jsx)(t.code,{children:"ec_paramgen_curve:secp521r1"})})]}),(0,s.jsxs)(t.tr,{children:[(0,s.jsx)(t.td,{style:{textAlign:"right"},children:(0,s.jsx)(t.code,{children:"ed25519"})}),(0,s.jsx)(t.td,{style:{textAlign:"left"}})]})]})]}),(0,s.jsxs)(t.admonition,{type:"warning",children:[(0,s.jsxs)(t.p,{children:["For generating, importing, and deleting objects on the HSM there are tools more suitable than OpenSSL. Consider using the ",(0,s.jsx)(t.code,{children:"pkcs11-tool"})," instead."]}),(0,s.jsx)(t.p,{children:'At the moment "Error writing key(s)" is displayed despite creating the key-pair successfully.'})]}),(0,s.jsx)(t.pre,{children:(0,s.jsx)(t.code,{className:"language-bash",children:'openssl genpkey -propquery "provider=pkcs11" \\\n\t\t-algorithm "${ALGORITHM}" ${ALGORITHM_OPT:+-pkeyopt} ${ALGORITHM_OPT} \\\n\t\t-pkeyopt "pkcs11_uri:pkcs11:object=${P11_KEY_NAME}?pin-value=${P11_PIN}"\n'})}),(0,s.jsx)(t.h4,{id:"exporting-public-keys-1",children:"Exporting public keys"}),(0,s.jsx)(t.pre,{children:(0,s.jsx)(t.code,{className:"language-bash",children:'openssl pkeyutl -pubout -in "pkcs11:type=public;object=${P11_KEY_NAME}" \\\n\t\t-out "${P11_KEY_NAME}_pub.pem"\n'})}),(0,s.jsx)(t.h4,{id:"creating-a-self-signed-certificate-1",children:"Creating a self signed certificate"}),(0,s.jsx)(t.pre,{children:(0,s.jsx)(t.code,{className:"language-bash",children:'openssl req -new -x509 -key "pkcs11:object=${P11_KEY_NAME}" \\\n\t    -sha256 -days 99 -out "${P11_KEY_NAME}_crt.pem"\n'})}),(0,s.jsx)(t.admonition,{type:"note",children:(0,s.jsxs)(t.p,{children:["The above certificate is ",(0,s.jsx)(t.strong,{children:"not"})," stored on the HSM. The certificate file needs to be written to the token explicitly."]})})]})]}),"\n",(0,s.jsx)(t.h2,{id:"more-resources",children:"More resources"}),"\n",(0,s.jsxs)(t.ul,{children:["\n",(0,s.jsx)(t.li,{children:(0,s.jsx)(t.a,{href:"/openssl/osslv3/Tutorial/openssl_cli",children:"Generate a key with OpenSSL"})}),"\n",(0,s.jsx)(t.li,{children:(0,s.jsx)(t.a,{href:"../Tutorial/troubleshooting",children:"Troubleshooting"})}),"\n",(0,s.jsxs)(t.li,{children:[(0,s.jsx)(t.a,{href:"https://docs.openssl.org/master/",children:"OpenSSL documentation"}),"."]}),"\n"]})]})}function h(e={}){const{wrapper:t}={...(0,i.R)(),...e.components};return t?(0,s.jsx)(t,{...e,children:(0,s.jsx)(u,{...e})}):u(e)}},28453:(e,t,n)=>{n.d(t,{R:()=>l,x:()=>o});var r=n(96540);const s={},i=r.createContext(s);function l(e){const t=r.useContext(i);return r.useMemo((function(){return"function"==typeof e?e(t):{...t,...e}}),[t,e])}function o(e){let t;return t=e.disableParentContext?"function"==typeof e.components?e.components(s):e.components||s:l(e.components),r.createElement(i.Provider,{value:t},e.children)}},65537:(e,t,n)=>{n.d(t,{A:()=>L});var r=n(96540),s=n(34164),i=n(65627),l=n(56347),o=n(50372),a=n(30604),c=n(11861),d=n(78749);function p(e){return r.Children.toArray(e).filter((e=>"\n"!==e)).map((e=>{if(!e||(0,r.isValidElement)(e)&&function(e){const{props:t}=e;return!!t&&"object"==typeof t&&"value"in t}(e))return e;throw new Error(`Docusaurus error: Bad <Tabs> child <${"string"==typeof e.type?e.type:e.type.name}>: all children of the <Tabs> component should be <TabItem>, and every <TabItem> should have a unique "value" prop.`)}))?.filter(Boolean)??[]}function u(e){const{values:t,children:n}=e;return(0,r.useMemo)((()=>{const e=t??function(e){return p(e).map((e=>{let{props:{value:t,label:n,attributes:r,default:s}}=e;return{value:t,label:n,attributes:r,default:s}}))}(n);return function(e){const t=(0,c.XI)(e,((e,t)=>e.value===t.value));if(t.length>0)throw new Error(`Docusaurus error: Duplicate values "${t.map((e=>e.value)).join(", ")}" found in <Tabs>. Every value needs to be unique.`)}(e),e}),[t,n])}function h(e){let{value:t,tabValues:n}=e;return n.some((e=>e.value===t))}function S(e){let{queryString:t=!1,groupId:n}=e;const s=(0,l.W6)(),i=function(e){let{queryString:t=!1,groupId:n}=e;if("string"==typeof t)return t;if(!1===t)return null;if(!0===t&&!n)throw new Error('Docusaurus error: The <Tabs> component groupId prop is required if queryString=true, because this value is used as the search param name. You can also provide an explicit value such as queryString="my-search-param".');return n??null}({queryString:t,groupId:n});return[(0,a.aZ)(i),(0,r.useCallback)((e=>{if(!i)return;const t=new URLSearchParams(s.location.search);t.set(i,e),s.replace({...s.location,search:t.toString()})}),[i,s])]}function g(e){const{defaultValue:t,queryString:n=!1,groupId:s}=e,i=u(e),[l,a]=(0,r.useState)((()=>function(e){let{defaultValue:t,tabValues:n}=e;if(0===n.length)throw new Error("Docusaurus error: the <Tabs> component requires at least one <TabItem> children component");if(t){if(!h({value:t,tabValues:n}))throw new Error(`Docusaurus error: The <Tabs> has a defaultValue "${t}" but none of its children has the corresponding value. Available values are: ${n.map((e=>e.value)).join(", ")}. If you intend to show no default tab, use defaultValue={null} instead.`);return t}const r=n.find((e=>e.default))??n[0];if(!r)throw new Error("Unexpected error: 0 tabValues");return r.value}({defaultValue:t,tabValues:i}))),[c,p]=S({queryString:n,groupId:s}),[g,x]=function(e){let{groupId:t}=e;const n=function(e){return e?`docusaurus.tab.${e}`:null}(t),[s,i]=(0,d.Dv)(n);return[s,(0,r.useCallback)((e=>{n&&i.set(e)}),[n,i])]}({groupId:s}),m=(()=>{const e=c??g;return h({value:e,tabValues:i})?e:null})();(0,o.A)((()=>{m&&a(m)}),[m]);return{selectedValue:l,selectValue:(0,r.useCallback)((e=>{if(!h({value:e,tabValues:i}))throw new Error(`Can't select invalid tab value=${e}`);a(e),p(e),x(e)}),[p,x,i]),tabValues:i}}var x=n(9136);const m={tabList:"tabList__CuJ",tabItem:"tabItem_LNqP"};var b=n(74848);function f(e){let{className:t,block:n,selectedValue:r,selectValue:l,tabValues:o}=e;const a=[],{blockElementScrollPositionUntilNextRender:c}=(0,i.a_)(),d=e=>{const t=e.currentTarget,n=a.indexOf(t),s=o[n].value;s!==r&&(c(t),l(s))},p=e=>{let t=null;switch(e.key){case"Enter":d(e);break;case"ArrowRight":{const n=a.indexOf(e.currentTarget)+1;t=a[n]??a[0];break}case"ArrowLeft":{const n=a.indexOf(e.currentTarget)-1;t=a[n]??a[a.length-1];break}}t?.focus()};return(0,b.jsx)("ul",{role:"tablist","aria-orientation":"horizontal",className:(0,s.A)("tabs",{"tabs--block":n},t),children:o.map((e=>{let{value:t,label:n,attributes:i}=e;return(0,b.jsx)("li",{role:"tab",tabIndex:r===t?0:-1,"aria-selected":r===t,ref:e=>{a.push(e)},onKeyDown:p,onClick:d,...i,className:(0,s.A)("tabs__item",m.tabItem,i?.className,{"tabs__item--active":r===t}),children:n??t},t)}))})}function y(e){let{lazy:t,children:n,selectedValue:i}=e;const l=(Array.isArray(n)?n:[n]).filter(Boolean);if(t){const e=l.find((e=>e.props.value===i));return e?(0,r.cloneElement)(e,{className:(0,s.A)("margin-top--md",e.props.className)}):null}return(0,b.jsx)("div",{className:"margin-top--md",children:l.map(((e,t)=>(0,r.cloneElement)(e,{key:t,hidden:e.props.value!==i})))})}function j(e){const t=g(e);return(0,b.jsxs)("div",{className:(0,s.A)("tabs-container",m.tabList),children:[(0,b.jsx)(f,{...t,...e}),(0,b.jsx)(y,{...t,...e})]})}function L(e){const t=(0,x.A)();return(0,b.jsx)(j,{...e,children:p(e.children)},String(t))}},79329:(e,t,n)=>{n.d(t,{A:()=>l});n(96540);var r=n(34164);const s={tabItem:"tabItem_Ymn6"};var i=n(74848);function l(e){let{children:t,hidden:n,className:l}=e;return(0,i.jsx)("div",{role:"tabpanel",className:(0,r.A)(s.tabItem,l),hidden:n,children:t})}}}]);