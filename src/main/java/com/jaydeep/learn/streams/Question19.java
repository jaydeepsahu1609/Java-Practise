package com.jaydeep.learn.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Question19 {
    // Tutorial: https://youtu.be/NTGqtCExlLE?si=ulYYktr-6ZofIAMD

    public static void main(String[] args) {
        // Write a stream program to move all zero’s beginning of array
        int[] arr = new int[]{1, 2, 0, 4, 0, 6, 0, 0, 0};

        System.out.println(
                Arrays.stream(arr).boxed().collect(Collectors.groupingBy(x -> x != 0)).values().stream().flatMap(List::stream).toList()
        );

        // use sorting with custom comparator
        // if the number is 0
        //      then it should come first : return -1 (if a<b) or 1 (if b>0)
        // otherwise keep the order : return 0
        System.out.println(
                Arrays.stream(arr).boxed().sorted((a, b) -> a == 0 ? -1 : (b == 0 ? 1 : 0)).toList()
        );

    }
}
