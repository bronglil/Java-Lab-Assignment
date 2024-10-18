package com.emse.spring.hello;

import com.emse.spring.hello.DatabaseConnection;
import com.emse.spring.hello.StudentRepository;
import com.emse.spring.hello.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.util.Scanner;

@SpringBootApplication
public class HelloApplication implements CommandLineRunner {

	@Autowired
	private DatabaseConnection databaseConnection;

	public static void main(String[] args) {
		SpringApplication.run(HelloApplication.class, args);
	}

	@Override
	public void run(String... args) {
		Connection connection = databaseConnection.getConnection();

		if (connection != null) {
			StudentRepository studentRepository = new StudentRepository(connection);
			StudentService studentService = new StudentService(studentRepository);

			Scanner scanner = new Scanner(System.in);
			System.out.println("What would you like to do? (insert/list)");
			String action = scanner.nextLine();

			if ("insert".equalsIgnoreCase(action)) {
				// Insert new student
				System.out.println("Enter student name:");
				String name = scanner.nextLine();
				System.out.println("Enter student age:");
				int age = scanner.nextInt();
				scanner.nextLine();
				System.out.println("Enter student email:");
				String email = scanner.nextLine();

				studentService.createStudent(name, age, email);
			} else if ("list".equalsIgnoreCase(action)) {
				System.out.println("Listing all students:");
				studentService.listStudents();
			} else {
				System.out.println("Invalid action. Please choose 'insert' or 'list'.");
			}
		}
	}
}
