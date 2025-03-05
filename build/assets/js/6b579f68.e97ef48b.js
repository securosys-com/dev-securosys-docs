"use strict";(self.webpackChunksecurosys_docs=self.webpackChunksecurosys_docs||[]).push([[18556],{28453:(e,t,n)=>{n.d(t,{R:()=>a,x:()=>o});var s=n(96540);const r={},i=s.createContext(r);function a(e){const t=s.useContext(i);return s.useMemo((function(){return"function"==typeof e?e(t):{...t,...e}}),[t,e])}function o(e){let t;return t=e.disableParentContext?"function"==typeof e.components?e.components(r):e.components||r:a(e.components),s.createElement(i.Provider,{value:t},e.children)}},76467:(e,t,n)=>{n.r(t),n.d(t,{assets:()=>h,contentTitle:()=>o,default:()=>l,frontMatter:()=>a,metadata:()=>s,toc:()=>d});const s=JSON.parse('{"id":"Use-Cases/ecies/ecies","title":"PKCS#11 - ECIES HSM implementation","description":"This features of the Primus PKCS#11 provider is a vendor defined","source":"@site/pkcs/Use-Cases/ecies/ecies.md","sourceDirName":"Use-Cases/ecies","slug":"/Use-Cases/ecies/","permalink":"/pkcs/Use-Cases/ecies/","draft":false,"unlisted":false,"tags":[],"version":"current","lastUpdatedAt":1741162132000,"sidebarPosition":1,"frontMatter":{"sidebar_position":1,"title":"PKCS#11 - ECIES HSM implementation","sidebar_label":"HSM implementation"},"sidebar":"tutorialSidebar","previous":{"title":"ECIES","permalink":"/pkcs/category/ecies"},"next":{"title":"Custom Implementation","permalink":"/pkcs/Use-Cases/ecies/custom_ecies_implementation"}}');var r=n(74848),i=n(28453);const a={sidebar_position:1,title:"PKCS#11 - ECIES HSM implementation",sidebar_label:"HSM implementation"},o="ECIES - HSM Implementation",h={},d=[{value:"ECIES Mechanism Parameters",id:"ecies-mechanism-parameters",level:3}];function c(e){const t={a:"a",admonition:"admonition",code:"code",em:"em",h1:"h1",h3:"h3",header:"header",p:"p",pre:"pre",table:"table",tbody:"tbody",td:"td",th:"th",thead:"thead",tr:"tr",...(0,i.R)(),...e.components};return(0,r.jsxs)(r.Fragment,{children:[(0,r.jsx)(t.header,{children:(0,r.jsx)(t.h1,{id:"ecies---hsm-implementation",children:"ECIES - HSM Implementation"})}),"\n",(0,r.jsx)(t.admonition,{type:"info",children:(0,r.jsx)(t.p,{children:"This features of the Primus PKCS#11 provider is a vendor defined\nextension and is not part of the standard PKCS#11 suite."})}),"\n",(0,r.jsx)(t.p,{children:"ECIES is a hybrid encryption scheme which allows to encrypt data to an\nECC public key but using symmetric cryptography for encryption and\nauthentication. The symmetric keys are derived from the receiver's\npublic key and an ephemeral private key of the sender with an ECDH\nagreement. The ephemeral key pair is used only for a single message:\nits private key will be deleted after encryption and the ephemeral\npublic key is part of the cryptogram such that the receiver can\nperform the same ECDH agreement and get the same shared symmetric\nkeys."}),"\n",(0,r.jsx)(t.p,{children:"The Primus PKCS#11 provider implements a convenient mechanism that\nallows to use ECIES with the common API of C_Encrypt* and C_Decrypt*\nfunctions."}),"\n",(0,r.jsx)(t.p,{children:"An example is shown below:"}),"\n",(0,r.jsx)(t.pre,{children:(0,r.jsx)(t.code,{className:"language-cpp",children:'CK_OBJECT_HANDLE hPublic = ...; // Public ECC key of the receiver.\nCK_SESSION_HANDLE hSession = ...;\n\nCK_ECIES_PARAMS params;\n// Set parameters to default values.\nmemset((void*)&params, 0, sizeof(params));\n\nparams.kdfMethod = CKD_SHA256_KDF; // KDF method used to derive the key from the ECDH result.\nparams.cryptMethod = CKM_AES_CBC_PAD; // Cipher algorithm.\nparams.ulCipherKeyLen = 32; // Cipher key length in bytes.\nparams.macMethod = CKM_SHA256_HMAC; // MAC algorithm.\nparams.ulMacKeyLen = 32; // Length of the MAC key in bytes.\nparams.ulMacTagLen = 32; // Length of the MAC tag in bytes.\n\n// Shared infos are optional.\n// If they are not defined, the pointers must be NULL and the lengths 0.\nparams.pSharedInfo1 = "123";\nparams.ulSharedInfo1Len = 3;\nparams.pSharedInfo2 = "1234";\nparams.ulSharedInfo2Len = 4;\n\n// The encryption of the data can optionally be done on the \n// local computer. This is much faster than sending all the data\n// to the HSM but requires that the ephemeral encryption key and\n// the MAC key are exported onto the local computer.\nparams.bLocalDataEncryption = false;\n\nCK_MECHANISM mechanism = {\n        CKM_ECIES, &params, sizeof(params)\n};\n\n// The API for encryption and decryption is the same as for standard mechanisms:\n\n// Initialize the ECIES encryption.\nCK_RV rv1 = C_EncryptInit(hSession, &mechanism, hPublic);\n// Encrypt data.\nCK_RV rv2 = C_Encrypt(hSession, plaintext.data(), plaintext.size(), ciphertext.data(), &ciphertextLen)\n'})}),"\n",(0,r.jsxs)(t.p,{children:["ECIES can also be ",(0,r.jsx)(t.a,{href:"/pkcs/Use-Cases/ecies/custom_ecies_implementation",children:"implemented using separate function\ncalls"})," for each of the necessary steps. This gives\nfiner grained control and allows to use algorithms that are otherwise\nnot supported for the symmetric cryptography operations."]}),"\n",(0,r.jsx)(t.h3,{id:"ecies-mechanism-parameters",children:"ECIES Mechanism Parameters"}),"\n",(0,r.jsxs)(t.table,{children:[(0,r.jsx)(t.thead,{children:(0,r.jsxs)(t.tr,{children:[(0,r.jsx)(t.th,{children:"Parameter"}),(0,r.jsx)(t.th,{children:"Description"})]})}),(0,r.jsxs)(t.tbody,{children:[(0,r.jsxs)(t.tr,{children:[(0,r.jsx)(t.td,{children:"kdfMethod"}),(0,r.jsx)(t.td,{children:"The key derivation function to be used. Supported values: CKD_SHA256_KDF, CKD_SHA512_KDF"})]}),(0,r.jsxs)(t.tr,{children:[(0,r.jsx)(t.td,{children:"cryptMethod"}),(0,r.jsx)(t.td,{children:"Cipher algorithm and mode for symmetric encryption.  Supported values: CKD_SHA256_KDF, CKD_SHA512_KDF"})]}),(0,r.jsxs)(t.tr,{children:[(0,r.jsx)(t.td,{children:"ulCipherKeyLen"}),(0,r.jsx)(t.td,{children:"Length of the symmetric cipher key in bytes.  Supported values for AES: 16, 24, 32"})]}),(0,r.jsxs)(t.tr,{children:[(0,r.jsx)(t.td,{children:"macMethod"}),(0,r.jsx)(t.td,{children:"MAC algorithm to be used for the authentication of the cipher text. Supported values: CKD_SHA256_KDF, CKD_SHA512_KDF"})]}),(0,r.jsxs)(t.tr,{children:[(0,r.jsx)(t.td,{children:"ulMacKeyLen"}),(0,r.jsx)(t.td,{children:"Length of the MAC key in bytes."})]}),(0,r.jsxs)(t.tr,{children:[(0,r.jsx)(t.td,{children:"ulMacTagLen"}),(0,r.jsx)(t.td,{children:"Length of the MAC tag in bytes.  Recommended value: 32"})]}),(0,r.jsxs)(t.tr,{children:[(0,r.jsx)(t.td,{children:"pSharedInfo1"}),(0,r.jsx)(t.td,{children:'Optional. Pointer to the data to be used for "sharedInfo1". This value is optional. If the pointer is set to NULL, then ulSharedInfo1Len must be 0.'})]}),(0,r.jsxs)(t.tr,{children:[(0,r.jsx)(t.td,{children:"ulSharedInfo1Len"}),(0,r.jsx)(t.td,{children:"Length of pSharedInfo1."})]}),(0,r.jsxs)(t.tr,{children:[(0,r.jsx)(t.td,{children:"pSharedInfo2"}),(0,r.jsx)(t.td,{children:'Optional. Pointer to the data to be used for "sharedInfo2". This value is optional. If the pointer is set to NULL, then ulSharedInfo2Len must be 0.'})]}),(0,r.jsxs)(t.tr,{children:[(0,r.jsx)(t.td,{children:"ulSharedInfo2Len"}),(0,r.jsx)(t.td,{children:"Length of pSharedInfo2."})]}),(0,r.jsxs)(t.tr,{children:[(0,r.jsx)(t.td,{children:"bLocalDataEncryption"}),(0,r.jsx)(t.td,{children:"If set to true, then the symmetric keys are exported to the local client and used to process the payload data locally. This way the payload data does not need to be sent to the HSM."})]}),(0,r.jsxs)(t.tr,{children:[(0,r.jsx)(t.td,{children:"bLocalKdf"}),(0,r.jsx)(t.td,{children:"When true: Compute the KDF locally (requires that the ECDH result is extractable). When false: Compute the KDF on the HSM."})]}),(0,r.jsxs)(t.tr,{children:[(0,r.jsx)(t.td,{children:"pKdfParameter"}),(0,r.jsx)(t.td,{children:"Mechanism parameters for the KDF function. May be necessary depending on the chosen KDF mechanism."})]}),(0,r.jsxs)(t.tr,{children:[(0,r.jsx)(t.td,{children:"ulKdfParameterLen"}),(0,r.jsx)(t.td,{children:"Byte length of the supplied KDF parameter data."})]}),(0,r.jsxs)(t.tr,{children:[(0,r.jsx)(t.td,{children:"hSenderPublicKey"}),(0,r.jsx)(t.td,{children:"Optional. If the handle is non-zero then bypass the generation of an ephemeral key pair and use this public key instead. For encryption also hSenderPrivateKey must be set to the corresponding private key handle."})]}),(0,r.jsxs)(t.tr,{children:[(0,r.jsx)(t.td,{children:"hSenderPrivateKey"}),(0,r.jsx)(t.td,{children:"Optional. If the handle is non-zero then bypass the generation of an ephemeral key pair and use this private key instead."})]}),(0,r.jsxs)(t.tr,{children:[(0,r.jsx)(t.td,{children:"bSkipSenderPublicKeyOutput"}),(0,r.jsx)(t.td,{children:"Optional. Disable the output of the public key in the ciphertext. For decryption requires that the sender public key is provided by hSenderPublicKey."})]})]})]}),"\n",(0,r.jsx)(t.admonition,{type:"warning",children:(0,r.jsxs)(t.p,{children:["When setting the parameters ",(0,r.jsx)(t.em,{children:"hSenderPublicKey"})," or\n",(0,r.jsx)(t.em,{children:"hSenderPrivateKey"})," the generation of an ephemeral key pair is\nbypassed. Only set these parameters when you know what you do! When\nusing non-ephemeral keys, a nonce must be fed into ",(0,r.jsx)(t.em,{children:"sharedInfo1"}),"  to\nguarantee that the derived symmetric keys are not used multiple times."]})})]})}function l(e={}){const{wrapper:t}={...(0,i.R)(),...e.components};return t?(0,r.jsx)(t,{...e,children:(0,r.jsx)(c,{...e})}):c(e)}}}]);