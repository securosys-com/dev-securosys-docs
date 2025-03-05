"use strict";(self.webpackChunksecurosys_docs=self.webpackChunksecurosys_docs||[]).push([[57034],{28453:(e,n,r)=>{r.d(n,{R:()=>t,x:()=>c});var s=r(96540);const o={},i=s.createContext(o);function t(e){const n=s.useContext(i);return s.useMemo((function(){return"function"==typeof e?e(n):{...n,...e}}),[n,e])}function c(e){let n;return n=e.disableParentContext?"function"==typeof e.components?e.components(o):e.components||o:t(e.components),s.createElement(i.Provider,{value:n},e.children)}},65537:(e,n,r)=>{r.d(n,{A:()=>_});var s=r(96540),o=r(34164),i=r(65627),t=r(56347),c=r(50372),l=r(30604),a=r(11861),d=r(78749);function u(e){return s.Children.toArray(e).filter((e=>"\n"!==e)).map((e=>{if(!e||(0,s.isValidElement)(e)&&function(e){const{props:n}=e;return!!n&&"object"==typeof n&&"value"in n}(e))return e;throw new Error(`Docusaurus error: Bad <Tabs> child <${"string"==typeof e.type?e.type:e.type.name}>: all children of the <Tabs> component should be <TabItem>, and every <TabItem> should have a unique "value" prop.`)}))?.filter(Boolean)??[]}function p(e){const{values:n,children:r}=e;return(0,s.useMemo)((()=>{const e=n??function(e){return u(e).map((e=>{let{props:{value:n,label:r,attributes:s,default:o}}=e;return{value:n,label:r,attributes:s,default:o}}))}(r);return function(e){const n=(0,a.XI)(e,((e,n)=>e.value===n.value));if(n.length>0)throw new Error(`Docusaurus error: Duplicate values "${n.map((e=>e.value)).join(", ")}" found in <Tabs>. Every value needs to be unique.`)}(e),e}),[n,r])}function h(e){let{value:n,tabValues:r}=e;return r.some((e=>e.value===n))}function g(e){let{queryString:n=!1,groupId:r}=e;const o=(0,t.W6)(),i=function(e){let{queryString:n=!1,groupId:r}=e;if("string"==typeof n)return n;if(!1===n)return null;if(!0===n&&!r)throw new Error('Docusaurus error: The <Tabs> component groupId prop is required if queryString=true, because this value is used as the search param name. You can also provide an explicit value such as queryString="my-search-param".');return r??null}({queryString:n,groupId:r});return[(0,l.aZ)(i),(0,s.useCallback)((e=>{if(!i)return;const n=new URLSearchParams(o.location.search);n.set(i,e),o.replace({...o.location,search:n.toString()})}),[i,o])]}function y(e){const{defaultValue:n,queryString:r=!1,groupId:o}=e,i=p(e),[t,l]=(0,s.useState)((()=>function(e){let{defaultValue:n,tabValues:r}=e;if(0===r.length)throw new Error("Docusaurus error: the <Tabs> component requires at least one <TabItem> children component");if(n){if(!h({value:n,tabValues:r}))throw new Error(`Docusaurus error: The <Tabs> has a defaultValue "${n}" but none of its children has the corresponding value. Available values are: ${r.map((e=>e.value)).join(", ")}. If you intend to show no default tab, use defaultValue={null} instead.`);return n}const s=r.find((e=>e.default))??r[0];if(!s)throw new Error("Unexpected error: 0 tabValues");return s.value}({defaultValue:n,tabValues:i}))),[a,u]=g({queryString:r,groupId:o}),[y,x]=function(e){let{groupId:n}=e;const r=function(e){return e?`docusaurus.tab.${e}`:null}(n),[o,i]=(0,d.Dv)(r);return[o,(0,s.useCallback)((e=>{r&&i.set(e)}),[r,i])]}({groupId:o}),f=(()=>{const e=a??y;return h({value:e,tabValues:i})?e:null})();(0,c.A)((()=>{f&&l(f)}),[f]);return{selectedValue:t,selectValue:(0,s.useCallback)((e=>{if(!h({value:e,tabValues:i}))throw new Error(`Can't select invalid tab value=${e}`);l(e),u(e),x(e)}),[u,x,i]),tabValues:i}}var x=r(9136);const f={tabList:"tabList__CuJ",tabItem:"tabItem_LNqP"};var m=r(74848);function j(e){let{className:n,block:r,selectedValue:s,selectValue:t,tabValues:c}=e;const l=[],{blockElementScrollPositionUntilNextRender:a}=(0,i.a_)(),d=e=>{const n=e.currentTarget,r=l.indexOf(n),o=c[r].value;o!==s&&(a(n),t(o))},u=e=>{let n=null;switch(e.key){case"Enter":d(e);break;case"ArrowRight":{const r=l.indexOf(e.currentTarget)+1;n=l[r]??l[0];break}case"ArrowLeft":{const r=l.indexOf(e.currentTarget)-1;n=l[r]??l[l.length-1];break}}n?.focus()};return(0,m.jsx)("ul",{role:"tablist","aria-orientation":"horizontal",className:(0,o.A)("tabs",{"tabs--block":r},n),children:c.map((e=>{let{value:n,label:r,attributes:i}=e;return(0,m.jsx)("li",{role:"tab",tabIndex:s===n?0:-1,"aria-selected":s===n,ref:e=>{l.push(e)},onKeyDown:u,onClick:d,...i,className:(0,o.A)("tabs__item",f.tabItem,i?.className,{"tabs__item--active":s===n}),children:r??n},n)}))})}function k(e){let{lazy:n,children:r,selectedValue:i}=e;const t=(Array.isArray(r)?r:[r]).filter(Boolean);if(n){const e=t.find((e=>e.props.value===i));return e?(0,s.cloneElement)(e,{className:(0,o.A)("margin-top--md",e.props.className)}):null}return(0,m.jsx)("div",{className:"margin-top--md",children:t.map(((e,n)=>(0,s.cloneElement)(e,{key:n,hidden:e.props.value!==i})))})}function b(e){const n=y(e);return(0,m.jsxs)("div",{className:(0,o.A)("tabs-container",f.tabList),children:[(0,m.jsx)(j,{...n,...e}),(0,m.jsx)(k,{...n,...e})]})}function _(e){const n=(0,x.A)();return(0,m.jsx)(b,{...e,children:u(e.children)},String(n))}},79329:(e,n,r)=>{r.d(n,{A:()=>t});r(96540);var s=r(34164);const o={tabItem:"tabItem_Ymn6"};var i=r(74848);function t(e){let{children:n,hidden:r,className:t}=e;return(0,i.jsx)("div",{role:"tabpanel",className:(0,s.A)(o.tabItem,t),hidden:r,children:n})}},94928:(e,n,r)=>{r.r(n),r.d(n,{assets:()=>d,contentTitle:()=>a,default:()=>h,frontMatter:()=>l,metadata:()=>s,toc:()=>u});const s=JSON.parse('{"id":"Installation/unpacking","title":"Docker Encryption Plugin - Download & Unpacking Skopeo","description":"Download & Unpacking","source":"@site/docker_encryption/Installation/unpacking.md","sourceDirName":"Installation","slug":"/Installation/unpacking","permalink":"/docker_encryption/Installation/unpacking","draft":false,"unlisted":false,"tags":[],"version":"current","lastUpdatedAt":1741162132000,"sidebarPosition":1,"frontMatter":{"sidebar_position":1,"title":"Docker Encryption Plugin - Download & Unpacking Skopeo","sidebar_label":"2. Download & Unpacking","description":"Download & Unpacking","keywords":["hsm","cloud hsm"]},"sidebar":"tutorialSidebar","previous":{"title":"1. Prerequisites","permalink":"/docker_encryption/Installation/prerequisites"},"next":{"title":"3. Create A Key","permalink":"/docker_encryption/Installation/createEncryptionKey"}}');var o=r(74848),i=r(28453),t=r(65537),c=r(79329);const l={sidebar_position:1,title:"Docker Encryption Plugin - Download & Unpacking Skopeo",sidebar_label:"2. Download & Unpacking",description:"Download & Unpacking",keywords:["hsm","cloud hsm"]},a="Download & Unpacking",d={},u=[{value:"Download and install Skopeo",id:"download-and-install-skopeo",level:2},{value:"Download Securosys Docker Image Encryption Plugin files",id:"download-securosys-docker-image-encryption-plugin-files",level:2},{value:"Unzip Securosys Docker Image Encryption Plugin binary",id:"unzip-securosys-docker-image-encryption-plugin-binary",level:3},{value:"Unzip Securosys Docker Image Encryption Plugin configuration file",id:"unzip-securosys-docker-image-encryption-plugin-configuration-file",level:3},{value:"Read the Securosys Docker Image Encryption Plugin Release Notes",id:"read-the-securosys-docker-image-encryption-plugin-release-notes",level:3}];function p(e){const n={a:"a",admonition:"admonition",code:"code",em:"em",h1:"h1",h2:"h2",h3:"h3",header:"header",li:"li",p:"p",pre:"pre",strong:"strong",table:"table",tbody:"tbody",td:"td",th:"th",thead:"thead",tr:"tr",ul:"ul",...(0,i.R)(),...e.components};return(0,o.jsxs)(o.Fragment,{children:[(0,o.jsx)(n.header,{children:(0,o.jsx)(n.h1,{id:"download--unpacking",children:"Download & Unpacking"})}),"\n",(0,o.jsx)(n.admonition,{title:"Caution",type:"tip",children:(0,o.jsxs)(n.p,{children:["In this guide we will use the ",(0,o.jsx)(n.strong,{children:"Linux Ubuntu 22 (amd 64)"}),". For other operating systems and Linux distributions, please refer to the referenced guides."]})}),"\n",(0,o.jsx)(n.h2,{id:"download-and-install-skopeo",children:"Download and install Skopeo"}),"\n",(0,o.jsxs)(n.p,{children:["To successfully encrypt and decrypt Docker images, it is required to install ",(0,o.jsx)(n.strong,{children:"Skopeo"}),"."]}),"\n",(0,o.jsxs)(n.p,{children:["To install ",(0,o.jsx)(n.strong,{children:"Skopeo"})," on Linux Ubuntu 22, run the following commands:"]}),"\n",(0,o.jsxs)(t.A,{groupId:"device-setup",children:[(0,o.jsx)(c.A,{value:"linux",label:"Linux",default:!0,children:(0,o.jsx)(n.pre,{children:(0,o.jsx)(n.code,{className:"language-sh",children:"sudo apt-get -y update\nsudo apt-get -y install skopeo\n"})})}),(0,o.jsx)(c.A,{value:"mac",label:"MacOS",default:!0,children:(0,o.jsx)(n.pre,{children:(0,o.jsx)(n.code,{className:"language-sh",children:"brew install skopeo\n"})})})]}),"\n",(0,o.jsx)(n.h2,{id:"download-securosys-docker-image-encryption-plugin-files",children:"Download Securosys Docker Image Encryption Plugin files"}),"\n",(0,o.jsxs)(n.p,{children:["Follow the instructions provided in section ",(0,o.jsx)(n.a,{href:"/docker_encryption/Downloads/",children:"Download"})," regarding the download of the ",(0,o.jsx)(n.em,{children:"Securosys Docker Image Encryption Plugin"}),"."]}),"\n",(0,o.jsx)(n.admonition,{title:"TIP",type:"tip",children:(0,o.jsxs)(n.p,{children:["The examples in this guide are given for Securosys Docker Image Encryption Plugin version ",(0,o.jsx)(n.strong,{children:(0,o.jsx)(n.code,{children:"latest"})}),".\nFor specific versions, adjust the version variable in the file name and subfolder, e.g. ",(0,o.jsx)(n.a,{href:"https://securosys.jfrog.io/artifactory/docker-security/docker-",children:"https://securosys.jfrog.io/artifactory/docker-security/docker-"})," encryption/",(0,o.jsx)(n.strong,{children:(0,o.jsx)(n.code,{children:"v1.0.0"})}),"/securosys_docker-encryption-skopeo-plugin-",(0,o.jsx)(n.strong,{children:(0,o.jsx)(n.code,{children:"v1.0.0"})}),".zip/"]})}),"\n",(0,o.jsx)(n.p,{children:"The Securosys Docker Image Encryption Plugin files are packaged as a single compressed file that includes:"}),"\n",(0,o.jsxs)(n.ul,{children:["\n",(0,o.jsx)(n.li,{children:"Release Notes"}),"\n",(0,o.jsx)(n.li,{children:"Config file"}),"\n",(0,o.jsx)(n.li,{children:"Binary file (platform specific)"}),"\n",(0,o.jsx)(n.li,{children:"Download-Link file"}),"\n"]}),"\n",(0,o.jsxs)(n.p,{children:["When downloading artefacts from the Securosys repository, it is assumed that the files will be downloaded to a ",(0,o.jsx)(n.strong,{children:(0,o.jsx)(n.code,{children:"Securosys"})})," directory within the ",(0,o.jsx)(n.strong,{children:(0,o.jsx)(n.code,{children:"Downloads"})})," directory in the user\u2019s home directory."]}),"\n",(0,o.jsxs)(n.admonition,{title:"NOTE",type:"note",children:[(0,o.jsxs)(n.p,{children:["All commands executed during the download, unzip and installation procedures presume that the path to the working directory is:  ",(0,o.jsx)(n.strong,{children:(0,o.jsx)(n.code,{children:"${HOME}/Downloads/Securosys/"})}),"."]}),(0,o.jsx)(n.p,{children:"Operating system dependent parameter:"}),(0,o.jsxs)(n.table,{children:[(0,o.jsx)(n.thead,{children:(0,o.jsxs)(n.tr,{children:[(0,o.jsx)(n.th,{children:(0,o.jsx)(n.strong,{children:"Variable"})}),(0,o.jsx)(n.th,{children:(0,o.jsx)(n.strong,{children:"Variable options"})})]})}),(0,o.jsx)(n.tbody,{children:(0,o.jsxs)(n.tr,{children:[(0,o.jsx)(n.td,{children:(0,o.jsx)(n.strong,{children:(0,o.jsx)(n.code,{children:"${HOME}/"})})}),(0,o.jsxs)(n.td,{children:[(0,o.jsx)(n.strong,{children:"Linux"}),':  "/home/USERNAME/"',(0,o.jsx)("br",{})," ",(0,o.jsx)(n.strong,{children:"macOS"}),':  "/Users/USERNAME/"',(0,o.jsx)("br",{})," ",(0,o.jsx)(n.strong,{children:"Windows"}),':  "C:\\Users\\USERNAME"']})]})})]})]}),"\n",(0,o.jsx)(n.h3,{id:"unzip-securosys-docker-image-encryption-plugin-binary",children:"Unzip Securosys Docker Image Encryption Plugin binary"}),"\n",(0,o.jsx)(n.p,{children:"Navigate to the download folder:"}),"\n",(0,o.jsx)(n.p,{children:(0,o.jsx)(n.code,{children:"cd ${HOME}/Downloads/Securosys"})}),"\n",(0,o.jsxs)(n.p,{children:["Unzip the downloaded ",(0,o.jsx)(n.code,{children:"securosys_docker-encryption-skopeo-plugin-executable-latest.zip"})," file by executing the below command:"]}),"\n",(0,o.jsx)(n.pre,{children:(0,o.jsx)(n.code,{className:"language-sh",children:"unzip securosys_docker-encryption-skopeo-plugin-executable-latest.zip\n"})}),"\n",(0,o.jsx)(n.pre,{children:(0,o.jsx)(n.code,{className:"language-sh",children:"Archive:  securosys_docker-encryption-skopeo-plugin-executable-latest.zip\n    extracting: securosys_docker-encryption-skopeo-plugin_darwin_arm64.zip\n    extracting:securosys_docker-encryption-skopeo-plugin_netbsd_amd64.zip\n    extracting: securosys_docker-encryption-skopeo-plugin_linux_arm64.zip\n    extracting: securosys_docker-encryption-skopeo-plugin_freebsd_arm.zip\n    extracting: securosys_docker-encryption-skopeo-plugin_linux_amd64.zip\n    extracting: securosys_docker-encryption-skopeo-plugin_freebsd_amd64.zip\n    extracting: securosys_docker-encryption-skopeo-plugin_linux_386.zip\n    extracting: securosys_docker-encryption-skopeo-plugin_solaris_amd64.zip\n    extracting: securosys_docker-encryption-skopeo-plugin_openbsd_386.zip\n    extracting: securosys_docker-encryption-skopeo-plugin_darwin_amd64.zip\n    extracting: securosys_docker-encryption-skopeo-plugin_netbsd_386.zip\n    extracting: securosys_docker-encryption-skopeo-plugin_netbsd_arm.zip\n    extracting: securosys_docker-encryption-skopeo-plugin_openbsd_amd64.zip\n    extracting: securosys_docker-encryption-skopeo-plugin_openbsd_arm.zip\n        inflating: securosys_docker-encryption-skopeo-plugin_SHA256SUMS\n    extracting: securosys_docker-encryption-skopeo-plugin_freebsd_386.zip \n"})}),"\n",(0,o.jsx)(n.p,{children:"Unzip the binary file for your platform (e.g. for Ubuntu 22):"}),"\n",(0,o.jsx)(n.pre,{children:(0,o.jsx)(n.code,{children:"unzip securosys_docker-encryption-skopeo-plugin_linux_amd64.zip\n"})}),"\n",(0,o.jsx)(n.pre,{children:(0,o.jsx)(n.code,{children:"Archive:  securosys_ docker-encryption-skopeo-plugin_darwin_arm64.zip\n    inflating: skopeo-securosys\n    inflating: skopeo-securosys_SHA256SUM\n"})}),"\n",(0,o.jsx)(n.p,{children:"The unzipped file contains the following content:"}),"\n",(0,o.jsxs)(n.table,{children:[(0,o.jsx)(n.thead,{children:(0,o.jsxs)(n.tr,{children:[(0,o.jsx)(n.th,{children:(0,o.jsx)(n.strong,{children:"Extraction directory & files"})}),(0,o.jsx)(n.th,{children:(0,o.jsx)(n.strong,{children:"File description"})})]})}),(0,o.jsxs)(n.tbody,{children:[(0,o.jsxs)(n.tr,{children:[(0,o.jsx)(n.td,{children:"./skopeo-securosys"}),(0,o.jsx)(n.td,{children:"Securosys Docker Image Signing Plugin binary"})]}),(0,o.jsxs)(n.tr,{children:[(0,o.jsx)(n.td,{children:"./skopeo-securosys_SHA265SUM"}),(0,o.jsx)(n.td,{children:"SHA256 check sum for the notation-securosys binary"})]})]})]}),"\n",(0,o.jsx)(n.p,{children:"Confirm that the shasum command succeeds for the extracted binary file with the following command:"}),"\n",(0,o.jsx)(n.pre,{children:(0,o.jsx)(n.code,{children:"shasum --check skopeo_securosys_SHA256SUM\n"})}),"\n",(0,o.jsx)(n.p,{children:"The output should result in OK. Example output:"}),"\n",(0,o.jsx)(n.pre,{children:(0,o.jsx)(n.code,{children:"skopeo_securosys: OK\n"})}),"\n",(0,o.jsx)(n.h3,{id:"unzip-securosys-docker-image-encryption-plugin-configuration-file",children:"Unzip Securosys Docker Image Encryption Plugin configuration file"}),"\n",(0,o.jsxs)(n.p,{children:["Unzip the downloaded ",(0,o.jsx)(n.code,{children:"securosys_docker-encryption-skopeo-plugin-configuration-latest.zip"})," file by executing the below command:"]}),"\n",(0,o.jsx)(n.pre,{children:(0,o.jsx)(n.code,{className:"language-sh",children:"unzip securosys_docker-encryption-skopeo-plugin-configuration-latest.zip\n"})}),"\n",(0,o.jsx)(n.pre,{children:(0,o.jsx)(n.code,{children:"Archive:  securosys_docker-encryption-skopeo-plugin-configuration-latest.zip\n    inflating: ocicrypt.conf\n        creating: config-files/ \n        creating: config-files/log/\n"})}),"\n",(0,o.jsx)(n.p,{children:"The unzipped file contains the following content:"}),"\n",(0,o.jsxs)(n.table,{children:[(0,o.jsx)(n.thead,{children:(0,o.jsxs)(n.tr,{children:[(0,o.jsx)(n.th,{children:(0,o.jsx)(n.strong,{children:"Extraction directory and files"})}),(0,o.jsx)(n.th,{children:(0,o.jsx)(n.strong,{children:"File Description"})})]})}),(0,o.jsxs)(n.tbody,{children:[(0,o.jsxs)(n.tr,{children:[(0,o.jsx)(n.td,{children:"./config.json"}),(0,o.jsx)(n.td,{children:"Securosys Docker Image Signing Plugin connectionconfiguration file"})]}),(0,o.jsxs)(n.tr,{children:[(0,o.jsx)(n.td,{children:"./config-files/log"}),(0,o.jsx)(n.td,{children:"Folder create by default, not used in this project"})]})]})]}),"\n",(0,o.jsx)(n.h3,{id:"read-the-securosys-docker-image-encryption-plugin-release-notes",children:"Read the Securosys Docker Image Encryption Plugin Release Notes"}),"\n",(0,o.jsx)(n.p,{children:"Refer to the release notes for precise instructions pertaining to the applicable release:"}),"\n",(0,o.jsx)(n.pre,{children:(0,o.jsx)(n.code,{className:"language-sh",children:"less securosys_docker-encryption-skopeo-plugin-releasenotes-latest.md\n"})})]})}function h(e={}){const{wrapper:n}={...(0,i.R)(),...e.components};return n?(0,o.jsx)(n,{...e,children:(0,o.jsx)(p,{...e})}):p(e)}}}]);