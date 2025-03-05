"use strict";(self.webpackChunksecurosys_docs=self.webpackChunksecurosys_docs||[]).push([[74673],{28453:(e,n,t)=>{t.d(n,{R:()=>s,x:()=>a});var r=t(96540);const i={},o=r.createContext(i);function s(e){const n=r.useContext(o);return r.useMemo((function(){return"function"==typeof e?e(n):{...n,...e}}),[n,e])}function a(e){let n;return n=e.disableParentContext?"function"==typeof e.components?e.components(i):e.components||i:s(e.components),r.createElement(o.Provider,{value:n},e.children)}},65537:(e,n,t)=>{t.d(n,{A:()=>v});var r=t(96540),i=t(34164),o=t(65627),s=t(56347),a=t(50372),l=t(30604),c=t(11861),u=t(78749);function d(e){return r.Children.toArray(e).filter((e=>"\n"!==e)).map((e=>{if(!e||(0,r.isValidElement)(e)&&function(e){const{props:n}=e;return!!n&&"object"==typeof n&&"value"in n}(e))return e;throw new Error(`Docusaurus error: Bad <Tabs> child <${"string"==typeof e.type?e.type:e.type.name}>: all children of the <Tabs> component should be <TabItem>, and every <TabItem> should have a unique "value" prop.`)}))?.filter(Boolean)??[]}function h(e){const{values:n,children:t}=e;return(0,r.useMemo)((()=>{const e=n??function(e){return d(e).map((e=>{let{props:{value:n,label:t,attributes:r,default:i}}=e;return{value:n,label:t,attributes:r,default:i}}))}(t);return function(e){const n=(0,c.XI)(e,((e,n)=>e.value===n.value));if(n.length>0)throw new Error(`Docusaurus error: Duplicate values "${n.map((e=>e.value)).join(", ")}" found in <Tabs>. Every value needs to be unique.`)}(e),e}),[n,t])}function p(e){let{value:n,tabValues:t}=e;return t.some((e=>e.value===n))}function g(e){let{queryString:n=!1,groupId:t}=e;const i=(0,s.W6)(),o=function(e){let{queryString:n=!1,groupId:t}=e;if("string"==typeof n)return n;if(!1===n)return null;if(!0===n&&!t)throw new Error('Docusaurus error: The <Tabs> component groupId prop is required if queryString=true, because this value is used as the search param name. You can also provide an explicit value such as queryString="my-search-param".');return t??null}({queryString:n,groupId:t});return[(0,l.aZ)(o),(0,r.useCallback)((e=>{if(!o)return;const n=new URLSearchParams(i.location.search);n.set(o,e),i.replace({...i.location,search:n.toString()})}),[o,i])]}function y(e){const{defaultValue:n,queryString:t=!1,groupId:i}=e,o=h(e),[s,l]=(0,r.useState)((()=>function(e){let{defaultValue:n,tabValues:t}=e;if(0===t.length)throw new Error("Docusaurus error: the <Tabs> component requires at least one <TabItem> children component");if(n){if(!p({value:n,tabValues:t}))throw new Error(`Docusaurus error: The <Tabs> has a defaultValue "${n}" but none of its children has the corresponding value. Available values are: ${t.map((e=>e.value)).join(", ")}. If you intend to show no default tab, use defaultValue={null} instead.`);return n}const r=t.find((e=>e.default))??t[0];if(!r)throw new Error("Unexpected error: 0 tabValues");return r.value}({defaultValue:n,tabValues:o}))),[c,d]=g({queryString:t,groupId:i}),[y,f]=function(e){let{groupId:n}=e;const t=function(e){return e?`docusaurus.tab.${e}`:null}(n),[i,o]=(0,u.Dv)(t);return[i,(0,r.useCallback)((e=>{t&&o.set(e)}),[t,o])]}({groupId:i}),m=(()=>{const e=c??y;return p({value:e,tabValues:o})?e:null})();(0,a.A)((()=>{m&&l(m)}),[m]);return{selectedValue:s,selectValue:(0,r.useCallback)((e=>{if(!p({value:e,tabValues:o}))throw new Error(`Can't select invalid tab value=${e}`);l(e),d(e),f(e)}),[d,f,o]),tabValues:o}}var f=t(9136);const m={tabList:"tabList__CuJ",tabItem:"tabItem_LNqP"};var x=t(74848);function b(e){let{className:n,block:t,selectedValue:r,selectValue:s,tabValues:a}=e;const l=[],{blockElementScrollPositionUntilNextRender:c}=(0,o.a_)(),u=e=>{const n=e.currentTarget,t=l.indexOf(n),i=a[t].value;i!==r&&(c(n),s(i))},d=e=>{let n=null;switch(e.key){case"Enter":u(e);break;case"ArrowRight":{const t=l.indexOf(e.currentTarget)+1;n=l[t]??l[0];break}case"ArrowLeft":{const t=l.indexOf(e.currentTarget)-1;n=l[t]??l[l.length-1];break}}n?.focus()};return(0,x.jsx)("ul",{role:"tablist","aria-orientation":"horizontal",className:(0,i.A)("tabs",{"tabs--block":t},n),children:a.map((e=>{let{value:n,label:t,attributes:o}=e;return(0,x.jsx)("li",{role:"tab",tabIndex:r===n?0:-1,"aria-selected":r===n,ref:e=>{l.push(e)},onKeyDown:d,onClick:u,...o,className:(0,i.A)("tabs__item",m.tabItem,o?.className,{"tabs__item--active":r===n}),children:t??n},n)}))})}function j(e){let{lazy:n,children:t,selectedValue:o}=e;const s=(Array.isArray(t)?t:[t]).filter(Boolean);if(n){const e=s.find((e=>e.props.value===o));return e?(0,r.cloneElement)(e,{className:(0,i.A)("margin-top--md",e.props.className)}):null}return(0,x.jsx)("div",{className:"margin-top--md",children:s.map(((e,n)=>(0,r.cloneElement)(e,{key:n,hidden:e.props.value!==o})))})}function k(e){const n=y(e);return(0,x.jsxs)("div",{className:(0,i.A)("tabs-container",m.tabList),children:[(0,x.jsx)(b,{...n,...e}),(0,x.jsx)(j,{...n,...e})]})}function v(e){const n=(0,f.A)();return(0,x.jsx)(k,{...e,children:d(e.children)},String(n))}},78481:(e,n,t)=>{t.r(n),t.d(n,{assets:()=>u,contentTitle:()=>c,default:()=>p,frontMatter:()=>l,metadata:()=>r,toc:()=>d});const r=JSON.parse('{"id":"Quickstart/quickstart","title":"Getting started with Docker Encryption HSM Plugin","description":"Getting started with Docker Encryption HSM Plugin","source":"@site/docker_encryption/Quickstart/quickstart.md","sourceDirName":"Quickstart","slug":"/Quickstart/","permalink":"/docker_encryption/Quickstart/","draft":false,"unlisted":false,"tags":[],"version":"current","lastUpdatedAt":1741162132000,"sidebarPosition":0,"frontMatter":{"sidebar_position":0,"title":"Getting started with Docker Encryption HSM Plugin","sidebar_label":"Quick Start","description":"Getting started with Docker Encryption HSM Plugin","keywords":["hsm","cloud hsm"]},"sidebar":"tutorialSidebar","previous":{"title":"Get Started","permalink":"/docker_encryption/category/get-started"},"next":{"title":"Use Cases","permalink":"/docker_encryption/category/use-cases"}}');var i=t(74848),o=t(28453),s=t(65537),a=t(79329);const l={sidebar_position:0,title:"Getting started with Docker Encryption HSM Plugin",sidebar_label:"Quick Start",description:"Getting started with Docker Encryption HSM Plugin",keywords:["hsm","cloud hsm"]},c="Getting Started with Docker Encryption",u={},d=[];function h(e){const n={a:"a",admonition:"admonition",code:"code",h1:"h1",header:"header",li:"li",ol:"ol",p:"p",pre:"pre",...(0,o.R)(),...e.components};return(0,i.jsxs)(i.Fragment,{children:[(0,i.jsx)(n.header,{children:(0,i.jsx)(n.h1,{id:"getting-started-with-docker-encryption",children:"Getting Started with Docker Encryption"})}),"\n",(0,i.jsxs)(n.p,{children:["This quick start guide provides a comprehensive task listing to download, setup and use the Securosys Docker Image Encryption plugin. For more detailled detailled instructions consult the ",(0,i.jsx)(n.a,{href:"../category/installation",children:"Installation"})," and ",(0,i.jsx)(n.a,{href:"../category/tutorial",children:"Tutorial"})," section."]}),"\n",(0,i.jsxs)(n.ol,{children:["\n",(0,i.jsxs)(n.li,{children:["Download and install ",(0,i.jsx)(n.a,{href:"https://github.com/containers/skopeo",children:"Skopeo"})]}),"\n"]}),"\n",(0,i.jsxs)(s.A,{groupId:"device-setup",children:[(0,i.jsx)(a.A,{value:"linux",label:"Linux",default:!0,children:(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-sh",children:"sudo apt-get -y update\nsudo apt-get -y install skopeo\n"})})}),(0,i.jsx)(a.A,{value:"mac",label:"MacOS",default:!0,children:(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-sh",children:"brew install skopeo\n"})})})]}),"\n",(0,i.jsxs)(n.ol,{start:"2",children:["\n",(0,i.jsxs)(n.li,{children:["\n",(0,i.jsx)(n.p,{children:"Download Securosys Docker Image Encryption Plugin files"}),"\n",(0,i.jsxs)(n.p,{children:["Follow the instructions provided in section ",(0,i.jsx)(n.a,{href:"/docker_encryption/Downloads/",children:"Download"}),"."]}),"\n"]}),"\n",(0,i.jsxs)(n.li,{children:["\n",(0,i.jsx)(n.p,{children:"Unzip Securosys Docker Image Encryption Plugin binary"}),"\n"]}),"\n"]}),"\n",(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-sh",children:"unzip securosys_docker-encryption-skopeo-plugin-executable-latest.zip\n"})}),"\n",(0,i.jsxs)(n.ol,{start:"4",children:["\n",(0,i.jsx)(n.li,{children:"Unzip Securosys Docker Image Encryption Plugin configuration file"}),"\n"]}),"\n",(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-sh",children:"unzip securosys_docker-encryption-skopeo-plugin-configuration-latest.zip\n"})}),"\n",(0,i.jsxs)(n.ol,{start:"5",children:["\n",(0,i.jsx)(n.li,{children:"Read the Securosys Docker Image Encryption Plugin Release Notes"}),"\n"]}),"\n",(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-sh",children:"less securosys_docker-encryption-skopeo-plugin-releasenotes-latest.md\n"})}),"\n",(0,i.jsxs)(n.ol,{start:"6",children:["\n",(0,i.jsx)(n.li,{children:"Create an encryption key on the HSM (unless a key has been generated before)"}),"\n"]}),"\n",(0,i.jsxs)(s.A,{groupId:"device-setup",children:[(0,i.jsx)(a.A,{value:"linux",label:"Linux / MacOS",default:!0,children:(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-sh",children:'curl -X \'POST\' \\ \n# highlight-next-line-note\n# highlight-next-line\n    \'<TSB_APIendpoint>/v1/key\' \\\n    -H \'accept: application/json\' \\ \n# highlight-next-line-note\n# highlight-next-line\n    -H \'Authorization: Bearer ergq0ejgadjlfkgjaldfjgaodf9gjad0f9hgadfhgadhfogiah\u2026\'\\\n    -H \'Content-Type: application/json\' \\ \n    -d \'{ \n# highlight-next-line-note\n# highlight-next-line\n    "label": "SecurosysEncKey01", \n    "algorithm": "RSA", \n    "keySize": 2048, \n    "attributes": {\n# highlight-start\n# highlight-note-start\n     "encrypt": true,\n     "decrypt": true,\n# highlight-end     \n# highlight-note-end\n     "verify": false, \n     "sign": false, \n     "wrap": false, \n     "unwrap": false, \n     "derive": false, \n     "bip32": false, \n     "extractable": false,\n     "modifiable": false,\n     "destroyable": false,\n     "sensitive": true,\n     "copyable": false \n    } \n} \'\n'})})}),(0,i.jsx)(a.A,{value:"Windows",label:"Windows",default:!0,children:(0,i.jsx)(n.p,{children:"curl command for Windows"})})]}),"\n",(0,i.jsxs)(n.ol,{start:"7",children:["\n",(0,i.jsxs)(n.li,{children:["Copy the plugin binary ",(0,i.jsx)(n.code,{children:"skopeo-securosys"})," and the ",(0,i.jsx)(n.code,{children:"ocicrypt.conf"})," to ",(0,i.jsx)(n.code,{children:"${HOME}/Securosys/skopeo"})," and adapt the parameters ",(0,i.jsx)(n.code,{children:"pathToExecutable"}),", ",(0,i.jsx)(n.code,{children:"-cipher-algorithm"}),", ",(0,i.jsx)(n.code,{children:"tsb-api-endpoint"}),", ",(0,i.jsx)(n.code,{children:"-auth"}),", ",(0,i.jsx)(n.code,{children:"-token"})," , ",(0,i.jsx)(n.code,{children:"-keyOperationToken"}),", ",(0,i.jsx)(n.code,{children:"-publicKey"}),", ",(0,i.jsx)(n.code,{children:"-privateKey"})," according to your environment in ",(0,i.jsx)(n.code,{children:"ocicrypt.conf"}),":"]}),"\n"]}),"\n",(0,i.jsx)(n.admonition,{title:"NOTE",type:"note",children:(0,i.jsxs)(n.p,{children:["When utilizing ",(0,i.jsx)(n.a,{href:"../../cloudhsm/overview",children:"CloudHSM"})," service, refer to ",(0,i.jsx)(n.a,{href:"/connectivity-details/cloudhsm-connectivity-details",children:"Cloud Connectivity Details"})," for accurate API-Endpoint URI. For on-premise deployments, verify API-Endpoint URI with your administrator. Contact your service administrator for authentication credentials in any setup (on-prem or cloud)."]})}),"\n",(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-sh",metastring:"{5,7,8,9,10,11,12,13,14,15}",children:'{ \n    "key-providers": { \n        "securosys_encryption": { \n            "cmd": { \n            "path":"/<pathToExecutable>/Skopeo-securosys",\n            "args":  [ \n                "-cipher-algorithm <yourCipherAlgorithm>",\n                "-tsb-api-endpoint <TSB_APIendpoint>",\n                "-auth <TOKEN>",\n                "-token <yourToken>",\n                "-certpath <PathToCrt>",\n                "-keypath <PathToKey>",\n                "-keyOperationToken <TSB-TOKEN>",\n                "-publicKey <PUBLIC_KEY>",\n                "-privateKey <PRIVATE_KEY>"\n                ] \n            } \n        } \n    } \n} \n'})}),"\n",(0,i.jsxs)(n.ol,{start:"8",children:["\n",(0,i.jsxs)(n.li,{children:["Prepare a sample image for encryption or use an already-built image from any public/private registry by pulling it into your local repository. Ensure the image is OCI-compliant (",(0,i.jsx)(n.a,{href:"https://github.com/containers/skopeo",children:"Skopeo"}),"). As an example, we utilize the Alpine project's image from docker.io and copy it to the working directory:"]}),"\n"]}),"\n",(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-sh",children:"skopeo copy docker://docker.io/amd64/alpine:latest oci:alpine\n"})}),"\n",(0,i.jsxs)(n.ol,{start:"9",children:["\n",(0,i.jsx)(n.li,{children:"Encrypt the image as shown below:"}),"\n"]}),"\n",(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-sh",children:"[KEY_PASSWORD=password] OCICRYPT_KEYPROVIDER_CONFIG=<pathToConfig>/ocicrypt.conf skopeo --override-os linux copy --encryption-key provider:skopeo-securosys:<keyLabel> oci:alpine oci:apline-encrypted \n"})}),"\n",(0,i.jsxs)(n.ol,{start:"10",children:["\n",(0,i.jsx)(n.li,{children:"Decrypt the image as shown below:"}),"\n"]}),"\n",(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{children:"[KEY_PASSWORD=<password>] OCICRYPT_KEYPROVIDER_CONFIG=/<pathToConfig>/ocicrypt.conf skopeo --override-os linux copy --decryption-key provider:skopeo-securosys:<keyLabel> oci:alpine-encrypted oci:alpine-decrypted\n"})})]})}function p(e={}){const{wrapper:n}={...(0,o.R)(),...e.components};return n?(0,i.jsx)(n,{...e,children:(0,i.jsx)(h,{...e})}):h(e)}},79329:(e,n,t)=>{t.d(n,{A:()=>s});t(96540);var r=t(34164);const i={tabItem:"tabItem_Ymn6"};var o=t(74848);function s(e){let{children:n,hidden:t,className:s}=e;return(0,o.jsx)("div",{role:"tabpanel",className:(0,r.A)(i.tabItem,s),hidden:t,children:n})}}}]);