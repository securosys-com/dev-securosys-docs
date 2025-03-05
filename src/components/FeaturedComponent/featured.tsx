import React, { useState } from "react";
import "./featured.css";

const data = {
  guides: [
    {
      icon: "/img/company_logo/openssl.png",
      title: "OpenSSL with PrimusHSM",
      description: "Check out an example of how PrimusHSM can elaborate on OpenSSL.",
      link: '/openssl/osslv3/overview',
    },
    {
      icon: "/img/company_logo/vault.png",
      title: "Securosys' Secrets Engine",
      description: "PrimusHSM with HashiCorp Vault Community.",
      link: '/hc_sse/overview',
    },
    {
        icon: "/img/company_logo/ejbca.png",
        title: "PQC ready PKI with Keyfactor EJBCA",
        description: "Prepare yourself for the future with PostQuantumCryptography.",
        link: '/primekey-ejbca/overview',
    },
  ],
  apps: [
    {
      icon: "/img/company_logo/securosys.png",
      title: "Multi-Authorization App - iOS & Android",
      description: "Authorization App for Smart Key Attributes (SKA).",      
      link: '/AuthorizationApp/overview',
    },
    {
      icon: "/img/company_logo/connectivity.png",
      title: "HSM Connectivity Details",
      description: "Learn how to connect your API / Integration.",      
      link: '/connectivity-details/overview',
    },
    {
      icon: "/img/company_logo/tool.png",
      title: "Securosys Primus Tools",
      description: "Leightweight CLI-Tool for KeyManagement ensures optimal compatibility with HSM.",      
      link: '/primus-tools/overview',
    },
  ],
};

const HowToGuides = ({ guides, selectedGuide, setSelectedGuide }) => (
  <div className="featured_section">
    <div className="featured_section-header">
      <h3>Popular how-to guides</h3>
      <a href="/integrations" className="featured_view-more">
        View more guides &rarr;
      </a>
    </div>
    <ul className="featured_guides-list">
      {guides.map((guide, index) => (
        <a href={guide.link}>
        <li
          key={index}          
          className={`featured_guide-item ${
            selectedGuide === index ? "featured_selected" : ""
          }`}
          onClick={() => setSelectedGuide(index)}
        >          
          <span className="featured_icon">
          <img
            src={guide.icon} // Assuming the product object has a `logoUrl` property
            alt={guide.title} // Use the product title as alt text for accessibility
          /> 
          </span>
          <div className="featured_guide-content">
            <h3>{guide.title}</h3>
            <p>{guide.description}</p>
          </div>
          <span className="featured_arrow">&rarr;</span>
        </li>
        </a>
      ))}
    </ul>
  </div>
);

const SampleApps = ({ apps, selectedApp, setSelectedApp }) => (
  <div className="featured_section">
    <div className="featured_section-header">
      <h3>Popular sample apps</h3>
      <a href="/integrations" className="featured_view-more">
        View all &rarr;
      </a>
    </div>
    <ul className="featured_guides-list">
      {apps.map((app, index) => (
        <a href={app.link}>
        <li
          key={index}
          className={`featured_guide-item ${
            selectedApp === index ? "featured_selected" : ""
          }`}
          onClick={() => setSelectedApp(index)}
        >
        <span className="featured_icon">
          <img
            src={app.icon} // Assuming the product object has a `logoUrl` property
            alt={app.title} // Use the product title as alt text for accessibility
          /> 
        </span>
          <div className="featured_guide-content">
            <h3>{app.title}</h3>
            <p>{app.description}</p>
          </div>
          <div className="featured_actions">            
          </div>
          <span className="featured_arrow">&rarr;</span>
        </li>
        </a>
      ))}
    </ul>
  </div>
);

const FeaturedSection = () => {
  const [selectedGuide, setSelectedGuide] = useState(null);
  const [selectedApp, setSelectedApp] = useState(null);

  return (    
    <div className="featured_section_container">
      <HowToGuides
        guides={data.guides}
        selectedGuide={selectedGuide}
        setSelectedGuide={setSelectedGuide}
      />      
      <div className="featured_divider"></div>
      <SampleApps
        apps={data.apps}
        selectedApp={selectedApp}
        setSelectedApp={setSelectedApp}
      />      
    </div>
    );
};

export default FeaturedSection;
