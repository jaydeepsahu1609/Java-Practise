package com.jaydeep.learn.arrays;

import java.util.Arrays;


public class ArraysPractise {
    static class Student {
        private final int id;
        private final String name;

        Student(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Student{id=" + id + ", name='" + name + "'}";
        }

        // getters
        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5};

        // copyOf
        System.out.println("--- copyOf ---");

        int[] numCopy = Arrays.copyOf(nums, nums.length - 2);
        System.out.println(Arrays.toString(numCopy));

        // copyOfRange
        System.out.println("--- copyOfRange ---");
        // fills rest of the indices with default values
        int[] numCopyRange = Arrays.copyOfRange(nums, 1, 10);
        System.out.println(Arrays.toString(numCopyRange));

        // toString
        // doesnt work on 2D+ arrays
        System.out.println("--- toString ---");

        System.out.println(Arrays.toString(nums));

        int[][] multiDim = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println(Arrays.toString(multiDim));

        Student[] students = new Student[]{
                new Student(16, "Jaydeep"),
                new Student(15, "Palak"),
                new Student(15, "Vidhyash"),
                new Student(15, "Bharat")
        };
        System.out.println(students);
        System.out.println(Arrays.toString(students));

        Student[][] multiStudent = new Student[][]{
                {new Student(16, "Jaydeep"), new Student(15, "Palak")},
                {new Student(26, "Vidhyansh"), new Student(12, "Bharat")}
        };
        System.out.println(Arrays.toString(multiStudent));

        // deepToString
        System.out.println("--- deepToString ---");
        System.out.println(Arrays.deepToString(multiStudent));

        // stream
        System.out.println("--- stream ---");
        int[] numsList = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(Arrays.toString(numsList));
        numsList = Arrays.stream(numsList).map(a -> a * 2).toArray();
        System.out.println(Arrays.toString(numsList));

        // fill
        System.out.println("--- fill ---");
        Student[] studentArr = new Student[5];
        Arrays.fill(studentArr, new Student(15, "Palak"));
        System.out.println(Arrays.toString(studentArr));

        Arrays.fill(studentArr, 2, 4, new Student(16, "Jaydeep"));
        System.out.println(Arrays.toString(studentArr));

        // sort
        System.out.println("--- sort ---");
        System.out.println(Arrays.toString(students));
        Arrays.sort(students);
        System.out.println(Arrays.toString(students));
    }
}
