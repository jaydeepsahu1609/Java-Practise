package com.jaydeep.learn.multithreading.deadlock;

/**
 * Simulate a deadlock where thread-1 waits to acquire lock-2 which is help by another thread thread-2 waiting for lock-1.
 * Hence, both waiting for a lock held by each other.
 * <p>
 * To detect this deadlock:
 * <p>
 * <ul>
 *     <li>Use `jconsole` tool. Go to `Threads` and select `detect deadlock`.</li>
 *     OR
 *     <li>Run `jps -l`. Copy the process ID of this program. </li>
 *     <li>Then run- `jcmd &lt;Thread-Id&gt; Thread.Print`. You can notice Thread-0 waiting for lock held by Thread-1 and vice-versa.</li>
 * </ul>
 * <p>
 * <b>Output:</b>
 * <p>
 * Thread-2: Trying to acquire lock-2
 * <br/>
 * Thread-2: Holding lock-2
 * <br/>
 * Thread-1: Trying to acquire lock-1
 * <br/>
 * Thread-1: Holding lock-1
 * <br/>
 * Thread-2: Trying to acquire lock-1
 * <br/>
 * Thread-1: Trying to acquire lock-2
 * <br/>
 *
 */
public class DeadlockDemo {
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println("Thread-1: Trying to acquire lock-1");
            synchronized (lock1) {
                try {
                    System.out.println("Thread-1: Holding lock-1");
                    Thread.sleep(1000);
                    System.out.println("Thread-1: Trying to acquire lock-2");
                    synchronized (lock2) {
                        System.out.println("Thread-1: Holding lock-2");
                        Thread.sleep(3000);
                    }
                } catch (Exception e) {
                    System.out.println("Exception in thread-1");
                    Thread.currentThread().interrupt();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            System.out.println("Thread-2: Trying to acquire lock-2");
            synchronized (lock2) {
                try {
                    System.out.println("Thread-2: Holding lock-2");
                    Thread.sleep(1000);
                    System.out.println("Thread-2: Trying to acquire lock-1");
                    synchronized (lock1) {
                        System.out.println("Thread-2: Holding lock-1");
                        Thread.sleep(3000);
                    }
                } catch (Exception e) {
                    System.out.println("Exception in thread-2");
                    Thread.currentThread().interrupt();
                }
            }
        });

        t1.start();
        t2.start();
    }
}
