package ru.job4j;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {

    private final AtomicReference<Integer> count = new AtomicReference<>();

    public CASCount() {
        count.set(0);
    }

    public void increment() {
        int ref;
        do {
            ref = count.get();
        } while (!count.compareAndSet(count.get(), ref + 1));
    }

    public int get() {
        return count.get();
    }
}