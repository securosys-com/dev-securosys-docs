"use strict";(self.webpackChunksecurosys_docs=self.webpackChunksecurosys_docs||[]).push([[57856],{28453:(e,t,i)=>{i.d(t,{R:()=>s,x:()=>a});var r=i(96540);const n={},o=r.createContext(n);function s(e){const t=r.useContext(o);return r.useMemo((function(){return"function"==typeof e?e(t):{...t,...e}}),[t,e])}function a(e){let t;return t=e.disableParentContext?"function"==typeof e.components?e.components(n):e.components||n:s(e.components),r.createElement(o.Provider,{value:t},e.children)}},36280:(e,t,i)=>{i.r(t),i.d(t,{assets:()=>c,contentTitle:()=>a,default:()=>u,frontMatter:()=>s,metadata:()=>r,toc:()=>l});const r=JSON.parse('{"id":"quickstart","title":"Getting Started with FortiGate & Security HSM","description":"Fortinet FortiGate Next Gen Firewall (NGFW) with Securosys Hardware Security Modules (HSMs).","source":"@site/fortigate/quickstart.md","sourceDirName":".","slug":"/quickstart","permalink":"/fortigate/quickstart","draft":false,"unlisted":false,"tags":[],"version":"current","lastUpdatedAt":1741162132000,"sidebarPosition":2,"frontMatter":{"sidebar_position":2,"title":"Getting Started with FortiGate & Security HSM","sidebar_label":"Quickstart","description":"Fortinet FortiGate Next Gen Firewall (NGFW) with Securosys Hardware Security Modules (HSMs).","keywords":["fortinet","fortigate","firewall","hsm clusters","integration","primus hsm","securosys"]},"sidebar":"tutorialSidebar","previous":{"title":"Overview","permalink":"/fortigate/overview"},"next":{"title":"Installation","permalink":"/fortigate/category/installation"}}');var n=i(74848),o=i(28453);const s={sidebar_position:2,title:"Getting Started with FortiGate & Security HSM",sidebar_label:"Quickstart",description:"Fortinet FortiGate Next Gen Firewall (NGFW) with Securosys Hardware Security Modules (HSMs).",keywords:["fortinet","fortigate","firewall","hsm clusters","integration","primus hsm","securosys"]},a="Getting Started with Fortinet FortiGate",c={},l=[{value:"1. Prerequisites",id:"1-prerequisites",level:2},{value:"2. Installing the Primus PKCS#11 Provider",id:"2-installing-the-primus-pkcs11-provider",level:2},{value:"3. Configuring FortiGate with Securosys HSM",id:"3-configuring-fortigate-with-securosys-hsm",level:2},{value:"4. Configuring additional Fortigate components",id:"4-configuring-additional-fortigate-components",level:2}];function d(e){const t={a:"a",admonition:"admonition",h1:"h1",h2:"h2",header:"header",li:"li",ol:"ol",p:"p",...(0,o.R)(),...e.components};return(0,n.jsxs)(n.Fragment,{children:[(0,n.jsx)(t.header,{children:(0,n.jsx)(t.h1,{id:"getting-started-with-fortinet-fortigate",children:"Getting Started with Fortinet FortiGate"})}),"\n",(0,n.jsxs)(t.p,{children:["The quickstart section provides a comprehensive guide outlining the steps necessary to integrate FortiGate with Securosys on-premises Primus HSM or ",(0,n.jsx)(t.a,{href:"/cloudhsm/overview/",children:"CloudHSM"}),"."]}),"\n",(0,n.jsx)(t.h2,{id:"1-prerequisites",children:"1. Prerequisites"}),"\n",(0,n.jsxs)(t.p,{children:["Make sure to adhere to the ",(0,n.jsx)(t.a,{href:"/fortigate/Installation/prerequisites",children:"Prerequisites"})," before continuing with the procedure."]}),"\n",(0,n.jsx)(t.h2,{id:"2-installing-the-primus-pkcs11-provider",children:"2. Installing the Primus PKCS#11 Provider"}),"\n",(0,n.jsxs)(t.admonition,{type:"tip",children:[(0,n.jsx)(t.p,{children:"The Securosys PKCS#11 provider v2.2.4 or later is already integrated into FortiGate (no installation needed)."}),(0,n.jsx)(t.p,{children:"However a valid configuration file and secrets have to be prepared and tested in advance on a separate client machine, to be loaded then to the FortiGate via CLI or GUI."})]}),"\n",(0,n.jsxs)(t.ol,{children:["\n",(0,n.jsx)(t.li,{children:"Install and configure on a client PC the version of Primus PKCS#11 provider corresponding to the FortiGate integrated version."}),"\n",(0,n.jsx)(t.li,{children:"Check for connectivity with your HSM."}),"\n",(0,n.jsx)(t.li,{children:"Use the configuration file, PKCS#11 password and secret to be configured on the FortiGate."}),"\n"]}),"\n",(0,n.jsxs)(t.p,{children:["More details on ",(0,n.jsx)(t.a,{href:"/fortigate/Installation/ProviderPreparation",children:"Securosys PKCS#11 Provider Preparations"}),"."]}),"\n",(0,n.jsx)(t.h2,{id:"3-configuring-fortigate-with-securosys-hsm",children:"3. Configuring FortiGate with Securosys HSM"}),"\n",(0,n.jsxs)(t.p,{children:["Configure the FortiGate firewall to use the on-premises Primus HSM or ",(0,n.jsx)(t.a,{href:"/cloudhsm/overview/",children:"CloudHSM"})," cluster."]}),"\n",(0,n.jsxs)(t.p,{children:["Follow the instructions provided in ",(0,n.jsx)(t.a,{href:"/fortigate/Installation/FortiGate_Configuration",children:"FortiGate Configuration"}),"."]}),"\n",(0,n.jsx)(t.h2,{id:"4-configuring-additional-fortigate-components",children:"4. Configuring additional Fortigate components"}),"\n",(0,n.jsxs)(t.p,{children:["To configure additional components to use the HSM key (CA Certificate Generation, Certificate usage, WAD Deep Inspection in Explicit Proxy Policy, HTTPs Administrative Access), consult the ",(0,n.jsx)(t.a,{href:"https://docs.fortinet.com/",children:"FortiGate documentation"}),"."]})]})}function u(e={}){const{wrapper:t}={...(0,o.R)(),...e.components};return t?(0,n.jsx)(t,{...e,children:(0,n.jsx)(d,{...e})}):d(e)}}}]);