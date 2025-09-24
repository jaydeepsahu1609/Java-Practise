package com.jaydeep.learn.multithreading;

public class LearnVolatile {

    private static final class NonVolatile {
        private static boolean flag = false;

        public static void run(String name) throws InterruptedException {
            Thread worker = new Thread(() -> {
                Thread.currentThread().setName(name);

                System.out.println("[" + Thread.currentThread().getName() + "] Worker thread started, waiting for flag...");
                while (!flag) {
                    // busy wait until flag changes
                }
                System.out.println("[" + Thread.currentThread().getName() + "] Worker noticed flag change!");
            });

            worker.start();

            Thread.sleep(2000);
            System.out.println("[" + Thread.currentThread().getName() + "] Main thread setting flag to true. " +
                    "But changes will not be detected.");
            flag = true;

            worker.join();
        }
    }

    private static final class Volatile {
        private static volatile boolean flag = false;

        public static void run(String name) throws InterruptedException {
            Thread worker = new Thread(() -> {
                Thread.currentThread().setName(name);

                System.out.println("[" + Thread.currentThread().getName() + "] Worker thread started, waiting for flag...");
                while (!flag) {
                    Thread.onSpinWait();
                    // busy wait until flag changes
                }
                System.out.println("[" + Thread.currentThread().getName() + "] Worker noticed flag change!");
            });

            worker.start();

            Thread.sleep(2000);
            System.out.println("[" + Thread.currentThread().getName() + "] Main thread setting flag to true");
            flag = true;

            worker.join();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Volatile.run("Volatile");
        NonVolatile.run("NonVolatile");
    }

}
