import React, { FC }from 'react';
import clsx from 'clsx';
import useDocusaurusContext from '@docusaurus/useDocusaurusContext';
import Layout from '@theme/Layout';
import Link from '@docusaurus/Link';
import './styles.css';
import IntroSection from '@site/src/components/IntroComponent/intro.tsx';
import FeaturedSection from '@site/src/components/FeaturedComponent/featured.tsx';
import GridFilter from '@site/src/components/IntegrationComponent/gridFilter.tsx';
import SDKDocumentation from '@site/src/components/SdkComponent/sdk.tsx';

interface HomepageSectionProps {
  header?: string;
  description?: string;
  className?: string;
}

const HomepageSection: FC<HomepageSectionProps> = (props) => {
  const toKebabCase = (header) =>
    header &&
    header
      .match(
        /[A-Z]{2,}(?=[A-Z][a-z]+[0-9]*|\b)|[A-Z]?[a-z]+[0-9]*|[A-Z]|[0-9]+/g,
      )
      .map((parts) => parts.toLowerCase())
      .join('-');

  return (
    <div className={clsx('homepage__section', props.className)}>
      <div className='homepage__container'>
        {props.header && (
          <h2 className='homepage__header' id={toKebabCase(props.header)}>
            {props.header}
          </h2>
        )}
        {props.description && (
          <p className='homepage__description'>{props.description}</p>
        )}
        {props.children}
      </div>
    </div>
  );
};

export default function HomepageFeatures() {  
  const { siteConfig } = useDocusaurusContext();
  return (
    <Layout description={siteConfig.tagline}>
      <div className='homepage'>
          <HomepageSection className='homepage__section--intro'>
            <IntroSection />
          </HomepageSection>        

          <HomepageSection>
            <FeaturedSection />
          </HomepageSection>
          {/*
          <HomepageSection>
            <SDKDocumentation />
          </HomepageSection>
          */}

          {/*
          <HomepageSection>
            <GridFilter />
          </HomepageSection>
          */}
        </div>
    </Layout>
  );
}
