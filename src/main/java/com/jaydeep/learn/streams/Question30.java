package com.jaydeep.learn.streams;

import java.util.Arrays;
import java.util.List;

public class Question30 {
    // Tutorial: https://youtu.be/OdeYbZhGKV8?si=TcfYA1xz4mYPOfj-

    public static void main(String[] args) {
        // Convert a list of strings to uppercase
        List<String> list = Arrays.asList("jaydeep", "harsh", "prabhav", "palak");

        System.out.println(
                list.stream().map(String::toUpperCase).toList()
        );
    }
}
