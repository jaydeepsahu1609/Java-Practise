package com.jaydeep.learn.streams;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Question10 {
    // Tutorial: https://youtu.be/Cprnsyy7v1k?si=WWUsAEUW1aDCeVst
    public static void main(String[] args) {
        // Given an array, find the sum of unique elements
        int[] arr = new int[]{1, 1, 2, 2, 3, 4};
        System.out.println(Arrays.toString(arr));

        System.out.println(
                IntStream.of(arr).distinct().sum()
        );

        System.out.println(
                Arrays.stream(arr).distinct().sum()
        );

    }
}
