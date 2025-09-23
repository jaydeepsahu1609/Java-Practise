package com.jaydeep.learn.multithreading.locking;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

public class LearnSemaphores {
    /**
     * Sky simulates air traffic with a maximum of 3 planes allowed in the sky at a time.
     */
    private static final class Sky {
        static Semaphore smp = new Semaphore(3, true); // fair semaphore, limits max concurrent planes

        public static void fly(Plane plane) throws InterruptedException {
            if (smp.availablePermits() == 0) {
                System.out.println(plane.name + " needs to wait. Reason: High air traffic");
            }

            smp.acquire(); // acquire permit to fly
            try {
                System.out.println(plane.name + " is flying..");
                Thread.sleep(2000); // simulate flying duration
            } finally {
                smp.release(); // release permit after landing
                System.out.println(plane.name + " landed successfully");
            }
        }
    }

    private static final class Plane implements Runnable {
        final String name;

        private Plane(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                Sky.fly(this);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Random rnd = new Random();
        IntStream.range(0, 5).forEach(
                i -> executorService.submit(new Plane("Flight-" + (1100 + rnd.nextInt(401))))
        );

        executorService.shutdown();
    }
}
