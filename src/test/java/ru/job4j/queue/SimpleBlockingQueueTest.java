package ru.job4j.queue;

import org.junit.Assert;
import org.junit.Test;

public class SimpleBlockingQueueTest {

    @Test
    public void threadTest() throws InterruptedException {

        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);
        Thread producer = new Thread(() -> {

            try {
                queue.offer(1);
                queue.offer(2);
                queue.offer(3);
                queue.offer(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        Thread consumer = new Thread(() -> {
            try {
                Assert.assertEquals(queue.poll(), Integer.valueOf(1));
                Assert.assertEquals(queue.poll(), Integer.valueOf(2));
                Assert.assertEquals(queue.poll(), Integer.valueOf(3));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        producer.start();
        producer.join();
        consumer.start();
        consumer.join();
    }
}
