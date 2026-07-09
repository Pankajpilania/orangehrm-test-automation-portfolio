package com.orangehrm.bdd.stepdefs;

import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utils.ConfigReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import java.time.Duration;

public class LoginSteps {

    private WebDriver driver;
    private LoginPage loginPage;
    private DashboardPage dashboardPage;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        if(Boolean.parseBoolean(ConfigReader.getProperty("headless"))) {
            options.addArguments("--headless=new");
        }
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(ConfigReader.getProperty("implicit.wait"))));
        loginPage = new LoginPage(driver);
    }

    @Given("I am on the OrangeHRM login page")
    public void iAmOnTheOrangeHRMLoginPage() {
        driver.get(ConfigReader.getProperty("app.url"));
    }

    @When("I enter valid username and password")
    public void iEnterValidUsernameAndPassword() {
        loginPage.enterUsername(ConfigReader.getProperty("admin.username"));
        loginPage.enterPassword(ConfigReader.getProperty("admin.password"));
    }

    @When("I click the login button")
    public void iClickTheLoginButton() {
        dashboardPage = loginPage.clickLogin();
    }

    @Then("I should be redirected to the Dashboard page")
    public void iShouldBeRedirectedToTheDashboardPage() {
        Assert.assertTrue(dashboardPage.isDashboardDisplayed());
    }

    @When("I enter invalid username and password")
    public void iEnterInvalidUsernameAndPassword() {
        loginPage.enterUsername("InvalidUser");
        loginPage.enterPassword("InvalidPass");
    }

    @Then("I should see an error message")
    public void iShouldSeeAnErrorMessage() {
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message was not displayed");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
