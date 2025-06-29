# Selenium TestNG Automation Framework

A modular UI automation framework built using **Java, Selenium WebDriver, TestNG, and Maven**. Designed to demonstrate core automation concepts and structured project organization.

## Features

- Page Object Model (POM) design
- Data-driven testing using TestNG `@DataProvider` and Excel
- Screenshot capture on failure
- ExtentReports integration
- Retry logic for flaky tests
- Real-world scenarios:
  - Search & Add to Cart
  - File Upload with dynamic file generation
  - Web Table validation with Excel
  - Pagination handling across dynamic tables
  - Test Case Generator using input combinations
  - Mouse Hover & Multi-level Menus
  - Auto-suggestions with keyboard navigation

## Project Structure

```
├── src
│   ├── main
│   │   ├── base
│   │   ├── pages
│   │   ├── utils
│   │   └── factory
│   └── test
│       ├── tests
│       └── dataproviders
├── testng.xml
├── pom.xml
└── README.md
```

## Getting Started

```bash
git clone https://github.com/your-username/your-repo-name.git
cd your-repo-name
mvn clean test
```

Reports are available at: `test-output/ExtentReports/index.html`

## Status

🚧 Work in Progress – New scenarios and enhancements are being added.
