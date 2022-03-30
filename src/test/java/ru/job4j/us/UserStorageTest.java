package ru.job4j.us;

import org.junit.Assert;
import org.junit.Test;

public class UserStorageTest {

    private static class PayThread extends Thread {
        private final UserStorage store;
        private final int from;
        private final int to;
        private final int value;

        private PayThread(UserStorage store, int from, int to, int value) {
            this.store = store;
            this.from = from;
            this.to = to;
            this.value = value;
        }

        @Override
        public void run() {
            store.transfer(from, to, value);
        }
    }

    @Test
    public void simpleTest() {
        UserStorage storage = new UserStorage();
        Assert.assertTrue(storage.add(new User(1, 150)));
        Assert.assertTrue(storage.add(new User(2, 50)));
        storage.transfer(1, 2, 50);
        Assert.assertEquals(100, storage.getUser(1).getAmount());
        Assert.assertEquals(100, storage.getUser(2).getAmount());
    }

    @Test
    public void threadsTest() throws InterruptedException {

        UserStorage storage = new UserStorage();
        storage.add(new User(1, 1000));
        storage.add(new User(2, 300));
        storage.add(new User(3, 500));
        PayThread thread1 = new PayThread(storage, 1, 2, 300);
        PayThread thread2 = new PayThread(storage, 1, 3, 100);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        Assert.assertEquals(storage.getUser(1).getAmount(),600);
        Assert.assertEquals(storage.getUser(2).getAmount(),600);
        Assert.assertEquals(storage.getUser(3).getAmount(),600);
    }

}
