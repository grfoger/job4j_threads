package ru.job4j.queue;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    private int capacity;

    public SimpleBlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();

    public synchronized void offer(T value) {
        while (queue.size() == capacity) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        this.notify();
        queue.offer(value);
    }

    public synchronized T poll() {
        while (queue.isEmpty()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        this.notify();
        return queue.poll();
    }
}
