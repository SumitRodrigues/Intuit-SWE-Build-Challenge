package com.intuit.sa001;

import com.intuit.sa001.model.SalesRecord;
import com.intuit.sa001.service.SalesAnalyzer;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class SalesAnalyzerTest {

    private void print(String testName, Object expected, Object actual) {
        System.out.println("\n=== " + testName + " ===");
        System.out.println("Expected: " + expected);
        System.out.println("Actual:   " + actual);
    }

    @Test
    public void testTotalRevenue() {
        SalesAnalyzer analyzer = new SalesAnalyzer();

        List<SalesRecord> mock = List.of(
                new SalesRecord("1", "John", "West", "Phone", 2, 100.0, "2024-01-01"),
                new SalesRecord("2", "Amy", "East", "Laptop", 1, 1000.0, "2024-01-02")
        );

        double expected = 1200.0;
        double actual = analyzer.totalRevenue(mock);

        print("testTotalRevenue", expected, actual);
        assertEquals(expected, actual);
    }

    @Test
    public void testOrdersByRegion() {
        SalesAnalyzer analyzer = new SalesAnalyzer();

        List<SalesRecord> mock = List.of(
                new SalesRecord("1", "John", "West", "Phone", 1, 200.0, "2024-01-01"),
                new SalesRecord("2", "Amy", "East", "Tablet", 1, 300.0, "2024-01-01"),
                new SalesRecord("3", "Bob", "West", "Laptop", 1, 1200.0, "2024-01-02")
        );

        Map<String, Long> actual = analyzer.ordersByRegion(mock);
        Map<String, Long> expected = Map.of("West", 2L, "East", 1L);

        print("testOrdersByRegion", expected, actual);
        assertEquals(2L, actual.get("West"));
        assertEquals(1L, actual.get("East"));
    }

    @Test
    public void testRevenueByProduct() {
        SalesAnalyzer analyzer = new SalesAnalyzer();

        List<SalesRecord> mock = List.of(
                new SalesRecord("1", "John", "West", "Phone", 2, 500.0, "2024-01-01"),
                new SalesRecord("2", "Amy", "East", "Phone", 1, 500.0, "2024-01-01"),
                new SalesRecord("3", "Bob", "South", "Laptop", 1, 1500.0, "2024-01-02")
        );

        Map<String, Double> actual = analyzer.revenueByProduct(mock);
        Map<String, Double> expected = Map.of(
                "Phone", 1500.0,
                "Laptop", 1500.0
        );

        print("testRevenueByProduct", expected, actual);
        assertEquals(1500.0, actual.get("Laptop"));
        assertEquals(1500.0, actual.get("Phone"));
    }

    @Test
    public void testTopProductByRevenue() {
        SalesAnalyzer analyzer = new SalesAnalyzer();

        List<SalesRecord> mock = List.of(
                new SalesRecord("1", "John", "West", "Tablet", 2, 300.0, "2024-01-01"),
                new SalesRecord("2", "Amy", "East", "Headphones", 5, 50.0, "2024-01-01"),
                new SalesRecord("3", "Bob", "South", "Laptop", 1, 1500.0, "2024-01-02")
        );

        Optional<String> actual = analyzer.topProductByRevenue(mock);
        String expected = "Laptop";

        print("testTopProductByRevenue", expected, actual);
        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());
    }

    @Test
    public void testAverageOrderValue() {
        SalesAnalyzer analyzer = new SalesAnalyzer();

        List<SalesRecord> mock = List.of(
                new SalesRecord("1", "John", "West", "Phone", 1, 500.0, "2024-01-01"),
                new SalesRecord("2", "Amy", "East", "Laptop", 1, 1500.0, "2024-01-02")
        );

        double expected = 1000.0;
        double actual = analyzer.averageOrderValue(mock);

        print("testAverageOrderValue", expected, actual);
        assertEquals(expected, actual);
    }

    @Test
    public void testTopCustomerBySpend() {
        SalesAnalyzer analyzer = new SalesAnalyzer();

        List<SalesRecord> mock = List.of(
                new SalesRecord("1", "John", "West", "Phone", 1, 600.0, "2024-01-01"),
                new SalesRecord("2", "Amy", "East", "Laptop", 1, 1000.0, "2024-01-01"),
                new SalesRecord("3", "Amy", "East", "Tablet", 2, 200.0, "2024-01-02")
        );

        Optional<String> actual = analyzer.topCustomerBySpend(mock);
        String expected = "Amy";

        print("testTopCustomerBySpend", expected, actual);
        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());
    }

    @Test
    public void testEmptyDataset() {
        SalesAnalyzer analyzer = new SalesAnalyzer();
        List<SalesRecord> empty = List.of();

        print("testEmptyDataset.totalRevenue", 0.0, analyzer.totalRevenue(empty));
        print("testEmptyDataset.ordersByRegion", "{}", analyzer.ordersByRegion(empty));
        print("testEmptyDataset.averageOrderValue", 0.0, analyzer.averageOrderValue(empty));

        assertEquals(0.0, analyzer.totalRevenue(empty));
        assertTrue(analyzer.ordersByRegion(empty).isEmpty());
        assertEquals(0.0, analyzer.averageOrderValue(empty));
    }

    @Test
    public void testTopProductTie() {
        SalesAnalyzer analyzer = new SalesAnalyzer();
        List<SalesRecord> mock = List.of(
                new SalesRecord("1", "John", "West", "Phone", 1, 500.0, "2024-01-01"),
                new SalesRecord("2", "Amy", "East", "Laptop", 1, 500.0, "2024-01-01")
        );

        Optional<String> actual = analyzer.topProductByRevenue(mock);

        print("testTopProductTie", "Phone or Laptop", actual.orElse("None"));

        assertTrue(actual.isPresent());
    }

    @Test
    public void testNegativeValues() {
        SalesAnalyzer analyzer = new SalesAnalyzer();

        List<SalesRecord> mock = List.of(
                new SalesRecord("1", "John", "West", "Phone", -1, 500.0, "2024-01-01"),
                new SalesRecord("2", "Amy", "East", "Tablet", 2, -300.0, "2024-01-01")
        );

        double expected = -1100.0;
        double actual = analyzer.totalRevenue(mock);

        print("testNegativeValues", expected, actual);
        assertEquals(expected, actual);
    }
}
