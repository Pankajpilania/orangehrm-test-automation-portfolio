import timePage from '../../pages/TimePage';

describe('Time and Attendance (TIME-01 to TIME-04)', () => {
  beforeEach(() => {
    cy.fixture('credentials').then((creds) => {
      cy.login(creds.admin.username, creds.admin.password);
    });
    timePage.navigateViaMenu();
  });

  it('TIME-01: View Timesheets', () => {
    cy.url().should('include', 'viewEmployeeTimesheet');
    cy.contains('Timesheets').should('be.visible');
  });

  it('TIME-02: Search Timesheet by Employee Name', () => {
    timePage.getEmployeeNameInput().type('John');
    cy.contains('John', { timeout: 10000 }).click();
    timePage.viewTimesheet();
  });

  it('TIME-03: Punch In', () => {
    cy.contains('a', 'Attendance').click();
    cy.contains('li', 'Punch In/Out').click();
    cy.get('.oxd-text--h6').then(($header) => {
      if ($header.text().includes('Punch In')) {
        cy.get('button[type="submit"]').click();
        cy.contains('Successfully Saved').should('be.visible');
      }
    });
  });

  it('TIME-04: Punch Out', () => {
    cy.contains('a', 'Attendance').click();
    cy.contains('li', 'Punch In/Out').click();
    cy.get('.oxd-text--h6').then(($header) => {
      if ($header.text().includes('Punch Out')) {
        cy.get('button[type="submit"]').click();
        cy.contains('Successfully Saved').should('be.visible');
      }
    });
  });
});
