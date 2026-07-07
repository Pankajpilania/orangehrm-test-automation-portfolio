package com.orangehrm.tests.pim;

import com.orangehrm.base.BaseTest;
import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.pages.pim.AddEmployeePage;
import com.orangehrm.pages.pim.PimPage;
import com.orangehrm.utils.ConfigReader;
import com.orangehrm.utils.ExcelDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PimTest extends BaseTest {

    @Test(groups = {"pim", "smoke", "regression"}, dataProvider = "employeeData", dataProviderClass = ExcelDataProvider.class, description = "PIM-01 to PIM-09: Add employee tests")
    public void testAddEmployee(String firstName, String lastName) {
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = loginPage.login(ConfigReader.getProperty("admin.username"), ConfigReader.getProperty("admin.password"));
        
        Assert.assertTrue(dashboardPage.isDashboardDisplayed(), "Dashboard should be displayed after login");
        
        dashboardPage.clickPimMenu();
        PimPage pimPage = new PimPage(driver);
        AddEmployeePage addEmployeePage = pimPage.clickAddEmployee();
        
        addEmployeePage.addEmployee(firstName, lastName);
        
        // Assertions would go here to verify employee was added
    }
}
