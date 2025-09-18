package com.jaydeep.learn.multithreading;

public class ThreadRunnable {
    private static final class MyThread extends Thread {
        MyThread(String name){
            super(name);
        }
        @Override
        public void run() {
            System.out.println("[Thread::"+Thread.currentThread().getName()+"] is running.");

            for(int i=1; i<=20; i++){
                System.out.println("[Thread::"+Thread.currentThread().getName()+"] "+i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread t1 = new MyThread("t1");
        MyThread t2 = new MyThread("t2");
        MyThread t3 = new MyThread("t3");

        t1.start();

        Thread.sleep(500);
        t2.start();

        Thread.sleep(500);
        t3.start();
    }
}
