package com.jaydeep.learn.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Question36 {
    // Tutorial: https://youtu.be/GUT_zfnUXrY?si=2TmRKZA5tMgWswGj

    private static final class Person {
        private final String name;
        private final int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return this.name;
        }

        public int getAge() {
            return this.age;
        }

        @Override
        public String toString() {
            return this.getName() + ":" + this.getAge();
        }
    }

    public static void main(String[] args) {
        // Transform Person object stream into a single string
        List<Person> persons = new ArrayList<>(
                Arrays.asList(
                        new Person("Jaydeep", 23),
                        new Person("Palak", 24),
                        new Person("Prabhav", 25)
                )
        );

        System.out.println(
                persons.stream().map(Person::toString).collect(Collectors.joining("|"))
        );
    }
}
