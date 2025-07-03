package com.jarvis.cruddemo;

import com.jarvis.cruddemo.dao.StudentDAO;
import com.jarvis.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {

		return runner ->{
			//CreateStudent(studentDAO);
			//CreateMultiple(studentDAO);
//			readStudent(studentDAO);
			UpdateRecord(studentDAO);
		};
	}

	private void UpdateRecord(StudentDAO studentDAO) {

		Student student = studentDAO.get(1);
		System.out.println(student);
		System.out.println("Updating record...");
		studentDAO.update(student,"Scooby");
		System.out.println("Record updated!");
		System.out.println(student);


	}

	private void readStudent(StudentDAO studentDAO) {
		Student student = new Student();
		student=studentDAO.get(1);
		System.out.println(student);
	}

	private void CreateMultiple(StudentDAO studentDAO) {

		System.out.println("Create multiple students...");

		Student student1 = new Student("Phil","Foden","fden47@gmail.com");
		Student student2 = new Student("Chocolate","Dairymilk","chocomilk@gmail.com");
		Student student3 = new Student("Mark","Martin","martimark@gmail.com");

		System.out.println("Saving multiple students...");
		studentDAO.save(student1);
		studentDAO.save(student2);
		studentDAO.save(student3);

		System.out.println("Student saved, Student ID: " + student1.getId()+" "+student1.getFirstName());
		System.out.println("Student saved, Student ID: " + student2.getId()+" "+student2.getFirstName());
		System.out.println("Student saved, Student ID: " + student3.getId()+" "+student3.getFirstName());


	}

	private void CreateStudent(StudentDAO studentDAO) {
		System.out.println("Creating student");
		Student student = new Student("Paul","Stick","paul@gmail.com");

		System.out.println("Saving student");
		studentDAO.save(student);

		System.out.println("Student saved, Student ID: " + student.getId());
	}


}
