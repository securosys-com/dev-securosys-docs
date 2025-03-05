"use strict";(self.webpackChunksecurosys_docs=self.webpackChunksecurosys_docs||[]).push([[25259],{772:(e,n,r)=>{r.d(n,{A:()=>s});const s=r.p+"assets/images/KSP-Configuration-61aff0dd6c219205532538f4e04552ae.png"},28453:(e,n,r)=>{r.d(n,{R:()=>o,x:()=>d});var s=r(96540);const i={},t=s.createContext(i);function o(e){const n=s.useContext(t);return s.useMemo((function(){return"function"==typeof e?e(n):{...n,...e}}),[n,e])}function d(e){let n;return n=e.disableParentContext?"function"==typeof e.components?e.components(i):e.components||i:o(e.components),s.createElement(t.Provider,{value:n},e.children)}},36589:(e,n,r)=>{r.d(n,{A:()=>s});const s=r.p+"assets/images/NewHSM-KSP-Configuration-4d1560a1da6e544772352b070b6473b6.png"},42067:(e,n,r)=>{r.d(n,{A:()=>s});const s=r.p+"assets/images/KSP-Configuration-HSM_Management2-7660f04553d366cb4803b73598500156.png"},51535:(e,n,r)=>{r.r(n),r.d(n,{assets:()=>c,contentTitle:()=>d,default:()=>h,frontMatter:()=>o,metadata:()=>s,toc:()=>l});const s=JSON.parse('{"id":"Installation/Configuring","title":"Configuring Microsoft CNG for Security HSM","description":"Configuring Microsoft CNG for Securosys Hardware Security Modules (HSMs)","source":"@site/mscng/Installation/Configuring.md","sourceDirName":"Installation","slug":"/Installation/Configuring","permalink":"/mscng/Installation/Configuring","draft":false,"unlisted":false,"tags":[],"version":"current","lastUpdatedAt":1741162132000,"sidebarPosition":5,"frontMatter":{"sidebar_position":5,"title":"Configuring Microsoft CNG for Security HSM","sidebar_label":"Configuring the HSM","description":"Configuring Microsoft CNG for Securosys Hardware Security Modules (HSMs)","keywords":["hsm","cloud hsm"]},"sidebar":"tutorialSidebar","previous":{"title":"Installing Multiple Provider Instances","permalink":"/mscng/Installation/installation-multi"},"next":{"title":"Updating","permalink":"/mscng/Installation/Updating-Upgrading"}}');var i=r(74848),t=r(28453);const o={sidebar_position:5,title:"Configuring Microsoft CNG for Security HSM",sidebar_label:"Configuring the HSM",description:"Configuring Microsoft CNG for Securosys Hardware Security Modules (HSMs)",keywords:["hsm","cloud hsm"]},d="Configuring the Microsoft CNG/KSP Provider",c={},l=[{value:"Manage the Hardware Security Module (HSM)",id:"manage-the-hardware-security-module-hsm",level:2},{value:"Global Parameters",id:"global-parameters",level:2},{value:"Access Restrictions",id:"access-restrictions",level:2}];function a(e){const n={a:"a",code:"code",em:"em",h1:"h1",h2:"h2",header:"header",img:"img",li:"li",ol:"ol",p:"p",strong:"strong",table:"table",tbody:"tbody",td:"td",th:"th",thead:"thead",tr:"tr",...(0,t.R)(),...e.components};return(0,i.jsxs)(i.Fragment,{children:[(0,i.jsx)(n.header,{children:(0,i.jsx)(n.h1,{id:"configuring-the-microsoft-cngksp-provider",children:"Configuring the Microsoft CNG/KSP Provider"})}),"\n",(0,i.jsx)(n.h2,{id:"manage-the-hardware-security-module-hsm",children:"Manage the Hardware Security Module (HSM)"}),"\n",(0,i.jsx)(n.p,{children:"Each instance of the Primus CNG/KSP Provider addresses a single partition within a redundant Primus HSM cluster. Different partitions or different non-redundant HSM partitions require a dedicated provider instance."}),"\n",(0,i.jsxs)(n.ol,{children:["\n",(0,i.jsxs)(n.li,{children:["\n",(0,i.jsxs)(n.p,{children:["Run the Securosys ",(0,i.jsx)(n.strong,{children:(0,i.jsx)(n.code,{children:"Key Storage Provider Configuration"})})," tool from the Windows menu:"]}),"\n"]}),"\n",(0,i.jsxs)(n.li,{children:["\n",(0,i.jsxs)(n.p,{children:["From the ",(0,i.jsx)(n.em,{children:"Provider"})," drop-down list, select the provider instance to configure\n(in case multiple instances are installed)."]}),"\n"]}),"\n",(0,i.jsxs)(n.li,{children:["\n",(0,i.jsx)(n.p,{children:"Add all the redundant HSM connections of the cluster with their fallback priority."}),"\n"]}),"\n"]}),"\n",(0,i.jsx)(n.p,{children:(0,i.jsx)(n.img,{src:r(772).A+"",width:"588",height:"444"})}),"\n",(0,i.jsxs)(n.ol,{start:"4",children:["\n",(0,i.jsxs)(n.li,{children:["\n",(0,i.jsxs)(n.p,{children:["Click ",(0,i.jsx)(n.strong,{children:(0,i.jsx)(n.code,{children:"New"})}),"  to add a new HSM connection."]}),"\n"]}),"\n",(0,i.jsxs)(n.li,{children:["\n",(0,i.jsxs)(n.p,{children:["Fill in all the required parameters, user partition credentials and, if required, the proxy credentials (e.g. for CloudHSM), provided by your Security Officer or your CloudHSM service provider. You can also refer to ",(0,i.jsx)(n.a,{href:"/connectivity-details/cloudhsm-connectivity-details",children:"this section"}),"."]}),"\n"]}),"\n"]}),"\n",(0,i.jsx)(n.p,{children:(0,i.jsx)(n.img,{src:r(36589).A+"",width:"535",height:"541"})}),"\n",(0,i.jsxs)(n.table,{children:[(0,i.jsx)(n.thead,{children:(0,i.jsxs)(n.tr,{children:[(0,i.jsx)(n.th,{children:"Parameter"}),(0,i.jsx)(n.th,{children:"Description"})]})}),(0,i.jsxs)(n.tbody,{children:[(0,i.jsxs)(n.tr,{children:[(0,i.jsx)(n.td,{children:(0,i.jsx)(n.strong,{children:(0,i.jsx)(n.code,{children:"Identifier"})})}),(0,i.jsx)(n.td,{children:"Your HSM connection description. It is recommended to include a hint to your user/partition name for later reference, as the Username is later invisible."})]}),(0,i.jsxs)(n.tr,{children:[(0,i.jsx)(n.td,{children:(0,i.jsx)(n.strong,{children:(0,i.jsx)(n.code,{children:"Device Name"})})}),(0,i.jsx)(n.td,{children:"Will be read from the device after successful connection"})]}),(0,i.jsxs)(n.tr,{children:[(0,i.jsx)(n.td,{children:(0,i.jsx)(n.strong,{children:(0,i.jsx)(n.code,{children:"Hostname"})})}),(0,i.jsx)(n.td,{children:"Hostname or IP address of your HSM connection point"})]}),(0,i.jsxs)(n.tr,{children:[(0,i.jsx)(n.td,{children:(0,i.jsx)(n.strong,{children:(0,i.jsx)(n.code,{children:"Port Number"})})}),(0,i.jsxs)(n.td,{children:["TCP connection port number of your HSM connection point (CNG default: 2320, Clouds HSM: 2320, developer program: 2420; see ",(0,i.jsx)(n.a,{href:"/connectivity-details/cloudhsm-connectivity-details",children:"Default HSM Connection Parameters"}),")"]})]}),(0,i.jsxs)(n.tr,{children:[(0,i.jsx)(n.td,{children:(0,i.jsx)(n.strong,{children:(0,i.jsx)(n.code,{children:"Priority"})})}),(0,i.jsx)(n.td,{children:"Priority in selecting a redundant HSM. The smaller value, the higher priority. Random selection for equal priority."})]}),(0,i.jsxs)(n.tr,{children:[(0,i.jsx)(n.td,{children:(0,i.jsx)(n.strong,{children:(0,i.jsx)(n.code,{children:"HSM Credentials"})})}),(0,i.jsx)(n.td,{children:"Username and Password of your HSM partition (setup password) (as provided by your security officer or your Clouds HSM service provider)"})]}),(0,i.jsxs)(n.tr,{children:[(0,i.jsx)(n.td,{children:(0,i.jsx)(n.strong,{children:(0,i.jsx)(n.code,{children:"Proxy Credentials"})})}),(0,i.jsx)(n.td,{children:"Username and Password of your HSM proxy (service user) (as provided by your security officer or your Clouds HSM service provider)"})]})]})]}),"\n",(0,i.jsxs)(n.ol,{start:"6",children:["\n",(0,i.jsx)(n.li,{children:"Configure all your further redundant HSM connections (and provider instances) in the same way."}),"\n"]}),"\n",(0,i.jsxs)(n.p,{children:["Click ",(0,i.jsx)(n.strong,{children:(0,i.jsx)(n.code,{children:"Test all Connections"})})," to verify connectivity to your HSMs and to retrieve the permanent secret (if not done previously)."]}),"\n",(0,i.jsx)(n.p,{children:(0,i.jsx)(n.img,{src:r(97989).A+"",width:"587",height:"208"})}),"\n",(0,i.jsx)(n.p,{children:"The status indicators should change to green after successful connection.\nThe CNG/KSP Provider is now ready for use by the applications."}),"\n",(0,i.jsx)(n.p,{children:(0,i.jsx)(n.img,{src:r(42067).A+"",width:"586",height:"374"})}),"\n",(0,i.jsx)(n.h2,{id:"global-parameters",children:"Global Parameters"}),"\n",(0,i.jsxs)(n.ol,{children:["\n",(0,i.jsx)(n.li,{children:'If requested by your HSM administrato, adjust the "Global Parameters". The following screenshot shows the default values after installation:'}),"\n"]}),"\n",(0,i.jsx)(n.p,{children:(0,i.jsx)(n.img,{src:r(55639).A+"",width:"588",height:"255"})}),"\n",(0,i.jsxs)(n.table,{children:[(0,i.jsx)(n.thead,{children:(0,i.jsxs)(n.tr,{children:[(0,i.jsx)(n.th,{children:"Parameter"}),(0,i.jsx)(n.th,{children:"Description"})]})}),(0,i.jsxs)(n.tbody,{children:[(0,i.jsxs)(n.tr,{children:[(0,i.jsx)(n.td,{children:(0,i.jsx)(n.strong,{children:(0,i.jsx)(n.code,{children:"Connection retry interval [2000 ms]"})})}),(0,i.jsx)(n.td,{children:"The list of configured HSMs is processed once until a connection can be established to an HSM. If the connection is then interrupted or no connection can be established to any HSM, a new run is started. To avoid constant runs and thus high processor load in the event of an error, a pause is inserted between two runs. The duration of the pause is defined by the specified value."})]}),(0,i.jsxs)(n.tr,{children:[(0,i.jsx)(n.td,{children:(0,i.jsx)(n.strong,{children:(0,i.jsx)(n.code,{children:"Timeout for access to the shared configuration [5000 ms]"})})}),(0,i.jsx)(n.td,{children:"Timeout for serialized access to shared HSM configuration as multiple instances can run in parallel."})]}),(0,i.jsxs)(n.tr,{children:[(0,i.jsx)(n.td,{children:(0,i.jsx)(n.strong,{children:(0,i.jsx)(n.code,{children:"Timeout for logging on to an HSM [120000 ms]"})})}),(0,i.jsx)(n.td,{children:"Timeout for the entire logon process to an HSM, i.e. from Hello to the successful start of line encryption."})]}),(0,i.jsxs)(n.tr,{children:[(0,i.jsx)(n.td,{children:(0,i.jsx)(n.strong,{children:(0,i.jsx)(n.code,{children:"Timeout for triggering a Keep Alive message [30000 ms]"})})}),(0,i.jsx)(n.td,{children:"Idle time to trigger a keep alive message."})]}),(0,i.jsxs)(n.tr,{children:[(0,i.jsx)(n.td,{}),(0,i.jsx)(n.td,{})]}),(0,i.jsxs)(n.tr,{children:[(0,i.jsx)(n.td,{children:(0,i.jsx)(n.strong,{children:(0,i.jsx)(n.code,{children:"Timeout for waiting for a Keep Alive response [5000 ms]"})})}),(0,i.jsx)(n.td,{children:"Maximum time the provider waits for the keep alive response from an HSM"})]}),(0,i.jsxs)(n.tr,{children:[(0,i.jsx)(n.td,{children:(0,i.jsx)(n.strong,{children:(0,i.jsx)(n.code,{children:"Timeout for TCP connection setup to an HSM [1000 ms]"})})}),(0,i.jsx)(n.td,{children:"Timeout overriding the default TCP connection timeout (introduced with v1.43.1). Reduce this timeout if your HSMs are connected locally (not using satellite lines)."})]}),(0,i.jsxs)(n.tr,{children:[(0,i.jsx)(n.td,{children:(0,i.jsx)(n.strong,{children:(0,i.jsx)(n.code,{children:"Default timeout for an HSM command [120000 ms]"})})}),(0,i.jsx)(n.td,{children:"Maximum time the provider waits for a response to all other commands."})]})]})]}),"\n",(0,i.jsx)(n.h2,{id:"access-restrictions",children:"Access Restrictions"}),"\n",(0,i.jsxs)(n.p,{children:["In case of required ",(0,i.jsx)(n.strong,{children:"Registry Access Hardening"}),", please proceed according to ",(0,i.jsx)(n.a,{href:"/mscng/Tutorial/Hardening",children:"this chapter"}),"."]})]})}function h(e={}){const{wrapper:n}={...(0,t.R)(),...e.components};return n?(0,i.jsx)(n,{...e,children:(0,i.jsx)(a,{...e})}):a(e)}},55639:(e,n,r)=>{r.d(n,{A:()=>s});const s=r.p+"assets/images/KSP-Configuration-Global_Parameters-3098c90e1ebf51c755993c8147b8a44b.png"},97989:(e,n,r)=>{r.d(n,{A:()=>s});const s=r.p+"assets/images/KSP-Configuration-HSM_Management-0c940525da43f293f5391fd21596afcc.png"}}]);