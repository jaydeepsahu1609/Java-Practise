package com.jaydeep.learn.multithreading;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LearnCompletableFuture {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 20;
        }, executorService);
        future.thenAccept(System.out::println);

        future.thenApply(x -> x * 2).thenAccept(System.out::println);

        System.out.println("End of Main");
        boolean shutdown = executorService.awaitTermination(4, TimeUnit.SECONDS);
        if (!shutdown)
            executorService.shutdownNow();
    }
}
