package ru.job4j.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) {
        return memory.computeIfPresent(model.getId(), (k, v) -> {
            Base stored = memory.get(k);
            if (stored.getVersion() != v.getVersion()) {
                throw new OptimisticException("Versions are not equal");
            }
            Base newBase = new Base(k, v.getVersion() + 1);
            newBase.setName(model.getName());
            return newBase;
        }) == (memory.get(model.getId()));
    }

    public void delete(Base model) {
       memory.remove(model.getId(), model);
    }

    public Base get(int id) {
        return memory.get(id);
    }
}