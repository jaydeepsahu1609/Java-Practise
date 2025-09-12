package com.jaydeep.learn.collections;

import java.util.List;
import java.util.Set;

public class FactoryMethods {

    public static void main(String[] args) {
//        List<Integer> list = List.of(1,2,3,4l,5);
        List<Integer> list = List.of(1, 2, 3, 4, 5);

        System.out.println(list);

//        List<Integer> list1 = List.of(null);
        List<Integer> list1 = List.of();

        System.out.println(list1);

        Set<Integer> set = Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
//        Set<Integer> set = Set.of(1,2,3,4,4, 5);

        System.out.println(set);

        Set<Integer> set2 = Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);

        System.out.println(set2);

    }
}
