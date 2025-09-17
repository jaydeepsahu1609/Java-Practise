package com.jaydeep.learn.streams;

import java.util.Arrays;
import java.util.List;

public class Question22 {
    // Tutorial: https://youtu.be/Pxo9Mo2NS-Y?si=EwC-YZs4UZL0nxjE

    public static void main(String[] args) {
        // Find the sum of all the elements in a list.
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        System.out.println(
                list.stream().reduce(0, Integer::sum)
        );

        System.out.println(
                list.stream().mapToInt(x -> x).sum()
        );

    }
}
