/**
 * Copyright (c) Facebook, Inc. and its affiliates.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

import React from 'react';
import Link from '@docusaurus/Link';
import {translate} from '@docusaurus/Translate';
import { useThemeConfig } from '@docusaurus/theme-common'
import { useAllDocsData, useActiveDocContext } from '@docusaurus/plugin-content-docs/client';
import IconHome from '@theme/Icon/Home';

import styles from './styles.module.css';

export default function HomeBreadcrumbItem(): JSX.Element {  
  const allDocsData = useAllDocsData();

  const docsVersions = Object.fromEntries(
    useThemeConfig().navbar.items.filter(
      (item) => (item.type === 'docsVersionDropdown')
      ).map(item => [item.docsPluginId, item]));

  const docsData = Object.fromEntries(
    Object.keys(docsVersions).flatMap((k) => {
      let docsData = allDocsData[k];      
      docsData.docsPluginId = k;      
      return [
        [docsData?.path, docsData],
        [k, docsData]
      ]
    })
  );

  const activeContexts = Object.keys(docsVersions)
    .map(item => useActiveDocContext(item))
    .filter(item => !!item?.activeVersion);    
  const activeContext = activeContexts[0];

  const homePath = activeContext?.activeVersion?.path.match(/^\/[^/]*/)[0];  
  const activeDocsPluginId = (docsData[homePath]?.docsPluginId);
  const label = docsVersions[activeDocsPluginId]?.x_dropdownlabel_cloudhsm ?? docsVersions[activeDocsPluginId]?.x_dropdownlabel_api_reference ?? docsVersions[activeDocsPluginId]?.x_dropdownlabel_integrations;  

  return (    
    <li className="breadcrumbs__item">
      <Link
        className="breadcrumbs__link"
        href={`${homePath}/overview`}
      >
        <IconHome className={styles.breadcrumbHomeIcon} />        
        {label}
      </Link>
    </li>
  );
}