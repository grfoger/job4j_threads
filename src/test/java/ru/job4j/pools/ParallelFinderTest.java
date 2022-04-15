package ru.job4j.pools;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class ParallelFinderTest {

    @Test
    public void testFinder() {
        String[] array = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        ParallelFinder<String> pf = new ParallelFinder<>(array, 0, array.length - 1, "8");
        Assert.assertEquals(Optional.ofNullable(pf.compute()), Optional.ofNullable(8));
    }

    @Test
    public void testFinderLessTen() {
        String[] array = {"0", "1", "2", "3", "4", "5", "6", "7", "8"};
        ParallelFinder<String> pf = new ParallelFinder<>(array, 0, array.length - 1, "8");
        Assert.assertEquals(Optional.ofNullable(pf.compute()), Optional.ofNullable(8));
    }
}
