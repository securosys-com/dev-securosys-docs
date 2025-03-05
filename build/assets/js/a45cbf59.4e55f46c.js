"use strict";(self.webpackChunksecurosys_docs=self.webpackChunksecurosys_docs||[]).push([[44218],{25196:(e,t,r)=>{r.r(t),r.d(t,{assets:()=>l,contentTitle:()=>a,default:()=>h,frontMatter:()=>o,metadata:()=>i,toc:()=>c});const i=JSON.parse('{"id":"quickstart","title":"Getting started with Salesforce BYOK","description":"Description overview","source":"@site/salesforce-byok/quickstart.md","sourceDirName":".","slug":"/quickstart","permalink":"/salesforce-byok/quickstart","draft":false,"unlisted":false,"tags":[],"version":"current","lastUpdatedAt":1741162132000,"sidebarPosition":1,"frontMatter":{"sidebar_position":1,"title":"Getting started with Salesforce BYOK","sidebar_label":"Quickstart","description":"Description overview","keywords":["hsm","cloud hsm"]},"sidebar":"tutorialSidebar","previous":{"title":"Overview","permalink":"/salesforce-byok/overview"},"next":{"title":"Installation","permalink":"/salesforce-byok/category/installation"}}');var n=r(74848),s=r(28453);const o={sidebar_position:1,title:"Getting started with Salesforce BYOK",sidebar_label:"Quickstart",description:"Description overview",keywords:["hsm","cloud hsm"]},a="Getting Started with Salesforce BYOK",l={},c=[{value:"Install and Configure Primus Tools",id:"install-and-configure-primus-tools",level:2},{value:"Generate Salesforce BYOK Certificate",id:"generate-salesforce-byok-certificate",level:2},{value:"Generate and Wrap BYOK Key Material",id:"generate-and-wrap-byok-key-material",level:2},{value:"Import BYOK Tenant Secret",id:"import-byok-tenant-secret",level:2}];function d(e){const t={a:"a",admonition:"admonition",code:"code",h1:"h1",h2:"h2",header:"header",li:"li",p:"p",strong:"strong",ul:"ul",...(0,s.R)(),...e.components};return(0,n.jsxs)(n.Fragment,{children:[(0,n.jsx)(t.header,{children:(0,n.jsx)(t.h1,{id:"getting-started-with-salesforce-byok",children:"Getting Started with Salesforce BYOK"})}),"\n",(0,n.jsxs)(t.p,{children:["This Quickstart section provides a comprehensive task listing of the Bring Your Own Key (BYOK) process for Salesforce. For more detailed instructions please consult the ",(0,n.jsx)(t.strong,{children:"Installation"})," section. Visit ",(0,n.jsx)(t.a,{href:"/salesforce-byok/Installation/prerequisites",children:"Prerequisites"})," for the necessary preparations beforehand."]}),"\n",(0,n.jsx)(t.admonition,{type:"note",children:(0,n.jsx)(t.p,{children:"Parameters in this document are shown as an example. Replace these parameters with your own."})}),"\n",(0,n.jsx)(t.h2,{id:"install-and-configure-primus-tools",children:"Install and Configure Primus Tools"}),"\n",(0,n.jsxs)(t.p,{children:["Download, install and configure the ",(0,n.jsx)(t.a,{href:"/primus-tools/overview",children:"Primus Tools"})," on the computer with an established Primus HSM or CloudHSM connection. For more information, visit the ",(0,n.jsx)(t.a,{href:"/primus-tools/Installation/primus-tools-installation",children:"Primus Tools - Installation"})," section."]}),"\n",(0,n.jsx)(t.h2,{id:"generate-salesforce-byok-certificate",children:"Generate Salesforce BYOK Certificate"}),"\n",(0,n.jsxs)(t.p,{children:["To encrypt data in Salesforce with Bring Your Own Key (BYOK) key material, use Salesforce to generate a self-signed certificate. The public key from the certificate will be used to encrypt your key material generated in your Primus HSM or ",(0,n.jsx)(t.a,{href:"../cloudhsm/overview",children:"CloudHSM"}),"."]}),"\n",(0,n.jsx)(t.admonition,{type:"note",children:(0,n.jsx)(t.p,{children:"A 4096-bit RSA key size is required for Salesforce BYOK."})}),"\n",(0,n.jsxs)(t.p,{children:["A CA-signed certificate can also be used. See ",(0,n.jsx)(t.a,{href:"/salesforce-byok/Tutorials/salesforce-byok-certificate",children:"Generate Salesforce BYOK-Compatible Certificate"})," for more information."]}),"\n",(0,n.jsx)(t.h2,{id:"generate-and-wrap-byok-key-material",children:"Generate and Wrap BYOK Key Material"}),"\n",(0,n.jsxs)(t.p,{children:["Convert the exported Salesforce BYOK compatible certificate into either ",(0,n.jsx)(t.code,{children:".pem"})," or ",(0,n.jsx)(t.code,{children:".der"})," format, create a tenant secret key on the HSM and use Primus Tools to export and wrap the BYOK key material with the public key extractred from the BYOK-compatible certificate, generated in the previous chapter."]}),"\n",(0,n.jsx)(t.admonition,{type:"note",children:(0,n.jsxs)(t.p,{children:["The tenant secret must be an HMAC key, use parameter ",(0,n.jsx)(t.code,{children:"HMACSHA256"})," when specifiying the key type."]})}),"\n",(0,n.jsxs)(t.p,{children:["See ",(0,n.jsx)(t.a,{href:"/salesforce-byok/Tutorials/byok-key-material",children:"Generate and Wrap BYOK Key Material"})," for more information."]}),"\n",(0,n.jsx)(t.h2,{id:"import-byok-tenant-secret",children:"Import BYOK Tenant Secret"}),"\n",(0,n.jsx)(t.p,{children:"Import the generated BYOK-compatible key material files into Salesforce. If desired, opt out of Salesforce key derivation."}),"\n",(0,n.jsxs)(t.p,{children:["See ",(0,n.jsx)(t.a,{href:"/salesforce-byok/Tutorials/upload-byok-tenant-secret",children:"Import BYOK Key Material"})," for more information."]}),"\n",(0,n.jsxs)(t.admonition,{title:"Ready to try ?",type:"tip",children:[(0,n.jsxs)(t.p,{children:["Enjoy a ",(0,n.jsx)(t.strong,{children:"3-month"})," free trial of CloudHSM Sandbox, compatible with Salesforce BYOK."]}),(0,n.jsxs)(t.ul,{children:["\n",(0,n.jsxs)(t.li,{children:[(0,n.jsx)(t.a,{href:"https://cloud.securosys.com/sign-up",children:"Sign-up"})," and download the HSM credentials within minutes."]}),"\n",(0,n.jsxs)(t.li,{children:["Browse ",(0,n.jsx)(t.a,{href:"../cloudhsm/Packages/sandbox",children:"CloudHSM Sandbox service description"})]}),"\n"]})]})]})}function h(e={}){const{wrapper:t}={...(0,s.R)(),...e.components};return t?(0,n.jsx)(t,{...e,children:(0,n.jsx)(d,{...e})}):d(e)}},28453:(e,t,r)=>{r.d(t,{R:()=>o,x:()=>a});var i=r(96540);const n={},s=i.createContext(n);function o(e){const t=i.useContext(s);return i.useMemo((function(){return"function"==typeof e?e(t):{...t,...e}}),[t,e])}function a(e){let t;return t=e.disableParentContext?"function"==typeof e.components?e.components(n):e.components||n:o(e.components),i.createElement(s.Provider,{value:t},e.children)}}}]);