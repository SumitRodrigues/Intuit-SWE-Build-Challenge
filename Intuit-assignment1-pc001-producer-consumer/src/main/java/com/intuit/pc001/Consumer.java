package com.intuit.pc001;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Consumer retrieves items from the SharedQueue and processes them.
 */
public class Consumer implements Runnable {

    private final SharedQueue queue;
    private final int total;

    public Consumer(SharedQueue queue, int total) {
        this.queue = queue;
        this.total = total;
    }

    @Override
    public void run() {
        try {
            for (int count = 1; count <= total; count++) {

                int value = queue.pull();
                System.out.println("[CONSUMER] Processed → " + value);

                Thread.sleep(ThreadLocalRandom.current().nextInt(100, 200));
            }
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
