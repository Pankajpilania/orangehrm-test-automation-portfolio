import { test as setup, expect } from '@playwright/test';
import { LoginPage } from '../pages/LoginPage';
import * as dotenv from 'dotenv';
dotenv.config();

const authFile = 'playwright/.auth/user.json';

setup('authenticate', async ({ page }) => {
  const loginPage = new LoginPage(page);
  await page.goto('/web/index.php/auth/login');
  await loginPage.login(
    process.env.ADMIN_USERNAME || 'Admin', 
    process.env.ADMIN_PASSWORD || 'admin123'
  );
  await page.waitForURL(/.*dashboard/);
  await page.context().storageState({ path: authFile });
});
