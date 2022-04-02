package ru.job4j.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        /* TODO impl */
        return false;
    }

    public boolean update(Base model) {
        /* TODO impl */
        return false;
    }

    public void delete(Base model) {
        /* TODO impl */
    }
}