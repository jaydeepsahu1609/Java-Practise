package com.jaydeep.learn.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Question49 {
    // Tutorial: https://youtu.be/OoBP_TcONfY?si=_ITz4tRVQ9zi_miA

    public static void main(String[] args) {
        // Print distinct numbers which starts with "1" in descending order
        List<Integer> list = new ArrayList<>(Arrays.asList(1,21,34,112,49,12,99,100));

        System.out.println(
                list.stream().distinct().filter(num -> String.valueOf(num).startsWith("1")).sorted(Collections.reverseOrder()).toList()
        );
    }
}
