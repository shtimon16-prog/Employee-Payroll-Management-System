# Testing Checklist

## Employee CRUD
- Add an employee with valid information.
- View all employees.
- Search by name, department or position.
- Update an existing employee.
- Delete an existing employee.
- Try invalid employee IDs.

## Payroll
- Enter a valid employee ID.
- Enter allowance and deduction.
- Verify:
  Net Salary = Base Salary + Allowance - Deduction
- Confirm payroll is saved in MySQL.

## Collections
- View salary ranking.
- View department salary summary.

## Input Validation
- Enter text when an integer is required.
- Enter a negative salary.
- Enter empty text fields.

## Database
- Stop MySQL and verify that the application reports a database error instead of crashing.

## Additional Testing
- Verified employee data persistence in MySQL.
- Verified database connection through JDBC.

## Database Testing
- Verify employee records are stored in MySQL.
- Verify employee records can be retrieved using JDBC.
- Verify invalid employee IDs are handled correctly.
