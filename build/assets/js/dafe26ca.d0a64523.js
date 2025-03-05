"use strict";(self.webpackChunksecurosys_docs=self.webpackChunksecurosys_docs||[]).push([[26969],{18654:(e,t,n)=>{n.r(t),n.d(t,{assets:()=>c,contentTitle:()=>o,default:()=>d,frontMatter:()=>a,metadata:()=>r,toc:()=>l});const r=JSON.parse('{"id":"Tutorials/SigningCsr","title":"Generate CSR & Import Certificates for Docker","description":"Learn to generate a Certificate Signing Request (CSR), obtain CA-signed certificates, and import them into the truststore for Docker image signing with Securosys.","source":"@site/docker_signing/Tutorials/SigningCsr.md","sourceDirName":"Tutorials","slug":"/Tutorials/SigningCsr","permalink":"/docker_signing/Tutorials/SigningCsr","draft":false,"unlisted":false,"tags":[],"version":"current","lastUpdatedAt":1741162132000,"sidebarPosition":4,"frontMatter":{"sidebar_position":4,"title":"Generate CSR & Import Certificates for Docker","sidebar_label":"Generate CSR \ud83d\udd10","description":"Learn to generate a Certificate Signing Request (CSR), obtain CA-signed certificates, and import them into the truststore for Docker image signing with Securosys."},"sidebar":"tutorialSidebar","previous":{"title":"Self-Signed Certificate","permalink":"/docker_signing/Tutorials/SelfSignedCert"},"next":{"title":"Sign Image","permalink":"/docker_signing/Tutorials/SignImage"}}');var i=n(74848),s=n(28453);n(65537),n(79329);const a={sidebar_position:4,title:"Generate CSR & Import Certificates for Docker",sidebar_label:"Generate CSR \ud83d\udd10",description:"Learn to generate a Certificate Signing Request (CSR), obtain CA-signed certificates, and import them into the truststore for Docker image signing with Securosys."},o="Certificate Signing Request (CSR)",c={},l=[{value:"Securosys Docker Signing Notation Plugin",id:"securosys-docker-signing-notation-plugin",level:3},{value:"Import Certificate into &#39;Notation&#39; Truststore",id:"import-certificate-into-notation-truststore",level:2}];function u(e){const t={a:"a",code:"code",h1:"h1",h2:"h2",h3:"h3",header:"header",hr:"hr",li:"li",p:"p",pre:"pre",strong:"strong",table:"table",tbody:"tbody",td:"td",th:"th",thead:"thead",tr:"tr",ul:"ul",...(0,s.R)(),...e.components};return(0,i.jsxs)(i.Fragment,{children:[(0,i.jsx)(t.header,{children:(0,i.jsx)(t.h1,{id:"certificate-signing-request-csr",children:"Certificate Signing Request (CSR)"})}),"\n",(0,i.jsx)(t.h3,{id:"securosys-docker-signing-notation-plugin",children:"Securosys Docker Signing Notation Plugin"}),"\n",(0,i.jsx)(t.p,{children:(0,i.jsx)(t.strong,{children:"Generate a Certificate Signing Request (CSR) and signing by trusted provider"})}),"\n",(0,i.jsxs)(t.p,{children:["The procedure below assumes that the command is run from the directory where the notation-securosys binary is stored, ",(0,i.jsx)(t.code,{children:"{BINARY_PATH}"}),"."]}),"\n",(0,i.jsx)(t.p,{children:"Follow this procedure if you would like to use a certificate signed by your certificate authority (CA) or for a publicly trusted certificate from a trust service provider (TSP):"}),"\n",(0,i.jsxs)(t.ul,{children:["\n",(0,i.jsxs)(t.li,{children:["\n",(0,i.jsx)(t.p,{children:"Create certificate signing request (CSR),"}),"\n"]}),"\n",(0,i.jsxs)(t.li,{children:["\n",(0,i.jsx)(t.p,{children:"Request certificate based on CSR from CA / TSP,"}),"\n"]}),"\n",(0,i.jsxs)(t.li,{children:["\n",(0,i.jsx)(t.p,{children:"Import certificate to 'Notation'."}),"\n"]}),"\n"]}),"\n",(0,i.jsxs)(t.p,{children:["To create a ",(0,i.jsx)(t.strong,{children:"Certificate Signing Request"}),", execute the following command:"]}),"\n",(0,i.jsx)(t.pre,{children:(0,i.jsx)(t.code,{className:"language-sh",children:"./notation-securosys generate-csr\n"})}),"\n",(0,i.jsxs)(t.p,{children:['After running the command, provide the appropriate parameters in JSON format (example shown below).\nGo to section "generate-certificate-request" in the ',(0,i.jsx)(t.code,{children:"input_example.json"})," file and prepare the parameters according to ",(0,i.jsx)(t.a,{href:"./CreateSigningKey#prepare-docker-image-signing-key-parameters",children:"chapter"})," (use any editor you are familiar with):"]}),"\n",(0,i.jsx)(t.pre,{children:(0,i.jsx)(t.code,{className:"language-js",children:'{ \n\n  "keyId": "keyLabel", \n\n  "pluginConfig": { \n\n    "password": "optional" \n\n  }, \n\n  "certificate": { \n\n    "validity": 365, \n\n    "attributes": { \n\n      "commonName": "Securosys_CN", \n\n      "country": "CH", \n\n      "stateOrProvinceName": "Zurich", \n\n      "locality": "Zurich", \n\n      "organizationName": "Securosys SA", \n\n      "organizationUnitName": "IT" \n\n    } \n\n  } \n\n}\n'})}),"\n",(0,i.jsx)(t.p,{children:"Command parameters and description:"}),"\n",(0,i.jsxs)(t.table,{children:[(0,i.jsx)(t.thead,{children:(0,i.jsxs)(t.tr,{children:[(0,i.jsx)(t.th,{children:"Variable parameters"}),(0,i.jsx)(t.th,{children:"Command and parameter description"})]})}),(0,i.jsxs)(t.tbody,{children:[(0,i.jsxs)(t.tr,{children:[(0,i.jsx)(t.td,{children:(0,i.jsx)(t.code,{children:"CONFIG_PATH=$PLUGIN_CONFIG_PATH/config.json "})}),(0,i.jsxs)(t.td,{children:["The path to the Securosys Docker Signing Notation Plugin configuration file. To be added in front of the command only if the config file is not stored in the ",(0,i.jsx)(t.code,{children:"{BINARY_PATH}"})," or the environment variable is not set as described in ",(0,i.jsx)(t.a,{href:"/docker_signing/Installation/Plugin-Installation/AlternativeWays/PluginConfiguration#create-securosys-plugin-configuration-environment-variable-and-directory-optional",children:"chapter"}),"."]})]}),(0,i.jsxs)(t.tr,{children:[(0,i.jsx)(t.td,{children:(0,i.jsx)(t.code,{children:'"keyId": "pluginKeyId"'})}),(0,i.jsx)(t.td,{children:"Specify the key identifier (key name) associated with your signing key name."})]}),(0,i.jsxs)(t.tr,{children:[(0,i.jsx)(t.td,{children:(0,i.jsx)(t.code,{children:'["password":"optional"]'})}),(0,i.jsx)(t.td,{children:"Optionally the password for the signing key. Omit the parameter completely if the key has no password."})]}),(0,i.jsxs)(t.tr,{children:[(0,i.jsx)(t.td,{children:(0,i.jsx)(t.code,{children:'"certificate" {...}'})}),(0,i.jsx)(t.td,{children:"Specify your certificate parameters. These parameters are used for your certificate signature request (CSR). Set validity of certificate, by default set to 365 days. Adapt the attributes variables shown to best suit your certificate."})]})]})]}),"\n",(0,i.jsx)(t.hr,{}),"\n",(0,i.jsx)(t.p,{children:"Example command generating a certificate signing request based on a singing key without password:"}),"\n",(0,i.jsx)(t.pre,{children:(0,i.jsx)(t.code,{className:"language-sh",children:"./notation-securosys generate-csr\n"})}),"\n",(0,i.jsx)(t.pre,{children:(0,i.jsx)(t.code,{className:"language-js",children:'{ \n\n "keyId": "SecurosysImageSignKey02", \n\n  "pluginConfig": {}, \n\n  "certificate": { \n\n    "validity": 365, \n\n    "attributes": { \n\n      "commonName": "DockerImageSign05", \n\n      "country": "CH", \n\n      "stateOrProvinceName": "Zurich", \n\n      "locality": "Zurich", \n\n      "organizationName": "Securosys SA", \n\n      "organizationUnitName": "IT" \n\n    } \n  } \n} \n'})}),"\n",(0,i.jsx)(t.p,{children:"A certificate signing request will be returned. Example response of successful CSR generation:"}),"\n",(0,i.jsx)(t.pre,{children:(0,i.jsx)(t.code,{className:"language-js",children:"-----BEGIN NEW CERTIFICATE REQUEST----- \nMIIDDDCCAfQCAQAwbzELMAkGA1UEBhMCQ0gxDzANBgNVBAgTBlp1cmljaDEPMA0G \nA1UEBxMGWnVyaWNoMRUwEwYDVQQKEwxTZWN1cm9zeXMgU0ExCzAJBgNVBAsTAklU \n\u2026 \n-----END NEW CERTIFICATE REQUEST----- \n"})}),"\n",(0,i.jsx)(t.p,{children:"Afterwards send the certificate signing request file to the trusted certificate provider manually. Submit the certificate signing request (CSR) to your Certificate Authority / Trust Service Provider to obtain the certificate."}),"\n",(0,i.jsx)(t.h2,{id:"import-certificate-into-notation-truststore",children:"Import Certificate into 'Notation' Truststore"}),"\n",(0,i.jsxs)(t.p,{children:["After obtaining the certificate, save the certificate to directory ",(0,i.jsx)(t.code,{children:"${HOME}/Downloads/Securosys/"}),":"]}),"\n",(0,i.jsx)(t.pre,{children:(0,i.jsx)(t.code,{className:"language-sh",children:"cp ./certificatename.crt ~/Downloads/Securosys/\n"})}),"\n",(0,i.jsx)(t.p,{children:"Then, execute the command below to import the certificate:"}),"\n",(0,i.jsx)(t.pre,{children:(0,i.jsx)(t.code,{className:"language-sh",children:"./notation-securosys import-certificate\n"})}),"\n",(0,i.jsx)(t.p,{children:"After running the command, provide the appropriate parameters in JSON format including the full path to the certificate:"}),"\n",(0,i.jsx)(t.pre,{children:(0,i.jsx)(t.code,{className:"language-js",children:'{ \n "keyId": "SecurosysCertKey", \n\n "pluginConfig" : { \n\n   "password":"optional", \n\n   "certPath":"pathToCertificate",\n   "caPath":"pathtoCA"\n } \n}\n'})}),"\n",(0,i.jsx)(t.p,{children:"Example command:"}),"\n",(0,i.jsx)(t.pre,{children:(0,i.jsx)(t.code,{className:"language-sh",children:"./notation-securosys import-certificate\n"})}),"\n",(0,i.jsx)(t.pre,{children:(0,i.jsx)(t.code,{className:"language-js",children:'{ \n "keyId": "SecurosysCertKey", \n\n "pluginConfig" : { \n\n   "certPath":"/home/securosys/Downloads/Securosys/SecurosysImageSignKey01t.crt",\n   "caPath":"/home/securosys/Downloads/Securosys/SecurosysImageSignCA.crt"\n } \n} \n'})}),"\n",(0,i.jsx)(t.p,{children:"All created certificates are stored to the truststore of Notation in directory:"}),"\n",(0,i.jsx)(t.pre,{children:(0,i.jsx)(t.code,{className:"language-sh",children:"{NOTATION_CONFIG}/truststore/x509/ca/securosysTrustStore\n"})}),"\n",(0,i.jsx)(t.p,{children:"Verify the creation and find all existing certificates by listing the directory content. Notation does not provide any specific command to list the certificates."}),"\n",(0,i.jsx)(t.pre,{children:(0,i.jsx)(t.code,{className:"language-sh",children:"ls -al {NOTATION_CONFIG}/truststore/x509/ca/securosysTrustStore\n"})}),"\n",(0,i.jsxs)(t.p,{children:["After successfully creating the certificate, it is now possible to proceed to ",(0,i.jsx)(t.a,{href:"./SignImage",children:"sign Docker Images"}),"."]})]})}function d(e={}){const{wrapper:t}={...(0,s.R)(),...e.components};return t?(0,i.jsx)(t,{...e,children:(0,i.jsx)(u,{...e})}):u(e)}},28453:(e,t,n)=>{n.d(t,{R:()=>a,x:()=>o});var r=n(96540);const i={},s=r.createContext(i);function a(e){const t=r.useContext(s);return r.useMemo((function(){return"function"==typeof e?e(t):{...t,...e}}),[t,e])}function o(e){let t;return t=e.disableParentContext?"function"==typeof e.components?e.components(i):e.components||i:a(e.components),r.createElement(s.Provider,{value:t},e.children)}},65537:(e,t,n)=>{n.d(t,{A:()=>C});var r=n(96540),i=n(34164),s=n(65627),a=n(56347),o=n(50372),c=n(30604),l=n(11861),u=n(78749);function d(e){return r.Children.toArray(e).filter((e=>"\n"!==e)).map((e=>{if(!e||(0,r.isValidElement)(e)&&function(e){const{props:t}=e;return!!t&&"object"==typeof t&&"value"in t}(e))return e;throw new Error(`Docusaurus error: Bad <Tabs> child <${"string"==typeof e.type?e.type:e.type.name}>: all children of the <Tabs> component should be <TabItem>, and every <TabItem> should have a unique "value" prop.`)}))?.filter(Boolean)??[]}function h(e){const{values:t,children:n}=e;return(0,r.useMemo)((()=>{const e=t??function(e){return d(e).map((e=>{let{props:{value:t,label:n,attributes:r,default:i}}=e;return{value:t,label:n,attributes:r,default:i}}))}(n);return function(e){const t=(0,l.XI)(e,((e,t)=>e.value===t.value));if(t.length>0)throw new Error(`Docusaurus error: Duplicate values "${t.map((e=>e.value)).join(", ")}" found in <Tabs>. Every value needs to be unique.`)}(e),e}),[t,n])}function g(e){let{value:t,tabValues:n}=e;return n.some((e=>e.value===t))}function p(e){let{queryString:t=!1,groupId:n}=e;const i=(0,a.W6)(),s=function(e){let{queryString:t=!1,groupId:n}=e;if("string"==typeof t)return t;if(!1===t)return null;if(!0===t&&!n)throw new Error('Docusaurus error: The <Tabs> component groupId prop is required if queryString=true, because this value is used as the search param name. You can also provide an explicit value such as queryString="my-search-param".');return n??null}({queryString:t,groupId:n});return[(0,c.aZ)(s),(0,r.useCallback)((e=>{if(!s)return;const t=new URLSearchParams(i.location.search);t.set(s,e),i.replace({...i.location,search:t.toString()})}),[s,i])]}function f(e){const{defaultValue:t,queryString:n=!1,groupId:i}=e,s=h(e),[a,c]=(0,r.useState)((()=>function(e){let{defaultValue:t,tabValues:n}=e;if(0===n.length)throw new Error("Docusaurus error: the <Tabs> component requires at least one <TabItem> children component");if(t){if(!g({value:t,tabValues:n}))throw new Error(`Docusaurus error: The <Tabs> has a defaultValue "${t}" but none of its children has the corresponding value. Available values are: ${n.map((e=>e.value)).join(", ")}. If you intend to show no default tab, use defaultValue={null} instead.`);return t}const r=n.find((e=>e.default))??n[0];if(!r)throw new Error("Unexpected error: 0 tabValues");return r.value}({defaultValue:t,tabValues:s}))),[l,d]=p({queryString:n,groupId:i}),[f,m]=function(e){let{groupId:t}=e;const n=function(e){return e?`docusaurus.tab.${e}`:null}(t),[i,s]=(0,u.Dv)(n);return[i,(0,r.useCallback)((e=>{n&&s.set(e)}),[n,s])]}({groupId:i}),y=(()=>{const e=l??f;return g({value:e,tabValues:s})?e:null})();(0,o.A)((()=>{y&&c(y)}),[y]);return{selectedValue:a,selectValue:(0,r.useCallback)((e=>{if(!g({value:e,tabValues:s}))throw new Error(`Can't select invalid tab value=${e}`);c(e),d(e),m(e)}),[d,m,s]),tabValues:s}}var m=n(9136);const y={tabList:"tabList__CuJ",tabItem:"tabItem_LNqP"};var x=n(74848);function b(e){let{className:t,block:n,selectedValue:r,selectValue:a,tabValues:o}=e;const c=[],{blockElementScrollPositionUntilNextRender:l}=(0,s.a_)(),u=e=>{const t=e.currentTarget,n=c.indexOf(t),i=o[n].value;i!==r&&(l(t),a(i))},d=e=>{let t=null;switch(e.key){case"Enter":u(e);break;case"ArrowRight":{const n=c.indexOf(e.currentTarget)+1;t=c[n]??c[0];break}case"ArrowLeft":{const n=c.indexOf(e.currentTarget)-1;t=c[n]??c[c.length-1];break}}t?.focus()};return(0,x.jsx)("ul",{role:"tablist","aria-orientation":"horizontal",className:(0,i.A)("tabs",{"tabs--block":n},t),children:o.map((e=>{let{value:t,label:n,attributes:s}=e;return(0,x.jsx)("li",{role:"tab",tabIndex:r===t?0:-1,"aria-selected":r===t,ref:e=>{c.push(e)},onKeyDown:d,onClick:u,...s,className:(0,i.A)("tabs__item",y.tabItem,s?.className,{"tabs__item--active":r===t}),children:n??t},t)}))})}function j(e){let{lazy:t,children:n,selectedValue:s}=e;const a=(Array.isArray(n)?n:[n]).filter(Boolean);if(t){const e=a.find((e=>e.props.value===s));return e?(0,r.cloneElement)(e,{className:(0,i.A)("margin-top--md",e.props.className)}):null}return(0,x.jsx)("div",{className:"margin-top--md",children:a.map(((e,t)=>(0,r.cloneElement)(e,{key:t,hidden:e.props.value!==s})))})}function S(e){const t=f(e);return(0,x.jsxs)("div",{className:(0,i.A)("tabs-container",y.tabList),children:[(0,x.jsx)(b,{...t,...e}),(0,x.jsx)(j,{...t,...e})]})}function C(e){const t=(0,m.A)();return(0,x.jsx)(S,{...e,children:d(e.children)},String(t))}},79329:(e,t,n)=>{n.d(t,{A:()=>a});n(96540);var r=n(34164);const i={tabItem:"tabItem_Ymn6"};var s=n(74848);function a(e){let{children:t,hidden:n,className:a}=e;return(0,s.jsx)("div",{role:"tabpanel",className:(0,r.A)(i.tabItem,a),hidden:n,children:t})}}}]);