package com.jaydeep.learn.collections.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class LearnArrayBlockingQueue {

    // Producer and Consumers that wait indefinitely (using take and put)
    static class Producer1 implements Runnable {
        private final BlockingQueue<Integer> blockingQueue;
        private final int timeout;

        public Producer1(BlockingQueue<Integer> blockingQueue, int timeout) {
            this.blockingQueue = blockingQueue;
            this.timeout = timeout;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    blockingQueue.put(i);

                    System.out.println("Producing: " + i + " Queue: " + blockingQueue);
                    Thread.sleep(timeout);
                }
                blockingQueue.put(-1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    static class Consumer1 implements Runnable {
        private final BlockingQueue<Integer> blockingQueue;
        private final int timeout;

        public Consumer1(BlockingQueue<Integer> blockingQueue, int timeout) {
            this.blockingQueue = blockingQueue;
            this.timeout = timeout;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    Integer value = blockingQueue.take();
                    if (value == -1) {
                        break;
                    }

                    System.out.println("Consuming: " + value + " Queue: " + blockingQueue);
                    Thread.sleep(timeout);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // Producer and Consumers with timeouts (using offer and poll)

    static class Producer2 implements Runnable {
        private final BlockingQueue<Integer> blockingQueue;
        private final int timeout;

        public Producer2(BlockingQueue<Integer> blockingQueue, int timeout) {
            this.blockingQueue = blockingQueue;
            this.timeout = timeout;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    blockingQueue.offer(i, 100, TimeUnit.MILLISECONDS);

                    System.out.println("Producing: " + i + " Queue: " + blockingQueue);
                    Thread.sleep(timeout);
                }
                blockingQueue.put(-1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    static class Consumer2 implements Runnable {
        private final BlockingQueue<Integer> blockingQueue;
        private final int timeout;

        public Consumer2(BlockingQueue<Integer> blockingQueue, int timeout) {
            this.blockingQueue = blockingQueue;
            this.timeout = timeout;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    Integer value = blockingQueue.poll(100, TimeUnit.MILLISECONDS);
                    if (value == -1) {
                        break;
                    }

                    System.out.println("Consuming: " + value + " Queue: " + blockingQueue);
                    Thread.sleep(timeout);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // helper methods

    private static void simulateProducerConsumer(BlockingQueue<Integer> blockingQueue) {

        // Create consumer and producer
        Thread producerThread = new Thread(new Producer1(blockingQueue, 100));
        Thread consumerThread = new Thread(new Consumer1(blockingQueue, 1500));

//        producerThread.start();
        // // producer will be forever blocked as capacity is 5 & there is no consumer
        // consumerThread.start();

//        consumerThread.start(); // uncommented to avoid forever block
    }

    private static void simulateProducerConsumerWithTimeouts(BlockingQueue<Integer> blockingQueue) {

        // Create consumer and producer
        Thread producerThread = new Thread(new Producer2(blockingQueue, 100));
        Thread consumerThread = new Thread(new Consumer2(blockingQueue, 1500));

        producerThread.start();
        // // producer will be forever blocked as capacity is 5 & there is no consumer
        // consumerThread.start();

        consumerThread.start(); // uncommented to avoid forever block
    }

    // entry point

    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(5);

        // Simulate producer and consumer
        System.out.println("------- start of simulateProducerConsumer -------");
        simulateProducerConsumer(blockingQueue);
        System.out.println("------- end of simulateProducerConsumer -------");

        // take(E e) and put(), wait for indefinitely
        // if the timeout is must, we need to use offer and poll with timeouts
        // however, operation will be discarded in case of timeout, i.e, offer and poll will no effects
        System.out.println("------- start of simulateProducerConsumerWithTimeouts -------");
        simulateProducerConsumerWithTimeouts(blockingQueue);
        System.out.println("------- end of simulateProducerConsumerWithTimeouts -------");
    }
}
