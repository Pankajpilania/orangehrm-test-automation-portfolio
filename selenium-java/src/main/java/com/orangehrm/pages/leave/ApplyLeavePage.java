package com.orangehrm.pages.leave;

import com.orangehrm.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ApplyLeavePage extends BasePage {

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement applyButton;

    public ApplyLeavePage(WebDriver driver) {
        super(driver);
    }

    public void clickApply() {
        click(applyButton);
    }
}
