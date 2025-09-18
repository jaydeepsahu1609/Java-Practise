package com.jaydeep.learn.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Question53 {
    // Tutorial: https://youtu.be/cSEnwlyoyWY?si=GYz6LJG8MSPxscBI
    private static final class Person {
        private final String name;
        private final String department;

        Person(String name, String dep) {
            this.name = name;
            this.department = dep;
        }

        public String getName() {
            return this.name;
        }

        public String getDepartment() {
            return this.department;
        }

        @Override
        public String toString() {
            return this.getName() + ":" + this.getDepartment();
        }
    }

    public static void main(String[] args) {
        // Find the department with maximum people
        List<Person> persons = new ArrayList<>(
                Arrays.asList(
                        new Person("Jaydeep", "SDE"),
                        new Person("Saurabh", "SDE"),
                        new Person("Harsh", "SDE"),
                        new Person("Prabhav", "DS"),
                        new Person("Palak", "ASE")
                )
        );

        persons.stream().collect(Collectors.groupingBy(Person::getDepartment, Collectors.counting()))
                .entrySet().stream()
                .max((a, b) -> Math.toIntExact(a.getValue() - b.getValue())).ifPresent(x -> System.out.println(x.getKey()));

        // using comparingByValue
        persons.stream().collect(Collectors.groupingBy(Person::getDepartment, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue()).ifPresent(x -> System.out.println(x.getKey()));
    }
}
