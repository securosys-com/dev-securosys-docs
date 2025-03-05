"use strict";(self.webpackChunksecurosys_docs=self.webpackChunksecurosys_docs||[]).push([[35725],{10547:(e,r,t)=>{t.r(r),t.d(r,{assets:()=>c,contentTitle:()=>u,default:()=>p,frontMatter:()=>i,metadata:()=>o,toc:()=>d});const o=JSON.parse('{"id":"downloads","title":"Download Primus Tools for Security HSM","description":"Download Securosys Primus Tools for Hardware Security Modules (HSMs)","source":"@site/primus-tools/downloads.md","sourceDirName":".","slug":"/downloads","permalink":"/primus-tools/downloads","draft":false,"unlisted":false,"tags":[],"version":"current","lastUpdatedAt":1741162132000,"sidebarPosition":7,"frontMatter":{"sidebar_position":7,"title":"Download Primus Tools for Security HSM","sidebar_label":"Download","description":"Download Securosys Primus Tools for Hardware Security Modules (HSMs)","keywords":["hsm","cloud hsm"]},"sidebar":"tutorialSidebar","previous":{"title":"Key Migration","permalink":"/primus-tools/Use-Cases/key-migration"}}');var s=t(74848),n=t(28453),a=t(65537),l=t(79329);const i={sidebar_position:7,title:"Download Primus Tools for Security HSM",sidebar_label:"Download",description:"Download Securosys Primus Tools for Hardware Security Modules (HSMs)",keywords:["hsm","cloud hsm"]},u="Download",c={},d=[{value:"Credentials",id:"credentials",level:2},{value:"Primus Tools Bundle",id:"primus-tools-bundle",level:2}];function h(e){const r={a:"a",admonition:"admonition",code:"code",em:"em",h1:"h1",h2:"h2",header:"header",hr:"hr",p:"p",pre:"pre",strong:"strong",...(0,n.R)(),...e.components},{Icon:t}=r;return t||function(e,r){throw new Error("Expected "+(r?"component":"object")+" `"+e+"` to be defined: you likely forgot to import, pass, or provide it.")}("Icon",!0),(0,s.jsxs)(s.Fragment,{children:[(0,s.jsx)(r.header,{children:(0,s.jsx)(r.h1,{id:"download",children:"Download"})}),"\n",(0,s.jsx)(r.admonition,{title:"Download access",type:"warning",children:(0,s.jsxs)(r.p,{children:["To download the software, you must be a ",(0,s.jsx)(r.strong,{children:"registered support user"}),". ",(0,s.jsx)("br",{}),"\nIf you are ",(0,s.jsx)(r.strong,{children:"new"})," to developing applications with a Securosys-HSM, you can obtain a ",(0,s.jsx)(r.strong,{children:"free developer account"})," by contacting us at ",(0,s.jsx)(r.a,{href:"mailto:info@securosys.com",children:"info@securosys.com"}),"."]})}),"\n",(0,s.jsx)(r.hr,{}),"\n",(0,s.jsx)(r.h2,{id:"credentials",children:"Credentials"}),"\n",(0,s.jsxs)(r.p,{children:["To download the ",(0,s.jsx)(r.em,{children:"Primus Tools"})," retrieve the ",(0,s.jsx)(r.strong,{children:"Download-Link-File"})," from the Securosys Support Portal Knowledge Base article in the ",(0,s.jsx)(r.em,{children:"Download"})," section. This file contains the credentials necessary for downloading the software package."]}),"\n",(0,s.jsxs)(r.p,{children:[(0,s.jsx)(t,{icon:"fa-solid fa-download",size:"lg"})," ",(0,s.jsx)(r.a,{href:"https://support.securosys.com/external/knowledge-base/article/127",children:"Support Portal: Download-Link-File"})]}),"\n",(0,s.jsx)(r.h2,{id:"primus-tools-bundle",children:"Primus Tools Bundle"}),"\n",(0,s.jsxs)(r.p,{children:["Replace the variables ",(0,s.jsx)(r.code,{children:"CRED"}),", ",(0,s.jsx)(r.code,{children:"VERSION"})," with the values from the ",(0,s.jsx)(r.strong,{children:"Download-Link-File"}),"."]}),"\n",(0,s.jsxs)(a.A,{groupId:"os",children:[(0,s.jsx)(l.A,{value:"unix",label:"Unix",default:!0,children:(0,s.jsx)(r.pre,{children:(0,s.jsx)(r.code,{className:"language-sh",metastring:"{2,3}",children:"USER=support.reader.jce\nCRED=REPLACE_ME\nVERSION=latest\ncurl -L -XGET https://${USER}:${CRED}@securosys.jfrog.io/artifactory/jce-provider/PrimusTools_JCE-${VERSION}.zip -o PrimusTools_JCE-${VERSION}.zip\n"})})}),(0,s.jsx)(l.A,{value:"win",label:"Windows CMD",default:!0,children:(0,s.jsx)(r.pre,{children:(0,s.jsx)(r.code,{className:"language-sh",metastring:"{2,3}",children:'set USER=support.reader.jce\nset CRED=REPLACE_ME\nset VERSION=latest\ncurl -L -o "PrimusTools_JCE-%VERSION%.zip" https://%USER%:%CRED%@securosys.jfrog.io/artifactory/jce-provider/PrimusTools_JCE-%VERSION%.zip\n'})})}),(0,s.jsxs)(l.A,{value:"browser",label:"Browser",default:!0,children:[(0,s.jsxs)(r.p,{children:["Username: ",(0,s.jsx)(r.code,{children:"support.reader.jce"})]}),(0,s.jsxs)(r.p,{children:[(0,s.jsx)(t,{icon:"fa-solid fa-download",size:"lg"})," ",(0,s.jsx)(r.a,{href:"https://securosys.jfrog.io/artifactory/jce-provider/PrimusTools_JCE-latest.zip",children:"Primus Tools Bundle"})]})]})]})]})}function p(e={}){const{wrapper:r}={...(0,n.R)(),...e.components};return r?(0,s.jsx)(r,{...e,children:(0,s.jsx)(h,{...e})}):h(e)}},28453:(e,r,t)=>{t.d(r,{R:()=>a,x:()=>l});var o=t(96540);const s={},n=o.createContext(s);function a(e){const r=o.useContext(n);return o.useMemo((function(){return"function"==typeof e?e(r):{...r,...e}}),[r,e])}function l(e){let r;return r=e.disableParentContext?"function"==typeof e.components?e.components(s):e.components||s:a(e.components),o.createElement(n.Provider,{value:r},e.children)}},65537:(e,r,t)=>{t.d(r,{A:()=>y});var o=t(96540),s=t(34164),n=t(65627),a=t(56347),l=t(50372),i=t(30604),u=t(11861),c=t(78749);function d(e){return o.Children.toArray(e).filter((e=>"\n"!==e)).map((e=>{if(!e||(0,o.isValidElement)(e)&&function(e){const{props:r}=e;return!!r&&"object"==typeof r&&"value"in r}(e))return e;throw new Error(`Docusaurus error: Bad <Tabs> child <${"string"==typeof e.type?e.type:e.type.name}>: all children of the <Tabs> component should be <TabItem>, and every <TabItem> should have a unique "value" prop.`)}))?.filter(Boolean)??[]}function h(e){const{values:r,children:t}=e;return(0,o.useMemo)((()=>{const e=r??function(e){return d(e).map((e=>{let{props:{value:r,label:t,attributes:o,default:s}}=e;return{value:r,label:t,attributes:o,default:s}}))}(t);return function(e){const r=(0,u.XI)(e,((e,r)=>e.value===r.value));if(r.length>0)throw new Error(`Docusaurus error: Duplicate values "${r.map((e=>e.value)).join(", ")}" found in <Tabs>. Every value needs to be unique.`)}(e),e}),[r,t])}function p(e){let{value:r,tabValues:t}=e;return t.some((e=>e.value===r))}function f(e){let{queryString:r=!1,groupId:t}=e;const s=(0,a.W6)(),n=function(e){let{queryString:r=!1,groupId:t}=e;if("string"==typeof r)return r;if(!1===r)return null;if(!0===r&&!t)throw new Error('Docusaurus error: The <Tabs> component groupId prop is required if queryString=true, because this value is used as the search param name. You can also provide an explicit value such as queryString="my-search-param".');return t??null}({queryString:r,groupId:t});return[(0,i.aZ)(n),(0,o.useCallback)((e=>{if(!n)return;const r=new URLSearchParams(s.location.search);r.set(n,e),s.replace({...s.location,search:r.toString()})}),[n,s])]}function m(e){const{defaultValue:r,queryString:t=!1,groupId:s}=e,n=h(e),[a,i]=(0,o.useState)((()=>function(e){let{defaultValue:r,tabValues:t}=e;if(0===t.length)throw new Error("Docusaurus error: the <Tabs> component requires at least one <TabItem> children component");if(r){if(!p({value:r,tabValues:t}))throw new Error(`Docusaurus error: The <Tabs> has a defaultValue "${r}" but none of its children has the corresponding value. Available values are: ${t.map((e=>e.value)).join(", ")}. If you intend to show no default tab, use defaultValue={null} instead.`);return r}const o=t.find((e=>e.default))??t[0];if(!o)throw new Error("Unexpected error: 0 tabValues");return o.value}({defaultValue:r,tabValues:n}))),[u,d]=f({queryString:t,groupId:s}),[m,b]=function(e){let{groupId:r}=e;const t=function(e){return e?`docusaurus.tab.${e}`:null}(r),[s,n]=(0,c.Dv)(t);return[s,(0,o.useCallback)((e=>{t&&n.set(e)}),[t,n])]}({groupId:s}),w=(()=>{const e=u??m;return p({value:e,tabValues:n})?e:null})();(0,l.A)((()=>{w&&i(w)}),[w]);return{selectedValue:a,selectValue:(0,o.useCallback)((e=>{if(!p({value:e,tabValues:n}))throw new Error(`Can't select invalid tab value=${e}`);i(e),d(e),b(e)}),[d,b,n]),tabValues:n}}var b=t(9136);const w={tabList:"tabList__CuJ",tabItem:"tabItem_LNqP"};var v=t(74848);function g(e){let{className:r,block:t,selectedValue:o,selectValue:a,tabValues:l}=e;const i=[],{blockElementScrollPositionUntilNextRender:u}=(0,n.a_)(),c=e=>{const r=e.currentTarget,t=i.indexOf(r),s=l[t].value;s!==o&&(u(r),a(s))},d=e=>{let r=null;switch(e.key){case"Enter":c(e);break;case"ArrowRight":{const t=i.indexOf(e.currentTarget)+1;r=i[t]??i[0];break}case"ArrowLeft":{const t=i.indexOf(e.currentTarget)-1;r=i[t]??i[i.length-1];break}}r?.focus()};return(0,v.jsx)("ul",{role:"tablist","aria-orientation":"horizontal",className:(0,s.A)("tabs",{"tabs--block":t},r),children:l.map((e=>{let{value:r,label:t,attributes:n}=e;return(0,v.jsx)("li",{role:"tab",tabIndex:o===r?0:-1,"aria-selected":o===r,ref:e=>{i.push(e)},onKeyDown:d,onClick:c,...n,className:(0,s.A)("tabs__item",w.tabItem,n?.className,{"tabs__item--active":o===r}),children:t??r},r)}))})}function x(e){let{lazy:r,children:t,selectedValue:n}=e;const a=(Array.isArray(t)?t:[t]).filter(Boolean);if(r){const e=a.find((e=>e.props.value===n));return e?(0,o.cloneElement)(e,{className:(0,s.A)("margin-top--md",e.props.className)}):null}return(0,v.jsx)("div",{className:"margin-top--md",children:a.map(((e,r)=>(0,o.cloneElement)(e,{key:r,hidden:e.props.value!==n})))})}function j(e){const r=m(e);return(0,v.jsxs)("div",{className:(0,s.A)("tabs-container",w.tabList),children:[(0,v.jsx)(g,{...r,...e}),(0,v.jsx)(x,{...r,...e})]})}function y(e){const r=(0,b.A)();return(0,v.jsx)(j,{...e,children:d(e.children)},String(r))}},79329:(e,r,t)=>{t.d(r,{A:()=>a});t(96540);var o=t(34164);const s={tabItem:"tabItem_Ymn6"};var n=t(74848);function a(e){let{children:r,hidden:t,className:a}=e;return(0,n.jsx)("div",{role:"tabpanel",className:(0,o.A)(s.tabItem,a),hidden:t,children:r})}}}]);