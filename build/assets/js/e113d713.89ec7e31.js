"use strict";(self.webpackChunksecurosys_docs=self.webpackChunksecurosys_docs||[]).push([[62458],{28453:(e,t,r)=>{r.d(t,{R:()=>s,x:()=>l});var n=r(96540);const a={},o=n.createContext(a);function s(e){const t=n.useContext(o);return n.useMemo((function(){return"function"==typeof e?e(t):{...t,...e}}),[t,e])}function l(e){let t;return t=e.disableParentContext?"function"==typeof e.components?e.components(a):e.components||a:s(e.components),n.createElement(o.Provider,{value:t},e.children)}},33120:(e,t,r)=>{r.r(t),r.d(t,{assets:()=>u,contentTitle:()=>l,default:()=>d,frontMatter:()=>s,metadata:()=>n,toc:()=>i});const n=JSON.parse('{"id":"downloads","title":"AWS Bring Your Own Key (BYOK) - Download","description":"To download the Primus Tools please see Primus Tools Download section.","source":"@site/aws-byok/downloads.md","sourceDirName":".","slug":"/downloads","permalink":"/aws-byok/downloads","draft":false,"unlisted":false,"tags":[],"version":"current","lastUpdatedAt":1741162132000,"sidebarPosition":6,"frontMatter":{"sidebar_position":6,"title":"AWS Bring Your Own Key (BYOK) - Download","sidebar_label":"Download"},"sidebar":"tutorialSidebar","previous":{"title":"Glossary","permalink":"/aws-byok/Concepts/Glossary"}}');var a=r(74848),o=r(28453);r(65537),r(79329);const s={sidebar_position:6,title:"AWS Bring Your Own Key (BYOK) - Download",sidebar_label:"Download"},l="Download",u={},i=[];function c(e){const t={a:"a",em:"em",h1:"h1",header:"header",p:"p",...(0,o.R)(),...e.components};return(0,a.jsxs)(a.Fragment,{children:[(0,a.jsx)(t.header,{children:(0,a.jsx)(t.h1,{id:"download",children:"Download"})}),"\n",(0,a.jsxs)(t.p,{children:["To download the ",(0,a.jsx)(t.em,{children:"Primus Tools"})," please see ",(0,a.jsx)(t.a,{href:"/primus-tools/downloads",children:"Primus Tools Download"})," section."]})]})}function d(e={}){const{wrapper:t}={...(0,o.R)(),...e.components};return t?(0,a.jsx)(t,{...e,children:(0,a.jsx)(c,{...e})}):c(e)}},65537:(e,t,r)=>{r.d(t,{A:()=>k});var n=r(96540),a=r(34164),o=r(65627),s=r(56347),l=r(50372),u=r(30604),i=r(11861),c=r(78749);function d(e){return n.Children.toArray(e).filter((e=>"\n"!==e)).map((e=>{if(!e||(0,n.isValidElement)(e)&&function(e){const{props:t}=e;return!!t&&"object"==typeof t&&"value"in t}(e))return e;throw new Error(`Docusaurus error: Bad <Tabs> child <${"string"==typeof e.type?e.type:e.type.name}>: all children of the <Tabs> component should be <TabItem>, and every <TabItem> should have a unique "value" prop.`)}))?.filter(Boolean)??[]}function p(e){const{values:t,children:r}=e;return(0,n.useMemo)((()=>{const e=t??function(e){return d(e).map((e=>{let{props:{value:t,label:r,attributes:n,default:a}}=e;return{value:t,label:r,attributes:n,default:a}}))}(r);return function(e){const t=(0,i.XI)(e,((e,t)=>e.value===t.value));if(t.length>0)throw new Error(`Docusaurus error: Duplicate values "${t.map((e=>e.value)).join(", ")}" found in <Tabs>. Every value needs to be unique.`)}(e),e}),[t,r])}function b(e){let{value:t,tabValues:r}=e;return r.some((e=>e.value===t))}function f(e){let{queryString:t=!1,groupId:r}=e;const a=(0,s.W6)(),o=function(e){let{queryString:t=!1,groupId:r}=e;if("string"==typeof t)return t;if(!1===t)return null;if(!0===t&&!r)throw new Error('Docusaurus error: The <Tabs> component groupId prop is required if queryString=true, because this value is used as the search param name. You can also provide an explicit value such as queryString="my-search-param".');return r??null}({queryString:t,groupId:r});return[(0,u.aZ)(o),(0,n.useCallback)((e=>{if(!o)return;const t=new URLSearchParams(a.location.search);t.set(o,e),a.replace({...a.location,search:t.toString()})}),[o,a])]}function h(e){const{defaultValue:t,queryString:r=!1,groupId:a}=e,o=p(e),[s,u]=(0,n.useState)((()=>function(e){let{defaultValue:t,tabValues:r}=e;if(0===r.length)throw new Error("Docusaurus error: the <Tabs> component requires at least one <TabItem> children component");if(t){if(!b({value:t,tabValues:r}))throw new Error(`Docusaurus error: The <Tabs> has a defaultValue "${t}" but none of its children has the corresponding value. Available values are: ${r.map((e=>e.value)).join(", ")}. If you intend to show no default tab, use defaultValue={null} instead.`);return t}const n=r.find((e=>e.default))??r[0];if(!n)throw new Error("Unexpected error: 0 tabValues");return n.value}({defaultValue:t,tabValues:o}))),[i,d]=f({queryString:r,groupId:a}),[h,m]=function(e){let{groupId:t}=e;const r=function(e){return e?`docusaurus.tab.${e}`:null}(t),[a,o]=(0,c.Dv)(r);return[a,(0,n.useCallback)((e=>{r&&o.set(e)}),[r,o])]}({groupId:a}),v=(()=>{const e=i??h;return b({value:e,tabValues:o})?e:null})();(0,l.A)((()=>{v&&u(v)}),[v]);return{selectedValue:s,selectValue:(0,n.useCallback)((e=>{if(!b({value:e,tabValues:o}))throw new Error(`Can't select invalid tab value=${e}`);u(e),d(e),m(e)}),[d,m,o]),tabValues:o}}var m=r(9136);const v={tabList:"tabList__CuJ",tabItem:"tabItem_LNqP"};var w=r(74848);function y(e){let{className:t,block:r,selectedValue:n,selectValue:s,tabValues:l}=e;const u=[],{blockElementScrollPositionUntilNextRender:i}=(0,o.a_)(),c=e=>{const t=e.currentTarget,r=u.indexOf(t),a=l[r].value;a!==n&&(i(t),s(a))},d=e=>{let t=null;switch(e.key){case"Enter":c(e);break;case"ArrowRight":{const r=u.indexOf(e.currentTarget)+1;t=u[r]??u[0];break}case"ArrowLeft":{const r=u.indexOf(e.currentTarget)-1;t=u[r]??u[u.length-1];break}}t?.focus()};return(0,w.jsx)("ul",{role:"tablist","aria-orientation":"horizontal",className:(0,a.A)("tabs",{"tabs--block":r},t),children:l.map((e=>{let{value:t,label:r,attributes:o}=e;return(0,w.jsx)("li",{role:"tab",tabIndex:n===t?0:-1,"aria-selected":n===t,ref:e=>{u.push(e)},onKeyDown:d,onClick:c,...o,className:(0,a.A)("tabs__item",v.tabItem,o?.className,{"tabs__item--active":n===t}),children:r??t},t)}))})}function g(e){let{lazy:t,children:r,selectedValue:o}=e;const s=(Array.isArray(r)?r:[r]).filter(Boolean);if(t){const e=s.find((e=>e.props.value===o));return e?(0,n.cloneElement)(e,{className:(0,a.A)("margin-top--md",e.props.className)}):null}return(0,w.jsx)("div",{className:"margin-top--md",children:s.map(((e,t)=>(0,n.cloneElement)(e,{key:t,hidden:e.props.value!==o})))})}function x(e){const t=h(e);return(0,w.jsxs)("div",{className:(0,a.A)("tabs-container",v.tabList),children:[(0,w.jsx)(y,{...t,...e}),(0,w.jsx)(g,{...t,...e})]})}function k(e){const t=(0,m.A)();return(0,w.jsx)(x,{...e,children:d(e.children)},String(t))}},79329:(e,t,r)=>{r.d(t,{A:()=>s});r(96540);var n=r(34164);const a={tabItem:"tabItem_Ymn6"};var o=r(74848);function s(e){let{children:t,hidden:r,className:s}=e;return(0,o.jsx)("div",{role:"tabpanel",className:(0,n.A)(a.tabItem,s),hidden:r,children:t})}}}]);