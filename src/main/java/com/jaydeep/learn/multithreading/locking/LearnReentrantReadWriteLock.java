package com.jaydeep.learn.multithreading.locking;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LearnReentrantReadWriteLock {
    private static final class StoryBook {
        static final ReadWriteLock lock = new ReentrantReadWriteLock();

        public static void readBook(String user, boolean specialUser) throws InterruptedException {
            System.out.println(user + " wants to read the book. ");

            lock.readLock().lock();
            try {
                System.out.println(user + " is reading the book. ");
                Thread.sleep(2000); // simulate reading

//                if (specialUser) {
//                    writeBook(user); // we will get deadlock, read lock can't acquire write lock and vice versa
//                }
            } finally {
                System.out.println(user + " finished reading this book.");
                lock.readLock().unlock();
            }
        }

        public static void writeBook(String user) throws InterruptedException {
            System.out.println(user + " wants to write the book. ");

            lock.writeLock().lock();
            try {
                System.out.println(user + " is writing the book. ");
                Thread.sleep(2000); // simulate writing
            } finally {
                System.out.println(user + " finished writing this book.");
                lock.writeLock().unlock();
            }
        }

    }

    private static final class Member implements Runnable {
        enum MemberType {
            READER,
            WRITER,
            READER_WRITER;

            MemberType() {
            }
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
                    StoryBook.readBook(this.name, false);
                else if (this.type == MemberType.WRITER)
                    StoryBook.writeBook(this.name);
                else
                    StoryBook.readBook(this.name, true);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        List<Member> members = Arrays.asList(
                new Member("Jaydeep", Member.MemberType.READER_WRITER),
                new Member("Prabhav", Member.MemberType.WRITER),
                new Member("Harsh", Member.MemberType.READER_WRITER),
                new Member("Palak", Member.MemberType.READER)
        );

        for (Member member : members) {
            executorService.submit(member);
        }

        executorService.shutdown();
    }
}
