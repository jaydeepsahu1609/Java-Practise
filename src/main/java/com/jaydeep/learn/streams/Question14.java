package com.jaydeep.learn.streams;

import java.util.Arrays;

public class Question14 {
    // Tutorial: https://youtu.be/J0zYoORqqwA?si=m-ygJ0agWq70Fl50

    public static void main(String[] args) {
        // Find the products of the first two elements in an array.
        int[] nums = new int[]{9, 8, 7, 6, 5, 4};

        System.out.println(
                Arrays.stream(nums).limit(2).reduce(1, (oldVal, newVal) -> oldVal * newVal)
        );


    }
}
