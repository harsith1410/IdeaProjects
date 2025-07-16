package com.jarvis.springboot.cruddemo.rest;


import com.jarvis.springboot.cruddemo.dao.EmployeeDAO;
import com.jarvis.springboot.cruddemo.Entity.Employee;
import com.jarvis.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/employees/{emp_id}")
    public Employee findById(@PathVariable int emp_id) {
        Employee theEmp = employeeService.findById(emp_id);
        if (theEmp == null) {
            throw new RuntimeException("Employee not found - " + emp_id);
        }
        return theEmp;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee) {

        theEmployee.setId(0);
        Employee emp = employeeService.save(theEmployee);

        return emp;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee) {
        
        Employee emp = employeeService.save(theEmployee);
        return emp;
    }


}








