package ru.job4j.queue;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleBlockingQueueTest {

    @Test
    public void whenFewProducers() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(10);
        Thread producer1 = new Thread(
                () -> {
                    IntStream.range(0, 5).forEach(x -> {
                        try {
                            queue.offer(x);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                }
        );
        Thread producer2 = new Thread(
                () -> {
                    IntStream.range(5, 10).forEach(x -> {
                        try {
                            queue.offer(x);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                }
        );
        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        producer1.start();
        producer1.join();
        producer2.start();
        producer2.join();
        consumer.interrupt();
        consumer.join();
        assertThat(buffer, is(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)));
    }

    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);
        Thread producer = new Thread(
                () -> {
                    IntStream.range(0, 5).forEach(x -> {
                        try {
                            queue.offer(x);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                }
        );
        producer.start();
        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        assertThat(buffer, is(Arrays.asList(0, 1, 2, 3, 4)));
    }

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
