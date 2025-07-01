README - Selenium POM Automation Framework

Overview:
This is a professional-grade Selenium automation framework built using the Page Object Model (POM) design pattern with Java, TestNG, and Maven.
It is structured to support clean scalability, maintainability, and easy integration with CI/CD pipelines.
The framework showcases real-world scenarios such as:
- Amazon-style product search and add-to-cart validations
- File upload and dynamic file creation
- Tooltip and toast message validation
- Calendar picker selection
- Multi-window and iframe handling

Key Features:
- **Java + Selenium + TestNG + Maven**
- Modular and maintainable codebase
- Page Object Model (POM): Clean separation between test logic and UI interaction.
- Maven Project Structure: Standard Maven setup with clear separation of test and main code.
- TestNG Integration: TestNG for assertions, parallel execution, and test grouping.
- Retry Analyzer: Automatically reruns failed tests.
- Extent Reports: Rich, detailed reporting with screenshots on failure.
- Log4j Logging: Configurable and structured logging.
- Data-Driven Testing: Using TestNG DataProviders and Excel-based input.
- Utility Classes: WebDriver utilities, wait handling, Excel utilities, etc.
- Screenshot Captures: On failure and step-wise if required.
- Realistic test scenarios like:
  - Amazon-style search & cart validation
  - File upload, tooltips, alerts
  - Web tables + Excel read/write
  - Frames/iFrames, calendar pickers
  - Multi-window/tab handling



Project Structure:
src/
├── main/
│   └── java/
│       └── com.project.framework/
│           ├── base/               
│           ├── pages/             
│           ├── utilities/          
├── test/
│   └── java/
│       └── com.project.tests/
│           ├── amazon/             
│           ├── calendar/           
│           ├── upload/             
│           └── common/             
└── resources/
    ├── config.properties       
    └── log4j.properties


Scenarios Covered:
- Amazon-style Search + Add to Cart: POM-based validations on search and cart status.
- File Upload: Real-time file creation & upload automation.
- Tooltip & Toast Message: Hover tooltips, disappearing toasts, and alert popups.
- Calendar Picker: Select specific dates/months dynamically.
- Window & Iframe Handling: Multi-window/tab and iframe context switching.
- Web Table + Excel Validation: Read data from UI and assert against Excel.
- Alerts
- Testcase Generator
- Sorting Table
- Broken Links


Technologies Used:
- Java 17
- Selenium WebDriver 4.x
- TestNG
- Maven
- Log4j
- Extent Reports
- Apache POI (Excel)
- Git/GitHub

How to Run:
1. Clone the repo:
   git clone https://github.com/RemyaKrishnanTR/selenium-pom-framework.git

2. Import as Maven project in IntelliJ or Eclipse.
3. Update config.properties with your browser and base URL.
4. Run test using:
   mvn clean test
   
Note:This framework is a work in progress — more real-world scenarios, enhancements, and test cases are actively being added and optimized.
