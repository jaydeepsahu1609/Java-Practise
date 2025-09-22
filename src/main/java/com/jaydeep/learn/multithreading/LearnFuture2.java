package com.jaydeep.learn.multithreading;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.*;

public class LearnFuture2 {
    private static final class Factorial implements Callable<Long> {
        private final int num;

        Factorial(int n) {
            this.num = n;
        }

        @Override
        public Long call() throws Exception {
            long result = 1L;
            System.out.println("Enqueued " + num + "! at " + LocalDateTime.now());
            for (int i = 2; i <= num; i++) {
                result *= i;
                Thread.sleep(500);
            }

            return result;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Map<Integer, Future<Long>> futures = new HashMap<>();
        List<Integer> nums = new ArrayList<>(Arrays.asList(5, 10, 15, 20));

        for (int num : nums) {
            futures.put(num, executorService.submit(new Factorial(num)));
        }

        int i = 0;
        while (!futures.isEmpty() && !nums.isEmpty()) {
            int key = nums.get(i);

            // Future::get also blocks the main thread till Future::isDone
            // But using Future::isDone to prevent the blocking of main thread, hence making the threads independent
            // [Alternative] try ExecutorCompletionService, which Automatically returns completed results
            //          as soon as they finish, no need to check Future::isDone().
            if (futures.get(key).isDone()) {
                System.out.println("Dequeued " + key + "! at " + LocalDateTime.now());
                System.out.println("Result " + key + "! = " + futures.get(key).get());

                futures.remove(key);
                nums.remove(i);
            }

            i = !nums.isEmpty() ? (i + 1) % nums.size() : 0;
        }

        executorService.shutdown();
    }
}
