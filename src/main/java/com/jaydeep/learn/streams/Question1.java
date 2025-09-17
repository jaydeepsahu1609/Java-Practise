package com.jaydeep.learn.streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Question1 {
    // Tutorial: https://youtu.be/xifdVmHOAWc?si=thNEhB0dLErPaF6A

    public static void main(String[] args) {
        // Given a sentence, find the word with highest length.
        String sentence = "With great power comes great responsibilities";
        String answer = Arrays.stream(sentence.split(" ")).reduce("", (a, b) -> {
            if (a.length() > b.length())
                return a;
            else if (a.length() < b.length())
                return b;
            return a;
        });
        System.out.println(answer);

        answer = Arrays.stream(sentence.split(" ")).max(Comparator.comparing(String::length)).orElse("None");
        System.out.println(answer);

        // multiple words with max length
        sentence = "With great power comes great responsibilities aaaaaaaaaaaaaaaa";
        System.out.println(Arrays.stream(sentence.split(" "))
                .collect(Collectors.groupingBy(String::length))
                .entrySet().stream()
                .max(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .orElse(List.of()));
    }
}
