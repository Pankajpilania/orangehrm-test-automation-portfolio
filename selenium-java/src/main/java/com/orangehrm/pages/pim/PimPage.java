package com.orangehrm.pages.pim;

import com.orangehrm.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PimPage extends BasePage {

    @FindBy(xpath = "//a[text()='Add Employee']")
    private WebElement addEmployeeLink;

    public PimPage(WebDriver driver) {
        super(driver);
    }

    public AddEmployeePage clickAddEmployee() {
        click(addEmployeeLink);
        return new AddEmployeePage(driver);
    }
}
