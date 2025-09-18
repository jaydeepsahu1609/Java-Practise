package com.jaydeep.learn.streams;
import com.jaydeep.learn.streams.Question38;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Question40 {
    // Tutorial: https://youtu.be/Q3T7GEvkGsU?si=x8VPkZ5EniTkkai0
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
        // Can we reuse the same Stream?
        List<Person> persons = new ArrayList<>(
                Arrays.asList(
                        new Person("Jaydeep", "indore"),
                        new Person("Prabhav", "mandsaur"),
                        new Person("Mayank", "indore")
                )
        );

        Stream<Person> personStream = persons.stream();

        // consume
        personStream.forEach(person -> person.toString());

        // // consume again // IllegalStateException: stream has already been operated upon or closed
        // System.out.println(personStream.count());

        // Use supplier to reinitiate the stream, i.e, create a fresh stream under the hood
        Supplier<Stream<Person>> personSupplier = persons::stream;

        personSupplier.get().forEach(System.out::println);

        System.out.println(personSupplier.get().count());

    }
}
