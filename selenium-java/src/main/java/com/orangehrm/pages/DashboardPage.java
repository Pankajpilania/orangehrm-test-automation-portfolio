package com.orangehrm.pages;

import com.orangehrm.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends BasePage {

    @FindBy(xpath = "//h6[text()='Dashboard']")
    private WebElement dashboardHeader;

    @FindBy(xpath = "//span[text()='PIM']")
    private WebElement pimMenuLink;

    @FindBy(xpath = "//span[text()='Admin']")
    private WebElement adminMenuLink;

    @FindBy(xpath = "//span[text()='Leave']")
    private WebElement leaveMenuLink;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public boolean isDashboardDisplayed() {
        waitUtils.waitForElementToBeVisible(dashboardHeader);
        return dashboardHeader.isDisplayed();
    }

    public void clickPimMenu() {
        click(pimMenuLink);
    }

    public void clickAdminMenu() {
        click(adminMenuLink);
    }

    public void clickLeaveMenu() {
        click(leaveMenuLink);
    }
}
