import { test, expect } from '../../fixtures';
import { testData } from '../../utils/testData';

test.describe('Recruitment Management', () => {
  test.beforeEach(async ({ page }) => {
    await page.goto('/web/index.php/recruitment/viewCandidates');
  });

  test('REC-01: Navigate to Recruitment Page', async ({ page }) => {
    await expect(page).toHaveURL(/.*recruitment.*/);
  });

  test('REC-02: Add new candidate', async ({ recruitmentPage, addCandidatePage }) => {
    await recruitmentPage.clickAddCandidate();
    await addCandidatePage.fillCandidateDetails(
      testData.candidate.firstName,
      testData.candidate.lastName,
      testData.candidate.email
    );
    await addCandidatePage.saveCandidate();
    await expect(addCandidatePage.successToast).toBeVisible({ timeout: 10000 });
  });

  test('REC-03: Candidate list displays items', async ({ recruitmentPage }) => {
    // Basic check for list presence
    await expect(recruitmentPage.candidateRecords.first()).toBeVisible();
  });

  test('REC-04: Validation for mandatory fields', async ({ recruitmentPage, addCandidatePage, page }) => {
    await recruitmentPage.clickAddCandidate();
    await addCandidatePage.saveCandidate();
    const requiredMessages = page.locator('.oxd-input-field-error-message');
    await expect(requiredMessages.first()).toBeVisible();
  });

  test('REC-05: Cancel adding candidate', async ({ recruitmentPage, page }) => {
    await recruitmentPage.clickAddCandidate();
    await page.getByRole('button', { name: 'Cancel' }).click();
    await expect(page).toHaveURL(/.*viewCandidates/);
  });

  test('REC-06: Search candidate', async ({ page }) => {
    // Assuming search input exists
    const searchInput = page.getByPlaceholder('Type for hints...');
    await expect(searchInput.first()).toBeVisible();
  });
});
