# Mobile Automation Project

## Overview
This project focuses on automating mobile application testing for Android using [Appium](https://appium.io/) (or any other tool you're using). Before the Android tests are executed, Cypress tests are run to ensure that the web application is functioning as expected.
The implementation details for this orchestration are included in the test orchestration file. This setup enables comprehensive testing of both mobile and web applications through automated scripts, improving efficiency and coverage in the QA process.

## Prerequisites
Before you begin, ensure you have the following installed:

1. **Java** (or any language you’re using, e.g., JavaScript, Python, etc.)
2. **Appium** 
3. **Android Studio** (for Android automation)
4. **Xcode** (for iOS automation)
5. **Node.js** (for Appium server)
6. **Mobile Devices/Emulators** (Android/iOS)

### Additional Requirements
- Install all required dependencies and packages listed in `package.json` or `pom.xml` (if using Maven).
- Configure your environment variables for Android SDK (for Android) or Xcode (for iOS).

## Project Setup

1. Clone the repository:

   ```bash
   git clone https://github.com/LetsCodeWithIbrahim/MobileAutomation.git


**Install dependencies:**

npm install          # For JavaScript/Node.js projects
mvn clean install    # For Maven-based projects

**Configure device capabilities:**

Update the desired capabilities in the Base Class for your target device:

{
  "platformName": "Android",
  "platformVersion": "11.0",
  "deviceName": "Pixel_3a_API_30",
  "appPackage": "com.example.myapp",
  "appActivity": "com.example.myapp.MainActivity"
}

**Project Structure**

mobile-automation/CudaAT/
│
├── src/                    
│   ├── test/              # Test code and resources
│   │   ├── java/          # Java source files
│   │   │   ├── tests/     # Test scripts
│   │   │   ├── pages/     # Page Object Model files
│   │   │   └── fixtures/  # Test fixtures
│   │   └── resources/     # Resource files
│   │       └── json/      # JSON files used in tests
├── pom.xml                 # Dependency management (if using Maven)
├── README.md               # Project documentation
└── .gitignore              # Ignored files for version control
