package ru.job4j.buffer;

import ru.job4j.queue.SimpleBlockingQueue;

import java.util.concurrent.atomic.AtomicBoolean;

public class ParallelSearch {

    public static void main(String[] args) throws InterruptedException {
        AtomicBoolean isEnd = new AtomicBoolean(false);
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<Integer>(5);
        final Thread consumer = new Thread(
                () -> {
                    while (true) {
                        try {
                            System.out.println(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        new Thread(
                () -> {
                    for (int index = 0; index != 3; index++) {
                        try {
                            queue.offer(index);
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    isEnd.set(true);
                }


        ).start();
        if (consumer.getState() == Thread.State.WAITING && isEnd.get()) {
            consumer.join();
        }
    }
}