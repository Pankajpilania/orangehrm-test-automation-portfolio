import { test, expect } from '../../fixtures';

test.describe('Dashboard Functionality', () => {
  test.beforeEach(async ({ page }) => {
    await page.goto('/web/index.php/dashboard/index');
  });

  test('DASH-01: Dashboard page loads', async ({ page }) => {
    await expect(page).toHaveURL(/.*dashboard/);
  });

  test('DASH-02: Widgets are displayed', async ({ dashboardPage }) => {
    await expect(dashboardPage.widgetHeaders.first()).toBeVisible();
  });

  test('DASH-03: User menu is accessible', async ({ dashboardPage }) => {
    await expect(dashboardPage.userDropdown).toBeVisible();
  });

  test('DASH-04: Navigation sidebar present', async ({ page }) => {
    const sidebar = page.locator('.oxd-sidepanel');
    await expect(sidebar).toBeVisible();
  });
});
