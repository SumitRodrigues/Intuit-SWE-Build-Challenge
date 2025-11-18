package com.intuit.sa001.model;

public class SalesRecord {

    // Unique ID representing a single sales transaction
    private final String orderId;

    // Name of the customer who placed the order
    private final String customer;

    // Region where the sale occurred (East, West, Midwest, etc.)
    private final String region;

    // Product name (Phone, Laptop, Tablet, etc.)
    private final String product;

    // Number of units sold in this order
    private final int quantity;

    // Price per individual unit for the product
    private final double unitPrice;

    // Date of the order as a String (kept simple for this assignment)
    private final String date;


    public SalesRecord(String orderId,
                       String customer,
                       String region,
                       String product,
                       int quantity,
                       double unitPrice,
                       String date) {

        this.orderId = orderId;
        this.customer = customer;
        this.region = region;
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.date = date;
    }

    // Below are simple getter methods. They allow other classes
    // to access field values without modifying them.

    /**
     * @return The unique order ID for this sale.
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * @return The customer who placed this order.
     */
    public String getCustomer() {
        return customer;
    }

    /**
     * @return The region in which this sale occurred.
     */
    public String getRegion() {
        return region;
    }

    /**
     * @return The product that was sold.
     */
    public String getProduct() {
        return product;
    }

    /**
     * @return Number of units sold in this order.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @return Price per unit for this product.
     */
    public double getUnitPrice() {
        return unitPrice;
    }

    /**
     * @return Date of the sale in String format.
     */
    public String getDate() {
        return date;
    }
}
