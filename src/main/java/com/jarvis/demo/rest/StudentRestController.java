package com.jarvis.demo.rest;

import com.jarvis.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> students;

    @PostConstruct
    public void addData(){
        students = new ArrayList<>();
        students.add(new Student("John", "Smith"));
        students.add(new Student("Jane", "Taylor"));
        students.add(new Student("Jack", "Johnson"));
        students.add(new Student("Alice", "Brown"));
        students.add(new Student("Ethan", "Anderson"));
    }


    @GetMapping("/student")
    public List<Student> getStudents() {
        return students;
    }

//    @GetMapping("/student/{StudentID}")
//    public Student getStudent(@PathVariable int StudentID) {
//        return students.get(StudentID-1);
//    }




}
