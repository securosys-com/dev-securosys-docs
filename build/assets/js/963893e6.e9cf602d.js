"use strict";(self.webpackChunksecurosys_docs=self.webpackChunksecurosys_docs||[]).push([[74150],{19902:(e,t,r)=>{r.r(t),r.d(t,{assets:()=>l,contentTitle:()=>o,default:()=>d,frontMatter:()=>a,metadata:()=>n,toc:()=>u});const n=JSON.parse('{"id":"Quickstart","title":"Getting Started with Microsoft S/MIME & Security HSMs","description":"Getting Started with Microsoft S/MIME with Securosys Hardware Security Modules (HSMs)","source":"@site/ms-smime/Quickstart.md","sourceDirName":".","slug":"/Quickstart","permalink":"/ms-smime/Quickstart","draft":false,"unlisted":false,"tags":[],"version":"current","lastUpdatedAt":1741162132000,"sidebarPosition":1,"frontMatter":{"sidebar_position":1,"title":"Getting Started with Microsoft S/MIME & Security HSMs","sidebar_label":"Quickstart","description":"Getting Started with Microsoft S/MIME with Securosys Hardware Security Modules (HSMs)","keywords":["hsm","cloud hsm"]},"sidebar":"tutorialSidebar","previous":{"title":"Overview","permalink":"/ms-smime/overview"},"next":{"title":"Installation","permalink":"/ms-smime/category/installation"}}');var i=r(74848),s=r(28453);r(65537),r(79329);const a={sidebar_position:1,title:"Getting Started with Microsoft S/MIME & Security HSMs",sidebar_label:"Quickstart",description:"Getting Started with Microsoft S/MIME with Securosys Hardware Security Modules (HSMs)",keywords:["hsm","cloud hsm"]},o="Getting Started with Microsoft S/MIME",l={},u=[{value:"HSM Integration guide",id:"hsm-integration-guide",level:3},{value:"1. Install the Primus CNG/KSP Provider",id:"1-install-the-primus-cngksp-provider",level:2},{value:"2. Preparations and Obtaining a Certificate",id:"2-preparations-and-obtaining-a-certificate",level:2},{value:"3. Configure your Outlook Client",id:"3-configure-your-outlook-client",level:2}];function c(e){const t={a:"a",admonition:"admonition",h1:"h1",h2:"h2",h3:"h3",header:"header",li:"li",p:"p",strong:"strong",ul:"ul",...(0,s.R)(),...e.components};return(0,i.jsxs)(i.Fragment,{children:[(0,i.jsx)(t.header,{children:(0,i.jsx)(t.h1,{id:"getting-started-with-microsoft-smime",children:"Getting Started with Microsoft S/MIME"})}),"\n",(0,i.jsx)(t.h3,{id:"hsm-integration-guide",children:"HSM Integration guide"}),"\n",(0,i.jsxs)(t.p,{children:["This section provides a comprehensive guide outlining the 3 major steps necessary to integrate ",(0,i.jsx)(t.strong,{children:"Microsoft S/MIME"})," with Securosys ",(0,i.jsx)(t.a,{href:"/cloudhsm/overview/",children:"CloudHSM"})," or on-premises Primus HSM."]}),"\n",(0,i.jsx)(t.h2,{id:"1-install-the-primus-cngksp-provider",children:"1. Install the Primus CNG/KSP Provider"}),"\n",(0,i.jsxs)(t.p,{children:["Refer to the ",(0,i.jsx)(t.a,{href:"../../mscng/overview",children:"Primus MS CNG Provider documentation"})," on how to download, install and configure the Primus CNG/KSP Provider."]}),"\n",(0,i.jsx)(t.admonition,{type:"note",children:(0,i.jsx)(t.p,{children:"Ensure the CNG API is licensed and activated on your HSM device."})}),"\n",(0,i.jsx)(t.h2,{id:"2-preparations-and-obtaining-a-certificate",children:"2. Preparations and Obtaining a Certificate"}),"\n",(0,i.jsxs)(t.ul,{children:["\n",(0,i.jsxs)(t.li,{children:["Ensure your ",(0,i.jsx)(t.a,{href:"/ms-smime/Installation/prerequisites",children:"prerequisites"})," are in order."]}),"\n",(0,i.jsxs)(t.li,{children:["Make sure to have valid certificates issued. Follow the ",(0,i.jsx)(t.a,{href:"/ms-smime/Installation/digital-certificate",children:"Get your Digital Certificate"})," on how to do so."]}),"\n"]}),"\n",(0,i.jsx)(t.h2,{id:"3-configure-your-outlook-client",children:"3. Configure your Outlook Client"}),"\n",(0,i.jsx)(t.p,{children:"After acquiring the Certificates and installing the Securosys CNG Provider it's possible to configure the Outlook Client to use these certificates."}),"\n",(0,i.jsxs)(t.ul,{children:["\n",(0,i.jsx)(t.li,{children:"Configure Outlook Client to use your certificates for signing and encryption"}),"\n",(0,i.jsx)(t.li,{children:"Exchange Public keys if necessary."}),"\n"]}),"\n",(0,i.jsxs)(t.p,{children:["For more information follow the tutorial to ",(0,i.jsx)(t.a,{href:"/ms-smime/Installation/using-certificates",children:"Configure the Outlook Client"}),"."]})]})}function d(e={}){const{wrapper:t}={...(0,s.R)(),...e.components};return t?(0,i.jsx)(t,{...e,children:(0,i.jsx)(c,{...e})}):c(e)}},28453:(e,t,r)=>{r.d(t,{R:()=>a,x:()=>o});var n=r(96540);const i={},s=n.createContext(i);function a(e){const t=n.useContext(s);return n.useMemo((function(){return"function"==typeof e?e(t):{...t,...e}}),[t,e])}function o(e){let t;return t=e.disableParentContext?"function"==typeof e.components?e.components(i):e.components||i:a(e.components),n.createElement(s.Provider,{value:t},e.children)}},65537:(e,t,r)=>{r.d(t,{A:()=>x});var n=r(96540),i=r(34164),s=r(65627),a=r(56347),o=r(50372),l=r(30604),u=r(11861),c=r(78749);function d(e){return n.Children.toArray(e).filter((e=>"\n"!==e)).map((e=>{if(!e||(0,n.isValidElement)(e)&&function(e){const{props:t}=e;return!!t&&"object"==typeof t&&"value"in t}(e))return e;throw new Error(`Docusaurus error: Bad <Tabs> child <${"string"==typeof e.type?e.type:e.type.name}>: all children of the <Tabs> component should be <TabItem>, and every <TabItem> should have a unique "value" prop.`)}))?.filter(Boolean)??[]}function h(e){const{values:t,children:r}=e;return(0,n.useMemo)((()=>{const e=t??function(e){return d(e).map((e=>{let{props:{value:t,label:r,attributes:n,default:i}}=e;return{value:t,label:r,attributes:n,default:i}}))}(r);return function(e){const t=(0,u.XI)(e,((e,t)=>e.value===t.value));if(t.length>0)throw new Error(`Docusaurus error: Duplicate values "${t.map((e=>e.value)).join(", ")}" found in <Tabs>. Every value needs to be unique.`)}(e),e}),[t,r])}function f(e){let{value:t,tabValues:r}=e;return r.some((e=>e.value===t))}function m(e){let{queryString:t=!1,groupId:r}=e;const i=(0,a.W6)(),s=function(e){let{queryString:t=!1,groupId:r}=e;if("string"==typeof t)return t;if(!1===t)return null;if(!0===t&&!r)throw new Error('Docusaurus error: The <Tabs> component groupId prop is required if queryString=true, because this value is used as the search param name. You can also provide an explicit value such as queryString="my-search-param".');return r??null}({queryString:t,groupId:r});return[(0,l.aZ)(s),(0,n.useCallback)((e=>{if(!s)return;const t=new URLSearchParams(i.location.search);t.set(s,e),i.replace({...i.location,search:t.toString()})}),[s,i])]}function p(e){const{defaultValue:t,queryString:r=!1,groupId:i}=e,s=h(e),[a,l]=(0,n.useState)((()=>function(e){let{defaultValue:t,tabValues:r}=e;if(0===r.length)throw new Error("Docusaurus error: the <Tabs> component requires at least one <TabItem> children component");if(t){if(!f({value:t,tabValues:r}))throw new Error(`Docusaurus error: The <Tabs> has a defaultValue "${t}" but none of its children has the corresponding value. Available values are: ${r.map((e=>e.value)).join(", ")}. If you intend to show no default tab, use defaultValue={null} instead.`);return t}const n=r.find((e=>e.default))??r[0];if(!n)throw new Error("Unexpected error: 0 tabValues");return n.value}({defaultValue:t,tabValues:s}))),[u,d]=m({queryString:r,groupId:i}),[p,g]=function(e){let{groupId:t}=e;const r=function(e){return e?`docusaurus.tab.${e}`:null}(t),[i,s]=(0,c.Dv)(r);return[i,(0,n.useCallback)((e=>{r&&s.set(e)}),[r,s])]}({groupId:i}),b=(()=>{const e=u??p;return f({value:e,tabValues:s})?e:null})();(0,o.A)((()=>{b&&l(b)}),[b]);return{selectedValue:a,selectValue:(0,n.useCallback)((e=>{if(!f({value:e,tabValues:s}))throw new Error(`Can't select invalid tab value=${e}`);l(e),d(e),g(e)}),[d,g,s]),tabValues:s}}var g=r(9136);const b={tabList:"tabList__CuJ",tabItem:"tabItem_LNqP"};var v=r(74848);function S(e){let{className:t,block:r,selectedValue:n,selectValue:a,tabValues:o}=e;const l=[],{blockElementScrollPositionUntilNextRender:u}=(0,s.a_)(),c=e=>{const t=e.currentTarget,r=l.indexOf(t),i=o[r].value;i!==n&&(u(t),a(i))},d=e=>{let t=null;switch(e.key){case"Enter":c(e);break;case"ArrowRight":{const r=l.indexOf(e.currentTarget)+1;t=l[r]??l[0];break}case"ArrowLeft":{const r=l.indexOf(e.currentTarget)-1;t=l[r]??l[l.length-1];break}}t?.focus()};return(0,v.jsx)("ul",{role:"tablist","aria-orientation":"horizontal",className:(0,i.A)("tabs",{"tabs--block":r},t),children:o.map((e=>{let{value:t,label:r,attributes:s}=e;return(0,v.jsx)("li",{role:"tab",tabIndex:n===t?0:-1,"aria-selected":n===t,ref:e=>{l.push(e)},onKeyDown:d,onClick:c,...s,className:(0,i.A)("tabs__item",b.tabItem,s?.className,{"tabs__item--active":n===t}),children:r??t},t)}))})}function y(e){let{lazy:t,children:r,selectedValue:s}=e;const a=(Array.isArray(r)?r:[r]).filter(Boolean);if(t){const e=a.find((e=>e.props.value===s));return e?(0,n.cloneElement)(e,{className:(0,i.A)("margin-top--md",e.props.className)}):null}return(0,v.jsx)("div",{className:"margin-top--md",children:a.map(((e,t)=>(0,n.cloneElement)(e,{key:t,hidden:e.props.value!==s})))})}function w(e){const t=p(e);return(0,v.jsxs)("div",{className:(0,i.A)("tabs-container",b.tabList),children:[(0,v.jsx)(S,{...t,...e}),(0,v.jsx)(y,{...t,...e})]})}function x(e){const t=(0,g.A)();return(0,v.jsx)(w,{...e,children:d(e.children)},String(t))}},79329:(e,t,r)=>{r.d(t,{A:()=>a});r(96540);var n=r(34164);const i={tabItem:"tabItem_Ymn6"};var s=r(74848);function a(e){let{children:t,hidden:r,className:a}=e;return(0,s.jsx)("div",{role:"tabpanel",className:(0,n.A)(i.tabItem,a),hidden:r,children:t})}}}]);