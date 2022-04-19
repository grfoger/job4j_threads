package ru.job4j.pools;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

public class RolColSumTest {

    @Test
    public void simpleMatrix() {
        int[][] matrix = {
                {1, 2, 3, 4, 5},
                {1, 2, 3, 4, 5},
                {1, 2, 3, 4, 5},
                {1, 2, 3, 4, 5},
                {1, 2, 3, 4, 5}
        };
        RolColSum.Sums[] sums = RolColSum.sum(matrix);
        Assert.assertEquals(15, sums[0].getRowSum());
        Assert.assertEquals(5, sums[0].getColSum());
        Assert.assertEquals(15, sums[2].getRowSum());
        Assert.assertEquals(15, sums[2].getColSum());
    }

    @Test (expected = IllegalArgumentException.class)
    public void wrongMatrix() {
        int[][] matrix = {
                {1, 2},
                {4, 5, 6},
                {7, 8, 9},
                {10, 11, 12, 13}
        };
        RolColSum.Sums[] sums = RolColSum.sum(matrix);
    }

    @Test
    public void simpleAsyncMatrix() throws ExecutionException, InterruptedException {
        int[][] matrix = {
                {1, 2, 3, 4, 5},
                {1, 2, 3, 4, 5},
                {1, 2, 3, 4, 5},
                {1, 2, 3, 4, 5},
                {1, 2, 3, 4, 5}
        };
        RolColSum.Sums[] sums = RolColSum.asyncSum(matrix);
        Assert.assertEquals(15, sums[0].getRowSum());
        Assert.assertEquals(5, sums[0].getColSum());
        Assert.assertEquals(15, sums[2].getRowSum());
        Assert.assertEquals(15, sums[2].getColSum());
    }
}
