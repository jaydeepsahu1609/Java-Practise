package com.jaydeep.learn.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Question58 {
    // Tutorial: https://youtu.be/Ut3qXTYXry8?si=jr2b7GLaS0UAhS_O
    private static class Employee {
        private final int id;
        private final String name;

        private Employee(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name + "|" + this.id;
        }
    }

    private static class EmployeeDTO {
        private final int id;
        private final String name;

        private EmployeeDTO(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "DTO|" + this.name + "|" + this.id;
        }
    }

    public static void main(String[] args) {
        // Transform one object into another . Transform Employee to EmployeeDTO
        List<Employee> employees = new ArrayList<>(Arrays.asList(
                new Employee(1, "Jaydeep"),
                new Employee(2, "Harsh"),
                new Employee(3, "Prabhav"))
        );

        employees.stream().map(emp -> new EmployeeDTO(emp.id, emp.name)).forEach(System.out::println);

    }

}
