package com.jaydeep.learn.streams;

import java.util.Arrays;
import java.util.List;

public class Question29 {
    // Tutorial: https://youtu.be/B2pTn870Li8?si=TrbKL6TLOL2Ibefj

    public static void main(String[] args) {
        // Find and print strings containing only digits.
        List<String> list = Arrays.asList("a1b2c3", "1501", "1609", "V2607");

        System.out.println(
                list.stream().filter(x -> x.matches("\\d+")).toList()
        );
    }
}
