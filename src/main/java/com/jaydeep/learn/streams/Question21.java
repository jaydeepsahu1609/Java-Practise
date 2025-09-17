package com.jaydeep.learn.streams;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Question21 {
    // Tutorial: https://youtu.be/S-mYu5Oc538?si=EtzLiqSZ_u6aHZYh

    public static void main(String[] args) {
        // Given the string[] group the strings based on the middle character
        String[] strings = new String[]{"nap", "sip", "smith", "awake", "bridge"};

        System.out.println(
                Arrays.stream(strings).collect(Collectors.groupingBy(string -> string.charAt(string.length() / 2), Collectors.toList())).values()
        );
    }
}
