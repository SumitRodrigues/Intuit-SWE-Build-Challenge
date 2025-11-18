package com.intuit.sa001.util;

import com.intuit.sa001.model.SalesRecord;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class to read sales.csv and convert rows to SalesRecord objects.
 */
public class CsvReader {

    public List<SalesRecord> read(String filePath) {
        List<SalesRecord> records = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(Path.of(filePath));

            if (lines.isEmpty()) {
                System.err.println("CSV file is empty: " + filePath);
                return records;
            }

            // Skip header (start from index 1)
            for (int i = 1; i < lines.size(); i++) {
                String line = lines.get(i).trim();
                if (line.isEmpty()) {
                    continue;
                }

                // Simple split by comma; assumes no commas inside fields
                String[] parts = line.split(",");

                if (parts.length < 7) {
                    System.err.println("Skipping malformed line: " + line);
                    continue;
                }

                try {
                    String orderId = parts[0].trim();
                    String customer = parts[1].trim();
                    String region = parts[2].trim();
                    String product = parts[3].trim();
                    int quantity = Integer.parseInt(parts[4].trim());
                    double unitPrice = Double.parseDouble(parts[5].trim());
                    String date = parts[6].trim();

                    SalesRecord record = new SalesRecord(
                            orderId,
                            customer,
                            region,
                            product,
                            quantity,
                            unitPrice,
                            date
                    );
                    records.add(record);

                } catch (NumberFormatException nfe) {
                    System.err.println("Skipping line with invalid number: " + line);
                }
            }

        } catch (IOException e) {
            System.err.println("Failed to read CSV file: " + filePath);
            e.printStackTrace();
        }

        return records;
    }
}
