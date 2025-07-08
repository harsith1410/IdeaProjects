package com.jarvis.springboot.cruddemo.rest;

import com.jarvis.springboot.cruddemo.Entity.Employee;
import com.jarvis.springboot.cruddemo.dao.EmployeeDAO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeDAO employeeDAO;

    public EmployeeRestController(EmployeeDAO employeeDAO) {
        employeeDAO = employeeDAO;
    }

    @GetMapping("/home")
    public List<Employee> getAllEmployees() {
        return employeeDAO.findAll();
    }

}
