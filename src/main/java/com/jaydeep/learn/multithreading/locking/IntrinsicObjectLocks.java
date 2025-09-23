package com.jaydeep.learn.multithreading.locking;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IntrinsicObjectLocks {
    private static class CoffeeMachine {
        // object-level locking on a shared resource `coffeeMachine`
        // since there is only one coffee machine, all the users must wait for their turn to get coffee-machine
        public synchronized void makeCoffee(String user) {
            System.out.println("[" + Thread.currentThread().getName() +
                    "] Preparing Coffee for " + user);
            try {
                Thread.sleep(2000);
                System.out.println("[" + Thread.currentThread().getName() +
                        "] Coffee is ready for " + user);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static class CoffeeUser implements Runnable {
        private final String user;
        private final CoffeeMachine machine;

        CoffeeUser(String user, CoffeeMachine machine) {
            this.user = user;
            this.machine = machine;
        }

        @Override
        public void run() {
            machine.makeCoffee(user);
        }
    }

    public static void main(String[] args) {
        ExecutorService coffeeMachines = Executors.newFixedThreadPool(3);
        String[] users = new String[]{"Jaydeep", "Harsh", "Prabhav", "Palak"};

        CoffeeMachine coffeeMachine = new CoffeeMachine();

        for (String user : users) {
            CoffeeUser cu = new CoffeeUser(user, coffeeMachine);
            coffeeMachines.submit(cu);
        }

        coffeeMachines.shutdown();
    }
}
