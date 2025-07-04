package com.jarvis.demo.rest;

import com.jarvis.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/student/{StudentID}")
    public Student getStudent(@PathVariable int StudentID) {

        if(StudentID > students.size() || StudentID < 0){
            throw new StudentNotFoundException("Student ID not found - " + StudentID);
        }

        return students.get(StudentID-1);
    }

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException ex){

        StudentErrorResponse response = new StudentErrorResponse();

        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setMessage(ex.getMessage());
        response.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception ex){

        StudentErrorResponse response = new StudentErrorResponse();

        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage(ex.getMessage());
        response.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }

}
