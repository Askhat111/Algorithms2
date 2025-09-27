package app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SelectionSortTest {

    @Test
    void testReverseSorted() {
        int[] arr = {5, 4, 3, 2, 1};
        SelectionSort.sort(arr, new PerformanceTracker());
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }

    @Test
    void testAlreadySorted() {
        int[] arr = {1, 2, 3, 4, 5};
        SelectionSort.sort(arr, new PerformanceTracker());
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }
}