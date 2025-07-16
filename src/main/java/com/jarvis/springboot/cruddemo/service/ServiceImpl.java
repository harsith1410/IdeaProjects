package com.jarvis.springboot.cruddemo.service;

import com.jarvis.springboot.cruddemo.Entity.Employee;
import com.jarvis.springboot.cruddemo.dao.EmployeeDAO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceImpl implements EmployeeService {

    private EmployeeDAO employeeDAO;

    @Autowired
    public ServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }

    @Override
    public Employee findById(int id) {
        return employeeDAO.findById(id);
    }

    @Override
    @Transactional
    public Employee save(Employee employee) {
        return employeeDAO.save(employee);
    }

    @Override
    @Transactional
    public void deletebyID(int id) {
        employeeDAO.deletebyID(id);
    }
}
