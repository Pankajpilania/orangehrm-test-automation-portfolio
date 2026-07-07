import myInfoPage from '../../pages/MyInfoPage';

describe('My Info Module (MYI-01 to MYI-05)', () => {
  beforeEach(() => {
    cy.fixture('credentials').then((creds) => {
      cy.login(creds.admin.username, creds.admin.password);
    });
    myInfoPage.navigateViaMenu();
  });

  it('MYI-01: View Personal Details', () => {
    cy.url().should('include', 'viewPersonalDetails');
    myInfoPage.getFirstNameInput().should('be.visible');
  });

  it('MYI-02: Edit First Name', () => {
    myInfoPage.getFirstNameInput().clear().type('UpdatedName');
    myInfoPage.savePersonalDetails();
    myInfoPage.getSuccessToast().should('be.visible');
  });

  it('MYI-03: Edit Last Name', () => {
    myInfoPage.getLastNameInput().clear().type('UpdatedLastName');
    myInfoPage.savePersonalDetails();
    myInfoPage.getSuccessToast().should('be.visible');
  });

  it('MYI-04: Upload Avatar', () => {
    cy.get('.employee-image').click();
    cy.contains('Profile Picture').should('be.visible');
  });

  it('MYI-05: Add Custom Field', () => {
    cy.contains('a', 'Custom Fields').click({ force: true });
  });
});
