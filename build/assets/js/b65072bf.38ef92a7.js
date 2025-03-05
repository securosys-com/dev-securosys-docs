"use strict";(self.webpackChunksecurosys_docs=self.webpackChunksecurosys_docs||[]).push([[67263],{28453:(e,n,a)=>{a.d(n,{R:()=>s,x:()=>i});var o=a(96540);const r={},t=o.createContext(r);function s(e){const n=o.useContext(t);return o.useMemo((function(){return"function"==typeof e?e(n):{...n,...e}}),[n,e])}function i(e){let n;return n=e.disableParentContext?"function"==typeof e.components?e.components(r):e.components||r:s(e.components),o.createElement(t.Provider,{value:n},e.children)}},39604:(e,n,a)=>{a.r(n),a.d(n,{assets:()=>l,contentTitle:()=>i,default:()=>u,frontMatter:()=>s,metadata:()=>o,toc:()=>p});const o=JSON.parse('{"id":"Tutorials/examples/policy_example","title":"policy.json - Secrets Engine plugin for HashiCorp Vault","description":"MariaDB Integration for Securosy\' Secrets Engine plugin for HashiCorp Vault","source":"@site/hc_sse/Tutorials/examples/policy_example.md","sourceDirName":"Tutorials/examples","slug":"/Tutorials/examples/policy_example","permalink":"/hc_sse/Tutorials/examples/policy_example","draft":false,"unlisted":false,"tags":[],"version":"current","lastUpdatedAt":1741162132000,"sidebarPosition":2,"frontMatter":{"sidebar_position":2,"title":"policy.json - Secrets Engine plugin for HashiCorp Vault","sidebar_label":"Example - policy.json","description":"MariaDB Integration for Securosy\' Secrets Engine plugin for HashiCorp Vault","keywords":["hsm","cloud hsm"]},"sidebar":"tutorialSidebar","previous":{"title":"MariaDB Integration","permalink":"/hc_sse/Tutorials/examples/mariadb_example"},"next":{"title":"MariaDB","permalink":"/hc_sse/Tutorials/integrations/mariadb"}}');var r=a(74848),t=a(28453);const s={sidebar_position:2,title:"policy.json - Secrets Engine plugin for HashiCorp Vault",sidebar_label:"Example - policy.json",description:"MariaDB Integration for Securosy' Secrets Engine plugin for HashiCorp Vault",keywords:["hsm","cloud hsm"]},i="Example: policy.json",l={},p=[];function c(e){const n={code:"code",h1:"h1",header:"header",pre:"pre",...(0,t.R)(),...e.components};return(0,r.jsxs)(r.Fragment,{children:[(0,r.jsx)(n.header,{children:(0,r.jsx)(n.h1,{id:"example-policyjson",children:"Example: policy.json"})}),"\n",(0,r.jsx)(n.pre,{children:(0,r.jsx)(n.code,{className:"language-js",children:'{\n    "ruleUse": {\n      "tokens": [\n        {\n          "name": "MAIN1",\n          "timelock": 0,\n          "timeout": 0,\n          "groups": [\n            {\n              "name": "MAIN1",\n              "quorum": 1,\n              "approvals": [\n                {\n                  "type": "public_key",\n                  "name": "replace_me_with_approval_name",\n                  "value":"replace_me_with_approval_key"\n                }\n              ]\n            }\n          ]\n        }\n      ]\n    },\n    "ruleBlock": {\n      "tokens": [\n        {\n          "name": "MAIN1",\n          "timelock": 0,\n          "timeout": 0,\n          "groups": [\n            {\n              "name": "MAIN1",\n              "quorum": 1,\n              "approvals": [\n                {\n                  "type": "public_key",\n                  "name": "replace_me_with_approval_name",\n                  "value":"replace_me_with_approval_key"\n                }\n              ]\n            }\n          ]\n        }\n      ]\n    },\n    "ruleUnblock": {\n      "tokens": [\n        {\n          "name": "MAIN1",\n          "timelock": 0,\n          "timeout": 0,\n          "groups": [\n            {\n              "name": "MAIN1",\n              "quorum": 1,\n              "approvals": [\n                {\n                  "type": "public_key",\n                  "name": "replace_me_with_approval_name",\n                  "value":"replace_me_with_approval_key"\n                }\n              ]\n            }\n          ]\n        }\n      ]\n    },\n    "ruleModify": {\n      "tokens": [\n        {\n          "name": "MAIN1",\n          "timelock": 0,\n          "timeout": 0,\n          "groups": [\n            {\n              "name": "MAIN1",\n              "quorum": 1,\n              "approvals": [\n                {\n                  "type": "public_key",\n                  "name": "replace_me_with_approval_name",\n                  "value":"replace_me_with_approval_key"\n                }\n              ]\n            }\n          ]\n        }\n      ]\n    },\n    "keyStatus": {\n      "blocked": false\n    }\n  }\n\n\n'})})]})}function u(e={}){const{wrapper:n}={...(0,t.R)(),...e.components};return n?(0,r.jsx)(n,{...e,children:(0,r.jsx)(c,{...e})}):c(e)}}}]);