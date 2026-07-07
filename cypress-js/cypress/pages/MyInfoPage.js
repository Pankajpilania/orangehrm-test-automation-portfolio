class MyInfoPage {
  visit() {
    cy.visit('/pim/viewPersonalDetails/empNumber/7'); 
  }

  navigateViaMenu() {
    cy.get('a[href*="/viewMyDetails"]').click();
  }

  getFirstNameInput() {
    return cy.get('input[name="firstName"]');
  }

  getLastNameInput() {
    return cy.get('input[name="lastName"]');
  }

  savePersonalDetails() {
    cy.get('.orangehrm-horizontal-padding > .oxd-form > .oxd-form-actions > .oxd-button').click();
  }
  
  getSuccessToast() {
    return cy.contains('Successfully Updated');
  }
}

export default new MyInfoPage();
