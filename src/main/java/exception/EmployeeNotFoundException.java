package exception;

public class EmployeeNotFoundException extends Exception {
    public EmployeeNotFoundException(int employeeId) {
        super("Employee with ID " + employeeId + " was not found.");
    }
}
