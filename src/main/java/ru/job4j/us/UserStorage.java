package ru.job4j.us;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private final Map<Integer, User> store = new HashMap<>();

    public synchronized boolean add(User user) {
        store.put(user.getId(), user);
        return store.containsKey(user.getId());
    }

    public synchronized boolean update(User user) {
        store.replace(user.getId(), user);
        return store.get(user.getId()).getAmount() == user.getAmount();
    }

    public synchronized boolean delete(User user) {
        store.remove(user.getId());
        return !store.containsKey(user.getId());
    }

    public synchronized void transfer(int fromId, int toId, int amount) {
        User from = store.get(fromId);
        User to = store.get(toId);
        from.setAmount(from.getAmount() - amount);
        to.setAmount(to.getAmount() + amount);
    }

    public synchronized User getUser(int id) {
        return new User(store.get(id).getId(), store.get(id).getAmount());
    }

}
