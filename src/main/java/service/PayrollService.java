package service;

import dao.PayrollDAO;
import model.Employee;
import model.EmployeePayroll;
import model.Payroll;

import java.sql.SQLException;

public class PayrollService {
    private final PayrollDAO payrollDAO = new PayrollDAO();
    private final EmployeePayroll calculator = new EmployeePayroll();

    public Payroll createPayroll(Employee employee, String month,
                                 double allowance, double deduction) {
        Payroll payroll = new Payroll(employee.getEmployeeId(), month, allowance, deduction);

        double netSalary = calculator.calculatePay(
            employee.getBaseSalary(), allowance, deduction
        );

        payroll.calculateNetSalary(employee.getBaseSalary());

        // The calculation above uses the same formula through the model.
        // This assignment keeps the calculator interface as an example of polymorphism.
        if (Math.abs(netSalary - payroll.getNetSalary()) > 0.001) {
            throw new IllegalStateException("Payroll calculation mismatch.");
        }

        return payroll;
    }

    public void savePayroll(Payroll payroll) throws SQLException {
        payrollDAO.savePayroll(payroll);
    }
}
