# Multi-Layer Automation Framework (UI & API)
This project is a hybrid test automation framework designed to validate both Web UI and RESTful API layers. It specifically targets the Firsttrip.com flight search workflow and the JSONPlaceholder user management API.

### Framework Architecture & Design Choices

1. Behavior-Driven Development (BDD)
   - We utilize Cucumber with Gherkin syntax to define test scenarios in plain English.
   - Reasoning: This bridge ensures that technical tests remain aligned with business requirements. It allows non-technical stakeholders to review test coverage and promotes the "Three Amigos" collaboration model.

2. Page Object Model (POM) with a Logic Layer
   - The project separates locators from execution logic:
     - Locators: Stored as static strings in a dedicated locators package for easy maintenance.
     - Business Logic: Complex workflows (like dynamic date selection) are encapsulated in a businessLogic layer.
   - Reasoning: This prevents code duplication. If a UI element changes, you only update it in one file, not in every test case.

3. Data-Driven Testing via JSON
   - Test data (URLs, credentials, search parameters) is decoupled from the code and stored in src/test/resources/testdata/Test_Data.json.
   - Reasoning: Decoupling data allows the suite to run against multiple environments (QA, Staging, Dev) without changing a single line of Java code.

4. Resilient Synchronization (Explicit Waits)
   - The framework strictly avoids Thread.sleep(), utilizing WebDriverWait and ExpectedConditions.
   - Reasoning: Modern SPAs (Single Page Applications) like Firsttrip have asynchronous loading. Explicit waits ensure the script only interacts with elements when they are truly ready, significantly reducing flakiness.
  
### Project Structure 
```
Automation-Assessment-UI-API
├── src
│   ├── main/java
│   │   ├── businessLogic      # Reusable business actions (Click, Select, Assert)
│   │   ├── locators           # Pure Page Objects (XPaths and CSS)
│   │   └── utils              # DriverFactory, JSONReader, ConfigLoaders
│   ├── test/java
│   │   ├── runners            # Junit TestRunners (entry point)
│   │   └── stepDefinitions    # Glue code mapping Gherkin to Logic
│   └── test/resources
│       ├── features           # Gherkin Feature files (@web, @api)
│       └── testdata           # JSON files for data-driven testing
├── pom.xml                    # Maven dependencies and Plugins
└── .gitignore                 # Version control exclusions

```

### Tech Stack
  - Language: Java 11+
  - Web Engine: Selenium WebDriver (Chrome)
  - API Engine: RestAssured
  - Test Runner: JUnit 4/5
  - Framework: Cucumber BDD
  - Data Handling: Jackson Databind (JSON)
  - Reporting: Cucumber HTML Reports / Extent Reports

### Execution Instructions
  - Prerequisites
    - Java JDK 11 or higher installed.
    - Maven installed and configured in system PATH.
    - Chrome browser installed.
   
### Run via Command Line
  - Run the full suite: ```mvn clean test   ```
  - Run specific layers using tags: ``` mvn test -Dcucumber.filter.tags="@web"
                                        mvn test -Dcucumber.filter.tags="@api"```

### Reporting & Hooks
  - Automatic Teardown: The @After hook ensures the browser is closed after every scenario to prevent memory leaks.
  - Failure Analysis: If a UI test fails, the framework is configured to capture a screenshot and embed it directly into the HTML report.
  - Report Location: Open target/cucumber-reports/index.html in any browser after execution.

### Attachment 
   https://drive.google.com/file/d/1UFo24OZMNAtLvUB6uG0_ykKimZo32YNN/view?usp=sharing
   https://drive.google.com/file/d/1xU6ugHuCM0W9kH-nJoZntmZPKjYtpmBY/view?usp=sharing
