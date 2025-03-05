"use strict";(self.webpackChunksecurosys_docs=self.webpackChunksecurosys_docs||[]).push([[80134],{28453:(e,n,r)=>{r.d(n,{R:()=>i,x:()=>a});var s=r(96540);const t={},o=s.createContext(t);function i(e){const n=s.useContext(o);return s.useMemo((function(){return"function"==typeof e?e(n):{...n,...e}}),[n,e])}function a(e){let n;return n=e.disableParentContext?"function"==typeof e.components?e.components(t):e.components||t:i(e.components),s.createElement(o.Provider,{value:n},e.children)}},90161:(e,n,r)=>{r.r(n),r.d(n,{assets:()=>c,contentTitle:()=>a,default:()=>p,frontMatter:()=>i,metadata:()=>s,toc:()=>d});const s=JSON.parse('{"id":"Tutorials/CloudHSM/proxy_management","title":"PKCS#11 - CloudHSM Service Proxy Setup","description":"If you are not using CloudHSM (or a service proxy), you can skip this section.","source":"@site/pkcs/Tutorials/CloudHSM/proxy_management.md","sourceDirName":"Tutorials/CloudHSM","slug":"/Tutorials/CloudHSM/proxy_management","permalink":"/pkcs/Tutorials/CloudHSM/proxy_management","draft":false,"unlisted":false,"tags":[],"version":"current","lastUpdatedAt":1741162132000,"sidebarPosition":1,"frontMatter":{"sidebar_position":1,"title":"PKCS#11 - CloudHSM Service Proxy Setup","sidebar_label":"Service Proxy Setup"},"sidebar":"tutorialSidebar","previous":{"title":"Tutorial","permalink":"/pkcs/category/tutorial"},"next":{"title":"Setting up CloudHSM Access","permalink":"/pkcs/Tutorials/CloudHSM/cloud_hsm_setup"}}');var t=r(74848),o=r(28453);const i={sidebar_position:1,title:"PKCS#11 - CloudHSM Service Proxy Setup",sidebar_label:"Service Proxy Setup"},a="Service Proxy Setup",c={},d=[{value:"Validating/Adding a Service Proxy Password",id:"validatingadding-a-service-proxy-password",level:2},{value:"Removing a Service Proxy Password",id:"removing-a-service-proxy-password",level:2}];function l(e){const n={a:"a",admonition:"admonition",code:"code",h1:"h1",h2:"h2",header:"header",p:"p",pre:"pre",strong:"strong",...(0,o.R)(),...e.components};return(0,t.jsxs)(t.Fragment,{children:[(0,t.jsx)(n.header,{children:(0,t.jsx)(n.h1,{id:"service-proxy-setup",children:"Service Proxy Setup"})}),"\n",(0,t.jsx)(n.admonition,{type:"warning",children:(0,t.jsx)(n.p,{children:"If you are not using CloudHSM (or a service proxy), you can skip this section."})}),"\n",(0,t.jsx)(n.p,{children:"In bigger clustered HSM environments you may wish to authenticate the\naccess against an authentication service (e.g. LDAP) before the\nconnection to the HSM is opened. The HSM is then used via a so-called\nservice proxy."}),"\n",(0,t.jsxs)(n.p,{children:["Before validating the service proxy password and fetching the\npermanent secrets from the HSM you need to modify the primus.cfg\nconfiguration file, ",(0,t.jsx)(n.strong,{children:"adding the SERVICE_USER"})," name for the proxy and\nconfiguring ",(0,t.jsx)(n.strong,{children:"host and port of the service proxy"})," (instead of the\nHSM):"]}),"\n",(0,t.jsx)(n.pre,{children:(0,t.jsx)(n.code,{className:"language-libconfig",children:'...\n    hsm0: {\n// highlight-note-start\n      host = "PROXY_URL";\n      port = "2310";\n// highlight-note-end\n      slots: {\n         slot0: {\n            client_id = "CLIENT_ID";\n            user_name = "HSM_USERNAME";\n// highlight-next-line-note\n            proxy_user = "SERVICE_USER";\n            id = 0;\n         };\n      }; /* end slots */\n    }; /* end hsm0 */\n...\n'})}),"\n",(0,t.jsx)(n.h2,{id:"validatingadding-a-service-proxy-password",children:"Validating/Adding a Service Proxy Password"}),"\n",(0,t.jsx)(n.p,{children:"Then you can validate and add the password for the service proxy using\nthe ppin tool:"}),"\n",(0,t.jsx)(n.pre,{children:(0,t.jsx)(n.code,{className:"language-bash",children:"ppin -p -e SERVICE_USER\n"})}),"\n",(0,t.jsx)(n.pre,{children:(0,t.jsx)(n.code,{className:"language-text",children:"********************\nPrimus Permanent PIN\n********************\nProvide proxy password for 'SERVICE_USER' : <enter Service Proxy Password, no echo>\n\n********************\nPrimus Permanent PIN\n********************\n[01] slot-id 0:    user 'HSM_USERNAME' permanent secret: MISSING\n[01] service/proxy user 'SERVICE_USER' permanent secret: Configured\n"})}),"\n",(0,t.jsxs)(n.p,{children:["From now on you can ",(0,t.jsx)(n.a,{href:"/pkcs/Installation/permanent_secret_management",children:"retrieve the permanent secret"})," for 'HSM_USERNAME'\nvia the service proxy."]}),"\n",(0,t.jsx)(n.h2,{id:"removing-a-service-proxy-password",children:"Removing a Service Proxy Password"}),"\n",(0,t.jsx)(n.pre,{children:(0,t.jsx)(n.code,{className:"language-bash",children:"ppin -x -e SERVICE_USERNAME\n"})}),"\n",(0,t.jsx)(n.pre,{children:(0,t.jsx)(n.code,{className:"language-text",children:"********************\nPrimus Permanent PIN\n********************\n[01] service/proxy user 'SERVICE_USER' permanent secret: MISSING\n"})})]})}function p(e={}){const{wrapper:n}={...(0,o.R)(),...e.components};return n?(0,t.jsx)(n,{...e,children:(0,t.jsx)(l,{...e})}):l(e)}}}]);