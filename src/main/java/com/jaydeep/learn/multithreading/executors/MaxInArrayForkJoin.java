package com.jaydeep.learn.multithreading.executors;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

/**
 * Fork/Join pool scales better than raw threads for recursive tasks because it steals work from idle threads (work-stealing)
 *
 */
public class MaxInArrayForkJoin {
    private static final class MaxArrayTask extends RecursiveTask<Integer> {
        final int[] arr;
        final int low;
        final int high;

        MaxArrayTask(int[] arr, int low, int high) {
            this.arr = arr;
            this.low = low;
            this.high = high;
        }

        @Override
        protected Integer compute() {
            System.out.println("[" + Thread.currentThread().getName() + "] low: " + this.low + " high: " + high);
            if (high - low <= 5) {
                int localMax = 0;
                for (int i = low; i < high; i++) {
                    localMax = Math.max(arr[i], localMax);
                }
                return localMax;
            }
            int mid = (low + high) / 2;

            MaxArrayTask left = new MaxArrayTask(arr, low, mid);
            left.fork();                     // fork left asynchronously

            MaxArrayTask right = new MaxArrayTask(arr, mid, high);
            int rightMax = right.compute();  // compute right in current thread

            int leftMax = left.join();       // join left after right is done
            return Math.max(leftMax, rightMax);
        }
    }

    public static void main(String[] args) {
        int length = 45;

        int[] arr = new int[length];

        IntStream.range(0, length).forEach(idx -> {
            arr[idx] = idx + 1;
        });

        MaxArrayTask task = new MaxArrayTask(arr, 0, length);

        ForkJoinPool pool = new ForkJoinPool(4);
        int max = pool.invoke(task);

        System.out.println("Max: " + max);
    }
}
