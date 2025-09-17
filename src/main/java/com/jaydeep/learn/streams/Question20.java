package com.jaydeep.learn.streams;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Question20 {
    // Tutorial: https://youtu.be/cr-HrgYdfDk?si=8RnaiQRv5_8YnclC

    public static void main(String[] args) {
        // In a given array of integers, return true if it contains distinct values
        int[] arr = new int[]{1, 2, 0, 4, 5, 6, 7, 9};
        // int[] arr = new int[]{1, 2, 0, 4, 5, 6, 7, 9, 9};

        System.out.println(
                arr.length == Arrays.stream(arr).distinct().count()
        );

        System.out.println(
                Arrays.stream(arr).boxed().collect(Collectors.groupingBy(x -> x, Collectors.counting())).values().stream().noneMatch(x -> x > 1)
        );
    }
}
