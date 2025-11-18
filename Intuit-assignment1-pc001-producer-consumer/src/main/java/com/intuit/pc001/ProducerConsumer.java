package com.intuit.pc001;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Runs the Producer and Consumer using a thread pool.
 */
public class ProducerConsumer {

    public static void main(String[] args) {

        SharedQueue queue = new SharedQueue(4);   // Buffer size
        int items = 12;                           // Total items

        ExecutorService service = Executors.newFixedThreadPool(2);

        service.execute(new Producer(queue, items));
        service.execute(new Consumer(queue, items));

        service.shutdown();

        while (!service.isTerminated()) { }

        System.out.println("\n### Pipeline Completed Successfully ###");
    }
}
