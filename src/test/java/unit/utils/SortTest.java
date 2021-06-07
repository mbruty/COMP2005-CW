package unit.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.ISort;
import utils.Sort;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class SortTest {

    private Sort<Integer> sort;

    @BeforeEach
    void setup() {
        // Create a random array
        Random r = new Random();
        Integer[] arr = new Integer[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = r.nextInt();
        }
        sort = new Sort<>(arr);
    }

    @Test
    void changeStrategy() {
        sort.changeStrategy(new SortInts());
        assertEquals(SortInts.class, sort.getStrategy().getClass());
    }

    @Test
    void doSort() {
        sort.changeStrategy(new SortInts());
        try {
            Integer[] result = sort.doSort(true);
            // Ensure array is sorted
            for (int i = 0; i < result.length - 1; i++) {
                assertTrue(result[i] < result[i + 1]);
            }
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void noStrategyThrows() {
        assertThrows(Exception.class, () -> sort.doSort(), "No strategy set!");
    }

    private class SortInts implements ISort {
        @Override
        public boolean doCompare(Object itemOne, Object itemTwo) {
            return (Integer) itemOne > (Integer) itemTwo;
        }
    }
}