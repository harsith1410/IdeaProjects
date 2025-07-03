package com.jarvis.cruddemo.dao;

import com.jarvis.cruddemo.entity.Student;

public interface StudentDAO {

    public void save(Student student);

    public Student get(int ID);

    public void update(Student student,String First_name);

}
