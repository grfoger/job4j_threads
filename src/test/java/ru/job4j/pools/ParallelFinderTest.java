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

    @Test
    public void testFinderMoreTen() {
        final int count = 50;
        String[] array = new String[count];
        for (int i = 0; i < count; i++) {
            array[i] = "" + i;
        }
        int value = ParallelFinder.find(array, "42");
        Assert.assertEquals(42, value);
    }

    @Test
    public void testNoFind() {
        String[] array = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        int value = ParallelFinder.find(array, "ничего");
        Assert.assertEquals(-1, value);
    }
}
