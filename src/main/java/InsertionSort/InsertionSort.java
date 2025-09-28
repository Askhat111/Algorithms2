package InsertionSort;

public class InsertionSort {
    private static int comparisons = 0;
    private static int swaps = 0;
    private static int arrayAccesses = 0;

    public static void insertionSort(int[] arr) {
        comparisons = 0;
        swaps = 0;
        arrayAccesses = 0;

        for (int i = 1; i < arr.length; i++) {
            int current = arr[i];
            arrayAccesses++; 
            int j = i - 1;

            if (j >= 0) {
                comparisons++;
                arrayAccesses++;
            }

            while (j >= 0 && arr[j] > current) {
                comparisons++;
                arr[j + 1] = arr[j];
                arrayAccesses += 2;
                swaps++;
                j--;
                if (j >= 0) arrayAccesses++;
            }

            arr[j + 1] = current;
            arrayAccesses++;
            swaps++;
        }
    }

    public static int getComparisons() {
        return comparisons;
    }

    public static int getSwaps() {
        return swaps;
    }

    public static int getArrayAccesses() {
        return arrayAccesses;
    }

    public static void printArray(int[] arr) {
        for (int n : arr) System.out.print(n + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        int[] data = {3, 4, 6, 1, 2};

        System.out.println("Before Sort:");
        printArray(data);

        insertionSort(data);

        System.out.println("After Sort:");
        printArray(data);

        System.out.println("Comparisons: " + getComparisons());
        System.out.println("Swaps: " + getSwaps());
        System.out.println("Array accesses: " + getArrayAccesses());
    }
}

