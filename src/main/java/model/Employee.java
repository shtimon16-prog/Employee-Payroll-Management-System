package model;

public class Employee {
    private int employeeId;
    private String name;
    private String department;
    private String position;
    private double baseSalary;

    public Employee(int employeeId, String name, String department,
                    String position, double baseSalary) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.position = position;
        this.baseSalary = baseSalary;
    }

    public int getEmployeeId() { return employeeId; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public String getPosition() { return position; }
    public double getBaseSalary() { return baseSalary; }

    public void setName(String name) { this.name = name; }
    public void setDepartment(String department) { this.department = department; }
    public void setPosition(String position) { this.position = position; }
    public void setBaseSalary(double baseSalary) { this.baseSalary = baseSalary; }

    @Override
    public String toString() {
        return String.format(
            "ID: %d | Name: %s | Department: %s | Position: %s | Salary: %.2f",
            employeeId, name, department, position, baseSalary
        );
    }
}
