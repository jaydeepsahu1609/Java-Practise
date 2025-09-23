package com.jaydeep.learn.multithreading.locking;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LearnCountdownLatch {

    /**
     * Skydiving Plane Policy:
     * <p>
     * - A plane will only take off when exactly 3 skydivers are ready.
     * Flying with fewer than 3 divers is a waste of fuel and resources.
     * - The plane cannot safely carry more than 3 divers at a time due to weight limits.
     * - The flight is held until all 3 divers arrive and are ready, ensuring safety and efficiency.
     * <p>
     * CountDownLatch is used to coordinate the divers:
     * Each diver signals when ready, and the plane waits until all 3 signals are received.
     */
    private static final class SkyDivingPlane {

        public static void enter(String name, CountDownLatch latch) throws InterruptedException {
            System.out.println(name + " entered the airport. Waiting for the plane to take off.");
            latch.countDown();
            latch.await();
            System.out.println(name + " took off.");
        }
    }

    private static final class SkyDiver implements Runnable {
        private final String name;
        private final CountDownLatch latch;

        private SkyDiver(String name, CountDownLatch latch) {
            this.name = name;
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                SkyDivingPlane.enter(this.name, latch);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(6);

        String[] divers = new String[]{"Jaydeep", "Palak", "Prabhav", "Harsh", "Nipun", "Manmeet"};

        // Launch divers in batches of 3
        for (int i = 0; i < divers.length; i += 3) {
            int batchSize = Math.min(3, divers.length - i);
            CountDownLatch flightLatch = new CountDownLatch(batchSize);

            for (int j = 0; j < batchSize; j++) {
                executorService.submit(new SkyDiver(divers[i + j], flightLatch));
            }

            // wait a bit for this flight to finish before next batch starts
            Thread.sleep(500);
        }

        executorService.shutdown();
    }
}
