import { test as setup } from '@playwright/test';
import { LoginPage } from '../pages/LoginPage';
import * as fs from 'fs';
import * as path from 'path';
import * as dotenv from 'dotenv';
dotenv.config();

const authFile = 'playwright/.auth/user.json';

setup('authenticate', async ({ page }) => {
  // Ensure the directory exists before Playwright tries to write to it.
  // Without this, CI fails with ENOENT because the directory is gitignored
  // and therefore never present in a fresh checkout.
  fs.mkdirSync(path.dirname(authFile), { recursive: true });

  const loginPage = new LoginPage(page);
  await page.goto('/web/index.php/auth/login');
  await loginPage.login(
    process.env.ADMIN_USERNAME || 'Admin',
    process.env.ADMIN_PASSWORD || 'admin123'
  );
  await page.waitForURL(/.*dashboard/, { timeout: 60_000 });
  await page.context().storageState({ path: authFile });
});
