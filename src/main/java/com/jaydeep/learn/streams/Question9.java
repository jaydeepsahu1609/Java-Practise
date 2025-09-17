package com.jaydeep.learn.streams;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Question9 {
    // Tutorial: https://youtu.be/FNvZgvyWqZY?si=UROhmm3ljI8c_eTB
    public static void main(String[] args) {
        // Arrange the numbers in Descending/Ascending Order
        int[] arr = new int[]{1, 3, 5, 7, 9, 2, 4, 6, 8};
        System.out.println(Arrays.toString(arr));

        System.out.println(
                Arrays.toString(Arrays.stream(arr).sorted().toArray())
        );

        // for reverse order boxing is required; to utilize Collections class
        System.out.println(
                Arrays.toString(Arrays.stream(arr).boxed().sorted(Collections.reverseOrder()).toArray())
        );

    }
}
