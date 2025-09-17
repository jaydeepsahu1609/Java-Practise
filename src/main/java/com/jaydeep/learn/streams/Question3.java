package com.jaydeep.learn.streams;

import java.util.*;
import java.util.stream.Collectors;

public class Question3 {
    // Tutorial: https://youtu.be/lrguO_nZJyg?si=npoykn1BAyIeaBPk

    public static void main(String[] args) {
        // Find the word that has the second highest length
        List<String> input = Arrays.asList("jaydeep", "sahu", "palak", "pal");

        // NOTE: assuming all words are unique, else use distinct()

        // if lengths are unique
        System.out.println(input.stream().sorted(Comparator.comparingInt(String::length).reversed()).limit(2).toList().get(1));
        System.out.println(input.stream().sorted(Comparator.comparingInt(String::length).reversed()).limit(2).skip(1).toList().get(0));
        System.out.println(input.stream().sorted(Comparator.comparingInt(String::length).reversed()).limit(2).skip(1).findFirst().orElse("Not found"));

        input = Arrays.asList("jaydeep", "prabhav", "palak", "harsh");

        // multiple names with max length
        System.out.println(input.stream().sorted(Comparator.comparingInt(String::length).reversed())
                .collect(Collectors.toMap(
                        String::length,             // keyMapper
                        value -> value,             // valueMapper
                        (String first, String second) -> first,   // mergeFunction -> keep the first
                        LinkedHashMap::new          // supplier -> preserve insertion order
                )).entrySet().stream().skip(1).findFirst().get().getValue());

    }
}
