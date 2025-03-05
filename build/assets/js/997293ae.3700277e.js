"use strict";(self.webpackChunksecurosys_docs=self.webpackChunksecurosys_docs||[]).push([[14948],{24359:(e,r,t)=>{t.r(r),t.d(r,{assets:()=>l,contentTitle:()=>u,default:()=>m,frontMatter:()=>c,metadata:()=>n,toc:()=>d});const n=JSON.parse('{"id":"Installation/Prerequisites","title":"Prerequisites - Keyfactor EJBCA and HSMs","description":"Discover how the Keyfactor and PrimeKey merger enhances machine identity management with end-to-end PKI solutions. Integrating Keyfactor\'s automation with PrimeKey\u2019s EJBCA software, this robust platform supports IoT, DevOps, and enterprise environments. Secure your PKI with Securosys HSMs for strong compliance and key protection.","source":"@site/primekey-ejbca/Installation/Prerequisites.md","sourceDirName":"Installation","slug":"/Installation/Prerequisites","permalink":"/primekey-ejbca/Installation/Prerequisites","draft":false,"unlisted":false,"tags":[],"version":"current","lastUpdatedAt":1741162132000,"sidebarPosition":0,"frontMatter":{"sidebar_position":0,"title":"Prerequisites - Keyfactor EJBCA and HSMs","sidebar_label":"Prerequisites","description":"Discover how the Keyfactor and PrimeKey merger enhances machine identity management with end-to-end PKI solutions. Integrating Keyfactor\'s automation with PrimeKey\u2019s EJBCA software, this robust platform supports IoT, DevOps, and enterprise environments. Secure your PKI with Securosys HSMs for strong compliance and key protection.","keywords":["cloud hsm","hsm key management","hsm cloud","hsm as a service","cloud based hsm","hsm digital signature","hsm services","hsm service","what is cloud hsm","hsm signing","hsm pki","hsm encryption","code signing hsm","hsm key","code signing service","hsm code signing","cloud code signing","cloud encryption key management","cloud hardware security module","cloudhsm vs kms","code signing certificate","key management hsm","microsoft encryption key management","hsm aws","document signing services","code signing","hsm providers","code signing as a service","aws cloudhsm documentation","hsm pricing"]},"sidebar":"tutorialSidebar","previous":{"title":"Installation","permalink":"/primekey-ejbca/category/installation"},"next":{"title":"Install the API Provider","permalink":"/primekey-ejbca/Installation/Provider-setup"}}');var s=t(74848),i=t(28453),a=t(65537),o=t(79329);const c={sidebar_position:0,title:"Prerequisites - Keyfactor EJBCA and HSMs",sidebar_label:"Prerequisites",description:"Discover how the Keyfactor and PrimeKey merger enhances machine identity management with end-to-end PKI solutions. Integrating Keyfactor's automation with PrimeKey\u2019s EJBCA software, this robust platform supports IoT, DevOps, and enterprise environments. Secure your PKI with Securosys HSMs for strong compliance and key protection.",keywords:["cloud hsm","hsm key management","hsm cloud","hsm as a service","cloud based hsm","hsm digital signature","hsm services","hsm service","what is cloud hsm","hsm signing","hsm pki","hsm encryption","code signing hsm","hsm key","code signing service","hsm code signing","cloud code signing","cloud encryption key management","cloud hardware security module","cloudhsm vs kms","code signing certificate","key management hsm","microsoft encryption key management","hsm aws","document signing services","code signing","hsm providers","code signing as a service","aws cloudhsm documentation","hsm pricing"]},u="Prerequisites",l={},d=[{value:"HSM Setup and Configuration",id:"hsm-setup-and-configuration",level:2}];function h(e){const r={a:"a",admonition:"admonition",h1:"h1",h2:"h2",header:"header",li:"li",p:"p",ul:"ul",...(0,i.R)(),...e.components};return(0,s.jsxs)(s.Fragment,{children:[(0,s.jsx)(r.header,{children:(0,s.jsx)(r.h1,{id:"prerequisites",children:"Prerequisites"})}),"\n",(0,s.jsx)(r.p,{children:"Make sure to adhere to the below listed prerequisites as they are paramount to continuing with the guide."}),"\n",(0,s.jsxs)(r.ul,{children:["\n",(0,s.jsx)(r.li,{children:"OpenJDK 8"}),"\n",(0,s.jsxs)(r.li,{children:[(0,s.jsx)(r.a,{href:"https://www.keyfactor.com/products/ejbca-enterprise/",children:"EJBCA Enterprise"})," 7.x, 8.x or ",(0,s.jsx)(r.a,{href:"https://www.ejbca.org/",children:"EJBCA community edition"})," 6.x, 7.x, 8.x"]}),"\n",(0,s.jsx)(r.li,{children:"JbossEAP 7.2 or newer"}),"\n",(0,s.jsxs)(r.li,{children:["Securosys ",(0,s.jsx)(r.a,{href:"/pkcs/overview",children:"PKCS#11 Provider"})," v1.8.x or newer"]}),"\n",(0,s.jsxs)(r.li,{children:[(0,s.jsx)(r.a,{href:"/cloudhsm/overview",children:"CloudHSM"})," Service (HSM as a Service) or Primus HSM, firmware v2.8.21 or newer"]}),"\n",(0,s.jsx)(r.li,{children:"PKCS#11 API licensed"}),"\n"]}),"\n",(0,s.jsx)(r.h2,{id:"hsm-setup-and-configuration",children:"HSM Setup and Configuration"}),"\n",(0,s.jsxs)(a.A,{groupId:"HSM Setup & Configuration",children:[(0,s.jsxs)(o.A,{value:"HSMaas",label:"Securosys CloudHSM",default:!0,children:[(0,s.jsxs)(r.p,{children:["Securosys ",(0,s.jsx)(r.a,{href:"/cloudhsm/overview",children:"CloudHSM"})," allows almost instant HSM operation by selecting and contracting the different services and options for your Keyfactor/PrimeKey EJBCA.\nFor available service packages and options consult our website ",(0,s.jsx)(r.a,{href:"https://www.securosys.com/en/product/cloudshsm",children:"Securosys CloudHSM Service"})," and ",(0,s.jsx)(r.a,{href:"https://www.securosys.com/en/contact",children:"contact the sales team"}),"."]}),(0,s.jsx)(r.admonition,{type:"warning",children:(0,s.jsx)(r.p,{children:"Ensure the PCKS#11 API is included and activated in your subscription."})})]}),(0,s.jsxs)(o.A,{value:"on-premises",label:"Securosys Primus HSM (on-premises)",default:!0,children:[(0,s.jsxs)(r.p,{children:["Setup the Primus HSM for PKCS#11 usage with the ",(0,s.jsx)(r.a,{href:"/pkcs/Installation/primus_hsm_settings",children:"User Guide"}),"."]}),(0,s.jsx)(r.admonition,{type:"warning",children:(0,s.jsx)(r.p,{children:"Ensure the PCKS#11 API is licensed and activated on your device."})}),(0,s.jsxs)(r.p,{children:["For general or on-premises Primus HSM hardware, HA Cluster setup and operation in FIPS or Common Criteria certified modes, refer to the corresponding ",(0,s.jsx)(r.a,{href:"https://support.securosys.com/external/knowledge-base/article/63",children:"User Guide"})," (account required)."]})]})]})]})}function m(e={}){const{wrapper:r}={...(0,i.R)(),...e.components};return r?(0,s.jsx)(r,{...e,children:(0,s.jsx)(h,{...e})}):h(e)}},28453:(e,r,t)=>{t.d(r,{R:()=>a,x:()=>o});var n=t(96540);const s={},i=n.createContext(s);function a(e){const r=n.useContext(i);return n.useMemo((function(){return"function"==typeof e?e(r):{...r,...e}}),[r,e])}function o(e){let r;return r=e.disableParentContext?"function"==typeof e.components?e.components(s):e.components||s:a(e.components),n.createElement(i.Provider,{value:r},e.children)}},65537:(e,r,t)=>{t.d(r,{A:()=>S});var n=t(96540),s=t(34164),i=t(65627),a=t(56347),o=t(50372),c=t(30604),u=t(11861),l=t(78749);function d(e){return n.Children.toArray(e).filter((e=>"\n"!==e)).map((e=>{if(!e||(0,n.isValidElement)(e)&&function(e){const{props:r}=e;return!!r&&"object"==typeof r&&"value"in r}(e))return e;throw new Error(`Docusaurus error: Bad <Tabs> child <${"string"==typeof e.type?e.type:e.type.name}>: all children of the <Tabs> component should be <TabItem>, and every <TabItem> should have a unique "value" prop.`)}))?.filter(Boolean)??[]}function h(e){const{values:r,children:t}=e;return(0,n.useMemo)((()=>{const e=r??function(e){return d(e).map((e=>{let{props:{value:r,label:t,attributes:n,default:s}}=e;return{value:r,label:t,attributes:n,default:s}}))}(t);return function(e){const r=(0,u.XI)(e,((e,r)=>e.value===r.value));if(r.length>0)throw new Error(`Docusaurus error: Duplicate values "${r.map((e=>e.value)).join(", ")}" found in <Tabs>. Every value needs to be unique.`)}(e),e}),[r,t])}function m(e){let{value:r,tabValues:t}=e;return t.some((e=>e.value===r))}function p(e){let{queryString:r=!1,groupId:t}=e;const s=(0,a.W6)(),i=function(e){let{queryString:r=!1,groupId:t}=e;if("string"==typeof r)return r;if(!1===r)return null;if(!0===r&&!t)throw new Error('Docusaurus error: The <Tabs> component groupId prop is required if queryString=true, because this value is used as the search param name. You can also provide an explicit value such as queryString="my-search-param".');return t??null}({queryString:r,groupId:t});return[(0,c.aZ)(i),(0,n.useCallback)((e=>{if(!i)return;const r=new URLSearchParams(s.location.search);r.set(i,e),s.replace({...s.location,search:r.toString()})}),[i,s])]}function f(e){const{defaultValue:r,queryString:t=!1,groupId:s}=e,i=h(e),[a,c]=(0,n.useState)((()=>function(e){let{defaultValue:r,tabValues:t}=e;if(0===t.length)throw new Error("Docusaurus error: the <Tabs> component requires at least one <TabItem> children component");if(r){if(!m({value:r,tabValues:t}))throw new Error(`Docusaurus error: The <Tabs> has a defaultValue "${r}" but none of its children has the corresponding value. Available values are: ${t.map((e=>e.value)).join(", ")}. If you intend to show no default tab, use defaultValue={null} instead.`);return r}const n=t.find((e=>e.default))??t[0];if(!n)throw new Error("Unexpected error: 0 tabValues");return n.value}({defaultValue:r,tabValues:i}))),[u,d]=p({queryString:t,groupId:s}),[f,g]=function(e){let{groupId:r}=e;const t=function(e){return e?`docusaurus.tab.${e}`:null}(r),[s,i]=(0,l.Dv)(t);return[s,(0,n.useCallback)((e=>{t&&i.set(e)}),[t,i])]}({groupId:s}),y=(()=>{const e=u??f;return m({value:e,tabValues:i})?e:null})();(0,o.A)((()=>{y&&c(y)}),[y]);return{selectedValue:a,selectValue:(0,n.useCallback)((e=>{if(!m({value:e,tabValues:i}))throw new Error(`Can't select invalid tab value=${e}`);c(e),d(e),g(e)}),[d,g,i]),tabValues:i}}var g=t(9136);const y={tabList:"tabList__CuJ",tabItem:"tabItem_LNqP"};var v=t(74848);function b(e){let{className:r,block:t,selectedValue:n,selectValue:a,tabValues:o}=e;const c=[],{blockElementScrollPositionUntilNextRender:u}=(0,i.a_)(),l=e=>{const r=e.currentTarget,t=c.indexOf(r),s=o[t].value;s!==n&&(u(r),a(s))},d=e=>{let r=null;switch(e.key){case"Enter":l(e);break;case"ArrowRight":{const t=c.indexOf(e.currentTarget)+1;r=c[t]??c[0];break}case"ArrowLeft":{const t=c.indexOf(e.currentTarget)-1;r=c[t]??c[c.length-1];break}}r?.focus()};return(0,v.jsx)("ul",{role:"tablist","aria-orientation":"horizontal",className:(0,s.A)("tabs",{"tabs--block":t},r),children:o.map((e=>{let{value:r,label:t,attributes:i}=e;return(0,v.jsx)("li",{role:"tab",tabIndex:n===r?0:-1,"aria-selected":n===r,ref:e=>{c.push(e)},onKeyDown:d,onClick:l,...i,className:(0,s.A)("tabs__item",y.tabItem,i?.className,{"tabs__item--active":n===r}),children:t??r},r)}))})}function w(e){let{lazy:r,children:t,selectedValue:i}=e;const a=(Array.isArray(t)?t:[t]).filter(Boolean);if(r){const e=a.find((e=>e.props.value===i));return e?(0,n.cloneElement)(e,{className:(0,s.A)("margin-top--md",e.props.className)}):null}return(0,v.jsx)("div",{className:"margin-top--md",children:a.map(((e,r)=>(0,n.cloneElement)(e,{key:r,hidden:e.props.value!==i})))})}function x(e){const r=f(e);return(0,v.jsxs)("div",{className:(0,s.A)("tabs-container",y.tabList),children:[(0,v.jsx)(b,{...r,...e}),(0,v.jsx)(w,{...r,...e})]})}function S(e){const r=(0,g.A)();return(0,v.jsx)(x,{...e,children:d(e.children)},String(r))}},79329:(e,r,t)=>{t.d(r,{A:()=>a});t(96540);var n=t(34164);const s={tabItem:"tabItem_Ymn6"};var i=t(74848);function a(e){let{children:r,hidden:t,className:a}=e;return(0,i.jsx)("div",{role:"tabpanel",className:(0,n.A)(s.tabItem,a),hidden:t,children:r})}}}]);