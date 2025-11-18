package com.intuit.pc001;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Custom bounded buffer using ReentrantLock and Conditions.
 * Acts as the shared queue between Producer and Consumer.
 */
public class SharedQueue {

    private final Queue<Integer> queue = new LinkedList<>();
    private final int capacity;

    private final ReentrantLock lock = new ReentrantLock(true);
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public SharedQueue(int capacity) {
        this.capacity = capacity;
    }

    public void push(int value) throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == capacity) {
                notFull.await();  // Wait if buffer is full
            }

            queue.offer(value);
            System.out.println("[BUFFER] Added → " + value);

            notEmpty.signal();   // Wake consumer
        } finally {
            lock.unlock();
        }
    }

    public int pull() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                notEmpty.await();  // Wait if buffer is empty
            }

            int value = queue.poll();
            System.out.println("[BUFFER] Removed → " + value);

            notFull.signal();     // Wake producer
            return value;

        } finally {
            lock.unlock();
        }
    }
}
