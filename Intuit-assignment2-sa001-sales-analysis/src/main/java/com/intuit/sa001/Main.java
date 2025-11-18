package com.intuit.sa001;

import com.intuit.sa001.model.SalesRecord;
import com.intuit.sa001.service.SalesAnalyzer;
import com.intuit.sa001.util.CsvReader;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Main entry point for the SA-001 Sales Analysis application.
 */
public class Main {

    public static void main(String[] args) {

        String csvPath = "src/main/resources/sales.csv";

        CsvReader reader = new CsvReader();
        List<SalesRecord> records = reader.read(csvPath);

        if (records.isEmpty()) {
            System.out.println("No records loaded. Check CSV path or contents.");
            return;
        }

        SalesAnalyzer analyzer = new SalesAnalyzer();

        double totalRevenue = analyzer.totalRevenue(records);
        Map<String, Long> ordersByRegion = analyzer.ordersByRegion(records);
        Map<String, Double> revenueByProduct = analyzer.revenueByProduct(records);
        Optional<String> topProduct = analyzer.topProductByRevenue(records);
        double avgOrderValue = analyzer.averageOrderValue(records);
        Optional<String> topCustomer = analyzer.topCustomerBySpend(records);

        System.out.println("===== Sales Analysis Results =====");
        System.out.println("Total revenue: " + totalRevenue);
        System.out.println("Average order value: " + avgOrderValue);
        System.out.println();

        System.out.println("Orders by region:");
        ordersByRegion.forEach((region, count) ->
                System.out.println("  " + region + " -> " + count + " orders"));

        System.out.println();
        System.out.println("Revenue by product:");
        revenueByProduct.forEach((product, revenue) ->
                System.out.println("  " + product + " -> " + revenue));

        System.out.println();
        System.out.println("Top product by revenue: " + topProduct.orElse("N/A"));
        System.out.println("Top customer by spend: " + topCustomer.orElse("N/A"));
    }
}
