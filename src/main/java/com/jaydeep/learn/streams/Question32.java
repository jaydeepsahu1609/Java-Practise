package com.jaydeep.learn.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Question32 {
    // Tutorial: https://youtu.be/TjutfYwgb2w?si=fJoqUxCAsCLeK1hU

    public static void main(String[] args) {
        // Find the intersection of two lists using Java streams
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> list2 = Arrays.asList(4, 5, 6, 7, 8, 9);

        // Brute-force
        // merge the list and find the element with count=2
        System.out.println(
                Stream.concat(list1.stream(), list2.stream()).collect(Collectors.groupingBy(x->x, Collectors.counting())).entrySet().stream().filter(e -> e.getValue()==2).map(Map.Entry::getKey).toList()
        );

        // Optimal
        System.out.println(
                list1.stream().filter(list2::contains).toList()
        );
    }
}
