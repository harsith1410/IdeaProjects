package com.jarvis.springboot.cruddemo.service;

import com.jarvis.springboot.cruddemo.Entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(int id);

    Employee save(Employee employee);

    void deletebyID (int id);

}
