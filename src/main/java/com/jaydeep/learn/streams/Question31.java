package com.jaydeep.learn.streams;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Question31 {
    // Tutorial: https://youtu.be/x_TAb_coNyw?si=AzXF8OAKNY4n-iao

    public static void main(String[] args) {
        // Calculate the average of all the numbers.
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9);

        System.out.println(
                list.stream().mapToInt(i->i).average().orElse(0.0)
        );
    }
}
