package com.jaydeep.learn.multithreading;

import java.util.LinkedList;
import java.util.Queue;

class Producer extends Thread {
    private final int BOUNDED_BUFFER_SIZE;
    private final Queue<Integer> queue;

    Producer(Queue<Integer> queue, int bufferSize) {
        this.queue = queue;
        this.BOUNDED_BUFFER_SIZE = bufferSize;
    }

    public void produce(int item) {
        try {
            synchronized (queue) {
                while (queue.size() == BOUNDED_BUFFER_SIZE) {
                    queue.wait();
                }
                queue.offer(item);

                System.out.println("Produced: " + item);
                queue.notifyAll();
            }
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Error: " + e.getMessage());
        }

    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            this.produce(i);
        }
    }
}

class Consumer extends Thread {
    private final Queue<Integer> queue;

    Consumer(Queue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 10; i++) {
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        queue.wait();
                    }

                    System.out.println("Consumed: " + queue.poll());
                    queue.notifyAll();
                }
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Error: " + e.getMessage());
        }
    }
}

/**
 * Demonstrates the classic Producer–Consumer problem using
 * synchronized, wait(), and notifyAll().
 *
 * <p>
 * Producer: keeps generating items and puts them into a bounded buffer.
 * If the buffer is full, the producer waits until the consumer
 * removes an item and notifies it.
 * </p>
 *
 * <p>
 * Consumer: keeps consuming items from the buffer.
 * If the buffer is empty, the consumer waits until the producer
 * adds an item and notifies it.
 * </p>
 *
 * <p>
 * Coordination:
 * - The shared queue acts as the bounded buffer.
 * - Both Producer and Consumer use wait() to block when they cannot proceed,
 * and notifyAll() to wake up waiting threads when the state changes.
 * </p>
 * <p>
 * This example demonstrates low-level concurrency primitives.
 * In real-world applications, prefer higher-level constructs like BlockingQueue.
 */
public class ProducerConsumerProblem {
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();

        new Producer(queue, 2).start();
        new Consumer(queue).start();
    }
}