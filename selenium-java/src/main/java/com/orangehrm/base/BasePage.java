package com.orangehrm.base;

import com.orangehrm.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    protected WebDriver driver;
    protected WaitUtils waitUtils;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
        PageFactory.initElements(driver, this);
    }

    public void click(WebElement element) {
        waitUtils.waitForElementToBeClickable(element);
        element.click();
    }

    public void type(WebElement element, String text) {
        waitUtils.waitForElementToBeVisible(element);
        element.clear();
        element.sendKeys(text);
    }

    public boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void waitForVisibility(WebElement element) {
        waitUtils.waitForElementToBeVisible(element);
    }

    public String getText(WebElement element) {
        waitUtils.waitForElementToBeVisible(element);
        return element.getText().trim();
    }
}

