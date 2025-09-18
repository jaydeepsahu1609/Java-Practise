package com.jaydeep.learn.multithreading;

import java.util.LinkedList;
import java.util.Queue;

class Producer extends Thread {
    private final Queue<Integer> queue;

    Producer(Queue<Integer> queue) {
        this.queue = queue;
    }

    public void produce(int item) {
        try {
            synchronized (queue) {
                while (!queue.isEmpty()) {
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
 * <p>
 * Producer waits for consumer to consumer previous data.
 * Once done, it produces the data and notifies consumer to consume.
 * </p>
 * <p>
 * Consumer waits for producer to produce a data.
 * Once done, it consumes the data and notifies producer that it is ready to consume.
 * </p>
 */
public class ProducerConsumerProblem {
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();

        new Producer(queue).start();
        new Consumer(queue).start();
    }
}