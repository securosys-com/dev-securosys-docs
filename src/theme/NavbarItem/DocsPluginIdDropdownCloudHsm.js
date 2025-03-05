import React from 'react';
import DropdownNavbarItem from '@theme/NavbarItem/DropdownNavbarItem'
import { useThemeConfig } from '@docusaurus/theme-common'
import { useAllDocsData, useActiveDocContext } from '@docusaurus/plugin-content-docs/client';

export default function DocsPluginIdDropdownNavbarItem(props) {
  let dropdownProps = {...props};

  const allDocsData = useAllDocsData();

  const docsVersions = Object.fromEntries(
    useThemeConfig().navbar.items.filter(
      (item) => (item.type === 'docsVersionDropdown') && (item.x_dropdownlabel_cloudhsm !== undefined)
      ).map(item => [item.docsPluginId, item]));

  const docsData = Object.fromEntries(
    Object.keys(docsVersions).flatMap((k) => {
      let docsData =allDocsData[k];
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


  const path = activeContext?.activeVersion?.path.match(/^\/[^/]*/)[0];
  const activeDocsPluginId = (docsData[path]?.docsPluginId);

  dropdownProps.label = docsVersions[activeDocsPluginId]?.x_dropdownlabel_cloudhsm ?? dropdownProps.label;

  dropdownProps.items = Object.values(docsVersions).map(
    (item) => {
    return {
      "type":"docsVersion",
      "label": item.x_dropdownlabel_cloudhsm,
      "docsPluginId":item.docsPluginId,
      "activeBaseRegex": `^${docsData[item.docsPluginId].path}`
    }}
  );

  return (
    <>
      <DropdownNavbarItem {...dropdownProps} />
    </>
  );
}
