package com.jaydeep.learn.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Question37 {
    // Tutorial: https://youtu.be/jYz74lr4ctw?si=hL7mcw75zGsl5lnx

    public static void main(String[] args) {
        //  Group list of strings by their first character and count the number of strings
        List<String> list = new ArrayList<>(Arrays.asList("apple", "banana", "cherry", "avacado", "berries", "apricot"));

        System.out.println(
                list.stream().collect(Collectors.groupingBy(string -> string.charAt(0), Collectors.counting()))
        );
    }
}
