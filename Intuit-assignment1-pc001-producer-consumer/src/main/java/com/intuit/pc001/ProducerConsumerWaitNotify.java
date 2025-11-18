package com.intuit.pc001;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Classic producer-consumer implementation using synchronized + wait/notifyAll.
 * Updated to match the new logging style for clarity.
 */
public class ProducerConsumerWaitNotify {

    // Shared buffer that blocks using intrinsic locks (wait/notify)
    static class SharedBuffer {
        private final Queue<Integer> buffer = new LinkedList<>();
        private final int capacity;

        public SharedBuffer(int capacity) {
            this.capacity = capacity;
        }

        // Producer inserts item
        public synchronized void produce(int value) throws InterruptedException {

            while (buffer.size() == capacity) {
                wait();  // wait if buffer full
            }

            System.out.println("[PRODUCER] Produced → " + value);
            buffer.add(value);
            System.out.println("[BUFFER] Added → " + value);

            notifyAll();  // wake consumer
        }

        // Consumer removes item
        public synchronized int consume() throws InterruptedException {

            while (buffer.isEmpty()) {
                wait();  // wait if buffer empty
            }

            int value = buffer.poll();
            System.out.println("[BUFFER] Removed → " + value);
            System.out.println("[CONSUMER] Processed → " + value);

            notifyAll();  // wake producer
            return value;
        }
    }

    public static void main(String[] args) {

        SharedBuffer buffer = new SharedBuffer(4);  // same size as unique version
        int totalItems = 12;

        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= totalItems; i++) {
                    buffer.produce(i);
                    Thread.sleep(100);
                }
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= totalItems; i++) {
                    buffer.consume();
                    Thread.sleep(150);
                }
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();
    }
}
