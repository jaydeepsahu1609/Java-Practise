package com.jaydeep.learn.streams;

import java.util.Arrays;
import java.util.List;

public class Question27 {
    // Tutorial: https://youtu.be/Xq7xxurWfYo?si=0xzM44xNAyoGOoyD

    public static void main(String[] args) {
        // Find the kth smallest element in a list of integers.
        List<Integer> list = Arrays.asList(1, 3, 5, 7, 9, 11, 8, 6, 2, 4);
        int input = 3;

        System.out.println(
                list.stream().sorted().toList().get(input - 1)
        );
    }
}
