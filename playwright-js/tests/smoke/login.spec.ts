import { test, expect } from '../../fixtures';
import { testData } from '../../utils/testData';

// SMOKE-01 to SMOKE-05
test.describe('Login functionality', () => {
  test.use({ storageState: { cookies: [], origins: [] } }); // Clean state for login tests

  test.beforeEach(async ({ page }) => {
    await page.goto('/web/index.php/auth/login');
  });

  test('SMOKE-01: Successful login', async ({ loginPage, page }) => {
    await loginPage.login(testData.validUser.username, testData.validUser.password);
    await expect(page).toHaveURL(/.*dashboard/);
  });

  test('SMOKE-02: Invalid login', async ({ loginPage }) => {
    await loginPage.login(testData.invalidUser.username, testData.invalidUser.password);
    await expect(loginPage.errorMessage).toBeVisible();
  });

  test('SMOKE-03: Empty credentials', async ({ loginPage }) => {
    await loginPage.login('', '');
    const requiredMessages = loginPage.page.locator('.oxd-input-field-error-message');
    await expect(requiredMessages).toHaveCount(2);
  });

  test('SMOKE-04: Logout functionality', async ({ loginPage, dashboardPage, page }) => {
    await loginPage.login(testData.validUser.username, testData.validUser.password);
    await expect(page).toHaveURL(/.*dashboard/);
    await dashboardPage.logout();
    await expect(page).toHaveURL(/.*login/);
  });

  test('SMOKE-05: Page title verification', async ({ page }) => {
    await expect(page).toHaveTitle('OrangeHRM');
  });
});
