package com.jaydeep.learn.streams;

import java.util.Arrays;
import java.util.List;

public class Question24 {
    // Tutorial: https://youtu.be/30MqIoBhwHs?si=FxigViqH7R6k-PMP

    public static void main(String[] args) {
        // Convert a list of integers to a list of their square
        List<Integer> brands = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        System.out.println(
                brands.stream().map(x -> x*x).toList()
        );
    }
}
