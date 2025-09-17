package com.jaydeep.learn.streams;

import java.util.Arrays;
import java.util.List;

public class Question25 {
    // Tutorial: https://youtu.be/YX5s_aC8Q8I?si=4J6RSCXeT2I3XLTS

    public static void main(String[] args) {
        // Find and print the distinct odd numbers
        List<Integer> brands = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 13, 13);

        System.out.println(
                brands.stream().filter(x -> x % 2 == 1).distinct().toList()
        );
    }
}
