package com.jarvis.springboot.cruddemo.dao;

import com.jarvis.springboot.cruddemo.Entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmpDAOImpl implements EmployeeDAO {

    // define field for entitymanager
    private EntityManager em;


    // set up constructor injection
    @Autowired
    public EmpDAOImpl(EntityManager theEntityManager) {
        em = theEntityManager;
    }


    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> theQuery = em.createQuery("from Employee", Employee.class);
        List<Employee> employees = theQuery.getResultList();
        return employees;
    }

    @Override
    public Employee findById(int id) {

        Employee theEmployee = em.find(Employee.class, id);
        return theEmployee;
    }

    @Override
    public Employee save(Employee employee) {
        Employee theEmployee = em.merge(employee);
        return theEmployee;
    }

    @Override
    public void deletebyID(int id) {
        em.remove(em.find(Employee.class, id));
    }
}