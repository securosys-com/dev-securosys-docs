import React from "react";
import { Link } from "react-router-dom"; // Assuming you're using React Router
import "../../components/HomepageFeatures/styles.css";

const IntroSection = () => {
  return (
    <div>
      {/* Polygon SVG Background */}
      <div className="intro__box_polygon1">
        <svg
          width="875"
          height="889"
          viewBox="0 0 875 889"
          fill="none"
          xmlns="http://www.w3.org/2000/svg"
        >
          <path
            d="M875.009 231.328L874.912 1143.11L0.958556 -109.732L847.009 231.328Z"
            fill="url(#paint0_linear_9_127)"
          />
          <defs>
            <linearGradient
              id="paint0_linear_9_127"
              x1="815"
              y1="889.5"
              x2="473.5"
              y2="284.5"
              gradientUnits="userSpaceOnUse"
            >
              <stop stopColor="#D3101B" />
              <stop offset="0.613709" stopColor="#A81B23" />
              <stop offset="1" stopColor="#8D0E15" />
            </linearGradient>
          </defs>
        </svg>
      </div>

      {/* Main Content */}
      <div className="intro">
        <div className="intro__box"></div>
        <div className="intro__section">
          <h1 className="intro__title_custom">
            <p>Built Security with Securosys HSM</p>
          </h1>
          <div className="intro__explore_custom">
            <p>
              Fortify Your Data, Transactions, and Trust with Securosys HSMs.
              Achieve unparalleled security and performance with Securosys
              Hardware Security Modules (HSMs). Designed to meet the highest
              standards of protection, our HSMs safeguard your sensitive data,
              secure transactions, and uphold your organization’s reputation.
              From robust encryption to seamless key management, trust Securosys
              to deliver state-of-the-art security solutions that empower your
              business.
            </p>
          </div>

          {/* Resource Library */}
          <div className="resource_library_custom">
            {/* First Card */}
            <Link
              to="/tsb/Tutorials/TransactionSecurityBroker/transaction-security-broker"
              className="intro__api_card_custom"
            >
              <div className="intro__api_box_custom">
                <div className="overlay_custom">
                  <ReusableSVG />
                </div>
                <p className="title_custom">
                  <img src="/img/Securosys_Logo weiss.svg" alt="Logo" />
                  Multi Authorization
                </p>
                <p className="description_custom">
                  Enforced securely within the tamper-proof HSM environment
                  using Smart Key Attributes (SKA) for robust access control.
                </p>
              </div>
              <span className="cta_custom">Learn more &rarr;</span>
            </Link>

            {/* Second Card */}
            <Link
              to="/cloudhsm/cloudconsole"
              className="intro__api_card_custom"
            >
              <div className="intro__api_box_custom">
                <div className="overlay_custom">
                  <ReusableSVG />
                </div>
                <p className="title_custom">
                  <img src="/img/Securosys_Logo weiss.svg" alt="Logo" />
                  Cloud Console
                </p>
                <p className="description_custom">
                  The fastest way to get up and running with Securosys CloudHSM
                  services.
                </p>
              </div>
              <span className="cta_custom">Getting started &rarr;</span>
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
};

// Reusable SVG Component
const ReusableSVG = () => (
  <svg
    width="406"
    height="266"
    viewBox="0 0 406 266"
    fill="none"
    xmlns="http://www.w3.org/2000/svg"
  >
    <rect
      x="307.707"
      y="-265"
      width="214.271"
      height="648.491"
      transform="rotate(57.2991 307.707 -265)"
      fill="url(#paint0_linear_73_2)"
      fillOpacity="0.3"
    />
    <rect
      x="627.925"
      y="15.8533"
      width="214.271"
      height="596.416"
      transform="rotate(110.498 627.925 15.8533)"
      fill="url(#paint1_linear_73_2)"
      fillOpacity="0.6"
    />
    <rect
      x="575.686"
      y="51.8533"
      width="214.271"
      height="596.416"
      transform="rotate(110.498 575.686 51.8533)"
      fill="url(#paint2_linear_73_2)"
      fillOpacity="0.3"
    />
    <defs>
      <linearGradient
        id="paint0_linear_73_2"
        x1="414.842"
        y1="-265"
        x2="414.842"
        y2="383.491"
        gradientUnits="userSpaceOnUse"
      >
        <stop stopColor="#265C6D" />
        <stop offset="1" stopColor="#265C6D" />
      </linearGradient>
      <linearGradient
        id="paint1_linear_73_2"
        x1="735.061"
        y1="15.8533"
        x2="735.061"
        y2="612.27"
        gradientUnits="userSpaceOnUse"
      >
        <stop stopColor="#00C2FF" />
        <stop offset="1" stopColor="#265C6D" />
      </linearGradient>
      <linearGradient
        id="paint2_linear_73_2"
        x1="682.822"
        y1="51.8533"
        x2="682.822"
        y2="648.27"
        gradientUnits="userSpaceOnUse"
      >
        <stop stopColor="#00C2FF" />
        <stop offset="1" stopColor="#265C6D" />
      </linearGradient>
    </defs>
  </svg>
);

export default IntroSection;
