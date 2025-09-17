package com.jaydeep.learn.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Question18 {
    // Tutorial: https://youtu.be/we0n-q6etAc?si=noL4FycLHFS7Byj3

    public static void main(String[] args) {
        // Write a program to multiply 1st and last element, 2nd and 2nd last element etc.
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}; // [9, 16, 21, 24, 5]

        List<Integer> result = new ArrayList<>(IntStream.range(0, arr.length/2).boxed()
                .map(i -> arr[i] * arr[arr.length - i - 1])
                .toList());

        if (arr.length % 2 == 1)
            result.add(arr[arr.length / 2]);

        System.out.println(result);
    }
}
