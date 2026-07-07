package com.orangehrm.tests.smoke;

import com.aventstack.extentreports.Status;
import com.orangehrm.base.BaseTest;
import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utils.ConfigReader;
import com.orangehrm.utils.ExtentManager;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * SMOKE-01 to SMOKE-05 — Login / Logout smoke suite.
 * Runs across all three frameworks (Selenium, Playwright, Cypress) for side-by-side comparison.
 */
public class LoginTest extends BaseTest {

    // SMOKE-01: Valid login
    @Test(groups = {"smoke", "regression"}, description = "SMOKE-01: Valid credentials should load Dashboard")
    public void testValidLogin() {
        ExtentManager.getTest().log(Status.INFO, "Navigating to login page");
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = loginPage.login(
                ConfigReader.getProperty("admin.username"),
                ConfigReader.getProperty("admin.password")
        );
        ExtentManager.getTest().log(Status.INFO, "Verifying Dashboard is displayed");
        Assert.assertTrue(dashboardPage.isDashboardDisplayed(), "Dashboard should be visible after valid login");
    }

    // SMOKE-02: Invalid credentials
    @Test(groups = {"smoke", "regression"}, description = "SMOKE-02: Invalid credentials should show error message")
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithoutRedirect("Admin", "wrongPassword123");
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be shown for invalid credentials");
        Assert.assertEquals(loginPage.getErrorMessage(), "Invalid credentials", "Error message text mismatch");
    }

    // SMOKE-03: Empty field validation
    @Test(groups = {"smoke", "regression"}, description = "SMOKE-03: Empty submit should show 'Required' validation messages")
    public void testEmptyFieldValidation() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickLoginButton();
        Assert.assertTrue(loginPage.isUsernameRequiredMessageDisplayed(), "Username 'Required' message should appear");
        Assert.assertTrue(loginPage.isPasswordRequiredMessageDisplayed(), "Password 'Required' message should appear");
    }

    // SMOKE-04: Logout
    @Test(groups = {"smoke", "regression"}, description = "SMOKE-04: Logout should redirect to login page")
    public void testLogout() {
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = loginPage.login(
                ConfigReader.getProperty("admin.username"),
                ConfigReader.getProperty("admin.password")
        );
        Assert.assertTrue(dashboardPage.isDashboardDisplayed(), "Dashboard should load after login");
        dashboardPage.logout();
        Assert.assertTrue(driver.getCurrentUrl().contains("auth/login"), "Should redirect to login page after logout");
    }

    // SMOKE-05: Forgot password link
    @Test(groups = {"smoke"}, description = "SMOKE-05: 'Forgot your password?' link should load reset page")
    public void testForgotPasswordLink() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickForgotPassword();
        Assert.assertTrue(driver.getCurrentUrl().contains("requestPasswordResetCode"), "Should navigate to reset password page");
    }
}
