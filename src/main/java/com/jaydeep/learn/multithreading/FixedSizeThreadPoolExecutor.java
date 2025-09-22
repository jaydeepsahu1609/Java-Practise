package com.jaydeep.learn.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class FixedSizeThreadPoolExecutor {
    private static final class CounterThread extends Thread {
        private final String name;

        CounterThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + " (" + this.name + ") | " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("Thread " + this.name + " completed");

        }
    }

    public static void main(String[] args) {

//        ExecutorService executorService = Executors.newFixedThreadPool(3);

//        ExecutorService executorService = Executors.newFixedThreadPool(3, Executors.defaultThreadFactory());

        ExecutorService executorService = Executors.newFixedThreadPool(3, new ThreadFactory() {
            private int count = 1;

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "ThreadPool-" + count++);
            }
        });

        for (int i = 1; i <= 5; i++) {
            executorService.submit(new CounterThread("Thread-" + i));
        }

        executorService.shutdown(); // otherwise program will not terminate

    }
}
