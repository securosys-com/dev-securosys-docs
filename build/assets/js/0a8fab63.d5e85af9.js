"use strict";(self.webpackChunksecurosys_docs=self.webpackChunksecurosys_docs||[]).push([[35452],{28453:(e,n,r)=>{r.d(n,{R:()=>i,x:()=>o});var a=r(96540);const t={},s=a.createContext(t);function i(e){const n=a.useContext(s);return a.useMemo((function(){return"function"==typeof e?e(n):{...n,...e}}),[n,e])}function o(e){let n;return n=e.disableParentContext?"function"==typeof e.components?e.components(t):e.components||t:i(e.components),a.createElement(s.Provider,{value:n},e.children)}},80541:(e,n,r)=>{r.r(n),r.d(n,{assets:()=>l,contentTitle:()=>o,default:()=>h,frontMatter:()=>i,metadata:()=>a,toc:()=>c});const a=JSON.parse('{"id":"Tutorials/examples/mariadb_example","title":"MariaDB Integration - Secrets Engine plugin for HashiCorp Vault","description":"MariaDB Integration for Securosy\' Secrets Engine plugin for HashiCorp Vault","source":"@site/hc_sse/Tutorials/examples/mariadb_example.md","sourceDirName":"Tutorials/examples","slug":"/Tutorials/examples/mariadb_example","permalink":"/hc_sse/Tutorials/examples/mariadb_example","draft":false,"unlisted":false,"tags":[],"version":"current","lastUpdatedAt":1741162132000,"sidebarPosition":1,"frontMatter":{"sidebar_position":1,"title":"MariaDB Integration - Secrets Engine plugin for HashiCorp Vault","sidebar_label":"MariaDB Integration","description":"MariaDB Integration for Securosy\' Secrets Engine plugin for HashiCorp Vault","keywords":["hsm","cloud hsm"]},"sidebar":"tutorialSidebar","previous":{"title":"Key Arguments","permalink":"/hc_sse/Tutorials/key_arguments"},"next":{"title":"Example - policy.json","permalink":"/hc_sse/Tutorials/examples/policy_example"}}');var t=r(74848),s=r(28453);const i={sidebar_position:1,title:"MariaDB Integration - Secrets Engine plugin for HashiCorp Vault",sidebar_label:"MariaDB Integration",description:"MariaDB Integration for Securosy' Secrets Engine plugin for HashiCorp Vault",keywords:["hsm","cloud hsm"]},o="MariaDB Integration usage example",l={},c=[];function d(e){const n={a:"a",code:"code",em:"em",h1:"h1",header:"header",li:"li",ol:"ol",p:"p",pre:"pre",strong:"strong",table:"table",tbody:"tbody",td:"td",th:"th",thead:"thead",tr:"tr",...(0,s.R)(),...e.components};return(0,t.jsxs)(t.Fragment,{children:[(0,t.jsx)(n.header,{children:(0,t.jsx)(n.h1,{id:"mariadb-integration-usage-example",children:"MariaDB Integration usage example"})}),"\n",(0,t.jsxs)(n.p,{children:["This example uses the default configuration for ",(0,t.jsx)(n.strong,{children:"Hashicorp Vault dev server"}),"."]}),"\n",(0,t.jsxs)(n.table,{children:[(0,t.jsx)(n.thead,{children:(0,t.jsxs)(n.tr,{children:[(0,t.jsx)(n.th,{children:"Data"}),(0,t.jsx)(n.th,{style:{textAlign:"center"},children:"Value"})]})}),(0,t.jsxs)(n.tbody,{children:[(0,t.jsxs)(n.tr,{children:[(0,t.jsx)(n.td,{children:(0,t.jsx)(n.strong,{children:"vault address"})}),(0,t.jsx)(n.td,{style:{textAlign:"center"},children:(0,t.jsx)(n.a,{href:"https://localhost:8200",children:"https://localhost:8200"})})]}),(0,t.jsxs)(n.tr,{children:[(0,t.jsx)(n.td,{children:(0,t.jsx)(n.strong,{children:"vault access token"})}),(0,t.jsx)(n.td,{style:{textAlign:"center"},children:"root"})]})]})]}),"\n",(0,t.jsxs)(n.ol,{children:["\n",(0,t.jsxs)(n.li,{children:[(0,t.jsx)(n.strong,{children:"Create key"})," ",(0,t.jsx)(n.em,{children:"MariaDBEncryptionKey"})," with key size ",(0,t.jsx)(n.em,{children:"4096"}),' with attributes at last "decrypt" equals ',(0,t.jsx)(n.em,{children:"true"})," on HSM and store it as ",(0,t.jsx)(n.em,{children:"mariadb_encryption_key"})," on ",(0,t.jsx)(n.strong,{children:"Secrets engine"}),"\n",(0,t.jsx)(n.pre,{children:(0,t.jsx)(n.code,{className:"language-shell",children:'$ vault write securosys-hsm/keys/rsa/mariadb_encryption_key \nkeyLabel="MariaDBEncryptionKey"\nkeySize=4096 \nattributes=\'{"decrypt": true,"sign": false,"unwrap": false,"derive": true,"sensitive": true,"extractable": false,"modifiable": false,"copyable": false,"destroyable": true}\'\n'})}),"\n","or","\n",(0,t.jsx)(n.pre,{children:(0,t.jsx)(n.code,{className:"language-shell",children:'curl --location --request PUT \'https://localhost:8200/v1/securosys-hsm/keys/rsa/mariadb_encryption_key\' \\\n--header \'X-Vault-Token: root\' \\\n--header \'Content-Type: application/x-www-form-urlencoded\' \\\n--data-urlencode \'keyLabel=MariaDBEncryptionKey\' \\\n--data-urlencode \'keySize=4096\' \\\n--data-urlencode \'attributes={\n    "decrypt": true,\n    "sign": false,\n    "unwrap": false,\n    "derive": true,\n    "sensitive": true,\n    "extractable": false,\n    "neverExtractable": true,\n    "modifiable": false,\n    "copyable": false,\n    "destroyable": true\n}\'\n'})}),"\n"]}),"\n",(0,t.jsxs)(n.li,{children:["Generate new ",(0,t.jsx)(n.strong,{children:"secret"})," called ",(0,t.jsx)(n.em,{children:"mariadb_secret"})," and ",(0,t.jsx)(n.strong,{children:"encrypt it"})," using cipher algorithm ",(0,t.jsx)(n.em,{children:"RSA"})," and stored key ",(0,t.jsx)(n.em,{children:"mariadb_encryption_key"})," in ",(0,t.jsx)(n.strong,{children:"Secrets engine"}),"\n",(0,t.jsx)(n.pre,{children:(0,t.jsx)(n.code,{className:"language-shell",children:"$ vault write securosys-hsm/integrations/mariadb/mariadb_secret   \nkeyName=mariadb_encryption_key\ncipherAlgorithm=RSA\n"})}),"\n","or","\n",(0,t.jsx)(n.pre,{children:(0,t.jsx)(n.code,{className:"language-shell",children:"curl --location --request PUT 'https://localhost:8200/v1/securosys-hsm/integrations/mariadb/mariadb_secret ' \\\n--header 'X-Vault-Token: root'\n--header 'Content-Type: application/x-www-form-urlencoded' \\\n--data-urlencode 'keyName=mariadb_encryption_key' \\\n--data-urlencode 'cipherAlgorithm=RSA'\n"})}),"\n"]}),"\n"]}),"\n",(0,t.jsxs)(n.ol,{start:"3",children:["\n",(0,t.jsxs)(n.li,{children:["\n",(0,t.jsxs)(n.p,{children:["Configure ",(0,t.jsx)(n.strong,{children:"MariaDB plugin"}),' "Hashicorp Key Management" in database configuration in ',(0,t.jsx)(n.strong,{children:"my.cnf"})]}),"\n",(0,t.jsx)(n.pre,{children:(0,t.jsx)(n.code,{className:"language-ini",children:'[mariadb]\nplugin-load-add=hashicorp_key_management.so\nloose-hashicorp-key-management\nloose-hashicorp-key-management-vault-url="https://localhost:8200/v1/securosys-hsm/integrations/mariadb/mariadb_secret/?key_name=mariadb_encryption_key&cipher_algorithm=RSA&version="\nloose-hashicorp-key-management-token="root"\nloose-hashicorp-key-management-check-kv-version="off"\n#max timeout is 86400 seconds\nloose-hashicorp-key-management-timeout=3000 \nloose-hashicorp-key-management-retries=0\nloose-hashicorp-key-management-use-cache-on-timeout="on"\nloose-hashicorp-key-management-caching-enabled="on"\n#1 year in miliseconds\nloose-hashicorp-key-management-cache-timeout=31556952000 \n#1 year in miliseconds\nloose-hashicorp-key-management-cache-version-timeout=31556952000\n\n#Example of innodb config\ninnodb_encrypt_tables = ON\ninnodb_encrypt_temporary_tables = ON\ninnodb_encrypt_log = ON\ninnodb_encryption_threads = 4\ninnodb_encryption_rotate_key_age = 1\n'})}),"\n"]}),"\n"]})]})}function h(e={}){const{wrapper:n}={...(0,s.R)(),...e.components};return n?(0,t.jsx)(n,{...e,children:(0,t.jsx)(d,{...e})}):d(e)}}}]);