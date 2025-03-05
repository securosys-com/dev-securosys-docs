"use strict";(self.webpackChunksecurosys_docs=self.webpackChunksecurosys_docs||[]).push([[25358],{6794:(e,t,r)=>{r.r(t),r.d(t,{assets:()=>l,contentTitle:()=>i,default:()=>d,frontMatter:()=>o,metadata:()=>n,toc:()=>u});const n=JSON.parse('{"id":"Installation/provider","title":"Salesforce BYOK - HSM Connectivity","description":"Description overview","source":"@site/salesforce-byok/Installation/provider.md","sourceDirName":"Installation","slug":"/Installation/provider","permalink":"/salesforce-byok/Installation/provider","draft":false,"unlisted":false,"tags":[],"version":"current","lastUpdatedAt":1741162132000,"sidebarPosition":2,"frontMatter":{"sidebar_position":2,"title":"Salesforce BYOK - HSM Connectivity","sidebar_label":"HSM Credentials and Connectivity","description":"Description overview","keywords":["hsm","cloud hsm"]},"sidebar":"tutorialSidebar","previous":{"title":"Installing Primus Tools","permalink":"/salesforce-byok/Installation/"},"next":{"title":"Tutorial","permalink":"/salesforce-byok/category/tutorial"}}');var a=r(74848),s=r(28453);r(65537),r(79329);const o={sidebar_position:2,title:"Salesforce BYOK - HSM Connectivity",sidebar_label:"HSM Credentials and Connectivity",description:"Description overview",keywords:["hsm","cloud hsm"]},i="HSM Credentials and Connectivity",l={},u=[];function c(e){const t={a:"a",admonition:"admonition",h1:"h1",header:"header",li:"li",p:"p",ul:"ul",...(0,s.R)(),...e.components};return(0,a.jsxs)(a.Fragment,{children:[(0,a.jsx)(t.header,{children:(0,a.jsx)(t.h1,{id:"hsm-credentials-and-connectivity",children:"HSM Credentials and Connectivity"})}),"\n",(0,a.jsxs)(t.p,{children:["For each command using the ",(0,a.jsx)(t.a,{href:"../../primus-tools/overview",children:"Primus Tools"}),". the following connectivity details must be provided:"]}),"\n",(0,a.jsxs)(t.ul,{children:["\n",(0,a.jsx)(t.li,{children:"Connection details (FQDN hostname or IP address, TCP/JCE port number of the HSM),"}),"\n",(0,a.jsx)(t.li,{children:"user (partition) and credentials (setup password or permanent secret),"}),"\n",(0,a.jsxs)(t.li,{children:["for ",(0,a.jsx)(t.a,{href:"../../cloudhsm/overview",children:"CloudHSM"}),", both service user (proxy) and credentials (service password)."]}),"\n"]}),"\n",(0,a.jsxs)(t.p,{children:["General connectivity details for on-premises Primus HSM and ",(0,a.jsx)(t.a,{href:"../../cloudhsm/overview",children:"CloudHSM"})," can be found under ",(0,a.jsx)(t.a,{href:"../../connectivity-details/overview",children:"Connectivity Details"}),"."]}),"\n",(0,a.jsx)(t.admonition,{type:"info",children:(0,a.jsxs)(t.p,{children:["Keep your HSM credentials at hand, whether acquired from your on-premises Primus HSM administrator or from the Securosys support team for ",(0,a.jsx)(t.a,{href:"../../cloudhsm/overview",children:"CloudHSM"}),"."]})}),"\n",(0,a.jsxs)(t.p,{children:["For more details on how to prepare and configure the connection and access for Primus Tools please visit ",(0,a.jsx)(t.a,{href:"../../primus-tools/Installation/primus-tools-installation",children:"Primus Tools - Installation"})," guide."]})]})}function d(e={}){const{wrapper:t}={...(0,s.R)(),...e.components};return t?(0,a.jsx)(t,{...e,children:(0,a.jsx)(c,{...e})}):c(e)}},28453:(e,t,r)=>{r.d(t,{R:()=>o,x:()=>i});var n=r(96540);const a={},s=n.createContext(a);function o(e){const t=n.useContext(s);return n.useMemo((function(){return"function"==typeof e?e(t):{...t,...e}}),[t,e])}function i(e){let t;return t=e.disableParentContext?"function"==typeof e.components?e.components(a):e.components||a:o(e.components),n.createElement(s.Provider,{value:t},e.children)}},65537:(e,t,r)=>{r.d(t,{A:()=>S});var n=r(96540),a=r(34164),s=r(65627),o=r(56347),i=r(50372),l=r(30604),u=r(11861),c=r(78749);function d(e){return n.Children.toArray(e).filter((e=>"\n"!==e)).map((e=>{if(!e||(0,n.isValidElement)(e)&&function(e){const{props:t}=e;return!!t&&"object"==typeof t&&"value"in t}(e))return e;throw new Error(`Docusaurus error: Bad <Tabs> child <${"string"==typeof e.type?e.type:e.type.name}>: all children of the <Tabs> component should be <TabItem>, and every <TabItem> should have a unique "value" prop.`)}))?.filter(Boolean)??[]}function p(e){const{values:t,children:r}=e;return(0,n.useMemo)((()=>{const e=t??function(e){return d(e).map((e=>{let{props:{value:t,label:r,attributes:n,default:a}}=e;return{value:t,label:r,attributes:n,default:a}}))}(r);return function(e){const t=(0,u.XI)(e,((e,t)=>e.value===t.value));if(t.length>0)throw new Error(`Docusaurus error: Duplicate values "${t.map((e=>e.value)).join(", ")}" found in <Tabs>. Every value needs to be unique.`)}(e),e}),[t,r])}function h(e){let{value:t,tabValues:r}=e;return r.some((e=>e.value===t))}function f(e){let{queryString:t=!1,groupId:r}=e;const a=(0,o.W6)(),s=function(e){let{queryString:t=!1,groupId:r}=e;if("string"==typeof t)return t;if(!1===t)return null;if(!0===t&&!r)throw new Error('Docusaurus error: The <Tabs> component groupId prop is required if queryString=true, because this value is used as the search param name. You can also provide an explicit value such as queryString="my-search-param".');return r??null}({queryString:t,groupId:r});return[(0,l.aZ)(s),(0,n.useCallback)((e=>{if(!s)return;const t=new URLSearchParams(a.location.search);t.set(s,e),a.replace({...a.location,search:t.toString()})}),[s,a])]}function m(e){const{defaultValue:t,queryString:r=!1,groupId:a}=e,s=p(e),[o,l]=(0,n.useState)((()=>function(e){let{defaultValue:t,tabValues:r}=e;if(0===r.length)throw new Error("Docusaurus error: the <Tabs> component requires at least one <TabItem> children component");if(t){if(!h({value:t,tabValues:r}))throw new Error(`Docusaurus error: The <Tabs> has a defaultValue "${t}" but none of its children has the corresponding value. Available values are: ${r.map((e=>e.value)).join(", ")}. If you intend to show no default tab, use defaultValue={null} instead.`);return t}const n=r.find((e=>e.default))??r[0];if(!n)throw new Error("Unexpected error: 0 tabValues");return n.value}({defaultValue:t,tabValues:s}))),[u,d]=f({queryString:r,groupId:a}),[m,v]=function(e){let{groupId:t}=e;const r=function(e){return e?`docusaurus.tab.${e}`:null}(t),[a,s]=(0,c.Dv)(r);return[a,(0,n.useCallback)((e=>{r&&s.set(e)}),[r,s])]}({groupId:a}),b=(()=>{const e=u??m;return h({value:e,tabValues:s})?e:null})();(0,i.A)((()=>{b&&l(b)}),[b]);return{selectedValue:o,selectValue:(0,n.useCallback)((e=>{if(!h({value:e,tabValues:s}))throw new Error(`Can't select invalid tab value=${e}`);l(e),d(e),v(e)}),[d,v,s]),tabValues:s}}var v=r(9136);const b={tabList:"tabList__CuJ",tabItem:"tabItem_LNqP"};var y=r(74848);function x(e){let{className:t,block:r,selectedValue:n,selectValue:o,tabValues:i}=e;const l=[],{blockElementScrollPositionUntilNextRender:u}=(0,s.a_)(),c=e=>{const t=e.currentTarget,r=l.indexOf(t),a=i[r].value;a!==n&&(u(t),o(a))},d=e=>{let t=null;switch(e.key){case"Enter":c(e);break;case"ArrowRight":{const r=l.indexOf(e.currentTarget)+1;t=l[r]??l[0];break}case"ArrowLeft":{const r=l.indexOf(e.currentTarget)-1;t=l[r]??l[l.length-1];break}}t?.focus()};return(0,y.jsx)("ul",{role:"tablist","aria-orientation":"horizontal",className:(0,a.A)("tabs",{"tabs--block":r},t),children:i.map((e=>{let{value:t,label:r,attributes:s}=e;return(0,y.jsx)("li",{role:"tab",tabIndex:n===t?0:-1,"aria-selected":n===t,ref:e=>{l.push(e)},onKeyDown:d,onClick:c,...s,className:(0,a.A)("tabs__item",b.tabItem,s?.className,{"tabs__item--active":n===t}),children:r??t},t)}))})}function g(e){let{lazy:t,children:r,selectedValue:s}=e;const o=(Array.isArray(r)?r:[r]).filter(Boolean);if(t){const e=o.find((e=>e.props.value===s));return e?(0,n.cloneElement)(e,{className:(0,a.A)("margin-top--md",e.props.className)}):null}return(0,y.jsx)("div",{className:"margin-top--md",children:o.map(((e,t)=>(0,n.cloneElement)(e,{key:t,hidden:e.props.value!==s})))})}function w(e){const t=m(e);return(0,y.jsxs)("div",{className:(0,a.A)("tabs-container",b.tabList),children:[(0,y.jsx)(x,{...t,...e}),(0,y.jsx)(g,{...t,...e})]})}function S(e){const t=(0,v.A)();return(0,y.jsx)(w,{...e,children:d(e.children)},String(t))}},79329:(e,t,r)=>{r.d(t,{A:()=>o});r(96540);var n=r(34164);const a={tabItem:"tabItem_Ymn6"};var s=r(74848);function o(e){let{children:t,hidden:r,className:o}=e;return(0,s.jsx)("div",{role:"tabpanel",className:(0,n.A)(a.tabItem,o),hidden:r,children:t})}}}]);