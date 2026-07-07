import { Page, Locator } from '@playwright/test';
import { BasePage } from '../base/BasePage';

export class AddCandidatePage extends BasePage {
  readonly firstNameInput: Locator;
  readonly lastNameInput: Locator;
  readonly emailInput: Locator;
  readonly saveButton: Locator;
  readonly successToast: Locator;

  constructor(page: Page) {
    super(page);
    this.firstNameInput = page.locator('input[name="firstName"]');
    this.lastNameInput = page.locator('input[name="lastName"]');
    this.emailInput = page.locator('input[placeholder="Type here"]').first();
    this.saveButton = page.getByRole('button', { name: 'Save' });
    this.successToast = page.locator('.oxd-toast-content--success');
  }

  async fillCandidateDetails(firstName: string, lastName: string, email: string) {
    await this.firstNameInput.fill(firstName);
    await this.lastNameInput.fill(lastName);
    await this.emailInput.fill(email);
  }

  async saveCandidate() {
    await this.saveButton.click();
  }
}
