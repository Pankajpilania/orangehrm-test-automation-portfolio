class TimePage {
  navigateViaMenu() {
    cy.get('a[href*="/viewTimeModule"]').click();
  }

  getEmployeeNameInput() {
    return cy.get('input[placeholder="Type for hints..."]');
  }

  viewTimesheet() {
    cy.get('button[type="submit"]').click();
  }

  getPunchInButton() {
    return cy.contains('button', 'Punch In');
  }
}

export default new TimePage();
