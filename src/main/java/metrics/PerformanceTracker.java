package metrics;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class PerformanceTracker {

    public static void exportToCSV(String fileName, List<String[]> data) {
        try (FileWriter csvWriter = new FileWriter(fileName)) {
            for (String[] rowData : data) {
                csvWriter.append(String.join(",", rowData));
                csvWriter.append("\n");
            }
            csvWriter.flush();
            System.out.println("CSV saved: " + fileName);
        } catch (IOException e) {
            System.err.println("Error writing CSV file: " + e.getMessage());
        }
    }
}

