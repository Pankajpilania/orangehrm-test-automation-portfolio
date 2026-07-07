import loginPage from '../../pages/LoginPage';

describe('Smoke Test - Login (SMOKE-01 to SMOKE-05)', () => {
  beforeEach(() => {
    cy.fixture('credentials').as('creds');
  });

  it('SMOKE-01: Valid Login', function() {
    loginPage.login(this.creds.admin.username, this.creds.admin.password);
    cy.url().should('include', '/dashboard');
  });

  it('SMOKE-02: Invalid Login', function() {
    loginPage.login(this.creds.invalid.username, this.creds.invalid.password);
    loginPage.getErrorMessage().should('contain', 'Invalid credentials');
  });

  it('SMOKE-03: Empty Username', function() {
    loginPage.visit();
    loginPage.fillPassword(this.creds.admin.password);
    loginPage.submit();
    cy.contains('Required').should('be.visible');
  });

  it('SMOKE-04: Empty Password', function() {
    loginPage.visit();
    loginPage.fillUsername(this.creds.admin.username);
    loginPage.submit();
    cy.contains('Required').should('be.visible');
  });

  it('SMOKE-05: Logout', function() {
    loginPage.login(this.creds.admin.username, this.creds.admin.password);
    cy.get('.oxd-userdropdown-name').click();
    cy.contains('Logout').click();
    cy.url().should('include', '/auth/login');
  });
});
