"use strict";(self.webpackChunksecurosys_docs=self.webpackChunksecurosys_docs||[]).push([[9858],{17884:(e,r,t)=>{t.d(r,{A:()=>n});const n=t.p+"assets/files/create_rsa-9106e479a151471d9b2edd7b27980ddd.sh"},28453:(e,r,t)=>{t.d(r,{R:()=>a,x:()=>o});var n=t(96540);const s={},i=n.createContext(s);function a(e){const r=n.useContext(i);return n.useMemo((function(){return"function"==typeof e?e(r):{...r,...e}}),[r,e])}function o(e){let r;return r=e.disableParentContext?"function"==typeof e.components?e.components(s):e.components||s:a(e.components),n.createElement(i.Provider,{value:r},e.children)}},55660:(e,r,t)=>{t.r(r),t.d(r,{assets:()=>d,contentTitle:()=>c,default:()=>p,frontMatter:()=>l,metadata:()=>n,toc:()=>u});const n=JSON.parse('{"id":"Tutorials/TransactionSecurityBroker/samples/step-by-step/create-policy-based-key","title":"Creating a key with Smart Key Attributes?","description":"Learn how to create SKA-keys using the PrimusHSM Rest-API.","source":"@site/tsb/Tutorials/TransactionSecurityBroker/samples/step-by-step/create-policy-based-key.md","sourceDirName":"Tutorials/TransactionSecurityBroker/samples/step-by-step","slug":"/Tutorials/TransactionSecurityBroker/samples/step-by-step/create-policy-based-key","permalink":"/tsb/Tutorials/TransactionSecurityBroker/samples/step-by-step/create-policy-based-key","draft":false,"unlisted":false,"tags":[],"version":"current","lastUpdatedAt":1741162132000,"sidebarPosition":1,"frontMatter":{"sidebar_position":1,"title":"Creating a key with Smart Key Attributes?","sidebar_label":"1. Create Policy based key","description":"Learn how to create SKA-keys using the PrimusHSM Rest-API.","keywords":["Create Key","SKA-key","PrimusHSM Rest-API","cryptographic key management","key creation","smart key attributes","TSB_ENGINE","cryptography","cybersecurity","data security","HSM","hardware security module"]},"sidebar":"tutorialSidebar","previous":{"title":"Step By Step","permalink":"/tsb/category/step-by-step"},"next":{"title":"2. Create a Authorized Signature-Request","permalink":"/tsb/Tutorials/TransactionSecurityBroker/samples/step-by-step/signature-request"}}');var s=t(74848),i=t(28453),a=t(65537),o=t(79329);const l={sidebar_position:1,title:"Creating a key with Smart Key Attributes?",sidebar_label:"1. Create Policy based key",description:"Learn how to create SKA-keys using the PrimusHSM Rest-API.",keywords:["Create Key","SKA-key","PrimusHSM Rest-API","cryptographic key management","key creation","smart key attributes","TSB_ENGINE","cryptography","cybersecurity","data security","HSM","hardware security module"]},c="Create Policy based key",d={},u=[];function h(e){const r={a:"a",admonition:"admonition",code:"code",em:"em",h1:"h1",header:"header",li:"li",p:"p",pre:"pre",strong:"strong",table:"table",tbody:"tbody",td:"td",th:"th",thead:"thead",tr:"tr",ul:"ul",...(0,i.R)(),...e.components},{Details:n}=r;return n||function(e,r){throw new Error("Expected "+(r?"component":"object")+" `"+e+"` to be defined: you likely forgot to import, pass, or provide it.")}("Details",!0),(0,s.jsxs)(s.Fragment,{children:[(0,s.jsx)(r.header,{children:(0,s.jsx)(r.h1,{id:"create-policy-based-key",children:"Create Policy based key"})}),"\n",(0,s.jsxs)(a.A,{groupId:"key-provider",children:[(0,s.jsxs)(o.A,{value:"create-ska-key",label:"Simple Policy",default:!0,children:[(0,s.jsx)(r.admonition,{title:"Rules",type:"info",children:(0,s.jsxs)(r.p,{children:["This example demonstrates how to create an RSA key with Policy enabled. It is simplified with a ",(0,s.jsx)(r.strong,{children:"quorum"})," of 1 and a single approver on ",(0,s.jsx)(r.strong,{children:"rule-use"}),".\nYou can expand the policy section with rule-modify, rule-block, rule-unblock. For futher information about SKA-Policy please check ",(0,s.jsx)(r.a,{href:"/tsb/Tutorials/TransactionSecurityBroker/smart-key-attributes",children:"here"}),"."]})}),(0,s.jsx)("br",{}),(0,s.jsxs)(r.p,{children:["The ",(0,s.jsx)(r.code,{children:"ruleUse"})," means that whenever the key is used for cryptographic operations, such as ",(0,s.jsx)(r.em,{children:"signing, decrypting, unwrapping, or issuing certificates"}),", the request must be ",(0,s.jsx)(r.strong,{children:"authorized"})," by the designated ",(0,s.jsx)(r.strong,{children:"approver"})," in the policy."]}),(0,s.jsx)("br",{}),(0,s.jsx)(r.admonition,{title:"Create RSA Key with Policy",type:"tip",children:(0,s.jsxs)(r.p,{children:["For simplicity you can use the script to create your approver key-pair locally ",(0,s.jsx)(r.a,{target:"_blank","data-noBrokenLinkCheck":!0,href:t(17884).A+"",title:"create_rsa.sh",children:"Create Approver Key-Pair"})," ",(0,s.jsx)("br",{}),"\nRun: ",(0,s.jsx)(r.code,{children:"./create_rsa.sh approverx"}),"\n(Don't forget to allow exec of script ",(0,s.jsx)(r.code,{children:"chmod +x create_rsa.sh"}),")"]})}),(0,s.jsxs)(r.p,{children:[(0,s.jsx)(r.strong,{children:"POST"})," ",(0,s.jsx)(r.a,{href:"https://tsb-demo.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Keys/createKey",children:"/v1/key"})]}),(0,s.jsxs)(a.A,{groupId:"key-provider",children:[(0,s.jsx)(o.A,{value:"Commented",label:"Commented",default:!0,children:(0,s.jsx)(r.pre,{children:(0,s.jsx)(r.code,{className:"language-bash",children:'{\n#highlight-tip-start\n    "label": "TSB_TUTORIAL_1-RSA",       # Label must be unique and is used for any request of the key action\n    "algorithm": "RSA",\n    "keySize": 2048,                    # keySize is required for RSA\n    #highlight-tip-end\n    "attributes": {                      # For this example, we\'ll assume the key will be used only for signing. We also rely on defaults for most attributes (see in response)\n        "decrypt": false,\n        "sign": true,\n        "unwrap": false,\n        "destroyable": true \n    },\n    "policy": {                          # To better understand structure of the policies, refer to the concept diagram\n    #highlight-warning-start\n        "ruleUse": {                     # We\'ll set a very simple policy - 1/1 approval with no timelock and a 10 minute timeout\n    #highlight-warning-end\n            "tokens": [\n                {\n                    "name": "Token1",\n                    "timelock": 0,\n                    "timeout": 3600,     # Time restrictions are defined in seconds and must be multiples of 60\n                    "groups": [\n                        {\n                            "name": "Group1",\n    #highlight-tip-start\n                            "quorum": 1,  # Quorum of 1 means that only 1 approver needs to sign in order to get a request EXECUTED\n    #highlight-tip-end\n                            "approvals": [\n                                {\n    #highlight-tip-start\n                                    "type": "certificate",   # the type can vary based on your preference: certificate, public-key or onboarded_approver_certificate\n                                    "value": "MIIDAjCCAeoCCQCcSLgNCjDsRzANBgkqhkiG9w0BAQsFADBDMQswCQYDVQQGEwJDSDEPMA0GA1UECAwGWnVyaWNoMQ8wDQYDVQQHDAZadXJpY2gxEjAQBgNVBAoMCVNlY3Vyb3N5czAeFw0yMDA1MTExNDI5MDdaFw0yMTA1MTExNDI5MDdaMEMxCzAJBgNVBAYTAkNIMQ8wDQYDVQQIDAZadXJpY2gxDzANBgNVBAcMBlp1cmljaDESMBAGA1UECgwJU2VjdXJvc3lzMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArA0bxSqhL7xfvcHbKKa8wMTMsIeJfYRdIgPxp5cU9JcmV86kyfpyRcSNSi44LVeNmAi94F3OZrXXi6CZvWrFL+VcewUtUSu+kG5oLJ9T4O6R2I5GO2Ev1HJnK3WfHOsFKFxLGzmKyjEkSLGgopY+Nh74K8Q6yxsvQPETOs9TzQiUXFYlfEZnbjUWG4eAgW9WWEopmK/X295ToOuTHFzmzO00btkjAy6vwWOabCE4kaJg+bCNW1snZz84uonr60rB9H0Mj98RbTfbDyMh6cIkaj8WrXeaYh4fxQYXApYu3nzhe3Q1bNCzV5M68rCsgVrmWcK/xUhM9BK6QHSwS/l76wIDAQABMA0GCSqGSIb3DQEBCwUAA4IBAQBNmg+gx2mH+fkU/dtM+tDvMIj2SY4pNU8H144aRY9I5kARN7Uwp+zRfJC+rCxrrYxXmx/OD+mIrTAHxPd5WuUWgULB6DXPho5Tyl4Czt6qOuzl7Qp7n1G9R/evZCPyEHflcGVEko/uCL5N8Ch9YboW5QwTrdftnL+zLLC5nON7KUCqbtVrDSdeMKF+dHKTX4Z90gdbv1C8x1fMWrsaoNw194DNBZCTVe4Di69xz3lHNEWVZ460mqKg0n5010VfEQqA92ceNJhjl4hgNMH9+asdBVAWmt0gk4PJUbqtuOKGKyxqi2k9QX8N2tjlsuMJmwRIw2YsZN4EKqQZ+0NAn1N7"\n                                    #highlight-tip-end   \n                                                            # Certificate or PublicKey values must be provided without new lines\n                                }\n                            ]\n                        }\n                    ]\n                }\n            ]\n        },\n    "keyStatus": {                     # Make sure this is inside of policy object\n        "blocked": false               # If setting this to true, make sure ruleUnblock is defined\n    }\n    }\n}\n'})})}),(0,s.jsx)(o.A,{value:"Un-commented",label:"Un-commented",default:!0,children:(0,s.jsx)(r.pre,{children:(0,s.jsx)(r.code,{className:"language-js",metastring:"{24,25}",children:'{\n    "label": "TSB_TUTORIAL_1-RSA",\n    "algorithm": "RSA",\n    "keySize": 2048,\n    "attributes": {\n        "decrypt": false,\n        "sign": true,\n        "unwrap": false,\n        "destroyable": true\n    },\n    "policy": {\n        "ruleUse": {\n            "tokens": [\n                {\n                    "name": "Token1",\n                    "timelock": 0,\n                    "timeout": 3600,\n                    "groups": [\n                        {\n                            "name": "Group1",\n                            "quorum": 1,\n                            "approvals": [\n                                {\n                                    "type": "certificate",\n                                    "value": "MIIDAjCCAeoCCQCcSLgNCjDsRzANBgkqhkiG9w0BAQsFADBDMQswCQYDVQQGEwJDSDEPMA0GA1UECAwGWnVyaWNoMQ8wDQYDVQQHDAZadXJpY2gxEjAQBgNVBAoMCVNlY3Vyb3N5czAeFw0yMDA1MTExNDI5MDdaFw0yMTA1MTExNDI5MDdaMEMxCzAJBgNVBAYTAkNIMQ8wDQYDVQQIDAZadXJpY2gxDzANBgNVBAcMBlp1cmljaDESMBAGA1UECgwJU2VjdXJvc3lzMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArA0bxSqhL7xfvcHbKKa8wMTMsIeJfYRdIgPxp5cU9JcmV86kyfpyRcSNSi44LVeNmAi94F3OZrXXi6CZvWrFL+VcewUtUSu+kG5oLJ9T4O6R2I5GO2Ev1HJnK3WfHOsFKFxLGzmKyjEkSLGgopY+Nh74K8Q6yxsvQPETOs9TzQiUXFYlfEZnbjUWG4eAgW9WWEopmK/X295ToOuTHFzmzO00btkjAy6vwWOabCE4kaJg+bCNW1snZz84uonr60rB9H0Mj98RbTfbDyMh6cIkaj8WrXeaYh4fxQYXApYu3nzhe3Q1bNCzV5M68rCsgVrmWcK/xUhM9BK6QHSwS/l76wIDAQABMA0GCSqGSIb3DQEBCwUAA4IBAQBNmg+gx2mH+fkU/dtM+tDvMIj2SY4pNU8H144aRY9I5kARN7Uwp+zRfJC+rCxrrYxXmx/OD+mIrTAHxPd5WuUWgULB6DXPho5Tyl4Czt6qOuzl7Qp7n1G9R/evZCPyEHflcGVEko/uCL5N8Ch9YboW5QwTrdftnL+zLLC5nON7KUCqbtVrDSdeMKF+dHKTX4Z90gdbv1C8x1fMWrsaoNw194DNBZCTVe4Di69xz3lHNEWVZ460mqKg0n5010VfEQqA92ceNJhjl4hgNMH9+asdBVAWmt0gk4PJUbqtuOKGKyxqi2k9QX8N2tjlsuMJmwRIw2YsZN4EKqQZ+0NAn1N7"\n                                }\n                            ]\n                        }\n                    ]\n                }\n            ]\n        },\n        "keyStatus": {\n            "blocked": false\n        }   \n    }\n}\n'})})})]})]}),(0,s.jsxs)(o.A,{value:"authorization_app",label:"Securosys Authorization App Policy",default:!0,children:[(0,s.jsxs)(r.p,{children:["Use the sample below if you want to create an SKA-Key with an ",(0,s.jsx)(r.strong,{children:"onboarded approver"}),". The key difference when using the ",(0,s.jsx)(r.a,{href:"/tsb/Tutorials/TransactionSecurityBroker/PrimusAuthorizationApp/approver-mangement-api",children:"Approver Management API via REST"})," is that you can specify the ",(0,s.jsx)(r.code,{children:"onboarded_approver_certificate"})," type in the Key Policy."]}),(0,s.jsx)(r.admonition,{title:"Rules",type:"info",children:(0,s.jsxs)(r.p,{children:["The sample below is simplified with a ",(0,s.jsx)(r.strong,{children:"quorum"})," of 1 and a single approver on ",(0,s.jsx)(r.strong,{children:"rule-use"}),".\nYou can expand the policy section with rule-modify, rule-block, rule-unblock. For futher information about SKA-Policy please check ",(0,s.jsx)(r.a,{href:"/tsb/Tutorials/TransactionSecurityBroker/smart-key-attributes",children:"here"}),"."]})}),(0,s.jsxs)(a.A,{groupId:"key-provider",children:[(0,s.jsx)(o.A,{value:"Commented",label:"Commented",default:!0,children:(0,s.jsx)(r.pre,{children:(0,s.jsx)(r.code,{className:"language-bash",children:'{\n    "label": "TSB_TUTORIAL_1-RSA",       # Label must be unique and is used for any request of the key action\n    "algorithm": "RSA",\n    "keySize": 2048,                    # keySize is required for RSA\n    "attributes": {                      # For this example, we\'ll assume the key will be used only for signing. We also rely on defaults for most attributes (see in response)\n        "decrypt": false,\n        "sign": true,\n        "unwrap": false,\n        "destroyable": true \n    },\n    "policy": {                          # To better understand structure of the policies, refer to the concept diagram\n    #highlight-warning-start\n        "ruleUse": {                     # We\'ll set a very simple policy - 1/1 approval with no timelock and a 10 minute timeout\n    #highlight-warning-end\n            "tokens": [\n                {\n                    "name": "Token1",\n                    "timelock": 0,\n                    "timeout": 3600,     # Time restrictions are defined in seconds and must be multiples of 60\n                    "groups": [\n                        {                        \n                            "name": "Group1",\n    #highlight-warning-start\n                            "quorum": 1,  # Quorum of 1 means that only 1 approver needs to sign in order to get a request EXECUTED\n    #highlight-warning-end\n                            "approvals": [\n                                {\n    #highlight-warning-start\n                                    "type": "onboarded_approver_certificate",   # the type can vary based on your preference: certificate, public-key or onboarded_approver_certificate\n                                    "name": "officer1@securosys.com"\n    #highlight-warning-end                                  \n                                }\n                            ]\n                        }\n                    ]\n                }\n            ]\n        },\n        "keyStatus": {                     # Make sure this is inside of policy object\n            "blocked": false               # If setting this to true, make sure ruleUnblock is defined\n        }\n    }\n}\n'})})}),(0,s.jsx)(o.A,{value:"Un-commented",label:"Un-commented",default:!0,children:(0,s.jsx)(r.pre,{children:(0,s.jsx)(r.code,{className:"language-js",metastring:"{24,25}",children:'{\n    "label": "TSB_TUTORIAL_1-RSA",\n    "algorithm": "RSA",\n    "keySize": 2048,\n    "attributes": {\n        "decrypt": false,\n        "sign": true,\n        "unwrap": false,\n        "destroyable": true\n    },\n    "policy": {\n        "ruleUse": {\n            "tokens": [\n                {\n                    "name": "Token1",\n                    "timelock": 0,\n                    "timeout": 3600,\n                    "groups": [\n                        {\n                            "name": "Group1",\n                            "quorum": 1,\n                            "approvals": [\n                                {\n                                    "type": "onboarded_approver_certificate",\n                                    "name": "officer1@securosys.com"\n                                }\n                            ]\n                        }\n                    ]\n                }\n            ]\n        },\n        "keyStatus": {\n            "blocked": false\n        }\n    }\n}\n'})})})]}),(0,s.jsxs)(r.p,{children:["This article provides samples to create a key with onboarded ",(0,s.jsx)(r.strong,{children:"Approver"})," to an HSM-Key with SmartKeyAttributes (Policy), to enable true Multi-Authorization."]}),(0,s.jsx)(r.admonition,{type:"note",children:(0,s.jsxs)(r.p,{children:["Status of onboarded approvers can be retrieved by the ",(0,s.jsx)(r.strong,{children:"Approver Manager"})," utilizing ",(0,s.jsx)(r.strong,{children:"POST"})," ",(0,s.jsx)(r.a,{href:"/AuthorizationApp/Tutorials/ApproverManagment/verify_onboarding_status",children:(0,s.jsx)(r.code,{children:"/v1/approverManagement/onboarding/status"})})]})})]}),(0,s.jsxs)(o.A,{value:"empty_policy",label:"Empty Policy",default:!0,children:[(0,s.jsx)(r.admonition,{title:"Take care",type:"danger",children:(0,s.jsxs)(r.p,{children:["Key with ",(0,s.jsx)(r.strong,{children:"empty policy"})," does not enforce true multiauthorization, but enables the key to be used with multiauthorization later.\nAn empty policy means that the request is executed immediately without authorization."]})}),(0,s.jsxs)(r.p,{children:[(0,s.jsx)(r.strong,{children:"POST"})," ",(0,s.jsx)(r.a,{href:"https://tsb-demo.cloudshsm.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/Keys/createKey",children:"/v1/key"})]}),(0,s.jsx)(r.pre,{children:(0,s.jsx)(r.code,{className:"language-js",metastring:"{15-18}",children:'{\n   "label": "<keyname>",\n   "algorithm": "EC",\n   "curveOid": "1.3.132.0.10",   \n   "attributes": {\n      "decrypt": false,\n      "sign": true,\n      "unwrap": false,\n      "destroyable": true\n   },\n   "policy": {\n      "ruleUse": null,\n      "ruleBlock": null,\n      "ruleUnblock": null,\n      "ruleModify": null,\n      "keyStatus": {\n         "blocked": false\n      }\n   }\n}\n'})})]})]}),"\n",(0,s.jsxs)(n,{children:[(0,s.jsxs)("summary",{children:[(0,s.jsx)(r.strong,{children:"Key"})," Parameters"]}),(0,s.jsxs)(r.table,{children:[(0,s.jsx)(r.thead,{children:(0,s.jsxs)(r.tr,{children:[(0,s.jsx)(r.th,{children:"Parameter"}),(0,s.jsx)(r.th,{children:"Description"})]})}),(0,s.jsxs)(r.tbody,{children:[(0,s.jsxs)(r.tr,{children:[(0,s.jsx)(r.td,{children:(0,s.jsx)(r.strong,{children:"label"})}),(0,s.jsxs)(r.td,{children:["The keyname e.g., ",(0,s.jsx)(r.code,{children:"TSB_TUTORIAL_1-RSA"}),"."]})]}),(0,s.jsxs)(r.tr,{children:[(0,s.jsx)(r.td,{children:(0,s.jsx)(r.strong,{children:"algorithm"})}),(0,s.jsxs)(r.td,{children:["The key algorithm. ",(0,s.jsx)(r.em,{children:(0,s.jsx)(r.a,{href:"/tsb/Concepts/key/Algorithms",children:"Supported algorithms"})}),"."]})]}),(0,s.jsxs)(r.tr,{children:[(0,s.jsx)(r.td,{children:(0,s.jsx)(r.strong,{children:"keySize"})}),(0,s.jsxs)(r.td,{children:["The size of key. ",(0,s.jsx)(r.em,{children:(0,s.jsx)(r.a,{href:"/tsb/Concepts/key/CreateKey#rsa-rivest-shamir-adleman",children:"Supported sizes"})})]})]}),(0,s.jsxs)(r.tr,{children:[(0,s.jsx)(r.td,{children:(0,s.jsx)(r.strong,{children:"curveOid"})}),(0,s.jsxs)(r.td,{children:["The curveOid (for EC/ED only). ",(0,s.jsx)(r.em,{children:(0,s.jsx)(r.a,{href:"/tsb/Concepts/key/Curve-Oid",children:"supported curveOid's"})})]})]}),(0,s.jsxs)(r.tr,{children:[(0,s.jsx)(r.td,{children:(0,s.jsx)(r.strong,{children:"decrypt"})}),(0,s.jsxs)(r.td,{children:["Key decrypt capability. ",(0,s.jsx)(r.em,{children:(0,s.jsx)(r.a,{href:"/tsb/Concepts/key/Attributes",children:"Key-Attributes"})})]})]}),(0,s.jsxs)(r.tr,{children:[(0,s.jsx)(r.td,{children:(0,s.jsx)(r.strong,{children:"sign"})}),(0,s.jsxs)(r.td,{children:["Key sign capability. ",(0,s.jsx)(r.em,{children:(0,s.jsx)(r.a,{href:"/tsb/Concepts/key/Attributes",children:"Key-Attributes"})})]})]}),(0,s.jsxs)(r.tr,{children:[(0,s.jsx)(r.td,{children:(0,s.jsx)(r.strong,{children:"unwrap"})}),(0,s.jsxs)(r.td,{children:["Key unwrap capability. ",(0,s.jsx)(r.em,{children:(0,s.jsx)(r.a,{href:"/tsb/Concepts/key/Attributes",children:"Key-Attributes"})})]})]}),(0,s.jsxs)(r.tr,{children:[(0,s.jsx)(r.td,{children:(0,s.jsx)(r.strong,{children:"destroyable"})}),(0,s.jsxs)(r.td,{children:["Key access. ",(0,s.jsx)(r.em,{children:(0,s.jsx)(r.a,{href:"/tsb/Concepts/key/Attributes",children:"Key-Attributes"})})]})]})]})]})]}),"\n",(0,s.jsxs)(n,{children:[(0,s.jsx)("summary",{children:(0,s.jsx)(r.strong,{children:"Attributes"})}),(0,s.jsxs)(r.ul,{children:["\n",(0,s.jsxs)(r.li,{children:["All Key Attributes are described ",(0,s.jsx)(r.em,{children:(0,s.jsx)(r.strong,{children:(0,s.jsx)(r.a,{href:"/tsb/Concepts/key/Attributes",children:"here"})})}),"."]}),"\n",(0,s.jsx)(r.li,{children:"Minimal attributes:"}),"\n"]}),(0,s.jsxs)(r.table,{children:[(0,s.jsx)(r.thead,{children:(0,s.jsxs)(r.tr,{children:[(0,s.jsx)(r.th,{children:"Attribute"}),(0,s.jsx)(r.th,{children:"Description"})]})}),(0,s.jsxs)(r.tbody,{children:[(0,s.jsxs)(r.tr,{children:[(0,s.jsx)(r.td,{children:"Decrypt"}),(0,s.jsx)(r.td,{children:"The key can be used for decryption, allowing it to transform ciphertext back into plaintext."})]}),(0,s.jsxs)(r.tr,{children:[(0,s.jsx)(r.td,{children:"Sign"}),(0,s.jsx)(r.td,{children:"The key can be used for creating digital signatures."})]}),(0,s.jsxs)(r.tr,{children:[(0,s.jsx)(r.td,{children:"Unwrap"}),(0,s.jsx)(r.td,{children:"The key can be used to unwrap (decrypt) encrypted keys."})]}),(0,s.jsxs)(r.tr,{children:[(0,s.jsx)(r.td,{children:"Destroyable"}),(0,s.jsx)(r.td,{children:"The key can be intentionally destroyed (deleted)."})]})]})]})]}),"\n",(0,s.jsxs)(n,{children:[(0,s.jsxs)("summary",{children:[(0,s.jsx)(r.strong,{children:"Policy"})," Parameters"]}),(0,s.jsxs)(r.table,{children:[(0,s.jsx)(r.thead,{children:(0,s.jsxs)(r.tr,{children:[(0,s.jsx)(r.th,{children:"Parameter"}),(0,s.jsx)(r.th,{children:"Description"})]})}),(0,s.jsxs)(r.tbody,{children:[(0,s.jsxs)(r.tr,{children:[(0,s.jsx)(r.td,{children:(0,s.jsx)(r.strong,{children:"ruleUse"})}),(0,s.jsxs)(r.td,{children:["The ruleUse for ",(0,s.jsx)(r.strong,{children:"private-key Operations"})," such as: ",(0,s.jsx)(r.code,{children:"v1/sign"}),", ",(0,s.jsx)(r.code,{children:"/v1/decrypt"}),", ",(0,s.jsx)(r.code,{children:"/v1/unwrap"})]})]}),(0,s.jsxs)(r.tr,{children:[(0,s.jsx)(r.td,{children:(0,s.jsx)(r.strong,{children:"ruleModify"})}),(0,s.jsxs)(r.td,{children:["The ruleModify to modify the policy: ",(0,s.jsx)(r.code,{children:"v1/modify"})]})]}),(0,s.jsxs)(r.tr,{children:[(0,s.jsx)(r.td,{children:(0,s.jsx)(r.strong,{children:"ruleBlock"})}),(0,s.jsxs)(r.td,{children:["The ruleBlock to block usage of the private-key: ",(0,s.jsx)(r.code,{children:"v1/block"})]})]}),(0,s.jsxs)(r.tr,{children:[(0,s.jsx)(r.td,{children:(0,s.jsx)(r.strong,{children:"ruleUnblock"})}),(0,s.jsxs)(r.td,{children:["The ruleUnblock to unblock a blocked key: ",(0,s.jsx)(r.code,{children:"v1/unblock"})]})]}),(0,s.jsxs)(r.tr,{children:[(0,s.jsx)(r.td,{children:(0,s.jsx)(r.strong,{children:"tokens"})}),(0,s.jsxs)(r.td,{children:["A Token array, which are ",(0,s.jsx)(r.strong,{children:"OR"})," associated, if multiple tokens are specified, either token1 or token2 has the be satisfied."]})]}),(0,s.jsxs)(r.tr,{children:[(0,s.jsx)(r.td,{children:(0,s.jsx)(r.strong,{children:"timelock"})}),(0,s.jsx)(r.td,{children:"The timelock before the approval is accepted, in seconds, a multiple of 60"})]}),(0,s.jsxs)(r.tr,{children:[(0,s.jsx)(r.td,{children:(0,s.jsx)(r.strong,{children:"timeout"})}),(0,s.jsx)(r.td,{children:"The timeout after which no approvals are accepted, in seconds, a multiple of 60"})]}),(0,s.jsxs)(r.tr,{children:[(0,s.jsx)(r.td,{children:(0,s.jsx)(r.strong,{children:"groups"})}),(0,s.jsxs)(r.td,{children:["An Group array, which are ",(0,s.jsx)(r.strong,{children:"AND"})," associated, if multiple groups with quorum 1 is specified, each group has to fullfill the quorum."]})]}),(0,s.jsxs)(r.tr,{children:[(0,s.jsx)(r.td,{children:(0,s.jsx)(r.strong,{children:"quorum"})}),(0,s.jsx)(r.td,{children:"Quorum of 1 means that only 1 approver needs to sign in order to get a request EXECUTED"})]}),(0,s.jsxs)(r.tr,{children:[(0,s.jsx)(r.td,{children:(0,s.jsx)(r.strong,{children:"approvals"})}),(0,s.jsx)(r.td,{children:"The approvers (mobile applications) onboarded to the policy. In order to use the key, the Approver has to approve the request, before it gets executed."})]}),(0,s.jsxs)(r.tr,{children:[(0,s.jsx)(r.td,{children:(0,s.jsx)(r.strong,{children:"type"})}),(0,s.jsxs)(r.td,{children:["The type can vary based on your preference: ",(0,s.jsx)(r.em,{children:"certificate"}),", ",(0,s.jsx)(r.em,{children:"public-key"})," or ",(0,s.jsx)(r.em,{children:"onboarded_approver_certificate"})," (for approverManagement API only!)"]})]}),(0,s.jsxs)(r.tr,{children:[(0,s.jsx)(r.td,{}),(0,s.jsx)(r.td,{})]}),(0,s.jsxs)(r.tr,{children:[(0,s.jsx)(r.td,{children:(0,s.jsx)(r.strong,{children:"type"})}),(0,s.jsxs)(r.td,{children:["The type, for the use of Securosys Authorization App, it is always  ",(0,s.jsx)(r.code,{children:"onboarded_approver_certificate"})]})]}),(0,s.jsxs)(r.tr,{children:[(0,s.jsx)(r.td,{children:(0,s.jsx)(r.strong,{children:"value"})}),(0,s.jsx)(r.td,{children:"The name of the onboarded approver."})]})]})]}),(0,s.jsx)(r.admonition,{title:"Policy",type:"note",children:(0,s.jsxs)(r.ul,{children:["\n",(0,s.jsxs)(r.li,{children:["For more infromation about SmartKeyAttributes and the Approval Workflow: ",(0,s.jsx)(r.a,{href:"/tsb/Tutorials/TransactionSecurityBroker/transaction-security-broker",children:"TransactionSecurityBroker"})]}),"\n",(0,s.jsxs)(r.li,{children:["For more information about the Policy: ",(0,s.jsx)(r.a,{href:"/tsb/Tutorials/TransactionSecurityBroker/smart-key-attributes",children:"SKA-Policy"})]}),"\n"]})})]}),"\n",(0,s.jsx)("br",{}),"\n",(0,s.jsx)(r.h1,{id:"whats-next",children:"What's next?"}),"\n",(0,s.jsxs)(r.ul,{children:["\n",(0,s.jsx)(r.li,{children:(0,s.jsx)(r.a,{href:"/tsb/Tutorials/TransactionSecurityBroker/samples/step-by-step/signature-request",children:"Create Multi Authorization Request"})}),"\n"]})]})}function p(e={}){const{wrapper:r}={...(0,i.R)(),...e.components};return r?(0,s.jsx)(r,{...e,children:(0,s.jsx)(h,{...e})}):h(e)}},65537:(e,r,t)=>{t.d(r,{A:()=>A});var n=t(96540),s=t(34164),i=t(65627),a=t(56347),o=t(50372),l=t(30604),c=t(11861),d=t(78749);function u(e){return n.Children.toArray(e).filter((e=>"\n"!==e)).map((e=>{if(!e||(0,n.isValidElement)(e)&&function(e){const{props:r}=e;return!!r&&"object"==typeof r&&"value"in r}(e))return e;throw new Error(`Docusaurus error: Bad <Tabs> child <${"string"==typeof e.type?e.type:e.type.name}>: all children of the <Tabs> component should be <TabItem>, and every <TabItem> should have a unique "value" prop.`)}))?.filter(Boolean)??[]}function h(e){const{values:r,children:t}=e;return(0,n.useMemo)((()=>{const e=r??function(e){return u(e).map((e=>{let{props:{value:r,label:t,attributes:n,default:s}}=e;return{value:r,label:t,attributes:n,default:s}}))}(t);return function(e){const r=(0,c.XI)(e,((e,r)=>e.value===r.value));if(r.length>0)throw new Error(`Docusaurus error: Duplicate values "${r.map((e=>e.value)).join(", ")}" found in <Tabs>. Every value needs to be unique.`)}(e),e}),[r,t])}function p(e){let{value:r,tabValues:t}=e;return t.some((e=>e.value===r))}function y(e){let{queryString:r=!1,groupId:t}=e;const s=(0,a.W6)(),i=function(e){let{queryString:r=!1,groupId:t}=e;if("string"==typeof r)return r;if(!1===r)return null;if(!0===r&&!t)throw new Error('Docusaurus error: The <Tabs> component groupId prop is required if queryString=true, because this value is used as the search param name. You can also provide an explicit value such as queryString="my-search-param".');return t??null}({queryString:r,groupId:t});return[(0,l.aZ)(i),(0,n.useCallback)((e=>{if(!i)return;const r=new URLSearchParams(s.location.search);r.set(i,e),s.replace({...s.location,search:r.toString()})}),[i,s])]}function x(e){const{defaultValue:r,queryString:t=!1,groupId:s}=e,i=h(e),[a,l]=(0,n.useState)((()=>function(e){let{defaultValue:r,tabValues:t}=e;if(0===t.length)throw new Error("Docusaurus error: the <Tabs> component requires at least one <TabItem> children component");if(r){if(!p({value:r,tabValues:t}))throw new Error(`Docusaurus error: The <Tabs> has a defaultValue "${r}" but none of its children has the corresponding value. Available values are: ${t.map((e=>e.value)).join(", ")}. If you intend to show no default tab, use defaultValue={null} instead.`);return r}const n=t.find((e=>e.default))??t[0];if(!n)throw new Error("Unexpected error: 0 tabValues");return n.value}({defaultValue:r,tabValues:i}))),[c,u]=y({queryString:t,groupId:s}),[x,m]=function(e){let{groupId:r}=e;const t=function(e){return e?`docusaurus.tab.${e}`:null}(r),[s,i]=(0,d.Dv)(t);return[s,(0,n.useCallback)((e=>{t&&i.set(e)}),[t,i])]}({groupId:s}),b=(()=>{const e=c??x;return p({value:e,tabValues:i})?e:null})();(0,o.A)((()=>{b&&l(b)}),[b]);return{selectedValue:a,selectValue:(0,n.useCallback)((e=>{if(!p({value:e,tabValues:i}))throw new Error(`Can't select invalid tab value=${e}`);l(e),u(e),m(e)}),[u,m,i]),tabValues:i}}var m=t(9136);const b={tabList:"tabList__CuJ",tabItem:"tabItem_LNqP"};var j=t(74848);function g(e){let{className:r,block:t,selectedValue:n,selectValue:a,tabValues:o}=e;const l=[],{blockElementScrollPositionUntilNextRender:c}=(0,i.a_)(),d=e=>{const r=e.currentTarget,t=l.indexOf(r),s=o[t].value;s!==n&&(c(r),a(s))},u=e=>{let r=null;switch(e.key){case"Enter":d(e);break;case"ArrowRight":{const t=l.indexOf(e.currentTarget)+1;r=l[t]??l[0];break}case"ArrowLeft":{const t=l.indexOf(e.currentTarget)-1;r=l[t]??l[l.length-1];break}}r?.focus()};return(0,j.jsx)("ul",{role:"tablist","aria-orientation":"horizontal",className:(0,s.A)("tabs",{"tabs--block":t},r),children:o.map((e=>{let{value:r,label:t,attributes:i}=e;return(0,j.jsx)("li",{role:"tab",tabIndex:n===r?0:-1,"aria-selected":n===r,ref:e=>{l.push(e)},onKeyDown:u,onClick:d,...i,className:(0,s.A)("tabs__item",b.tabItem,i?.className,{"tabs__item--active":n===r}),children:t??r},r)}))})}function f(e){let{lazy:r,children:t,selectedValue:i}=e;const a=(Array.isArray(t)?t:[t]).filter(Boolean);if(r){const e=a.find((e=>e.props.value===i));return e?(0,n.cloneElement)(e,{className:(0,s.A)("margin-top--md",e.props.className)}):null}return(0,j.jsx)("div",{className:"margin-top--md",children:a.map(((e,r)=>(0,n.cloneElement)(e,{key:r,hidden:e.props.value!==i})))})}function k(e){const r=x(e);return(0,j.jsxs)("div",{className:(0,s.A)("tabs-container",b.tabList),children:[(0,j.jsx)(g,{...r,...e}),(0,j.jsx)(f,{...r,...e})]})}function A(e){const r=(0,m.A)();return(0,j.jsx)(k,{...e,children:u(e.children)},String(r))}},79329:(e,r,t)=>{t.d(r,{A:()=>a});t(96540);var n=t(34164);const s={tabItem:"tabItem_Ymn6"};var i=t(74848);function a(e){let{children:r,hidden:t,className:a}=e;return(0,i.jsx)("div",{role:"tabpanel",className:(0,n.A)(s.tabItem,a),hidden:t,children:r})}}}]);