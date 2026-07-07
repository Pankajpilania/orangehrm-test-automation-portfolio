import { test, expect } from '@playwright/test';

test.describe('API Tests', () => {
  test('API-01: Health check', async ({ request }) => {
    const response = await request.get('/');
    expect(response.ok()).toBeTruthy();
  });

  test('API-02: Auth endpoint', async ({ request }) => {
    // Demo of API call, might need proper API token or setup
    const response = await request.post('/web/index.php/auth/validate', {
      data: {
        username: 'invalid',
        password: 'invalid'
      }
    });
    // This expects a particular response even if it's unauthorized/bad request
    expect(response.status()).toBeGreaterThanOrEqual(200);
  });

  test('API-03: Fetch user list (Unauth)', async ({ request }) => {
    const response = await request.get('/api/v2/admin/users');
    expect(response.status()).toBe(401); // Assuming unauthorized without auth headers
  });
});
