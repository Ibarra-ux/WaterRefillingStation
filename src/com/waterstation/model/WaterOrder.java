
package com.waterstation.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WaterOrder {

    private int orderId;
    private String customerName;
    private int quantity;
    private boolean delivery;
    private double totalAmount;
    private LocalDateTime orderDate;

    public WaterOrder(int orderId, String customerName,
                      int quantity, boolean delivery,
                      double totalAmount, LocalDateTime orderDate) {

        this.orderId = orderId;
        this.customerName = customerName;
        this.quantity = quantity;
        this.delivery = delivery;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isDelivery() {
        return delivery;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void displayOrder() {

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        System.out.println("----------------------------");
        System.out.println("Order ID: " + orderId);
        System.out.println("Customer: " + customerName);
        System.out.println("Quantity: " + quantity);
        System.out.println("Order Type: " +
                (delivery ? "Delivery" : "Pick-up"));
        System.out.println("Total Amount: P" + totalAmount);
        System.out.println("Order Date: " +
                orderDate.format(formatter));
        System.out.println("----------------------------");
    }
}
