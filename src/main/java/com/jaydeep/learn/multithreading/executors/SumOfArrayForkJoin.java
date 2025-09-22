package com.jaydeep.learn.multithreading.executors;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

/**
 * Fork/Join pool scales better than raw threads for recursive tasks because it steals work from idle threads (work-stealing)
 *
 */
public class SumOfArrayForkJoin {
    private static final class ArraySumTask extends RecursiveTask<Integer> {
        final int[] arr;
        final int low;
        final int high;

        ArraySumTask(int[] arr, int low, int high) {
            this.arr = arr;
            this.low = low;
            this.high = high;
        }

        @Override
        protected Integer compute() {
            System.out.println("[" + Thread.currentThread().getName() + "] low: " + this.low + " high: " + high);
            if (high - low <= 5) {
                int sum = 0;
                for (int i = low; i < high; i++) {
                    sum += arr[i];
                }
                return sum;
            }
            int mid = (low + high) / 2;

            ArraySumTask left = new ArraySumTask(arr, low, mid);
            left.fork(); // submits the task to the pool asynchronously

            ArraySumTask right = new ArraySumTask(arr, mid, high);

            int leftSum = left.join(); // Waits for the forked task to finish and retrieves its result.
            int rightSum = right.compute(); // runs task in current thread to avoid context-switching overhead
            // you can use right.invoke() i.e., fork+join

            return leftSum + rightSum;
        }
    }

    public static void main(String[] args) {
        int length = 45;

        int[] arr = new int[length];

        IntStream.range(0, length).forEach(idx -> {
            arr[idx] = idx + 1;
        });

        ArraySumTask task = new ArraySumTask(arr, 0, length);

//        ForkJoinPool pool = ForkJoinPool.commonPool();
        ForkJoinPool pool = new ForkJoinPool(4);
        int sum = pool.invoke(task);

        System.out.println("Sum: " + sum);
    }
}
