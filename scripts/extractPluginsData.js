const fs = require('fs');
const path = require('path');
const matter = require('gray-matter');
const siteConfig = require('../docusaurus.config.js');

const docsPath = path.join(__dirname, '../.'); // Path to your markdown docs
const outputPath = path.join(__dirname, '../static/pluginData.json'); // Where to save JSON

function extractMarkdownData() {
  const plugins = siteConfig.plugins || [];
  const pluginsData = [];
  
  plugins.map((plugin) => {
        const [pluginName, pluginContent] = plugin;
        if (pluginName !== "@docusaurus/plugin-content-docs"){
            return null;
        }
        const pluginPath = pluginContent.routeBasePath;
        const overviewMd = path.join(docsPath, pluginPath, 'overview.md');
        if (!fs.existsSync(overviewMd)) {
            return null;
        }
        const overviewContent = fs.readFileSync(overviewMd, 'utf-8');
        const { data } = matter(overviewContent);
        if (data.grid_enabled || data.grid_enabled === undefined) {
            const pluginData = {
            title: data.grid_title || 'Unknown',
            sidebarLabel: data.sidebar_label || 'N/A',
            description: data.description || 'No description available',
            keywords: data.keywords || [],
            sidebarPosition: data.sidebar_position || 0,
            grid_search_tags: data.grid_search_tags || [],
            grid_description: data.grid_description || 'No description available',
            grid_categories: data.grid_categories || [],
            grid_tile_logoUrl: data.grid_tile_logoUrl || '',        
            url: pluginPath + '/overview',
            fileName: 'overview.md',            
            };
            pluginsData.push(pluginData);
        }        
    }
    );
    return fs.writeFileSync(outputPath, JSON.stringify(pluginsData, null, 2));
}

extractMarkdownData();
console.log('Markdown data has been extracted to JSON');
