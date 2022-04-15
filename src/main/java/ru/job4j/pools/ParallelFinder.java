package ru.job4j.pools;

import java.util.concurrent.RecursiveTask;

public class ParallelFinder<T> extends RecursiveTask<Integer> {

    private final T[] array;

    public ParallelFinder(T[] array) {
        this.array = array;
    }

    public int find(T[] array, T obj) {
        return find(array, 0, array.length, obj);
    }

    private int find(T[] array, int from, int to, T obj) {
        if (from == to) {
            return obj.equals(array[from]) ? from : -1;
        }
        int mid = (from + to) / 2;
        return merge(find(array, from, mid, obj), find(array, mid + 1, to, obj));
    }

    private int merge(int leftIndex, int rightIndex) {
        return leftIndex > rightIndex ? leftIndex : rightIndex;
    }

    @Override
    protected Integer compute() {
        return 0;
    }
}
