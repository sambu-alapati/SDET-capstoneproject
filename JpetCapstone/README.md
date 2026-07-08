

# JpetCapstone Automation Framework

A behavior-driven development (BDD) test automation framework designed to validate the end-to-end user workflows of the JPetStore web application. This framework is built using Java, Selenium WebDriver, Cucumber, and TestNG, following the Page Object Model (POM) design pattern.

---

##  Key Features
* **Behavior Driven Development**: Clear, human-readable Gherkin feature scenarios.
* **Page Object Model (POM)**: Isolated UI element locators and clean page action classes.
* **Global Hooks**: Automatic driver setup, teardown, and conditional screenshot hooks.
* **Triple Reporting Engine**: Comprehensive coverage metrics tracked across **Allure Reports**, **ExtentReports**, and native **Cucumber Reporting Dashboard formats**.
* **Robust Utilities**: Specialized utilities for programmatic screenshot capture and secure verification validation.

---

##  Tech Stack & Dependencies
* **Language**: Java 17
* **Automation Tool**: Selenium WebDriver (4.x)
* **Test Runner**: TestNG / Cucumber JVM
* **Design Pattern**: Page Object Model (POM) with Step Definitions
* **Build Management**: Apache Maven
* **Reporting**: Allure Reports, ExtentReports, and Cucumber JVM Plugins

---

##  Project Structure

```text
JpetCapstone/
├── .allure/                     # Allure binary downloads
├── allure-results/              # Raw Allure execution data JSONs (generated dynamically)
├── src/
│   └── test/
│       └── java/
│           ├── features/        # Gherkin .feature workflow definition files
│           ├── hooks/           # Cucumber global execution setup & teardown hooks
│           ├── pages/           # Page Object classes (element locators and UI actions)
│           ├── runner/          # TestNG-Cucumber suite runner entry points
│           ├── stepDef/         # Step definition glue code files
│           └── utils/           # Utility helpers (allure screenshots, password verification)
├── target/                      # Compiled code artifacts and test outputs
│   └── cucumber-reports/        # Native Cucumber report dashboard outputs
│       ├── Cucumber.json        # Structured JSON engine log for CI/CD integrations
│       ├── Cucumber.xml         # JUnit formatted XML report for test parsing
│       └── JpetCapstone.html    # Standalone, interactive HTML report file
├── test-output/                 # TestNG run engine execution logs
├── pom.xml                      # Central Maven dependencies and plugin lifecycle rules
└── testng.xml                   # Parallelization parameters and regression suites
```

---

##  Automated Scenarios Covered
The framework comprehensively validates the core user lifecycle across the following feature tracks:
1. **`01_JpetRegister.feature`**: Registration field parameters, data persistence, and confirmation validation.
2. **`02_JpetLoginAndLogout.feature`**: Credentials validation, session management, and explicit sign-out states.
3. **`03_JpetHome.feature`**: Catalog search functionality, header navigations, and landing layouts.
4. **`04_JpetCart.feature`**: Dynamic updates, volume calculations, and persistent item additions.
5. **`05_JpetCheckout.feature`**: Payment profiles, delivery address forms, and order confirmation tracking.
6. **`06_JpetDeleteOrder.feature`**: Purchase histories, record cancellations, and backend order purge flows.

---

##  Setup & Local Execution

### Prerequisites
* Java Development Kit (JDK 17)
* Apache Maven (3.8+)
* Google Chrome (or target browser configured in hooks)
* **Eclipse IDE** (Eclipse IDE for Java Developers or Enterprise Edition)
* **Cucumber Eclipse Plugin** (Installed via Eclipse Marketplace for Gherkin syntax highlighting)

---

##  Importing and Using in Eclipse IDE

### Step 1: Clone the Repository directly into Eclipse
1. Open Eclipse and switch to the **Git Perspective** (Window > Perspective > Open Perspective > Other... > Git).
2. Click on **Clone a Git repository** in the Git Repositories view.
3. Paste the Repository URL in the **URI** field: `https://github.com`
4. Enter your credentials if requested and click **Next**.
5. Select the target branch and click **Next**.
6. Set your local destination directory path and click **Finish**.

### Step 2: Import as an Existing Maven Project
1. Switch back to the **Java Perspective** or standard **Project Explorer** view.
2. Navigate to **File** > **Import...**
3. Expand the **Maven** folder, choose **Existing Maven Projects**, and click **Next**.
4. Click **Browse** and select the cloned root folder (`SDET-capstoneproject`).
5. Check the box next to `/JpetCapstone/pom.xml`.
6. Click **Finish**. Eclipse will begin resolving dependencies from the `pom.xml` automatically.

### Step 3: Update and Build Project Dependencies
1. Right-click on the `JpetCapstone` project root folder in the Project Explorer.
2. Select **Maven** > **Update Project...**
3. Check the box for **Force Update of Snapshots/Releases** and click **OK**.

### Step 4: Running Tests inside Eclipse
You can execute your automation test suites using any of the following methods inside the IDE:

* **Using TestNG Runner (Recommended):**
  Right-click on **`testng.xml`** or **`JpetRunner.java`** > **Run As** > **TestNG Test**.
* **Using Maven Lifecycle Build:**
  Right-click on **`pom.xml`** > **Run As** > **Maven test**.

---

##  Reporting Architecture

### Cucumber Native Reports
Located automatically upon run completion in `target/cucumber-reports/`.
* **`JpetCapstone.html`**: A lightweight, standalone visual web report tracking passed/failed step timelines.
* **`Cucumber.json` / `Cucumber.xml`**: Machine-readable summaries ideal for integrating into CI/CD build pipelines (Jenkins, GitHub Actions).

### Allure Reports
Advanced interactive dashboards tracking historical passes, specific feature breakdowns, step execution times, and contextual error screenshots embedded directly on step failures.
* To launch the dashboard locally via the terminal, use:
  ```bash
  mvn allure:serve -Dallure.results.directory=allure-results
  ```

### Extent Reports
Located in `target/ExtentReports/`. Provides optimized, tabular layout logs of runtime verification checkpoints.
