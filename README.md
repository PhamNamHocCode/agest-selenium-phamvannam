# Railway Ticket Booking - Selenium Test Automation

Automated testing framework for Railway Ticket Booking System using Selenium WebDriver, TestNG, and Page Object Model (POM) pattern. Fully configured for Maven and VS Code.

**Author:** Phạm Văn Nam

---

## Overview

| Item               | Details                                    |
| ------------------ | ------------------------------------------ |
| **AUT**            | SafeRailway (http://saferailway.somee.com) |
| **Framework**      | TestNG + Selenium 4                        |
| **Language**       | Java 21                                    |
| **Design Pattern** | Page Object Model (POM)                    |
| **Build Tool**     | Maven 3.13.0+                              |
| **IDE**            | VS Code with Extension Pack for Java       |

---

## Technology Stack

- **Selenium WebDriver 4.27.0** - Browser automation
- **TestNG 7.12.0** - Test execution framework
- **Log4j 2.24.3** - Logging
- **Jackson 2.21.0** - JSON data processing
- **WebDriverManager 5.9.2** - Browser driver management
- **Allure 2.33.0** - Test reporting

---

## Architecture

```
┌─────────────────────────────────────────────────────────┐
│                   TestCases Layer                        │
│  (LoginTest, BookTicket, CancelBooking, etc.)            │
└────────────────┬────────────────────────────────────────┘
                 │
┌────────────────▼────────────────────────────────────────┐
│                PageObjects Layer (POM)                   │
│  (LoginPage, BookTicketPage, HomePage, etc.)             │
└────────────────┬────────────────────────────────────────┘
                 │
┌────────────────▼────────────────────────────────────────┐
│                Utilities & Helpers                       │
│  (FluentWait, WebDriver Setup, Data Utils, Logger)       │
└────────────────┬────────────────────────────────────────┘
                 │
┌────────────────▼────────────────────────────────────────┐
│              Selenium WebDriver                          │
│         (Chrome, Firefox via WebDriverManager)           │
└─────────────────────────────────────────────────────────┘
```

---

## Project Structure

```
web-railway/
├── src/
│   ├── main/java/
│   │   ├── common/                 # Shared utilities & logging
│   │   │   ├── Utilities.java      # FluentWait, WebDriver setup
│   │   │   ├── Log4j.java          # Logging wrapper
│   │   │   └── JsonDataReader.java # JSON test data loader
│   │   │
│   │   ├── constant/               # Constants & enums
│   │   │   ├── Constant.java       # App constants
│   │   │   ├── PageMenu.java       # Menu enum
│   │   │   ├── FieldsLogin.java    # Login field mapping
│   │   │   ├── SeatType.java       # Seat type enum
│   │   │   ├── StationCity.java    # Station enum
│   │   │   └── TicketTableCol.java # Table column constants
│   │   │
│   │   ├── dataobjects/            # POJO models for test data
│   │   │   ├── DataObjectBase.java # Base POJO class
│   │   │   ├── BookTicketData.java # Booking data model
│   │   │   └── RegisterAccount.java# Account registration model
│   │   │
│   │   ├── helpers/                # Test helper utilities
│   │   │   ├── PreconditionHelper.java # Pre-test setup
│   │   │   └── TableHelper.java    # HTML table navigation
│   │   │
│   │   └── pageobjects/            # Page Object Model classes
│   │       ├── GeneralPage.java    # Base page class
│   │       ├── LoginPage.java      # Login page
│   │       ├── RegisterPage.java   # Registration page
│   │       ├── HomePage.java       # Main home page
│   │       ├── BookTicketPage.java # Ticket booking page
│   │       ├── MyTicketPage.java   # My tickets page
│   │       ├── TimetablePage.java  # Timetable page
│   │       ├── TicketPricePage.java# Pricing page
│   │       ├── FAQPage.java        # FAQ page
│   │       └── GuerrillaHomePage.java # Guerrilla page
│   │
│   ├── test/java/
│   │   ├── railway/                # Test cases
│   │   │   ├── TestBase.java       # Base test configuration
│   │   │   ├── LoginTest.java      # Login scenarios
│   │   │   ├── LogoutTest.java     # Logout scenarios
│   │   │   ├── CreateAccount.java  # Account creation
│   │   │   ├── ResetPassword.java  # Password reset
│   │   │   ├── BookTicket.java     # Ticket booking tests
│   │   │   └── CancelBooking.java  # Cancellation tests
│   │   │
│   │   └── common/                 # Test utilities
│   │       └── DataProviders.java  # TestNG data providers
│   │
│   └── test/resources/             # Test configuration & data
│       ├── testng.xml              # TestNG suite configuration
│       ├── log4j2.xml              # Log4j2 configuration
│       └── Railway/
│           └── BookTicket.json     # Test data (data-driven)
│
├── .vscode/                        # VS Code configuration
│   ├── settings.json               # Java/Maven settings
│   ├── launch.json                 # Debug configurations
│   └── tasks.json                  # Maven build tasks
│
├── pom.xml                         # Maven project configuration
└── README.md
```

---

## Test Coverage

| Test Class    | Scenarios                                  | Count             |
| ------------- | ------------------------------------------ | ----------------- |
| LoginTest     | Valid/invalid login, remember me           | 3                 |
| LogoutTest    | Logout & session cleanup                   | 1                 |
| CreateAccount | Registration, validation, duplicates       | 3                 |
| ResetPassword | Forgot password, recovery                  | 2                 |
| BookTicket    | Single/round-trip, seat selection, pricing | 8                 |
| CancelBooking | Cancellation, refund                       | 2                 |
| **Total**     |                                            | **19 test cases** |

---

## Getting Started

### Prerequisites

- **Java JDK 21+** (Eclipse Temurin recommended)
- **Maven 3.13.0+**
- **VS Code** with:
  - Extension Pack for Java
  - Maven for Java
- Git

### Installation

#### 1. Install Java 21

```bash
# Check if already installed
java -version

# Download from Eclipse Temurin (if not installed)
# https://adoptium.net/installation/
```

#### 2. Install Maven

```bash
# Check version
mvn --version

# On Windows (with Chocolatey)
choco install maven

# On macOS (with Homebrew)
brew install maven

# Or download from Maven website
# https://maven.apache.org/download.cgi
```

#### 3. Install VS Code Extensions

```
- Extension Pack for Java (Microsoft)
- Maven for Java (Microsoft)
- Optional: Allure Report Viewer, TestNG Runner
```

### Setup Project

```bash
# Clone repository
git clone <repository-url>
cd web-railway

# Install dependencies and compile
mvn clean install

# Verify setup
mvn --version
java --version
```

---

## Running Tests

### From VS Code (Recommended)

#### 1. **Open Project in VS Code**

```bash
code web-railway
```

#### 2. **Build Project** (Ctrl+Shift+B)

```
Select: Maven - web-railway (clean install)
```

#### 3. **Run All Tests**

- Open **Command Palette** (Ctrl+Shift+P)
- Type: `Run Tests` or `TestNG: Run All`

#### 4. **Run Specific Test Class**

- Right-click on test file → `Run Test`
- Or use **Test Explorer** view (left sidebar)

#### 5. **Debug Tests**

- Click on test class/method
- Press F5 or select `Debug Test` from context menu
- Debug output appears in VS Code console

#### 6. **Using VS Code Tasks**

- Open **Command Palette** (Ctrl+Shift+P)
- Type `Tasks: Run Task` and select from:
  - `Maven: clean`
  - `Maven: compile`
  - `Maven: test`
  - `Maven: package`

### From Terminal

#### All Tests

```bash
mvn clean test
```

#### Specific Test Class

```bash
mvn clean test -Dtest=LoginTest
mvn clean test -Dtest=BookTicket
mvn clean test -Dtest=CancelBooking
```

#### Specific Test Method

```bash
mvn clean test -Dtest=LoginTest#testLoginWithValidCredentials
mvn clean test -Dtest=BookTicket#testBookTicketSingleTrip
```

#### Run with Browser Parameter

```bash
# Chrome (default)
mvn clean test -Dbrowser=chrome

# Firefox
mvn clean test -Dbrowser=firefox
```

#### Skip Tests (Build Only)

```bash
mvn clean package -DskipTests
```

---

## Test Reports

### TestNG Report (Built-in)

- **Location:** `test-output/index.html`
- **Generates:** After each test run automatically
- **View:** Open in browser

### Allure Report (Enhanced)

```bash
# Generate Allure report
mvn allure:report

# Serve Allure report (opens browser)
mvn allure:serve
```

- Opens at: `http://localhost:4444`
- Press Ctrl+C to stop serving

### Logs

- **Location:** `logs/TestExecution.log`
- **Config:** `src/test/resources/log4j2.xml`
- **Level:** DEBUG, INFO, ERROR
- **Output:** Console + File

---

## Key Features

✅ **Maven Standard Structure** - Full Maven project layout with `src/main/java` and `src/test/java`

✅ **Page Object Model (POM)** - Maintainable and scalable page objects with `pageobjects` package

✅ **Explicit Waits (FluentWait)** - Reliable element synchronization via `common.Utilities`

✅ **Data-Driven Testing** - JSON-based test parameterization in `src/test/resources/Railway/BookTicket.json`

✅ **Structured Test Data** - Type-safe POJOs in `dataobjects` package (BookTicketData, RegisterAccount)

✅ **Constants & Enums** - All static values in `constant` package (PageMenu, SeatType, StationCity, FieldsLogin)

✅ **Helper Utilities** - Pre-conditions and table parsing via `helpers` package

✅ **Comprehensive Logging** - Log4j2 with detailed execution traces

✅ **Exception Handling** - Stale element and timeout recovery

✅ **Browser Management** - WebDriverManager for automatic driver handling (Chrome/Firefox)

✅ **VS Code Integration** - Fully configured with tasks, debug launch, and settings

✅ **Java 21 Support** - Modern Java 21 with Maven compiler target=21

---

## Configuration Files

### Test Suite (testng.xml)

**Location:** `src/test/resources/testng.xml`

- Defines test execution order
- Browser configuration parameter
- 6 test classes (LoginTest, LogoutTest, CreateAccount, ResetPassword, BookTicket, CancelBooking)

### Application Constants (Constant.java)

**Location:** `src/main/java/constant/Constant.java`

```properties
Base URL: http://saferailway.somee.com
Implicit Wait: 12 seconds
Browser Window: Maximized
Test User: phamnam@sharklasers.com
Test Password: 11111111
```

### Logging Configuration (log4j2.xml)

**Location:** `src/test/resources/log4j2.xml`

- Log level: DEBUG
- Output: Console + `logs/TestExecution.log`

### Maven Configuration (pom.xml)

**Location:** `pom.xml`

- Java version: 21
- Selenium: 4.27.0
- TestNG: 7.12.0
- Log4j: 2.24.3
- WebDriverManager: 5.9.2
- Allure: 2.33.0

---

## Dependencies & Technology Stack

```
✅ Selenium WebDriver 4.27.0 - Browser automation
✅ TestNG 7.12.0 - Test execution framework
✅ Log4j 2.24.3 - Logging framework
✅ Jackson 2.21.0 - JSON data processing
✅ WebDriverManager 5.9.2 - Browser driver management
✅ Allure 2.33.0 - Test reporting
✅ AspectJ 1.9.24 - AOP support
✅ Maven 3.13.0+ - Build automation
✅ Java 21 - Programming language
```

---

## Test Data Management

### Data-Driven Testing

- Test data stored in JSON: `src/test/resources/Railway/BookTicket.json`
- Loaded via `common.JsonDataReader` utility
- Used with TestNG `@DataProvider` for parameterization

### Example Structure

```
BookTicket.java → @DataProvider
  ↓
DataProviders.getBookTicketData()
  ↓
JsonDataReader.loadTestData()
  ↓
BookTicket.json (JSON test data)
  ↓
BookTicketData POJO (data binding)
```

---

## Troubleshooting

| Issue                              | Solution                                        |
| ---------------------------------- | ----------------------------------------------- |
| Chrome driver not found            | WebDriverManager auto-downloads; check internet |
| "Cannot resolve symbol" in VS Code | Run `mvn clean install` and reload VS Code      |
| Tests timeout or hang              | Check network connectivity; increase wait times |
| Port 4444 in use (Allure)          | Use `mvn allure:serve -Dallure.serverPort=8888` |
| Maven not found                    | Add Maven to PATH; verify with `mvn --version`  |

---

## Quick Reference Commands

```bash
# Build & Compile
mvn clean compile              # Compile production code
mvn clean test-compile         # Compile test code
mvn clean install              # Full build & install

# Testing
mvn clean test                 # Run all tests
mvn clean test -Dtest=LoginTest  # Run specific test class
mvn clean test -Dtest=LoginTest#testLoginWithValidCredentials  # Run specific method

# Reporting
mvn allure:report              # Generate Allure report
mvn allure:serve               # Serve Allure report

# Other
mvn clean package -DskipTests  # Package without running tests
mvn clean                      # Clean build artifacts
```

---

## Key Features Summary

| Feature          | Implementation               | Location                                     |
| ---------------- | ---------------------------- | -------------------------------------------- |
| **Page Objects** | Encapsulated UI interactions | `src/main/java/pageobjects/`                 |
| **Test Data**    | JSON-driven parameterization | `src/test/resources/Railway/BookTicket.json` |
| **Constants**    | Centralized static values    | `src/main/java/constant/`                    |
| **Helpers**      | Pre-conditions & utilities   | `src/main/java/helpers/`                     |
| **Logging**      | Log4j2 with file & console   | `src/test/resources/log4j2.xml`              |
| **Reports**      | TestNG + Allure              | `test-output/` + `allure-results/`           |
| **VS Code**      | Integrated debugging & tasks | `.vscode/`                                   |

---

## Test Coverage

| Test Class         | Purpose                      | Status                |
| ------------------ | ---------------------------- | --------------------- |
| LoginTest          | User authentication          | ✅ 3 scenarios        |
| LogoutTest         | Session termination          | ✅ 1 scenario         |
| CreateAccount      | User registration            | ✅ 3 scenarios        |
| ResetPassword      | Password recovery            | ✅ 2 scenarios        |
| BookTicket         | Ticket booking (data-driven) | ✅ 8+ scenarios       |
| CancelBooking      | Ticket cancellation          | ✅ 2 scenarios        |
| **Total Coverage** |                              | **✅ 19+ test cases** |

---

## Architecture Overview

```
Test Execution Layer
    ├── LoginTest.java
    ├── BookTicket.java
    └── CancelBooking.java
         ↓
Page Object Layer
    ├── pageobjects/LoginPage.java
    ├── pageobjects/BookTicketPage.java
    └── pageobjects/GeneralPage.java (base)
         ↓
Utility Layer
    ├── common/Utilities.java (waits, actions)
    ├── common/Log4j.java (logging)
    ├── helpers/PreconditionHelper.java (setup)
    └── helpers/TableHelper.java (parsing)
         ↓
Data Layer
    ├── dataobjects/BookTicketData.java
    ├── dataobjects/RegisterAccount.java
    └── constant/* (enums & constants)
         ↓
Selenium WebDriver
    └── Chrome, Firefox (via WebDriverManager)
```

---

## Best Practices Applied

✅ **Maven Standard Structure** - Follows Maven directory conventions

✅ **Page Object Model** - Separation between test logic and page interactions

✅ **Data-Driven Testing** - Externalized test data in JSON format

✅ **Explicit Waits** - FluentWait for reliable element synchronization

✅ **Comprehensive Logging** - Detailed execution traces with Log4j2

✅ **Type-Safe Constants** - Enums and constants in dedicated package

✅ **Exception Handling** - Graceful error recovery with informative messages

✅ **Modular Helpers** - Reusable pre-conditions and utilities

✅ **IDE Integration** - Full VS Code configuration for smooth development

✅ **Clean Code** - Single responsibility, DRY principle, meaningful names

---

## Resources & Documentation

- 📚 [Selenium Official Docs](https://www.selenium.dev/documentation/)
- 📚 [TestNG Documentation](https://testng.org/doc/)
- 📚 [Maven Guide](https://maven.apache.org/guides/)
- 📚 [Log4j 2 Manual](https://logging.apache.org/log4j/2.x/)
- 📚 [Allure Report](https://docs.qameta.io/allure/)
- 📚 [WebDriverManager](https://github.com/bonigarcia/webdrivermanager)

---

## Notes

- All tests use Explicit Waits (FluentWait) - no `Thread.sleep()`
- Browser drivers auto-downloaded via WebDriverManager
- Test data is external JSON, not hardcoded in tests
- Logging captures every action for debugging
- Supports Chrome and Firefox browsers
- Compatible with Java 21+

---

## Author

**Phạm Văn Nam**

---

_Last Updated: April 2026 | Maven-based Railway Ticket Booking Automation Framework_
