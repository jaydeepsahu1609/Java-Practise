package com.jaydeep.learn.collections.blockingqueue;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class LearnDelayQueue {

    /**
     * Process object to be scheduled
     */

    static class Process implements Delayed {
        int id;
        Timestamp enqueueTime;
        int delay = 2000;

        public Process(int id) {
            this.id = id;
            enqueueTime = Timestamp.valueOf(LocalDateTime.now());
        }

        public Process(int id, int delay) {
            this.id = id;
            this.enqueueTime = Timestamp.valueOf(LocalDateTime.now());
            this.delay = delay * 1000;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            long diff = enqueueTime.getTime() + this.delay - System.currentTimeMillis();
            return unit.convert(diff, TimeUnit.MILLISECONDS);
        }


        @Override
        public int compareTo(Delayed o) {
            return Long.compare(this.getDelay(TimeUnit.MILLISECONDS),
                    o.getDelay(TimeUnit.MILLISECONDS));
        }

        @Override
        public String toString() {
            return "Process[" + this.id + "|" + this.enqueueTime + "|" + this.delay + "]";
        }
    }

    /**
     * Producer that schedules processes
     * */
    static class Producer implements Runnable {
        private final BlockingQueue<Process> queue;

        public Producer(BlockingQueue<Process> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    Process process = new Process(i, new Random().nextInt(0, 5));
                    System.out.println("Scheduling: " + process + " Process Queue: " + queue);
                    queue.put(process);
                    Thread.sleep(500); // Simulate time between scheduling processes
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Consumer that processes scheduled processes
     * */
    static class Consumer implements Runnable {
        private final BlockingQueue<Process> queue;

        public Consumer(BlockingQueue<Process> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (Thread.activeCount() > 1) {
                try {
                    Process process = queue.take();
                    System.out.println("Scheduled: " + process);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    // helper method
    private static void simulateSchedulingQueue(BlockingQueue<Process> queue) {
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);

        producerThread.start();
        consumerThread.start();
    }

    // --------------------------------------------------

    public static void main(String[] args) {
        BlockingQueue<Process> queue = new DelayQueue<>();

        simulateSchedulingQueue(queue);
    }


}
