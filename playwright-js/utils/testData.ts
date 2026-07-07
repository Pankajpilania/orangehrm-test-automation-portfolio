export const testData = {
  validUser: {
    username: process.env.ADMIN_USERNAME || 'Admin',
    password: process.env.ADMIN_PASSWORD || 'admin123'
  },
  invalidUser: {
    username: 'invalidUser',
    password: 'invalidPassword'
  },
  candidate: {
    firstName: 'John',
    lastName: 'Doe',
    email: 'john.doe@example.com'
  }
};
