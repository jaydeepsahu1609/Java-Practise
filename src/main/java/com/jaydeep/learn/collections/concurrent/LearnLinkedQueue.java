package com.jaydeep.learn.collections.concurrent;

import java.util.concurrent.ConcurrentLinkedQueue;

public class LearnLinkedQueue {

    public static void main(String[] args) {
        ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();

        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        queue.offer(6);

        System.out.println(queue);
    }
}
