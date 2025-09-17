package com.jaydeep.learn.streams;

import java.util.Arrays;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Question13 {
    // Tutorial: https://youtu.be/hKDmBzTzUbA?si=NNCtmNQMXjSQoYxM

    public static void main(String[] args) {
        // Given an array of integers, group the numbers by the range
        int[] arr = new int[]{11, 12, 21, 24, 25, 31, 39};

        // Collectors.groupingBy(classifier, downstream)
        System.out.println(
                Arrays.stream(arr).boxed().collect(Collectors.groupingBy(x -> x / 10 * 10, Collectors.toList()))
        );

        // Collectors.groupingBy(classifier, a supplier providing a new empty Map into which the results will be inserted, downstream)
        System.out.println(
                Arrays.stream(arr).boxed().collect(Collectors.groupingBy(x -> x / 10 * 10, TreeMap::new, Collectors.toList())).toString()
        );
    }
}
