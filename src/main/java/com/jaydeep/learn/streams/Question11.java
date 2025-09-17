package com.jaydeep.learn.streams;

import java.util.Arrays;

public class Question11 {
    // Tutorial: https://youtu.be/__iFugXuwsY?si=yu-J09PPvY9L84kC

    public static void main(String[] args) {
        // Given a string, find the first non-repeated character
        String input = "iiiiiiiiiiiiiiiiabc";

        System.out.println(
                Arrays.stream(input.split("")).distinct().filter(c -> input.indexOf(c) == input.lastIndexOf(c)).findFirst().orElse("not exist")
        );

    }
}
