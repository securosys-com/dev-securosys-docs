import React, { useEffect, useState } from 'react';
import './gridFilter.css';

function GridFilter() {
  const [filter, setFilter] = useState('');
  const [productList, setProductList] = useState([]);
  const [selectedTags, setSelectedTags] = useState([]);
  const [sortOption, setSortOption] = useState('relevance');
  const [filterMode, setFilterMode] = useState('OR'); // Default filter mode is OR

  useEffect(() => {
    // Fetch product list data
    fetch('/pluginData.json')
      .then((response) => response.json())
      .then((data) => setProductList(data))
      .catch((error) => console.error('Error loading plugin data:', error));
  }, []);

  // Generate tag counts for sorting and display
  const tagCounts = productList.reduce((acc, product) => {
    (product.grid_search_tags || []).forEach((tag) => {
      acc[tag] = (acc[tag] || 0) + 1;
    });
    return acc;
  }, {});

  // Generate unique tags list
  const uniqueTagsList = Array.from(
    new Map(
      Object.entries(tagCounts)
        .sort(([, countA], [, countB]) => countB - countA)
        .map(([tag, count]) => [tag, count])
    )
  ).map(([tag]) => tag);

  // Hash function to generate a consistent hash for a given string
  const hashStringToColor = (string) => {
    let hash = 0;
    for (let i = 0; i < string.length; i++) {
      hash = string.charCodeAt(i) + ((hash << 5) - hash); // Create a simple hash
      hash = hash & hash; // Convert to 32-bit integer
    }
    const color = `#${((hash >> 24) & 0xff).toString(16).padStart(2, '0')}${((hash >> 16) & 0xff)
      .toString(16)
      .padStart(2, '0')}${((hash >> 8) & 0xff).toString(16).padStart(2, '0')}`;
    return color;
  };

  const getColorForTag = (tag) => {
    if (!tag) return '#ccc';
    return hashStringToColor(tag.toLowerCase());
  };

  // Process and filter products
  const filteredProducts = productList
    .map((product) => {
      const keywords = Array.isArray(product.keywords)
        ? product.keywords.join(' ')
        : product.keywords || '';
      const gridTags = product.grid_search_tags || [];

      // Matching logic
      const matchingTagsCount = selectedTags.filter((tag) =>
        gridTags.includes(tag)
      ).length;
      const tagsMatch = gridTags.some((tag) =>
        tag.includes(filter)
      );
      const keywordsMatch = keywords
        .toLowerCase()
        .includes(filter.toLowerCase());

      // Match conditions based on filter mode
      const matchesTags =
        filterMode === 'AND'
          ? matchingTagsCount === selectedTags.length // All selected tags must match
          : selectedTags.length === 0 || matchingTagsCount > 0; // Any tag can match for OR

      const relevanceScore =
        matchingTagsCount + (tagsMatch ? 1 : 0) + (keywordsMatch ? 1 : 0);

      return {
        ...product,
        matches: matchesTags && (tagsMatch || keywordsMatch),
        relevanceScore,
      };
    })
    .filter((product) => product.matches);

  // Apply sorting based on user selection
  const sortedProducts = filteredProducts.sort((a, b) => {
    if (sortOption === 'alphabetical') {      
      return a.title.localeCompare(b.title);      
    }
    return b.relevanceScore - a.relevanceScore; // Default is relevance
  });

  const toggleTag = (tag) => {
    setSelectedTags((prev) =>
      prev.includes(tag) ? prev.filter((t) => t !== tag) : [...prev, tag]
    );
  };

  const resetFilters = () => {
    setFilter('');
    setSelectedTags([]);
    setSortOption('relevance');
    setFilterMode('OR');
  };

  return (
    <div id="integrations" className="integrations">
      {/* Header Section */}
      <div className="grid-controls">
        <div className="grid-left-aligned">
          <h1>Integrations</h1>
          <span className="grid-site-count">
            {filteredProducts.length === 1
              ? `${filteredProducts.length} result`
              : `${filteredProducts.length} results`}
          </span>
        </div>
        <div className="grid-right-aligned">

          <button className="reset-button" onClick={resetFilters}>
            Clear All
          </button>

          <select
            value={sortOption}
            onChange={(e) => setSortOption(e.target.value)}
            className="sort-dropdown"
          >
            <option value="relevance">Sort by relevance</option>
            <option value="alphabetical">Sort alphabetically</option>
          </select>

          {/* Filter Mode Toggle */}
          <div className="filter-mode-toggle">
            <label className="toggle-switch">
              <input
                type="checkbox"
                checked={filterMode === 'AND'}
                onChange={(e) => setFilterMode(e.target.checked ? 'AND' : 'OR')}
              />
              <span className="slider"></span>
            </label>
            <span className="filter-label">
              {filterMode === 'AND' ? 'Match All (AND)' : 'Match Any (OR)'}
            </span>
          </div>
        </div>
      </div>

      {/* Tag Filters */}
      <div className="tags-container">
        {uniqueTagsList.map((tag, tagIndex) => (
          <div
            className={`tag-item ${
              selectedTags.includes(tag) ? 'selected' : ''
            }`}
            key={`${tag}-${tagIndex}`}
            onClick={() => toggleTag(tag)}
          >
            <div
              className="tag-dot"
              style={{ backgroundColor: getColorForTag(tag) }}
            ></div>
            {tag}            
          </div>
        ))}
        <input
          type="text"
          className="tag-item-search"
          placeholder="Search for integration ..."
          value={filter}
          onChange={(e) => setFilter(e.target.value)}
        />
      </div>

      {/* Product Grid */}
      <div className="grid__product">
        {sortedProducts.map((product) => (
          <div className="tile" key={product.title}>            
            <a href={product.url}>
              <div className="tile-logo">
                <img
                  src={product.grid_tile_logoUrl} // Assuming the product object has a `logoUrl` property
                  alt={product.title} // Use the product title as alt text for accessibility
                />
              </div>
            </a>
            <div className="tile-header">
              <a href={product.url} className="tile-title">{product.title}</a>
              <span className="tile-description">{product.grid_description}</span>
            </div>
            <div className="tile-footer">
              <div className="grid-categories">
                {product.grid_categories.map((category, index) => {
                  const matchingTag =
                    uniqueTagsList &&
                    Array.isArray(uniqueTagsList)
                      ? uniqueTagsList.find(
                          (uniqueTag) =>
                            uniqueTag === category
                        )
                      : null;
                  return (
                    <div className="grid__product_tag-category" key={`${product.id}-${index}`}>
                      <div
                        className="tag-dot"
                        style={{
                          backgroundColor: getColorForTag(matchingTag),
                        }}
                      ></div>
                      {category}
                    </div>
                  );
                })}
              </div>
            </div>
          </div>
        ))}
      </div>

    </div>
  );
}

export default GridFilter;
