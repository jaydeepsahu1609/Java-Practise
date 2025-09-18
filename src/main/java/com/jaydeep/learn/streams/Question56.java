package com.jaydeep.learn.streams;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public class Question56 {
    // Tutorial: https://youtu.be/7UVQmv2xZNk?si=ZZa9Luy5JqXRa1F-

    public static void main(String[] args) {
        // Return character with the maximum frequency
        String input = "javadeveloper";

        System.out.println(
                input.chars().mapToObj(x -> (char) x).sorted(Collections.reverseOrder())
                        .collect(Collectors.groupingBy(x->x, Collectors.counting()))
                        .entrySet().stream()
                        .max(Map.Entry.comparingByValue()).get().getKey()
        );
    }


}
