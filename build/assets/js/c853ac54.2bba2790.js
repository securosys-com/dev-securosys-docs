"use strict";(self.webpackChunksecurosys_docs=self.webpackChunksecurosys_docs||[]).push([[55977],{20119:(e,t,s)=>{s.d(t,{A:()=>r});const r=s.p+"assets/images/service_user-af374b1a5b9bd63e1c6ac12a08f6d000.png"},28453:(e,t,s)=>{s.d(t,{R:()=>a,x:()=>o});var r=s(96540);const n={},i=r.createContext(n);function a(e){const t=r.useContext(i);return r.useMemo((function(){return"function"==typeof e?e(t):{...t,...e}}),[t,e])}function o(e){let t;return t=e.disableParentContext?"function"==typeof e.components?e.components(n):e.components||n:a(e.components),r.createElement(i.Provider,{value:t},e.children)}},56067:(e,t,s)=>{s.r(t),s.d(t,{assets:()=>d,contentTitle:()=>c,default:()=>p,frontMatter:()=>l,metadata:()=>r,toc:()=>h});const r=JSON.parse('{"id":"GettingStarted/activation_process","title":"How to retrieve CloudHSM Credentials?","description":"Activate CloudHSM services through in-person sales or online via the Cloud Console, with onboarding tailored to your chosen purchase channel.","source":"@site/cloudhsm/GettingStarted/activation_process.md","sourceDirName":"GettingStarted","slug":"/GettingStarted/activation_process","permalink":"/cloudhsm/GettingStarted/activation_process","draft":false,"unlisted":false,"tags":[],"version":"current","lastUpdatedAt":1741162132000,"sidebarPosition":4,"frontMatter":{"sidebar_position":4,"title":"How to retrieve CloudHSM Credentials?","sidebar_label":"3. HSM Credentials","description":"Activate CloudHSM services through in-person sales or online via the Cloud Console, with onboarding tailored to your chosen purchase channel.","keywords":["cloud hsm","hsm key management","hsm cloud","hsm as a service","cloud based hsm","hsm digital signature","hsm services","hsm service","what is cloud hsm","hsm signing","hsm pki","hsm encryption","code signing hsm","hsm key","code signing service","hsm code signing","cloud code signing","cloud encryption key management","cloud hardware security module","cloudhsm vs kms","code signing certificate","key management hsm","microsoft encryption key management","hsm aws","document signing services","code signing","hsm providers","code signing as a service","aws cloudhsm documentation","hsm pricing"]},"sidebar":"tutorialSidebar","previous":{"title":"2. Support account","permalink":"/cloudhsm/GettingStarted/supportaccount"},"next":{"title":"4. Connect HSM","permalink":"/cloudhsm/GettingStarted/setup_instructions"}}');var n=s(74848),i=s(28453),a=s(65537),o=s(79329);const l={sidebar_position:4,title:"How to retrieve CloudHSM Credentials?",sidebar_label:"3. HSM Credentials",description:"Activate CloudHSM services through in-person sales or online via the Cloud Console, with onboarding tailored to your chosen purchase channel.",keywords:["cloud hsm","hsm key management","hsm cloud","hsm as a service","cloud based hsm","hsm digital signature","hsm services","hsm service","what is cloud hsm","hsm signing","hsm pki","hsm encryption","code signing hsm","hsm key","code signing service","hsm code signing","cloud code signing","cloud encryption key management","cloud hardware security module","cloudhsm vs kms","code signing certificate","key management hsm","microsoft encryption key management","hsm aws","document signing services","code signing","hsm providers","code signing as a service","aws cloudhsm documentation","hsm pricing"]},c="How to retrieve the HSM Credentials?",d={},h=[{value:"Download the HSM Service Credentials",id:"download-the-hsm-service-credentials",level:3},{value:"Description of the Service Credentials",id:"description-of-the-service-credentials",level:3},{value:"1. What are the Service User Credentials?",id:"1-what-are-the-service-user-credentials",level:3},{value:"2. What are the Technical User Credentials?",id:"2-what-are-the-technical-user-credentials",level:3},{value:"What is the JSON Web Token?",id:"what-is-the-json-web-token",level:3},{value:"Download the HSM Service Credentials",id:"download-the-hsm-service-credentials-1",level:3}];function u(e){const t={a:"a",admonition:"admonition",code:"code",h1:"h1",h3:"h3",header:"header",hr:"hr",img:"img",li:"li",ol:"ol",p:"p",pre:"pre",strong:"strong",ul:"ul",...(0,i.R)(),...e.components};return(0,n.jsxs)(n.Fragment,{children:[(0,n.jsx)(t.header,{children:(0,n.jsx)(t.h1,{id:"how-to-retrieve-the-hsm-credentials",children:"How to retrieve the HSM Credentials?"})}),"\n",(0,n.jsx)(t.p,{children:"This guide applies to both CloudHSM:"}),"\n",(0,n.jsxs)(t.ul,{children:["\n",(0,n.jsx)(t.li,{children:"Dedicated"}),"\n",(0,n.jsx)(t.li,{children:"Shared, with a single partition"}),"\n"]}),"\n",(0,n.jsxs)(a.A,{groupId:"purchase-channel",children:[(0,n.jsxs)(o.A,{value:"offline",label:"via Sales",default:!0,children:[(0,n.jsx)(t.h3,{id:"download-the-hsm-service-credentials",children:"Download the HSM Service Credentials"}),(0,n.jsxs)(t.p,{children:["The provisioning of the HSM credentials must be performed by the ",(0,n.jsx)(t.strong,{children:"privileged support user"})," and include the following steps:"]}),(0,n.jsxs)(t.ol,{children:["\n",(0,n.jsxs)(t.li,{children:["Check your mailbox and look for one (or more) email with a ",(0,n.jsx)(t.a,{href:"https://www.securesafe.com/en/",children:"SecureSafe"})," link."]}),"\n",(0,n.jsx)(t.li,{children:"Check your text messages on your mobile phone and look for one (ore more) password(s) (SecureCode)."}),"\n",(0,n.jsx)(t.li,{children:"Use the password to download the HSM Service Credentials file(s) from SecureSafe."}),"\n"]}),(0,n.jsx)("figure",{class:"image",children:(0,n.jsxs)(t.p,{children:[(0,n.jsx)(t.img,{src:s(20119).A+"",width:"3052",height:"660"}),"\n",(0,n.jsx)("figcaption",{children:"CloudHSM Service Credentials Provisioning"})]})}),(0,n.jsx)(t.h3,{id:"description-of-the-service-credentials",children:"Description of the Service Credentials"}),(0,n.jsx)(t.p,{children:"You will receive one or two sets of credentials depending on your subcription type:"}),(0,n.jsxs)(t.ul,{children:["\n",(0,n.jsx)(t.li,{children:"HSM Standard"}),"\n",(0,n.jsx)(t.li,{children:"HSM with Multi-Tenant TSBaaS"}),"\n"]}),(0,n.jsxs)(a.A,{children:[(0,n.jsxs)(o.A,{value:"Standard",children:[(0,n.jsx)(t.p,{children:"You will receive 2 sets of credentials, in 2 separate emails:"}),(0,n.jsxs)(t.ol,{children:["\n",(0,n.jsxs)(t.li,{children:[(0,n.jsx)(t.strong,{children:"Service"})," User credentials"]}),"\n",(0,n.jsxs)(t.li,{children:[(0,n.jsx)(t.strong,{children:"Technical"})," User credentials"]}),"\n"]}),(0,n.jsx)(t.p,{children:"Both credentials apply to CloudHSM Sandbox (SBX), Economy (ECO), Certified (ECO-CC) and Platinum subscriptions."}),(0,n.jsx)(t.h3,{id:"1-what-are-the-service-user-credentials",children:"1. What are the Service User Credentials?"}),(0,n.jsxs)(t.p,{children:["The ",(0,n.jsx)(t.strong,{children:"Service-User"})," credentials are used to authenticate the CloudHSM subscriber on the service level. After successful authentication, the network gateway proxy permits the session to pass through to the HSM cluster."]}),(0,n.jsx)(t.p,{children:"The Service-User credential file contains the name and password to authenticate with reverse proxies."}),(0,n.jsx)(t.pre,{children:(0,n.jsx)(t.code,{className:"language-yml",metastring:'title="<company>_<svc>_service_<user>_<date>.txt"',children:"Service User Name: ...              # Reverse proxy Service-User name\nService User Password: ...          # Reverse proxy Service-User password\nHSM User Name: ...                  # Reference to HSM user/partition name\n"})}),(0,n.jsx)(t.admonition,{type:"info",children:(0,n.jsxs)(t.p,{children:["During ",(0,n.jsx)(t.a,{href:"/cloudhsm/GettingStarted/setup_instructions",children:"step 5: Setup API Provider"}),', the Service-User credentials are referred to as "Proxy-User"/"Proxy-Password".']})}),(0,n.jsx)(t.hr,{}),(0,n.jsx)(t.h3,{id:"2-what-are-the-technical-user-credentials",children:"2. What are the Technical User Credentials?"}),(0,n.jsxs)(t.p,{children:["The ",(0,n.jsx)(t.strong,{children:"Technical-User"})," credentials are used when connecting for the first time with your HSM cluster partition. This setup password will be disposed of and replaced with a ",(0,n.jsx)(t.strong,{children:"user secret"}),"."]}),(0,n.jsx)(t.p,{children:"The user secret will never be revealed on display."}),(0,n.jsx)(t.admonition,{type:"info",children:(0,n.jsxs)(t.p,{children:["During ",(0,n.jsx)(t.a,{href:"/cloudhsm/GettingStarted/setup_instructions",children:"step 5: Setup API Provider"}),', the Technical-User credentials are referred to as "HSM-User"/"HSM-Password".']})}),(0,n.jsx)(t.p,{children:"The Technical-User credential file contains the name and setup password to access the HSM partition and PKCS#11 secret:"}),(0,n.jsx)(t.pre,{children:(0,n.jsx)(t.code,{className:"language-yml",metastring:'title="<company>_<svc>_hsm_<user>_<date>.txt"',children:"HSM User Name: ...                  # HSM user/partition name\nHSM User Setup Password: ...        # HSM user/partition initial password\nPKCS#11 password : ...              # if PKCS#11 API ordered\n"})}),(0,n.jsxs)(t.admonition,{title:"Limited lifetime",type:"danger",children:[(0,n.jsxs)(t.p,{children:["The Technical-User ",(0,n.jsx)(t.code,{children:"HSM Setup Password"})," has a limited lifetime of ",(0,n.jsx)(t.strong,{children:"7 days"}),":"]}),(0,n.jsxs)(t.ul,{children:["\n",(0,n.jsxs)(t.li,{children:["from the ",(0,n.jsx)(t.strong,{children:"first usage"}),", for CloudHSM Economy (ECO), Sandbox (SBX) and Platinum"]}),"\n",(0,n.jsxs)(t.li,{children:["from the ",(0,n.jsx)(t.strong,{children:"date of issuance"}),", for CloudHSM Economy Certified (ECO-CC)"]}),"\n"]}),(0,n.jsxs)(t.p,{children:["If you are not able to setup your application and connect to the HSM cluster within that time, please ",(0,n.jsx)(t.a,{href:"https://support.securosys.com",children:"create a support ticket"})," to renew the HSM Setup Password."]})]})]}),(0,n.jsxs)(o.A,{value:"Multi-Tenant TSBaaS",children:[(0,n.jsxs)(t.p,{children:["You will receive 1 single set of credentials with a ",(0,n.jsx)(t.strong,{children:"JWT Token"}),"."]}),(0,n.jsx)(t.h3,{id:"what-is-the-json-web-token",children:"What is the JSON Web Token?"}),(0,n.jsxs)(t.p,{children:["The ",(0,n.jsx)(t.strong,{children:"JSON Web Token (JWT)"})," serves as an authentication mechanism for CloudHSM subscribers within the multi-tenant TSBaaS environment. It enables secure communication with the CloudHSM partition via ",(0,n.jsx)(t.a,{href:"/tsb/overview",children:"Transaction Security Broker (REST-API)"}),"."]}),(0,n.jsx)(t.p,{children:"The credential file contains a JSON Web Token to authenticate on Transaction Security Broker (TSB) and RESTful-API."}),(0,n.jsx)(t.pre,{children:(0,n.jsx)(t.code,{className:"language-txt",metastring:'title="<organization>_<svc>_jwt_<user>_<date>.txt"',children:"- Details for Restful-API & Transaction Security Broker:\n\xa0\xa0\xa0 API-Endpoint: https://<service-url>.cloudshsm.com\n\xa0\xa0  JWT-TOKEN: ...\n\n\xa0\xa0  Documentation available at: https://<service-url>.cloudshsm.com/swagger-ui/index.html\n\xa0\xa0  - Select the 'Authorize' button and insert the JWT token if you wish to try out some commands on the Swagger-UI.\n\xa0\xa0  - You may also test connectivity using the following curl command:\n\xa0\xa0\xa0\xa0\xa0 curl -X GET 'https://sbx-rest-api.cloudshsm.com/v1/versionInfo' -H 'Authorization: Bearer <YOUR_JWT_TOKEN_HERE>\n"})})]}),(0,n.jsx)(o.A,{value:"Dedicated TSBaaS",children:(0,n.jsxs)(t.p,{children:["This authentication is based on mutual TLS (mTLS) and follows ",(0,n.jsx)(t.a,{href:"/tsb/Installation/https-setup/mtls-configuration",children:"this process"}),", in close cooperation between the Securosys Support Team and you."]})})]})]}),(0,n.jsxs)(o.A,{value:"online",label:"Online",default:!0,children:[(0,n.jsx)(t.h3,{id:"download-the-hsm-service-credentials-1",children:"Download the HSM Service Credentials"}),(0,n.jsxs)(t.ol,{children:["\n",(0,n.jsxs)(t.li,{children:["Log into ",(0,n.jsx)(t.a,{href:"https://cloud.securosys.com/login",children:"Cloud Console"}),"."]}),"\n",(0,n.jsxs)(t.li,{children:["Go to ",(0,n.jsx)(t.a,{href:"https://cloud.securosys.com/my-services",children:"MyServices page"}),".\nYour service should appear in the list with the status ",(0,n.jsx)("span",{style:{fontWeight:"bold",color:"green"},children:"Active"}),"."]}),"\n",(0,n.jsxs)(t.li,{children:["Click on the ",(0,n.jsx)(t.code,{children:"Download"})," button in the last column. A pop-up opens."]}),"\n",(0,n.jsxs)(t.li,{children:["Copy/paste you ",(0,n.jsx)(t.strong,{children:"6-character PIN code"}),". This was sent via email during the ",(0,n.jsx)(t.a,{href:"/cloudhsm/GettingStarted/subscribe#1-create-a-tenant",children:"subscription"}),"."]}),"\n",(0,n.jsxs)(t.li,{children:["Click on the ",(0,n.jsx)(t.code,{children:"Download"})," button to retrieve the HSM Service credentials file."]}),"\n"]}),(0,n.jsx)(t.p,{children:"The HSM Service credentials file will contain:"}),(0,n.jsx)(t.pre,{children:(0,n.jsx)(t.code,{className:"language-txt",metastring:'title="<organization>_<svc>_credentials_<user>_<date>.txt"',children:"- Details  for Native-API (JCE, PKCS#11, MS CNG) \n    HSM User Name: ...                  # HSM user/partition name\n    HSM User Setup Password: ...        # HSM user/partition initial password\n    Service (Proxy) User Name: ...      # Reverse proxy Service-User name\n    Service (Proxy) User Password: ...  # Reverse proxy Service-User password\n    PKCS#11 password : ...              # if PKCS#11 API ordered\n\n- Details for Restful-API & Transaction Security Broker: \n    JWT-TOKEN for Restful-API & Transaction Security Broker: ...\n"})}),(0,n.jsx)(t.admonition,{title:"Optional HSM Credentials",type:"info",children:(0,n.jsx)(t.p,{children:"Not all the HSM credentials provided are required for your service subscription."})})]})]}),"\n",(0,n.jsxs)(t.p,{children:["Continue with the ",(0,n.jsx)(t.strong,{children:(0,n.jsx)(t.a,{href:"/cloudhsm/GettingStarted/setup_instructions",children:"step 4: Setup the API Provider"})}),"."]}),"\n",(0,n.jsx)(t.hr,{}),"\n",(0,n.jsxs)(t.admonition,{title:"need help ?",type:"tip",children:[(0,n.jsxs)(t.p,{children:["Check the ",(0,n.jsx)(t.a,{href:"./troubleshooting",children:"troublehsooting"})," section or contact our support team for further assistance:"]}),(0,n.jsxs)(t.ul,{children:["\n",(0,n.jsx)(t.li,{children:(0,n.jsx)(t.a,{href:"https://support.securosys.com",children:"Create a ticket (login required)"})}),"\n",(0,n.jsx)(t.li,{children:(0,n.jsx)(t.a,{href:"mailto:support@securosys.com",children:"Send an email"})}),"\n"]})]})]})}function p(e={}){const{wrapper:t}={...(0,i.R)(),...e.components};return t?(0,n.jsx)(t,{...e,children:(0,n.jsx)(u,{...e})}):u(e)}},65537:(e,t,s)=>{s.d(t,{A:()=>w});var r=s(96540),n=s(34164),i=s(65627),a=s(56347),o=s(50372),l=s(30604),c=s(11861),d=s(78749);function h(e){return r.Children.toArray(e).filter((e=>"\n"!==e)).map((e=>{if(!e||(0,r.isValidElement)(e)&&function(e){const{props:t}=e;return!!t&&"object"==typeof t&&"value"in t}(e))return e;throw new Error(`Docusaurus error: Bad <Tabs> child <${"string"==typeof e.type?e.type:e.type.name}>: all children of the <Tabs> component should be <TabItem>, and every <TabItem> should have a unique "value" prop.`)}))?.filter(Boolean)??[]}function u(e){const{values:t,children:s}=e;return(0,r.useMemo)((()=>{const e=t??function(e){return h(e).map((e=>{let{props:{value:t,label:s,attributes:r,default:n}}=e;return{value:t,label:s,attributes:r,default:n}}))}(s);return function(e){const t=(0,c.XI)(e,((e,t)=>e.value===t.value));if(t.length>0)throw new Error(`Docusaurus error: Duplicate values "${t.map((e=>e.value)).join(", ")}" found in <Tabs>. Every value needs to be unique.`)}(e),e}),[t,s])}function p(e){let{value:t,tabValues:s}=e;return s.some((e=>e.value===t))}function m(e){let{queryString:t=!1,groupId:s}=e;const n=(0,a.W6)(),i=function(e){let{queryString:t=!1,groupId:s}=e;if("string"==typeof t)return t;if(!1===t)return null;if(!0===t&&!s)throw new Error('Docusaurus error: The <Tabs> component groupId prop is required if queryString=true, because this value is used as the search param name. You can also provide an explicit value such as queryString="my-search-param".');return s??null}({queryString:t,groupId:s});return[(0,l.aZ)(i),(0,r.useCallback)((e=>{if(!i)return;const t=new URLSearchParams(n.location.search);t.set(i,e),n.replace({...n.location,search:t.toString()})}),[i,n])]}function v(e){const{defaultValue:t,queryString:s=!1,groupId:n}=e,i=u(e),[a,l]=(0,r.useState)((()=>function(e){let{defaultValue:t,tabValues:s}=e;if(0===s.length)throw new Error("Docusaurus error: the <Tabs> component requires at least one <TabItem> children component");if(t){if(!p({value:t,tabValues:s}))throw new Error(`Docusaurus error: The <Tabs> has a defaultValue "${t}" but none of its children has the corresponding value. Available values are: ${s.map((e=>e.value)).join(", ")}. If you intend to show no default tab, use defaultValue={null} instead.`);return t}const r=s.find((e=>e.default))??s[0];if(!r)throw new Error("Unexpected error: 0 tabValues");return r.value}({defaultValue:t,tabValues:i}))),[c,h]=m({queryString:s,groupId:n}),[v,g]=function(e){let{groupId:t}=e;const s=function(e){return e?`docusaurus.tab.${e}`:null}(t),[n,i]=(0,d.Dv)(s);return[n,(0,r.useCallback)((e=>{s&&i.set(e)}),[s,i])]}({groupId:n}),f=(()=>{const e=c??v;return p({value:e,tabValues:i})?e:null})();(0,o.A)((()=>{f&&l(f)}),[f]);return{selectedValue:a,selectValue:(0,r.useCallback)((e=>{if(!p({value:e,tabValues:i}))throw new Error(`Can't select invalid tab value=${e}`);l(e),h(e),g(e)}),[h,g,i]),tabValues:i}}var g=s(9136);const f={tabList:"tabList__CuJ",tabItem:"tabItem_LNqP"};var x=s(74848);function S(e){let{className:t,block:s,selectedValue:r,selectValue:a,tabValues:o}=e;const l=[],{blockElementScrollPositionUntilNextRender:c}=(0,i.a_)(),d=e=>{const t=e.currentTarget,s=l.indexOf(t),n=o[s].value;n!==r&&(c(t),a(n))},h=e=>{let t=null;switch(e.key){case"Enter":d(e);break;case"ArrowRight":{const s=l.indexOf(e.currentTarget)+1;t=l[s]??l[0];break}case"ArrowLeft":{const s=l.indexOf(e.currentTarget)-1;t=l[s]??l[l.length-1];break}}t?.focus()};return(0,x.jsx)("ul",{role:"tablist","aria-orientation":"horizontal",className:(0,n.A)("tabs",{"tabs--block":s},t),children:o.map((e=>{let{value:t,label:s,attributes:i}=e;return(0,x.jsx)("li",{role:"tab",tabIndex:r===t?0:-1,"aria-selected":r===t,ref:e=>{l.push(e)},onKeyDown:h,onClick:d,...i,className:(0,n.A)("tabs__item",f.tabItem,i?.className,{"tabs__item--active":r===t}),children:s??t},t)}))})}function j(e){let{lazy:t,children:s,selectedValue:i}=e;const a=(Array.isArray(s)?s:[s]).filter(Boolean);if(t){const e=a.find((e=>e.props.value===i));return e?(0,r.cloneElement)(e,{className:(0,n.A)("margin-top--md",e.props.className)}):null}return(0,x.jsx)("div",{className:"margin-top--md",children:a.map(((e,t)=>(0,r.cloneElement)(e,{key:t,hidden:e.props.value!==i})))})}function b(e){const t=v(e);return(0,x.jsxs)("div",{className:(0,n.A)("tabs-container",f.tabList),children:[(0,x.jsx)(S,{...t,...e}),(0,x.jsx)(j,{...t,...e})]})}function w(e){const t=(0,g.A)();return(0,x.jsx)(b,{...e,children:h(e.children)},String(t))}},79329:(e,t,s)=>{s.d(t,{A:()=>a});s(96540);var r=s(34164);const n={tabItem:"tabItem_Ymn6"};var i=s(74848);function a(e){let{children:t,hidden:s,className:a}=e;return(0,i.jsx)("div",{role:"tabpanel",className:(0,r.A)(n.tabItem,a),hidden:s,children:t})}}}]);