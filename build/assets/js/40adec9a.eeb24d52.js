"use strict";(self.webpackChunksecurosys_docs=self.webpackChunksecurosys_docs||[]).push([[12538],{3431:(e,t,n)=>{n.r(t),n.d(t,{assets:()=>l,contentTitle:()=>i,default:()=>d,frontMatter:()=>o,metadata:()=>r,toc:()=>c});const r=JSON.parse('{"id":"Installation/https-setup/tls-configuration","title":"Configuring HTTPS with REST-API & JKS","description":"Guide to configuring HTTPS (TLS) for Securosys Rest-API using Java Key Store (JKS).","source":"@site/tsb/Installation/https-setup/tls-configuration.md","sourceDirName":"Installation/https-setup","slug":"/Installation/https-setup/tls-configuration","permalink":"/tsb/Installation/https-setup/tls-configuration","draft":false,"unlisted":false,"tags":[],"version":"current","lastUpdatedAt":1741162132000,"sidebarPosition":1,"frontMatter":{"sidebar_position":1,"title":"Configuring HTTPS with REST-API & JKS","sidebar_label":"Configure HTTPS","description":"Guide to configuring HTTPS (TLS) for Securosys Rest-API using Java Key Store (JKS).","keywords":["cybersecurity","data security","key management","cloud hsm","hsm key management","hsm cloud","hsm as a service","cloud based hsm","hsm digital signature","hsm services","hsm service","hsm","hardware security module","TLS configuration","JKS file","OpenSSL","Securosys Rest-API","HTTPS setup","certificate generation","application-local.yml configuration"]},"sidebar":"tutorialSidebar","previous":{"title":"Configure mTLS","permalink":"/tsb/Installation/https-setup/mtls-configuration"},"next":{"title":"Tutorial","permalink":"/tsb/category/tutorial"}}');var s=n(74848),a=n(28453);n(65537),n(79329);const o={sidebar_position:1,title:"Configuring HTTPS with REST-API & JKS",sidebar_label:"Configure HTTPS",description:"Guide to configuring HTTPS (TLS) for Securosys Rest-API using Java Key Store (JKS).",keywords:["cybersecurity","data security","key management","cloud hsm","hsm key management","hsm cloud","hsm as a service","cloud based hsm","hsm digital signature","hsm services","hsm service","hsm","hardware security module","TLS configuration","JKS file","OpenSSL","Securosys Rest-API","HTTPS setup","certificate generation","application-local.yml configuration"]},i="Configure HTTPS",l={},c=[{value:"Creating a Key and Certificate",id:"creating-a-key-and-certificate",level:3},{value:"Import Key and Certificate into Key Store",id:"import-key-and-certificate-into-key-store",level:3},{value:"Configure HTTPS on TSB",id:"configure-https-on-tsb",level:3},{value:"Example startup",id:"example-startup",level:3}];function u(e){const t={a:"a",admonition:"admonition",blockquote:"blockquote",code:"code",h1:"h1",h3:"h3",header:"header",li:"li",ol:"ol",p:"p",pre:"pre",strong:"strong",...(0,a.R)(),...e.components};return(0,s.jsxs)(s.Fragment,{children:[(0,s.jsx)(t.header,{children:(0,s.jsx)(t.h1,{id:"configure-https",children:"Configure HTTPS"})}),"\n",(0,s.jsx)(t.p,{children:"The example below shows how to create a JKS (Java Key Store) file to enable TLS (https) for Securosys Rest-API."}),"\n",(0,s.jsxs)(t.admonition,{title:"TIP",type:"tip",children:[(0,s.jsxs)(t.p,{children:["There are multiple ways to generate a .jks file. In this example we are using openssl to create a certificate for ",(0,s.jsx)(t.strong,{children:"CN=localhost"}),".\ne.g. ",(0,s.jsx)(t.a,{href:"https://localhost:8080/v1/licenseInfo",children:"https://localhost:8080/v1/licenseInfo"})]}),(0,s.jsx)(t.p,{children:"It is required to have these utilities preinstalled on the device where the .jks file will be created."})]}),"\n",(0,s.jsx)(t.h3,{id:"creating-a-key-and-certificate",children:"Creating a Key and Certificate"}),"\n",(0,s.jsx)(t.p,{children:"Use the following command to generate a key (ca.key) and certificate (ca.crt) for TLS configuration with openssl:"}),"\n",(0,s.jsx)(t.pre,{children:(0,s.jsx)(t.code,{className:"language-sh",children:"openssl req -new -x509 -nodes -sha256 -newkey rsa:4096 -days 3650 -subj '/CN=localhost' -keyout ca.key -out ca.crt\n"})}),"\n",(0,s.jsx)(t.h3,{id:"import-key-and-certificate-into-key-store",children:"Import Key and Certificate into Key Store"}),"\n",(0,s.jsxs)(t.p,{children:["For the next steps you will require your ",(0,s.jsx)(t.code,{children:"*.key"}),", ",(0,s.jsx)(t.code,{children:"*.crt"})," files (or simply using a self-signed certificate, as show in the command above)."]}),"\n",(0,s.jsx)(t.pre,{children:(0,s.jsx)(t.code,{className:"language-sh",children:"openssl pkcs12 -export -in ca.crt -inkey ca.key -out securosys-ska-server.p12\n"})}),"\n",(0,s.jsx)(t.h3,{id:"configure-https-on-tsb",children:"Configure HTTPS on TSB"}),"\n",(0,s.jsxs)(t.ol,{children:["\n",(0,s.jsxs)(t.li,{children:["\n",(0,s.jsxs)(t.p,{children:["Copy the previously generated ",(0,s.jsx)(t.code,{children:"securosys-ska-server.p12"})," file to the securosys-tsb ",(0,s.jsx)(t.code,{children:"config-files/tls"})," folder. ",(0,s.jsx)("br",{}),"\n(Create a new directory ",(0,s.jsx)(t.strong,{children:(0,s.jsx)(t.code,{children:"tls"})})," if it does not exist)"]}),"\n"]}),"\n",(0,s.jsxs)(t.li,{children:["\n",(0,s.jsxs)(t.p,{children:["Adapt the following properties in ",(0,s.jsx)(t.code,{children:"application-local.yml"})," file:"]}),"\n"]}),"\n"]}),"\n",(0,s.jsx)(t.pre,{children:(0,s.jsx)(t.code,{className:"language-yml",children:"## HTTPS CONFIGURATION\ntls:\n  # set to true if you want to use TLS (https)\n  enabled: true\n  keyStore: 'file:/etc/app/config/tls/securosys-ska-server.p12'\n  keyStorePassword: secret  \n"})}),"\n",(0,s.jsx)(t.admonition,{title:"Public Domain",type:"tip",children:(0,s.jsxs)(t.p,{children:["The example above provides a guide to create a Certificate on localhost. If you want to use a public domain, a prerequisite step for this example is to generate a certificate for your domain. Then you can go to step ",(0,s.jsx)(t.a,{href:"#import-key-and-certificate-into-key-store",children:"Import Key and Certificate into Key Store"})," to import the files into KeyStore."]})}),"\n",(0,s.jsx)(t.h3,{id:"example-startup",children:"Example startup"}),"\n",(0,s.jsxs)(t.p,{children:["You should see in the TSB logs, that the server started with http(s). ",(0,s.jsx)("br",{}),"\nA sample below:"]}),"\n",(0,s.jsxs)(t.blockquote,{children:["\n",(0,s.jsx)(t.p,{children:(0,s.jsx)(t.code,{children:"Tomcat initialized with port(s): 8080 (https)"})}),"\n"]}),"\n",(0,s.jsxs)(t.blockquote,{children:["\n",(0,s.jsx)(t.p,{children:(0,s.jsx)(t.code,{children:"Connector [https-jsse-nio-8080], TLS virtual host [_default_], certificate type [UNDEFINED] configured from keystore [/root/.keystore] using alias [tomcat] with trust store [null]"})}),"\n"]}),"\n",(0,s.jsxs)(t.blockquote,{children:["\n",(0,s.jsx)(t.p,{children:(0,s.jsx)(t.code,{children:"Tomcat started on port(s): 8080 (https) with context path ''"})}),"\n"]}),"\n",(0,s.jsx)(t.pre,{children:(0,s.jsx)(t.code,{className:"language-text",children:"  .   ____          _            __ _ _\n /\\\\ / ___'_ __ _ _(_)_ __  __ _ \\ \\ \\ \\\n( ( )\\___ | '_ | '_| | '_ \\/ _` | \\ \\ \\ \\\n \\\\/  ___)| |_)| | | | | || (_| |  ) ) ) )\n  '  |____| .__|_| |_|_| |_\\__, | / / / /\n =========|_|==============|___/=/_/_/_/\n :: Spring Boot ::                (v3.1.6)\n\n2024.01.03 10:13:28.700 INFO [  restartedMain] [ com.securosys.ska.Application]Starting Application using Java 17.0.2 with PID 1 (/opt/app started by root in /)\n2024.01.03 10:13:28.706 INFO [  restartedMain] [ com.securosys.ska.Application]No active profile set, falling back to 1 default profile: \"default\"\n2024.01.03 10:13:28.905 INFO [  restartedMain] [sPropertyDefaultsPostProcessor]For additional web related logging consider setting the 'logging.level.web' property to 'DEBUG'\n2024.01.03 10:13:31.887 INFO [  restartedMain] [epositoryConfigurationDelegate]Bootstrapping Spring Data JPA repositories in DEFAULT mode.\n2024.01.03 10:13:32.096 INFO [  restartedMain] [epositoryConfigurationDelegate]Finished Spring Data repository scanning in 196 ms. Found 10 JPA repository interfaces.\n2024.01.03 10:13:36.722 INFO [  restartedMain] [mbedded.tomcat.TomcatWebServer]Tomcat initialized with port(s): 8080 (https)\n2024.01.03 10:13:36.805 INFO [  restartedMain] [oyote.http11.Http11NioProtocol]Initializing ProtocolHandler [\"https-jsse-nio-8080\"]\n2024.01.03 10:13:36.819 INFO [  restartedMain] [.catalina.core.StandardService]Starting service [Tomcat]\n...\n2024.01.03 10:13:53.391 INFO [  restartedMain] [oyote.http11.Http11NioProtocol]Starting ProtocolHandler [\"https-jsse-nio-8080\"]\n2024.01.03 10:13:54.082 INFO [  restartedMain] [il.net.NioEndpoint.certificate]Connector [https-jsse-nio-8080], TLS virtual host [_default_], certificate type [UNDEFINED] configured from keystore [/root/.keystore] using alias [tomcat] with trust store [null]\n2024.01.03 10:13:54.099 INFO [  restartedMain] [mbedded.tomcat.TomcatWebServer]Tomcat started on port(s): 8080 (https) with context path ''\n2024.01.03 10:13:54.179 INFO [  restartedMain] [ com.securosys.ska.Application]Started Application in 27.365 seconds (process running for 29.127)\n2024.01.03 10:13:54.183 INFO [  restartedMain] [.business.BootstrappingProcess]Executing application bootstrapping\n...\n"})})]})}function d(e={}){const{wrapper:t}={...(0,a.R)(),...e.components};return t?(0,s.jsx)(t,{...e,children:(0,s.jsx)(u,{...e})}):u(e)}},28453:(e,t,n)=>{n.d(t,{R:()=>o,x:()=>i});var r=n(96540);const s={},a=r.createContext(s);function o(e){const t=r.useContext(a);return r.useMemo((function(){return"function"==typeof e?e(t):{...t,...e}}),[t,e])}function i(e){let t;return t=e.disableParentContext?"function"==typeof e.components?e.components(s):e.components||s:o(e.components),r.createElement(a.Provider,{value:t},e.children)}},65537:(e,t,n)=>{n.d(t,{A:()=>T});var r=n(96540),s=n(34164),a=n(65627),o=n(56347),i=n(50372),l=n(30604),c=n(11861),u=n(78749);function d(e){return r.Children.toArray(e).filter((e=>"\n"!==e)).map((e=>{if(!e||(0,r.isValidElement)(e)&&function(e){const{props:t}=e;return!!t&&"object"==typeof t&&"value"in t}(e))return e;throw new Error(`Docusaurus error: Bad <Tabs> child <${"string"==typeof e.type?e.type:e.type.name}>: all children of the <Tabs> component should be <TabItem>, and every <TabItem> should have a unique "value" prop.`)}))?.filter(Boolean)??[]}function p(e){const{values:t,children:n}=e;return(0,r.useMemo)((()=>{const e=t??function(e){return d(e).map((e=>{let{props:{value:t,label:n,attributes:r,default:s}}=e;return{value:t,label:n,attributes:r,default:s}}))}(n);return function(e){const t=(0,c.XI)(e,((e,t)=>e.value===t.value));if(t.length>0)throw new Error(`Docusaurus error: Duplicate values "${t.map((e=>e.value)).join(", ")}" found in <Tabs>. Every value needs to be unique.`)}(e),e}),[t,n])}function h(e){let{value:t,tabValues:n}=e;return n.some((e=>e.value===t))}function f(e){let{queryString:t=!1,groupId:n}=e;const s=(0,o.W6)(),a=function(e){let{queryString:t=!1,groupId:n}=e;if("string"==typeof t)return t;if(!1===t)return null;if(!0===t&&!n)throw new Error('Docusaurus error: The <Tabs> component groupId prop is required if queryString=true, because this value is used as the search param name. You can also provide an explicit value such as queryString="my-search-param".');return n??null}({queryString:t,groupId:n});return[(0,l.aZ)(a),(0,r.useCallback)((e=>{if(!a)return;const t=new URLSearchParams(s.location.search);t.set(a,e),s.replace({...s.location,search:t.toString()})}),[a,s])]}function m(e){const{defaultValue:t,queryString:n=!1,groupId:s}=e,a=p(e),[o,l]=(0,r.useState)((()=>function(e){let{defaultValue:t,tabValues:n}=e;if(0===n.length)throw new Error("Docusaurus error: the <Tabs> component requires at least one <TabItem> children component");if(t){if(!h({value:t,tabValues:n}))throw new Error(`Docusaurus error: The <Tabs> has a defaultValue "${t}" but none of its children has the corresponding value. Available values are: ${n.map((e=>e.value)).join(", ")}. If you intend to show no default tab, use defaultValue={null} instead.`);return t}const r=n.find((e=>e.default))??n[0];if(!r)throw new Error("Unexpected error: 0 tabValues");return r.value}({defaultValue:t,tabValues:a}))),[c,d]=f({queryString:n,groupId:s}),[m,g]=function(e){let{groupId:t}=e;const n=function(e){return e?`docusaurus.tab.${e}`:null}(t),[s,a]=(0,u.Dv)(n);return[s,(0,r.useCallback)((e=>{n&&a.set(e)}),[n,a])]}({groupId:s}),y=(()=>{const e=c??m;return h({value:e,tabValues:a})?e:null})();(0,i.A)((()=>{y&&l(y)}),[y]);return{selectedValue:o,selectValue:(0,r.useCallback)((e=>{if(!h({value:e,tabValues:a}))throw new Error(`Can't select invalid tab value=${e}`);l(e),d(e),g(e)}),[d,g,a]),tabValues:a}}var g=n(9136);const y={tabList:"tabList__CuJ",tabItem:"tabItem_LNqP"};var b=n(74848);function v(e){let{className:t,block:n,selectedValue:r,selectValue:o,tabValues:i}=e;const l=[],{blockElementScrollPositionUntilNextRender:c}=(0,a.a_)(),u=e=>{const t=e.currentTarget,n=l.indexOf(t),s=i[n].value;s!==r&&(c(t),o(s))},d=e=>{let t=null;switch(e.key){case"Enter":u(e);break;case"ArrowRight":{const n=l.indexOf(e.currentTarget)+1;t=l[n]??l[0];break}case"ArrowLeft":{const n=l.indexOf(e.currentTarget)-1;t=l[n]??l[l.length-1];break}}t?.focus()};return(0,b.jsx)("ul",{role:"tablist","aria-orientation":"horizontal",className:(0,s.A)("tabs",{"tabs--block":n},t),children:i.map((e=>{let{value:t,label:n,attributes:a}=e;return(0,b.jsx)("li",{role:"tab",tabIndex:r===t?0:-1,"aria-selected":r===t,ref:e=>{l.push(e)},onKeyDown:d,onClick:u,...a,className:(0,s.A)("tabs__item",y.tabItem,a?.className,{"tabs__item--active":r===t}),children:n??t},t)}))})}function x(e){let{lazy:t,children:n,selectedValue:a}=e;const o=(Array.isArray(n)?n:[n]).filter(Boolean);if(t){const e=o.find((e=>e.props.value===a));return e?(0,r.cloneElement)(e,{className:(0,s.A)("margin-top--md",e.props.className)}):null}return(0,b.jsx)("div",{className:"margin-top--md",children:o.map(((e,t)=>(0,r.cloneElement)(e,{key:t,hidden:e.props.value!==a})))})}function S(e){const t=m(e);return(0,b.jsxs)("div",{className:(0,s.A)("tabs-container",y.tabList),children:[(0,b.jsx)(v,{...t,...e}),(0,b.jsx)(x,{...t,...e})]})}function T(e){const t=(0,g.A)();return(0,b.jsx)(S,{...e,children:d(e.children)},String(t))}},79329:(e,t,n)=>{n.d(t,{A:()=>o});n(96540);var r=n(34164);const s={tabItem:"tabItem_Ymn6"};var a=n(74848);function o(e){let{children:t,hidden:n,className:o}=e;return(0,a.jsx)("div",{role:"tabpanel",className:(0,r.A)(s.tabItem,o),hidden:n,children:t})}}}]);