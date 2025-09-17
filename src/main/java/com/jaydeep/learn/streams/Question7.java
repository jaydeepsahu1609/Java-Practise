package com.jaydeep.learn.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Question7 {
    // Tutorial: https://youtu.be/QERuQaQT74U?si=RbQNp3NUpA7y0_6i
    public static void main(String[] args) {
        // Divide given integer list into lists of even and odd numbers
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

        // Always divides the stream into a map of two keys:- true and false
        // Map<Boolean, List<String>>
        System.out.println(
                list.stream().collect(Collectors.partitioningBy(num -> num%2==0))
        );

        // Divides the stream into a map of multiple keys based classifier function
        // Map<ReturnedByClassifier, List<String>>
        System.out.println(
                list.stream().collect(Collectors.groupingBy(num -> num%2==0 ? "Even" : "Odd"))
        );
    }
}
