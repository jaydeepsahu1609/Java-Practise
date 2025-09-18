package com.jaydeep.learn.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Question57 {
    // Tutorial: https://youtu.be/a6IkOJ4wTOc?si=uCgHWVLmzafYXY3a

    public static void main(String[] args) {
        // Convert list of string into map of String and its equivalent length
        List<String> fruits = Arrays.asList("banana", "orange", "peach");

        System.out.println(
                fruits.stream().collect(Collectors.toMap(x->x, String::length))
        );
    }


}
