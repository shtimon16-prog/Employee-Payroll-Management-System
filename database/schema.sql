CREATE DATABASE IF NOT EXISTS employee_payroll;
USE employee_payroll;

CREATE TABLE IF NOT EXISTS employees (
    employee_id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    department VARCHAR(100) NOT NULL,
    position VARCHAR(100) NOT NULL,
    base_salary DECIMAL(12,2) NOT NULL
);

CREATE TABLE IF NOT EXISTS payroll (
    payroll_id INT AUTO_INCREMENT PRIMARY KEY,
    employee_id INT NOT NULL,
    payroll_month VARCHAR(20) NOT NULL,
    allowance DECIMAL(12,2) NOT NULL,
    deduction DECIMAL(12,2) NOT NULL,
    net_salary DECIMAL(12,2) NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employees(employee_id)
        ON DELETE CASCADE
);

INSERT INTO employees (employee_id, name, department, position, base_salary)
VALUES
(101, 'Ram Sharma', 'IT', 'Software Developer', 50000.00),
(102, 'Sita Thapa', 'HR', 'HR Officer', 45000.00)
ON DUPLICATE KEY UPDATE employee_id = employee_id;
