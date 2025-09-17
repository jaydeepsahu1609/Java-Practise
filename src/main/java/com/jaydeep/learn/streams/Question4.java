package com.jaydeep.learn.streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Question4 {
    // Tutorial: https://youtu.be/qpF-olV37c0?si=qyef4klwe0LqYbxX

    public static void main(String[] args) {
        // Find the second-highest length
        List<String> input = Arrays.asList("jaydeep", "sahu", "palak", "pal");

        System.out.println(input.stream().map(String::length).distinct().sorted(Comparator.reverseOrder()).skip(1).findFirst().orElse(-1));
    }
}
