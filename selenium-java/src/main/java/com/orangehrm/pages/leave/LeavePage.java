package com.orangehrm.pages.leave;

import com.orangehrm.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LeavePage extends BasePage {

    @FindBy(xpath = "//a[text()='Apply']")
    private WebElement applyLink;

    public LeavePage(WebDriver driver) {
        super(driver);
    }

    public ApplyLeavePage clickApplyLeave() {
        click(applyLink);
        return new ApplyLeavePage(driver);
    }
}
