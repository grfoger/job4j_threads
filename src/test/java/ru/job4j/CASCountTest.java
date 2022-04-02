package ru.job4j;

import org.junit.Assert;
import org.junit.Test;

public class CASCountTest {


    @Test
    public void CASCountTest() throws InterruptedException {
        CASCount count = new CASCount();
        Thread thread = new Thread(() -> {
            count.increment();
            count.increment();
            count.increment();
        });
        thread.start();
        thread.join();
        Assert.assertEquals(3, count.get());
    }
}
