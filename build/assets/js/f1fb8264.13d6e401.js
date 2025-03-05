"use strict";(self.webpackChunksecurosys_docs=self.webpackChunksecurosys_docs||[]).push([[46068],{24908:(e,n,r)=>{r.r(n),r.d(n,{assets:()=>a,contentTitle:()=>c,default:()=>p,frontMatter:()=>i,metadata:()=>o,toc:()=>d});const o=JSON.parse('{"id":"Tutorials/decrypt","title":"Docker Encryption Plugin - Decrypt an Image","description":"Decrypt an Image","source":"@site/docker_encryption/Tutorials/decrypt.md","sourceDirName":"Tutorials","slug":"/Tutorials/decrypt","permalink":"/docker_encryption/Tutorials/decrypt","draft":false,"unlisted":false,"tags":[],"version":"current","lastUpdatedAt":1741162132000,"sidebarPosition":2,"frontMatter":{"sidebar_position":2,"title":"Docker Encryption Plugin - Decrypt an Image","sidebar_label":"Decrypt an Image","description":"Decrypt an Image","keywords":["hsm","cloud hsm"]},"sidebar":"tutorialSidebar","previous":{"title":"Encrypt an Image","permalink":"/docker_encryption/Tutorials/encrypt"},"next":{"title":"Troubleshooting","permalink":"/docker_encryption/Tutorials/troubleshooting"}}');var t=r(74848),s=r(28453);const i={sidebar_position:2,title:"Docker Encryption Plugin - Decrypt an Image",sidebar_label:"Decrypt an Image",description:"Decrypt an Image",keywords:["hsm","cloud hsm"]},c="Decrypt an Image",a={},d=[{value:"Decrypt Docker image using local registry",id:"decrypt-docker-image-using-local-registry",level:2},{value:"Example",id:"example",level:3},{value:"Simplified Docker Image Decryption Example (optional)",id:"simplified-docker-image-decryption-example-optional",level:2},{value:"Example",id:"example-1",level:3}];function l(e){const n={a:"a",admonition:"admonition",code:"code",em:"em",h1:"h1",h2:"h2",h3:"h3",header:"header",p:"p",pre:"pre",strong:"strong",table:"table",tbody:"tbody",td:"td",th:"th",thead:"thead",tr:"tr",...(0,s.R)(),...e.components};return(0,t.jsxs)(t.Fragment,{children:[(0,t.jsx)(n.header,{children:(0,t.jsx)(n.h1,{id:"decrypt-an-image",children:"Decrypt an Image"})}),"\n",(0,t.jsx)(n.p,{children:"After successfully encrypting an image, proceed with the decryption."}),"\n",(0,t.jsx)(n.admonition,{title:"CAUTION",type:"tip",children:(0,t.jsxs)(n.p,{children:["This guide assumes the operating system used is ",(0,t.jsx)(n.strong,{children:"Linux Ubuntu 22"}),"."]})}),"\n",(0,t.jsx)(n.h2,{id:"decrypt-docker-image-using-local-registry",children:"Decrypt Docker image using local registry"}),"\n",(0,t.jsxs)(n.p,{children:["Decrypt the formerly created encrypted docker image and tag it to alpine",":latest-decrypted"," using below command. Adapt the values ",(0,t.jsx)(n.code,{children:"<password"}),", ",(0,t.jsx)(n.code,{children:"<pathToConfig>"}),", ",(0,t.jsx)(n.code,{children:"<keyLabel>"})," to your needs."]}),"\n",(0,t.jsx)(n.pre,{children:(0,t.jsx)(n.code,{className:"language-sh",children:"[KEY_PASSWORD=password] OCICRYPT_KEYPROVIDER_CONFIG=/<pathToConfig>/ocicrypt.conf skopeo --override-os mac copy --decryption-key provider:skopeo-securosys:<keyLabel> --src-tls-verify=false -- dest-tls-verify=false docker://localhost:5000/alpine:latest-encrypted-<keyLabel> docker://localhost:5000/alpine:latest-decrypted \n"})}),"\n",(0,t.jsx)(n.h3,{id:"example",children:"Example"}),"\n",(0,t.jsx)(n.p,{children:"Example command and its output:"}),"\n",(0,t.jsx)(n.pre,{children:(0,t.jsx)(n.code,{className:"language-sh",children:"OCICRYPT_KEYPROVIDER_CONFIG=/home/Securosys/Securosys/skopeo/ocicrypt.conf skopeo -- override-os mac copy --decryption-key provider:skopeo-securosys:SecurosysEncKey21 --src- tls-verify=false --dest-tls-verify=false docker://localhost:5000/alpine:latest- encrypted-SecurosysEncKey01 docker://localhost:5000/alpine:latest-decrypted\n"})}),"\n",(0,t.jsx)(n.pre,{children:(0,t.jsx)(n.code,{children:"Getting image source signatures \nCopying blob d1a9fbe0d395 done   |  \nCopying config 1021c26281 done   |  \nWriting manifest to image destination\n"})}),"\n",(0,t.jsx)(n.h2,{id:"simplified-docker-image-decryption-example-optional",children:"Simplified Docker Image Decryption Example (optional)"}),"\n",(0,t.jsx)(n.p,{children:"Decrypt the previously encrypted image as shown below, following the example of using skopeo directly without the registry:"}),"\n",(0,t.jsx)(n.pre,{children:(0,t.jsx)(n.code,{children:"[KEY_PASSWORD=<password>] OCICRYPT_KEYPROVIDER_CONFIG=/<pathToConfig>/ocicrypt.conf skopeo --override-os linux copy --decryption-key provider:skopeo-securosys:<keyLabel> oci:alpine-encrypted oci:alpine-decrypted\n"})}),"\n",(0,t.jsx)(n.p,{children:"Command parameters:"}),"\n",(0,t.jsxs)(n.table,{children:[(0,t.jsx)(n.thead,{children:(0,t.jsxs)(n.tr,{children:[(0,t.jsx)(n.th,{children:(0,t.jsx)(n.strong,{children:"Command parameters:"})}),(0,t.jsx)(n.th,{children:(0,t.jsx)(n.strong,{children:"Description"})})]})}),(0,t.jsxs)(n.tbody,{children:[(0,t.jsxs)(n.tr,{children:[(0,t.jsxs)(n.td,{children:["KEY_PASSWORD=",(0,t.jsx)(n.strong,{children:(0,t.jsx)(n.code,{children:"<password>"})})]}),(0,t.jsxs)(n.td,{children:["Replace the ",(0,t.jsx)(n.strong,{children:(0,t.jsx)(n.code,{children:"<password>"})})," variable with the -key- password parameter in the configuration file."]})]}),(0,t.jsxs)(n.tr,{children:[(0,t.jsx)(n.td,{}),(0,t.jsx)(n.td,{})]}),(0,t.jsxs)(n.tr,{children:[(0,t.jsxs)(n.td,{children:["OCICRYPT_KEYPROVIDER_CONFIG=/",(0,t.jsx)(n.strong,{children:(0,t.jsx)(n.code,{children:"<pathToConfig>"})}),"/ocicrypt.conf"]}),(0,t.jsxs)(n.td,{children:["Replace the ",(0,t.jsx)(n.strong,{children:(0,t.jsx)(n.code,{children:"<pathToConfig>"})})," with your path to the Securosys Docker Image Encryption plugin config, ",(0,t.jsx)(n.strong,{children:(0,t.jsx)(n.code,{children:"${CONFIG_PATH}"})})," as set in chapter ",(0,t.jsx)(n.a,{href:"/docker_encryption/Installation/configuration#install-the-securosys-docker-image-encryption-plugin-config-file",children:"Installation - Install the Securosys Docker Image Encryption Plugin config file "})]})]})]})]}),"\n",(0,t.jsx)(n.h3,{id:"example-1",children:"Example"}),"\n",(0,t.jsx)(n.p,{children:"Example command and its output for the decryption of an image \u201calpine-encrypted\u201d and storing it as \u201calpine-decrypted\u201d. For a key without password:"}),"\n",(0,t.jsx)(n.pre,{children:(0,t.jsx)(n.code,{className:"language-sh",children:"OCICRYPT\\_KEYPROVIDER\\_CONFIG=/home/Securosys/Securosys/skopeo/ocicrypt.conf skopeo -- override-os linux copy --decryption-key provider:skopeo-securosys:SecurosysEncKey01 oci:alpine-encrypted oci:alpine-decrypted \n"})}),"\n",(0,t.jsx)(n.pre,{children:(0,t.jsx)(n.code,{children:"Getting image source signatures\nCopying blob 4029b2314db9 done\nCopying config 5c41fd95ee done\nWriting manifest to image destination \n"})}),"\n",(0,t.jsxs)(n.p,{children:["After successfully decryption the image is stored in directory ",(0,t.jsx)(n.em,{children:"decrypted"})," and can be used normally."]})]})}function p(e={}){const{wrapper:n}={...(0,s.R)(),...e.components};return n?(0,t.jsx)(n,{...e,children:(0,t.jsx)(l,{...e})}):l(e)}},28453:(e,n,r)=>{r.d(n,{R:()=>i,x:()=>c});var o=r(96540);const t={},s=o.createContext(t);function i(e){const n=o.useContext(s);return o.useMemo((function(){return"function"==typeof e?e(n):{...n,...e}}),[n,e])}function c(e){let n;return n=e.disableParentContext?"function"==typeof e.components?e.components(t):e.components||t:i(e.components),o.createElement(s.Provider,{value:n},e.children)}}}]);