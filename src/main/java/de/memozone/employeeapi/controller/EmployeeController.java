package de.memozone.employeeapi.controller;


import de.memozone.employeeapi.model.Employee;
import de.memozone.employeeapi.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @PostMapping("/employees")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {

        Employee empl = employeeService.addEmployee(employee);

        return new ResponseEntity<Employee>(empl, HttpStatus.CREATED);

    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> allEmplyees = employeeService.getEmployees();

        return new ResponseEntity<>(allEmplyees, HttpStatus.OK);

    }


    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable("id") Long id) {

        boolean deleted = false;

        deleted = employeeService.deletedEmployee(id);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", deleted);
        return ResponseEntity.ok(response);

    }


    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) {
        Employee employee = null;
        employee = employeeService.getSingleEmployeeById(id);
        return ResponseEntity.ok(employee);

    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee) {


        employee=employeeService.updateEmployee(id,employee);

        return ResponseEntity.ok(employee);

    }


}
