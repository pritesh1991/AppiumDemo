# Appium Mobile Test Automation Framework

[![Java](https://img.shields.io/badge/Java-17+-blue.svg)](https://www.oracle.com/java/)
[![Appium](https://img.shields.io/badge/Appium-9.2.2-purple.svg)](https://appium.io/)
[![TestNG](https://img.shields.io/badge/TestNG-7.9.0-red.svg)](https://testng.org/)
[![Gradle](https://img.shields.io/badge/Gradle-8.2-green.svg)](https://gradle.org/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

A robust and scalable mobile test automation framework built with **Appium**, **Java**, and **TestNG** for testing Android and iOS mobile applications. This framework implements the **Page Object Model (POM)** design pattern and supports parallel test execution.

## 📋 Table of Contents

- [Features](#features)
- [Architecture](#architecture)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Project Structure](#project-structure)
- [Configuration](#configuration)
- [Usage](#usage)
- [Running Tests](#running-tests)
- [Best Practices](#best-practices)
- [Contributing](#contributing)
- [License](#license)
- [Support](#support)

## ✨ Features

- 🚀 **Cross-Platform Support**: Test both Android and iOS applications
- 🏗️ **Page Object Model**: Clean and maintainable test code structure
- 🔄 **Thread-Safe Execution**: Support for parallel test execution
- 🎯 **Appium Server Management**: Automatic Appium server start/stop
- 📱 **Multi-Device Support**: Run tests on emulators, simulators, and real devices
- 🧪 **TestNG Integration**: Powerful test framework with annotations and reporting
- 📊 **Test Reporting**: Built-in reporting capabilities with TestNG
- 🔧 **Flexible Configuration**: Easy platform and device configuration
- 🎨 **Clean Architecture**: Separation of concerns with page objects and test classes

## 🏛️ Architecture

This framework follows a layered architecture:

```
┌─────────────────────────────┐
│      Test Layer             │  ← FirstTest.java
├─────────────────────────────┤
│      Page Layer             │  ← WelcomePage.java
├─────────────────────────────┤
│   Page Objects Layer        │  ← WelcomePageObject.java
├─────────────────────────────┤
│     Base Test Layer         │  ← BaseTest.java
├─────────────────────────────┤
│   Driver Factory Layer      │  ← DriverFactory.java
└─────────────────────────────┘
```

### Design Patterns

- **Page Object Model (POM)**: Separates page elements from test logic
- **Factory Pattern**: Driver creation and management
- **ThreadLocal Pattern**: Thread-safe driver instances for parallel execution

## 📦 Prerequisites

Before you begin, ensure you have the following installed:

- **Java Development Kit (JDK)**: Version 17 or higher
  - [Download JDK](https://adoptium.net/)
  - Verify installation: `java -version`

- **Gradle**: Version 8.2 or higher (included via Gradle Wrapper)
  - Verify installation: `./gradlew --version`

- **Node.js and npm**: For Appium installation
  - [Download Node.js](https://nodejs.org/)
  - Verify installation: `node --version && npm --version`

- **Appium**: Version 2.x recommended
  ```bash
  npm install -g appium
  ```
  - Verify installation: `appium --version`

- **Android SDK** (for Android testing):
  - [Download Android Studio](https://developer.android.com/studio)
  - Set `ANDROID_HOME` environment variable
  - Install platform tools and emulator

- **Xcode** (for iOS testing - macOS only):
  - [Download from Mac App Store](https://apps.apple.com/app/xcode/id497799835)
  - Install Xcode Command Line Tools: `xcode-select --install`

- **Appium Drivers**:
  ```bash
  appium driver install uiautomator2  # For Android
  appium driver install xcuitest       # For iOS
  ```

## 🚀 Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/pritesh1991/AppiumDemo.git
   cd AppiumDemo
   ```

2. **Verify Gradle installation**:
   ```bash
   ./gradlew --version
   ```

3. **Download dependencies**:
   ```bash
   ./gradlew build --no-daemon -x test
   ```

4. **Set up your mobile app**:
   - Place your Android APK file in the `app/` directory
   - Place your iOS app file in the `app/` directory
   - Update the app paths in `DriverFactory.java` if needed

## 📁 Project Structure

```
AppiumDemo/
├── app/                          # Mobile application files
│   ├── gojek.apk                 # Sample Android app
│   ├── coffee-timer.app          # Sample iOS app
│   └── sample.apk
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── appium/
│   │       │   └── driver/
│   │       │       └── DriverFactory.java      # Driver initialization
│   │       ├── pages/
│   │       │   └── WelcomePage.java            # Page actions
│   │       ├── pageobjects/
│   │       │   └── WelcomePageObject.java      # Page elements
│   │       └── BaseTest.java                   # Base test setup
│   └── test/
│       └── java/
│           └── FirstTest.java                  # Test cases
├── build.gradle                  # Gradle build configuration
├── settings.gradle               # Gradle settings
├── gradlew                       # Gradle wrapper script (Unix)
├── gradlew.bat                   # Gradle wrapper script (Windows)
└── README.md                     # This file
```

## ⚙️ Configuration

### Platform Configuration

Edit `src/main/java/appium/driver/DriverFactory.java` to configure the platform:

```java
String platform = "android";  // Change to "ios" for iOS testing
```

### Android Configuration

Update the following in `DriverFactory.java`:

```java
options.setAutomationName("UiAutomator2")
       .setPlatformName("Android")
       .setApp(getAndroidPath())
       .setUdid("emulator-5554");  // Your device/emulator ID
```

**Find your device ID**:
```bash
adb devices
```

### iOS Configuration

Update the following in `DriverFactory.java`:

```java
options.setApp(getiOSPath())
       .setAutomationName("XCUITest")
       .setPlatformName("iOS")
       .setDeviceName("iPhone 14")  // Your device/simulator
       .setPlatformVersion("16.0");  // Your iOS version
```

**Find available simulators**:
```bash
xcrun simctl list devices
```

### Appium Server Configuration

The framework automatically starts Appium server on a free port. To use a custom Appium installation path, update:

```java
.withAppiumJS(new File("/path/to/appium"))
```

## 📖 Usage

### Writing Tests

1. **Create a Page Object** (define elements):
   ```java
   public class LoginPageObject {
       @AndroidFindBy(id = "username_field")
       public WebElement usernameField;
       
       @AndroidFindBy(id = "password_field")
       public WebElement passwordField;
   }
   ```

2. **Create a Page Class** (define actions):
   ```java
   public class LoginPage {
       LoginPageObject loginPageObject;
       
       public LoginPage(AppiumDriver driver) {
           loginPageObject = new LoginPageObject();
           PageFactory.initElements(new AppiumFieldDecorator(driver), 
                                   this.loginPageObject);
       }
       
       public void login(String username, String password) {
           loginPageObject.usernameField.sendKeys(username);
           loginPageObject.passwordField.sendKeys(password);
       }
   }
   ```

3. **Create a Test Class**:
   ```java
   public class LoginTest extends BaseTest {
       @Test
       public void testLogin() {
           LoginPage loginPage = new LoginPage(threadLocalDriver.get());
           loginPage.login("user@example.com", "password123");
       }
   }
   ```

## 🧪 Running Tests

### Run All Tests

```bash
./gradlew test
```

### Run Specific Test Class

```bash
./gradlew test --tests FirstTest
```

### Run with Custom JVM Arguments

```bash
./gradlew test -Dplatform=android -Ddevice=emulator-5554
```

### Build Without Tests

```bash
./gradlew build -x test
```

### Clean and Build

```bash
./gradlew clean build
```

## 📊 Test Reports

TestNG generates test reports after execution:

- **Location**: `build/reports/tests/test/index.html`
- **Open in browser**: Double-click the file or use:
  ```bash
  open build/reports/tests/test/index.html  # macOS
  xdg-open build/reports/tests/test/index.html  # Linux
  start build/reports/tests/test/index.html  # Windows
  ```

## 🎯 Best Practices

1. **Keep Tests Independent**: Each test should be able to run independently
2. **Use Meaningful Names**: Test and method names should clearly describe what they test
3. **Follow Page Object Model**: Keep element locators separate from test logic
4. **Avoid Hard Waits**: Use explicit waits instead of Thread.sleep()
5. **Clean Up Resources**: Always close drivers and stop Appium server
6. **Use Data Providers**: For data-driven testing with TestNG
7. **Handle Exceptions**: Implement proper error handling and logging
8. **Version Control**: Don't commit sensitive data or large binary files

## 🤝 Contributing

We welcome contributions! Please follow these steps:

1. **Fork the repository**
2. **Create a feature branch**:
   ```bash
   git checkout -b feature/your-feature-name
   ```
3. **Make your changes**
4. **Run tests** to ensure nothing breaks:
   ```bash
   ./gradlew test
   ```
5. **Commit your changes**:
   ```bash
   git commit -m "Add: your feature description"
   ```
6. **Push to your fork**:
   ```bash
   git push origin feature/your-feature-name
   ```
7. **Open a Pull Request**

### Coding Standards

- Follow Java naming conventions
- Add JavaDoc comments for public methods
- Write unit tests for new features
- Keep methods small and focused
- Use meaningful variable names

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🆘 Support

### Common Issues

**Issue**: "Appium server not found"
- **Solution**: Ensure Appium is installed globally: `npm install -g appium`
- Verify installation path and update `withAppiumJS()` in DriverFactory

**Issue**: "Device not found"
- **Solution**: Check device/emulator is running: `adb devices` (Android) or `xcrun simctl list` (iOS)

**Issue**: "App not found"
- **Solution**: Verify the app path in `getAndroidPath()` or `getiOSPath()` methods

**Issue**: "Build fails with dependency errors"
- **Solution**: Clear Gradle cache: `./gradlew clean build --refresh-dependencies`

### Getting Help

- 📧 **Email**: [Contact Repository Owner](https://github.com/pritesh1991)
- 🐛 **Issues**: [GitHub Issues](https://github.com/pritesh1991/AppiumDemo/issues)
- 💬 **Discussions**: [GitHub Discussions](https://github.com/pritesh1991/AppiumDemo/discussions)

### Useful Resources

- [Appium Documentation](https://appium.io/docs/en/latest/)
- [TestNG Documentation](https://testng.org/doc/documentation-main.html)
- [Selenium Documentation](https://www.selenium.dev/documentation/)
- [Gradle User Guide](https://docs.gradle.org/current/userguide/userguide.html)
- [Page Object Model Best Practices](https://www.selenium.dev/documentation/test_practices/encouraged/page_object_models/)

## 🌟 Keywords

Mobile Testing, Appium, Java, TestNG, Android Testing, iOS Testing, Test Automation, Page Object Model, Gradle, Selenium, Mobile App Testing, UI Testing, Automated Testing, Test Framework, Cross-Platform Testing, CI/CD, Quality Assurance, Software Testing

---

**Made with ❤️ for the mobile testing community**

*Star ⭐ this repository if you find it helpful!*
