import { test as base } from '@playwright/test';
import { LoginPage } from '../pages/LoginPage';
import { DashboardPage } from '../pages/DashboardPage';
import { RecruitmentPage } from '../pages/recruitment/RecruitmentPage';
import { AddCandidatePage } from '../pages/recruitment/AddCandidatePage';

type MyFixtures = {
  loginPage: LoginPage;
  dashboardPage: DashboardPage;
  recruitmentPage: RecruitmentPage;
  addCandidatePage: AddCandidatePage;
};

export const test = base.extend<MyFixtures>({
  loginPage: async ({ page }, use) => {
    await use(new LoginPage(page));
  },
  dashboardPage: async ({ page }, use) => {
    await use(new DashboardPage(page));
  },
  recruitmentPage: async ({ page }, use) => {
    await use(new RecruitmentPage(page));
  },
  addCandidatePage: async ({ page }, use) => {
    await use(new AddCandidatePage(page));
  },
});
export { expect } from '@playwright/test';
