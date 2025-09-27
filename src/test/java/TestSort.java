package Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import InsertionSort.InsertionSort;

public class TestSort {

    @Test
    public void testEmptyArray() {
        int[] arr = {};
        InsertionSort.insertionSort(arr);
        assertArrayEquals(new int[]{}, arr);
        assertEquals(0, InsertionSort.getComparisons());
        assertEquals(0, InsertionSort.getSwaps());
        assertEquals(0, InsertionSort.getArrayAccesses());
    }

    @Test
    public void testSingleElement() {
        int[] arr = {5};
        InsertionSort.insertionSort(arr);
        assertArrayEquals(new int[]{5}, arr);
        assertEquals(0, InsertionSort.getComparisons());
        assertEquals(0, InsertionSort.getSwaps());
        assertEquals(0, InsertionSort.getArrayAccesses());
    }

    @Test
    public void testSortedArray() {
        int[] arr = {1, 2, 3, 4, 5};
        InsertionSort.insertionSort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
        assertTrue(InsertionSort.getComparisons() > 0);
        assertTrue(InsertionSort.getSwaps() >= 0);
        assertTrue(InsertionSort.getArrayAccesses() > 0);
    }

    @Test
    public void testUnsortedArray() {
        int[] arr = {5, 3, 1, 4, 2};
        InsertionSort.insertionSort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
        assertTrue(InsertionSort.getComparisons() > 0);
        assertTrue(InsertionSort.getSwaps() > 0);
        assertTrue(InsertionSort.getArrayAccesses() > 0);
    }

    @Test
    public void testArrayWithDuplicates() {
        int[] arr = {3, 1, 3, 2, 1};
        InsertionSort.insertionSort(arr);
        assertArrayEquals(new int[]{1, 1, 2, 3, 3}, arr);
        assertTrue(InsertionSort.getComparisons() > 0);
        assertTrue(InsertionSort.getSwaps() > 0);
        assertTrue(InsertionSort.getArrayAccesses() > 0);
    }
}
