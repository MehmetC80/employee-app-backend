package de.memozone.employeeapi.services;


import de.memozone.employeeapi.entity.EmployeeEntity;
import de.memozone.employeeapi.model.Employee;
import de.memozone.employeeapi.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public Employee addEmployee(Employee employee) {
        EmployeeEntity employeeEntity = new EmployeeEntity();

        BeanUtils.copyProperties(employee, employeeEntity);
        employeeRepository.save(employeeEntity);
        return employee;
    }

    @Override
    public List<Employee> getEmployees() {

        List<EmployeeEntity> employeeEntityList = employeeRepository.findAll();

        List<Employee> employees = employeeEntityList
                .stream()
                .map(emp -> new Employee
                        (emp.getId(),
                                emp.getFirstname(),
                                emp.getLastname(),
                                emp.getEmailId()))
                .collect(Collectors.toList());

        return employees;

    }

    @Override
    public boolean deletedEmployee(Long id) {

        EmployeeEntity employee = employeeRepository.findById(id).get();
        employeeRepository.delete(employee);
        return true;
    }

    @Override
    public Employee getSingleEmployeeById(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeEntity, employee);
        return employee;
    }


}
