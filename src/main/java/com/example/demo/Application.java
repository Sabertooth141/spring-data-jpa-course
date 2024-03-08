package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepo studentRepo) {
        return args -> {
            Student maria = new Student("Maria", "Jones", "maria.jones@gmail.com", 21);
            Student ahmed = new Student("Ahmed", "Ali", "ahmed.ali@gmail.com", 21);

            System.out.println("Adding Maria and Ahmed");
            studentRepo.saveAll(List.of(maria, ahmed));

            System.out.print("Number of students: ");
            System.out.println(studentRepo.count());

            studentRepo.findById(2L).ifPresentOrElse(System.out::println, () -> {
                System.out.println("Student with ID 2 not found");
            });
            studentRepo.findById(3L).ifPresentOrElse(System.out::println, () -> {
                System.out.println("Student with ID 3 not found");
            });

            System.out.println("Select all students");
            List<Student> students = studentRepo.findAll();
            students.forEach(System.out::println);

            System.out.println("delete Maria");
            studentRepo.deleteById(1L);

            System.out.print("Number of students: ");
            System.out.println(studentRepo.count());
        };

    }

}
