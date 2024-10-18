package com.emse.spring.hello;

import com.emse.spring.hello.Student;
import com.emse.spring.hello.StudentRepository;

import java.sql.SQLException;
import java.util.List;

public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void createStudent(String name, int age, String email) {
        Student student = new Student(null, name, age, email);
        try {
            studentRepository.insertStudent(student);
            System.out.println("Student inserted successfully: " + name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listStudents() {
        try {
            List<Student> students = studentRepository.listStudents();
            for (Student student : students) {
                System.out.println("ID: " + student.getId() + ", Name: " + student.getName() +
                        ", Age: " + student.getAge() + ", Email: " + student.getEmail());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
