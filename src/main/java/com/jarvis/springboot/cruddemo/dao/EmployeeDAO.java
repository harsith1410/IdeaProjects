package com.jarvis.springboot.cruddemo.dao;

import com.jarvis.springboot.cruddemo.Entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> findAll();

}
