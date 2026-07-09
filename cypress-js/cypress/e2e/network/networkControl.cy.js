describe('Network Control (NET-01 to NET-04)', () => {
  beforeEach(() => {
    cy.fixture('credentials').then((creds) => {
      cy.login(creds.admin.username, creds.admin.password);
    });
  });

  it('NET-01: Intercept Dashboard API', () => {
    cy.intercept('GET', '**/api/v2/dashboard/**').as('getDashboardApi');
    cy.visit('/dashboard/index');
    cy.wait('@getDashboardApi').its('response.statusCode').should('eq', 200);
  });

  it('NET-02: Mock API Response for Widgets', () => {
    cy.intercept('GET', '**/api/v2/dashboard/employees/action-summary', {
      statusCode: 200,
      body: {
        "data": [
            { "id": 1, "group": "Leave Requests to Approve", "value": 10 }
        ]
      }
    }).as('getActionSummary');
    cy.visit('/dashboard/index');
    cy.wait('@getActionSummary');
    cy.contains('10').should('exist'); // Use 'exist' or wait for the mock to render
  });

  it('NET-03: Throttle Network Speed', () => {
    cy.intercept('GET', '**/api/v2/dashboard/employees/locations', (req) => {
      req.on('response', (res) => {
        res.setDelay(2000); // simulate 2s delay
      });
    }).as('getLocationsDelay');
    cy.visit('/dashboard/index');
    cy.wait('@getLocationsDelay');
  });

  it('NET-04: Force API Error (500)', () => {
    cy.intercept('GET', '**/api/v2/dashboard/employees/subunit', {
      statusCode: 500,
      body: { error: 'Internal Server Error' }
    }).as('get500Error');
    cy.visit('/dashboard/index');
    cy.wait('@get500Error');
  });
});
