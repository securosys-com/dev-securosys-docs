"use strict";(self.webpackChunksecurosys_docs=self.webpackChunksecurosys_docs||[]).push([[36002],{25824:(e,r,n)=>{n.r(r),n.d(r,{assets:()=>c,contentTitle:()=>u,default:()=>m,frontMatter:()=>o,metadata:()=>t,toc:()=>d});const t=JSON.parse('{"id":"Installation/Prerequisites","title":"Installing XKS Proxy for AWS - Prerequisites","description":"Ensure you meet prerequisites before integrating Securosys CloudHSM or Primus HSM with XKS Proxy and AWS KMS including AWS account, VPC, XKS Proxy, and more.","source":"@site/xks/Installation/Prerequisites.md","sourceDirName":"Installation","slug":"/Installation/Prerequisites","permalink":"/xks/Installation/Prerequisites","draft":false,"unlisted":false,"tags":[{"inline":true,"label":"AWS","permalink":"/xks/tags/aws"},{"inline":true,"label":"AWS KMS","permalink":"/xks/tags/aws-kms"},{"inline":true,"label":"XKS","permalink":"/xks/tags/xks"},{"inline":true,"label":"EC2","permalink":"/xks/tags/ec-2"}],"version":"current","lastUpdatedAt":1741162132000,"sidebarPosition":0,"frontMatter":{"sidebar_position":0,"title":"Installing XKS Proxy for AWS - Prerequisites","sidebar_label":"1. Prerequisites","description":"Ensure you meet prerequisites before integrating Securosys CloudHSM or Primus HSM with XKS Proxy and AWS KMS including AWS account, VPC, XKS Proxy, and more.","tags":["AWS","AWS KMS","XKS","EC2"]},"sidebar":"tutorialSidebar","previous":{"title":"Installation","permalink":"/xks/category/installation"},"next":{"title":"2. Install Docker","permalink":"/xks/Installation/ec2-docker-installation"}}');var s=n(74848),a=n(28453),i=n(65537),l=n(79329);const o={sidebar_position:0,title:"Installing XKS Proxy for AWS - Prerequisites",sidebar_label:"1. Prerequisites",description:"Ensure you meet prerequisites before integrating Securosys CloudHSM or Primus HSM with XKS Proxy and AWS KMS including AWS account, VPC, XKS Proxy, and more.",tags:["AWS","AWS KMS","XKS","EC2"]},u="Prerequisites",c={},d=[{value:"AWS KMS &amp; Securosys HSM - Integration Guide",id:"aws-kms--securosys-hsm---integration-guide",level:3},{value:"Securosys Hardware Security Module (HSM):",id:"securosys-hardware-security-module-hsm",level:4},{value:"More content",id:"more-content",level:2}];function h(e){const r={a:"a",admonition:"admonition",h1:"h1",h2:"h2",h3:"h3",h4:"h4",header:"header",li:"li",p:"p",strong:"strong",ul:"ul",...(0,a.R)(),...e.components};return(0,s.jsxs)(s.Fragment,{children:[(0,s.jsx)(r.header,{children:(0,s.jsx)(r.h1,{id:"prerequisites",children:"Prerequisites"})}),"\n",(0,s.jsx)(r.h3,{id:"aws-kms--securosys-hsm---integration-guide",children:"AWS KMS & Securosys HSM - Integration Guide"}),"\n",(0,s.jsx)(r.p,{children:"Before integrating your HSM with Securosys External Key Store (XKS) Proxy and AWS KMS External Key Store, please make sure to fulfill all the necessary requirements listed below:"}),"\n",(0,s.jsxs)(r.ul,{children:["\n",(0,s.jsxs)(r.li,{children:[(0,s.jsx)(r.strong,{children:"AWS"}),":","\n",(0,s.jsxs)(r.ul,{children:["\n",(0,s.jsx)(r.li,{children:"Existing AWS account,"}),"\n",(0,s.jsx)(r.li,{children:"Configured AWS VPC, AWS KMS external key store successfully connected (Example basic VPC configuration guide shown in the document annex)"}),"\n"]}),"\n"]}),"\n",(0,s.jsxs)(r.li,{children:[(0,s.jsx)(r.strong,{children:"Docker"}),": Latest version installed on your host device and configured user permissions. ",(0,s.jsx)(r.a,{href:"/xks/Installation/ec2-docker-installation",children:"View installation guide"})]}),"\n",(0,s.jsxs)(r.li,{children:[(0,s.jsx)(r.strong,{children:"Securosys"}),":","\n",(0,s.jsxs)(r.ul,{children:["\n",(0,s.jsxs)(r.li,{children:[(0,s.jsx)(r.a,{href:"https://support.securosys.com/external",children:"Support Portal"})," account (free), to download the XKS Proxy,"]}),"\n",(0,s.jsxs)(r.li,{children:["Securosys XKS Proxy v1 or newer - ",(0,s.jsx)(r.a,{href:"../downloads",children:"Download (login required)"}),","]}),"\n"]}),"\n"]}),"\n"]}),"\n",(0,s.jsx)(r.h4,{id:"securosys-hardware-security-module-hsm",children:"Securosys Hardware Security Module (HSM):"}),"\n",(0,s.jsxs)(i.A,{groupId:"device-setup",children:[(0,s.jsxs)(l.A,{value:"cloud",label:"Cloud",default:!0,children:[(0,s.jsxs)(r.p,{children:[(0,s.jsx)(r.a,{href:"../../cloudhsm/overview",children:"Securosys CloudHSM"})," offers near-instant Hardware Security Module (HSM) operation by allowing you to select and subscribe to a variety of services and options tailored to your needs."]}),(0,s.jsxs)(r.ul,{children:["\n",(0,s.jsxs)(r.li,{children:[(0,s.jsx)(r.a,{href:"https://cloud.securosys.com/cloudhsm",children:"Subscribe online"})," (Free trial available)"]}),"\n",(0,s.jsxs)(r.li,{children:[(0,s.jsx)(r.a,{href:"https://www.securosys.com/en/contactus",children:"Contact sales"}),"."]}),"\n"]})]}),(0,s.jsxs)(l.A,{value:"on-premises",label:"On-premises",children:[(0,s.jsx)(r.p,{children:"Securosys Primus HSM, firmware v2.8.21, v2.10.5 or newer with JCE API license:"}),(0,s.jsxs)(r.ul,{children:["\n",(0,s.jsx)(r.li,{children:(0,s.jsx)(r.a,{href:"https://www.securosys.com/en/hsm/hsm-overview",children:"Securosys Primus HSM"})}),"\n",(0,s.jsx)(r.li,{children:(0,s.jsx)(r.a,{href:"https://www.securosys.com/en/contact",children:"Contact Sales"})}),"\n"]})]})]}),"\n",(0,s.jsx)(r.admonition,{title:"Load Balancing",type:"tip",children:(0,s.jsxs)(r.p,{children:["It is recommended to establish redundancy in your environment. For more information on Securosys XKS proxy redundancy please refer to AWS documentation ",(0,s.jsx)(r.a,{href:"https://docs.aws.amazon.com/kms/latest/developerguide/vpc-connectivity.html#xks-nlb",children:"Creating a network load balancer"}),"."]})}),"\n",(0,s.jsx)(r.h2,{id:"more-content",children:"More content"}),"\n",(0,s.jsxs)(r.ul,{children:["\n",(0,s.jsxs)(r.li,{children:[(0,s.jsx)(r.a,{href:"../downloads",children:"Download the Securosys XKS Proxy for AWS"})," (login required)"]}),"\n",(0,s.jsx)(r.li,{children:(0,s.jsx)(r.a,{href:"./ec2-docker-installation",children:"Installing Docker on an EC2 instance"})}),"\n",(0,s.jsx)(r.li,{children:(0,s.jsx)(r.a,{href:"/xks/Tutorials/Examples/Example-AWS-KMS",children:"Example - Creation of an XKS in AWS KMS"})}),"\n",(0,s.jsx)(r.li,{children:(0,s.jsx)(r.a,{href:"/xks/Tutorials/Examples/Example-jks",children:"Example - Generating a .jks domain file"})}),"\n"]})]})}function m(e={}){const{wrapper:r}={...(0,a.R)(),...e.components};return r?(0,s.jsx)(r,{...e,children:(0,s.jsx)(h,{...e})}):h(e)}},28453:(e,r,n)=>{n.d(r,{R:()=>i,x:()=>l});var t=n(96540);const s={},a=t.createContext(s);function i(e){const r=t.useContext(a);return t.useMemo((function(){return"function"==typeof e?e(r):{...r,...e}}),[r,e])}function l(e){let r;return r=e.disableParentContext?"function"==typeof e.components?e.components(s):e.components||s:i(e.components),t.createElement(a.Provider,{value:r},e.children)}},65537:(e,r,n)=>{n.d(r,{A:()=>j});var t=n(96540),s=n(34164),a=n(65627),i=n(56347),l=n(50372),o=n(30604),u=n(11861),c=n(78749);function d(e){return t.Children.toArray(e).filter((e=>"\n"!==e)).map((e=>{if(!e||(0,t.isValidElement)(e)&&function(e){const{props:r}=e;return!!r&&"object"==typeof r&&"value"in r}(e))return e;throw new Error(`Docusaurus error: Bad <Tabs> child <${"string"==typeof e.type?e.type:e.type.name}>: all children of the <Tabs> component should be <TabItem>, and every <TabItem> should have a unique "value" prop.`)}))?.filter(Boolean)??[]}function h(e){const{values:r,children:n}=e;return(0,t.useMemo)((()=>{const e=r??function(e){return d(e).map((e=>{let{props:{value:r,label:n,attributes:t,default:s}}=e;return{value:r,label:n,attributes:t,default:s}}))}(n);return function(e){const r=(0,u.XI)(e,((e,r)=>e.value===r.value));if(r.length>0)throw new Error(`Docusaurus error: Duplicate values "${r.map((e=>e.value)).join(", ")}" found in <Tabs>. Every value needs to be unique.`)}(e),e}),[r,n])}function m(e){let{value:r,tabValues:n}=e;return n.some((e=>e.value===r))}function p(e){let{queryString:r=!1,groupId:n}=e;const s=(0,i.W6)(),a=function(e){let{queryString:r=!1,groupId:n}=e;if("string"==typeof r)return r;if(!1===r)return null;if(!0===r&&!n)throw new Error('Docusaurus error: The <Tabs> component groupId prop is required if queryString=true, because this value is used as the search param name. You can also provide an explicit value such as queryString="my-search-param".');return n??null}({queryString:r,groupId:n});return[(0,o.aZ)(a),(0,t.useCallback)((e=>{if(!a)return;const r=new URLSearchParams(s.location.search);r.set(a,e),s.replace({...s.location,search:r.toString()})}),[a,s])]}function x(e){const{defaultValue:r,queryString:n=!1,groupId:s}=e,a=h(e),[i,o]=(0,t.useState)((()=>function(e){let{defaultValue:r,tabValues:n}=e;if(0===n.length)throw new Error("Docusaurus error: the <Tabs> component requires at least one <TabItem> children component");if(r){if(!m({value:r,tabValues:n}))throw new Error(`Docusaurus error: The <Tabs> has a defaultValue "${r}" but none of its children has the corresponding value. Available values are: ${n.map((e=>e.value)).join(", ")}. If you intend to show no default tab, use defaultValue={null} instead.`);return r}const t=n.find((e=>e.default))??n[0];if(!t)throw new Error("Unexpected error: 0 tabValues");return t.value}({defaultValue:r,tabValues:a}))),[u,d]=p({queryString:n,groupId:s}),[x,f]=function(e){let{groupId:r}=e;const n=function(e){return e?`docusaurus.tab.${e}`:null}(r),[s,a]=(0,c.Dv)(n);return[s,(0,t.useCallback)((e=>{n&&a.set(e)}),[n,a])]}({groupId:s}),S=(()=>{const e=u??x;return m({value:e,tabValues:a})?e:null})();(0,l.A)((()=>{S&&o(S)}),[S]);return{selectedValue:i,selectValue:(0,t.useCallback)((e=>{if(!m({value:e,tabValues:a}))throw new Error(`Can't select invalid tab value=${e}`);o(e),d(e),f(e)}),[d,f,a]),tabValues:a}}var f=n(9136);const S={tabList:"tabList__CuJ",tabItem:"tabItem_LNqP"};var b=n(74848);function y(e){let{className:r,block:n,selectedValue:t,selectValue:i,tabValues:l}=e;const o=[],{blockElementScrollPositionUntilNextRender:u}=(0,a.a_)(),c=e=>{const r=e.currentTarget,n=o.indexOf(r),s=l[n].value;s!==t&&(u(r),i(s))},d=e=>{let r=null;switch(e.key){case"Enter":c(e);break;case"ArrowRight":{const n=o.indexOf(e.currentTarget)+1;r=o[n]??o[0];break}case"ArrowLeft":{const n=o.indexOf(e.currentTarget)-1;r=o[n]??o[o.length-1];break}}r?.focus()};return(0,b.jsx)("ul",{role:"tablist","aria-orientation":"horizontal",className:(0,s.A)("tabs",{"tabs--block":n},r),children:l.map((e=>{let{value:r,label:n,attributes:a}=e;return(0,b.jsx)("li",{role:"tab",tabIndex:t===r?0:-1,"aria-selected":t===r,ref:e=>{o.push(e)},onKeyDown:d,onClick:c,...a,className:(0,s.A)("tabs__item",S.tabItem,a?.className,{"tabs__item--active":t===r}),children:n??r},r)}))})}function g(e){let{lazy:r,children:n,selectedValue:a}=e;const i=(Array.isArray(n)?n:[n]).filter(Boolean);if(r){const e=i.find((e=>e.props.value===a));return e?(0,t.cloneElement)(e,{className:(0,s.A)("margin-top--md",e.props.className)}):null}return(0,b.jsx)("div",{className:"margin-top--md",children:i.map(((e,r)=>(0,t.cloneElement)(e,{key:r,hidden:e.props.value!==a})))})}function v(e){const r=x(e);return(0,b.jsxs)("div",{className:(0,s.A)("tabs-container",S.tabList),children:[(0,b.jsx)(y,{...r,...e}),(0,b.jsx)(g,{...r,...e})]})}function j(e){const r=(0,f.A)();return(0,b.jsx)(v,{...e,children:d(e.children)},String(r))}},79329:(e,r,n)=>{n.d(r,{A:()=>i});n(96540);var t=n(34164);const s={tabItem:"tabItem_Ymn6"};var a=n(74848);function i(e){let{children:r,hidden:n,className:i}=e;return(0,a.jsx)("div",{role:"tabpanel",className:(0,t.A)(s.tabItem,i),hidden:n,children:r})}}}]);