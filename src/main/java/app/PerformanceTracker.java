package app;

public class PerformanceTracker {
    private long comparisons, swaps, accesses;

    public void incComparisons() { comparisons++; }
    public void incSwaps() { swaps++; }
    public void incAccesses(int k) { accesses += k; }

    public void reset() { comparisons = swaps = accesses = 0; }

    public long getComparisons() { return comparisons; }
    public long getSwaps() { return swaps; }
    public long getAccesses() { return accesses; }
}