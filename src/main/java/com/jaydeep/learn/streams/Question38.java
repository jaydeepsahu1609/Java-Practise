package com.jaydeep.learn.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Question38 {
    // Tutorial: https://youtu.be/orIu9YRggRA?si=ImKLiJsqImKkHjuQ

    private static final class Person {
        private final String name;
        private final String city;

        Person(String name, String age) {
            this.name = name;
            this.city = age;
        }

        public String getName() {
            return this.name;
        }

        public String getCity() {
            return this.city;
        }

        @Override
        public String toString() {
            return this.getName() + ":" + this.getCity();
        }
    }

    public static void main(String[] args) {
        // Convert a list to a map
        List<Person> persons = new ArrayList<>(
                Arrays.asList(
                        new Person("Jaydeep", "indore"),
                        new Person("Prabhav", "mandsaur"),
                        new Person("Mayank", "indore")
                )
        );

        System.out.println(
                persons.stream().collect(Collectors.groupingBy(Person::getCity, Collectors.toList()))
        );

        System.out.println(
                persons.stream().collect(Collectors.toMap(Person::getCity, x -> new ArrayList<>(List.of(x)), (List<Person> a, List<Person> b) -> {
                    a.addAll(b);
                    return a;
                }))
        );
    }
}
