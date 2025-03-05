# Docusaurus

This website is built using [Docusaurus 3](https://docs-dev.securosys.com/), a modern static website generator.

This file will help guide you on how to configure your VS Code client to be aligned with other members of the team, so that you can easily collaborate on tasks.

Specifically, you will learn about how to:

1. Install and configure **VS Code Extensions**
2. How to create a **New Project**
3. How to structure your new pages in a consistent and cohesive manner

## Prerequisites

If you haven't already, please follow README.md's instructions on how to setup your local environment first and then continue with this file.

# Code Spell Checker for VS Code (Optional)

To ensure that the documents/projects are spell-checked, introduce the Code Spell Checker extension to your Visual Studio Code.

1. Open Visual Studio Code and Move to **Extensions** (Icons on the top left side) and search for **Code Spell Checker**,
2. Click on the extension and click on **install**. 
3. After a successful installation click on the **cogwheel icon** and open the **extension settings**.
4. Now your files will have a highlight on all words that do not match the extention's Dictionary. If there are words that are spelled correctly (or are a technical term), you can Right click on them > Spelling > *Add Words to User Dictionary*

# How to create a new Project

When a new document/project must be added to docusaurus follow the next steps to create/initialize it. See [Video-Guide](/training/Project_branch%20creation%20-%20Docusaurus.mp4) or follow the below steps:

- First and foremost make sure to synchronize (pull) your develop branch before continuing.
```
# develop is the default branch
git switch develop
git pull
```
- Create a new branch and switch to the new branch, replace the `ExampleBranch` with your branch name:
```
git switch -c ExampleBranch
```
or 

```
git checkout -b ExampleBranch
```

- Copy&Paste the `template_project` folder and rename it with your branch name.
- Add the project/document to the home page under APP INTEGRATIONS.

1. Open `/securosys-docs/src/components/HomepageFeatures/index.tsx`
2. Navigate to `Integrations Start` section (marked in green).
3. If no group is available, create a new group below the latest existing one (copy paste from previous). If available, skip to step 7.
Example:
```
...
              </div>
<div className='about__section'>
                <h3 className='about__header'>New Group</h3>
                <ul className='about__ul'>
                  <li className='about__li'>
                    <a className='about__a' href="/example/overview">
                      Example Project/Document Name
                      <svg xmlns="http://www.w3.org/2000/svg" height="24" viewBox="0 -960 960 960" width="24"><path d="M280-280h280v-80H280v80Zm0-160h400v-80H280v80Zm0-160h400v-80H280v80Zm-80 480q-33 0-56.5-23.5T120-200v-560q0-33 23.5-56.5T200-840h560q33 0 56.5 23.5T840-760v560q0 33-23.5 56.5T760-120H200Zm0-80h560v-560H200v560Zm0-560v560-560Z"/></svg>
                    </a>
                  </li>
                </ul>
              </div>
...
```
4. Specify a header in `<h3 className='about__header'>New Group</h3>`, replace `New Group`.
5. Refer to the project files in `<a className='about__a' href="/example/overview">"`, replace `/example/overview`.
6. Specify the Project/Document name after the reference to its files, replace `Example Project/Document Name`
```<a className='about__a' href="/example/overview">
                      Example Project/Document Name
```
7. If a group is already created, add an additional `li` (copy paste from previous), see steps 5. and 6. to refer to your project files and specify Project name. Example:
```
...
                  </li>
<li className='about__li'>
                    <a className='about__a' href="/example/overview">
                      Example Project/Document Name
                      <svg xmlns="http://www.w3.org/2000/svg" height="24" viewBox="0 -960 960 960" width="24"><path d="M280-280h280v-80H280v80Zm0-160h400v-80H280v80Zm0-160h400v-80H280v80Zm-80 480q-33 0-56.5-23.5T120-200v-560q0-33 23.5-56.5T200-840h560q33 0 56.5 23.5T840-760v560q0 33-23.5 56.5T760-120H200Zm0-80h560v-560H200v560Zm0-560v560-560Z"/></svg>
                    </a>
                  </li>
...
```

8. Run `npm run start`, when the page opens you should see your project under APP INTEGRATIONS. If clicked the page will not yet be found. 

- For the the link to the project page to work we will have to configure the docusaurus to point to the project.

1. Open file `docusaurus.config.js`,
2. Navigate to the `plugins:` section and then `Integrations` (marked in green).
3. Create a new plugin section after the latest one (copy paste from previous). Make sure to add it after `],` of the previous section. Example:
```
...
    ],
  [
      "@docusaurus/plugin-content-docs",
       /** @type {import('@docusaurus/plugin-content-docs').Options} */     
      {           
        id: 'example',
        path: "example",
        routeBasePath: "example",
        sidebarPath: require.resolve("./sidebars.js"),
        showLastUpdateAuthor: false,
        showLastUpdateTime: true,
      },
    ],
```
4. Replace the `example` in `id`, `path`, `routeBasePath` with your project branch name.
5. Move to the `themes:` section and then `Integrations` (marked in green).
6. Create a new theme item after the latest one (copy paste from previous). Make sure to add it after `},` of the previous theme item. Example:
```
...
          },
          {
            type: 'docsVersionDropdown',
            docsPluginId: "example",
            dropdownActiveClassDisabled: true,
            position: 'right',
            x_dropdownlabel: "Example Project",
          },
...
```
7. Replace the `example` in `docsPluginId` with your project branch name. 
8. Replace the `Example Project` in `x_dropdownlabel` with your Project name. This will allow the project/document to be found on the dropdown menu.

For changes to take effect, restart the npm server by terminating the batch job. Then start it again via `npm run start`. 
Make sure to commit (and push) to the branch after a successful configuration.

# How to Define Grid Elements
In the `overview.md` file of your project add the following lines (as shown in the `template_project`). Each element is relevant to the grid view in the (HSM Solutions Explorer)[https://docs-dev.securosys.com/integrations]. Here is an *example* breakdown of each element
```
  # a title of your Project, to be shown in the grid
grid_title: Template Project
  # a list of tags that the clients can search/filter by
grid_search_tags: [Setup, Rest-API, Encryption]
  # a brief description of what your new project does
grid_description: Integrate Securosys External Key Store (XKS) Proxy with AWS KMS to manage keys in Hardware Security Modules (HSMs) securely, both cloud and on-premises.
  # a list of categories
grid_categories: [Setup, Rest-API, Encryption]
  # a url to a local file of a logo related to the product
grid_tile_logoUrl: '/img/securosys_logo.png'
```
These elements are auto populated by the `pluginData` plugin in `/static/pluginData.json`. If the overview grid elements are defined correctly, they will be reflected in this file.

:::note
Be careful not to introduce a new tag/category when one exists already. Unfortunately, there isn't a clean list of existing tags/categories to compare to, you have to just check and see.

For example, `Email`, `email` and `e-mail` should be synthesized into one `Email`.
:::

:::note
If you make changes to the grid elements they need to be rebuild by the plugin to show on your live-server instance. To do this, please run `npm run rebuild`. This will reload the pluginData, run a clean `build` and then run a `start`. This command runs slower as it has to re-create a lot of things. For best experience, use this sparingly.
:::

# How To Markdown

Markdown is a lightweight markup language that allows you to write simple, readable text that can be easily converted to HTML or other formats. It's widely used for creating documents, web pages, and README files. This user guide will introduce you to the basic syntax and features of Markdown.

**Markdown-Cheatsheet**: [Cheatsheet.html](https://www.markdownguide.org/cheat-sheet/)

**Icon Webpage**: [Font Awesome free-solid Icons](https://fontawesome.com/search?p=3&o=r&m=free&s=solid)

## Extended Docusaurus Syntax

### Add new Version (project-based)

```
cd tsb
npm run docusaurus docs:version:tsb 1.17.0
```

### File-Download
```
- Create RSA key [create_rsa.sh](./resources/ska/rsa/create_rsa.sh)
```

### Tabs
To include tabs first add the following section to import the tabs to the page:

```
import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';
```

To add new tabs see the example below. Make sure to specify your `groupId`, `label`, 
```
<Tabs groupId="device-setup">
  <TabItem value="ui" label="HSM User Interface (LC Display) Primus X/S-Series" default>
      Some Text
  </TabItem>
  <TabItem value="cli" label="HSM Console Primus HSM, all Series" default>
      Some Text
  </TabItem>
</Tabs>
```

### SideBar Position

The position of a page within a folder (category) can be configured with the addition of the `sidebar_position` to the `.md` file:
```
---
sidebar_position: 1
---
```

### Category Position

The `_category_.json` file can be added to a folder to define it. Specify the `label`, `position` and the `description` of the label. Example:
{
  "label": "Installation",
  "position": 5,
  "link": {
    "type": "generated-index",
    "description": " "
  }
}

### Showing a Tip

![](/static/img/tip.png)

```
:::tip SKA

This symbol contains helpful or important information for setting up TSB onPremise

:::
```

### Showing a Warning

![](/static/img/warning.png)

```
:::danger Take care

The initial setup of the Primus HSM is not covered in this guide, follow the initial wizard for device setup.

:::
```

### Showing an image
 `![](../img/TSB_Architecture.png)`

 In case of multiple lower directories introduce `../` for each additional folder below the img folder. Example:
 `![](../../../img/Private-key3.png)`

### Icons
How to use Icons:
```
<Icon icon="fa-brands fa-github" size="lg" /> This is a GitHub icon.
```
```
<FontAwesomeIcon icon="fa-arrow-right" size="sm" /> Arrow
```

The arrow icon used to indicate navigation on the device user
interface (e.g. SETUP -> CONFIGURATION -> SECURITY) has its own
shorthand option:
```
<IconNavNext />
```

### Add image description:
```
<figure class="image">
  ![](./img/ossl_p11_architecture.png)
  <figcaption>Architecture: The pkcs11-provider sits between the
  OpenSSL library and the PKCS#11 interface provided by
  Securosys</figcaption>
</figure>
```

### Code-block highlighting
Add the type of code directly after the triple backticks to get some
syntax highlighting. E.g.: ` ```bash `.

Highlight lines inside the code block using `// highlight-start` and
`// highlight-end`. Example:
```
\```js
...
    hsm0: {
// highlight-start
      host = "a-api.cloudshsm.com";
      port = "2310";
// highlight-end
      slots: {
         slot0: {
            client_id = "CLIENT_ID1";
// highlight-info-start
            user_name = "HSM_USERNAME";
            proxy_user = "SERVICE_USER";
// highlight-info-end
            id = 0;
         };
      }; /* end slots */
    }; /* end hsm0 */
    hsm1: {
// highlight-tip-start
      host = "b-api.cloudshsm.com";
      port = "2310";
// highlight-tip-end
      slots: {
         slot0: {
            client_id = "CLIENT_ID2";
// highlight-warning-start
            user_name = "HSM_USERNAME";
            proxy_user = "SERVICE_USER";
// highlight-warning-end
            id = 0;
         };
// highlight-danger-start
      }; /* end slots */
// highlight-danger-end
    }; /* end hsm1 */
...
\```
```
![](/static/img/code-highlight-light.png)
![](/static/img/code-highlight-dark.png)

## Links

Please try to keep the same naming of the chapter/page/title when specifying the link. 
### Page-Link (Same page)
To link to a chapter within the same page use the following syntax:
 `[Click Here](#rendering)`

- Replace "Click Here" with the name of the link,
- Replace "rendering" with the name of the chapter.

### Page-Link (Cross page)
Add a cross page link with the following syntax:
`[Release Notes](/tsb/Downloads/release_notes)`
Make sure to remove or not include `.md`. 

A cross page link to a page within the same folder can be done as such:
`[LINK](./overview.md)`

### Page-Link (Cross project)
`[JCE - Overview](/jce/overview)`

## Newlines and separators

**Newline** `<br />`

**Page separator** `---`

# Site Structure

```
.
└── securosys-docs
├── cloudshsm
│         ├── Concepts
│         │         ├── _category_.json
│         │         └── concepts.md
│         ├── Downloads
│         │         ├── _category_.json
│         │         └── downloads.md
│         ├── Installation
│         │         ├── _category_.json
│         │         └── installation.md
│         ├── Quickstart
│         │         ├── _category_.json
│         │         └── quickstart.md
│         ├── Tutorial
│         │         ├── _category_.json
│         │         └── tutorial.md
│         ├── Use-Cases
│         │         ├── _category_.json
│         │         └── usecases.md
│         ├── _category_.json
│         └── overview.md
├── deploy
│         ├── conf
│         │         ├── init-letsencrypt.sh
│         │         └── nginx
│         │             └── app.conf
│         └── docker-compose.yml
├── docs
│         └── DeveloperProgram.md
├── docusaurus.config.js
├── hsm
│         ├── Concepts
│         │         ├── _category_.json
│         │         └── concepts.md
│         ├── Downloads
│         │         ├── _category_.json
│         │         └── downloads.md
│         ├── Installation
│         │         ├── Setup
│         │         │         ├── Update
│         │         │         │         └── HSM-Update-to-v2-11-1.md
│         │         │         ├── UserGuide
│         │         │         │         ├── 1_Network
│         │         │         │         │         └── network.md
│         │         │         │         ├── 2_Policy
│         │         │         │         │         └── policy.md
│         │         │         │         ├── 3_Configuration
│         │         │         │         │         ├── configuration.md
│         │         │         │         │         └── reference-configuration.md
│         │         │         │         ├── 4_User
│         │         │         │         │         ├── create.md
│         │         │         │         │         └── user.md
│         │         │         │         ├── 5_Commands
│         │         │         │         │         └── commands.md
│         │         │         │         ├── GetStarted
│         │         │         │         │         ├── HSM-Setup-v2-11-1.md
│         │         │         │         │         └── HSM-Setup-v2.8.md
│         │         │         │         └── _category_.json
│         │         │         └── _category_.json
│         │         └── _category_.json
│         ├── Quickstart
│         │         ├── _category_.json
│         │         └── quickstart.md
│         ├── Tutorial
│         │         ├── _category_.json
│         │         └── tutorial.md
│         ├── Use-Cases
│         │         ├── _category_.json
│         │         └── usecases.md
│         ├── img
│         │         └── HSM
│         │             ├── JWT-Auth.png
│         │             ├── JWT-Enter-JWT.png
│         │             ├── TSB-Diagram.png
│         │             ├── TSB-Diagram.png:Zone.Identifier
│         │             ├── genesis_card.png
│         │             ├── hsm_console_icon.png
│         │             ├── hsm_user_interface_icon.png
│         │             └── so_card.png
│         └── overview.md
├── jce
│         ├── Concepts
│         │         ├── _category_.json
│         │         └── concepts.md
│         ├── Downloads
│         │         ├── _category_.json
│         │         └── downloads.md
│         ├── Installation
│         │         ├── _category_.json
│         │         └── installation.md
│         ├── Quickstart
│         │         ├── _category_.json
│         │         └── quickstart.md
│         ├── Tutorial
│         │         ├── _category_.json
│         │         └── tutorial.md
│         ├── Use-Cases
│         │         ├── _category_.json
│         │         └── usecases.md
│         ├── _category_.json
│         └── overview.md
├── mscng
│         ├── Concepts
│         │         ├── _category_.json
│         │         └── concepts.md
│         ├── Downloads
│         │         ├── _category_.json
│         │         └── downloads.md
│         ├── Installation
│         │         ├── _category_.json
│         │         └── installation.md
│         ├── Quickstart
│         │         ├── _category_.json
│         │         └── quickstart.md
│         ├── Tutorial
│         │         ├── _category_.json
│         │         └── tutorial.md
│         ├── Use-Cases
│         │         ├── _category_.json
│         │         └── usecases.md
│         ├── _category_.json
│         └── overview.md
├── openssl
│         ├── Concepts
│         │         ├── _category_.json
│         │         └── concepts.md
│         ├── Downloads
│         │         ├── _category_.json
│         │         └── downloads.md
│         ├── Installation
│         │         ├── _category_.json
│         │         └── installation.md
│         ├── Quickstart
│         │         ├── _category_.json
│         │         └── quickstart.md
│         ├── Tutorial
│         │         ├── _category_.json
│         │         └── tutorial.md
│         ├── Use-Cases
│         │         ├── _category_.json
│         │         └── usecases.md
│         ├── _category_.json
│         └── overview.md
├── package-lock.json
├── package.json
├── pkcs
│         ├── Concepts
│         │         ├── _category_.json
│         │         └── concepts.md
│         ├── Downloads
│         │         ├── _category_.json
│         │         └── downloads.md
│         ├── Installation
│         │         ├── _category_.json
│         │         └── installation.md
│         ├── Quickstart
│         │         ├── _category_.json
│         │         └── quickstart.md
│         ├── Tutorial
│         │         ├── _category_.json
│         │         └── tutorial.md
│         ├── Use-Cases
│         │         ├── _category_.json
│         │         └── usecases.md
│         ├── _category_.json
│         └── overview.md
├── sidebars.js
├── sidebarsOpenssl.js
├── tsb
│         ├── Concepts
│         │         ├── 1_Key
│         │         │         ├── 0_CreateKey.md
│         │         │         ├── 1_Attributes.md
│         │         │         ├── 2_Algorithms.md
│         │         │         ├── AddressFormat.md
│         │         │         ├── Curve-Oid.md
│         │         │         └── KeyDerivation.md
│         │         ├── 2_Key-Operations
│         │         │         ├── Encrypt-Decrypt.md
│         │         │         ├── Signing-Verify.md
│         │         │         └── Wrap-Unwrap.md
│         │         ├── 3_Policy-SKA
│         │         │         └── CreateKey.md
│         │         ├── 4_Certificate
│         │         │         ├── CertificateSigningRequest.md
│         │         │         └── SelfSignedCertificate.md
│         │         └── _category_.json
│         ├── Downloads
│         │         ├── _category_.json
│         │         └── release_notes.md
│         ├── Installation
│         │         ├── On-Premise-Installation.md
│         │         ├── _category_.json
│         │         └── hsm-device-setup.md
│         ├── Quickstart
│         │         ├── 0_TSB-Quickstart.md
│         │         ├── 1_TSB-Installation-OnPremises.md
│         │         └── _category_.json
│         ├── Tutorials
│         │         ├── CreateBitcoinMasterKey.md
│         │         ├── CreateCsr.md
│         │         ├── EncryptDecrypt.md
│         │         ├── FileEncryption.md
│         │         ├── KeyAttestation.md
│         │         ├── SignPDF.md
│         │         ├── SignRequest.md
│         │         ├── SmartKeyAttributes.md
│         │         └── _category_.json
│         ├── Use-Cases
│         │         ├── CryptoCurrency-Transaction-Signing.md
│         │         ├── FileEncryption.md
│         │         ├── QualifiedSignature.md
│         │         └── _category_.json
│         ├── _category_.json
│         ├── overview.md
│         └── resources
│             └── ska
│                 └── rsa
│                     ├── create_rsa.sh
│                     ├── sign_rsa.sh
│                     └── sign_timestamp_rsa.sh
├── versions.json
└── yarn.lock
```
