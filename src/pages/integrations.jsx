import React from 'react';
import { useEffect } from 'react';
import GridFilter from '@site/src/components/IntegrationComponent/gridFilter.tsx';
import Layout from '@theme/Layout';
import useDocusaurusContext from '@docusaurus/useDocusaurusContext';
import Head from '@docusaurus/Head';
import clsx from 'clsx';

const TITLE = 'List of HSM Integrations for Cloud and On-Prem';
const DESCRIPTION = 'Browse the full list of integrations, explore filters, APIs, and hyperscalers for Securosys Hardware Security Modules. Find your ideal solution today.';
const KEYWORDS = ['HSM Integrations', 'Securosys Integrations', 'CloudHSM Integrations']

export default function FAQPage() {
    useEffect(() => {
        if (typeof window === 'undefined') return;
    
        const faqId = window.location.hash.substring(1);
    
        if (faqId !== '') {
          setActiveFAQ(faqId);
          document.querySelector('#parent-' + faqId)?.scrollIntoView({
            behavior: 'smooth',
            block: 'center',
            inline: 'center',
          });
        }
      }, []);

      const { siteConfig } = useDocusaurusContext();
      return (
        <Layout description={siteConfig.tagline}>
          <Head>
            <title>{TITLE}</title>
            <meta name="description" content={DESCRIPTION}/>
            <meta name="keywords" content={KEYWORDS.join(', ')}/>
            <link rel="canonical" href={siteConfig.url + '/integrations'}/>
            <meta property="og:title" content={DESCRIPTION}/>
            <meta property="og:description" content={DESCRIPTION}/>
            <meta property="og:url" href={siteConfig.url + '/integrations'}/>
            <meta name="twitter:title" content={TITLE}/>
            <meta name="twitter:description" content={DESCRIPTION}/>
            <meta name="robots" content="index, follow"/>
          </Head>
          <div className='homepage'>
            <div className={clsx('homepage__section')}>
              <div className='homepage__container'>
              <section class="margin-top--lg margin-bottom--lg text--center">
                <h1>{TITLE}</h1>
                <p>{DESCRIPTION}</p>
              </section>
                  <GridFilter />
              </div>
            </div>
          </div>
        </Layout>
      );
}

