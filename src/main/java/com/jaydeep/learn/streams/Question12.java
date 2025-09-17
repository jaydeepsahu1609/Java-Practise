package com.jaydeep.learn.streams;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

public class Question12 {
    // Tutorial: https://youtu.be/JONnNOkB3mw?si=LsArFncMp1PjK-7d

    public static void main(String[] args) {
        // Given a string, find the first repeated character
        String input = "abcdefgghi";

        System.out.println(
                Arrays.stream(input.split("")).distinct().filter(c -> input.indexOf(c) != input.lastIndexOf(c)).findFirst().orElse("not exist")
        );

        System.out.println(
                Arrays.stream(input.split("")).collect(
                        Collectors.groupingBy(k->k, LinkedHashMap::new, Collectors.counting()))
                        .entrySet().stream().filter(e -> e.getValue() > 1L).findFirst().get().getKey()
        );
    }
}
