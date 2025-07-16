package com.jarvis.springboot.cruddemo.rest;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jarvis.springboot.cruddemo.dao.EmployeeDAO;
import com.jarvis.springboot.cruddemo.Entity.Employee;
import com.jarvis.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;
    private ObjectMapper objectMapper;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService, ObjectMapper objectMapper) {
        this.employeeService = employeeService;
        this.objectMapper = objectMapper;
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

    @PatchMapping("/employees/{emp_id}")
    public Employee updateEmployee(@PathVariable int emp_id, @RequestBody Map<String, Object> theEmployee) {

        Employee emp = employeeService.findById(emp_id);

        if (emp == null) {
            throw new RuntimeException("Employee not found - " + emp_id);
        } else if (theEmployee.containsKey("id")) {
            throw new RuntimeException("Employee id connot be changed - " + theEmployee.get("id"));
        }
        Employee patchedEmp = apply(theEmployee,emp);

        Employee dbEmp = employeeService.save(patchedEmp);

        return dbEmp;
    }

    private Employee apply(Map<String, Object> theEmployee, Employee emp) {

        ObjectNode employeeNode = objectMapper.convertValue(emp, ObjectNode.class);

        ObjectNode patchNode = objectMapper.convertValue(theEmployee, ObjectNode.class);

        employeeNode.setAll(patchNode);

        return objectMapper.convertValue(employeeNode,Employee.class);

    }

    @DeleteMapping("/employees/{emp_id}")
    public void deleteEmployee(@PathVariable int emp_id) {
        Employee theEmp = employeeService.findById(emp_id);
        if (theEmp == null) {
            throw new RuntimeException("Employee not found - " + emp_id);
        }
        employeeService.deletebyID(emp_id);
    }

}








