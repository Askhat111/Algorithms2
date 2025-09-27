package app;

public class SelectionSort {
    public static void sort(int[] a, PerformanceTracker pt) {
        int n = a.length;
        boolean improvedAtLeastOnce = false;

        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            boolean foundSmaller = false;

            for (int j = i + 1; j < n; j++) {
                pt.incComparisons();
                pt.incAccesses(2);

                if (a[j] < a[minIdx]) {
                    minIdx = j;
                    foundSmaller = true;
                    improvedAtLeastOnce = true;
                }
            }

            if (minIdx != i) {
                swap(a, i, minIdx, pt);
            }

            if (!foundSmaller && !improvedAtLeastOnce) {
                break; // ранний выход
            }
        }
    }

    private static void swap(int[] a, int i, int j, PerformanceTracker pt) {
        pt.incAccesses(4);
        int t = a[i]; a[i] = a[j]; a[j] = t;
        pt.incSwaps();
    }
}