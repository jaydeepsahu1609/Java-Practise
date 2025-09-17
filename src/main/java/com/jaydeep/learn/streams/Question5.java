package com.jaydeep.learn.streams;

import java.util.Arrays;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Question5 {
    // Tutorial: https://youtu.be/cU6nA1cnZ1g?si=XJyGqzcTVKummJkn
    public static void main(String[] args) {
        // Given a sentence find the occurrence of each word
        String sentence = "Betty Botter bought some butter but she said the butter was bitter so Betty Botter had to buy another butter";

        System.out.println(
                Arrays.stream(sentence.split(" ")).collect(Collectors.groupingBy(
                                word -> word,
                                Collectors.counting()
                        )
                )
        );

        sentence = "Betty Botter bought some butter but she said the butter was bitter so betty botter had to buy another butter";
        // Case-Insensitive: use toLowerCase
        // "\\s+": Split on the basis of one or more white spaces
        System.out.println(
                Arrays.stream(sentence.split("\\s+"))
                        .collect(Collectors.groupingBy(String::toLowerCase, Collectors.counting()))
        );

        sentence = "Betty Botter bought some butter but she said the butter was bitter, so betty botter had to buy another butter.";
        // Split punctuations
        // "\\W+" : Split on the basis of one or more non-word characters (spaces, special characters etc.)
        System.out.println(
                Arrays.stream(sentence.split("\\W+"))
                        .collect(Collectors.groupingBy(String::toLowerCase, Collectors.counting()))
        );
    }
}
