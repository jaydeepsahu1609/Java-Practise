package com.jaydeep.learn.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Question54 {
    // Tutorial: https://youtu.be/XFQiMQdJqb8?si=23daIIJzJJHPgnBJ
    private static final class Person {
        private final String name;
        private final String department;
        private final Integer salary;

        Person(String name, String dep, Integer salary) {
            this.name = name;
            this.department = dep;
            this.salary = salary;
        }

        public String getName() {
            return this.name;
        }

        public String getDepartment() {
            return this.department;
        }

        public Integer getSalary() {
            return this.salary;
        }

        @Override
        public String toString() {
            return this.getName() + ":" + this.getDepartment() + ":" + this.getSalary();
        }
    }

    public static void main(String[] args) {
        // Find the average salary from each department
        List<Person> persons = new ArrayList<>(
                Arrays.asList(
                        new Person("Jaydeep", "SDE", 10000),
                        new Person("Saurabh", "SDE", 12000),
                        new Person("Harsh", "SDE", 15000),
                        new Person("Prabhav", "DS", 11000),
                        new Person("Sharad", "DS", 12000),
                        new Person("Palak", "ASE", 11000)
                )
        );

        System.out.println(
                persons.stream().collect(Collectors.groupingBy(Person::getDepartment, Collectors.toList()))
                        .entrySet().stream()
                        .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().stream().mapToInt(Person::getSalary).average().orElse(0.0)))
        );

        // Optimal way
        System.out.println(
                persons.stream().collect(Collectors.groupingBy(Person::getDepartment, Collectors.averagingDouble(Person::getSalary)))
        );

    }
}
