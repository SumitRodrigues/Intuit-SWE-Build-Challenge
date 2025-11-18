package com.intuit.pc001;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Producer creates items and pushes them into the SharedQueue.
 */
public class Producer implements Runnable {

    private final SharedQueue queue;
    private final int total;

    public Producer(SharedQueue queue, int total) {
        this.queue = queue;
        this.total = total;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= total; i++) {

                System.out.println("[PRODUCER] Produced → " + i);
                queue.push(i);

                Thread.sleep(ThreadLocalRandom.current().nextInt(60, 140));
            }
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
