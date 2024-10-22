# Cucumber Selenium Project

This project is an automation framework using Cucumber and Selenium for testing web applications. 
It follows the Behavior-Driven Development (BDD) approach, allowing you to write tests in plain language.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Project Structure](#project-structure)
- [Writing Features](#writing-features)
- [Running Tests](#running-tests)
- [Generating Reports](#generating-reports)
- [Contributing](#contributing)
- [License](#license)

## Introduction

This Cucumber-Selenium project provides a framework for automated testing of web applications. 
It allows testers to write test scenarios in Gherkin syntax and execute them using Selenium WebDriver.

## Features

- Behavior-Driven Development (BDD) with Cucumber
- Automated web testing with Selenium WebDriver
- Easy to read and maintain feature files
- HTML and XML reporting

## Prerequisites

Before you begin, ensure you have met the following requirements:

- **Java Development Kit (JDK)** 8 or higher
- **Maven** for dependency management
- **WebDriver** for your browser (e.g., ChromeDriver for Google Chrome)
  - Ensure the WebDriver is in your system's PATH or specify the path in your test code.
- **IDE** (like IntelliJ IDEA or Eclipse)

## Installation

1. **Clone the repository**:
   ```bash
   git clone git@github.com:aujlaramandeep27/SeleniumJava.git
   cd SeleniumJava
   
## Project Structure

SeleniumJava/
├── src/
│   ├── main/
│   └── test/
│       ├── java/
│       │   └── feature/
│       
├── pom.xml
└── README.md

- `src/test/resources/features`: Contains .feature files written in Gherkin syntax.
- `src/test/java/steps`: Contains Java classes for step definitions.
- `src/test/java/pages`: Contains Java classes for web pages.
- `pom.xml`: Maven configuration file for dependencies.

## Writing Features

Feature files are written in Gherkin syntax. 
Each feature file should be placed in the `src/test/java/features` directory

## Running Tests
To run the tests, use the following Maven command:

`mvn test`

This will execute all tests defined in your feature files.

## Generating Reports
Cucumber can generate reports in various formats. 
To generate an XML or HTML report, follow these steps:

1. Run the tests to generate XML report:

`mvn clean test`

The reports will be available in the `target/surefire-reports`directory named `TEST-runners.TestRunner.xml`

2. Generate the HTML report:

`mvn surefire-report:report`

The reports will be available in the `target/reports`directory named `surefire.html`