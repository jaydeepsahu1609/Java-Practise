package com.jaydeep.learn.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Question55 {
    // Tutorial: https://youtu.be/AwRln_LftAE?si=uhy83e17AhSprNS5

    public static void main(String[] args) {
        // Reorder message from format in chronological order
        List<String> chunks = new ArrayList<>(
                Arrays.asList(
                        "14:30:3:session started",
                        "14:30:1:user logged in",
                        "14:29:2:database connected",
                        "18:32:4:session closed"
                )
        );

        chunks.stream().sorted((c1, c2) -> {
            String[] arr1 = c1.split(":");
            String[] arr2 = c2.split(":");

            int h1 = Integer.parseInt(arr1[0]);
            int h2 = Integer.parseInt(arr2[0]);

            if (h1 != h2)
                return h1 - h2;

            int m1 = Integer.parseInt(arr1[1]);
            int m2 = Integer.parseInt(arr2[1]);

            if (m1 != m2)
                return m1 - m2;

            int i1 = Integer.parseInt(arr1[2]);
            int i2 = Integer.parseInt(arr2[2]);

            return i1 - i2;
        }).forEach(System.out::println);

        System.out.println("----------");

        chunks.stream()
                .sorted(Comparator
                        .comparingInt((String c) -> Integer.parseInt(c.split(":")[0])) // hour
                        .thenComparingInt(c -> Integer.parseInt(c.split(":")[1]))      // minute
                        .thenComparingInt(c -> Integer.parseInt(c.split(":")[2]))      // index
                )
                .forEach(System.out::println);
    }


}
