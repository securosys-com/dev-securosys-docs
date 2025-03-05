// @ts-check
// Note: type annotations allow type checking and IDEs autocompletion


const { themes } = require('prism-react-renderer');
const lightTheme = themes.github;
const darkTheme = themes.dracula;

/** @type {import('@docusaurus/types').Config} */
const config = {
  title: 'Securosys Docs', // do not touch ! it will break the SEO
  tagline: 'Your Securosys journey starts here',
  favicon: 'img/favicon.ico',
  //index_name: 'https://docs-dev.securosys.com',


  // Set the production url of your site here
  url: 'https://dev-docs.securosys.com',
  // Set the /<baseUrl>/ pathname under which your site is served
  // For GitHub pages deployment, it is often '/<projectName>/'
  baseUrl: '/',

  // GitHub pages deployment config.
  // If you aren't using GitHub pages, you don't need these.
  organizationName: 'Securosys', // Usually your GitHub org/user name.
  projectName: 'Securosys - Docs', // Usually your repo name.

  onBrokenLinks: 'log',
  onBrokenAnchors: 'log',
  onBrokenMarkdownLinks: 'warn',

  // Even if you don't use internalization, you can use this field to set useful
  // metadata like html lang. For example, if your site is Chinese, you may want
  // to replace "en" with "zh-Hans".
  i18n: {
    defaultLocale: 'en',
    locales: ['en'],
  },

  scripts: [
    {
      src: 'https://cdnjs.cloudflare.com/polyfill/v3/polyfill.min.js',
      async: false,
    },
    {
      src: 'https://unpkg.com/@webcomponents/webcomponentsjs@2.1.3/webcomponents-bundle.js',
      async: false,
    },
    {
      src: 'https://unpkg.com/@statuspage/status-widget/dist/index.js',
      async: false,
    },
    {
      src: 'https://status.cloudshsm.com/embed/script.js',
      async: false,
    },
  ],

  plugins: [

    // =============================================================================== Redirect

    [
      '@docusaurus/plugin-client-redirects',
      {
        redirects: [
          //temporary redirect (SEO) - can be deleted after 01/06/2025
          {
            from: '/docker_signing/Tutorials/Plugin-Commands/Policy',  // Old URL
            to: '/docker_signing/Tutorials/Commands/Policy',    // New URL
          },
          {
            from: '/cloudshsm-connectivity/tsbaas_connectivity_details',  // Old URL
            to: '/connectivity-details/cloudhsm-connectivity-details#cloudhsm---tsbaas',    // New URL
          },
          {
            from: '/docker_signing/Tutorials/Plugin-Commands/GenerateCsr',  // Old URL
            to: '/docker_signing/Tutorials/Commands/GenerateCsr',    // New URL
          },
          {
            from: '/docker_signing/Tutorials/CreateSigningKey.md',  // Old URL
            to: '/docker_signing/Tutorials/CreateSigningKey',    // New URL
          },
          {
            from: '/docker_signing/Installation/',  // Old URL
            to: '/docker_signing/category/installation',    // New URL
          },
          {
            from: '/docker_signing/Tutorials/Plugin-Commands/',  // Old URL
            to: '/docker_signing/Tutorials/Commands/DescribeKey',    // New URL
          },
          {
            from: '/hc_sse/Concepts/Integrations/mariadb',  // Old URL
            to: '/hc_sse/Tutorials/integrations/mariadb',    // New URL
          },
          {
            from: '/pkcs/category/ecies---elliptic-curve-integrated-encryption-scheme',  // Old URL
            to: '/pkcs/category/ecies',    // New URL
          },
          {
            from: '/cloudshsm-connectivity/primus-hsm-connectivity-details',  // Old URL
            to: '/connectivity-details/on-premises-connectivity-details',    // New URL
          },
          {
            from: '/cloudshsm/Packages/economy',  // Old URL
            to: '/cloudhsm/Packages/economy',    // New URL
          },
          {
            from: '/openssl/osslv3/Installation/Prerequisites/openssl3',  // Old URL
            to: '/openssl/osslv3/Installation/prerequisites',    // New URL
          },
          /*{
            from: '/url',  // Old URL
            to: '/url',    // New URL
          },*/
        ],
      },
    ],

    // =============================================================================== PushFeedback
    [
      'docusaurus-pushfeedback',{
          project: '3tw49na6zq',
          version:'0.1.52',
          buttonPosition: 'center-right',
          modalPosition: 'sidebar-right',
          buttonStyle: 'dark'
      }
    ],
    // =============================================================================== Chatbot
    [
    'docusaurus-biel',{
            enable: true,
            project: '9ttjyso6y1',
            headerTitle: 'Securosys - Docs | chatbot',
            version: '0.1.25',
            bielButtonText: 'Need help?',
            // buttonPosition: 'center-right',
            // modalPosition: 'sidebar-right',            
            // buttonStyle: 'dark',
        }
    ],
    // =============================================================================== Products
    [
      "@docusaurus/plugin-content-docs",
      /** @type {import('@docusaurus/plugin-content-docs').Options} */
      {
        id: 'hsm',
        path: "hsm",
        routeBasePath: "hsm",
        sidebarPath: require.resolve("./sidebars.js"),
        showLastUpdateAuthor: false,
        showLastUpdateTime: true,
        sidebarCollapsed: true,
      },
    ],
    [
      '@docusaurus/plugin-content-docs',
      {
        id: "cloudhsm",
        path: "cloudhsm",
        routeBasePath: "cloudhsm",
        sidebarPath: require.resolve("./sidebars.js"),
        showLastUpdateAuthor: false,
        showLastUpdateTime: true,
        sidebarCollapsed: true,
      },
    ],
// =============================================================================== API's
  [
    "@docusaurus/plugin-content-docs",
    /** @type {import('@docusaurus/plugin-content-docs').Options} */
    {
      id: 'tsb',
      path: "tsb",
      routeBasePath: "tsb",
      sidebarPath: require.resolve("./sidebars.js"),
      showLastUpdateAuthor: false,
      showLastUpdateTime: true,
      sidebarCollapsed: true,
    },
  ],
  [
    "@docusaurus/plugin-content-docs",
    /** @type {import('@docusaurus/plugin-content-docs').Options} */
    {
      id: 'jce',
      path: "jce",
      routeBasePath: "jce",
      sidebarPath: require.resolve("./sidebars.js"),
      showLastUpdateAuthor: false,
      showLastUpdateTime: true,
      sidebarCollapsed: false,
    },
  ],
  [
    "@docusaurus/plugin-content-docs",
    /** @type {import('@docusaurus/plugin-content-docs').Options} */
    {
      id: 'pkcs',
      path: "pkcs",
      routeBasePath: "pkcs",
      sidebarPath: require.resolve("./sidebars.js"),
      showLastUpdateAuthor: false,
      showLastUpdateTime: true,
      sidebarCollapsed: true,
    },
  ],
  [
    "@docusaurus/plugin-content-docs",
    /** @type {import('@docusaurus/plugin-content-docs').Options} */
    {
      id: 'mscng',
      path: "mscng",
      routeBasePath: "mscng",
      sidebarPath: require.resolve("./sidebars.js"),
      showLastUpdateAuthor: false,
      showLastUpdateTime: true,
      sidebarCollapsed: false,
    },
  ],
// =============================================================================== Integrations
    [
      "@docusaurus/plugin-content-docs",
      /** @type {import('@docusaurus/plugin-content-docs').Options} */
      {
        id: 'openssl',
        path: "openssl",
        routeBasePath: "openssl",
        sidebarPath: require.resolve("./sidebarsOpenssl.js"),
        showLastUpdateAuthor: false,
        showLastUpdateTime: true,
        sidebarCollapsed: true,
      },
    ],
    [
      "@docusaurus/plugin-content-docs",
      /** @type {import('@docusaurus/plugin-content-docs').Options} */
      {
        id: 'AuthorizationApp',
        path: "AuthorizationApp",
        routeBasePath: "AuthorizationApp",
        sidebarPath: require.resolve("./sidebars.js"),
        showLastUpdateAuthor: false,
        showLastUpdateTime: true,
        sidebarCollapsed: true,
      },
    ],
    [
      "@docusaurus/plugin-content-docs",
      /** @type {import('@docusaurus/plugin-content-docs').Options} */
      {
        id: 'xks',
        path: "xks",
        routeBasePath: "xks",
        sidebarPath: require.resolve("./sidebars.js"),
        showLastUpdateAuthor: false,
        showLastUpdateTime: true,
        sidebarCollapsed: false,
        sidebarCollapsible: true,
      },
    ],
    [
      "@docusaurus/plugin-content-docs",
      /** @type {import('@docusaurus/plugin-content-docs').Options} */
      {
        id: 'hc_vault',
        path: "hc_vault",
        routeBasePath: "hc_vault",
        sidebarPath: require.resolve("./sidebars.js"),
        showLastUpdateAuthor: false,
        showLastUpdateTime: true,
        sidebarCollapsed: false,
      },
    ],
    [
      "@docusaurus/plugin-content-docs",
      /** @type {import('@docusaurus/plugin-content-docs').Options} */
      {
        id: 'hc_sse',
        path: "hc_sse",
        routeBasePath: "hc_sse",
        sidebarPath: require.resolve("./sidebars.js"),
        showLastUpdateAuthor: false,
        showLastUpdateTime: true,
        sidebarCollapsed: false,
      },
    ],
    [
      "@docusaurus/plugin-content-docs",
      /** @type {import('@docusaurus/plugin-content-docs').Options} */
      {
        id: 'docker_signing',
        path: "docker_signing",
        routeBasePath: "docker_signing",
        sidebarPath: require.resolve("./sidebars.js"),
        showLastUpdateAuthor: false,
        showLastUpdateTime: true,
        sidebarCollapsed: false,
        sidebarCollapsible: true,
      },
    ],
    [
      "@docusaurus/plugin-content-docs",
      /** @type {import('@docusaurus/plugin-content-docs').Options} */
      {
        id: 'docker_encryption',
        path: "docker_encryption",
        routeBasePath: "docker_encryption",
        sidebarPath: require.resolve("./sidebars.js"),
        showLastUpdateAuthor: false,
        showLastUpdateTime: true,
        sidebarCollapsed: false,
      },
    ],
    [
      "@docusaurus/plugin-content-docs",
      /** @type {import('@docusaurus/plugin-content-docs').Options} */
      {
        id: 'connectivity-details',
        path: "connectivity-details",
        routeBasePath: "connectivity-details",
        sidebarPath: require.resolve("./sidebars.js"),
        showLastUpdateAuthor: false,
        showLastUpdateTime: true,
        sidebarCollapsed: false,
      },
    ],
    [
      "@docusaurus/plugin-content-docs",
      /** @type {import('@docusaurus/plugin-content-docs').Options} */
      {
        id: 'securden-pam',
        path: "securden-pam",
        routeBasePath: "securden-pam",
        sidebarPath: require.resolve("./sidebars.js"),
        showLastUpdateAuthor: false,
        showLastUpdateTime: true,
        sidebarCollapsed: false,
      },
    ],
    [
      "@docusaurus/plugin-content-docs",
      /** @type {import('@docusaurus/plugin-content-docs').Options} */
      {
        id: 'aws-byok',
        path: "aws-byok",
        routeBasePath: "aws-byok",
        sidebarPath: require.resolve("./sidebars.js"),
        showLastUpdateAuthor: false,
        showLastUpdateTime: true,
        sidebarCollapsed: false,
      },
    ],
    [
      "@docusaurus/plugin-content-docs",
      /** @type {import('@docusaurus/plugin-content-docs').Options} */
      {
        id: 'ms-signtool',
        path: "ms-signtool",
        routeBasePath: "ms-signtool",
        sidebarPath: require.resolve("./sidebars.js"),
        showLastUpdateAuthor: false,
        showLastUpdateTime: true,
        sidebarCollapsed: false,
      },
    ],
    [
      "@docusaurus/plugin-content-docs",
      /** @type {import('@docusaurus/plugin-content-docs').Options} */
      {
        id: 'ms-smime',
        path: "ms-smime",
        routeBasePath: "ms-smime",
        sidebarPath: require.resolve("./sidebars.js"),
        showLastUpdateAuthor: false,
        showLastUpdateTime: true,
        sidebarCollapsed: false,
      },
    ],
    [
      "@docusaurus/plugin-content-docs",
      /** @type {import('@docusaurus/plugin-content-docs').Options} */
      {
        id: 'ms-pki-adcs',
        path: "ms-pki-adcs",
        routeBasePath: "ms-pki-adcs",
        sidebarPath: require.resolve("./sidebars.js"),
        showLastUpdateAuthor: false,
        showLastUpdateTime: true,
        sidebarCollapsed: true,
      },
    ],
    [
      "@docusaurus/plugin-content-docs",
      /** @type {import('@docusaurus/plugin-content-docs').Options} */
      {
        id: 'ms-sql-ae',
        path: "ms-sql-ae",
        routeBasePath: "ms-sql-ae",
        sidebarPath: require.resolve("./sidebars.js"),
        showLastUpdateAuthor: false,
        showLastUpdateTime: true,
        sidebarCollapsed: false,
      },
    ],
    [
      "@docusaurus/plugin-content-docs",
      /** @type {import('@docusaurus/plugin-content-docs').Options} */
      {
        id: 'microsoft-byok',
        path: "microsoft-byok",
        routeBasePath: "microsoft-byok",
        sidebarPath: require.resolve("./sidebars.js"),
        showLastUpdateAuthor: false,
        showLastUpdateTime: true,
        sidebarCollapsed: false,
      },
    ],
    [
      "@docusaurus/plugin-content-docs",
      /** @type {import('@docusaurus/plugin-content-docs').Options} */
      {
        id: 'ms-iis',
        path: "ms-iis",
        routeBasePath: "ms-iis",
        sidebarPath: require.resolve("./sidebars.js"),
        showLastUpdateAuthor: false,
        showLastUpdateTime: true,
        sidebarCollapsed: false,
      },
    ],
    [
      "@docusaurus/plugin-content-docs",
      /** @type {import('@docusaurus/plugin-content-docs').Options} */
      {
        id: 'ms-hgs',
        path: "ms-hgs",
        routeBasePath: "ms-hgs",
        sidebarPath: require.resolve("./sidebars.js"),
        showLastUpdateAuthor: false,
        showLastUpdateTime: true,
        sidebarCollapsed: false,
      },
    ],
    [
      "@docusaurus/plugin-content-docs",
      /** @type {import('@docusaurus/plugin-content-docs').Options} */
      {
        id: 'cyberark',
        path: "cyberark",
        routeBasePath: "cyberark",
        sidebarPath: require.resolve("./sidebars.js"),
        showLastUpdateAuthor: false,
        showLastUpdateTime: true,
        sidebarCollapsed: false,
      },
    ],
    [
      "@docusaurus/plugin-content-docs",
      /** @type {import('@docusaurus/plugin-content-docs').Options} */
      {
        id: 'sixscape',
        path: "sixscape",
        routeBasePath: "sixscape",
        sidebarPath: require.resolve("./sidebars.js"),
        showLastUpdateAuthor: false,
        showLastUpdateTime: true,
      },
    ],
    [
      "@docusaurus/plugin-content-docs",
      /** @type {import('@docusaurus/plugin-content-docs').Options} */
      {
        id: 'fortigate',
        path: "fortigate",
        routeBasePath: "fortigate",
        sidebarPath: require.resolve("./sidebars.js"),
        showLastUpdateAuthor: false,
        showLastUpdateTime: true,
        sidebarCollapsed: false,
      },
    ],
    [
      "@docusaurus/plugin-content-docs",
      /** @type {import('@docusaurus/plugin-content-docs').Options} */
      {
        id: 'salesforce-byok',
        path: "salesforce-byok",
        routeBasePath: "salesforce-byok",
        sidebarPath: require.resolve("./sidebars.js"),
        showLastUpdateAuthor: false,
        showLastUpdateTime: true,
        sidebarCollapsed: false,
      },
    ],
    [
      "@docusaurus/plugin-content-docs",
      /** @type {import('@docusaurus/plugin-content-docs').Options} */
      {
        id: 'primus-tools',
        path: "primus-tools",
        routeBasePath: "primus-tools",
        sidebarPath: require.resolve("./sidebars.js"),
        showLastUpdateAuthor: false,
        showLastUpdateTime: true,
        sidebarCollapsed: false,
      },
    ],
    [
      "@docusaurus/plugin-content-docs",
      /** @type {import('@docusaurus/plugin-content-docs').Options} */
      {
        id: 'versasec-cms',
        path: "versasec-cms",
        routeBasePath: "versasec-cms",
        sidebarPath: require.resolve("./sidebars.js"),
        showLastUpdateAuthor: false,
        showLastUpdateTime: true,
        sidebarCollapsed: false,
      },
    ],
    [
      "@docusaurus/plugin-content-docs",
      /** @type {import('@docusaurus/plugin-content-docs').Options} */
      {
        id: 'primekey-ejbca',
        path: "primekey-ejbca",
        routeBasePath: "primekey-ejbca",
        sidebarPath: require.resolve("./sidebars.js"),
        showLastUpdateAuthor: false,
        showLastUpdateTime: true,
        sidebarCollapsed: false,
      },
    ],
    [
      "@docusaurus/plugin-content-docs",
      /** @type {import('@docusaurus/plugin-content-docs').Options} */
      {
        id: 'hc-vault-enterprise',
        path: "hc-vault-enterprise",
        routeBasePath: "hc-vault-enterprise",
        sidebarPath: require.resolve("./sidebars.js"),
        showLastUpdateAuthor: false,
        showLastUpdateTime: true,
        sidebarCollapsed: false,
      },
    ],
    [
      "@docusaurus/plugin-content-docs",
      /** @type {import('@docusaurus/plugin-content-docs').Options} */
      {
        id: 'venafi',
        path: "venafi",
        routeBasePath: "venafi",
        sidebarPath: require.resolve("./sidebars.js"),
        showLastUpdateAuthor: false,
        showLastUpdateTime: true,
        sidebarCollapsed: false,
      },
    ],
    [
      "@docusaurus/plugin-content-docs",
      /** @type {import('@docusaurus/plugin-content-docs').Options} */
      {
        id: 'white-rabbit',
        path: "white-rabbit",
        routeBasePath: "white-rabbit",
        sidebarPath: require.resolve("./sidebars.js"),
        showLastUpdateAuthor: false,
        showLastUpdateTime: true,
        sidebarCollapsed: false,
      },
    ],
  ],
  // =============================================================================== END
  presets: [
    [
      '@docusaurus/preset-classic',
      /** @type {import('@docusaurus/preset-classic').Options} */
      ({
        theme: {
          customCss: require.resolve('./src/css/custom.css')
        },
        gtag: {
          trackingID: 'G-VY1N0BX740'
        },
        sitemap: {
          lastmod: 'date',
          changefreq: null,
          priority: null,
          ignorePatterns: ['/tags/**'],
          filename: 'sitemap.xml',
          createSitemapItems: async (params) => {
            const {defaultCreateSitemapItems, ...rest} = params;
            const items = await defaultCreateSitemapItems(rest);
            return items.filter((item) => !item.url.includes('/page/'));
          },
        },
      }),
    ],
  ],
  //themes: ['docusaurus-theme-search-typesense'],
  themeConfig:
    /** @type {import('@docusaurus/preset-classic').ThemeConfig} */
    ({
      /*typesense: {
        // Replace this with the name of your index/collection.
        // It should match the "index_name" entry in the scraper's "config.json" file.
        typesenseCollectionName: 'docusaurus-2',
  
        typesenseServerConfig: {
          nodes: [
            {
              host: 'typesense-index.securosys.com',
              port: 443,
              protocol: 'https',
            }
          ],
          apiKey: 'c2VjdXJvc3lz',
        },
  
        // Optional: Typesense search parameters: https://typesense.org/docs/0.24.0/api/search.html#search-parameters
        typesenseSearchParameters: {},
  
        // Optional
        contextualSearch: true,
      },*/
      algolia: {
        // The application ID provided by Algolia
        appId: 'YPB6OU7GI5',

        // Public API key: it is safe to commit it
        apiKey: 'dc9bdb8b2a76c1d5245337aff1f846e7',

        indexName: 'securosys',

        // Optional: see doc section below
        contextualSearch: true,

        // Optional: Specify domains where the navigation should occur through window.location instead on history.push. Useful when our Algolia config crawls multiple documentation sites and we want to navigate with window.location.href to them.
        //externalUrlRegex: 'external\\.com|domain\\.com',

        // Optional: Replace parts of the item URLs from Algolia. Useful when using the same search index for multiple deployments using a different baseUrl. You can use regexp or string in the `from` param. For example: localhost:3000 vs myCompany.com/docs
        replaceSearchResultPathname: {
          from: '/docs/', // or as RegExp: /\/docs\//
          to: '/',
        },

        // Optional: Algolia search parameters
        searchParameters: {},

        // Optional: path for search page that enabled by default (`false` to disable it)
        searchPagePath: 'search',

        // Optional: whether the insights feature is enabled or not on Docsearch (`false` by default)
        insights: false,
      },


      // Replace with your project's social card
      image: 'img/docusaurus-social-card.jpg',
      metadata: [
        {name: 'keywords', content: 'hsm, hardware security module, cloud hsm, security, securosys, fips 140-2 level 3, x hsm, documentation, developer, API, APIs, PKI, security broker, ethereum, tsb api, rest, JCE,PKCS#11'},
        {name: 'google-site-verification', content: 'H8Qp6DlzPErHhbe6evmQ6T5L3uU2B5pDoaI2UJX6-H0'},
      ],
      navbar: {
        title: '',
        logo: {
          alt: 'logo_Securosys',
          src: 'img/Logo - Subbrand - Docs - Black.png',
          srcDark: 'img/Logo - Subbrand - Docs - White.png',
        },
        //hideOnScroll: true, //conflict with white logo
        items: [
          {
            label: 'Products',
            type: 'custom-docsPluginIdDropdown-cloudhsm',
            position: 'left',
          },
          {
            label: 'APIs',
            type: 'custom-docsPluginIdDropdown-api-reference',
            position: 'left',
          },
          {
            label: 'Integrations',
            type: 'custom-docsPluginIdDropdown-integrations',
            position: 'left',
          },
          {
            to: 'integrations',
            label: 'HSM Solutions Explorer',
            position: 'left'
          },
          // =============================================================================== Products
          {
            type: 'docsVersionDropdown',
            docsPluginId: "hsm",
            dropdownActiveClassDisabled: false,
            position: 'right',
            // x_dropdownlabel: "Hardware Security Module (HSM)",
          },
          {
            type: 'docsVersionDropdown',
            docsPluginId: "cloudhsm",
            dropdownActiveClassDisabled: false,
            position: 'right',
            x_dropdownlabel_cloudhsm: "CloudHSM",
            homebreadcrumbitem: "Securosys",
          },
          {
            type: 'docsVersionDropdown',
            docsPluginId: "connectivity-details",
            dropdownActiveClassDisabled: false,
            position: 'right',
            x_dropdownlabel_cloudhsm: "HSM Connectivity Details",
          },
          {
            type: 'docsVersionDropdown',
            docsPluginId: "AuthorizationApp",
            dropdownActiveClassDisabled: false,
            position: 'right',
            x_dropdownlabel_cloudhsm: "Authorization App",
          },
          // =============================================================================== API's
          {
            type: 'docsVersionDropdown',
            docsPluginId: "tsb",
            dropdownActiveClassDisabled: false,
            position: 'right',
            x_dropdownlabel_api_reference: "Rest-API (Transaction Security Broker)",
          },
          {
            type: 'docsVersionDropdown',
            docsPluginId: "jce",
            dropdownActiveClassDisabled: false,
            position: 'right',
            x_dropdownlabel_api_reference: "Java Cryptography Extension",
          },
          {
            type: 'docsVersionDropdown',
            docsPluginId: "pkcs",
            dropdownActiveClassDisabled: false,
            position: 'right',
            x_dropdownlabel_api_reference: "PKCS#11 (Cryptoki)",
          },
          {
            type: 'docsVersionDropdown',
            docsPluginId: "openssl",
            dropdownActiveClassDisabled: false,
            position: 'right',
            x_dropdownlabel_api_reference: "OpenSSL",
          },
          {
            type: 'docsVersionDropdown',
            docsPluginId: "mscng",
            dropdownActiveClassDisabled: false,
            position: 'right',
            x_dropdownlabel_api_reference: "Microsoft CNG",
          },
          {
            type: 'docsVersionDropdown',
            docsPluginId: "primus-tools",
            dropdownActiveClassDisabled: false,
            position: 'right',
            x_dropdownlabel_api_reference: "Securosys Primus Tools",
          },
          // =============================================================================== Integrations
          {
            type: 'docsVersionDropdown',
            docsPluginId: "xks",
            dropdownActiveClassDisabled: false,
            position: 'right',
            x_dropdownlabel_integrations: "AWS - External Key Store (XKS)",
          },
          {
            type: 'docsVersionDropdown',
            docsPluginId: "aws-byok",
            dropdownActiveClassDisabled: false,
            position: 'right',
            x_dropdownlabel_integrations: "AWS - Bring Your Own Key",
          },
          {
            type: 'docsVersionDropdown',
            docsPluginId: "microsoft-byok",
            dropdownActiveClassDisabled: false,
            position: 'right',
            x_dropdownlabel_integrations: "Azure - Bring Your Own Key",
          },
          {
            type: 'docsVersionDropdown',
            docsPluginId: "cyberark",
            dropdownActiveClassDisabled: false,
            position: 'right',
            x_dropdownlabel_integrations: "CyberArk PAM",
          },
          {
            type: 'docsVersionDropdown',
            docsPluginId: "docker_signing",
            dropdownActiveClassDisabled: false,
            position: 'right',
            x_dropdownlabel_integrations: "Docker Signing",
          },
          {
            type: 'docsVersionDropdown',
            docsPluginId: "docker_encryption",
            dropdownActiveClassDisabled: false,
            position: 'right',
            x_dropdownlabel_integrations: "Docker Encryption",
          },
          {
            type: 'docsVersionDropdown',
            docsPluginId: "fortigate",
            dropdownActiveClassDisabled: false,
            position: 'right',
            x_dropdownlabel_integrations: "Fortinet FortiGate",
          },
          {
            type: 'docsVersionDropdown',
            docsPluginId: "hc-vault-enterprise",
            dropdownActiveClassDisabled: false,
            position: 'right',
            x_dropdownlabel_integrations: "HashiCorp Vault Enterprise",
          },
          {
            type: 'docsVersionDropdown',
            docsPluginId: "hc_vault",
            dropdownActiveClassDisabled: false,
            position: 'right',
            x_dropdownlabel_integrations: "HashiCorp Vault Community",
          },
          {
            type: 'docsVersionDropdown',
            docsPluginId: "hc_sse",
            dropdownActiveClassDisabled: false,
            position: 'right',
            x_dropdownlabel_integrations: "HashiCorp Vault Secrets Engine",
          },
          {
            type: 'docsVersionDropdown',
            docsPluginId: "primekey-ejbca",
            dropdownActiveClassDisabled: false,
            position: 'right',
            x_dropdownlabel_integrations: "KeyFactor/PrimeKey EJBCA",
          },
          {
            type: 'docsVersionDropdown',
            docsPluginId: "ms-pki-adcs",
            dropdownActiveClassDisabled: false,
            position: 'right',
            x_dropdownlabel_integrations: "Microsoft AD CS",
          },
          {
            type: 'docsVersionDropdown',
            docsPluginId: "ms-iis",
            dropdownActiveClassDisabled: false,
            position: 'right',
            x_dropdownlabel_integrations: "Microsoft IIS",
          },
          {
            type: 'docsVersionDropdown',
            docsPluginId: "ms-signtool",
            dropdownActiveClassDisabled: false,
            position: 'right',
            x_dropdownlabel_integrations: "Microsoft SignTool",
          },
          {
            type: 'docsVersionDropdown',
            docsPluginId: "ms-sql-ae",
            dropdownActiveClassDisabled: false,
            position: 'right',
            x_dropdownlabel_integrations: "Microsoft SQL Always Encrypted",
          },
          {
            type: 'docsVersionDropdown',
            docsPluginId: "ms-smime",
            dropdownActiveClassDisabled: false,
            position: 'right',
            x_dropdownlabel_integrations: "Microsoft SMIME",
          },
          {
            type: 'docsVersionDropdown',
            docsPluginId: "ms-hgs",
            dropdownActiveClassDisabled: false,
            position: 'right',
            x_dropdownlabel_integrations: "Microsoft Host Guardian Service",
          },        
          {
            type: 'docsVersionDropdown',
            docsPluginId: "salesforce-byok",
            dropdownActiveClassDisabled: false,
            position: 'right',
            x_dropdownlabel_integrations: "Salesforce - Bring Your Own Key",
          },
          {
            type: 'docsVersionDropdown',
            docsPluginId: "securden-pam",
            dropdownActiveClassDisabled: false,
            position: 'right',
            x_dropdownlabel_integrations: "Securden Unified PAM",
          },
          {
            type: 'docsVersionDropdown',
            docsPluginId: "sixscape",
            dropdownActiveClassDisabled: false,
            position: 'right',
            x_dropdownlabel_integrations: "Sixscape - IDcentral Key Management",
          },
          {
            type: 'docsVersionDropdown',
            docsPluginId: "venafi",
            dropdownActiveClassDisabled: false,
            position: 'right',
            x_dropdownlabel_integrations: "Venafi - Machine Identity Management",
          },
          {
            type: 'docsVersionDropdown',
            docsPluginId: "versasec-cms",
            dropdownActiveClassDisabled: false,
            position: 'right',
            x_dropdownlabel_integrations: "Versasec Credential Management System",
          },
          {
            type: 'docsVersionDropdown',
            docsPluginId: "white-rabbit",
            dropdownActiveClassDisabled: false,
            position: 'right',
            x_dropdownlabel_integrations: "White Rabbit OpenXPKI",
          },
          // =============================================================================== END
          {
            type: 'dropdown',
            label: 'Support',
            position: 'right',
            items: [
              {
                label: 'Support Guidelines',
                href: 'https://www.securosys.com/en/support',
              },
              {
                label: 'Create ticket',
                href: 'https://support.securosys.com/external',
              },
              {
                label: 'System Status',
                href: 'https://status.cloudshsm.com/',
              },
              // Add a horizontal bar
              {
                type: 'html',
                value: '<hr style="background-color: #000000; height: 1px; border: none; margin: 0 auto;" />',
              },
              {
                label: 'GitHub',
                href: 'https://github.com/securosys-com',
              },
              {
                label: 'HSM Trainings',
                href: 'https://www.securosys.com/en/training',
              },
            ],
          },
        ],
      },
      footer: {
        style: 'dark',
        logo: {
          alt: 'Securosys SA Logo',
          src: 'img/Logo - Subbrand - Docs - White.svg'
        },
        links: [
          {
            title: 'Products',
            items: [
              {
                label: 'PrimusHSM (On-prem)',
                to: 'https://www.securosys.com/en/hsm/what-is-an-hsm',
              },
              {
                label: 'CloudHSM',
                to: 'cloudhsm/overview',
              },
              {
                label: 'Securosys 365 - DKE',
                to: 'https://www.securosys.com/en/solution/dke-microsoft365',
              },
              {
                label: 'Smart Key Attributes (SKA)',
                to: 'tsb/Tutorials/TransactionSecurityBroker/smart-key-attributes',
              },
              {
                label: 'Key Attestation',
                to: 'https://www.securosys.com/en/key-attestation-by-securosys',
              },
              {
                label: 'Authorization Mobile App',
                to: 'AuthorizationApp/overview',
              },
            ],
          },
          {
            title: 'API Reference',
            items: [
              {
                label: 'Rest-API',
                to: 'tsb/overview',
              },
              {
                label: 'PKCS#11',
                to: 'pkcs/overview',
              },
              {
                label: 'JCE / JCA',
                to: 'jce/overview',
              },
              {
                label: 'Microsoft CNG',
                to: 'mscng/overview',
              },
              {
                label: 'OpenSSL',
                to: 'openssl/overview',
              },
              {
                label: 'Securosys Primus Tools',
                to: 'primus-tools/overview',
              },
            ],
          },
          {
            title: 'Integrations',
            items: [
              {
                label: 'View all (30+)',
                to: 'integrations',
              },
              {
                label: 'Microsoft AD CS',
                to: 'ms-pki-adcs/overview',
              },
              {
                label: 'Docker Image Signing',
                to: 'docker_signing/overview',
              },
              {
                label: 'HashiCorp Vault',
                to: 'hc_sse/overview',
              },
              {
                label: 'CyberArk PAM',
                to: 'cyberark/overview',
              },
              {
                label: 'Fortigate Fortinet',
                to: 'fortigate/overview',
              },
            ],
          },
          {
            title: 'Company',
            items: [
              {
                label: 'Who are we?',
                to: 'https://www.securosys.com/en/about',
              },
              {
                label: 'Partners',
                to: 'https://www.securosys.com/securosys-partner-directory',
              },
              {
                label: 'Events',
                to: 'https://www.securosys.com/en/events',
              },
              {
                label: 'Contact Us',
                to: 'https://www.securosys.com/en/contact',
              },
              {
                label: 'Support',
                to: 'https://support.securosys.com/external',
              },
              {
                html: '<Icon icon="fa-brands fa-github" size="sm" />',
              },
              {
                label: 'GitHub',
                to: 'https://github.com/securosys-com',
              },              
            ],
          },
          {
            title: 'CloudHSM',
            items: [
              {
                label: 'What is CloudHSM?',
                to: 'cloudhsm/overview',
              },
              {
                label: 'Getting started',
                to: 'cloudhsm/GettingStarted/activation_process',
              },
              {
                label: 'HSM Connectivity',
                to: 'connectivity-details/overview',
              },
              {
                label: 'Compliance',
                to: 'cloudhsm/Overview/compliance',
              },
              {
                label: '90-day free trial',
                to: 'https://cloud.securosys.com/cloudhsm/',
              },
              {
                label: 'Status Page',
                to: 'https://status.cloudshsm.com/'
              },
            ],
          },
        ],
        copyright: `Copyright © ${new Date().getFullYear()} Securosys SA • Built with Docusaurus • <a style='display: contents!important; color: white;' href="https://docs-dev.securosys.com/cloudhsm/category/terms-and-conditions">Terms</a> • <a target="_blank" style='display: contents!important; color: white;' href="https://www.securosys.com/en/about/privacy-policy-by-securosys">Privacy</a> `,
      },
      prism: {
        theme: lightTheme,
        darkTheme: darkTheme,
        additionalLanguages: ['bash', 'java', 'powershell'],
        magicComments: [
          // Remember to extend the default highlight class name as well!
          {
            className: 'theme-code-block-highlighted-line',
            line: 'highlight-next-line',
          block: {start: 'highlight-start', end: 'highlight-end'},
          },
          {
            className: "theme-code-block-highlighted-custom theme-code-block-note",
            line: "highlight-next-line-note",
            block: {
              start: "highlight-note-start",
              end: "highlight-note-end",
            },
          },
          {
            className: "theme-code-block-highlighted-custom theme-code-block-tip",
            line: "highlight-next-line-tip",
            block: {
              start: "highlight-tip-start",
              end: "highlight-tip-end",
            },
          },
          {
            className: "theme-code-block-highlighted-custom theme-code-block-info",
            line: "highlight-next-line-info",
            block: {
              start: "highlight-info-start",
              end: "highlight-info-end",
            },
          },
          {
            className: "theme-code-block-highlighted-custom theme-code-block-warning",
            line: "highlight-next-line-warning",
            block: {
              start: "highlight-warning-start",
              end: "highlight-warning-end",
            },
          },
          {
            className: "theme-code-block-highlighted-custom theme-code-block-danger",
            line: "highlight-next-line-danger",
            block: {
              start: "highlight-danger-start",
              end: "highlight-danger-end",
            },
          },
        ]
      },
      colorMode: {
        defaultMode: 'light',
      },
      tableOfContents: {
        minHeadingLevel: 2,
        maxHeadingLevel: 5,
      },
    }),
};

module.exports = config;
