package model;

public class EmployeePayroll implements Payable {
    @Override
    public double calculatePay(double baseSalary, double allowance, double deduction) {
        return baseSalary + allowance - deduction;
    }
}
