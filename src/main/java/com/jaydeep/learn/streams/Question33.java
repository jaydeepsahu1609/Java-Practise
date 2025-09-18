package com.jaydeep.learn.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Question33 {
    // Tutorial: https://youtu.be/6tAXf0xMHPI?si=PMLiyxByENYKyHrI

    private static final class Employee {
        private String name;
        private String emailId;

        Employee(String name, String emailId) {
            this.name = name;
            this.emailId = emailId;
        }

        public String getName() {
            return name;
        }

        public String getEmailId() {
            return emailId;
        }

        @Override
        public String toString() {
            return this.getName() + "|" + this.getEmailId();
        }
    }

    public static void main(String[] args) {
        // Find the occurrence of domains using Java streams
        List<Employee> employees = Arrays.asList(
                new Employee("Jaydeep", "jaydeep@gmail.com"),
                new Employee("Jaydeep", "jaydeep@outlook.com"),
                new Employee("Harsh", "harsh@gmail.com"),
                new Employee("Prabhav", "prabhav@gmail.com")
        );

        System.out.println(
                employees.stream().collect(Collectors.groupingBy(emp -> emp.getEmailId().split("@")[1], Collectors.counting()))
        );
    }
}
