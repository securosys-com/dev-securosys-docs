"use strict";(self.webpackChunksecurosys_docs=self.webpackChunksecurosys_docs||[]).push([[69401],{12037:(e,t,n)=>{n.r(t),n.d(t,{assets:()=>i,contentTitle:()=>s,default:()=>d,frontMatter:()=>l,metadata:()=>r,toc:()=>u});const r=JSON.parse('{"id":"Tutorials/Generate-KEK","title":"Azure BYOK - Generate Key Exchange Key","description":"The KEK is an RSA key that\u2019s generated in a Microsoft Key Vault HSM. The KEK is used to encrypt the key you","source":"@site/microsoft-byok/Tutorials/Generate-KEK.md","sourceDirName":"Tutorials","slug":"/Tutorials/Generate-KEK","permalink":"/microsoft-byok/Tutorials/Generate-KEK","draft":false,"unlisted":false,"tags":[],"version":"current","lastUpdatedAt":1741162132000,"sidebarPosition":0,"frontMatter":{"sidebar_position":0,"title":"Azure BYOK - Generate Key Exchange Key","sidebar_label":"Generate Key Exchange Key"},"sidebar":"tutorialSidebar","previous":{"title":"Tutorial","permalink":"/microsoft-byok/category/tutorial"},"next":{"title":"Generate and Wrap Target Key","permalink":"/microsoft-byok/Tutorials/Generate-Target-key"}}');var a=n(74848),o=n(28453);n(65537),n(79329);const l={sidebar_position:0,title:"Azure BYOK - Generate Key Exchange Key",sidebar_label:"Generate Key Exchange Key"},s="Generate Key Exchange Key",i={},u=[{value:"Download KEK",id:"download-kek",level:2}];function c(e){const t={a:"a",admonition:"admonition",code:"code",h1:"h1",h2:"h2",header:"header",li:"li",mdxAdmonitionTitle:"mdxAdmonitionTitle",p:"p",pre:"pre",ul:"ul",...(0,o.R)(),...e.components};return(0,a.jsxs)(a.Fragment,{children:[(0,a.jsx)(t.header,{children:(0,a.jsx)(t.h1,{id:"generate-key-exchange-key",children:"Generate Key Exchange Key"})}),"\n",(0,a.jsx)(t.p,{children:"The KEK is an RSA key that\u2019s generated in a Microsoft Key Vault HSM. The KEK is used to encrypt the key you\nwant to import (target key). The KEK must be:"}),"\n",(0,a.jsxs)(t.ul,{children:["\n",(0,a.jsx)(t.li,{children:"An RSA-HSM key (2\u2019048-bit, 3\u2019072-bit or 4\u2019096-bit),"}),"\n",(0,a.jsx)(t.li,{children:"Generated in the same key vault where you intend to import the target key,"}),"\n",(0,a.jsxs)(t.li,{children:["Created with allowed key operations set to ",(0,a.jsx)(t.code,{children:"import"}),"."]}),"\n"]}),"\n",(0,a.jsxs)(t.admonition,{type:"note",children:[(0,a.jsx)(t.mdxAdmonitionTitle,{}),(0,a.jsx)(t.p,{children:"Make sure to change the parameters in the example commands to your own."})]}),"\n",(0,a.jsx)(t.p,{children:"To generate the KEK, open the PowerShell and logon to Azure portal with the required subscription\nfor Azure Key Vault:"}),"\n",(0,a.jsx)(t.pre,{children:(0,a.jsx)(t.code,{className:"language-bash",children:"PS C:\\Users\\Public\\byok> az login\n"})}),"\n",(0,a.jsxs)(t.p,{children:["In case you need to create a new resource group and key vault, refer to ",(0,a.jsx)(t.a,{href:"/microsoft-byok/Installation/",children:"Installation"}),"."]}),"\n",(0,a.jsxs)(t.p,{children:["Use az keyvault key create command to create a Key Exchange Key (KEK) that has key operations set to ",(0,a.jsx)(t.code,{children:"import"}),",\nfor example:"]}),"\n",(0,a.jsx)(t.pre,{children:(0,a.jsx)(t.code,{className:"language-bash",children:"az keyvault key create --kty RSA-HSM --size 4096 --name KEKforBYOK --ops import --vault-name myKeyVaultHSM\n"})}),"\n",(0,a.jsx)(t.p,{children:"When the KEK is created, note down the key identifier (kid) for the generated key as it will be used\nlater on, for example:"}),"\n",(0,a.jsx)(t.pre,{children:(0,a.jsx)(t.code,{className:"language-bash",children:"https://myKeyVaultHSM.vault.azure.net/keys/KEKforBYOK/d0dfd74d5bfc494abc572867b20e4260\n"})}),"\n",(0,a.jsx)(t.h2,{id:"download-kek",children:"Download KEK"}),"\n",(0,a.jsxs)(t.p,{children:["Use ",(0,a.jsx)(t.code,{children:"az keyvault key download"})," command to download the KEK public key as ",(0,a.jsx)(t.code,{children:".pem"})," file.\nFor example:"]}),"\n",(0,a.jsx)(t.pre,{children:(0,a.jsx)(t.code,{className:"language-bash",children:"az keyvault key download --name KEKforBYOK --vault-name myKeyVaultHSM --file KEKforBYOK.publickey.pem\n"})}),"\n",(0,a.jsxs)(t.p,{children:["Transfer the ",(0,a.jsx)(t.code,{children:"KEKforBYOK.publickey.pem"})," file to your HSM Client computer. You will need this file\nin the next step. The target key to import will be encrypted by using this KEK public key."]})]})}function d(e={}){const{wrapper:t}={...(0,o.R)(),...e.components};return t?(0,a.jsx)(t,{...e,children:(0,a.jsx)(c,{...e})}):c(e)}},28453:(e,t,n)=>{n.d(t,{R:()=>l,x:()=>s});var r=n(96540);const a={},o=r.createContext(a);function l(e){const t=r.useContext(o);return r.useMemo((function(){return"function"==typeof e?e(t):{...t,...e}}),[t,e])}function s(e){let t;return t=e.disableParentContext?"function"==typeof e.components?e.components(a):e.components||a:l(e.components),r.createElement(o.Provider,{value:t},e.children)}},65537:(e,t,n)=>{n.d(t,{A:()=>v});var r=n(96540),a=n(34164),o=n(65627),l=n(56347),s=n(50372),i=n(30604),u=n(11861),c=n(78749);function d(e){return r.Children.toArray(e).filter((e=>"\n"!==e)).map((e=>{if(!e||(0,r.isValidElement)(e)&&function(e){const{props:t}=e;return!!t&&"object"==typeof t&&"value"in t}(e))return e;throw new Error(`Docusaurus error: Bad <Tabs> child <${"string"==typeof e.type?e.type:e.type.name}>: all children of the <Tabs> component should be <TabItem>, and every <TabItem> should have a unique "value" prop.`)}))?.filter(Boolean)??[]}function h(e){const{values:t,children:n}=e;return(0,r.useMemo)((()=>{const e=t??function(e){return d(e).map((e=>{let{props:{value:t,label:n,attributes:r,default:a}}=e;return{value:t,label:n,attributes:r,default:a}}))}(n);return function(e){const t=(0,u.XI)(e,((e,t)=>e.value===t.value));if(t.length>0)throw new Error(`Docusaurus error: Duplicate values "${t.map((e=>e.value)).join(", ")}" found in <Tabs>. Every value needs to be unique.`)}(e),e}),[t,n])}function p(e){let{value:t,tabValues:n}=e;return n.some((e=>e.value===t))}function m(e){let{queryString:t=!1,groupId:n}=e;const a=(0,l.W6)(),o=function(e){let{queryString:t=!1,groupId:n}=e;if("string"==typeof t)return t;if(!1===t)return null;if(!0===t&&!n)throw new Error('Docusaurus error: The <Tabs> component groupId prop is required if queryString=true, because this value is used as the search param name. You can also provide an explicit value such as queryString="my-search-param".');return n??null}({queryString:t,groupId:n});return[(0,i.aZ)(o),(0,r.useCallback)((e=>{if(!o)return;const t=new URLSearchParams(a.location.search);t.set(o,e),a.replace({...a.location,search:t.toString()})}),[o,a])]}function y(e){const{defaultValue:t,queryString:n=!1,groupId:a}=e,o=h(e),[l,i]=(0,r.useState)((()=>function(e){let{defaultValue:t,tabValues:n}=e;if(0===n.length)throw new Error("Docusaurus error: the <Tabs> component requires at least one <TabItem> children component");if(t){if(!p({value:t,tabValues:n}))throw new Error(`Docusaurus error: The <Tabs> has a defaultValue "${t}" but none of its children has the corresponding value. Available values are: ${n.map((e=>e.value)).join(", ")}. If you intend to show no default tab, use defaultValue={null} instead.`);return t}const r=n.find((e=>e.default))??n[0];if(!r)throw new Error("Unexpected error: 0 tabValues");return r.value}({defaultValue:t,tabValues:o}))),[u,d]=m({queryString:n,groupId:a}),[y,f]=function(e){let{groupId:t}=e;const n=function(e){return e?`docusaurus.tab.${e}`:null}(t),[a,o]=(0,c.Dv)(n);return[a,(0,r.useCallback)((e=>{n&&o.set(e)}),[n,o])]}({groupId:a}),b=(()=>{const e=u??y;return p({value:e,tabValues:o})?e:null})();(0,s.A)((()=>{b&&i(b)}),[b]);return{selectedValue:l,selectValue:(0,r.useCallback)((e=>{if(!p({value:e,tabValues:o}))throw new Error(`Can't select invalid tab value=${e}`);i(e),d(e),f(e)}),[d,f,o]),tabValues:o}}var f=n(9136);const b={tabList:"tabList__CuJ",tabItem:"tabItem_LNqP"};var K=n(74848);function x(e){let{className:t,block:n,selectedValue:r,selectValue:l,tabValues:s}=e;const i=[],{blockElementScrollPositionUntilNextRender:u}=(0,o.a_)(),c=e=>{const t=e.currentTarget,n=i.indexOf(t),a=s[n].value;a!==r&&(u(t),l(a))},d=e=>{let t=null;switch(e.key){case"Enter":c(e);break;case"ArrowRight":{const n=i.indexOf(e.currentTarget)+1;t=i[n]??i[0];break}case"ArrowLeft":{const n=i.indexOf(e.currentTarget)-1;t=i[n]??i[i.length-1];break}}t?.focus()};return(0,K.jsx)("ul",{role:"tablist","aria-orientation":"horizontal",className:(0,a.A)("tabs",{"tabs--block":n},t),children:s.map((e=>{let{value:t,label:n,attributes:o}=e;return(0,K.jsx)("li",{role:"tab",tabIndex:r===t?0:-1,"aria-selected":r===t,ref:e=>{i.push(e)},onKeyDown:d,onClick:c,...o,className:(0,a.A)("tabs__item",b.tabItem,o?.className,{"tabs__item--active":r===t}),children:n??t},t)}))})}function g(e){let{lazy:t,children:n,selectedValue:o}=e;const l=(Array.isArray(n)?n:[n]).filter(Boolean);if(t){const e=l.find((e=>e.props.value===o));return e?(0,r.cloneElement)(e,{className:(0,a.A)("margin-top--md",e.props.className)}):null}return(0,K.jsx)("div",{className:"margin-top--md",children:l.map(((e,t)=>(0,r.cloneElement)(e,{key:t,hidden:e.props.value!==o})))})}function k(e){const t=y(e);return(0,K.jsxs)("div",{className:(0,a.A)("tabs-container",b.tabList),children:[(0,K.jsx)(x,{...t,...e}),(0,K.jsx)(g,{...t,...e})]})}function v(e){const t=(0,f.A)();return(0,K.jsx)(k,{...e,children:d(e.children)},String(t))}},79329:(e,t,n)=>{n.d(t,{A:()=>l});n(96540);var r=n(34164);const a={tabItem:"tabItem_Ymn6"};var o=n(74848);function l(e){let{children:t,hidden:n,className:l}=e;return(0,o.jsx)("div",{role:"tabpanel",className:(0,r.A)(a.tabItem,l),hidden:n,children:t})}}}]);