package app;

public class Main {
    public static void main(String[] args) {
        int[] arr = {5, 3, 1, 4, 2};

        PerformanceTracker pt = new PerformanceTracker();
        SelectionSort.sort(arr, pt);

        System.out.println("Sorted array:");
        for (int x : arr) {
            System.out.print(x + " ");
        }
        System.out.println();

        System.out.println("Comparisons: " + pt.getComparisons());
        System.out.println("Swaps: " + pt.getSwaps());
        System.out.println("Array accesses: " + pt.getAccesses());
    }
}