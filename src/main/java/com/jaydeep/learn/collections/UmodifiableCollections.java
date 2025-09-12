package com.jaydeep.learn.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UmodifiableCollections {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(List.of(1,2,3,4,5,6));
        System.out.println(list);

        list.add(7);
        System.out.println(list);

        List<Integer> unmodifiableList = List.copyOf(list);
        System.out.println(unmodifiableList);

//        unmodifiableList.set(0, 2);
//        System.out.println(unmodifiableList);

        list.add(8);
        System.out.println(list);
        System.out.println(unmodifiableList);

        List<Integer> newList = Collections.emptyList();
    }

}
