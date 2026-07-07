import { Page, Locator } from '@playwright/test';
import { BasePage } from './base/BasePage';

export class DashboardPage extends BasePage {
  readonly userDropdown: Locator;
  readonly logoutLink: Locator;
  readonly widgetHeaders: Locator;

  constructor(page: Page) {
    super(page);
    this.userDropdown = page.locator('.oxd-userdropdown-name');
    this.logoutLink = page.getByRole('menuitem', { name: 'Logout' });
    this.widgetHeaders = page.locator('.orangehrm-dashboard-widget-name');
  }

  async logout() {
    await this.userDropdown.click();
    await this.logoutLink.click();
  }
}
