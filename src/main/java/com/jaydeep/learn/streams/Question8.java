package com.jaydeep.learn.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Question8 {
    // Tutorial: https://youtu.be/y3h47AqCMIc?si=9qtXbYaxN3i-wAhi
    public static void main(String[] args) {
        // Given a word, find the occurrence of each character
        String input = "unconscientious";

        System.out.println(
                Arrays.stream(input.split("")).collect(Collectors.toMap(key->key, value->1, (Integer oldVal, Integer newVal) -> oldVal+1))
        );

        System.out.println(
                Arrays.stream(input.split("")).collect(Collectors.groupingBy(key->key, Collectors.counting()))
        );
    }
}
