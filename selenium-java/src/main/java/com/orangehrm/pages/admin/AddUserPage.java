package com.orangehrm.pages.admin;

import com.orangehrm.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddUserPage extends BasePage {

    @FindBy(xpath = "//label[text()='Username']/../following-sibling::div//input")
    private WebElement usernameInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveButton;

    public AddUserPage(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(String username) {
        type(usernameInput, username);
    }

    public void clickSave() {
        click(saveButton);
    }
}
