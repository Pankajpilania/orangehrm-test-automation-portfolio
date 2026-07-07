describe('JWT Security (NET-05, NET-06)', () => {
  
  it('NET-05: API Login returning token', () => {
    cy.fixture('credentials').then((creds) => {
      cy.request({
        method: 'POST',
        url: '/auth/validate',
        body: {
          username: creds.admin.username,
          password: creds.admin.password
        },
        failOnStatusCode: false
      }).then((resp) => {
        // Validation for auth API
        expect([200, 302]).to.include(resp.status);
      });
    });
  });

  it('NET-06: API Request without Token/Session', () => {
    cy.request({
      method: 'GET',
      url: '/api/v2/dashboard/employees/time-at-work',
      failOnStatusCode: false
    }).then((resp) => {
      // Should fail due to unauthenticated
      expect(resp.status).to.not.eq(200);
    });
  });
});
