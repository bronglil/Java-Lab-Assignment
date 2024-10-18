package com.emse.spring.hello;

import com.emse.spring.hello.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {

    private final Connection connection;

    public StudentRepository(Connection connection) {
        this.connection = connection;
    }

    public void insertStudent(Student student) throws SQLException {
        String sql = "INSERT INTO STUDENTS (NAME, AGE, EMAIL) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getAge());
            preparedStatement.setString(3, student.getEmail());
            preparedStatement.executeUpdate();
        }
    }

    public List<Student> listStudents() throws SQLException {
        String sql = "SELECT * FROM STUDENTS";
        List<Student> students = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Long id = resultSet.getLong("ID");
                String name = resultSet.getString("NAME");
                int age = resultSet.getInt("AGE");
                String email = resultSet.getString("EMAIL");
                students.add(new Student(id, name, age, email));
            }
        }
        return students;
    }
}
