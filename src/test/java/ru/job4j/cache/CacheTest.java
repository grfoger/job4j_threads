package ru.job4j.cache;

import org.junit.Assert;
import org.junit.Test;

public class CacheTest {

    @Test
    public void whenAddUpdate() {
        Cache cache = new Cache();
        Base model1 = new Base(1, 1);
        Assert.assertTrue(cache.add(model1));
        model1.setName("один");
        Assert.assertTrue(cache.update(model1));
        model1.setName("два");
        Assert.assertTrue(cache.update(cache.get(model1.getId())));
        Assert.assertEquals(3, cache.get(model1.getId()).getVersion());
    }

    @Test
    public void whenAddTwice() {
        Cache cache = new Cache();
        Base model1 = new Base(1, 1);
        Assert.assertTrue(cache.add(model1));
        Assert.assertFalse(cache.add(model1));
    }

    @Test
    public void whenMultiAdd() {
        Cache cache = new Cache();
        Base model1 = new Base(1, 1);
        Base model2 = new Base(2, 1);
        Base model3 = new Base(3, 1);
        Assert.assertTrue(cache.add(model1));
        Assert.assertTrue(cache.add(model2));
        Assert.assertTrue(cache.add(model3));
    }

    @Test
    public void whenAddAndDelete() {
        Cache cache = new Cache();
        Base model1 = new Base(1, 1);
        Assert.assertTrue(cache.add(model1));
        cache.delete(model1);
    }

}
