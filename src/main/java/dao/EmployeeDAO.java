package dao;

import model.Employee;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    public void addEmployee(Employee employee) throws SQLException {
        String sql = "INSERT INTO employees (employee_id, name, department, position, base_salary) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, employee.getEmployeeId());
            ps.setString(2, employee.getName());
            ps.setString(3, employee.getDepartment());
            ps.setString(4, employee.getPosition());
            ps.setDouble(5, employee.getBaseSalary());
            ps.executeUpdate();
        }
    }

    public List<Employee> getAllEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees ORDER BY employee_id";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                employees.add(new Employee(
                    rs.getInt("employee_id"),
                    rs.getString("name"),
                    rs.getString("department"),
                    rs.getString("position"),
                    rs.getDouble("base_salary")
                ));
            }
        }
        return employees;
    }

    public Employee getEmployeeById(int id) throws SQLException {
        String sql = "SELECT * FROM employees WHERE employee_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Employee(
                        rs.getInt("employee_id"),
                        rs.getString("name"),
                        rs.getString("department"),
                        rs.getString("position"),
                        rs.getDouble("base_salary")
                    );
                }
            }
        }
        return null;
    }

    public List<Employee> searchEmployees(String keyword) throws SQLException {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees WHERE name LIKE ? OR department LIKE ? OR position LIKE ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            String search = "%" + keyword + "%";
            ps.setString(1, search);
            ps.setString(2, search);
            ps.setString(3, search);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    employees.add(new Employee(
                        rs.getInt("employee_id"),
                        rs.getString("name"),
                        rs.getString("department"),
                        rs.getString("position"),
                        rs.getDouble("base_salary")
                    ));
                }
            }
        }
        return employees;
    }

    public void updateEmployee(Employee employee) throws SQLException {
        String sql = "UPDATE employees SET name=?, department=?, position=?, base_salary=? WHERE employee_id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, employee.getName());
            ps.setString(2, employee.getDepartment());
            ps.setString(3, employee.getPosition());
            ps.setDouble(4, employee.getBaseSalary());
            ps.setInt(5, employee.getEmployeeId());

            ps.executeUpdate();
        }
    }

    public void deleteEmployee(int id) throws SQLException {
        String sql = "DELETE FROM employees WHERE employee_id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
