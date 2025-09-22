package com.jaydeep.learn.multithreading.executors;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

/**
 * Fork/Join pool scales better than raw threads for recursive tasks because it steals work from idle threads (work-stealing)
 *
 */
public class ParallelSearchInArrayForkJoin {
    private static final class MaxArrayTask extends RecursiveTask<Boolean> {
        final int[] arr;
        final int low;
        final int high;
        final int target;

        MaxArrayTask(int[] arr, int low, int high, int target) {
            this.arr = arr;
            this.low = low;
            this.high = high;
            this.target = target;
        }

        @Override
        protected Boolean compute() {
            System.out.println("[" + Thread.currentThread().getName() + "] low: " + this.low + " high: " + high);
            if (high - low <= 5) {
                for (int i = low; i < high; i++) {
                    if (arr[i] == target)
                        return true;
                }
                return false;
            }
            int mid = (low + high) / 2;

            MaxArrayTask left = new MaxArrayTask(arr, low, mid, target);
            left.fork();                     // fork left asynchronously

            MaxArrayTask right = new MaxArrayTask(arr, mid, high, target);
            boolean rightMax = right.compute();  // compute right in current thread

            if (rightMax)
                return true;

            return left.join();       // join left after right is done
        }
    }

    public static void main(String[] args) {
        int length = 45;

        int[] arr = new int[length];

        IntStream.range(0, length).forEach(idx -> {
            arr[idx] = idx + 1;
        });

        MaxArrayTask task1 = new MaxArrayTask(arr, 0, length, 43);
        MaxArrayTask task2 = new MaxArrayTask(arr, 0, length, 49);

        ForkJoinPool pool = new ForkJoinPool(4);
        ForkJoinTask<Boolean> f1 = pool.submit(task1);
        ForkJoinTask<Boolean> f2 = pool.submit(task2);

        boolean found1 = f1.join();
        System.out.println("Found 43: " + found1);

        boolean found2 = f2.join();
        System.out.println("Found 49: " + found2);

        pool.shutdown();
    }
}
