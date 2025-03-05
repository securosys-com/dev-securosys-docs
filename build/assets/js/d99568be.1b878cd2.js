"use strict";(self.webpackChunksecurosys_docs=self.webpackChunksecurosys_docs||[]).push([[15817],{28453:(e,t,n)=>{n.d(t,{R:()=>a,x:()=>l});var r=n(96540);const s={},i=r.createContext(s);function a(e){const t=r.useContext(i);return r.useMemo((function(){return"function"==typeof e?e(t):{...t,...e}}),[t,e])}function l(e){let t;return t=e.disableParentContext?"function"==typeof e.components?e.components(s):e.components||s:a(e.components),r.createElement(i.Provider,{value:t},e.children)}},55290:(e,t,n)=>{n.r(t),n.d(t,{assets:()=>d,contentTitle:()=>c,default:()=>g,frontMatter:()=>o,metadata:()=>r,toc:()=>h});const r=JSON.parse('{"id":"overview","title":"Security HSM Integration with Rest-API","description":"Explore the integration of Securosys REST API within Docker containers for seamless interaction with Primus HSM clusters and CloudHSM, ensuring robust hardware security module (HSM) capabilities.","source":"@site/tsb/overview.md","sourceDirName":".","slug":"/overview","permalink":"/tsb/overview","draft":false,"unlisted":false,"tags":[],"version":"current","lastUpdatedAt":1741162132000,"sidebarPosition":0,"frontMatter":{"sidebar_position":0,"title":"Security HSM Integration with Rest-API","sidebar_label":"Introduction","description":"Explore the integration of Securosys REST API within Docker containers for seamless interaction with Primus HSM clusters and CloudHSM, ensuring robust hardware security module (HSM) capabilities.","keywords":["cybersecurity","data security","key management","cloud hsm","hsm key management","hsm cloud","hsm as a service","cloud based hsm","hsm digital signature","hsm services","hsm service","hsm","hardware security module","Rest-API","Docker integration","cryptographic operations","RSA key generation","synchronous signing","OpenSSL commands","scalable architecture","JWT tokens","system management"],"grid_title":"Rest-API","grid_search_tags":["Docker","Rest-API","Provider","JCE/JCA","Smart Key Attributes","PQC"],"grid_description":"Get started with REST-API for Hardware Security Modules (HSMs), Smart Key Attributes (SKA), and Transaction Security Broker (TSB).","grid_categories":["Rest-API","Smart Key Attributes","Provider","PQC"],"grid_tile_logoUrl":"/img/securosys_logo.png"},"sidebar":"tutorialSidebar","next":{"title":"Get Started","permalink":"/tsb/category/get-started"}}');var s=n(74848),i=n(28453),a=n(65537),l=n(79329);const o={sidebar_position:0,title:"Security HSM Integration with Rest-API",sidebar_label:"Introduction",description:"Explore the integration of Securosys REST API within Docker containers for seamless interaction with Primus HSM clusters and CloudHSM, ensuring robust hardware security module (HSM) capabilities.",keywords:["cybersecurity","data security","key management","cloud hsm","hsm key management","hsm cloud","hsm as a service","cloud based hsm","hsm digital signature","hsm services","hsm service","hsm","hardware security module","Rest-API","Docker integration","cryptographic operations","RSA key generation","synchronous signing","OpenSSL commands","scalable architecture","JWT tokens","system management"],grid_title:"Rest-API",grid_search_tags:["Docker","Rest-API","Provider","JCE/JCA","Smart Key Attributes","PQC"],grid_description:"Get started with REST-API for Hardware Security Modules (HSMs), Smart Key Attributes (SKA), and Transaction Security Broker (TSB).",grid_categories:["Rest-API","Smart Key Attributes","Provider","PQC"],grid_tile_logoUrl:"/img/securosys_logo.png"},c="REST-API & Transaction Security Broker",d={},h=[{value:"HSM Integration Guide",id:"hsm-integration-guide",level:3},{value:"Rest-API",id:"rest-api",level:2},{value:"How it works",id:"how-it-works",level:3},{value:"Try it out",id:"try-it-out",level:3},{value:"Create an RSA Key",id:"create-an-rsa-key",level:3},{value:"Sign a Payload with the Key",id:"sign-a-payload-with-the-key",level:3},{value:"What&#39;s next?",id:"whats-next",level:2}];function u(e){const t={a:"a",admonition:"admonition",code:"code",em:"em",h1:"h1",h2:"h2",h3:"h3",header:"header",hr:"hr",li:"li",ol:"ol",p:"p",pre:"pre",strong:"strong",table:"table",tbody:"tbody",td:"td",th:"th",thead:"thead",tr:"tr",ul:"ul",...(0,i.R)(),...e.components},{Details:n}=t;return n||function(e,t){throw new Error("Expected "+(t?"component":"object")+" `"+e+"` to be defined: you likely forgot to import, pass, or provide it.")}("Details",!0),(0,s.jsxs)(s.Fragment,{children:[(0,s.jsx)(t.header,{children:(0,s.jsx)(t.h1,{id:"rest-api--transaction-security-broker",children:"REST-API & Transaction Security Broker"})}),"\n",(0,s.jsx)(t.h3,{id:"hsm-integration-guide",children:"HSM Integration Guide"}),"\n",(0,s.jsx)(t.h2,{id:"rest-api",children:"Rest-API"}),"\n",(0,s.jsx)(t.p,{children:"The Securosys REST API, encapsulated within a Docker container, seamlessly interfaces with Primus HSM clusters and CloudHSM, providing a versatile, language-agnostic solution for robust hardware security module (HSM) integration. This API serves as a bridge for secure key management, enabling cryptographic operations such as signing and encryption with the highest level of protection. Leveraging the power of HSMs, it ensures the confidentiality and integrity of sensitive data, making it an indispensable tool for applications demanding advanced security measures."}),"\n",(0,s.jsx)(t.h3,{id:"how-it-works",children:"How it works"}),"\n",(0,s.jsxs)(t.p,{children:["The Rest-API, deployed as a Docker container, establishes a direct connection to a single HSM partition (User), enabling secure cryptographic operations. To enhance scalability and distribute the workload, multiple Docker containers can communicate with a shared HSM partition, facilitating effective load balancing. Additionally, the Rest-API's flexible architecture supports a 1",":n"," configuration, allowing one API interface (one Docker container) to connect with multiple HSMs or partitions using JWT-Token's for authentication, while a second container manages states and credential information in a dedicated database for comprehensive system management."]}),"\n",(0,s.jsx)(t.h3,{id:"try-it-out",children:"Try it out"}),"\n",(0,s.jsx)(t.p,{children:"On this page it is shown how to:"}),"\n",(0,s.jsxs)(t.ul,{children:["\n",(0,s.jsx)(t.li,{children:"create an RSA Key"}),"\n",(0,s.jsx)(t.li,{children:"sign a payload with the previously created key"}),"\n"]}),"\n",(0,s.jsx)(t.h3,{id:"create-an-rsa-key",children:"Create an RSA Key"}),"\n",(0,s.jsxs)(t.p,{children:[(0,s.jsx)(t.strong,{children:"POST"}),": ",(0,s.jsx)(t.a,{href:"https://tsb-demo.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Keys/createKey",children:"/v1/key"})]}),"\n",(0,s.jsxs)(n,{children:[(0,s.jsxs)("summary",{children:[(0,s.jsx)(t.strong,{children:"Key"})," Parameters"]}),(0,s.jsxs)(t.table,{children:[(0,s.jsx)(t.thead,{children:(0,s.jsxs)(t.tr,{children:[(0,s.jsx)(t.th,{children:"Parameter"}),(0,s.jsx)(t.th,{children:"Description"})]})}),(0,s.jsxs)(t.tbody,{children:[(0,s.jsxs)(t.tr,{children:[(0,s.jsx)(t.td,{children:(0,s.jsx)(t.strong,{children:"label"})}),(0,s.jsxs)(t.td,{children:["The email address of the Approver, e.g., ",(0,s.jsx)(t.code,{children:"officer1@securosys.com"}),"."]})]}),(0,s.jsxs)(t.tr,{children:[(0,s.jsx)(t.td,{children:(0,s.jsx)(t.strong,{children:"algorithm"})}),(0,s.jsxs)(t.td,{children:["The algorithm of the key. Supported algorithms: ",(0,s.jsx)(t.a,{href:"/tsb/Concepts/key/Algorithms",children:"/Algorithms"})]})]}),(0,s.jsxs)(t.tr,{children:[(0,s.jsx)(t.td,{children:(0,s.jsx)(t.strong,{children:"keySize"})}),(0,s.jsx)(t.td,{children:"The size of the Key. Applicable for RSA, DSA, ISS and Symmetric algorithms."})]}),(0,s.jsxs)(t.tr,{children:[(0,s.jsx)(t.td,{children:(0,s.jsx)(t.strong,{children:"curveOid"})}),(0,s.jsx)(t.td,{children:"Needed if Alrogithm==EC"})]}),(0,s.jsxs)(t.tr,{children:[(0,s.jsx)(t.td,{children:(0,s.jsx)(t.strong,{children:"decrypt"})}),(0,s.jsxs)(t.td,{children:["check ",(0,s.jsx)(t.a,{href:"/tsb/Concepts/key/Attributes",children:"Key-Attributes"})]})]}),(0,s.jsxs)(t.tr,{children:[(0,s.jsx)(t.td,{children:(0,s.jsx)(t.strong,{children:"sign"})}),(0,s.jsxs)(t.td,{children:["check ",(0,s.jsx)(t.a,{href:"/tsb/Concepts/key/Attributes",children:"Key-Attributes"})]})]}),(0,s.jsxs)(t.tr,{children:[(0,s.jsx)(t.td,{children:(0,s.jsx)(t.strong,{children:"unwrap"})}),(0,s.jsxs)(t.td,{children:["check ",(0,s.jsx)(t.a,{href:"/tsb/Concepts/key/Attributes",children:"Key-Attributes"})]})]}),(0,s.jsxs)(t.tr,{children:[(0,s.jsx)(t.td,{children:(0,s.jsx)(t.strong,{children:"destroyable"})}),(0,s.jsxs)(t.td,{children:["check ",(0,s.jsx)(t.a,{href:"/tsb/Concepts/key/Attributes",children:"Key-Attributes"})]})]})]})]})]}),"\n",(0,s.jsxs)(a.A,{groupId:"device-setup",children:[(0,s.jsxs)(l.A,{value:"swagger",label:"Swagger",default:!0,children:[(0,s.jsx)(t.admonition,{title:"Connectivity Details",type:"tip",children:(0,s.jsxs)(t.p,{children:["You may replace the endpoint ",(0,s.jsx)(t.code,{children:"https://tsb-demo.cloudshsm.com/"})," ",(0,s.jsx)(t.em,{children:"below"}),"  by a ",(0,s.jsx)(t.a,{href:"/connectivity-details/cloudhsm-connectivity-details",children:"TSBaaS - Connectivity Details"})," for accurate API-Endpoint URI."]})}),(0,s.jsxs)(t.ol,{children:["\n",(0,s.jsxs)(t.li,{children:["\n",(0,s.jsxs)(t.p,{children:[(0,s.jsx)(t.strong,{children:"Open"})," the Swagger documentation to interact directly with the API using your browser: ",(0,s.jsx)(t.a,{href:"https://tsb-demo.cloudshsm.com/swagger-ui/index.html",children:"https://tsb-demo.cloudshsm.com/swagger-ui/index.html"})]}),"\n"]}),"\n",(0,s.jsxs)(t.li,{children:["\n",(0,s.jsxs)(t.p,{children:[(0,s.jsx)(t.strong,{children:"Request:"})," Replace ",(0,s.jsx)(t.code,{children:"<keyname>"})]}),"\n",(0,s.jsx)(t.pre,{children:(0,s.jsx)(t.code,{className:"language-js",metastring:"{2}",children:'{\n  "label": "<keyname>",\n  "algorithm": "RSA",\n  "keySize": 2048,\n  "attributes": {\n      "sign": true,\n      "extractable": false,\n      "modifiable": true,\n      "destroyable": true,\n      "sensitive": true,\n      "decrypt": false,\n      "unwrap": false\n  }\n}\n'})}),"\n",(0,s.jsx)(t.p,{children:(0,s.jsx)(t.strong,{children:"Response:"})}),"\n",(0,s.jsx)(t.pre,{children:(0,s.jsx)(t.code,{className:"language-js",children:'{\n"xml": "<xml-formatted attributes of the key>",\n"json": {\n    "label": "...",\n    "id": null,\n    "algorithm": "RSA",\n    "algorithmOid": "1.2.840.113549.1.1.1",\n    "curveOid": null,\n    "keySize": 2048,\n    "createTime": "2021-02-24T15:10:03Z",\n    "attestTime": "2021-02-24T15:10:03Z",\n    "publicKey": "MIIBIj...AB",\n    "addressTruncated": null,\n    "attributes": {\n    "decrypt": false,\n    "sign": true,\n    "ekaSign": null,\n    "unwrap": false,\n    "derive": false,\n    "sensitive": true,\n    "alwaysSensitive": true,\n    "extractable": false,\n    "neverExtractable": true,\n    "modifiable": true,\n    "copyable": true,\n    "destroyable": true\n    },\n    "policy": null\n},\n"xmlSignature": "<base64-encoded-signature>",\n"attestationKeyName": "attestation-key"\n}\n'})}),"\n"]}),"\n"]})]}),(0,s.jsxs)(l.A,{value:"curl",label:"CURL",default:!0,children:[(0,s.jsx)(t.admonition,{title:"Connectivity Details",type:"tip",children:(0,s.jsxs)(t.p,{children:["You may replace the endpoint ",(0,s.jsx)(t.code,{children:"https://tsb-demo.cloudshsm.com/"})," ",(0,s.jsx)(t.em,{children:"below"}),"  by a ",(0,s.jsx)(t.a,{href:"/connectivity-details/cloudhsm-connectivity-details",children:"TSBaaS - Connectivity Details"})," for accurate API-Endpoint URI."]})}),(0,s.jsxs)(t.p,{children:[(0,s.jsx)(t.strong,{children:"Request:"})," Replace ",(0,s.jsx)(t.code,{children:"<keyname>"})," and ",(0,s.jsx)(t.code,{children:"<bearer_token>"}),"."]}),(0,s.jsx)(t.pre,{children:(0,s.jsx)(t.code,{className:"language-sh",metastring:"{2,7}",children:'curl --request POST \\\n--url "https://tsb-demo.cloudshsm.com/v1/key" \\\n--header \'accept: application/json\' \\\n--header \'Content-Type: application/json\' \\\n--data \'{\n    "label": "<keyname>",\n    "algorithm": "RSA",\n    "keySize": 2048,\n    "attributes": {\n        "sign": true,\n        "extractable": false,\n        "modifiable": true,\n        "destroyable": true,\n        "sensitive": true,\n        "decrypt": false,\n        "unwrap": false\n    }\n}\'\n'})}),(0,s.jsx)(t.p,{children:(0,s.jsx)(t.strong,{children:"Response:"})}),(0,s.jsx)(t.pre,{children:(0,s.jsx)(t.code,{className:"language-js",children:'{\n"xml": "<xml-formatted attributes of the key>",\n"json": {\n    "label": "...",\n    "id": null,\n    "algorithm": "RSA",\n    "algorithmOid": "1.2.840.113549.1.1.1",\n    "curveOid": null,\n    "keySize": 2048,\n    "createTime": "2021-02-24T15:10:03Z",\n    "attestTime": "2021-02-24T15:10:03Z",\n    "publicKey": "MIIBIj...AB",\n    "addressTruncated": null,\n    "attributes": {\n    "decrypt": false,\n    "sign": true,\n    "ekaSign": null,\n    "unwrap": false,\n    "derive": false,\n    "sensitive": true,\n    "alwaysSensitive": true,\n    "extractable": false,\n    "neverExtractable": true,\n    "modifiable": true,\n    "copyable": true,\n    "destroyable": true\n    },\n    "policy": null\n},\n"xmlSignature": "<base64-encoded-signature>",\n"attestationKeyName": "attestation-key"\n}\n'})})]})]}),"\n",(0,s.jsx)(t.hr,{}),"\n",(0,s.jsx)(t.hr,{}),"\n",(0,s.jsx)(t.h3,{id:"sign-a-payload-with-the-key",children:"Sign a Payload with the Key"}),"\n",(0,s.jsxs)(t.p,{children:[(0,s.jsx)(t.strong,{children:"POST"}),": ",(0,s.jsx)(t.a,{href:"https://tsb-demo.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Synchronous%20Key%20Operations/synchronousSign",children:"/v1/synchronousSign"})]}),"\n",(0,s.jsx)(t.p,{children:(0,s.jsx)(t.strong,{children:"Description:"})}),"\n",(0,s.jsxs)(a.A,{groupId:"device-setup",children:[(0,s.jsxs)(l.A,{value:"swagger",label:"Swagger",default:!0,children:[(0,s.jsxs)(t.p,{children:[(0,s.jsx)(t.strong,{children:"Request:"})," Replace ",(0,s.jsx)(t.code,{children:"<keyname>"})]}),(0,s.jsx)(t.pre,{children:(0,s.jsx)(t.code,{className:"language-js",metastring:"{3-5}",children:'{\n    "signRequest": {\n        "payload": "UGF5bG9hZA==",\n        "signKeyName": "<keyname>",\n        "signatureAlgorithm": "SHA224_WITH_RSA_PSS"\n    }\n}\n'})}),(0,s.jsxs)(t.table,{children:[(0,s.jsx)(t.thead,{children:(0,s.jsxs)(t.tr,{children:[(0,s.jsx)(t.th,{}),(0,s.jsx)(t.th,{})]})}),(0,s.jsxs)(t.tbody,{children:[(0,s.jsxs)(t.tr,{children:[(0,s.jsx)(t.td,{children:(0,s.jsx)(t.code,{children:"payload"})}),(0,s.jsxs)(t.td,{children:["The data for which you want to create a digital signature. It should be encoded using ",(0,s.jsx)(t.a,{href:"https://www.base64encode.org/",children:"base64"}),"."]})]}),(0,s.jsxs)(t.tr,{children:[(0,s.jsx)(t.td,{children:(0,s.jsx)(t.code,{children:"signKeyName"})}),(0,s.jsx)(t.td,{children:"The label or identifier of the key stored within the HSM. This key is used for generating the digital signature."})]}),(0,s.jsxs)(t.tr,{children:[(0,s.jsx)(t.td,{children:(0,s.jsx)(t.code,{children:"signatureAlgorithm"})}),(0,s.jsx)(t.td,{children:"Specifies the algorithm used for generating the digital signature. In this case, it's SHA224_WITH_RSA_PSS, indicating that SHA224 is used for hashing the payload and RSA with PSS padding scheme is used for signing."})]})]})]}),(0,s.jsx)(t.p,{children:(0,s.jsx)(t.strong,{children:"Response:"})}),(0,s.jsx)(t.pre,{children:(0,s.jsx)(t.code,{children:'{\n"signature": "A4Jw63iLqG4gj6lyxi+BxDA4QtlN3PHQGk6BeCmd0zrt4OFn56v0XKQqa4sZ73ukeNSa0c1VcMPT0U6fe1Lt7DCNK9CTeerbanwtEkvGnmTFXt8FyOMABppeNCbLdcDzr7u7in+9jDdQzw0/Q+lVF/4lLFA9QykTtYNh7p+7a9QSv2ZucNCjAK0ief95Kb9KuJ6SjyV4jeiI8yIsliH/TLjfMswCa+Bmyq53c3QdcHkJDypm3riUHOCXAPn1YpwjwNPy9KFR+0Hyhf2MI2ar051J4F+/zooYjYJHYggYBnJ6LyOShudXOIH4UKjsgt2tkrMHSCHcnsappRgq4oRQ+Q=="\n}\n'})})]}),(0,s.jsxs)(l.A,{value:"curl",label:"CURL",children:[(0,s.jsxs)(t.p,{children:[(0,s.jsx)(t.strong,{children:"Request:"})," Replace ",(0,s.jsx)(t.code,{children:"<keyname>"})," and ",(0,s.jsx)(t.code,{children:"<bearer_token>"}),"."]}),(0,s.jsx)(t.pre,{children:(0,s.jsx)(t.code,{className:"language-sh",metastring:"{2,7-9}",children:'curl --request POST \\\n--url "https://tsb-demo.cloudshsm.com/v1/synchronousSign" \\\n--header \'accept: application/json\' \\\n--header \'Content-Type: application/json\' \\\n--data \'{\n    "signRequest": {\n        "payload": "UGF5bG9hZA==",\n        "signKeyName": "REST-API_Tutorial123",\n        "signatureAlgorithm": "SHA224_WITH_RSA_PSS"\n    }\n}\'\n'})}),(0,s.jsx)(t.p,{children:(0,s.jsx)(t.strong,{children:"Response:"})}),(0,s.jsx)(t.pre,{children:(0,s.jsx)(t.code,{children:'{"signature":"rF43P3iGgCvUZnoFY3+Qmyc8zxdydJ7RKcrQ+eQM4eM+7FmlAMAIOw9h2ae+/On4hyRY/VT/9SLMVe1UU/J1gIp9giX9zr3ycYBZ8r5l9wYuh1HVv5J9YX478ppLI6DoHQSw/8cxEPFzB2dZLmov5g51nJI9qvd8qJjgwYd/iYhAN9Vf7XlSa9LhoL/73ZOR3JSb68pWlfXVXIhN0ShaIoduE9ba+2Z7QkNG0bSXjS1j8tI9LiYLfBX2yEYyJgBaNFlBGI5EdlblxXMgNzBk3IMpQIPMYZ06N/3amIL+x46bb74ERZMGYcg0TXlEMd4KQTFD6wtUhkmOFCbRnDaq2w=="}\n'})})]})]}),"\n",(0,s.jsx)(t.hr,{}),"\n",(0,s.jsx)(t.h2,{id:"whats-next",children:"What's next?"}),"\n",(0,s.jsx)(t.p,{children:(0,s.jsx)(t.strong,{children:"Crypto Operations"})}),"\n",(0,s.jsxs)(t.ul,{children:["\n",(0,s.jsxs)(t.li,{children:["Encrypt & Decrypt a Payload ",(0,s.jsx)(t.a,{href:"/tsb/Tutorials/Encryption/EncryptDecrypt",children:"Sample"})]}),"\n",(0,s.jsxs)(t.li,{children:["File Encryption ",(0,s.jsx)(t.a,{href:"/tsb/Tutorials/Encryption/FileEncryption",children:"Sample"})]}),"\n"]}),"\n",(0,s.jsx)(t.p,{children:(0,s.jsx)(t.strong,{children:"Deployment Guides"})}),"\n",(0,s.jsxs)(t.ul,{children:["\n",(0,s.jsxs)(t.li,{children:["Read the ",(0,s.jsx)(t.a,{href:"/tsb/Quickstart/cloud-quickstart",children:"Deployment Guide - TSB as a Service"})]}),"\n",(0,s.jsxs)(t.li,{children:["Read the ",(0,s.jsx)(t.a,{href:"/tsb/Installation/On-Premise-Installation",children:"Deployment Guide - on Premise Installation (Docker)"})]}),"\n"]}),"\n",(0,s.jsx)(t.p,{children:(0,s.jsx)(t.strong,{children:"Transaction Security Broker"})}),"\n",(0,s.jsxs)(t.ul,{children:["\n",(0,s.jsxs)(t.li,{children:[(0,s.jsx)(t.a,{href:"/tsb/Tutorials/TransactionSecurityBroker/transaction-security-broker",children:"Tutorial and Sample"})," of TSB"]}),"\n"]}),"\n",(0,s.jsx)(t.p,{children:(0,s.jsx)(t.strong,{children:"More"})}),"\n",(0,s.jsxs)(t.ul,{children:["\n",(0,s.jsx)(t.li,{children:(0,s.jsx)(t.a,{href:"/tsb/category/concepts",children:"Concepts"})}),"\n",(0,s.jsxs)(t.li,{children:["More ",(0,s.jsx)(t.a,{href:"/tsb/category/tutorial",children:"Tutorials"})]}),"\n"]})]})}function g(e={}){const{wrapper:t}={...(0,i.R)(),...e.components};return t?(0,s.jsx)(t,{...e,children:(0,s.jsx)(u,{...e})}):u(e)}},65537:(e,t,n)=>{n.d(t,{A:()=>v});var r=n(96540),s=n(34164),i=n(65627),a=n(56347),l=n(50372),o=n(30604),c=n(11861),d=n(78749);function h(e){return r.Children.toArray(e).filter((e=>"\n"!==e)).map((e=>{if(!e||(0,r.isValidElement)(e)&&function(e){const{props:t}=e;return!!t&&"object"==typeof t&&"value"in t}(e))return e;throw new Error(`Docusaurus error: Bad <Tabs> child <${"string"==typeof e.type?e.type:e.type.name}>: all children of the <Tabs> component should be <TabItem>, and every <TabItem> should have a unique "value" prop.`)}))?.filter(Boolean)??[]}function u(e){const{values:t,children:n}=e;return(0,r.useMemo)((()=>{const e=t??function(e){return h(e).map((e=>{let{props:{value:t,label:n,attributes:r,default:s}}=e;return{value:t,label:n,attributes:r,default:s}}))}(n);return function(e){const t=(0,c.XI)(e,((e,t)=>e.value===t.value));if(t.length>0)throw new Error(`Docusaurus error: Duplicate values "${t.map((e=>e.value)).join(", ")}" found in <Tabs>. Every value needs to be unique.`)}(e),e}),[t,n])}function g(e){let{value:t,tabValues:n}=e;return n.some((e=>e.value===t))}function p(e){let{queryString:t=!1,groupId:n}=e;const s=(0,a.W6)(),i=function(e){let{queryString:t=!1,groupId:n}=e;if("string"==typeof t)return t;if(!1===t)return null;if(!0===t&&!n)throw new Error('Docusaurus error: The <Tabs> component groupId prop is required if queryString=true, because this value is used as the search param name. You can also provide an explicit value such as queryString="my-search-param".');return n??null}({queryString:t,groupId:n});return[(0,o.aZ)(i),(0,r.useCallback)((e=>{if(!i)return;const t=new URLSearchParams(s.location.search);t.set(i,e),s.replace({...s.location,search:t.toString()})}),[i,s])]}function m(e){const{defaultValue:t,queryString:n=!1,groupId:s}=e,i=u(e),[a,o]=(0,r.useState)((()=>function(e){let{defaultValue:t,tabValues:n}=e;if(0===n.length)throw new Error("Docusaurus error: the <Tabs> component requires at least one <TabItem> children component");if(t){if(!g({value:t,tabValues:n}))throw new Error(`Docusaurus error: The <Tabs> has a defaultValue "${t}" but none of its children has the corresponding value. Available values are: ${n.map((e=>e.value)).join(", ")}. If you intend to show no default tab, use defaultValue={null} instead.`);return t}const r=n.find((e=>e.default))??n[0];if(!r)throw new Error("Unexpected error: 0 tabValues");return r.value}({defaultValue:t,tabValues:i}))),[c,h]=p({queryString:n,groupId:s}),[m,y]=function(e){let{groupId:t}=e;const n=function(e){return e?`docusaurus.tab.${e}`:null}(t),[s,i]=(0,d.Dv)(n);return[s,(0,r.useCallback)((e=>{n&&i.set(e)}),[n,i])]}({groupId:s}),x=(()=>{const e=c??m;return g({value:e,tabValues:i})?e:null})();(0,l.A)((()=>{x&&o(x)}),[x]);return{selectedValue:a,selectValue:(0,r.useCallback)((e=>{if(!g({value:e,tabValues:i}))throw new Error(`Can't select invalid tab value=${e}`);o(e),h(e),y(e)}),[h,y,i]),tabValues:i}}var y=n(9136);const x={tabList:"tabList__CuJ",tabItem:"tabItem_LNqP"};var j=n(74848);function b(e){let{className:t,block:n,selectedValue:r,selectValue:a,tabValues:l}=e;const o=[],{blockElementScrollPositionUntilNextRender:c}=(0,i.a_)(),d=e=>{const t=e.currentTarget,n=o.indexOf(t),s=l[n].value;s!==r&&(c(t),a(s))},h=e=>{let t=null;switch(e.key){case"Enter":d(e);break;case"ArrowRight":{const n=o.indexOf(e.currentTarget)+1;t=o[n]??o[0];break}case"ArrowLeft":{const n=o.indexOf(e.currentTarget)-1;t=o[n]??o[o.length-1];break}}t?.focus()};return(0,j.jsx)("ul",{role:"tablist","aria-orientation":"horizontal",className:(0,s.A)("tabs",{"tabs--block":n},t),children:l.map((e=>{let{value:t,label:n,attributes:i}=e;return(0,j.jsx)("li",{role:"tab",tabIndex:r===t?0:-1,"aria-selected":r===t,ref:e=>{o.push(e)},onKeyDown:h,onClick:d,...i,className:(0,s.A)("tabs__item",x.tabItem,i?.className,{"tabs__item--active":r===t}),children:n??t},t)}))})}function f(e){let{lazy:t,children:n,selectedValue:i}=e;const a=(Array.isArray(n)?n:[n]).filter(Boolean);if(t){const e=a.find((e=>e.props.value===i));return e?(0,r.cloneElement)(e,{className:(0,s.A)("margin-top--md",e.props.className)}):null}return(0,j.jsx)("div",{className:"margin-top--md",children:a.map(((e,t)=>(0,r.cloneElement)(e,{key:t,hidden:e.props.value!==i})))})}function S(e){const t=m(e);return(0,j.jsxs)("div",{className:(0,s.A)("tabs-container",x.tabList),children:[(0,j.jsx)(b,{...t,...e}),(0,j.jsx)(f,{...t,...e})]})}function v(e){const t=(0,y.A)();return(0,j.jsx)(S,{...e,children:h(e.children)},String(t))}},79329:(e,t,n)=>{n.d(t,{A:()=>a});n(96540);var r=n(34164);const s={tabItem:"tabItem_Ymn6"};var i=n(74848);function a(e){let{children:t,hidden:n,className:a}=e;return(0,i.jsx)("div",{role:"tabpanel",className:(0,r.A)(s.tabItem,a),hidden:n,children:t})}}}]);