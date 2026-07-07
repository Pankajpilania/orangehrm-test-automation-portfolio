# OrangeHRM Test Automation Portfolio

> A production-quality, multi-framework QA automation portfolio targeting the [OrangeHRM Demo](https://opensource-demo.orangehrmlive.com) — designed to showcase enterprise Java patterns alongside modern JavaScript-based tooling in a single, structured mono-repo.

[![Selenium CI](https://img.shields.io/github/actions/workflow/status/Pankajpilania/orangehrm-test-automation-portfolio/selenium.yml?label=Selenium&logo=java)](https://github.com/Pankajpilania/orangehrm-test-automation-portfolio/actions)
[![Playwright CI](https://img.shields.io/github/actions/workflow/status/Pankajpilania/orangehrm-test-automation-portfolio/playwright.yml?label=Playwright&logo=playwright)](https://github.com/Pankajpilania/orangehrm-test-automation-portfolio/actions)
[![Cypress CI](https://img.shields.io/github/actions/workflow/status/Pankajpilania/orangehrm-test-automation-portfolio/cypress.yml?label=Cypress&logo=cypress)](https://github.com/Pankajpilania/orangehrm-test-automation-portfolio/actions)

---

## Why Three Frameworks?

Rather than duplicating identical tests three times, each framework is assigned the module where its strengths genuinely shine:

| Framework | Language | Module Focus | Strengths Demonstrated |
|---|---|---|---|
| **Selenium + Java** | Java 17 | PIM, Admin, Leave | PageFactory POM, `@DataProvider` CSV, ExtentReports, Cucumber/BDD, Rest Assured API, Jenkins CI |
| **Playwright** | TypeScript | Recruitment, Dashboard, API | `storageState` auth reuse, 3-browser parallel matrix, built-in trace/video, UI + API in one suite |
| **Cypress** | JavaScript | My Info, Time & Attendance, Network | `cy.intercept()` stubbing, JWT security tests, custom commands, Mochawesome reporting |

**All three share the Login Smoke Suite (SMOKE-01 to SMOKE-05)** — same scenarios, three implementations, side by side so a reviewer can compare style across tools in 30 seconds.

---

## Repo Structure

```
orangehrm-test-automation-portfolio/
├── selenium-java/          # Java 17 · Maven · TestNG · ExtentReports · Cucumber · Rest Assured
├── playwright-js/          # TypeScript · Playwright · 3-browser parallel
├── cypress-js/             # JavaScript · Cypress 13 · Mochawesome
├── docker/
│   └── selenium-grid/
│       └── docker-compose.yml   # Selenium Hub + Chrome Node
├── docs/
│   ├── test-plan.md
│   └── ai-assisted-development.md
├── Jenkinsfile             # Declarative pipeline for selenium-java suite
└── .github/workflows/      # GitHub Actions for Playwright & Cypress
```

---

## Prerequisites

| Tool | Version | Required For |
|---|---|---|
| Java | 17+ | Selenium, Rest Assured, Cucumber |
| Maven | 3.8+ | Selenium build & dependency management |
| Node.js | 20+ | Playwright, Cypress |
| Docker Desktop | any | Optional — Selenium Grid |
| Chrome | latest | All frameworks (primary browser) |

---

## Quick Start

### Clone & Setup
```bash
git clone https://github.com/Pankajpilania/orangehrm-test-automation-portfolio.git
cd orangehrm-test-automation-portfolio
```

### 1. Selenium + Java
```bash
cd selenium-java

# Run smoke suite
mvn clean test -Dgroups=smoke -Dbrowser=chrome

# Run full regression suite
mvn clean test -Dgroups=regression -Dbrowser=chrome

# Run Cucumber BDD smoke suite
mvn test -Dtest=CucumberTestRunner

# Run Rest Assured API tests
mvn test -Dtest=LoginApiTest,EmployeeApiTest

# View ExtentReport
# Open: selenium-java/target/test-output/ExtentReport.html

# Optional: run against Dockerized Selenium Grid
docker compose -f ../docker/selenium-grid/docker-compose.yml up -d
mvn clean test -Dgroups=smoke -Dgrid.url=http://localhost:4444/wd/hub
```

### 2. Playwright + TypeScript
```bash
cd playwright-js
npm install
npx playwright install chromium   # or: npx playwright install (all browsers)

# Run smoke suite (Chromium)
npx playwright test tests/smoke/ --project=chromium

# Run all tests on all 3 browsers
npx playwright test

# View HTML report + trace viewer
npx playwright show-report
```

### 3. Cypress + JavaScript
```bash
cd cypress-js
npm install

# Headless run
npx cypress run --spec "cypress/e2e/smoke/**"

# Interactive mode (Cypress UI)
npx cypress open

# View Mochawesome report
# Open: cypress-js/cypress/reports/html/index.html
```

---

## Test Coverage

### Core Smoke Suite — all three frameworks

| ID | Scenario |
|---|---|
| SMOKE-01 | Valid login → Dashboard loads |
| SMOKE-02 | Invalid credentials → Error message shown |
| SMOKE-03 | Empty fields → "Required" validation messages |
| SMOKE-04 | Logout → Redirected to login page |
| SMOKE-05 | Page title / forgot password link |

### Selenium + Java — PIM · Admin · Leave (19 scenarios)
PIM-01–09 · ADM-01–05 · LEA-01–05  
Includes: data-driven `@DataProvider` from `employees.csv`, Cucumber BDD feature file for login, Rest Assured JSON schema validation

### Playwright — Recruitment · Dashboard · API (13 scenarios)
REC-01–06 · DASH-01–04 · API-01–03  
Includes: `storageState` for auth, cross-browser matrix (Chromium/Firefox/WebKit), API-layer tests via `request` fixture

### Cypress — My Info · Time & Attendance · Network Control (14 scenarios)
MYI-01–05 · TIME-01–04 · NET-01–06  
Includes: `cy.intercept()` stubs, JWT token capture & tamper tests, custom `cy.login()` / `cy.addEmployee()` commands

---

## CI / CD

| Pipeline | Tool | Trigger |
|---|---|---|
| `Jenkinsfile` | Jenkins declarative | Manual / SCM poll |
| `.github/workflows/selenium.yml` | GitHub Actions | Push / PR to `main` |
| `.github/workflows/playwright.yml` | GitHub Actions matrix | Push / PR — 3 browsers in parallel |
| `.github/workflows/cypress.yml` | GitHub Actions | Push / PR |

---

## Reporting

| Framework | Report | Location |
|---|---|---|
| Selenium | ExtentReports (Spark) + screenshots on failure | `selenium-java/target/test-output/ExtentReport.html` |
| Playwright | Built-in HTML report + Trace Viewer | `playwright-js/playwright-report/` |
| Cypress | Mochawesome (charts + embedded screenshots) | `cypress-js/cypress/reports/html/index.html` |

---

## Docker — Selenium Grid

Run the Selenium suite against a real Selenium Grid instead of a local ChromeDriver:

```bash
# Start hub + Chrome node
docker compose -f docker/selenium-grid/docker-compose.yml up -d

# Grid UI available at http://localhost:4444

# Run tests against the grid
cd selenium-java
mvn clean test -Dgroups=smoke -Dgrid.url=http://localhost:4444/wd/hub

# Tear down
docker compose -f docker/selenium-grid/docker-compose.yml down
```

---

## Environment Variables

Each sub-project has an `.env.example`. Copy to `.env` and fill in values (demo credentials are public but modelled correctly for portfolio realism):

```bash
# playwright-js/.env.example
ADMIN_USERNAME=Admin
ADMIN_PASSWORD=admin123
BASE_URL=https://opensource-demo.orangehrmlive.com
```

> **Never commit real credentials.** The demo site resets periodically — tests generate unique data with `Date.now()` suffixes to avoid collisions.

---

## AI-Assisted Development

This portfolio was built with AI-assisted tooling (GitHub Copilot / LLMs). See [`docs/ai-assisted-development.md`](docs/ai-assisted-development.md) for specifics on where AI accelerated development — step-definition boilerplate, JSON schema generation, and locator strategy suggestions.

---

## License

MIT — free to fork, adapt, and use as a portfolio template.
