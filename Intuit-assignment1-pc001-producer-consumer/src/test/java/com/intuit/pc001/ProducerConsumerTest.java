package com.intuit.pc001;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProducerConsumerTest {

    @Test
    void testProducerConsumerFlow() {
        SharedQueue queue = new SharedQueue(3);
        Thread producer = new Thread(new Producer(queue, 5));
        Thread consumer = new Thread(new Consumer(queue, 5));

        producer.start();
        consumer.start();

        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            fail("Threads interrupted");
        }

        assertTrue(true); // test passes if threads complete without deadlock
    }
}
