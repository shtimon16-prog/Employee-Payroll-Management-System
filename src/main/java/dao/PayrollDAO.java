package dao;

import model.Payroll;
import util.DatabaseConnection;

import java.sql.*;

public class PayrollDAO {

    public void savePayroll(Payroll payroll) throws SQLException {
        String sql = "INSERT INTO payroll (employee_id, payroll_month, allowance, deduction, net_salary) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, payroll.getEmployeeId());
            ps.setString(2, payroll.getPayrollMonth());
            ps.setDouble(3, payroll.getAllowance());
            ps.setDouble(4, payroll.getDeduction());
            ps.setDouble(5, payroll.getNetSalary());
            ps.executeUpdate();
        }
    }
}
