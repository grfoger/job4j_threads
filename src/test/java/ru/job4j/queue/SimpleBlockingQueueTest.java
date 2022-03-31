package ru.job4j.queue;

import org.junit.Assert;
import org.junit.Test;

public class SimpleBlockingQueueTest {

    @Test
    public void threadTest() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);
        Thread producer = new Thread(() -> {
            queue.offer(1);
            queue.offer(2);
            queue.offer(3);
            queue.offer(4);
        });
        Thread consumer = new Thread(() -> {
            Assert.assertEquals(queue.poll(), Integer.valueOf(1));
            Assert.assertEquals(queue.poll(), Integer.valueOf(2));
            Assert.assertEquals(queue.poll(), Integer.valueOf(3));
        });
        producer.start();
        producer.join();
        consumer.start();
        consumer.join();
    }
}
