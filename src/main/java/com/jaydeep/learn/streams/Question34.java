package com.jaydeep.learn.streams;

import java.util.stream.Stream;

public class Question34 {
    // Tutorial: https://youtu.be/442N_OighlY?si=6RxMopgo7xCsiOdT

    public static void main(String[] args) {
        // Generate the first 10 numbers of the Fibonacci Sequence
        int input = 10;

        System.out.println(
                Stream.iterate(new int[]{0, 1}, f -> new int[]{f[1], f[0] + f[1]}).limit(input).map(x -> x[0]).toList()
        );

    }
}
