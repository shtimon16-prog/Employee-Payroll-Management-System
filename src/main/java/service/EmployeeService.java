package service;

import dao.EmployeeDAO;
import exception.EmployeeNotFoundException;
import model.Employee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeService {
    private final EmployeeDAO employeeDAO = new EmployeeDAO();

    public void add(Employee employee) throws SQLException {
        employeeDAO.addEmployee(employee);
    }

    public List<Employee> getAll() throws SQLException {
        return employeeDAO.getAllEmployees();
    }

    public Employee getById(int id) throws SQLException, EmployeeNotFoundException {
        Employee employee = employeeDAO.getEmployeeById(id);
        if (employee == null) {
            throw new EmployeeNotFoundException(id);
        }
        return employee;
    }

    public List<Employee> search(String keyword) throws SQLException {
        return employeeDAO.searchEmployees(keyword);
    }

    public void update(Employee employee) throws SQLException, EmployeeNotFoundException {
        getById(employee.getEmployeeId());
        employeeDAO.updateEmployee(employee);
    }

    public void delete(int id) throws SQLException, EmployeeNotFoundException {
        getById(id);
        employeeDAO.deleteEmployee(id);
    }

    public List<Employee> sortBySalaryDescending() throws SQLException {
        List<Employee> employees = new ArrayList<>(getAll());
        employees.sort(Comparator.comparingDouble(Employee::getBaseSalary).reversed());
        return employees;
    }

    public Map<String, Double> departmentSalarySummary() throws SQLException {
        Map<String, Double> summary = new HashMap<>();

        for (Employee employee : getAll()) {
            summary.merge(employee.getDepartment(), employee.getBaseSalary(), Double::sum);
        }

        return summary;
    }
}
