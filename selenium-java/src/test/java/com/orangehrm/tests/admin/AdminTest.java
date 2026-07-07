package com.orangehrm.tests.admin;

import com.orangehrm.base.BaseTest;
import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.pages.admin.AddUserPage;
import com.orangehrm.pages.admin.AdminPage;
import com.orangehrm.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AdminTest extends BaseTest {

    @Test(groups = {"admin", "regression"}, description = "ADM-01 to ADM-05: Admin user management")
    public void testAddAdminUser() {
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = loginPage.login(ConfigReader.getProperty("admin.username"), ConfigReader.getProperty("admin.password"));
        
        Assert.assertTrue(dashboardPage.isDashboardDisplayed());
        
        dashboardPage.clickAdminMenu();
        AdminPage adminPage = new AdminPage(driver);
        AddUserPage addUserPage = adminPage.clickAddUser();
        
        addUserPage.enterUsername("TestUser123");
        // Form filling logic
    }
}
