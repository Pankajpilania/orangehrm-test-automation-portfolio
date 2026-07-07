package com.orangehrm.tests.leave;

import com.orangehrm.base.BaseTest;
import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.pages.leave.ApplyLeavePage;
import com.orangehrm.pages.leave.LeavePage;
import com.orangehrm.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LeaveTest extends BaseTest {

    @Test(groups = {"leave", "regression"}, description = "LEA-01 to LEA-05: Leave application tests")
    public void testApplyLeave() {
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = loginPage.login(ConfigReader.getProperty("admin.username"), ConfigReader.getProperty("admin.password"));
        
        Assert.assertTrue(dashboardPage.isDashboardDisplayed());
        
        dashboardPage.clickLeaveMenu();
        LeavePage leavePage = new LeavePage(driver);
        ApplyLeavePage applyLeavePage = leavePage.clickApplyLeave();
        
        // Interaction logic
    }
}
