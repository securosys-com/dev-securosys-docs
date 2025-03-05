"use strict";(self.webpackChunksecurosys_docs=self.webpackChunksecurosys_docs||[]).push([[29625],{15621:(e,t,r)=>{r.r(t),r.d(t,{assets:()=>o,contentTitle:()=>l,default:()=>d,frontMatter:()=>i,metadata:()=>n,toc:()=>u});const n=JSON.parse('{"id":"Installation/p11-install","title":"API Provider for Securden Unified PAM","description":"Prerequisites to install Securden Unified Privileged Access Management (PAM) for Securosys Hardware Security Modules (HSMs)","source":"@site/securden-pam/Installation/p11-install.md","sourceDirName":"Installation","slug":"/Installation/p11-install","permalink":"/securden-pam/Installation/p11-install","draft":false,"unlisted":false,"tags":[],"version":"current","lastUpdatedAt":1741162132000,"sidebarPosition":1,"frontMatter":{"sidebar_position":1,"title":"API Provider for Securden Unified PAM","sidebar_label":"API Provider","description":"Prerequisites to install Securden Unified Privileged Access Management (PAM) for Securosys Hardware Security Modules (HSMs)","keywords":["hsm","cloud hsm"]},"sidebar":"tutorialSidebar","previous":{"title":"Prerequisites","permalink":"/securden-pam/Installation/prerequisites"},"next":{"title":"HSM Configuration","permalink":"/securden-pam/Installation/hsm-config"}}');var a=r(74848),s=r(28453);r(65537),r(79329);const i={sidebar_position:1,title:"API Provider for Securden Unified PAM",sidebar_label:"API Provider",description:"Prerequisites to install Securden Unified Privileged Access Management (PAM) for Securosys Hardware Security Modules (HSMs)",keywords:["hsm","cloud hsm"]},l="PKCS#11 API Installation and Configuration",o={},u=[];function c(e){const t={a:"a",h1:"h1",header:"header",li:"li",p:"p",ul:"ul",...(0,s.R)(),...e.components};return(0,a.jsxs)(a.Fragment,{children:[(0,a.jsx)(t.header,{children:(0,a.jsx)(t.h1,{id:"pkcs11-api-installation-and-configuration",children:"PKCS#11 API Installation and Configuration"})}),"\n",(0,a.jsxs)(t.p,{children:["Refer to the ",(0,a.jsx)(t.a,{href:"/pkcs/overview",children:"Primus HSM PKCS#11 Provider documentation"}),"  for:"]}),"\n",(0,a.jsxs)(t.ul,{children:["\n",(0,a.jsxs)(t.li,{children:["Primus HSM PKCS#11 Provider software ",(0,a.jsx)(t.a,{href:"/pkcs/Installation/pkcs11_provider_installation",children:"Installation"}),"."]}),"\n",(0,a.jsxs)(t.li,{children:["Primus HSM PKCS#11 Provider ",(0,a.jsx)(t.a,{href:"/pkcs/Installation/pkcs11_provider_configuration",children:"Configuration"}),"."]}),"\n",(0,a.jsxs)(t.li,{children:[(0,a.jsx)(t.a,{href:"/pkcs/Installation/permanent_secret_management",children:"Permanent Secret Fetching"}),", using the temporary setup password."]}),"\n",(0,a.jsxs)(t.li,{children:[(0,a.jsx)(t.a,{href:"/pkcs/Tutorials/troubleshooting",children:"Connectivity Test"}),"."]}),"\n"]})]})}function d(e={}){const{wrapper:t}={...(0,s.R)(),...e.components};return t?(0,a.jsx)(t,{...e,children:(0,a.jsx)(c,{...e})}):c(e)}},28453:(e,t,r)=>{r.d(t,{R:()=>i,x:()=>l});var n=r(96540);const a={},s=n.createContext(a);function i(e){const t=n.useContext(s);return n.useMemo((function(){return"function"==typeof e?e(t):{...t,...e}}),[t,e])}function l(e){let t;return t=e.disableParentContext?"function"==typeof e.components?e.components(a):e.components||a:i(e.components),n.createElement(s.Provider,{value:t},e.children)}},65537:(e,t,r)=>{r.d(t,{A:()=>y});var n=r(96540),a=r(34164),s=r(65627),i=r(56347),l=r(50372),o=r(30604),u=r(11861),c=r(78749);function d(e){return n.Children.toArray(e).filter((e=>"\n"!==e)).map((e=>{if(!e||(0,n.isValidElement)(e)&&function(e){const{props:t}=e;return!!t&&"object"==typeof t&&"value"in t}(e))return e;throw new Error(`Docusaurus error: Bad <Tabs> child <${"string"==typeof e.type?e.type:e.type.name}>: all children of the <Tabs> component should be <TabItem>, and every <TabItem> should have a unique "value" prop.`)}))?.filter(Boolean)??[]}function f(e){const{values:t,children:r}=e;return(0,n.useMemo)((()=>{const e=t??function(e){return d(e).map((e=>{let{props:{value:t,label:r,attributes:n,default:a}}=e;return{value:t,label:r,attributes:n,default:a}}))}(r);return function(e){const t=(0,u.XI)(e,((e,t)=>e.value===t.value));if(t.length>0)throw new Error(`Docusaurus error: Duplicate values "${t.map((e=>e.value)).join(", ")}" found in <Tabs>. Every value needs to be unique.`)}(e),e}),[t,r])}function p(e){let{value:t,tabValues:r}=e;return r.some((e=>e.value===t))}function h(e){let{queryString:t=!1,groupId:r}=e;const a=(0,i.W6)(),s=function(e){let{queryString:t=!1,groupId:r}=e;if("string"==typeof t)return t;if(!1===t)return null;if(!0===t&&!r)throw new Error('Docusaurus error: The <Tabs> component groupId prop is required if queryString=true, because this value is used as the search param name. You can also provide an explicit value such as queryString="my-search-param".');return r??null}({queryString:t,groupId:r});return[(0,o.aZ)(s),(0,n.useCallback)((e=>{if(!s)return;const t=new URLSearchParams(a.location.search);t.set(s,e),a.replace({...a.location,search:t.toString()})}),[s,a])]}function m(e){const{defaultValue:t,queryString:r=!1,groupId:a}=e,s=f(e),[i,o]=(0,n.useState)((()=>function(e){let{defaultValue:t,tabValues:r}=e;if(0===r.length)throw new Error("Docusaurus error: the <Tabs> component requires at least one <TabItem> children component");if(t){if(!p({value:t,tabValues:r}))throw new Error(`Docusaurus error: The <Tabs> has a defaultValue "${t}" but none of its children has the corresponding value. Available values are: ${r.map((e=>e.value)).join(", ")}. If you intend to show no default tab, use defaultValue={null} instead.`);return t}const n=r.find((e=>e.default))??r[0];if(!n)throw new Error("Unexpected error: 0 tabValues");return n.value}({defaultValue:t,tabValues:s}))),[u,d]=h({queryString:r,groupId:a}),[m,b]=function(e){let{groupId:t}=e;const r=function(e){return e?`docusaurus.tab.${e}`:null}(t),[a,s]=(0,c.Dv)(r);return[a,(0,n.useCallback)((e=>{r&&s.set(e)}),[r,s])]}({groupId:a}),v=(()=>{const e=u??m;return p({value:e,tabValues:s})?e:null})();(0,l.A)((()=>{v&&o(v)}),[v]);return{selectedValue:i,selectValue:(0,n.useCallback)((e=>{if(!p({value:e,tabValues:s}))throw new Error(`Can't select invalid tab value=${e}`);o(e),d(e),b(e)}),[d,b,s]),tabValues:s}}var b=r(9136);const v={tabList:"tabList__CuJ",tabItem:"tabItem_LNqP"};var g=r(74848);function P(e){let{className:t,block:r,selectedValue:n,selectValue:i,tabValues:l}=e;const o=[],{blockElementScrollPositionUntilNextRender:u}=(0,s.a_)(),c=e=>{const t=e.currentTarget,r=o.indexOf(t),a=l[r].value;a!==n&&(u(t),i(a))},d=e=>{let t=null;switch(e.key){case"Enter":c(e);break;case"ArrowRight":{const r=o.indexOf(e.currentTarget)+1;t=o[r]??o[0];break}case"ArrowLeft":{const r=o.indexOf(e.currentTarget)-1;t=o[r]??o[o.length-1];break}}t?.focus()};return(0,g.jsx)("ul",{role:"tablist","aria-orientation":"horizontal",className:(0,a.A)("tabs",{"tabs--block":r},t),children:l.map((e=>{let{value:t,label:r,attributes:s}=e;return(0,g.jsx)("li",{role:"tab",tabIndex:n===t?0:-1,"aria-selected":n===t,ref:e=>{o.push(e)},onKeyDown:d,onClick:c,...s,className:(0,a.A)("tabs__item",v.tabItem,s?.className,{"tabs__item--active":n===t}),children:r??t},t)}))})}function I(e){let{lazy:t,children:r,selectedValue:s}=e;const i=(Array.isArray(r)?r:[r]).filter(Boolean);if(t){const e=i.find((e=>e.props.value===s));return e?(0,n.cloneElement)(e,{className:(0,a.A)("margin-top--md",e.props.className)}):null}return(0,g.jsx)("div",{className:"margin-top--md",children:i.map(((e,t)=>(0,n.cloneElement)(e,{key:t,hidden:e.props.value!==s})))})}function S(e){const t=m(e);return(0,g.jsxs)("div",{className:(0,a.A)("tabs-container",v.tabList),children:[(0,g.jsx)(P,{...t,...e}),(0,g.jsx)(I,{...t,...e})]})}function y(e){const t=(0,b.A)();return(0,g.jsx)(S,{...e,children:d(e.children)},String(t))}},79329:(e,t,r)=>{r.d(t,{A:()=>i});r(96540);var n=r(34164);const a={tabItem:"tabItem_Ymn6"};var s=r(74848);function i(e){let{children:t,hidden:r,className:i}=e;return(0,s.jsx)("div",{role:"tabpanel",className:(0,n.A)(a.tabItem,i),hidden:r,children:t})}}}]);