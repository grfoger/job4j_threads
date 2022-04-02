package ru.job4j.queue;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();

    private int capacity;

    public SimpleBlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public synchronized void offer(T value) throws InterruptedException {
        while (queue.size() == capacity) {
            this.wait();
        }
        this.notify();
        queue.offer(value);
    }

    public synchronized T poll() throws InterruptedException {
        while (queue.isEmpty()) {
                this.wait();
        }
        T result = queue.poll();
        this.notify();
        return result;
    }
}
