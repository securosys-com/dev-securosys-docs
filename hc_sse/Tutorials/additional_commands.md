---
sidebar_position: 4
title: Command Options- Secrets Engine plugin for HashiCorp Vault
sidebar_label: Command Options
description: Additional Command Options for Securosy' Secrets Engine plugin for HashiCorp Vault
keywords: [hsm, cloud hsm]
---


# Additional Command Options
All **Securosys Secrets Engine** commands have the additional options:
- **-field** (string: "")
    - Prints only the field with the given name. Specifying this option will take precedence over other formatting directives. The result will not have a trailing newline making it ideal for piping to other processes.
- **-format** (string: "table")
    - Prints the output in the given format. The valid formats are "table", "json", "yaml", or "raw". This can also be specified via the VAULT_FORMAT environment variable.

## Help

The command **path-help** will print help information of a specific path:
```shell
$ vault path-help {path}
```
