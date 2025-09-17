package com.jaydeep.learn.streams;

import java.util.Arrays;
import java.util.List;

public class Question23 {
    // Tutorial: https://youtu.be/40MZkFNG3KY?si=cSFTcZ9iNKJbbTcx

    public static void main(String[] args) {
        // Sort a list of strings in alphabetical order
        List<String> brands = Arrays.asList("JioMart", "DMart", "BigBazar", "RelianceFresh");

        System.out.println(
                brands.stream().sorted().toList()
        );
    }
}
