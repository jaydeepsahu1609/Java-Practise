package com.jaydeep.learn.multithreading.deadlock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @see RaceConditionDemo
 *
 */
public class SolveRaceConditionDemo {

    /**
     * Use {@link AtomicInteger}
     *
     */
    private static final class Solution1 {
        private static AtomicInteger counter = new AtomicInteger(0);

        public static void run() {
            try {
                Thread t1 = new Thread(() -> {
                    System.out.println("Thread-1 is running...");
                    for (int i = 0; i < 10_000; i++)
                        counter.incrementAndGet();
                });

                Thread t2 = new Thread(() -> {
                    System.out.println("Thread-2 is running...");
                    for (int i = 0; i < 10_000; i++)
                        counter.incrementAndGet();
                });

                t1.start();
                t2.start();

                t1.join();
                t2.join();

            } catch (Exception e) {
                System.out.println("Exception in solution-1");
                Thread.currentThread().interrupt();
            }
            System.out.println("[Solution-1] Counter: " + counter);
        }
    }

    /**
     * Use <code>synchronized</code> monitor
     *
     */
    private static final class Solution2 {
        private static int counter = 0;

        public static void run() {
            try {
                Thread t1 = new Thread(() -> {
                    System.out.println("Thread-1 is running...");
                    for (int i = 0; i < 10_000; i++) {
                        synchronized (Solution2.class) {
                            counter++;
                        }
                    }
                });

                Thread t2 = new Thread(() -> {
                    System.out.println("Thread-2 is running...");
                    for (int i = 0; i < 10_000; i++) {
                        synchronized (Solution2.class) {
                            counter++;
                        }
                    }
                });

                t1.start();
                t2.start();

                t1.join();
                t2.join();

            } catch (Exception e) {
                System.out.println("Exception in solution-2");
                Thread.currentThread().interrupt();
            }
            System.out.println("[Solution-2] Counter: " + counter);
        }
    }

    /**
     * Use <code>synchronized</code> method
     *
     */
    private static final class Solution3 {
        private static int counter = 0;

        private static synchronized void count() {
            counter++;
        }

        public static void run() {
            try {
                Thread t1 = new Thread(() -> {
                    System.out.println("Thread-1 is running...");
                    for (int i = 0; i < 10_000; i++) {
                        count();
                    }
                });

                Thread t2 = new Thread(() -> {
                    System.out.println("Thread-2 is running...");
                    for (int i = 0; i < 10_000; i++) {
                        count();
                    }
                });

                t1.start();
                t2.start();

                t1.join();
                t2.join();

            } catch (Exception e) {
                System.out.println("Exception in solution-3");
                Thread.currentThread().interrupt();
            }
            System.out.println("[Solution-3] Counter: " + counter);
        }
    }

    public static void main(String[] args) {
        Solution1.run();
        Solution2.run();
        Solution3.run();
    }

}
