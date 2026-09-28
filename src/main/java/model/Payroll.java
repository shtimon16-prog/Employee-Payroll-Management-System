package model;

public class Payroll {
    private int payrollId;
    private int employeeId;
    private String payrollMonth;
    private double allowance;
    private double deduction;
    private double netSalary;

    public Payroll(int employeeId, String payrollMonth,
                   double allowance, double deduction) {
        this.employeeId = employeeId;
        this.payrollMonth = payrollMonth;
        this.allowance = allowance;
        this.deduction = deduction;
    }

    public Payroll(int payrollId, int employeeId, String payrollMonth,
                   double allowance, double deduction, double netSalary) {
        this.payrollId = payrollId;
        this.employeeId = employeeId;
        this.payrollMonth = payrollMonth;
        this.allowance = allowance;
        this.deduction = deduction;
        this.netSalary = netSalary;
    }

    public int getPayrollId() { return payrollId; }
    public int getEmployeeId() { return employeeId; }
    public String getPayrollMonth() { return payrollMonth; }
    public double getAllowance() { return allowance; }
    public double getDeduction() { return deduction; }
    public double getNetSalary() { return netSalary; }

    public void calculateNetSalary(double baseSalary) {
        netSalary = baseSalary + allowance - deduction;
    }

    @Override
    public String toString() {
        return String.format(
            "Payroll ID: %d | Employee ID: %d | Month: %s | Allowance: %.2f | Deduction: %.2f | Net Salary: %.2f",
            payrollId, employeeId, payrollMonth, allowance, deduction, netSalary
        );
    }
}
