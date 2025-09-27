package CLI;

import InsertionSort.InsertionSort;
import metrics.PerformanceTracker;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Benchmark {

    private static final List<String[]> dataRows = new ArrayList<>();

    public static int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i=0; i<size; i++) {
            arr[i] = rand.nextInt(size * 10);
        }
        return arr;
    }

    public static int[] generateSortedArray(int size) {
        int[] arr = new int[size];
        for (int i=0; i<size; i++) arr[i] = i;
        return arr;
    }

    public static int[] generateReverseSortedArray(int size) {
        int[] arr = new int[size];
        for (int i=0; i<size; i++) arr[i] = size - i;
        return arr;
    }

    public static void runBenchmark(int size, String type) {
        int[] arr;
        switch(type.toLowerCase()) {
            case "sorted": arr = generateSortedArray(size); break;
            case "reverse": arr = generateReverseSortedArray(size); break;
            case "random": default: arr = generateRandomArray(size); break;
        }

        long start = System.nanoTime();
        InsertionSort.insertionSort(arr);
        long end = System.nanoTime();

        long timeMs = (end - start) / 1_000_000;

        System.out.printf("Size: %d, Type: %s, Time: %d ms, Comparisons: %d, Swaps: %d, Array Accesses: %d\n",
                size, type, timeMs, InsertionSort.getComparisons(), InsertionSort.getSwaps(), InsertionSort.getArrayAccesses());

        dataRows.add(new String[]{
                String.valueOf(size), type, String.valueOf(timeMs),
                String.valueOf(InsertionSort.getComparisons()),
                String.valueOf(InsertionSort.getSwaps()),
                String.valueOf(InsertionSort.getArrayAccesses())
        });
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        dataRows.add(new String[]{"Size", "Type", "TimeMs", "Comparisons", "Swaps", "ArrayAccesses"});

        while (true) {
            System.out.print("Enter array size or 'exit' to stop: ");
            String input = scanner.nextLine();
            if(input.equalsIgnoreCase("exit")) break;

            int size;
            try {
                size = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid size, try again.");
                continue;
            }

            System.out.print("Enter array type (random/sorted/reverse): ");
            String type = scanner.nextLine();

            runBenchmark(size, type);
        }

        PerformanceTracker.exportToCSV("benchmark_results.csv", dataRows);

        System.out.println("Benchmarking complete. CSV saved.");
        scanner.close();
    }
}
