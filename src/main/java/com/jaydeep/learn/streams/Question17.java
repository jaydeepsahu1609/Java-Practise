package com.jaydeep.learn.streams;

import java.util.stream.IntStream;

public class Question17 {
    // Tutorial: https://youtu.be/gXDwSeYQi9o?si=3XS8X_EUangNiALO

    public static void main(String[] args) {
        // Write a stream program to multiply alternative numbers in an array
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8}; // 105

        System.out.println(
                IntStream.range(0, arr.length).boxed()
                        .filter(i -> i % 2 == 0)
                        .map(i -> arr[i])
                        .reduce(1, (oldVal, newVal) -> oldVal * newVal)
        );
    }
}
