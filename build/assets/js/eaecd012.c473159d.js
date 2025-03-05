"use strict";(self.webpackChunksecurosys_docs=self.webpackChunksecurosys_docs||[]).push([[27880],{28453:(e,t,r)=>{r.d(t,{R:()=>a,x:()=>o});var n=r(96540);const i={},s=n.createContext(i);function a(e){const t=n.useContext(s);return n.useMemo((function(){return"function"==typeof e?e(t):{...t,...e}}),[t,e])}function o(e){let t;return t=e.disableParentContext?"function"==typeof e.components?e.components(i):e.components||i:a(e.components),n.createElement(s.Provider,{value:t},e.children)}},65537:(e,t,r)=>{r.d(t,{A:()=>x});var n=r(96540),i=r(34164),s=r(65627),a=r(56347),o=r(50372),l=r(30604),u=r(11861),c=r(78749);function d(e){return n.Children.toArray(e).filter((e=>"\n"!==e)).map((e=>{if(!e||(0,n.isValidElement)(e)&&function(e){const{props:t}=e;return!!t&&"object"==typeof t&&"value"in t}(e))return e;throw new Error(`Docusaurus error: Bad <Tabs> child <${"string"==typeof e.type?e.type:e.type.name}>: all children of the <Tabs> component should be <TabItem>, and every <TabItem> should have a unique "value" prop.`)}))?.filter(Boolean)??[]}function h(e){const{values:t,children:r}=e;return(0,n.useMemo)((()=>{const e=t??function(e){return d(e).map((e=>{let{props:{value:t,label:r,attributes:n,default:i}}=e;return{value:t,label:r,attributes:n,default:i}}))}(r);return function(e){const t=(0,u.XI)(e,((e,t)=>e.value===t.value));if(t.length>0)throw new Error(`Docusaurus error: Duplicate values "${t.map((e=>e.value)).join(", ")}" found in <Tabs>. Every value needs to be unique.`)}(e),e}),[t,r])}function p(e){let{value:t,tabValues:r}=e;return r.some((e=>e.value===t))}function g(e){let{queryString:t=!1,groupId:r}=e;const i=(0,a.W6)(),s=function(e){let{queryString:t=!1,groupId:r}=e;if("string"==typeof t)return t;if(!1===t)return null;if(!0===t&&!r)throw new Error('Docusaurus error: The <Tabs> component groupId prop is required if queryString=true, because this value is used as the search param name. You can also provide an explicit value such as queryString="my-search-param".');return r??null}({queryString:t,groupId:r});return[(0,l.aZ)(s),(0,n.useCallback)((e=>{if(!s)return;const t=new URLSearchParams(i.location.search);t.set(s,e),i.replace({...i.location,search:t.toString()})}),[s,i])]}function m(e){const{defaultValue:t,queryString:r=!1,groupId:i}=e,s=h(e),[a,l]=(0,n.useState)((()=>function(e){let{defaultValue:t,tabValues:r}=e;if(0===r.length)throw new Error("Docusaurus error: the <Tabs> component requires at least one <TabItem> children component");if(t){if(!p({value:t,tabValues:r}))throw new Error(`Docusaurus error: The <Tabs> has a defaultValue "${t}" but none of its children has the corresponding value. Available values are: ${r.map((e=>e.value)).join(", ")}. If you intend to show no default tab, use defaultValue={null} instead.`);return t}const n=r.find((e=>e.default))??r[0];if(!n)throw new Error("Unexpected error: 0 tabValues");return n.value}({defaultValue:t,tabValues:s}))),[u,d]=g({queryString:r,groupId:i}),[m,f]=function(e){let{groupId:t}=e;const r=function(e){return e?`docusaurus.tab.${e}`:null}(t),[i,s]=(0,c.Dv)(r);return[i,(0,n.useCallback)((e=>{r&&s.set(e)}),[r,s])]}({groupId:i}),y=(()=>{const e=u??m;return p({value:e,tabValues:s})?e:null})();(0,o.A)((()=>{y&&l(y)}),[y]);return{selectedValue:a,selectValue:(0,n.useCallback)((e=>{if(!p({value:e,tabValues:s}))throw new Error(`Can't select invalid tab value=${e}`);l(e),d(e),f(e)}),[d,f,s]),tabValues:s}}var f=r(9136);const y={tabList:"tabList__CuJ",tabItem:"tabItem_LNqP"};var S=r(74848);function b(e){let{className:t,block:r,selectedValue:n,selectValue:a,tabValues:o}=e;const l=[],{blockElementScrollPositionUntilNextRender:u}=(0,s.a_)(),c=e=>{const t=e.currentTarget,r=l.indexOf(t),i=o[r].value;i!==n&&(u(t),a(i))},d=e=>{let t=null;switch(e.key){case"Enter":c(e);break;case"ArrowRight":{const r=l.indexOf(e.currentTarget)+1;t=l[r]??l[0];break}case"ArrowLeft":{const r=l.indexOf(e.currentTarget)-1;t=l[r]??l[l.length-1];break}}t?.focus()};return(0,S.jsx)("ul",{role:"tablist","aria-orientation":"horizontal",className:(0,i.A)("tabs",{"tabs--block":r},t),children:o.map((e=>{let{value:t,label:r,attributes:s}=e;return(0,S.jsx)("li",{role:"tab",tabIndex:n===t?0:-1,"aria-selected":n===t,ref:e=>{l.push(e)},onKeyDown:d,onClick:c,...s,className:(0,i.A)("tabs__item",y.tabItem,s?.className,{"tabs__item--active":n===t}),children:r??t},t)}))})}function v(e){let{lazy:t,children:r,selectedValue:s}=e;const a=(Array.isArray(r)?r:[r]).filter(Boolean);if(t){const e=a.find((e=>e.props.value===s));return e?(0,n.cloneElement)(e,{className:(0,i.A)("margin-top--md",e.props.className)}):null}return(0,S.jsx)("div",{className:"margin-top--md",children:a.map(((e,t)=>(0,n.cloneElement)(e,{key:t,hidden:e.props.value!==s})))})}function w(e){const t=m(e);return(0,S.jsxs)("div",{className:(0,i.A)("tabs-container",y.tabList),children:[(0,S.jsx)(b,{...t,...e}),(0,S.jsx)(v,{...t,...e})]})}function x(e){const t=(0,f.A)();return(0,S.jsx)(w,{...e,children:d(e.children)},String(t))}},79329:(e,t,r)=>{r.d(t,{A:()=>a});r(96540);var n=r(34164);const i={tabItem:"tabItem_Ymn6"};var s=r(74848);function a(e){let{children:t,hidden:r,className:a}=e;return(0,s.jsx)("div",{role:"tabpanel",className:(0,n.A)(i.tabItem,a),hidden:r,children:t})}},87454:(e,t,r)=>{r.r(t),r.d(t,{assets:()=>l,contentTitle:()=>o,default:()=>d,frontMatter:()=>a,metadata:()=>n,toc:()=>u});const n=JSON.parse('{"id":"overview","title":"Fortinet FortiGate - HSM Integration Guide","description":"Fortinet FortiGate Next Gen Firewall (NGFW) with Securosys Hardware Security Modules (HSMs).","source":"@site/fortigate/overview.md","sourceDirName":".","slug":"/overview","permalink":"/fortigate/overview","draft":false,"unlisted":false,"tags":[],"version":"current","lastUpdatedAt":1741162132000,"sidebarPosition":0,"frontMatter":{"sidebar_position":0,"title":"Fortinet FortiGate - HSM Integration Guide","sidebar_label":"Overview","description":"Fortinet FortiGate Next Gen Firewall (NGFW) with Securosys Hardware Security Modules (HSMs).","keywords":["fortinet","fortigate","firewall","hsm clusters","integration","primus hsm","securosys"],"grid_title":"Fortinet FortiGate","grid_search_tags":["PKCS#11","Network security"],"grid_description":"Offload cryptographic key management to a tamper-resistant environment. The HSM securely generates, stores, and manages private keys for SSL/TLS inspection, ensuring keys remain protected. Securosys HSMs, available as on-premises (Primus HSM) and cloud-based (CloudHSM) solutions, offer scalability and ease of operation.","grid_categories":["Network Security","Fortinet"],"grid_tile_logoUrl":"/img/company_logo/fortinet.webp"},"sidebar":"tutorialSidebar","next":{"title":"Quickstart","permalink":"/fortigate/quickstart"}}');var i=r(74848),s=r(28453);r(65537),r(79329);const a={sidebar_position:0,title:"Fortinet FortiGate - HSM Integration Guide",sidebar_label:"Overview",description:"Fortinet FortiGate Next Gen Firewall (NGFW) with Securosys Hardware Security Modules (HSMs).",keywords:["fortinet","fortigate","firewall","hsm clusters","integration","primus hsm","securosys"],grid_title:"Fortinet FortiGate",grid_search_tags:["PKCS#11","Network security"],grid_description:"Offload cryptographic key management to a tamper-resistant environment. The HSM securely generates, stores, and manages private keys for SSL/TLS inspection, ensuring keys remain protected. Securosys HSMs, available as on-premises (Primus HSM) and cloud-based (CloudHSM) solutions, offer scalability and ease of operation.",grid_categories:["Network Security","Fortinet"],grid_tile_logoUrl:"/img/company_logo/fortinet.webp"},o="Fortinet FortiGate",l={},u=[{value:"HSM Integration Guide",id:"hsm-integration-guide",level:3},{value:"Target Audience",id:"target-audience",level:2},{value:"Support Contact",id:"support-contact",level:2},{value:"Getting Started with FortiGate &amp; Securosy HSMs",id:"getting-started-with-fortigate--securosy-hsms",level:2}];function c(e){const t={a:"a",h1:"h1",h2:"h2",h3:"h3",header:"header",img:"img",li:"li",p:"p",strong:"strong",ul:"ul",...(0,s.R)(),...e.components};return(0,i.jsxs)(i.Fragment,{children:[(0,i.jsx)(t.header,{children:(0,i.jsx)(t.h1,{id:"fortinet-fortigate",children:"Fortinet FortiGate"})}),"\n",(0,i.jsx)(t.h3,{id:"hsm-integration-guide",children:"HSM Integration Guide"}),"\n",(0,i.jsxs)(t.p,{children:["The integration of Securosys HSMs with ",(0,i.jsx)(t.strong,{children:"Fortinet FortiGate Next Generation Firewall (NGFW)"})," enhances security by offloading critical cryptographic key data to a tamper-resistant environment."]}),"\n",(0,i.jsx)(t.p,{children:"The Hardware Security Module (HSM) securely generates, stores, and manages private keys. During SSL/TLS inspection short-lived server certificates are issued using these protected keys on the HSM to decrypt the SSL/TLS encrypted traffic. After inspection and any necessary security remediation by FortiGate, the traffic is re-encrypted before being transmitted to its destination. This approach ensures that private keys used remain secure and are never exposed outside the HSM, thereby significantly reducing the risk of key compromise and bolstering the overall integrity of the security architecture."}),"\n",(0,i.jsx)(t.p,{children:"Securosys Hardware Security Modules (HSMs) are available as:"}),"\n",(0,i.jsxs)(t.ul,{children:["\n",(0,i.jsx)(t.li,{children:"on-premises solutions (Primus HSM) or"}),"\n",(0,i.jsxs)(t.li,{children:["cloud service (CloudHSM). ",(0,i.jsx)(t.a,{href:"/cloudhsm/overview/",children:"CloudHSM"})," minimizes customers\u2019 time for evaluation, setup, operation, redundancy, and maintenance of the HSM infrastructure. Moreover, it is scalable according to customer needs."]}),"\n"]}),"\n",(0,i.jsxs)("figure",{className:"image",children:[(0,i.jsx)(t.p,{children:(0,i.jsx)(t.img,{src:r(99726).A+"",width:"1200",height:"627"})}),(0,i.jsx)("figcaption",{children:(0,i.jsx)(t.p,{children:"FortiGate Securosys Primus HSM integration via built-in PKCS#11 Provider"})})]}),"\n",(0,i.jsxs)(t.p,{children:["The ",(0,i.jsx)(t.strong,{children:"Fortinet FortiGate"})," firewalls have the Primus HSM PKCS#11 Provider built-in allowing to integrate easily Securosys on-premises Primus HSMs or CloudHSM (HSM as a service), enabling the following key benefits:"]}),"\n",(0,i.jsxs)(t.ul,{children:["\n",(0,i.jsxs)(t.li,{children:["\n",(0,i.jsx)(t.p,{children:(0,i.jsx)(t.strong,{children:"Secure Key Generation and Storage"})}),"\n",(0,i.jsx)(t.p,{children:"Ensuring the security of cryptographic keys is critical for any organization. Securosys Primus HSM and CloudHSM provide high-entropy, hardware-based true random number generation and tamper-resistant environment. While FortiGate has built-in key generation and storage capabilities, offloading this task to Securosys HSMs further enhances the security of the SSL/TLS inspection process."}),"\n"]}),"\n",(0,i.jsxs)(t.li,{children:["\n",(0,i.jsx)(t.p,{children:(0,i.jsx)(t.strong,{children:"Compliance with Security Standards"})}),"\n",(0,i.jsx)(t.p,{children:"Being compliant with regulatory requirements such as PCI-DSS, HIPAA, and GDPR demands rigorous control over cryptographic key management. Integrating FortiGate with Securosys Primus HSM or CloudHSM enables centralized management of keys and certificates on FIPS 140-2 Level 3 and Common Criteria EAL4+ certified hardware. This integration helps mitigate risks associated with key compromise and mismanagement, ensuring adherence to these stringent regulations."}),"\n"]}),"\n"]}),"\n",(0,i.jsx)(t.h2,{id:"target-audience",children:"Target Audience"}),"\n",(0,i.jsx)(t.p,{children:"This document is intended for Securosys Primus HSM or CloudHSM users and administrators and IT professionals in charge of FortiGate administration."}),"\n",(0,i.jsx)(t.p,{children:"For on-premises HSM deployed operation administrative skills are required for Securosys Primus HSMs."}),"\n",(0,i.jsx)(t.h2,{id:"support-contact",children:"Support Contact"}),"\n",(0,i.jsxs)(t.p,{children:["If you encounter a problem while installing/configuring the PKCS#11 provider or\nintegrating the HSM with FortiGate, make sure that you have read the\nreferenced documentation. If you cannot resolve the issue, please\ncontact Securosys Customer Support. For specific requests regarding\nSecurosys integration with Fortigate, the Securosys\nSupport Portal is reachable under ",(0,i.jsx)(t.a,{href:"https://support.securosys.com",children:"https://support.securosys.com"}),"."]}),"\n",(0,i.jsx)(t.h2,{id:"getting-started-with-fortigate--securosy-hsms",children:"Getting Started with FortiGate & Securosy HSMs"}),"\n",(0,i.jsx)(t.p,{children:"For a smooth start integrating your Primus HSM with FortiGate:"}),"\n",(0,i.jsxs)(t.ul,{children:["\n",(0,i.jsxs)(t.li,{children:["Consult the ",(0,i.jsx)(t.a,{href:"/fortigate/quickstart",children:"Quickstart"})," section for a comprehensive task listing."]}),"\n",(0,i.jsxs)(t.li,{children:["For detailed installation and configuration instructions, follow the ",(0,i.jsx)(t.a,{href:"/fortigate/Installation/prerequisites",children:"Installation"})," section."]}),"\n",(0,i.jsxs)(t.li,{children:["Try ",(0,i.jsx)(t.a,{href:"https://cloud.securosys.com/cloudhsm/",children:"Securosys CloudHSM"})," for free."]}),"\n"]})]})}function d(e={}){const{wrapper:t}={...(0,s.R)(),...e.components};return t?(0,i.jsx)(t,{...e,children:(0,i.jsx)(c,{...e})}):c(e)}},99726:(e,t,r)=>{r.d(t,{A:()=>n});const n=r.p+"assets/images/FortiGateIntegration-2-41061abc073dd28b45947b15f6ddc6f4.png"}}]);