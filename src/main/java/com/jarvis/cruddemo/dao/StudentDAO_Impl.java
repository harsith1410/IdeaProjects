package com.jarvis.cruddemo.dao;

import com.jarvis.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDAO_Impl implements StudentDAO {

    private EntityManager em;

    @Autowired
    public StudentDAO_Impl(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public void save(Student student) {
        em.persist(student);
    }

    @Override
    public Student get(int id) {
        return em.find(Student.class, id);
    }

    @Override
    @Transactional
    public void update(Student student,String First_Name) {

        student.setFirstName(First_Name);

        em.merge(student);
    }
}
