package com.jaydeep.learn.multithreading.executors;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolExecutor {
    private static final class ScheduledThread implements Runnable {
        private final String name;

        ScheduledThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " (" + this.name + ") | completed at: " + LocalDateTime.now());
        }
    }

    public static void main(String[] args) throws InterruptedException {

//        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3, new ThreadFactory() {
            private int count = 1;

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "ThreadPool-" + count++);
            }
        });

        for (int i = 1; i <= 5; i++) {
//            executorService.schedule(new ScheduledThread("Thread-" + i), 1L, TimeUnit.SECONDS);

            executorService.scheduleWithFixedDelay(new ScheduledThread("Thread-" + i),
                    0, 5L, TimeUnit.SECONDS);

//            executorService.scheduleAtFixedRate(new ScheduledThread("Thread-" + i),
//                    0, 5L, TimeUnit.SECONDS);
        }


    }
}
