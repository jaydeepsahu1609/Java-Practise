package com.jaydeep.learn.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SynchronizedCollections {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5));

        List<Integer> synchronizedList = Collections.synchronizedList(list);

        System.out.println(synchronizedList);
    }

}
