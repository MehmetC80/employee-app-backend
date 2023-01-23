package de.memozone.employeeapi.services;

import de.memozone.employeeapi.entity.EmployeeEntity;
import de.memozone.employeeapi.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Employee addEmployee(Employee employee);

    List<Employee> getEmployees();


    boolean deletedEmployee(Long id);


    Employee getSingleEmployeeById(Long id);

    Employee updateEmployee(Long id, Employee employee);
}
