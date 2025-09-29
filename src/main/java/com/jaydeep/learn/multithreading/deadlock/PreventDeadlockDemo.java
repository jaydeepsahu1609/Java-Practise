package com.jaydeep.learn.multithreading.deadlock;

/**
 * @see DeadlockDemo
 */
public class PreventDeadlockDemo {

    /**
     * Reorder the locking. Always acquire lock1 before lock2 if they share resources.
     *
     */
    private static final class Solution1 {
        private static final Object lock1 = new Object();
        private static final Object lock2 = new Object();

        public static void run() throws InterruptedException {
            Thread t1 = new Thread(() -> {
                System.out.println("Thread-1: Trying to acquire lock-1");
                synchronized (lock1) {
                    try {
                        System.out.println("Thread-1: Holding lock-1");
                        Thread.sleep(1000);
                        System.out.println("Thread-1: Trying to acquire lock-2");
                        synchronized (lock2) {
                            System.out.println("Thread-1: Holding lock-2");
                            Thread.sleep(3000);
                        }
                        System.out.println("Thread:1 Release lock-2");
                    } catch (Exception e) {
                        System.out.println("Exception in thread-1");
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println("Thread:1 Release lock-1");
            });

            Thread t2 = new Thread(() -> {
                System.out.println("Thread-2: Trying to acquire lock-1");
                synchronized (lock1) {
                    try {
                        System.out.println("Thread-2: Holding lock-1");
                        Thread.sleep(1000);
                        System.out.println("Thread-2: Trying to acquire lock-2");
                        synchronized (lock2) {
                            System.out.println("Thread-2: Holding lock-2");
                            Thread.sleep(3000);
                        }
                        System.out.println("Thread:2 Release lock-2");
                    } catch (Exception e) {
                        System.out.println("Exception in thread-2");
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println("Thread:2 Release lock-1");
            });

            t1.start();
            t2.start();
            t2.join();
            System.out.println("No deadlock.");
        }
    }

    // Solution2
    // Use Re-entrant lock's `tryLock` method with timeout.

    public static void main(String[] args) throws InterruptedException {
        Solution1.run();
    }
}
