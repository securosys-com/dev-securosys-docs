import React from 'react';
// Import the original mapper
import MDXComponents from '@theme-original/MDXComponents';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'; // Import the FontAwesomeIcon component.
import { library } from '@fortawesome/fontawesome-svg-core'; // Import the library component.
import { fab } from '@fortawesome/free-brands-svg-icons'; // Import all brands icons.
import { fas } from '@fortawesome/free-solid-svg-icons'; // Import all solid icons.
import Button from '../components/Button';
import Columns from '@site/src/components/Columns';
import Column from '@site/src/components/Column';

library.add(fab, fas); // Add all icons to the library so you can use them without importing them individually.

class IconNavNext extends React.Component {
  render() {
    return (
      <FontAwesomeIcon icon="fa-arrow-right" size="sm" />
    );
  }
}

export default {
  // Re-use the default mapping
  ...MDXComponents,
  Icon: FontAwesomeIcon, // Make the FontAwesomeIcon component available in MDX as <icon />.
  IconNavNext,
  Button,
  Columns,
  Column,
};
