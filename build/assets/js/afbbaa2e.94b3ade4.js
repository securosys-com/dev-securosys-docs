"use strict";(self.webpackChunksecurosys_docs=self.webpackChunksecurosys_docs||[]).push([[73651],{6099:(e,s,n)=>{n.d(s,{A:()=>i});const i=n.p+"assets/images/Versasec-Smart-Card-Repo-d7eca63dde34668d79508c8e415e439b.png"},11451:(e,s,n)=>{n.d(s,{A:()=>i});const i=n.p+"assets/images/Versasec-successfully-verified-keys-c5f4641cef53241553f5dbdee3228117.png"},28453:(e,s,n)=>{n.d(s,{R:()=>r,x:()=>a});var i=n(96540);const t={},o=i.createContext(t);function r(e){const s=i.useContext(o);return i.useMemo((function(){return"function"==typeof e?e(s):{...s,...e}}),[s,e])}function a(e){let s;return s=e.disableParentContext?"function"==typeof e.components?e.components(t):e.components||t:r(e.components),i.createElement(o.Provider,{value:s},e.children)}},30734:(e,s,n)=>{n.r(s),n.d(s,{assets:()=>d,contentTitle:()=>a,default:()=>h,frontMatter:()=>r,metadata:()=>i,toc:()=>c});const i=JSON.parse('{"id":"Installation/configure-cms","title":"Configure Versasec vSEC:CMS for HSM","description":"Configuring Versasec Credential Management System (CMS) for Securosys Hardware Security Modules (HSMs)","source":"@site/versasec-cms/Installation/configure-cms.md","sourceDirName":"Installation","slug":"/Installation/configure-cms","permalink":"/versasec-cms/Installation/configure-cms","draft":false,"unlisted":false,"tags":[],"version":"current","lastUpdatedAt":1741162132000,"sidebarPosition":2,"frontMatter":{"sidebar_position":2,"title":"Configure Versasec vSEC:CMS for HSM","sidebar_label":"Configure vSEC:CMS","description":"Configuring Versasec Credential Management System (CMS) for Securosys Hardware Security Modules (HSMs)","keywords":["hsm","cloud hsm"]},"sidebar":"tutorialSidebar","previous":{"title":"API Provider","permalink":"/versasec-cms/Installation/Provider"},"next":{"title":"Download","permalink":"/versasec-cms/downloads"}}');var t=n(74848),o=n(28453);const r={sidebar_position:2,title:"Configure Versasec vSEC:CMS for HSM",sidebar_label:"Configure vSEC:CMS",description:"Configuring Versasec Credential Management System (CMS) for Securosys Hardware Security Modules (HSMs)",keywords:["hsm","cloud hsm"]},a="Configure Versasec CMS with Securosys HSM",d={},c=[{value:"Additional Information",id:"additional-information",level:2}];function l(e){const s={a:"a",admonition:"admonition",code:"code",h1:"h1",h2:"h2",header:"header",img:"img",li:"li",mdxAdmonitionTitle:"mdxAdmonitionTitle",ol:"ol",p:"p",ul:"ul",...(0,o.R)(),...e.components};return(0,t.jsxs)(t.Fragment,{children:[(0,t.jsx)(s.header,{children:(0,t.jsx)(s.h1,{id:"configure-versasec-cms-with-securosys-hsm",children:"Configure Versasec CMS with Securosys HSM"})}),"\n",(0,t.jsxs)(s.p,{children:["The vSEC",":CMS"," makes use of the PKCS#11 interface available in the HSM to utilize the benefits of the HSM key storage and generation. Follow the below shown steps to configure Versasec vSEC",":CMS"," to work with Primus HSM or CloudHSM."]}),"\n",(0,t.jsx)(s.admonition,{type:"warning",children:(0,t.jsxs)(s.p,{children:["It is expected at this point that the Primus HSM PKCS#11 provider is installed and configured to connect to the HSM on the server where the vSEC",":CMS"," is installed. See ",(0,t.jsx)(s.a,{href:"/versasec-cms/Installation/Provider",children:"Primus HSM PKCS#11 Provider"})," for more information. vSEC",":CMS"," will search in the system path for the PKCS#11 module."]})}),"\n",(0,t.jsx)(s.admonition,{type:"info",children:(0,t.jsxs)(s.p,{children:["Visit ",(0,t.jsxs)(s.a,{href:"https://support.versasec.com/hc/en-us/articles/115000851714-HSM-Support",children:["vSEC",":CMS"," HSM Support"]})," on the Versasec online documentation for more details."]})}),"\n",(0,t.jsxs)(s.ol,{children:["\n",(0,t.jsxs)(s.li,{children:["\n",(0,t.jsxs)(s.p,{children:["From ",(0,t.jsx)(s.code,{children:"Options - Connections"})," click the ",(0,t.jsx)(s.code,{children:"Configure"})," button. Make sure that the ",(0,t.jsx)(s.code,{children:"Hardware Security Module (HSM)"})," is in the selected window."]}),"\n"]}),"\n",(0,t.jsxs)(s.li,{children:["\n",(0,t.jsxs)(s.p,{children:["Click ",(0,t.jsx)(s.code,{children:"Hardware Security Module (HSM)"})," and click the ",(0,t.jsx)(s.code,{children:"Add"})," button to setup a template."]}),"\n",(0,t.jsxs)(s.ul,{children:["\n",(0,t.jsx)(s.li,{children:"Enter a template name and from the drop-down list select the Securosys Primus HSM.Moin"}),"\n"]}),"\n",(0,t.jsx)(s.p,{children:(0,t.jsx)(s.img,{src:n(54197).A+"",width:"477",height:"375"})}),"\n"]}),"\n",(0,t.jsxs)(s.li,{children:["\n",(0,t.jsxs)(s.p,{children:["The PKSC#11 module will be automatically detected and populated into the ",(0,t.jsx)(s.code,{children:"PKCS11 DLL name"})," field. The URL will be read from the configuration file that is  included as part of the Primus HSM PKCS#11 provider configuration file."]}),"\n",(0,t.jsxs)(s.ul,{children:["\n",(0,t.jsxs)(s.li,{children:["Select the ",(0,t.jsx)(s.code,{children:"SLOT"}),", where the master key will reside in, from the available slot list in the drop-down list. See ",(0,t.jsx)(s.a,{href:"/pkcs/Installation/pkcs11_provider_configuration#section-slots",children:"Primus HSM PKCS#11 Provider - Configuration - Section Slots"})," on more information about PKCS#11 slots."]}),"\n",(0,t.jsxs)(s.li,{children:["Enter the PKCS#11 PIN credential for the user who has access to the slot and click ",(0,t.jsx)(s.code,{children:"Check connection"})," to test connectivity. If connectivity to the HSM is successful a success dialog will pop-up."]}),"\n"]}),"\n",(0,t.jsx)(s.admonition,{type:"info",children:(0,t.jsxs)(s.p,{children:["Obtain the PKCS#11 Password (PIN) from your HSM administrator. In case of CloudHSM subscription the PKCS#11 credentials are provided during the on-boarding procedure. See ",(0,t.jsx)(s.a,{href:"/pkcs/Installation/primus_hsm_settings#preparing-the-pkcs11-password-pin",children:"Primus HSM PKCS#11 Provider - Primus HSM Configuration - Preparing the PKCS#11 Password (PIN)"}),"."]})}),"\n"]}),"\n"]}),"\n",(0,t.jsx)(s.p,{children:"Example successful connection pop-up:"}),"\n",(0,t.jsx)(s.p,{children:(0,t.jsx)(s.img,{src:n(97693).A+"",width:"227",height:"111"})}),"\n",(0,t.jsxs)(s.p,{children:["Click ",(0,t.jsx)(s.code,{children:"Save"})," to save and close the configuration."]}),"\n",(0,t.jsxs)(s.ol,{start:"4",children:["\n",(0,t.jsxs)(s.li,{children:["Once the connection is setup it will be necessary to create an ",(0,t.jsx)(s.code,{children:"Operator Service Key Store"})," (OSKS). Navigate to ",(0,t.jsx)(s.code,{children:"Options - Operators"})," and click the ",(0,t.jsx)(s.code,{children:"Add service key store"})," button. Enter a name in the ",(0,t.jsx)(s.code,{children:"Store name "}),"field and click ",(0,t.jsx)(s.code,{children:"Add"}),"."]}),"\n"]}),"\n",(0,t.jsx)(s.p,{children:(0,t.jsx)(s.img,{src:n(71013).A+"",width:"227",height:"130"})}),"\n",(0,t.jsxs)(s.p,{children:["All vSEC",":CMS"," master keys will be copied to the HSM during this process and at the end you will get a success dialog."]}),"\n",(0,t.jsx)(s.p,{children:(0,t.jsx)(s.img,{src:n(71013).A+"",width:"227",height:"130"})}),"\n",(0,t.jsxs)(s.p,{children:["From the Operators table you can see that the HSM is now used as key store as indicated by the ",(0,t.jsx)(s.code,{children:"*"})," character."]}),"\n",(0,t.jsx)(s.p,{children:(0,t.jsx)(s.img,{src:n(59215).A+"",width:"602",height:"107"})}),"\n",(0,t.jsxs)(s.p,{children:["Additionally, if you go back to ",(0,t.jsx)(s.code,{children:"Options - Connections - Hardware Security Module (HSM)"})," and select your ",(0,t.jsx)(s.code,{children:"HSM connector"})," and ",(0,t.jsx)(s.code,{children:"Edit"}),", then click the ",(0,t.jsx)(s.code,{children:"Check connection"})," button you should see more information in the success dialog indicating that the master key used by vSEC",":CMS"," was found in the slot on the HSM."]}),"\n",(0,t.jsx)(s.p,{children:(0,t.jsx)(s.img,{src:n(11451).A+"",width:"362",height:"176"})}),"\n",(0,t.jsxs)(s.ol,{start:"5",children:["\n",(0,t.jsxs)(s.li,{children:["It is strongly recommended to generate a new master key. From ",(0,t.jsx)(s.code,{children:"Options - Master Key"})," click the ",(0,t.jsx)(s.code,{children:"Generate new master key"})," button to start the process."]}),"\n"]}),"\n",(0,t.jsxs)(s.admonition,{type:"caution",children:[(0,t.jsx)(s.mdxAdmonitionTitle,{}),(0,t.jsxs)(s.p,{children:["It is important to remember that any new credential administration key will be diversified from the newly generated master key. Any credential administration key diversified from older master key(s) of the vSEC",":CMS"," application will remain operable. However, it is recommended to re-register those credentials issued from the older master key(s) of the vSEC",":CMS",". This will update the user's credential administration key so that it is diversified from the new master key."]})]}),"\n",(0,t.jsxs)(s.ol,{start:"6",children:["\n",(0,t.jsxs)(s.li,{children:["Select the option ",(0,t.jsx)(s.code,{children:"On server side HSM"})," and from the drop-down list 3 options are available, ",(0,t.jsx)(s.code,{children:"DES-EDE3"}),", ",(0,t.jsx)(s.code,{children:"AES128"})," and ",(0,t.jsx)(s.code,{children:"AES256"}),". Select the appropriate key type and size as required for your environment."]}),"\n"]}),"\n",(0,t.jsx)(s.admonition,{type:"note",children:(0,t.jsxs)(s.p,{children:["It is recommended to select ",(0,t.jsx)(s.code,{children:"AES"})," for key type as ",(0,t.jsx)(s.code,{children:"DES"})," has been withdrawn as a standard by the National Institute of Standards and Technology. From version 6.5 of vSEC",":CMS"," it is possible to migrate from ",(0,t.jsx)(s.code,{children:"DES"})," managed master keys to ",(0,t.jsx)(s.code,{children:"AES"}),"."]})}),"\n",(0,t.jsx)(s.p,{children:(0,t.jsx)(s.img,{src:n(64477).A+"",width:"276",height:"290"})}),"\n",(0,t.jsxs)(s.p,{children:["Click ",(0,t.jsx)(s.code,{children:"Ok"}),". A dialog will popup informing you about the change to the system. Click ",(0,t.jsx)(s.code,{children:"Yes"})," to complete the setup."]}),"\n",(0,t.jsxs)(s.ol,{start:"7",children:["\n",(0,t.jsxs)(s.li,{children:["Navigate to ",(0,t.jsx)(s.code,{children:"Repository - Master keys"})," and you will see the entry. The ",(0,t.jsx)(s.code,{children:"*"})," character indicates which master key is to be used by vSEC",":CMS","."]}),"\n"]}),"\n",(0,t.jsx)(s.p,{children:(0,t.jsx)(s.img,{src:n(84949).A+"",width:"380",height:"130"})}),"\n",(0,t.jsxs)(s.p,{children:["Additionally, from ",(0,t.jsx)(s.code,{children:"Repository - Smart Cards"})," you will see that credentials managed by older master keys will have an Update needed message."]}),"\n",(0,t.jsx)(s.p,{children:(0,t.jsx)(s.img,{src:n(6099).A+"",width:"602",height:"105"})}),"\n",(0,t.jsx)(s.admonition,{type:"caution",children:(0,t.jsxs)(s.p,{children:["Once the HSM master key is generated it will not be possible to roll back to use an earlier master key. For any credential that was previously managed by the vSEC",":CMS"," with an older master key that was not generated by the HSM it will be possible to continue to manage these credentials, but it is recommended to update these credentials so that they will be managed by the newly created master key."]})}),"\n",(0,t.jsx)(s.p,{children:"Credentials whose master key needs to be updated can be done as described below:"}),"\n",(0,t.jsxs)(s.ul,{children:["\n",(0,t.jsxs)(s.li,{children:["From vSEC",":CMS"," ",(0,t.jsx)(s.code,{children:"Admin console"})," navigate to ",(0,t.jsx)(s.code,{children:"Actions - Smart Card Update"})," and attach a credential that needs to be updated and click the ",(0,t.jsx)(s.code,{children:"Execute"})," button;"]}),"\n",(0,t.jsxs)(s.li,{children:["From a client host that has vSEC",":CMS"," User installed and configured for updates, navigate to the ",(0,t.jsx)(s.code,{children:"Update"})," tab to perform the update."]}),"\n"]}),"\n",(0,t.jsx)(s.h2,{id:"additional-information",children:"Additional Information"}),"\n",(0,t.jsxs)(s.p,{children:["Any master key added to the HSM will have a label starting with ",(0,t.jsx)(s.code,{children:"CMS MK"})," on the HSM. Depending on whether the master key is generated only on the HSM or was a key created and stored on a full-featured operator card and synced with the HSM, the label will have a different value depending on which option is selected.\nFor a master key generated only on the HSM the label on the HSM will be: ",(0,t.jsx)(s.code,{children:"CMS MK 4099"})," (this is a hex value), if this was the first key. Any additional key(s) would be incremented by one; therefore, a second master key would have a value of ",(0,t.jsx)(s.code,{children:"CMS MK 4100"})," and so on.\nFor a master key created and stored on a full-featured operator card and synced with the HSM the label on the HSM will be: ",(0,t.jsx)(s.code,{children:"CMS MK 00"}),", if this was the first key. Any additional key(s) would be incremented by one; therefore, a second master key would have a value of ",(0,t.jsx)(s.code,{children:"CMS MK 01"})," and so on."]})]})}function h(e={}){const{wrapper:s}={...(0,o.R)(),...e.components};return s?(0,t.jsx)(s,{...e,children:(0,t.jsx)(l,{...e})}):l(e)}},54197:(e,s,n)=>{n.d(s,{A:()=>i});const i=n.p+"assets/images/Versasec-hsm-configuration-3d1d0504a9f85ab1c6230f4a1395c436.png"},59215:(e,s,n)=>{n.d(s,{A:()=>i});const i=n.p+"assets/images/Versasec-Operators-dca870ab5557bd1f93b2ec5b38d98979.png"},64477:(e,s,n)=>{n.d(s,{A:()=>i});const i=n.p+"assets/images/Versasec-generate-new-master-key-e9c39587757b9dbd04328c26e5614d36.png"},71013:(e,s,n)=>{n.d(s,{A:()=>i});const i=n.p+"assets/images/Versasec-add-service-key-store-approval-f0bb75a0e739ed46fc935e7bc1da13e5.png"},84949:(e,s,n)=>{n.d(s,{A:()=>i});const i=n.p+"assets/images/Versasec-Master-Key-repo-e171fcc7a4b7689c90c979e05add0602.png"},97693:(e,s,n)=>{n.d(s,{A:()=>i});const i="data:image/png;base64,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"}}]);