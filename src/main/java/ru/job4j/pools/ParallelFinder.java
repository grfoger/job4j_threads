package ru.job4j.pools;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelFinder<T> extends RecursiveTask<Integer> {

    private final T[] array;
    private final int from;
    private final int to;
    private final T obj;

    public ParallelFinder(T[] array, int from, int to, T obj) {
        this.array = array;
        this.from = from;
        this.to = to;
        this.obj = obj;
    }

    private int checkLoop() {
        for (int i = from; i <= to; i++) {
            if (obj.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }


    @Override
    protected Integer compute() {
        if (to - from <= 10) {
            return checkLoop();
        }
        int mid = (from + to) / 2;
        ParallelFinder<T> leftFind = new ParallelFinder<>(array, from, mid, obj);
        ParallelFinder<T> rightFind = new ParallelFinder<>(array, mid + 1, to, obj);
        leftFind.fork();
        rightFind.fork();
        int left = leftFind.join();
        int right = rightFind.join();
        return Math.max(left, right);
    }

    public static <T> int find(T[] array, T obj) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelFinder<T>(array, 0, array.length - 1, obj));
    }
}
