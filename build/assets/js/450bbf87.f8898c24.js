"use strict";(self.webpackChunksecurosys_docs=self.webpackChunksecurosys_docs||[]).push([[66971],{28453:(e,t,n)=>{n.d(t,{R:()=>s,x:()=>a});var r=n(96540);const o={},i=r.createContext(o);function s(e){const t=r.useContext(i);return r.useMemo((function(){return"function"==typeof e?e(t):{...t,...e}}),[t,e])}function a(e){let t;return t=e.disableParentContext?"function"==typeof e.components?e.components(o):e.components||o:s(e.components),r.createElement(i.Provider,{value:t},e.children)}},38811:(e,t,n)=>{n.r(t),n.d(t,{assets:()=>l,contentTitle:()=>a,default:()=>d,frontMatter:()=>s,metadata:()=>r,toc:()=>c});const r=JSON.parse('{"id":"Tutorials/troubleshooting","title":"Docker Encryption Plugin - Troubleshooting","description":"Docker Encryption - Troubleshooting","source":"@site/docker_encryption/Tutorials/troubleshooting.md","sourceDirName":"Tutorials","slug":"/Tutorials/troubleshooting","permalink":"/docker_encryption/Tutorials/troubleshooting","draft":false,"unlisted":false,"tags":[],"version":"current","lastUpdatedAt":1741162132000,"sidebarPosition":3,"frontMatter":{"sidebar_position":3,"title":"Docker Encryption Plugin - Troubleshooting","sidebar_label":"Troubleshooting","description":"Docker Encryption - Troubleshooting","keywords":["hsm","cloud hsm"]},"sidebar":"tutorialSidebar","previous":{"title":"Decrypt an Image","permalink":"/docker_encryption/Tutorials/decrypt"},"next":{"title":"Concepts","permalink":"/docker_encryption/category/concepts"}}');var o=n(74848),i=n(28453);n(65537),n(79329);const s={sidebar_position:3,title:"Docker Encryption Plugin - Troubleshooting",sidebar_label:"Troubleshooting",description:"Docker Encryption - Troubleshooting",keywords:["hsm","cloud hsm"]},a="Troubleshooting",l={},c=[{value:"Troubleshoot the Securosys Docker Image Encryption Plugin",id:"troubleshoot-the-securosys-docker-image-encryption-plugin",level:2},{value:"Skopeo",id:"skopeo",level:3},{value:"HSM Connectivity",id:"hsm-connectivity",level:3},{value:"Testing TSB connectivity",id:"testing-tsb-connectivity",level:3},{value:"Testing complete communication path",id:"testing-complete-communication-path",level:3},{value:"Troubleshooting Primus HSM",id:"troubleshooting-primus-hsm",level:2}];function u(e){const t={a:"a",admonition:"admonition",code:"code",h1:"h1",h2:"h2",h3:"h3",header:"header",li:"li",p:"p",pre:"pre",strong:"strong",ul:"ul",...(0,i.R)(),...e.components};return(0,o.jsxs)(o.Fragment,{children:[(0,o.jsx)(t.header,{children:(0,o.jsx)(t.h1,{id:"troubleshooting",children:"Troubleshooting"})}),"\n",(0,o.jsx)(t.h2,{id:"troubleshoot-the-securosys-docker-image-encryption-plugin",children:"Troubleshoot the Securosys Docker Image Encryption Plugin"}),"\n",(0,o.jsx)(t.h3,{id:"skopeo",children:"Skopeo"}),"\n",(0,o.jsx)(t.p,{children:"The troubleshooting capabilities of the plugin and Skopeo is limited. Please review any error messages carefully upon command execution."}),"\n",(0,o.jsx)(t.h3,{id:"hsm-connectivity",children:"HSM Connectivity"}),"\n",(0,o.jsx)(t.admonition,{title:"NOTE",type:"note",children:(0,o.jsxs)(t.p,{children:["When utilizing CloudHSM service, refer to ",(0,o.jsx)(t.a,{href:"/connectivity-details/cloudhsm-connectivity-details",children:"Cloud Connectivity Details"})," for accurate API-Endpoint URI. For on-premise deployments, verify API-Endpoint URI with your administrator. Contact your service administrator for authentication credentials in any setup (on-prem or cloud)."]})}),"\n",(0,o.jsx)(t.h3,{id:"testing-tsb-connectivity",children:"Testing TSB connectivity"}),"\n",(0,o.jsxs)(t.p,{children:["Connectivity to the TSB can be tested using the curl command below or via swaggerUI. This test does not verify the connectiviity with the HSM infrastrustructure. Adapt ",(0,o.jsx)(t.strong,{children:(0,o.jsx)(t.code,{children:"<TSB_APIendpoint>"})}),", ",(0,o.jsx)(t.strong,{children:(0,o.jsx)(t.code,{children:"<yourBearerToken>"})})," for your environment:"]}),"\n",(0,o.jsx)(t.pre,{children:(0,o.jsx)(t.code,{className:"language-sh",children:"curl -X\n    'GET' '<TSB_APIendpoint>/v1/versionInfo' \\   \n    -H 'accept: application/json' \\\n    -H 'Authorization: Bearer <yourBearerToken>'\n"})}),"\n",(0,o.jsx)(t.h3,{id:"testing-complete-communication-path",children:"Testing complete communication path"}),"\n",(0,o.jsxs)(t.p,{children:["Connectivity through the TSB to the HSM infrastructure can be tested using the curl command below or via swaggerUI. Adapt ",(0,o.jsx)(t.strong,{children:(0,o.jsx)(t.code,{children:"<TSB_APIendpoint>"})}),", ",(0,o.jsx)(t.strong,{children:(0,o.jsx)(t.code,{children:"<yourBearerToken>"})})," for your environment:"]}),"\n",(0,o.jsx)(t.pre,{children:(0,o.jsx)(t.code,{className:"language-sh",children:"curl -X\n    'GET' '<TSB_APIendpoint>/v1/licenseInfo' \\   \n    -H 'accept: application/json' \\\n    -H 'Authorization: Bearer <yourBearerToken>'\n"})}),"\n",(0,o.jsx)(t.h2,{id:"troubleshooting-primus-hsm",children:"Troubleshooting Primus HSM"}),"\n",(0,o.jsx)(t.p,{children:"Additional information can be obtained by reviewing the HSM logs."}),"\n",(0,o.jsxs)(t.ul,{children:["\n",(0,o.jsxs)(t.li,{children:["To review the HSM logs of your on-premises Primus HSM please refer to the ",(0,o.jsx)(t.a,{href:"https://support.securosys.com/external/knowledge-base/article/63",children:"Securosys Primus HSM User Guide"})," (account required) chapter 4.7."]}),"\n"]})]})}function d(e={}){const{wrapper:t}={...(0,i.R)(),...e.components};return t?(0,o.jsx)(t,{...e,children:(0,o.jsx)(u,{...e})}):u(e)}},65537:(e,t,n)=>{n.d(t,{A:()=>j});var r=n(96540),o=n(34164),i=n(65627),s=n(56347),a=n(50372),l=n(30604),c=n(11861),u=n(78749);function d(e){return r.Children.toArray(e).filter((e=>"\n"!==e)).map((e=>{if(!e||(0,r.isValidElement)(e)&&function(e){const{props:t}=e;return!!t&&"object"==typeof t&&"value"in t}(e))return e;throw new Error(`Docusaurus error: Bad <Tabs> child <${"string"==typeof e.type?e.type:e.type.name}>: all children of the <Tabs> component should be <TabItem>, and every <TabItem> should have a unique "value" prop.`)}))?.filter(Boolean)??[]}function h(e){const{values:t,children:n}=e;return(0,r.useMemo)((()=>{const e=t??function(e){return d(e).map((e=>{let{props:{value:t,label:n,attributes:r,default:o}}=e;return{value:t,label:n,attributes:r,default:o}}))}(n);return function(e){const t=(0,c.XI)(e,((e,t)=>e.value===t.value));if(t.length>0)throw new Error(`Docusaurus error: Duplicate values "${t.map((e=>e.value)).join(", ")}" found in <Tabs>. Every value needs to be unique.`)}(e),e}),[t,n])}function p(e){let{value:t,tabValues:n}=e;return n.some((e=>e.value===t))}function m(e){let{queryString:t=!1,groupId:n}=e;const o=(0,s.W6)(),i=function(e){let{queryString:t=!1,groupId:n}=e;if("string"==typeof t)return t;if(!1===t)return null;if(!0===t&&!n)throw new Error('Docusaurus error: The <Tabs> component groupId prop is required if queryString=true, because this value is used as the search param name. You can also provide an explicit value such as queryString="my-search-param".');return n??null}({queryString:t,groupId:n});return[(0,l.aZ)(i),(0,r.useCallback)((e=>{if(!i)return;const t=new URLSearchParams(o.location.search);t.set(i,e),o.replace({...o.location,search:t.toString()})}),[i,o])]}function b(e){const{defaultValue:t,queryString:n=!1,groupId:o}=e,i=h(e),[s,l]=(0,r.useState)((()=>function(e){let{defaultValue:t,tabValues:n}=e;if(0===n.length)throw new Error("Docusaurus error: the <Tabs> component requires at least one <TabItem> children component");if(t){if(!p({value:t,tabValues:n}))throw new Error(`Docusaurus error: The <Tabs> has a defaultValue "${t}" but none of its children has the corresponding value. Available values are: ${n.map((e=>e.value)).join(", ")}. If you intend to show no default tab, use defaultValue={null} instead.`);return t}const r=n.find((e=>e.default))??n[0];if(!r)throw new Error("Unexpected error: 0 tabValues");return r.value}({defaultValue:t,tabValues:i}))),[c,d]=m({queryString:n,groupId:o}),[b,g]=function(e){let{groupId:t}=e;const n=function(e){return e?`docusaurus.tab.${e}`:null}(t),[o,i]=(0,u.Dv)(n);return[o,(0,r.useCallback)((e=>{n&&i.set(e)}),[n,i])]}({groupId:o}),f=(()=>{const e=c??b;return p({value:e,tabValues:i})?e:null})();(0,a.A)((()=>{f&&l(f)}),[f]);return{selectedValue:s,selectValue:(0,r.useCallback)((e=>{if(!p({value:e,tabValues:i}))throw new Error(`Can't select invalid tab value=${e}`);l(e),d(e),g(e)}),[d,g,i]),tabValues:i}}var g=n(9136);const f={tabList:"tabList__CuJ",tabItem:"tabItem_LNqP"};var v=n(74848);function y(e){let{className:t,block:n,selectedValue:r,selectValue:s,tabValues:a}=e;const l=[],{blockElementScrollPositionUntilNextRender:c}=(0,i.a_)(),u=e=>{const t=e.currentTarget,n=l.indexOf(t),o=a[n].value;o!==r&&(c(t),s(o))},d=e=>{let t=null;switch(e.key){case"Enter":u(e);break;case"ArrowRight":{const n=l.indexOf(e.currentTarget)+1;t=l[n]??l[0];break}case"ArrowLeft":{const n=l.indexOf(e.currentTarget)-1;t=l[n]??l[l.length-1];break}}t?.focus()};return(0,v.jsx)("ul",{role:"tablist","aria-orientation":"horizontal",className:(0,o.A)("tabs",{"tabs--block":n},t),children:a.map((e=>{let{value:t,label:n,attributes:i}=e;return(0,v.jsx)("li",{role:"tab",tabIndex:r===t?0:-1,"aria-selected":r===t,ref:e=>{l.push(e)},onKeyDown:d,onClick:u,...i,className:(0,o.A)("tabs__item",f.tabItem,i?.className,{"tabs__item--active":r===t}),children:n??t},t)}))})}function T(e){let{lazy:t,children:n,selectedValue:i}=e;const s=(Array.isArray(n)?n:[n]).filter(Boolean);if(t){const e=s.find((e=>e.props.value===i));return e?(0,r.cloneElement)(e,{className:(0,o.A)("margin-top--md",e.props.className)}):null}return(0,v.jsx)("div",{className:"margin-top--md",children:s.map(((e,t)=>(0,r.cloneElement)(e,{key:t,hidden:e.props.value!==i})))})}function x(e){const t=b(e);return(0,v.jsxs)("div",{className:(0,o.A)("tabs-container",f.tabList),children:[(0,v.jsx)(y,{...t,...e}),(0,v.jsx)(T,{...t,...e})]})}function j(e){const t=(0,g.A)();return(0,v.jsx)(x,{...e,children:d(e.children)},String(t))}},79329:(e,t,n)=>{n.d(t,{A:()=>s});n(96540);var r=n(34164);const o={tabItem:"tabItem_Ymn6"};var i=n(74848);function s(e){let{children:t,hidden:n,className:s}=e;return(0,i.jsx)("div",{role:"tabpanel",className:(0,r.A)(o.tabItem,s),hidden:n,children:t})}}}]);