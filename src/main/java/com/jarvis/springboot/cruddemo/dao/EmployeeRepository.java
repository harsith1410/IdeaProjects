package com.jarvis.springboot.cruddemo.dao;

import com.jarvis.springboot.cruddemo.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
