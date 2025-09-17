package com.jaydeep.learn.streams;

import java.util.Arrays;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Question6 {
    // Tutorial: https://youtu.be/F1wB6DjspUc?si=55_pB9Is71wPy6NS
    public static void main(String[] args) {
        // Given a sentence, find the words with a specified number of vowels
        String sentence = "Betty Botter bought some butter but she said the butter was bitter so Betty Botter had to buy another butter";
        int input = 3;
        System.out.println(
                Arrays.stream(sentence.split("\\W+")).filter(word -> word.replaceAll("[^aeiouAEIOU]", "").length() >= input).toList()
        );
    }
}
