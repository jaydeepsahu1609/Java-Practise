package com.jaydeep.learn.collections;

import java.util.Comparator;
import java.util.PriorityQueue;

public class LearnQueue {
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

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    public static void main(String[] args) {
        PriorityQueue<Student> queue = new PriorityQueue<>(Comparator.comparingInt((Student s) -> s.getName().length()).reversed());

        queue.offer(new Student(16, "jaydeep"));
        queue.offer(new Student(16, "harsh"));
        queue.offer(new Student(12, "prabhav"));
        queue.offer(new Student(26, "manmeet"));
        queue.offer(new Student(15, "palak"));

        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }
}
