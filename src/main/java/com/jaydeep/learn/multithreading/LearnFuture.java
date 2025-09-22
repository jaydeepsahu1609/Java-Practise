package com.jaydeep.learn.multithreading;

import java.util.concurrent.*;

public class LearnFuture {
    private static final class Factorial implements Callable<Long> {
        private final int num;

        Factorial(int n) {
            this.num = n;
        }

        @Override
        public Long call() throws Exception {
            long result = 1L;

            for (int i = 2; i <= num; i++) {
                result *= i;
                Thread.sleep(200);
            }

            return result;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        Future<Long> factorial = executorService.submit(new Factorial(20));

        // Future::get method blocks till it gets the result.
        //  Although Future::isDone is not needed here, adding to display that the calculations are in progress.
        while (!factorial.isDone()) {
            System.out.println("Factorial calculation is in progress. Please wait!");
            Thread.sleep(1500);
        }

        System.out.println("Factorial is : " + factorial.get());

        executorService.shutdown();
    }
}
