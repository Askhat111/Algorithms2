package app;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

public class BenchmarkRunner {
    public static void main(String[] args) throws Exception {
        int n = 1000;
        int trials = 3;
        String dist = "random";
        String csvPath = "docs/results.csv";

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--n")) n = Integer.parseInt(args[++i]);
            else if (args[i].equals("--trials")) trials = Integer.parseInt(args[++i]);
            else if (args[i].equals("--dist")) dist = args[++i];
            else if (args[i].equals("--csv")) csvPath = args[++i];
        }

        Random rnd = new Random();
        try (PrintWriter out = new PrintWriter(new FileWriter(csvPath, true))) {
            if (new java.io.File(csvPath).length() == 0) {
                out.println("dist,n,trial,time_ms,comparisons,swaps,accesses");
            }

            for (int t = 1; t <= trials; t++) {
                int[] arr = new int[n];
                for (int i = 0; i < n; i++) arr[i] = rnd.nextInt(n);

                PerformanceTracker pt = new PerformanceTracker();
                long start = System.nanoTime();
                SelectionSort.sort(arr, pt);
                long elapsed = (System.nanoTime() - start) / 1_000_000;

                out.printf("%s,%d,%d,%d,%d,%d,%d%n",
                        dist, n, t, elapsed,
                        pt.getComparisons(), pt.getSwaps(), pt.getAccesses());
            }
        }
    }
}