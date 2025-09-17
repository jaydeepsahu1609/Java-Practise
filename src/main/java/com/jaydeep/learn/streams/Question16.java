package com.jaydeep.learn.streams;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Question16 {
    // Tutorial: https://youtu.be/pWRNbNpmguA?si=n-pIhxchgRP96pMY

    public static void main(String[] args) {
        // Group /Pair anagrams from a list of Strings

        String[] strings = new String[]{"neo", "nap", "net", "pan", "ten", "one", "zoo"};

        System.out.println(
                Arrays.stream(strings).collect(Collectors.groupingBy(x -> Arrays.stream(x.toLowerCase().split("")).sorted().collect(Collectors.toList()))).values()
        );

    }
}
