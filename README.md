# Employee Payroll Management System

A terminal-based Java application for managing employee records and monthly payroll.

## Features
- Add, view, search, update and delete employees
- Store employee data in MySQL using JDBC
- ArrayList and HashMap Collections Framework usage
- Sort employees by salary
- Department salary report
- Payroll calculation
- Payslip generation
- Custom EmployeeNotFoundException
- Input validation
- PreparedStatement for database queries
- Try-with-resources for JDBC resources

## Technologies
- Java JDK 11+
- MySQL
- JDBC
- Maven

## Project Structure
- `model` - Employee and Payroll models
- `dao` - Database operations
- `service` - Business logic and collections
- `exception` - Custom exceptions
- `util` - Database connection
- `Main.java` - Console menu

## Database Setup
1. Install MySQL.
2. Open `database/schema.sql`.
3. Run the SQL script.
4. Open `src/main/java/util/DatabaseConnection.java`.
5. Change the database username and password for your local MySQL setup.

For a real submission, move credentials to environment variables or a local config file and do not commit them to GitHub.

## Run
Using Maven:
```bash
mvn clean compile
mvn exec:java -Dexec.mainClass=Main
```

Or run `Main.java` from IntelliJ IDEA after Maven dependencies are downloaded.

## Example Payroll
Net Salary = Base Salary + Allowance - Deduction

Example:
Base Salary = 50,000
Allowance = 5,000
Deduction = 2,000
Net Salary = 53,000

## Assignment Requirements Covered
- OOP and encapsulation
- Interface and polymorphism
- ArrayList and HashMap
- Sorting with Comparator
- JDBC connectivity
- CRUD operations
- PreparedStatement
- Custom exception
- Menu-driven console interface
- Input validation
