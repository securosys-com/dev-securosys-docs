"use strict";(self.webpackChunksecurosys_docs=self.webpackChunksecurosys_docs||[]).push([[8442],{28453:(e,t,r)=>{r.d(t,{R:()=>o,x:()=>l});var a=r(96540);const n={},s=a.createContext(n);function o(e){const t=a.useContext(s);return a.useMemo((function(){return"function"==typeof e?e(t):{...t,...e}}),[t,e])}function l(e){let t;return t=e.disableParentContext?"function"==typeof e.components?e.components(n):e.components||n:o(e.components),a.createElement(s.Provider,{value:t},e.children)}},65537:(e,t,r)=>{r.d(t,{A:()=>S});var a=r(96540),n=r(34164),s=r(65627),o=r(56347),l=r(50372),i=r(30604),d=r(11861),c=r(78749);function u(e){return a.Children.toArray(e).filter((e=>"\n"!==e)).map((e=>{if(!e||(0,a.isValidElement)(e)&&function(e){const{props:t}=e;return!!t&&"object"==typeof t&&"value"in t}(e))return e;throw new Error(`Docusaurus error: Bad <Tabs> child <${"string"==typeof e.type?e.type:e.type.name}>: all children of the <Tabs> component should be <TabItem>, and every <TabItem> should have a unique "value" prop.`)}))?.filter(Boolean)??[]}function h(e){const{values:t,children:r}=e;return(0,a.useMemo)((()=>{const e=t??function(e){return u(e).map((e=>{let{props:{value:t,label:r,attributes:a,default:n}}=e;return{value:t,label:r,attributes:a,default:n}}))}(r);return function(e){const t=(0,d.XI)(e,((e,t)=>e.value===t.value));if(t.length>0)throw new Error(`Docusaurus error: Duplicate values "${t.map((e=>e.value)).join(", ")}" found in <Tabs>. Every value needs to be unique.`)}(e),e}),[t,r])}function p(e){let{value:t,tabValues:r}=e;return r.some((e=>e.value===t))}function b(e){let{queryString:t=!1,groupId:r}=e;const n=(0,o.W6)(),s=function(e){let{queryString:t=!1,groupId:r}=e;if("string"==typeof t)return t;if(!1===t)return null;if(!0===t&&!r)throw new Error('Docusaurus error: The <Tabs> component groupId prop is required if queryString=true, because this value is used as the search param name. You can also provide an explicit value such as queryString="my-search-param".');return r??null}({queryString:t,groupId:r});return[(0,i.aZ)(s),(0,a.useCallback)((e=>{if(!s)return;const t=new URLSearchParams(n.location.search);t.set(s,e),n.replace({...n.location,search:t.toString()})}),[s,n])]}function m(e){const{defaultValue:t,queryString:r=!1,groupId:n}=e,s=h(e),[o,i]=(0,a.useState)((()=>function(e){let{defaultValue:t,tabValues:r}=e;if(0===r.length)throw new Error("Docusaurus error: the <Tabs> component requires at least one <TabItem> children component");if(t){if(!p({value:t,tabValues:r}))throw new Error(`Docusaurus error: The <Tabs> has a defaultValue "${t}" but none of its children has the corresponding value. Available values are: ${r.map((e=>e.value)).join(", ")}. If you intend to show no default tab, use defaultValue={null} instead.`);return t}const a=r.find((e=>e.default))??r[0];if(!a)throw new Error("Unexpected error: 0 tabValues");return a.value}({defaultValue:t,tabValues:s}))),[d,u]=b({queryString:r,groupId:n}),[m,f]=function(e){let{groupId:t}=e;const r=function(e){return e?`docusaurus.tab.${e}`:null}(t),[n,s]=(0,c.Dv)(r);return[n,(0,a.useCallback)((e=>{r&&s.set(e)}),[r,s])]}({groupId:n}),x=(()=>{const e=d??m;return p({value:e,tabValues:s})?e:null})();(0,l.A)((()=>{x&&i(x)}),[x]);return{selectedValue:o,selectValue:(0,a.useCallback)((e=>{if(!p({value:e,tabValues:s}))throw new Error(`Can't select invalid tab value=${e}`);i(e),u(e),f(e)}),[u,f,s]),tabValues:s}}var f=r(9136);const x={tabList:"tabList__CuJ",tabItem:"tabItem_LNqP"};var y=r(74848);function k(e){let{className:t,block:r,selectedValue:a,selectValue:o,tabValues:l}=e;const i=[],{blockElementScrollPositionUntilNextRender:d}=(0,s.a_)(),c=e=>{const t=e.currentTarget,r=i.indexOf(t),n=l[r].value;n!==a&&(d(t),o(n))},u=e=>{let t=null;switch(e.key){case"Enter":c(e);break;case"ArrowRight":{const r=i.indexOf(e.currentTarget)+1;t=i[r]??i[0];break}case"ArrowLeft":{const r=i.indexOf(e.currentTarget)-1;t=i[r]??i[i.length-1];break}}t?.focus()};return(0,y.jsx)("ul",{role:"tablist","aria-orientation":"horizontal",className:(0,n.A)("tabs",{"tabs--block":r},t),children:l.map((e=>{let{value:t,label:r,attributes:s}=e;return(0,y.jsx)("li",{role:"tab",tabIndex:a===t?0:-1,"aria-selected":a===t,ref:e=>{i.push(e)},onKeyDown:u,onClick:c,...s,className:(0,n.A)("tabs__item",x.tabItem,s?.className,{"tabs__item--active":a===t}),children:r??t},t)}))})}function w(e){let{lazy:t,children:r,selectedValue:s}=e;const o=(Array.isArray(r)?r:[r]).filter(Boolean);if(t){const e=o.find((e=>e.props.value===s));return e?(0,a.cloneElement)(e,{className:(0,n.A)("margin-top--md",e.props.className)}):null}return(0,y.jsx)("div",{className:"margin-top--md",children:o.map(((e,t)=>(0,a.cloneElement)(e,{key:t,hidden:e.props.value!==s})))})}function j(e){const t=m(e);return(0,y.jsxs)("div",{className:(0,n.A)("tabs-container",x.tabList),children:[(0,y.jsx)(k,{...t,...e}),(0,y.jsx)(w,{...t,...e})]})}function S(e){const t=(0,f.A)();return(0,y.jsx)(j,{...e,children:u(e.children)},String(t))}},79329:(e,t,r)=>{r.d(t,{A:()=>o});r(96540);var a=r(34164);const n={tabItem:"tabItem_Ymn6"};var s=r(74848);function o(e){let{children:t,hidden:r,className:o}=e;return(0,s.jsx)("div",{role:"tabpanel",className:(0,a.A)(n.tabItem,o),hidden:r,children:t})}},88202:(e,t,r)=>{r.r(t),r.d(t,{assets:()=>i,contentTitle:()=>l,default:()=>u,frontMatter:()=>o,metadata:()=>a,toc:()=>d});const a=JSON.parse('{"id":"Tutorials/Export&Wrap","title":"AWS BYOK - Export & Wrap Key Material","description":"The Securosys HSM records an entry in the logs when performing actions with keys as well as any established or failed authentication.","source":"@site/aws-byok/Tutorials/Export&Wrap.md","sourceDirName":"Tutorials","slug":"/Tutorials/Export&Wrap","permalink":"/aws-byok/Tutorials/Export&Wrap","draft":false,"unlisted":false,"tags":[],"version":"current","lastUpdatedAt":1741162132000,"sidebarPosition":6,"frontMatter":{"sidebar_position":6,"title":"AWS BYOK - Export & Wrap Key Material","sidebar_label":"Export & Wrap Key Material"},"sidebar":"tutorialSidebar","previous":{"title":"Create Key on HSM","permalink":"/aws-byok/Tutorials/Create-key-HSM"},"next":{"title":"Import Key Material","permalink":"/aws-byok/Tutorials/Import-Key-Material"}}');var n=r(74848),s=r(28453);r(65537),r(79329);const o={sidebar_position:6,title:"AWS BYOK - Export & Wrap Key Material",sidebar_label:"Export & Wrap Key Material"},l="Export & Wrap Key Material",i={},d=[];function c(e){const t={a:"a",code:"code",h1:"h1",header:"header",p:"p",pre:"pre",table:"table",tbody:"tbody",td:"td",th:"th",thead:"thead",tr:"tr",...(0,s.R)(),...e.components};return(0,n.jsxs)(n.Fragment,{children:[(0,n.jsx)(t.header,{children:(0,n.jsx)(t.h1,{id:"export--wrap-key-material",children:"Export & Wrap Key Material"})}),"\n",(0,n.jsxs)(t.p,{children:["The Securosys HSM records an entry in the logs when performing actions with keys as well as any established or failed authentication.\nUse the ",(0,n.jsx)(t.code,{children:"AwsKmsByokExport"})," command to wrap the specified key on the HSM using the public key that was downloaded from AWS KMS, see chapter ",(0,n.jsx)(t.a,{href:"/aws-byok/Tutorials/Download-Pub-key",children:"Downloading Public Key and Import Token"}),". The same wrapping algorithm (either ",(0,n.jsx)(t.code,{children:"RSAES_OAEP_SHA_256"})," or ",(0,n.jsx)(t.code,{children:"RSAES_OAEP_SHA_1"}),") must be specified."]}),"\n",(0,n.jsxs)(t.p,{children:[(0,n.jsx)(t.code,{children:"AwsKmsByokExport"})," sample command:"]}),"\n",(0,n.jsx)(t.pre,{children:(0,n.jsx)(t.code,{className:"language-bash",children:"java -jar primus-tools.jar AwsKmsByokExport <HSM connection and credentials>\\\n-aeskey AES-KEY-NAME [-aeskeypassword <HSM AES key password>] \\\n-kekfile <kek public key file, DER or PEM format> \\\n[-kekdata <kek public key data, base64 of DER>] \\\n-outfile <AWS KMS BYOK encrypted key output file>\n"})}),"\n",(0,n.jsx)(t.p,{children:"Example command:"}),"\n",(0,n.jsx)(t.pre,{children:(0,n.jsx)(t.code,{className:"language-bash",children:"java -jar primus-tools.jar AwsKmsByokExport \\\n--host a-api.cloudshsm.com -port 2300 \\\n-user DEMO-TEST -password file:pwsetup \\\n-primusproxyuser FQO...QQOS -primusproxypassword file:pwproxy -aeskey AWS-ByokAES256 \\\n-kekdata public_key_b64 -outfile ImportKey\n"})}),"\n",(0,n.jsxs)(t.table,{children:[(0,n.jsx)(t.thead,{children:(0,n.jsxs)(t.tr,{children:[(0,n.jsx)(t.th,{children:"Parameter"}),(0,n.jsx)(t.th,{children:"Description"})]})}),(0,n.jsxs)(t.tbody,{children:[(0,n.jsxs)(t.tr,{children:[(0,n.jsx)(t.td,{children:(0,n.jsx)(t.code,{children:"-aeskey"})}),(0,n.jsxs)(t.td,{children:["Replace the ",(0,n.jsx)(t.code,{children:"-aeskey"})," parameter variable ",(0,n.jsx)(t.code,{children:"AES-KEY-NAME"})," with the key name of the key to be extracted from the HSM, wrapped and imported into AWS. Additionally, if the key was protected with a password the ",(0,n.jsx)(t.code,{children:"-password"})," parameter must be added to the command with where the variable ",(0,n.jsx)(t.code,{children:"<HSM AES key password>"})," should be replaced with its correspondent password."]})]}),(0,n.jsxs)(t.tr,{children:[(0,n.jsx)(t.td,{children:(0,n.jsx)(t.code,{children:"-mode"})}),(0,n.jsxs)(t.td,{children:["The ",(0,n.jsx)(t.code,{children:"-mode"})," parameter defines the mode with which to wrap the key material, default: ",(0,n.jsx)(t.code,{children:"RSAES_OAEP_SHA_256"}),", alternative: ",(0,n.jsx)(t.code,{children:"RSAES_OAEP_SHA_1"})]})]}),(0,n.jsxs)(t.tr,{children:[(0,n.jsx)(t.td,{children:(0,n.jsx)(t.code,{children:"-kekfile"})}),(0,n.jsxs)(t.td,{children:["Use the ",(0,n.jsx)(t.code,{children:"-kekfile"})," parameter if the public key was downloaded from the AWS KMS console, alternatively if the AWS KMS API was used to download the base 64 version of the public key the ",(0,n.jsx)(t.code,{children:"-kekdata"})," parameter should be used instead. Replace the variable ",(0,n.jsx)(t.code,{children:"<kek public key file, DER or PEM format>"})," with the downloaded public key file."]})]}),(0,n.jsxs)(t.tr,{children:[(0,n.jsx)(t.td,{children:(0,n.jsx)(t.code,{children:"- kekdata"})}),(0,n.jsxs)(t.td,{children:["Use the ",(0,n.jsx)(t.code,{children:"-kekdata"})," parameter if the base 64 encoded public key was downloaded with the AWS KMS API, alternatively if the AWS KMS Console was used to download the public key, the ",(0,n.jsx)(t.code,{children:"-kekfile"})," parameter should be used instead. Replace the variable ",(0,n.jsx)(t.code,{children:"<kek public key data, base64 of DER>"})," with the downloaded public key file."]})]}),(0,n.jsxs)(t.tr,{children:[(0,n.jsx)(t.td,{children:(0,n.jsx)(t.code,{children:"-outfile"})}),(0,n.jsxs)(t.td,{children:["Replace the ",(0,n.jsx)(t.code,{children:"-outfile"})," parameter variable ",(0,n.jsx)(t.code,{children:"<AWS KMS BYOK encrypted key output file>"})," with the file name of the to be exported and wrapped key material."]})]})]})]})]})}function u(e={}){const{wrapper:t}={...(0,s.R)(),...e.components};return t?(0,n.jsx)(t,{...e,children:(0,n.jsx)(c,{...e})}):c(e)}}}]);