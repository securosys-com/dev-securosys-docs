import React from "react";
import "./sdk.css";

const SDKDocumentation = () => {
  const uiKitFrameworks = [
    { name: "React", icon: "react-icon.png" },
    { name: "Angular", icon: "angular-icon.png" },
    { name: "HTML", icon: "html-icon.png" },
    { name: "Flutter", icon: "flutter-icon.png" },
    { name: "React Native", icon: "react-native-icon.png" },
    { name: "iOS", icon: "ios-icon.png" },
    { name: "Android", icon: "android-icon.png" },
  ];

  const coreSDKFrameworks = [
    { name: "JS", icon: "js-icon.png" },
    { name: "React", icon: "react-icon.png" },
    { name: "Flutter", icon: "flutter-icon.png" },
    { name: "React Native", icon: "react-native-icon.png" },
    { name: "iOS", icon: "ios-icon.png" },
    { name: "Android", icon: "android-icon.png" },
  ];

  return (
    <div className="sdk_documentation-section">
      <h4 className="sdk_section-heading">SDK Documentation</h4>
      <h1 className="sdk_main-title">
        Build the way you want in the framework you want!
      </h1>

      {/* UI Kit Section */}
      <div className="sdk_category">
        <h2 className="sdk_category-title">UI Kit</h2>
        <p className="sdk_category-description">
          Build faster with our prebuilt library of UI components, available for
          all frameworks.
        </p>
        <div className="sdk_framework-grid">
          {uiKitFrameworks.map((framework, index) => (
            <div className="sdk_framework-card" key={index}>
              <img src={framework.icon} alt={framework.name} />
              <span>{framework.name}</span>
            </div>
          ))}
        </div>
      </div>

      {/* Core SDK Section */}
      <div className="sdk_category">
        <h2 className="sdk_category-title">Core SDK</h2>
        <p className="sdk_category-description">
          Our easy-to-use Video + Voice + Livestreaming + Chat all in one SDK.
        </p>
        <div className="sdk_framework-grid">
          {coreSDKFrameworks.map((framework, index) => (
            <div className="sdk_framework-card" key={index}>
              <img src={framework.icon} alt={framework.name} />
              <span>{framework.name}</span>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};

export default SDKDocumentation;
