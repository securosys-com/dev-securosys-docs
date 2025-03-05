# Docusaurus Best Practices

This section showcases some best practices and general rules for the managing of documents in docusaurus.

## General Rules

- Whenever possible reference to central documentation, e.g.: 
A chapter describes the installation and configuration of PKCS#11 provider. Instead of having a whole section on the provider installation and configuration write a short intro and link to PKCS#11 provider documentation on docusaurus. E.g.:

Follow the [Primus HSM PKCS#11 Provider Installation](/pkcs/Installation/pkcs11_provider_installation) process to install the Primus PKCS#11 Provide on the CyberArk PAM Digital Vault machines.

- Join pages into one when there is little content on a single page. This does not apply to important pages where they have to be separate (e.g. Prerequisites)

- It is not our aim and our expertise to explain users how to setup / configure other companies' products/solutions. Write a remark on it and refer to the relevant documentation.



## Naming

General rules:
- Use small caps.
- Use abbreviations to keep the names short, e.g. pkcs (instead of PKCS#11), ms (instead of Microsoft), hsm (instead of Hardware Security Module), pam, iam, etc.
- Use `-` instead of spaces when required. 
- Keep the name as short as possible, e.g. `provider-installation` instead of `pkcs-provider-installation`
- Try to be as consistent as possible.
- Sometimes some names will have to be changed. Double check all possible links/headers/context/content to ensure they are working/understandable.

### Branches

Double check that the branch is named properly as it incurs plenty of problems otherwise.

### Projects

Double check that the project is named the same as the branch as it incurs plenty of problems otherwise.

### Categories

Make sure to adhere to the [General Project structure](#general-project-structure) naming conventions. 

### Images

- Name the images with the content they provide.
- 
- If they provide the same content from a different perspective, use the same name and attach it a subsequent number. 

### Pages

- Name the pages with the content they provide or the name they will have shown
- Adhere to the [General Project structure](#general-project-structure) naming conventions. 

## Styling

### Text

- Use **bold** whenever emphasis is required, but doesn't merit a tip/warning/note box. Usually used for a word or a few words.
- Use _Italic_ when ?

### Code

- Use the code box ("``... ``") for emphasizing shorter commands, file extensions and names
- Use the code box ("**`...`**) for GUI elements, Menu Navigation, etc.
- Use the triple code box ("```") without a style definition when listing configuration file contents,
- use triple code box with style definition (majority `bash`, only when oddly shown use without) For longer commands and command outputs, 
### Headers

Use the first header (`#`) only once and at the start(as the title of the page).
Use the second header for subsequent headers when needed.
 - never use the second header right after the first, always have some text in between. 

### Links

See [Links](./README-2.md#links) on how to use them.

### Tip/Note/Warning/Caution/Info Boxes

Tip:
This box contains helpful or important information which is useful but not required
Note:
This box contains helpful or important information which is normally required
Info:
Information ???
Caution/Warning:
This symbol means to be careful and obey all instructions. You might do something that could result in data loss or other bad things.
Use this very sparingly.

### Icons

Don't use custom icons, or use them when not otherwise possible.

### Tabs

See [Readme Tabview](/README-2.md#tabs) on how to add tabs.

Use tabs whenever there are two or more options to the same result, e.g. you have a description of commands for Linux, Windows. Generate 2 tabs instead of creating two separate code blocks back to back.

## General Project structure

Below is an example which showcases the top to bottom general structure on how it should look on the web, and not a folder structure. 
Configure the positions of categories and pages, see [SideBar Position](./README-2.md#sidebar-position) and [Category Position](./README-2.md#category-position).

Adhere to this structure as much as possible. Sometimes the document will not have any additional use cases, or have any thing to describe in the concept page, if so then remove the sections which cannot be included due to no content.

├── example-project
│   ├── _category_.json
│   └── overview.md
│   ├── get-started
│   │   ├── _category_.json
│   │   └── quickstart1.md
│   │   └── quickstart2.md
│   │   └── quickstart3.md
│   ├── use-Cases
│   │   ├── _category_.json
│   │   └── usecase1.md
│   │   └── usecase2.md
│   │   └── usecase3.md
│   ├── installation
│   │   ├── _category_.json
│   │   └── prerequisites.md
│   │   └── provider-installation.md
│   │   └── installation.md
│   ├── tutorial
│   │   ├── _category_.json
│   │   └── tutorial1.md
│   │   └── tutorial2.md
│   │   └── tutorial3.md
│   ├── concepts
│   │   ├── _category_.json
│   │   └── concept1.md
│   │   └── concept2.md
│   │   └── concept3.md
│   ├── download
│   │   ├── _category_.json
│   │   └── download.md
│   │   └── release-notes.md


In the next chapters, we'll dive more in-depth on the overall usage and best practices for each category. 

The General rule of categories and subcategories is that if there is only one page in it there should not be a separate category for it. Example:
Don't do this:
│   ├── Get Started
│   │   ├── _category_.json
│   │   └── quickstart.md
Do this:
│   ├── quickstart.md

Make sure to position the page the same way that the previous category was positioned.

## category description

the category.json file allows for the addition of the description. See more [Category Position](./README-2.md#category-position)

- Sadly currently there can only be text added, so we've opted out of adding any (but it requires a space).
 

## Overview

An overview should present the document, explain the service or solution from both or all products involved of the integration (application note) both from the Securosys side and the integration company/ies side.

more details?

The overview should always include the following headings as well:
 - Target Audience, explaining the target audience of the document.
 - Support Contact, explaining how and when to contact Securosys.
 - What's next, give a sentence long explanation of the next chapters/categories/sections (Quickstart, Installation, etc.) and linking them to it's corresponding category.

Example provided here: [Example Overview](/example-project/example-overview.md)

### get-started

The get started category is used for the quickstart sections. If only one quickstart is used (which will be in majority cases) please move the quickstart.md out from the Get Started category and delete the category.

### quickstart

The general concept of the quick start guide is to provide a comprehensive guide outlining the steps necessary to integrate the solution. Meaning it shouldn't be identical to the installation and other mentioned categories, but should entice the reader to move to these sections.

- The quickstart should be a "bullet point listing" (or headers instead) of the sequence. 
- It should provide all the required steps from start to finish of the whole integration, mentioning the download and finishing with the most important tutorial sections.
- Each step/bullet point should provide only a short description (without commands/details) and then link/refer to the more in depth description of the step. 
See example: [Quickstart](/example-project/Get-Started/example-quickstart)

## use-cases

The use cases category is used for "use cases", sections which describe other potential use of the tool/provider/product described in the documentation.

Example of such usecases for PKCS#11 are CyberArk PAM, pkcs11tool, PrimeKey EJBCA, HashiCorp Vault, etc.

In each usecase section in short describe the implementation/integration and its product.

Don't describe the whole integration/implementation and its steps (and commands/configurations etc.), as this is regarded as a "tutorial" and should be in its respective category.

## installation

The installation category should almost always include these three sections:
- Prerequisites
- Provider installation
- Installation

Sometimes there is no such content (such as prereqs or provider installation), so they can be removed.

Add more pages when required. 

The installation category should contain the following information:
- Generally any subject of the document that touches on the installation/setup/configuration.
- Prerequisites (requirements) of the integration/implementation
- Installation & configuration of the provider/provider credentials
- 

### Prerequisites

Write a short introductory sentence and then list the prereqs (requirements) with bullet points. 
- Some prereqs require commands to be explained in more detail. In such cases list them first under the bullet point and then describe them in a heading section. E.g. Primus CloudsHSM or Primus HSM Setup and Configuration, see [Example Prerequisites](/example-project/installation/example-prerequisites).

### Provider Installation

- The page should inform that it's not the scope of this document to install and configure the provider and then link to the installation and configuration of the provider project. e.g:

Follow the [Primus HSM PKCS#11 Provider Installation](/pkcs/Installation/pkcs11_provider_installation) process to install the Primus PKCS#11 Provide on the CyberArk PAM Digital Vault machines.

- Add any specific steps when required, e.g. disabling windows hardening on CyberArk server machines to be able to install pkcs provider
- Some projects don't require the provider installation (e.g. JCE), but do require access credentials and connectivity for the HSM. In such cases correct the naming of the page to reflect its contents. 


### Installation.md

The installation page provides the necessary steps to take to install/setup and configure the integration/installation.


## Tutorial

The tutorial category is used for pages that describe in detail procedures which use the now installed, configured and integrated HSMs.
Any troubleshooting documentation belongs to the tutorial page.


## Concepts

The concepts category is used for pages that describe any document specific concepts used in this documentation. 
 - These concepts can be either ours (e.g. referring to the SKA, setup password, etc.) or they can be global (e.g. concept of code signing, ) but not other company specific (e.g. cyberark vault)
 - include pages `references and more information` and `glossary`

## Download

The download section should include the download page and the release notes. The release notes sometimes are not specified/maintained for certain product so the page shouldn't be added, hence the download category should not be there, only the download page (see [General Project structure](#general-project-structure))


### Download.md

Use the [Example Download](/example-project/download/example-download) page. Make sure to change the downloadable products to the yours and change the download links/parameters/credentials.

In case the download of this product is already mentioned on a different document (e.g. pkcs11, jce, etc.), write a short reference statement and refer to the respective download section.