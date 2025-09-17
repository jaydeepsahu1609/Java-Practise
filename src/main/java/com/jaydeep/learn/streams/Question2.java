package com.jaydeep.learn.streams;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Question2 {
    // Tutorial: https://youtu.be/6iyDi7q11Lk?si=qqhjPt8fX3GqPDNN

    public static void main(String[] args) {
        // Remove duplicates from the string and return in the same order
        String input = "mississippi";

        input.chars().distinct().mapToObj(ch -> (char)ch).forEach(System.out::print);
        System.out.println();

        System.out.println(Arrays.stream(input.split("")).distinct().collect(Collectors.joining()));
    }
}
