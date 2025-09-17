package com.jaydeep.learn.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Question26 {
    // Tutorial: https://youtu.be/52Sc3qXkdGE?si=yK2u2r7RCMHw0OrH

    public static void main(String[] args) {
        // Find the union of two lists of integers
        List<Integer> list1 = Arrays.asList(1, 3, 5, 7, 9, 11);
        List<Integer> list2 = Arrays.asList(2, 4, 6, 8, 10, 11, 12);

        System.out.println(
                Stream.concat(list1.stream(), list2.stream()).distinct().toList()
        );
    }
}
