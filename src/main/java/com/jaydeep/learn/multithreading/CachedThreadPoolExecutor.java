package com.jaydeep.learn.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * CachedThreadPool is elastic: grows as needed, shrinks when idle (threads die after 60s of inactivity).
 * <ul>
 * <li><b>Quick bursts</b> → more threads spawned.</li>
 * <li><b>Idle periods</b> → threads terminate → no wasted resources.</li>
 * </ul>
 */
public class CachedThreadPoolExecutor {
    private static final class CounterThread implements Runnable {
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
            System.out.println(this.name + " completed");

        }
    }

    public static void main(String[] args) {

//        ExecutorService executorService = Executors.newCachedThreadPool();

        ExecutorService executorService = Executors.newCachedThreadPool(new ThreadFactory() {
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
