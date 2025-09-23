package com.jaydeep.learn.multithreading.locking;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IntrinsicClassLocks {
    private static class CoffeeMachine implements Runnable {
        final String user;
        private CoffeeMachine(String user) {
            this.user = user;
        }

        // there is only one coffee machine shared between all the users
        // class level synchronized lock on static method prevents multiple users from accessing coffee-machine at the same time
        // "WE ALLOW ONLY ONE COFFEE ORDER AT A TIME"
        private static synchronized void makeCoffee(String name){
            System.out.println("Preparing Coffee for " + name);
            try {
                Thread.sleep(2000);
                System.out.println("Coffee is ready for "+name);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        @Override
        public void run() {
            CoffeeMachine.makeCoffee(this.user);
        }
    }

    public static void main(String[] args) {
        ExecutorService coffeeMachines = Executors.newFixedThreadPool(3);
        String[] users = new String[]{"Jaydeep", "Harsh", "Prabhav", "Palak"};

        for (String user : users) {
            coffeeMachines.submit(new CoffeeMachine(user));
        }

        coffeeMachines.shutdown();
    }
}
