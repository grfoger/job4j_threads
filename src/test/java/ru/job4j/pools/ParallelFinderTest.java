package ru.job4j.pools;

import org.junit.Assert;
import org.junit.Test;


public class ParallelFinderTest {

    @Test
    public void testFinder() {
        String[] array = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        int value = ParallelFinder.find(array, "8");
        Assert.assertEquals(8, value);
    }

    @Test
    public void testFinderLessTen() {
        String[] array = {"0", "1", "2", "3", "4", "5", "6", "7", "8"};
        int value = ParallelFinder.find(array, "8");
        Assert.assertEquals(8, value);
    }
}
