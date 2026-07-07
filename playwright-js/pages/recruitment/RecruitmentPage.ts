import { Page, Locator } from '@playwright/test';
import { BasePage } from '../base/BasePage';

export class RecruitmentPage extends BasePage {
  readonly addCandidateButton: Locator;
  readonly candidateRecords: Locator;

  constructor(page: Page) {
    super(page);
    this.addCandidateButton = page.getByRole('button', { name: 'Add' });
    this.candidateRecords = page.locator('.oxd-table-row');
  }

  async clickAddCandidate() {
    await this.addCandidateButton.click();
  }
}
