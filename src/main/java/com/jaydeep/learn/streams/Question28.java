package com.jaydeep.learn.streams;

import java.util.Arrays;
import java.util.List;

public class Question28 {
    // Tutorial: https://youtu.be/McDZ8-KNaFw?si=v-drbnDY4wW0LNux

    public static void main(String[] args) {
        // Remove all non-numeric characters from a list
        List<String> list = Arrays.asList("a1b2c3", "j16p15v26", "jaydeep", "p15alak");

        System.out.println(
                list.stream().map(x -> x.replaceAll("\\D", "")).filter(x -> !x.isEmpty()).toList()
        );
    }
}
