"use strict";(self.webpackChunksecurosys_docs=self.webpackChunksecurosys_docs||[]).push([[78176],{19708:(e,t,r)=>{r.r(t),r.d(t,{assets:()=>l,contentTitle:()=>i,default:()=>d,frontMatter:()=>s,metadata:()=>n,toc:()=>u});const n=JSON.parse('{"id":"Quickstart","title":"Getting started with BYOK for AWS","description":"The process described in this document allows maintaining ownership and management over your KMS keys and to generate them using entropy in a safe environment within the Securosys Hardware Security Module as well as maintain a secure copy for disaster recovery scenarios.","source":"@site/aws-byok/Quickstart.md","sourceDirName":".","slug":"/Quickstart","permalink":"/aws-byok/Quickstart","draft":false,"unlisted":false,"tags":[],"version":"current","lastUpdatedAt":1741162132000,"sidebarPosition":1,"frontMatter":{"sidebar_position":1,"title":"Getting started with BYOK for AWS","sidebar_label":"Quickstart"},"sidebar":"tutorialSidebar","previous":{"title":"Overview","permalink":"/aws-byok/overview"},"next":{"title":"Installation","permalink":"/aws-byok/category/installation"}}');var a=r(74848),o=r(28453);r(65537),r(79329);const s={sidebar_position:1,title:"Getting started with BYOK for AWS",sidebar_label:"Quickstart"},i="Getting started with BYOK for AWS",l={},u=[{value:"HSM Setup and Configuration",id:"hsm-setup-and-configuration",level:2},{value:"Install and Configure Primus Tools",id:"install-and-configure-primus-tools",level:2},{value:"Create AWS KMS Key",id:"create-aws-kms-key",level:2},{value:"Download Public Key &amp; Import Token",id:"download-public-key--import-token",level:2},{value:"Create Key using Primus Tools",id:"create-key-using-primus-tools",level:2},{value:"Export &amp; Wrap Key Material",id:"export--wrap-key-material",level:2},{value:"Import Wrapped Key",id:"import-wrapped-key",level:2}];function c(e){const t={a:"a",admonition:"admonition",code:"code",h1:"h1",h2:"h2",header:"header",li:"li",p:"p",ul:"ul",...(0,o.R)(),...e.components};return(0,a.jsxs)(a.Fragment,{children:[(0,a.jsx)(t.header,{children:(0,a.jsx)(t.h1,{id:"getting-started-with-byok-for-aws",children:"Getting started with BYOK for AWS"})}),"\n",(0,a.jsx)(t.p,{children:"The process described in this document allows maintaining ownership and management over your KMS keys and to generate them using entropy in a safe environment within the Securosys Hardware Security Module as well as maintain a secure copy for disaster recovery scenarios."}),"\n",(0,a.jsxs)(t.p,{children:["This Quickstart section provides a comprehensive task listing of the Bring Your Own Key process for AWS Key management service (KMS). For more detailed instructions please consult the ",(0,a.jsx)(t.a,{href:"/aws-byok/Installation/Prerequisites",children:"Installation"})," section. Visit ",(0,a.jsx)(t.a,{href:"/aws-byok/Installation/Prerequisites",children:"Prerequisites"})," for the necessary preparations beforehand."]}),"\n",(0,a.jsx)(t.h2,{id:"hsm-setup-and-configuration",children:"HSM Setup and Configuration"}),"\n",(0,a.jsxs)(t.p,{children:["Setting-up the on-premises Primus HSM is not covered of this document. Please refer to Primus HSM User Manual, downloadable from the ",(0,a.jsx)(t.a,{href:"https://support.securosys.com/external/knowledge-base/article/63",children:"Securosys Support Portal"}),"."]}),"\n",(0,a.jsx)(t.admonition,{type:"note",children:(0,a.jsx)(t.p,{children:"The CloudHSM partition already comes preconfigured for AWS BYOK."})}),"\n",(0,a.jsx)(t.p,{children:"Primus HSM needs specific policy settings:"}),"\n",(0,a.jsxs)(t.ul,{children:["\n",(0,a.jsx)(t.li,{children:"JCE API enabled and appropriate license"}),"\n",(0,a.jsx)(t.li,{children:"Crypto policy (and User policy) configuration to allow Key Export for the used partition"}),"\n"]}),"\n",(0,a.jsx)(t.h2,{id:"install-and-configure-primus-tools",children:"Install and Configure Primus Tools"}),"\n",(0,a.jsxs)(t.p,{children:["Download the latest Primus Tools collection from the ",(0,a.jsx)(t.a,{href:"/primus-tools/downloads",children:"Primus Tools Download"})," section. For more details on Primus Tools see ",(0,a.jsx)(t.a,{href:"/primus-tools/overview",children:"Primus Tools"}),"."]}),"\n",(0,a.jsx)(t.admonition,{type:"note",children:(0,a.jsx)(t.p,{children:"HSM Provider Connection and Access must be prepared for the use of Primus Tools commands."})}),"\n",(0,a.jsx)(t.h2,{id:"create-aws-kms-key",children:"Create AWS KMS Key"}),"\n",(0,a.jsxs)(t.p,{children:["Begin the BYOK procedure by creating an AWS Key management service key with no key material associated. For more details see ",(0,a.jsx)(t.a,{href:"/aws-byok/Tutorials/Create-KMS-key",children:"Create AWS KMS Key"}),"."]}),"\n",(0,a.jsx)(t.h2,{id:"download-public-key--import-token",children:"Download Public Key & Import Token"}),"\n",(0,a.jsxs)(t.p,{children:["Download the public key and import token and specify the KMS key that will be associated with the imported key material. This KMS key must have an Origin value of ",(0,a.jsx)(t.code,{children:"EXTERNAL"}),"."]}),"\n",(0,a.jsxs)(t.p,{children:["For more information on the step by step procedure please see ",(0,a.jsx)(t.a,{href:"/aws-byok/Tutorials/Download-Pub-key",children:"Downloading Public Key & Import Token"}),"."]}),"\n",(0,a.jsx)(t.h2,{id:"create-key-using-primus-tools",children:"Create Key using Primus Tools"}),"\n",(0,a.jsxs)(t.p,{children:["Create a key on the Securosys CloudHSM or PrimusHSM which is going to be used in the BYOK procedure. Securosys Primus Tools offers the possibility to do so, but other tools can be used. ",(0,a.jsx)("br",{}),"\nIt is paramount to set the flag of the import key to ",(0,a.jsx)(t.code,{children:"extractable"}),". To import the key to AWS KMS the import key algorithm must be symmetric AES with a recommended size of 256 bits. If you already have a symmetric AES key with the necessary parameters previously created which you would like to use, please skip this step."]}),"\n",(0,a.jsxs)(t.p,{children:["For more information on the step by step procedure please see ",(0,a.jsx)(t.a,{href:"/aws-byok/Tutorials/Create-key-HSM",children:"Create Key on HSM"})," section."]}),"\n",(0,a.jsx)(t.h2,{id:"export--wrap-key-material",children:"Export & Wrap Key Material"}),"\n",(0,a.jsxs)(t.p,{children:["Use the Securosys Primus Tools tool set command ",(0,a.jsx)(t.code,{children:"AwsKmsByokExport"})," to wrap and export your specified key material on the HSM using the public key downloaded from AWS KMS.\nFor more information on the step by step procedure please see ",(0,a.jsx)(t.a,{href:"/aws-byok/Tutorials/Export&Wrap",children:"Export & Wrap Key Material"}),"."]}),"\n",(0,a.jsx)(t.h2,{id:"import-wrapped-key",children:"Import Wrapped Key"}),"\n",(0,a.jsxs)(t.p,{children:["Import the wrapped key material from the previous section into the AWS KMS.\nFor more information on the step by step procedure please see ",(0,a.jsx)(t.a,{href:"/aws-byok/Tutorials/Import-Key-Material",children:"Import Key Material"}),"."]}),"\n",(0,a.jsx)(t.p,{children:"After a successful import of the encrypted key material to the AWS KMS, it uses your corresponding account private key to decrypt the encrypted key material."})]})}function d(e={}){const{wrapper:t}={...(0,o.R)(),...e.components};return t?(0,a.jsx)(t,{...e,children:(0,a.jsx)(c,{...e})}):c(e)}},28453:(e,t,r)=>{r.d(t,{R:()=>s,x:()=>i});var n=r(96540);const a={},o=n.createContext(a);function s(e){const t=n.useContext(o);return n.useMemo((function(){return"function"==typeof e?e(t):{...t,...e}}),[t,e])}function i(e){let t;return t=e.disableParentContext?"function"==typeof e.components?e.components(a):e.components||a:s(e.components),n.createElement(o.Provider,{value:t},e.children)}},65537:(e,t,r)=>{r.d(t,{A:()=>x});var n=r(96540),a=r(34164),o=r(65627),s=r(56347),i=r(50372),l=r(30604),u=r(11861),c=r(78749);function d(e){return n.Children.toArray(e).filter((e=>"\n"!==e)).map((e=>{if(!e||(0,n.isValidElement)(e)&&function(e){const{props:t}=e;return!!t&&"object"==typeof t&&"value"in t}(e))return e;throw new Error(`Docusaurus error: Bad <Tabs> child <${"string"==typeof e.type?e.type:e.type.name}>: all children of the <Tabs> component should be <TabItem>, and every <TabItem> should have a unique "value" prop.`)}))?.filter(Boolean)??[]}function p(e){const{values:t,children:r}=e;return(0,n.useMemo)((()=>{const e=t??function(e){return d(e).map((e=>{let{props:{value:t,label:r,attributes:n,default:a}}=e;return{value:t,label:r,attributes:n,default:a}}))}(r);return function(e){const t=(0,u.XI)(e,((e,t)=>e.value===t.value));if(t.length>0)throw new Error(`Docusaurus error: Duplicate values "${t.map((e=>e.value)).join(", ")}" found in <Tabs>. Every value needs to be unique.`)}(e),e}),[t,r])}function h(e){let{value:t,tabValues:r}=e;return r.some((e=>e.value===t))}function m(e){let{queryString:t=!1,groupId:r}=e;const a=(0,s.W6)(),o=function(e){let{queryString:t=!1,groupId:r}=e;if("string"==typeof t)return t;if(!1===t)return null;if(!0===t&&!r)throw new Error('Docusaurus error: The <Tabs> component groupId prop is required if queryString=true, because this value is used as the search param name. You can also provide an explicit value such as queryString="my-search-param".');return r??null}({queryString:t,groupId:r});return[(0,l.aZ)(o),(0,n.useCallback)((e=>{if(!o)return;const t=new URLSearchParams(a.location.search);t.set(o,e),a.replace({...a.location,search:t.toString()})}),[o,a])]}function y(e){const{defaultValue:t,queryString:r=!1,groupId:a}=e,o=p(e),[s,l]=(0,n.useState)((()=>function(e){let{defaultValue:t,tabValues:r}=e;if(0===r.length)throw new Error("Docusaurus error: the <Tabs> component requires at least one <TabItem> children component");if(t){if(!h({value:t,tabValues:r}))throw new Error(`Docusaurus error: The <Tabs> has a defaultValue "${t}" but none of its children has the corresponding value. Available values are: ${r.map((e=>e.value)).join(", ")}. If you intend to show no default tab, use defaultValue={null} instead.`);return t}const n=r.find((e=>e.default))??r[0];if(!n)throw new Error("Unexpected error: 0 tabValues");return n.value}({defaultValue:t,tabValues:o}))),[u,d]=m({queryString:r,groupId:a}),[y,f]=function(e){let{groupId:t}=e;const r=function(e){return e?`docusaurus.tab.${e}`:null}(t),[a,o]=(0,c.Dv)(r);return[a,(0,n.useCallback)((e=>{r&&o.set(e)}),[r,o])]}({groupId:a}),b=(()=>{const e=u??y;return h({value:e,tabValues:o})?e:null})();(0,i.A)((()=>{b&&l(b)}),[b]);return{selectedValue:s,selectValue:(0,n.useCallback)((e=>{if(!h({value:e,tabValues:o}))throw new Error(`Can't select invalid tab value=${e}`);l(e),d(e),f(e)}),[d,f,o]),tabValues:o}}var f=r(9136);const b={tabList:"tabList__CuJ",tabItem:"tabItem_LNqP"};var v=r(74848);function w(e){let{className:t,block:r,selectedValue:n,selectValue:s,tabValues:i}=e;const l=[],{blockElementScrollPositionUntilNextRender:u}=(0,o.a_)(),c=e=>{const t=e.currentTarget,r=l.indexOf(t),a=i[r].value;a!==n&&(u(t),s(a))},d=e=>{let t=null;switch(e.key){case"Enter":c(e);break;case"ArrowRight":{const r=l.indexOf(e.currentTarget)+1;t=l[r]??l[0];break}case"ArrowLeft":{const r=l.indexOf(e.currentTarget)-1;t=l[r]??l[l.length-1];break}}t?.focus()};return(0,v.jsx)("ul",{role:"tablist","aria-orientation":"horizontal",className:(0,a.A)("tabs",{"tabs--block":r},t),children:i.map((e=>{let{value:t,label:r,attributes:o}=e;return(0,v.jsx)("li",{role:"tab",tabIndex:n===t?0:-1,"aria-selected":n===t,ref:e=>{l.push(e)},onKeyDown:d,onClick:c,...o,className:(0,a.A)("tabs__item",b.tabItem,o?.className,{"tabs__item--active":n===t}),children:r??t},t)}))})}function k(e){let{lazy:t,children:r,selectedValue:o}=e;const s=(Array.isArray(r)?r:[r]).filter(Boolean);if(t){const e=s.find((e=>e.props.value===o));return e?(0,n.cloneElement)(e,{className:(0,a.A)("margin-top--md",e.props.className)}):null}return(0,v.jsx)("div",{className:"margin-top--md",children:s.map(((e,t)=>(0,n.cloneElement)(e,{key:t,hidden:e.props.value!==o})))})}function g(e){const t=y(e);return(0,v.jsxs)("div",{className:(0,a.A)("tabs-container",b.tabList),children:[(0,v.jsx)(w,{...t,...e}),(0,v.jsx)(k,{...t,...e})]})}function x(e){const t=(0,f.A)();return(0,v.jsx)(g,{...e,children:d(e.children)},String(t))}},79329:(e,t,r)=>{r.d(t,{A:()=>s});r(96540);var n=r(34164);const a={tabItem:"tabItem_Ymn6"};var o=r(74848);function s(e){let{children:t,hidden:r,className:s}=e;return(0,o.jsx)("div",{role:"tabpanel",className:(0,n.A)(a.tabItem,s),hidden:r,children:t})}}}]);