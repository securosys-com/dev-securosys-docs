"use strict";(self.webpackChunksecurosys_docs=self.webpackChunksecurosys_docs||[]).push([[84457],{28453:(e,t,n)=>{n.d(t,{R:()=>a,x:()=>o});var r=n(96540);const i={},s=r.createContext(i);function a(e){const t=r.useContext(s);return r.useMemo((function(){return"function"==typeof e?e(t):{...t,...e}}),[t,e])}function o(e){let t;return t=e.disableParentContext?"function"==typeof e.components?e.components(i):e.components||i:a(e.components),r.createElement(s.Provider,{value:t},e.children)}},62092:(e,t,n)=>{n.r(t),n.d(t,{assets:()=>l,contentTitle:()=>o,default:()=>d,frontMatter:()=>a,metadata:()=>r,toc:()=>c});const r=JSON.parse('{"id":"Quickstart","title":"Getting Started with Microsoft SignTool & Security HSMs","description":"Getting Started with Microsoft SignTool with Securosys Hardware Security Modules (HSMs)","source":"@site/ms-signtool/Quickstart.md","sourceDirName":".","slug":"/Quickstart","permalink":"/ms-signtool/Quickstart","draft":false,"unlisted":false,"tags":[],"version":"current","lastUpdatedAt":1741162132000,"sidebarPosition":1,"frontMatter":{"sidebar_position":1,"title":"Getting Started with Microsoft SignTool & Security HSMs","sidebar_label":"Quickstart","description":"Getting Started with Microsoft SignTool with Securosys Hardware Security Modules (HSMs)","keywords":["hsm","cloud hsm"]},"sidebar":"tutorialSidebar","previous":{"title":"Overview","permalink":"/ms-signtool/overview"},"next":{"title":"Installation","permalink":"/ms-signtool/category/installation"}}');var i=n(74848),s=n(28453);n(65537),n(79329);const a={sidebar_position:1,title:"Getting Started with Microsoft SignTool & Security HSMs",sidebar_label:"Quickstart",description:"Getting Started with Microsoft SignTool with Securosys Hardware Security Modules (HSMs)",keywords:["hsm","cloud hsm"]},o="Getting Started with Microsoft SignTool",l={},c=[{value:"1. Install the Primus CNG/KSP Provider",id:"1-install-the-primus-cngksp-provider",level:2},{value:"2. Install new Signing key &amp; Certificate",id:"2-install-new-signing-key--certificate",level:2},{value:"3. Start Signing Files",id:"3-start-signing-files",level:2}];function u(e){const t={a:"a",admonition:"admonition",code:"code",h1:"h1",h2:"h2",header:"header",input:"input",li:"li",p:"p",strong:"strong",ul:"ul",...(0,s.R)(),...e.components};return(0,i.jsxs)(i.Fragment,{children:[(0,i.jsx)(t.header,{children:(0,i.jsx)(t.h1,{id:"getting-started-with-microsoft-signtool",children:"Getting Started with Microsoft SignTool"})}),"\n",(0,i.jsxs)(t.p,{children:["This section provides a comprehensive guide outlining the 3 steps necessary to integrate ",(0,i.jsx)(t.strong,{children:"Microsoft SignTool"})," with Securosys ",(0,i.jsx)(t.a,{href:"/cloudhsm/overview/",children:"CloudHSM"})," or on-premises Primus HSM."]}),"\n",(0,i.jsx)(t.h2,{id:"1-install-the-primus-cngksp-provider",children:"1. Install the Primus CNG/KSP Provider"}),"\n",(0,i.jsxs)(t.p,{children:["Refer to the ",(0,i.jsx)(t.a,{href:"../../mscng/overview",children:"Primus MS CNG Provider documentation"})," on how to download, install and configure the Primus CNG/KSP Provider."]}),"\n",(0,i.jsx)(t.admonition,{type:"note",children:(0,i.jsx)(t.p,{children:"Ensure the CNG API is licensed and activated on your HSM device."})}),"\n",(0,i.jsx)(t.h2,{id:"2-install-new-signing-key--certificate",children:"2. Install new Signing key & Certificate"}),"\n",(0,i.jsxs)(t.ul,{className:"contains-task-list",children:["\n",(0,i.jsxs)(t.li,{className:"task-list-item",children:[(0,i.jsx)(t.input,{type:"checkbox",checked:!0,disabled:!0})," ","Check the ",(0,i.jsx)(t.a,{href:"/ms-signtool/Installation/Requirements",children:"prerequisites"})]}),"\n",(0,i.jsxs)(t.li,{className:"task-list-item",children:[(0,i.jsx)(t.input,{type:"checkbox",checked:!0,disabled:!0})," ","Complete the ",(0,i.jsx)(t.a,{href:"/ms-signtool/Installation/",children:"installation"}),".","\n",(0,i.jsxs)(t.ul,{children:["\n",(0,i.jsxs)(t.li,{children:["Prepare the signing key request sample ",(0,i.jsx)(t.code,{children:"request.inf"})," file,"]}),"\n",(0,i.jsx)(t.li,{children:"generate the signing key and self-signed certificate with the previously created request file,"}),"\n",(0,i.jsx)(t.li,{children:"generate the signing key and public signed certificate with the request file,"}),"\n",(0,i.jsx)(t.li,{children:"validate your signing certificate."}),"\n"]}),"\n"]}),"\n"]}),"\n",(0,i.jsx)(t.h2,{id:"3-start-signing-files",children:"3. Start Signing Files"}),"\n",(0,i.jsxs)(t.p,{children:["Now you are ready to sign your files or timestamp codes, certificates, etc. using the Microsoft ",(0,i.jsx)(t.code,{children:"signtool.exe"}),"."]}),"\n",(0,i.jsxs)(t.p,{children:["Follow the tutorial to ",(0,i.jsx)(t.a,{href:"/ms-signtool/Tutorials/Signing-files",children:"sign files with Microsoft SignTool"})," and:"]}),"\n",(0,i.jsxs)(t.ul,{children:["\n",(0,i.jsx)(t.li,{children:"Sign an example application,"}),"\n",(0,i.jsx)(t.li,{children:"Verify the signed application (file) either via CLI or GUI."}),"\n"]})]})}function d(e={}){const{wrapper:t}={...(0,s.R)(),...e.components};return t?(0,i.jsx)(t,{...e,children:(0,i.jsx)(u,{...e})}):u(e)}},65537:(e,t,n)=>{n.d(t,{A:()=>w});var r=n(96540),i=n(34164),s=n(65627),a=n(56347),o=n(50372),l=n(30604),c=n(11861),u=n(78749);function d(e){return r.Children.toArray(e).filter((e=>"\n"!==e)).map((e=>{if(!e||(0,r.isValidElement)(e)&&function(e){const{props:t}=e;return!!t&&"object"==typeof t&&"value"in t}(e))return e;throw new Error(`Docusaurus error: Bad <Tabs> child <${"string"==typeof e.type?e.type:e.type.name}>: all children of the <Tabs> component should be <TabItem>, and every <TabItem> should have a unique "value" prop.`)}))?.filter(Boolean)??[]}function h(e){const{values:t,children:n}=e;return(0,r.useMemo)((()=>{const e=t??function(e){return d(e).map((e=>{let{props:{value:t,label:n,attributes:r,default:i}}=e;return{value:t,label:n,attributes:r,default:i}}))}(n);return function(e){const t=(0,c.XI)(e,((e,t)=>e.value===t.value));if(t.length>0)throw new Error(`Docusaurus error: Duplicate values "${t.map((e=>e.value)).join(", ")}" found in <Tabs>. Every value needs to be unique.`)}(e),e}),[t,n])}function f(e){let{value:t,tabValues:n}=e;return n.some((e=>e.value===t))}function p(e){let{queryString:t=!1,groupId:n}=e;const i=(0,a.W6)(),s=function(e){let{queryString:t=!1,groupId:n}=e;if("string"==typeof t)return t;if(!1===t)return null;if(!0===t&&!n)throw new Error('Docusaurus error: The <Tabs> component groupId prop is required if queryString=true, because this value is used as the search param name. You can also provide an explicit value such as queryString="my-search-param".');return n??null}({queryString:t,groupId:n});return[(0,l.aZ)(s),(0,r.useCallback)((e=>{if(!s)return;const t=new URLSearchParams(i.location.search);t.set(s,e),i.replace({...i.location,search:t.toString()})}),[s,i])]}function g(e){const{defaultValue:t,queryString:n=!1,groupId:i}=e,s=h(e),[a,l]=(0,r.useState)((()=>function(e){let{defaultValue:t,tabValues:n}=e;if(0===n.length)throw new Error("Docusaurus error: the <Tabs> component requires at least one <TabItem> children component");if(t){if(!f({value:t,tabValues:n}))throw new Error(`Docusaurus error: The <Tabs> has a defaultValue "${t}" but none of its children has the corresponding value. Available values are: ${n.map((e=>e.value)).join(", ")}. If you intend to show no default tab, use defaultValue={null} instead.`);return t}const r=n.find((e=>e.default))??n[0];if(!r)throw new Error("Unexpected error: 0 tabValues");return r.value}({defaultValue:t,tabValues:s}))),[c,d]=p({queryString:n,groupId:i}),[g,m]=function(e){let{groupId:t}=e;const n=function(e){return e?`docusaurus.tab.${e}`:null}(t),[i,s]=(0,u.Dv)(n);return[i,(0,r.useCallback)((e=>{n&&s.set(e)}),[n,s])]}({groupId:i}),b=(()=>{const e=c??g;return f({value:e,tabValues:s})?e:null})();(0,o.A)((()=>{b&&l(b)}),[b]);return{selectedValue:a,selectValue:(0,r.useCallback)((e=>{if(!f({value:e,tabValues:s}))throw new Error(`Can't select invalid tab value=${e}`);l(e),d(e),m(e)}),[d,m,s]),tabValues:s}}var m=n(9136);const b={tabList:"tabList__CuJ",tabItem:"tabItem_LNqP"};var v=n(74848);function S(e){let{className:t,block:n,selectedValue:r,selectValue:a,tabValues:o}=e;const l=[],{blockElementScrollPositionUntilNextRender:c}=(0,s.a_)(),u=e=>{const t=e.currentTarget,n=l.indexOf(t),i=o[n].value;i!==r&&(c(t),a(i))},d=e=>{let t=null;switch(e.key){case"Enter":u(e);break;case"ArrowRight":{const n=l.indexOf(e.currentTarget)+1;t=l[n]??l[0];break}case"ArrowLeft":{const n=l.indexOf(e.currentTarget)-1;t=l[n]??l[l.length-1];break}}t?.focus()};return(0,v.jsx)("ul",{role:"tablist","aria-orientation":"horizontal",className:(0,i.A)("tabs",{"tabs--block":n},t),children:o.map((e=>{let{value:t,label:n,attributes:s}=e;return(0,v.jsx)("li",{role:"tab",tabIndex:r===t?0:-1,"aria-selected":r===t,ref:e=>{l.push(e)},onKeyDown:d,onClick:u,...s,className:(0,i.A)("tabs__item",b.tabItem,s?.className,{"tabs__item--active":r===t}),children:n??t},t)}))})}function x(e){let{lazy:t,children:n,selectedValue:s}=e;const a=(Array.isArray(n)?n:[n]).filter(Boolean);if(t){const e=a.find((e=>e.props.value===s));return e?(0,r.cloneElement)(e,{className:(0,i.A)("margin-top--md",e.props.className)}):null}return(0,v.jsx)("div",{className:"margin-top--md",children:a.map(((e,t)=>(0,r.cloneElement)(e,{key:t,hidden:e.props.value!==s})))})}function y(e){const t=g(e);return(0,v.jsxs)("div",{className:(0,i.A)("tabs-container",b.tabList),children:[(0,v.jsx)(S,{...t,...e}),(0,v.jsx)(x,{...t,...e})]})}function w(e){const t=(0,m.A)();return(0,v.jsx)(y,{...e,children:d(e.children)},String(t))}},79329:(e,t,n)=>{n.d(t,{A:()=>a});n(96540);var r=n(34164);const i={tabItem:"tabItem_Ymn6"};var s=n(74848);function a(e){let{children:t,hidden:n,className:a}=e;return(0,s.jsx)("div",{role:"tabpanel",className:(0,r.A)(i.tabItem,a),hidden:n,children:t})}}}]);