"use strict";(self.webpackChunksecurosys_docs=self.webpackChunksecurosys_docs||[]).push([[23595],{28453:(e,r,t)=>{t.d(r,{R:()=>a,x:()=>n});var s=t(96540);const o={},i=s.createContext(o);function a(e){const r=s.useContext(i);return s.useMemo((function(){return"function"==typeof e?e(r):{...r,...e}}),[r,e])}function n(e){let r;return r=e.disableParentContext?"function"==typeof e.components?e.components(o):e.components||o:a(e.components),s.createElement(i.Provider,{value:r},e.children)}},59437:(e,r,t)=>{t.r(r),t.d(r,{assets:()=>l,contentTitle:()=>n,default:()=>d,frontMatter:()=>a,metadata:()=>s,toc:()=>u});const s=JSON.parse('{"id":"overview","title":"Primus Tools HSM Integration Guide","description":"What is Securosys Primus Tools for Hardware Security Modules (HSMs)?","source":"@site/primus-tools/overview.md","sourceDirName":".","slug":"/overview","permalink":"/primus-tools/overview","draft":false,"unlisted":false,"tags":[],"version":"current","lastUpdatedAt":1741162132000,"sidebarPosition":0,"frontMatter":{"sidebar_position":0,"title":"Primus Tools HSM Integration Guide","sidebar_label":"Overview","description":"What is Securosys Primus Tools for Hardware Security Modules (HSMs)?","keywords":["hsm","cloud hsm"],"grid_title":"Securosys Primus Tools","grid_search_tags":["KMS","JCE/JCA","Software Signing"],"grid_description":"Java-based toolkit for managing cryptographic objects with Securosys HSMs, simplifying key management, certificate procedures, and more. It supports key generation, modification, migration, and CSR generation, with tools like KeytoolX and JarsignerX for easy","grid_categories":["PrimusTools","Key Management System"],"grid_tile_logoUrl":"/img/securosys_logo.png"},"sidebar":"tutorialSidebar","next":{"title":"Quickstart","permalink":"/primus-tools/QuickStart"}}');var o=t(74848),i=t(28453);t(65537),t(79329);const a={sidebar_position:0,title:"Primus Tools HSM Integration Guide",sidebar_label:"Overview",description:"What is Securosys Primus Tools for Hardware Security Modules (HSMs)?",keywords:["hsm","cloud hsm"],grid_title:"Securosys Primus Tools",grid_search_tags:["KMS","JCE/JCA","Software Signing"],grid_description:"Java-based toolkit for managing cryptographic objects with Securosys HSMs, simplifying key management, certificate procedures, and more. It supports key generation, modification, migration, and CSR generation, with tools like KeytoolX and JarsignerX for easy",grid_categories:["PrimusTools","Key Management System"],grid_tile_logoUrl:"/img/securosys_logo.png"},n="Securosys Primus Tools",l={},u=[{value:"Target Audience",id:"target-audience",level:2},{value:"Support Contact",id:"support-contact",level:2},{value:"Get started with Primus Tools",id:"get-started-with-primus-tools",level:2}];function c(e){const r={a:"a",code:"code",h1:"h1",h2:"h2",header:"header",li:"li",p:"p",strong:"strong",ul:"ul",...(0,i.R)(),...e.components};return(0,o.jsxs)(o.Fragment,{children:[(0,o.jsx)(r.header,{children:(0,o.jsx)(r.h1,{id:"securosys-primus-tools",children:"Securosys Primus Tools"})}),"\n",(0,o.jsx)(r.p,{children:"There are many platform-specific tools to manage HSM key stores and certificate procedures (e.g. OpenSC/pkcs11-tool, p11tool, Java keytool, Windows certutil, etc.)."}),"\n",(0,o.jsxs)(r.p,{children:[(0,o.jsx)(r.strong,{children:"Primus Tools"})," serves as a versatile Java-based toolkit, designed to seamlessly integrate with your on-premises Primus HSM cluster or CloudHSM. With Primus Tools, complexity is streamlined, ensuring swift and efficient management of cryptographic objects while offering a robust foundation for secure operations."]}),"\n",(0,o.jsxs)(r.p,{children:["With ongoing updates and maintenance, Primus Tools ensures optimal compatibility with HSMs, directly addressing the needs of security-conscious enterprises. Setting up the tool is a breeze - a simple process of downloading the ",(0,o.jsx)(r.code,{children:".zip"})," file, extracting it, and it's ready for immediate use."]}),"\n",(0,o.jsx)(r.p,{children:"The tool's functionality encompasses a broad spectrum of object management commands, allowing for key tasks such as generation, listing, deletion, modification, wrapping, and more. Moreover, it facilitates more intricate operations like key migration, certificate procedures such as certificate signing request generation, and the creation of self-signed certificates."}),"\n",(0,o.jsx)(r.p,{children:"Included within Primus Tools are utilities like KeytoolX and JarsignerX, utilities adapted from Sun Oracle's Keytool and Jarsigner, tailored to interact seamlessly with either on-premises Primus HSM or CloudHSM."}),"\n",(0,o.jsxs)(r.p,{children:["This document describes the Primus Tools commands, their parameters and the use cases for Hardware Security Modules (HSMs), both Primus HSM and ",(0,o.jsx)(r.a,{href:"/cloudhsm/overview/",children:"CloudHSM services"}),"."]}),"\n",(0,o.jsx)(r.h2,{id:"target-audience",children:"Target Audience"}),"\n",(0,o.jsxs)(r.p,{children:["This document is intended for Securosys Primus HSM or ",(0,o.jsx)(r.a,{href:"/cloudhsm/overview/",children:"CloudHSM"})," administrators and IT professionals familiar with cryptographic object and certificate management."]}),"\n",(0,o.jsx)(r.p,{children:"The snstallation of Primus Tools and JCE provider requires that you are already familiar with java."}),"\n",(0,o.jsx)(r.p,{children:"For on-premises HSM deployed operation administrative skills are required for Securosys Primus HSMs."}),"\n",(0,o.jsx)(r.h2,{id:"support-contact",children:"Support Contact"}),"\n",(0,o.jsxs)(r.p,{children:["If you encounter a problem while installing/configuring the provider or\nintegrating the HSM with Primus Tools, make sure that you have read the\nreferenced documentation. If you cannot resolve the issue, please\ncontact Securosys Customer Support. For specific requests regarding\nPrimus Tools, the Securosys\nSupport Portal is reachable under ",(0,o.jsx)(r.a,{href:"https://support.securosys.com",children:"https://support.securosys.com"}),"."]}),"\n",(0,o.jsx)(r.h2,{id:"get-started-with-primus-tools",children:"Get started with Primus Tools"}),"\n",(0,o.jsx)(r.p,{children:"For a smooth start using Primus Tools:"}),"\n",(0,o.jsxs)(r.ul,{children:["\n",(0,o.jsxs)(r.li,{children:["Consult the ",(0,o.jsx)(r.a,{href:"/primus-tools/QuickStart",children:"Quickstart guide"})," for a comprehensive task listing."]}),"\n",(0,o.jsxs)(r.li,{children:["For detailed instructions, read and follow the ",(0,o.jsx)(r.a,{href:"/primus-tools/Installation/Prerequisites",children:"Installation"})," chapter."]}),"\n",(0,o.jsxs)(r.li,{children:["For detailed command usage and explanation view the ",(0,o.jsx)(r.a,{href:"/primus-tools/Tutorials/command-overview",children:"Tutorials"})," chapter."]}),"\n",(0,o.jsxs)(r.li,{children:["See examples of ",(0,o.jsx)(r.a,{href:"/primus-tools/Use-Cases/Azure-byok",children:"use cases"}),"."]}),"\n"]})]})}function d(e={}){const{wrapper:r}={...(0,i.R)(),...e.components};return r?(0,o.jsx)(r,{...e,children:(0,o.jsx)(c,{...e})}):c(e)}},65537:(e,r,t)=>{t.d(r,{A:()=>x});var s=t(96540),o=t(34164),i=t(65627),a=t(56347),n=t(50372),l=t(30604),u=t(11861),c=t(78749);function d(e){return s.Children.toArray(e).filter((e=>"\n"!==e)).map((e=>{if(!e||(0,s.isValidElement)(e)&&function(e){const{props:r}=e;return!!r&&"object"==typeof r&&"value"in r}(e))return e;throw new Error(`Docusaurus error: Bad <Tabs> child <${"string"==typeof e.type?e.type:e.type.name}>: all children of the <Tabs> component should be <TabItem>, and every <TabItem> should have a unique "value" prop.`)}))?.filter(Boolean)??[]}function m(e){const{values:r,children:t}=e;return(0,s.useMemo)((()=>{const e=r??function(e){return d(e).map((e=>{let{props:{value:r,label:t,attributes:s,default:o}}=e;return{value:r,label:t,attributes:s,default:o}}))}(t);return function(e){const r=(0,u.XI)(e,((e,r)=>e.value===r.value));if(r.length>0)throw new Error(`Docusaurus error: Duplicate values "${r.map((e=>e.value)).join(", ")}" found in <Tabs>. Every value needs to be unique.`)}(e),e}),[r,t])}function h(e){let{value:r,tabValues:t}=e;return t.some((e=>e.value===r))}function p(e){let{queryString:r=!1,groupId:t}=e;const o=(0,a.W6)(),i=function(e){let{queryString:r=!1,groupId:t}=e;if("string"==typeof r)return r;if(!1===r)return null;if(!0===r&&!t)throw new Error('Docusaurus error: The <Tabs> component groupId prop is required if queryString=true, because this value is used as the search param name. You can also provide an explicit value such as queryString="my-search-param".');return t??null}({queryString:r,groupId:t});return[(0,l.aZ)(i),(0,s.useCallback)((e=>{if(!i)return;const r=new URLSearchParams(o.location.search);r.set(i,e),o.replace({...o.location,search:r.toString()})}),[i,o])]}function f(e){const{defaultValue:r,queryString:t=!1,groupId:o}=e,i=m(e),[a,l]=(0,s.useState)((()=>function(e){let{defaultValue:r,tabValues:t}=e;if(0===t.length)throw new Error("Docusaurus error: the <Tabs> component requires at least one <TabItem> children component");if(r){if(!h({value:r,tabValues:t}))throw new Error(`Docusaurus error: The <Tabs> has a defaultValue "${r}" but none of its children has the corresponding value. Available values are: ${t.map((e=>e.value)).join(", ")}. If you intend to show no default tab, use defaultValue={null} instead.`);return r}const s=t.find((e=>e.default))??t[0];if(!s)throw new Error("Unexpected error: 0 tabValues");return s.value}({defaultValue:r,tabValues:i}))),[u,d]=p({queryString:t,groupId:o}),[f,g]=function(e){let{groupId:r}=e;const t=function(e){return e?`docusaurus.tab.${e}`:null}(r),[o,i]=(0,c.Dv)(t);return[o,(0,s.useCallback)((e=>{t&&i.set(e)}),[t,i])]}({groupId:o}),y=(()=>{const e=u??f;return h({value:e,tabValues:i})?e:null})();(0,n.A)((()=>{y&&l(y)}),[y]);return{selectedValue:a,selectValue:(0,s.useCallback)((e=>{if(!h({value:e,tabValues:i}))throw new Error(`Can't select invalid tab value=${e}`);l(e),d(e),g(e)}),[d,g,i]),tabValues:i}}var g=t(9136);const y={tabList:"tabList__CuJ",tabItem:"tabItem_LNqP"};var b=t(74848);function v(e){let{className:r,block:t,selectedValue:s,selectValue:a,tabValues:n}=e;const l=[],{blockElementScrollPositionUntilNextRender:u}=(0,i.a_)(),c=e=>{const r=e.currentTarget,t=l.indexOf(r),o=n[t].value;o!==s&&(u(r),a(o))},d=e=>{let r=null;switch(e.key){case"Enter":c(e);break;case"ArrowRight":{const t=l.indexOf(e.currentTarget)+1;r=l[t]??l[0];break}case"ArrowLeft":{const t=l.indexOf(e.currentTarget)-1;r=l[t]??l[l.length-1];break}}r?.focus()};return(0,b.jsx)("ul",{role:"tablist","aria-orientation":"horizontal",className:(0,o.A)("tabs",{"tabs--block":t},r),children:n.map((e=>{let{value:r,label:t,attributes:i}=e;return(0,b.jsx)("li",{role:"tab",tabIndex:s===r?0:-1,"aria-selected":s===r,ref:e=>{l.push(e)},onKeyDown:d,onClick:c,...i,className:(0,o.A)("tabs__item",y.tabItem,i?.className,{"tabs__item--active":s===r}),children:t??r},r)}))})}function S(e){let{lazy:r,children:t,selectedValue:i}=e;const a=(Array.isArray(t)?t:[t]).filter(Boolean);if(r){const e=a.find((e=>e.props.value===i));return e?(0,s.cloneElement)(e,{className:(0,o.A)("margin-top--md",e.props.className)}):null}return(0,b.jsx)("div",{className:"margin-top--md",children:a.map(((e,r)=>(0,s.cloneElement)(e,{key:r,hidden:e.props.value!==i})))})}function w(e){const r=f(e);return(0,b.jsxs)("div",{className:(0,o.A)("tabs-container",y.tabList),children:[(0,b.jsx)(v,{...r,...e}),(0,b.jsx)(S,{...r,...e})]})}function x(e){const r=(0,g.A)();return(0,b.jsx)(w,{...e,children:d(e.children)},String(r))}},79329:(e,r,t)=>{t.d(r,{A:()=>a});t(96540);var s=t(34164);const o={tabItem:"tabItem_Ymn6"};var i=t(74848);function a(e){let{children:r,hidden:t,className:a}=e;return(0,i.jsx)("div",{role:"tabpanel",className:(0,s.A)(o.tabItem,a),hidden:t,children:r})}}}]);