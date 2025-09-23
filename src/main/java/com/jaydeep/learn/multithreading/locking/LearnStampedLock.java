package com.jaydeep.learn.multithreading.locking;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.StampedLock;

public class LearnStampedLock {
    private static final class StoryBook {
        static final StampedLock lock = new StampedLock();

        public static void readBook(String user) throws InterruptedException {
            System.out.println(user + " wants to read the book.");

            // Try optimistic read
            long stamp = lock.tryOptimisticRead(); // non-blocking

            if (lock.validate(stamp)) {
                System.out.println(user + " is optimistically reading...");
                Thread.sleep(2000); // simulate processing
            } else {
                // Fallback to proper read lock
                stamp = lock.readLock();
                try {
                    System.out.println(user + " fallback to read lock.");
                    Thread.sleep(2000); // simulate processing
                } finally {
                    lock.unlockRead(stamp);
                }
            }

            System.out.println(user + " finished reading this book.");
        }

        public static void writeBook(String user) throws InterruptedException {
            System.out.println(user + " wants to write the book. ");
            long stamp = lock.writeLock();
            try {
                System.out.println(user + " is writing the book. ");
                Thread.sleep(2000); // simulate writing
            } finally {
                System.out.println(user + " finished writing this book.");
                lock.unlock(stamp);
            }
        }

    }

    private static final class Member implements Runnable {
        enum MemberType {
            READER,
            WRITER
        }

        private final MemberType type;
        private final String name;

        private Member(String name, MemberType type) {
            this.type = type;
            this.name = name;
        }

        @Override
        public void run() {
            try {
                if (this.type == MemberType.READER)
                    StoryBook.readBook(this.name);
                else if (this.type == MemberType.WRITER)
                    StoryBook.writeBook(this.name);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        List<Member> members = Arrays.asList(
                new Member("Jaydeep", Member.MemberType.READER),
                new Member("Prabhav", Member.MemberType.WRITER),
                new Member("Harsh", Member.MemberType.READER),
                new Member("Palak", Member.MemberType.READER)
        );

        for (Member member : members) {
            executorService.submit(member);
        }

        executorService.shutdown();
    }
}
