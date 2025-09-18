package com.jaydeep.learn.multithreading;

class SharedResource {
    private boolean ready = false;

    // wait until ready becomes true
    public synchronized void waitForSignal() {
        System.out.println(Thread.currentThread().getName() + " is waiting...");
        while (!ready) {
            try {
                wait(); // releases lock and waits
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(Thread.currentThread().getName() + " got the signal!");
    }

    // notify a single waiting thread
    public synchronized void signalOne() {
        ready = true;
        notify(); // wakes up ONE waiting thread
        System.out.println(Thread.currentThread().getName() + " signaled one thread");
    }

    // notify all waiting threads
    public synchronized void signalAll() {
        ready = true;
        notifyAll(); // wakes up ALL waiting threads
        System.out.println(Thread.currentThread().getName() + " signaled ALL threads");
    }
}

public class WaitNotifyDemo {
    public static void main(String[] args) throws InterruptedException {
        SharedResource resource = new SharedResource();

        // create 3 waiting threads
        for (int i = 1; i <= 3; i++) {
            new Thread(resource::waitForSignal, "Worker-" + i).start();
        }

        Thread.sleep(2000); // let them start and go into waiting

        // signal ONE thread
        new Thread(resource::signalOne, "Notifier-1").start();

        Thread.sleep(2000);

        // signal ALL remaining threads
        new Thread(resource::signalAll, "Notifier-2").start();
    }
}

