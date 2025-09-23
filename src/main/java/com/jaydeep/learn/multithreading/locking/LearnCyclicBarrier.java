package com.jaydeep.learn.multithreading.locking;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LearnCyclicBarrier {

    /**
     * Skydiving Plane Policy:
     * <p>
     * - A plane will only take off when exactly 3 skydivers are ready.
     * Flying with fewer than 3 divers is a waste of fuel and resources.
     * - The plane cannot safely carry more than 3 divers at a time due to weight limits.
     * - The flight is held until all 3 divers arrive and are ready, ensuring safety and efficiency.
     * </p>
     */
    private static final class SkyDivingPlane {
        private static final CyclicBarrier barrier = new CyclicBarrier(3, () -> {
            System.out.println("Plane took off with 3 divers");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        public static void enter(String name) throws InterruptedException, BrokenBarrierException {
            System.out.println(name + " entered the airport. Waiting for the plane to take off.");
            barrier.await(); // CyclicBarrier automatically resets after the barrier is tripped
        }
    }

    private static final class SkyDiver implements Runnable {
        private final String name;

        private SkyDiver(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                SkyDivingPlane.enter(this.name);
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(6);

        String[] divers = new String[]{"Jaydeep", "Palak", "Prabhav", "Harsh", "Nipun", "Manmeet"};

        // Launch divers in batches of 3
        for (String diver : divers) {
            executorService.submit(new SkyDiver(diver));
        }

        executorService.shutdown();
    }
}
