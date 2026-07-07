package com.orangehrm.pages.pim;

import com.orangehrm.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddEmployeePage extends BasePage {

    @FindBy(name = "firstName")
    private WebElement firstNameInput;

    @FindBy(name = "lastName")
    private WebElement lastNameInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveButton;

    public AddEmployeePage(WebDriver driver) {
        super(driver);
    }

    public void enterFirstName(String firstName) {
        type(firstNameInput, firstName);
    }

    public void enterLastName(String lastName) {
        type(lastNameInput, lastName);
    }

    public void clickSave() {
        click(saveButton);
    }

    public void addEmployee(String firstName, String lastName) {
        enterFirstName(firstName);
        enterLastName(lastName);
        clickSave();
    }
}
