"use strict";(self.webpackChunksecurosys_docs=self.webpackChunksecurosys_docs||[]).push([[78091],{28453:(e,n,r)=>{r.d(n,{R:()=>s,x:()=>a});var t=r(96540);const i={},o=t.createContext(i);function s(e){const n=t.useContext(o);return t.useMemo((function(){return"function"==typeof e?e(n):{...n,...e}}),[n,e])}function a(e){let n;return n=e.disableParentContext?"function"==typeof e.components?e.components(i):e.components||i:s(e.components),t.createElement(o.Provider,{value:n},e.children)}},65537:(e,n,r)=>{r.d(n,{A:()=>P});var t=r(96540),i=r(34164),o=r(65627),s=r(56347),a=r(50372),l=r(30604),u=r(11861),c=r(78749);function d(e){return t.Children.toArray(e).filter((e=>"\n"!==e)).map((e=>{if(!e||(0,t.isValidElement)(e)&&function(e){const{props:n}=e;return!!n&&"object"==typeof n&&"value"in n}(e))return e;throw new Error(`Docusaurus error: Bad <Tabs> child <${"string"==typeof e.type?e.type:e.type.name}>: all children of the <Tabs> component should be <TabItem>, and every <TabItem> should have a unique "value" prop.`)}))?.filter(Boolean)??[]}function h(e){const{values:n,children:r}=e;return(0,t.useMemo)((()=>{const e=n??function(e){return d(e).map((e=>{let{props:{value:n,label:r,attributes:t,default:i}}=e;return{value:n,label:r,attributes:t,default:i}}))}(r);return function(e){const n=(0,u.XI)(e,((e,n)=>e.value===n.value));if(n.length>0)throw new Error(`Docusaurus error: Duplicate values "${n.map((e=>e.value)).join(", ")}" found in <Tabs>. Every value needs to be unique.`)}(e),e}),[n,r])}function f(e){let{value:n,tabValues:r}=e;return r.some((e=>e.value===n))}function p(e){let{queryString:n=!1,groupId:r}=e;const i=(0,s.W6)(),o=function(e){let{queryString:n=!1,groupId:r}=e;if("string"==typeof n)return n;if(!1===n)return null;if(!0===n&&!r)throw new Error('Docusaurus error: The <Tabs> component groupId prop is required if queryString=true, because this value is used as the search param name. You can also provide an explicit value such as queryString="my-search-param".');return r??null}({queryString:n,groupId:r});return[(0,l.aZ)(o),(0,t.useCallback)((e=>{if(!o)return;const n=new URLSearchParams(i.location.search);n.set(o,e),i.replace({...i.location,search:n.toString()})}),[o,i])]}function g(e){const{defaultValue:n,queryString:r=!1,groupId:i}=e,o=h(e),[s,l]=(0,t.useState)((()=>function(e){let{defaultValue:n,tabValues:r}=e;if(0===r.length)throw new Error("Docusaurus error: the <Tabs> component requires at least one <TabItem> children component");if(n){if(!f({value:n,tabValues:r}))throw new Error(`Docusaurus error: The <Tabs> has a defaultValue "${n}" but none of its children has the corresponding value. Available values are: ${r.map((e=>e.value)).join(", ")}. If you intend to show no default tab, use defaultValue={null} instead.`);return n}const t=r.find((e=>e.default))??r[0];if(!t)throw new Error("Unexpected error: 0 tabValues");return t.value}({defaultValue:n,tabValues:o}))),[u,d]=p({queryString:r,groupId:i}),[g,m]=function(e){let{groupId:n}=e;const r=function(e){return e?`docusaurus.tab.${e}`:null}(n),[i,o]=(0,c.Dv)(r);return[i,(0,t.useCallback)((e=>{r&&o.set(e)}),[r,o])]}({groupId:i}),b=(()=>{const e=u??g;return f({value:e,tabValues:o})?e:null})();(0,a.A)((()=>{b&&l(b)}),[b]);return{selectedValue:s,selectValue:(0,t.useCallback)((e=>{if(!f({value:e,tabValues:o}))throw new Error(`Can't select invalid tab value=${e}`);l(e),d(e),m(e)}),[d,m,o]),tabValues:o}}var m=r(9136);const b={tabList:"tabList__CuJ",tabItem:"tabItem_LNqP"};var v=r(74848);function x(e){let{className:n,block:r,selectedValue:t,selectValue:s,tabValues:a}=e;const l=[],{blockElementScrollPositionUntilNextRender:u}=(0,o.a_)(),c=e=>{const n=e.currentTarget,r=l.indexOf(n),i=a[r].value;i!==t&&(u(n),s(i))},d=e=>{let n=null;switch(e.key){case"Enter":c(e);break;case"ArrowRight":{const r=l.indexOf(e.currentTarget)+1;n=l[r]??l[0];break}case"ArrowLeft":{const r=l.indexOf(e.currentTarget)-1;n=l[r]??l[l.length-1];break}}n?.focus()};return(0,v.jsx)("ul",{role:"tablist","aria-orientation":"horizontal",className:(0,i.A)("tabs",{"tabs--block":r},n),children:a.map((e=>{let{value:n,label:r,attributes:o}=e;return(0,v.jsx)("li",{role:"tab",tabIndex:t===n?0:-1,"aria-selected":t===n,ref:e=>{l.push(e)},onKeyDown:d,onClick:c,...o,className:(0,i.A)("tabs__item",b.tabItem,o?.className,{"tabs__item--active":t===n}),children:r??n},n)}))})}function C(e){let{lazy:n,children:r,selectedValue:o}=e;const s=(Array.isArray(r)?r:[r]).filter(Boolean);if(n){const e=s.find((e=>e.props.value===o));return e?(0,t.cloneElement)(e,{className:(0,i.A)("margin-top--md",e.props.className)}):null}return(0,v.jsx)("div",{className:"margin-top--md",children:s.map(((e,n)=>(0,t.cloneElement)(e,{key:n,hidden:e.props.value!==o})))})}function y(e){const n=g(e);return(0,v.jsxs)("div",{className:(0,i.A)("tabs-container",b.tabList),children:[(0,v.jsx)(x,{...n,...e}),(0,v.jsx)(C,{...n,...e})]})}function P(e){const n=(0,m.A)();return(0,v.jsx)(y,{...e,children:d(e.children)},String(n))}},72113:(e,n,r)=>{r.r(n),r.d(n,{assets:()=>l,contentTitle:()=>a,default:()=>d,frontMatter:()=>s,metadata:()=>t,toc:()=>u});const t=JSON.parse('{"id":"Installation/Install-Primus-Provider/Configuring-PKCS-Provider","title":"Configure HSM Key Management - CyberArk PAM","description":"Configure the Primus PKCS#11 provider on CyberArk PAM Vault with HSM integration, including setup details, user credentials, and configuration examples.","source":"@site/cyberark/Installation/Install-Primus-Provider/Configuring-PKCS-Provider.md","sourceDirName":"Installation/Install-Primus-Provider","slug":"/Installation/Install-Primus-Provider/Configuring-PKCS-Provider","permalink":"/cyberark/Installation/Install-Primus-Provider/Configuring-PKCS-Provider","draft":false,"unlisted":false,"tags":[],"version":"current","lastUpdatedAt":1741162132000,"sidebarPosition":1,"frontMatter":{"sidebar_position":1,"title":"Configure HSM Key Management - CyberArk PAM","sidebar_label":"Configuring PKCS#11","description":"Configure the Primus PKCS#11 provider on CyberArk PAM Vault with HSM integration, including setup details, user credentials, and configuration examples.","keywords":["hsm","cloud hsm"]},"sidebar":"tutorialSidebar","previous":{"title":"Installing PKCS#11","permalink":"/cyberark/Installation/Install-Primus-Provider/Installing-PKCS-Provider"},"next":{"title":"Connect HSM","permalink":"/cyberark/Installation/Install-Primus-Provider/Establishing-HSM-connection"}}');var i=r(74848),o=r(28453);r(65537),r(79329);const s={sidebar_position:1,title:"Configure HSM Key Management - CyberArk PAM",sidebar_label:"Configuring PKCS#11",description:"Configure the Primus PKCS#11 provider on CyberArk PAM Vault with HSM integration, including setup details, user credentials, and configuration examples.",keywords:["hsm","cloud hsm"]},a="Configuring PKCS#11 for CyberArk PAM",l={},u=[{value:"HSM integration guide",id:"hsm-integration-guide",level:3},{value:"Example Configuration",id:"example-configuration",level:2}];function c(e){const n={a:"a",admonition:"admonition",code:"code",h1:"h1",h2:"h2",h3:"h3",header:"header",li:"li",p:"p",pre:"pre",strong:"strong",ul:"ul",...(0,o.R)(),...e.components};return(0,i.jsxs)(i.Fragment,{children:[(0,i.jsx)(n.header,{children:(0,i.jsx)(n.h1,{id:"configuring-pkcs11-for-cyberark-pam",children:"Configuring PKCS#11 for CyberArk PAM"})}),"\n",(0,i.jsx)(n.h3,{id:"hsm-integration-guide",children:"HSM integration guide"}),"\n",(0,i.jsxs)(n.p,{children:["Configure the Primus PKCS#11 provider by adapting the configuration file ",(0,i.jsx)(n.strong,{children:(0,i.jsx)(n.code,{children:"primus.cfg"})})," according to your setup."]}),"\n",(0,i.jsxs)(n.ul,{children:["\n",(0,i.jsxs)(n.li,{children:["Follow the instructions in ",(0,i.jsxs)(n.strong,{children:[(0,i.jsx)(n.a,{href:"https://docs.securosys.ch/pkcs/Installation/pkcs11_provider_configuration",children:"Primus PKCS#11 Provider User Guide - Configuration"}),"."]})]}),"\n"]}),"\n",(0,i.jsx)(n.admonition,{type:"info",children:(0,i.jsxs)(n.p,{children:["For the configuration of the Primus PKCS#11 Provider installed on the CyberArk PAM obtain the credentials from your HSM administrator or CloudHSM Support:\n",(0,i.jsx)(n.code,{children:"user_name"}),", ",(0,i.jsx)(n.code,{children:"host"})," URL/IP address of all HSMs, and additionally for CloudHSM ",(0,i.jsx)(n.code,{children:"proxy_user"}),"."]})}),"\n",(0,i.jsx)(n.admonition,{type:"note",children:(0,i.jsxs)(n.p,{children:["If network hardening is already configured on the host device, please see ",(0,i.jsx)(n.a,{href:"/cyberark/Installation/Configuring-HSM-on-primary-Vault",children:"Configuring Primus HSM on CyberArk Primary Vault"})," on how to enable an outgoing connection to the Securosys HSM."]})}),"\n",(0,i.jsx)(n.h2,{id:"example-configuration",children:"Example Configuration"}),"\n",(0,i.jsxs)(n.p,{children:["The following example shows the configuration file ",(0,i.jsx)(n.strong,{children:(0,i.jsx)(n.code,{children:"primus.cfg"})})," in ",(0,i.jsx)(n.strong,{children:(0,i.jsx)(n.code,{children:"C:\\Program Files\\Securosys\\Primus P11\\primus.cfg"})})," on Windows Server 2019 platform for a redundant partition named ",(0,i.jsx)(n.strong,{children:(0,i.jsx)(n.code,{children:"DEMO_PARTITION"})})," residing on the CloudHSM service with the proxy user name ",(0,i.jsx)(n.strong,{children:(0,i.jsx)(n.code,{children:"SERVICE_USER"})}),". CloudHSM has geo-redundant active sites, thus two HSM partitions are defined in the configuration. In our example, ",(0,i.jsx)(n.strong,{children:(0,i.jsx)(n.code,{children:"hsm0"})})," prevails in priority over ",(0,i.jsx)(n.strong,{children:(0,i.jsx)(n.code,{children:"hsm1"})}),"."]}),"\n",(0,i.jsxs)(n.p,{children:["The ",(0,i.jsx)(n.strong,{children:(0,i.jsx)(n.code,{children:"SERVICE_USER"})})," parameter depends on your CloudHSM details."]}),"\n",(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{className:"language-js",children:'#-----------------------------\n# Primus PKCS#11 configuration\n#-----------------------------\nversion = "1.0";\n\n/*--- GLOBAL CONFIGURATION SECTION ----------------------------------------*/\nprimus:\n{\n  wait_delay = 250; /* in ms*/\n  wait_max_tries = 5;\n  \n  /*--- HSM CONFIGURATION SECTION -----------------------------------------*/\n  hsms:\n  {\n     hsm0:\n     {\n      // highlight-next-line-info\n        host = "a-api.cloudshsm.com";\n         // highlight-next-line-info\n        port = "2310";\n        priority = 0; /* Optional priority. Default 0 = highest priority */\n        slots:\n        {\n           slot0:\n           {\n             // highlight-info-start\n             client_id  = "CyberArk_Server";\n             user_name  = "DEMO_PARTITION";\n             proxy_user = "SERVICE_USER";            /* if proxy in use */\n             id = 0;\n            // highlight-info-end\n           }; /* end slot0 */   \n        }; /* end slots */\n     }; /* end hsm0 */\n    \n     hsm1:\n     {\n        // highlight-info-start    \n        host = "b-api.cloudshsm.com";\n        port = "2310";\n        // highlight-info-end\n        priority = 1; /* Optional priority. Default 0 = highest priority */\n        slots:\n        {\n           slot0:\n           {\n             // highlight-info-start\n             client_id  = "CyberArk_Server";\n             user_name  = "DEMO_PARTITION";\n             proxy_user = "SERVICE_USER";            /* if proxy in use */\n             id = 0;\n             // highlight-info-end\n           }; /* end slot0 */   \n        }; /* end slots */\n     }; /* end hsm1 */\n  }; /* end hsms */\n  \n  /*--- LOG CONFIGURATION SECTION -----------------------------------------*/\n  log:\n  {\n    file = "%PUBLIC%\\Securosys\\Primus P11\\primus.log";  /* for windows */\n    trace_linenumber    = false;     /* true or false */\n    trace_timestamp     = true;     /* true or false */ \n    trace_function      = true;     /* true or false */\n    trace_inout         = false;    /* true or false */\n    trace_pid           = true;     /* true or false */ \n    trace_filename      = false;    /* true or false */\n    trace_mask          = 0x00;\n    trace_level         = 4;        /* 0-7 log level details */\n  }; /* end log */\n}; /* end primus */\n'})}),"\n",(0,i.jsxs)(n.p,{children:["For configuration value details or variants consult ",(0,i.jsx)(n.a,{href:"/pkcs/Installation/pkcs11_provider_configuration",children:"Primus PKCS#11 Provider User Guide -  Configuration"}),"."]})]})}function d(e={}){const{wrapper:n}={...(0,o.R)(),...e.components};return n?(0,i.jsx)(n,{...e,children:(0,i.jsx)(c,{...e})}):c(e)}},79329:(e,n,r)=>{r.d(n,{A:()=>s});r(96540);var t=r(34164);const i={tabItem:"tabItem_Ymn6"};var o=r(74848);function s(e){let{children:n,hidden:r,className:s}=e;return(0,o.jsx)("div",{role:"tabpanel",className:(0,t.A)(i.tabItem,s),hidden:r,children:n})}}}]);