package com.orangehrm.pages.admin;

import com.orangehrm.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminPage extends BasePage {

    @FindBy(xpath = "//button[normalize-space()='Add']")
    private WebElement addButton;

    public AdminPage(WebDriver driver) {
        super(driver);
    }

    public AddUserPage clickAddUser() {
        click(addButton);
        return new AddUserPage(driver);
    }
}
