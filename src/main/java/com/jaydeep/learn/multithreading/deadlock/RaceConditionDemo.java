package com.jaydeep.learn.multithreading.deadlock;

public class RaceConditionDemo {
    private static int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.println("Thread-1 is running...");
            for (int i = 0; i < 10_000; i++)
                counter++;
        });

        Thread t2 = new Thread(() -> {
            System.out.println("Thread-2 is running...");
            for (int i = 0; i < 10_000; i++)
                counter++;
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Counter: " + counter);
    }
}
