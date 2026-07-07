package com.orangehrm.pages;

import com.orangehrm.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LoginPage extends BasePage {

    @FindBy(name = "username")
    private WebElement usernameInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    @FindBy(css = ".oxd-alert-content-text")
    private WebElement errorMessage;

    @FindBy(css = ".oxd-input-field-error-message")
    private List<WebElement> requiredMessages;

    @FindBy(xpath = "//p[contains(text(),'Forgot your password')]/..")
    private WebElement forgotPasswordLink;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(String username) {
        type(usernameInput, username);
    }

    public void enterPassword(String password) {
        type(passwordInput, password);
    }

    public void clickLoginButton() {
        click(loginButton);
    }

    public DashboardPage clickLogin() {
        click(loginButton);
        return new DashboardPage(driver);
    }

    /** Login and return Dashboard (happy path) */
    public DashboardPage login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        return clickLogin();
    }

    /** Login without expecting a redirect — used to test error states */
    public void loginWithoutRedirect(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    public boolean isErrorMessageDisplayed() {
        return isDisplayed(errorMessage);
    }

    public String getErrorMessage() {
        waitForVisibility(errorMessage);
        return errorMessage.getText().trim();
    }

    public boolean isUsernameRequiredMessageDisplayed() {
        return !requiredMessages.isEmpty() && requiredMessages.get(0).isDisplayed();
    }

    public boolean isPasswordRequiredMessageDisplayed() {
        return requiredMessages.size() >= 2 && requiredMessages.get(1).isDisplayed();
    }

    public void clickForgotPassword() {
        click(forgotPasswordLink);
    }
}

