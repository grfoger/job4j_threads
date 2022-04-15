package ru.job4j.pools;

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

    private int merge(int leftIndex, int rightIndex) {
        return leftIndex > rightIndex ? leftIndex : rightIndex;
    }

    @Override
    protected Integer compute() {
        if (array.length <= 10) {
            for (int i = 0; i < array.length; i++) {
                if (obj.equals(array[i])) {
                    return i;
                }
            }
        }
        if (from == to) {
            return obj.equals(array[from]) ? from : -1;
        }
        int mid = (from + to) / 2;
        ParallelFinder<T> leftFind = new ParallelFinder<>(array, from, mid, obj);
        ParallelFinder<T> rightFind = new ParallelFinder<>(array, mid + 1, to, obj);
        leftFind.fork();
        rightFind.fork();
        int left = leftFind.join();
        int right = rightFind.join();
        return merge(left, right);
    }
}
