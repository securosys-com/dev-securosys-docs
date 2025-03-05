"use strict";(self.webpackChunksecurosys_docs=self.webpackChunksecurosys_docs||[]).push([[7299],{28453:(e,n,t)=>{t.d(n,{R:()=>o,x:()=>s});var r=t(96540);const i={},a=r.createContext(i);function o(e){const n=r.useContext(a);return r.useMemo((function(){return"function"==typeof e?e(n):{...n,...e}}),[n,e])}function s(e){let n;return n=e.disableParentContext?"function"==typeof e.components?e.components(i):e.components||i:o(e.components),r.createElement(a.Provider,{value:n},e.children)}},58363:(e,n,t)=>{t.r(n),t.d(n,{assets:()=>u,contentTitle:()=>c,default:()=>h,frontMatter:()=>l,metadata:()=>r,toc:()=>d});const r=JSON.parse('{"id":"Installation/Setup/UserGuide/Configuration/configuration","title":"XML-Configuration","description":"The security settings and network settings parameters may also be imported via XML.","source":"@site/hsm/Installation/Setup/UserGuide/3_Configuration/configuration.md","sourceDirName":"Installation/Setup/UserGuide/3_Configuration","slug":"/Installation/Setup/UserGuide/Configuration/configuration","permalink":"/hsm/Installation/Setup/UserGuide/Configuration/configuration","draft":false,"unlisted":false,"tags":[],"version":"current","lastUpdatedAt":1741162132000,"frontMatter":{},"sidebar":"tutorialSidebar","previous":{"title":"Policy Setup","permalink":"/hsm/Installation/Setup/UserGuide/Policy/policy"},"next":{"title":"Reference config (v2.11.1)","permalink":"/hsm/Installation/Setup/UserGuide/Configuration/reference-configuration"}}');var i=t(74848),a=t(28453),o=t(65537),s=t(79329);const l={},c="XML-Configuration",u={},d=[{value:"Configuration with XML (Export and Import)",id:"configuration-with-xml-export-and-import",level:2}];function f(e){const n={a:"a",admonition:"admonition",code:"code",h1:"h1",h2:"h2",header:"header",hr:"hr",li:"li",p:"p",pre:"pre",ul:"ul",...(0,a.R)(),...e.components};return(0,i.jsxs)(i.Fragment,{children:[(0,i.jsx)(n.header,{children:(0,i.jsx)(n.h1,{id:"xml-configuration",children:"XML-Configuration"})}),"\n",(0,i.jsxs)(n.p,{children:["The security settings and network settings parameters may also be imported via XML.",(0,i.jsx)("br",{}),"\nThus, configuration management of multiple HSM can be greatly simplified. It is recommended to keep a copy of the configuration of each HSM, for reference."]}),"\n",(0,i.jsx)(n.hr,{}),"\n",(0,i.jsx)(n.h2,{id:"configuration-with-xml-export-and-import",children:"Configuration with XML (Export and Import)"}),"\n",(0,i.jsxs)(n.p,{children:["Check annotated ",(0,i.jsx)(n.a,{href:"/hsm/Installation/Setup/UserGuide/Configuration/reference-configuration",children:"Reference - Configuration"}),(0,i.jsx)("br",{}),"\nIt is best practice to export the configuration before manually modifying it."]}),"\n",(0,i.jsxs)(n.p,{children:["The default file name is ",(0,i.jsx)(n.code,{children:"<deviceName>.sconfig"}),". If device attestation is initialized, an archive ",(0,i.jsx)(n.code,{children:"<deviceName>.sconfig.zip"})," is created.",(0,i.jsx)("br",{}),"\nThe configuration XML file consists of network config, device security config and per user configurations.",(0,i.jsx)("br",{}),"\nEdit the xml configuration file ",(0,i.jsx)(n.code,{children:"<deviceName>.sconfig"})," according to your needs."]}),"\n",(0,i.jsxs)(o.A,{groupId:"device-setup",children:[(0,i.jsxs)(s.A,{value:"x-series",label:"Primus X-Series device",default:!0,children:[(0,i.jsxs)(n.ul,{children:["\n",(0,i.jsx)(n.li,{children:"Export the configuration to USB stick:"}),"\n"]}),(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{children:"    SETUP \u2192 CONFIGURATION \u2192 IMPORT/EXPORT \u2192 EXPORT SECURITY CONFIG\n"})}),(0,i.jsxs)(n.ul,{children:["\n",(0,i.jsx)(n.li,{children:"Import the configuration from USB stick (*.sconfig):"}),"\n"]}),(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{children:"   SETUP \u2192 CONFIGURATION \u2192 IMPORT/EXPORT \u2192 IMPORT SECURITY CONFIG\n"})})]}),(0,i.jsxs)(s.A,{value:"e-series",label:"Primus E-Series device",children:[(0,i.jsxs)(n.ul,{children:["\n",(0,i.jsx)(n.li,{children:"Export the configuration to USB stick:"}),"\n"]}),(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{children:"    hsm_sec_export_config [filename]\n"})}),(0,i.jsxs)(n.ul,{children:["\n",(0,i.jsx)(n.li,{children:"Import the configuration from USB stick (*.sconfig):"}),"\n"]}),(0,i.jsx)(n.pre,{children:(0,i.jsx)(n.code,{children:"   hsm_sec_import_config\n"})})]})]}),"\n",(0,i.jsx)(n.admonition,{title:"multiple *.sconfig",type:"danger",children:(0,i.jsxs)(n.ul,{children:["\n",(0,i.jsxs)(n.li,{children:["\n",(0,i.jsx)(n.p,{children:"If there are multiple *.sconfig files on the USB stick, import via Console will show a file selection of max. 8 files, and import via front panel GUI will fail (file selection not supported)."}),"\n"]}),"\n",(0,i.jsxs)(n.li,{children:["\n",(0,i.jsxs)(n.p,{children:["Note that import does not allow to create new user partitions (they must already exist). Stated parameters without value remain unchanged (e.g. ",(0,i.jsx)(n.code,{children:"<pkcs_password/>"}),")."]}),"\n"]}),"\n",(0,i.jsxs)(n.li,{children:["\n",(0,i.jsxs)(n.p,{children:["Missing parameters are reset to factory default (e.g. not having a ",(0,i.jsx)(n.code,{children:"<pkcs_password/>"})," statement deletes the PKCS#11 password)."]}),"\n"]}),"\n",(0,i.jsxs)(n.li,{children:["\n",(0,i.jsx)(n.p,{children:"In Cloning and HA Cloning, most of the Security settings are cloned from the Master onto the Clone and can therefore not be changed by XML import on the Clone."}),"\n"]}),"\n",(0,i.jsxs)(n.li,{children:["\n",(0,i.jsx)(n.p,{children:"In case of failure, export the Security Log (see 4.7) and check the file crypto.log for more details."}),"\n"]}),"\n"]})})]})}function h(e={}){const{wrapper:n}={...(0,a.R)(),...e.components};return n?(0,i.jsx)(n,{...e,children:(0,i.jsx)(f,{...e})}):f(e)}},65537:(e,n,t)=>{t.d(n,{A:()=>y});var r=t(96540),i=t(34164),a=t(65627),o=t(56347),s=t(50372),l=t(30604),c=t(11861),u=t(78749);function d(e){return r.Children.toArray(e).filter((e=>"\n"!==e)).map((e=>{if(!e||(0,r.isValidElement)(e)&&function(e){const{props:n}=e;return!!n&&"object"==typeof n&&"value"in n}(e))return e;throw new Error(`Docusaurus error: Bad <Tabs> child <${"string"==typeof e.type?e.type:e.type.name}>: all children of the <Tabs> component should be <TabItem>, and every <TabItem> should have a unique "value" prop.`)}))?.filter(Boolean)??[]}function f(e){const{values:n,children:t}=e;return(0,r.useMemo)((()=>{const e=n??function(e){return d(e).map((e=>{let{props:{value:n,label:t,attributes:r,default:i}}=e;return{value:n,label:t,attributes:r,default:i}}))}(t);return function(e){const n=(0,c.XI)(e,((e,n)=>e.value===n.value));if(n.length>0)throw new Error(`Docusaurus error: Duplicate values "${n.map((e=>e.value)).join(", ")}" found in <Tabs>. Every value needs to be unique.`)}(e),e}),[n,t])}function h(e){let{value:n,tabValues:t}=e;return t.some((e=>e.value===n))}function p(e){let{queryString:n=!1,groupId:t}=e;const i=(0,o.W6)(),a=function(e){let{queryString:n=!1,groupId:t}=e;if("string"==typeof n)return n;if(!1===n)return null;if(!0===n&&!t)throw new Error('Docusaurus error: The <Tabs> component groupId prop is required if queryString=true, because this value is used as the search param name. You can also provide an explicit value such as queryString="my-search-param".');return t??null}({queryString:n,groupId:t});return[(0,l.aZ)(a),(0,r.useCallback)((e=>{if(!a)return;const n=new URLSearchParams(i.location.search);n.set(a,e),i.replace({...i.location,search:n.toString()})}),[a,i])]}function m(e){const{defaultValue:n,queryString:t=!1,groupId:i}=e,a=f(e),[o,l]=(0,r.useState)((()=>function(e){let{defaultValue:n,tabValues:t}=e;if(0===t.length)throw new Error("Docusaurus error: the <Tabs> component requires at least one <TabItem> children component");if(n){if(!h({value:n,tabValues:t}))throw new Error(`Docusaurus error: The <Tabs> has a defaultValue "${n}" but none of its children has the corresponding value. Available values are: ${t.map((e=>e.value)).join(", ")}. If you intend to show no default tab, use defaultValue={null} instead.`);return n}const r=t.find((e=>e.default))??t[0];if(!r)throw new Error("Unexpected error: 0 tabValues");return r.value}({defaultValue:n,tabValues:a}))),[c,d]=p({queryString:t,groupId:i}),[m,g]=function(e){let{groupId:n}=e;const t=function(e){return e?`docusaurus.tab.${e}`:null}(n),[i,a]=(0,u.Dv)(t);return[i,(0,r.useCallback)((e=>{t&&a.set(e)}),[t,a])]}({groupId:i}),x=(()=>{const e=c??m;return h({value:e,tabValues:a})?e:null})();(0,s.A)((()=>{x&&l(x)}),[x]);return{selectedValue:o,selectValue:(0,r.useCallback)((e=>{if(!h({value:e,tabValues:a}))throw new Error(`Can't select invalid tab value=${e}`);l(e),d(e),g(e)}),[d,g,a]),tabValues:a}}var g=t(9136);const x={tabList:"tabList__CuJ",tabItem:"tabItem_LNqP"};var b=t(74848);function v(e){let{className:n,block:t,selectedValue:r,selectValue:o,tabValues:s}=e;const l=[],{blockElementScrollPositionUntilNextRender:c}=(0,a.a_)(),u=e=>{const n=e.currentTarget,t=l.indexOf(n),i=s[t].value;i!==r&&(c(n),o(i))},d=e=>{let n=null;switch(e.key){case"Enter":u(e);break;case"ArrowRight":{const t=l.indexOf(e.currentTarget)+1;n=l[t]??l[0];break}case"ArrowLeft":{const t=l.indexOf(e.currentTarget)-1;n=l[t]??l[l.length-1];break}}n?.focus()};return(0,b.jsx)("ul",{role:"tablist","aria-orientation":"horizontal",className:(0,i.A)("tabs",{"tabs--block":t},n),children:s.map((e=>{let{value:n,label:t,attributes:a}=e;return(0,b.jsx)("li",{role:"tab",tabIndex:r===n?0:-1,"aria-selected":r===n,ref:e=>{l.push(e)},onKeyDown:d,onClick:u,...a,className:(0,i.A)("tabs__item",x.tabItem,a?.className,{"tabs__item--active":r===n}),children:t??n},n)}))})}function j(e){let{lazy:n,children:t,selectedValue:a}=e;const o=(Array.isArray(t)?t:[t]).filter(Boolean);if(n){const e=o.find((e=>e.props.value===a));return e?(0,r.cloneElement)(e,{className:(0,i.A)("margin-top--md",e.props.className)}):null}return(0,b.jsx)("div",{className:"margin-top--md",children:o.map(((e,n)=>(0,r.cloneElement)(e,{key:n,hidden:e.props.value!==a})))})}function I(e){const n=m(e);return(0,b.jsxs)("div",{className:(0,i.A)("tabs-container",x.tabList),children:[(0,b.jsx)(v,{...n,...e}),(0,b.jsx)(j,{...n,...e})]})}function y(e){const n=(0,g.A)();return(0,b.jsx)(I,{...e,children:d(e.children)},String(n))}},79329:(e,n,t)=>{t.d(n,{A:()=>o});t(96540);var r=t(34164);const i={tabItem:"tabItem_Ymn6"};var a=t(74848);function o(e){let{children:n,hidden:t,className:o}=e;return(0,a.jsx)("div",{role:"tabpanel",className:(0,r.A)(i.tabItem,o),hidden:t,children:n})}}}]);