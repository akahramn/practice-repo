package com.abdullahkahraman.demo.concurrency;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class ConcurrencyTasks {
    public static void main(String[] args) {

        //describing what the thread is supposed to do
        //The Runnable interface is a functional interface and has a single run()
        // method that doesn’t accept any parameters or return any values.
        Runnable runnable = () -> System.out.println("I am Runnable !");
        //Manuel thread create
        Thread thread = new Thread(runnable);
        thread.start();

        //create 10 thread
        try (ExecutorService executorService = Executors.newFixedThreadPool(10)) {

            List<Runnable> listOfRunnable = Arrays.asList(
                    () -> System.out.println("Runnable 1"),
                    () -> System.out.println("Runnable 2"),
                    () -> System.out.println("Runnable 3"),
                    () -> System.out.println("Runnable 4"),
                    () -> System.out.println("Runnable 5"),
                    () -> System.out.println("Runnable 6"),
                    () -> System.out.println("Runnable 7"),
                    () -> System.out.println("Runnable 8"),
                    () -> System.out.println("Runnable 9")
            );

            IntStream.range(0, listOfRunnable.size()).forEach(i -> executorService.submit(listOfRunnable.get(i)));


            //The Callable interface is a generic interface containing a single call()
            // method that returns a generic value V:
            List<Callable<Integer>> listOfCallable = Arrays.asList(
                    () -> {
                        int a = 1;
                        System.out.println("Callable 1");
                        return a;
                    },
                    () -> {
                        int a = 2;
                        System.out.println("Callable 2");
                        return a;
                    },
                    () -> {
                        int a = 3;
                        System.out.println("Callable 3");
                        return a;
                    },
                    () -> {
                        int a = 4;
                        System.out.println("Callable 4");
                        return a;
                    },
                    () -> {
                        int a = 5;
                        System.out.println("Callable 5");
                        return a;
                    },
                    () -> {
                        int a = 6;
                        System.out.println("Callable 6");
                        return a;
                    }
            );

            try {
                List<Future<Integer>> results = executorService.invokeAll(listOfCallable);
                results.forEach(r -> {
                    try {
                        System.out.println("Result = " + r.get());
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                });
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
