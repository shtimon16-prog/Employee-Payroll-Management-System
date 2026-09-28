import exception.EmployeeNotFoundException;
import model.Employee;
import model.Payroll;
import service.EmployeeService;
import service.PayrollService;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final EmployeeService employeeService = new EmployeeService();
    private static final PayrollService payrollService = new PayrollService();

    public static void main(String[] args) {
        System.out.println("======================================");
        System.out.println("     EMPLOYEE PAYROLL MANAGEMENT");
        System.out.println("======================================");

        boolean running = true;

        while (running) {
            showMenu();
            int choice = readInt("Enter your choice: ");

            try {
                switch (choice) {
                    case 1:
                        addEmployee();
                        break;

                    case 2:
                        viewEmployees();
                        break;

                    case 3:
                        searchEmployee();
                        break;

                    case 4:
                        updateEmployee();
                        break;

                    case 5:
                        deleteEmployee();
                        break;

                    case 6:
                        calculatePayroll();
                        break;

                    case 7:
                        departmentReport();
                        break;

                    case 8:
                        sortedSalaryReport();
                        break;

                    case 9:
                        running = false;
                        System.out.println("Thank you for using the system.");
                        break;

                    default:
                        System.out.println("Invalid choice. Please choose 1-9.");
                        break;
                }
            } catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());
                System.out.println("Check that MySQL is running and your database settings are correct.");
            } catch (EmployeeNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private static void showMenu() {
        System.out.println();
        System.out.println("------------- MENU -------------");
        System.out.println("1. Add Employee");
        System.out.println("2. View All Employees");
        System.out.println("3. Search Employee");
        System.out.println("4. Update Employee");
        System.out.println("5. Delete Employee");
        System.out.println("6. Calculate & Save Payroll");
        System.out.println("7. Department Salary Report");
        System.out.println("8. Salary Ranking");
        System.out.println("9. Exit");
        System.out.println("--------------------------------");
    }

    private static void addEmployee() throws SQLException {
        int id = readInt("Employee ID: ");

        if (employeeService.search(String.valueOf(id)).stream()
                .anyMatch(e -> e.getEmployeeId() == id)) {
            System.out.println("Employee ID already exists.");
            return;
        }

        String name = readNonEmpty("Name: ");
        String department = readNonEmpty("Department: ");
        String position = readNonEmpty("Position: ");
        double salary = readNonNegativeDouble("Base Salary: ");

        employeeService.add(new Employee(id, name, department, position, salary));
        System.out.println("Employee added successfully.");
    }

    private static void viewEmployees() throws SQLException {
        List<Employee> employees = employeeService.getAll();

        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }

        System.out.println("\nEmployee List");
        System.out.println("-------------");
        employees.forEach(System.out::println);
    }

    private static void searchEmployee() throws SQLException {
        String keyword = readNonEmpty("Enter ID, name, department or position: ");
        List<Employee> employees = employeeService.search(keyword);

        if (employees.isEmpty()) {
            System.out.println("No matching employees found.");
        } else {
            employees.forEach(System.out::println);
        }
    }

    private static void updateEmployee()
            throws SQLException, EmployeeNotFoundException {

        int id = readInt("Enter employee ID to update: ");
        Employee employee = employeeService.getById(id);

        System.out.println("Current: " + employee);

        String name = readNonEmpty("New name: ");
        String department = readNonEmpty("New department: ");
        String position = readNonEmpty("New position: ");
        double salary = readNonNegativeDouble("New base salary: ");

        employee.setName(name);
        employee.setDepartment(department);
        employee.setPosition(position);
        employee.setBaseSalary(salary);

        employeeService.update(employee);
        System.out.println("Employee updated successfully.");
    }

    private static void deleteEmployee()
            throws SQLException, EmployeeNotFoundException {

        int id = readInt("Enter employee ID to delete: ");
        Employee employee = employeeService.getById(id);

        System.out.println("Employee: " + employee);
        String confirm = readNonEmpty("Type YES to delete: ");

        if (confirm.equalsIgnoreCase("YES")) {
            employeeService.delete(id);
            System.out.println("Employee deleted successfully.");
        } else {
            System.out.println("Delete cancelled.");
        }
    }

    private static void calculatePayroll()
            throws SQLException, EmployeeNotFoundException {

        int id = readInt("Employee ID: ");
        Employee employee = employeeService.getById(id);

        String month = readNonEmpty("Payroll month (e.g. September 2026): ");
        double allowance = readNonNegativeDouble("Allowance: ");
        double deduction = readNonNegativeDouble("Deduction: ");

        Payroll payroll = payrollService.createPayroll(
                employee, month, allowance, deduction
        );

        System.out.println("\n========== PAYSLIP ==========");
        System.out.println("Employee ID : " + employee.getEmployeeId());
        System.out.println("Name        : " + employee.getName());
        System.out.println("Department  : " + employee.getDepartment());
        System.out.println("Position    : " + employee.getPosition());
        System.out.println("Month       : " + payroll.getPayrollMonth());
        System.out.printf("Base Salary : %.2f%n", employee.getBaseSalary());
        System.out.printf("Allowance   : %.2f%n", payroll.getAllowance());
        System.out.printf("Deduction   : %.2f%n", payroll.getDeduction());
        System.out.printf("Net Salary  : %.2f%n", payroll.getNetSalary());
        System.out.println("==============================");

        payrollService.savePayroll(payroll);
        System.out.println("Payroll saved successfully.");
    }

    private static void departmentReport() throws SQLException {
        Map<String, Double> summary = employeeService.departmentSalarySummary();

        System.out.println("\nDepartment Salary Summary");
        System.out.println("--------------------------");

        if (summary.isEmpty()) {
            System.out.println("No employee data available.");
            return;
        }

        summary.forEach((department, total) ->
                System.out.printf("%-20s %.2f%n", department, total));
    }

    private static void sortedSalaryReport() throws SQLException {
        List<Employee> employees = employeeService.sortBySalaryDescending();

        System.out.println("\nSalary Ranking");
        System.out.println("--------------");

        int rank = 1;
        for (Employee employee : employees) {
            System.out.printf("%d. %s%n", rank++, employee);
        }
    }

    private static int readInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private static double readNonNegativeDouble(String message) {
        while (true) {
            try {
                System.out.print(message);
                double value = Double.parseDouble(scanner.nextLine().trim());

                if (value < 0) {
                    System.out.println("Value cannot be negative.");
                } else {
                    return value;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static String readNonEmpty(String message) {
        while (true) {
            System.out.print(message);
            String value = scanner.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }

            System.out.println("Input cannot be empty.");
        }
    }
}
