package com.intuit.sa001.service;

import com.intuit.sa001.model.SalesRecord;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Contains analysis methods implemented using Java Streams & lambdas.
 */
public class SalesAnalyzer {

    /**
     * Total revenue = sum(quantity * unitPrice) across all records.
     */
    public double totalRevenue(List<SalesRecord> records) {
        return records.stream()
                .mapToDouble(r -> r.getQuantity() * r.getUnitPrice())
                .sum();
    }

    /**
     * Count of orders by region.
     */
    public Map<String, Long> ordersByRegion(List<SalesRecord> records) {
        return records.stream()
                .collect(Collectors.groupingBy(
                        SalesRecord::getRegion,
                        Collectors.counting()
                ));
    }

    /**
     * Total revenue by product.
     */
    public Map<String, Double> revenueByProduct(List<SalesRecord> records) {
        return records.stream()
                .collect(Collectors.groupingBy(
                        SalesRecord::getProduct,
                        Collectors.summingDouble(r -> r.getQuantity() * r.getUnitPrice())
                ));
    }

    /**
     * Top-selling product by revenue.
     */
    public Optional<String> topProductByRevenue(List<SalesRecord> records) {
        return revenueByProduct(records).entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
    }

    /**
     * Average order value (total revenue divided by number of orders).
     */
    public double averageOrderValue(List<SalesRecord> records) {
        if (records.isEmpty()) {
            return 0.0;
        }
        return totalRevenue(records) / records.size();
    }

    /**
     * Returns the customer who spent the most (by total revenue).
     */
    public Optional<String> topCustomerBySpend(List<SalesRecord> records) {
        Map<String, Double> spendByCustomer = records.stream()
                .collect(Collectors.groupingBy(
                        SalesRecord::getCustomer,
                        Collectors.summingDouble(r -> r.getQuantity() * r.getUnitPrice())
                ));

        return spendByCustomer.entrySet().stream()
                .max(Map.Entry.comparingByValue(Comparator.naturalOrder()))
                .map(Map.Entry::getKey);
    }
}
