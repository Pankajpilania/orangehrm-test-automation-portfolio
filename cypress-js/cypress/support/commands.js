// cy.login using UI
Cypress.Commands.add('login', (username, password) => {
  cy.visit('/auth/login');
  cy.get('input[name="username"]').type(username);
  cy.get('input[name="password"]').type(password);
  cy.get('button[type="submit"]').click();
  cy.url().should('include', '/dashboard');
});

// cy.apiLogin
Cypress.Commands.add('apiLogin', (username, password) => {
  cy.request({
    method: 'POST',
    url: '/auth/validate',
    body: {
      username: username,
      password: password
    },
    failOnStatusCode: false
  }).then((response) => {
    // Session cookies handled automatically by Cypress
  });
});

// cy.addEmployee
Cypress.Commands.add('addEmployee', (firstName, lastName, empId) => {
  cy.visit('/pim/addEmployee');
  cy.get('input[name="firstName"]').type(firstName);
  cy.get('input[name="lastName"]').type(lastName);
  cy.get('.oxd-grid-item > .oxd-input-group > :nth-child(2) > .oxd-input').clear().type(empId);
  cy.get('button[type="submit"]').click();
  cy.contains('Successfully Saved').should('be.visible');
});
