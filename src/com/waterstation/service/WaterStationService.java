
package com.waterstation.service;

import java.time.LocalDateTime;
import java.util.List;

import com.waterstation.model.WaterOrder;
import com.waterstation.repository.OrderRepository;

public class WaterStationService {

    private OrderRepository repository;
    private int stock = 100;
    private double pricePerContainer = 30.00;
    private int nextOrderId = 1;
    private double totalSales = 0.0;

    public WaterStationService(OrderRepository repository) {
        this.repository = repository;
    }

    public WaterOrder placeOrder(String customerName,
                                 int quantity,
                                 boolean delivery) {

        if (customerName == null || customerName.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Customer name cannot be empty.");
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException(
                    "Quantity must be greater than zero.");
        }

        if (quantity > stock) {
            throw new IllegalArgumentException(
                    "Not enough water stock available.");
        }

        double totalAmount = quantity * pricePerContainer;

        WaterOrder order = new WaterOrder(
                nextOrderId,
                customerName,
                quantity,
                delivery,
                totalAmount,
                LocalDateTime.now()
        );

        stock -= quantity;
        totalSales += totalAmount;
        nextOrderId++;

        repository.save(order);

        return order;
    }

    public void addStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException(
                    "Stock quantity must be greater than zero.");
        }

        stock += quantity;
    }

    public void setPrice(double newPrice) {
        if (newPrice <= 0) {
            throw new IllegalArgumentException(
                    "Price must be greater than zero.");
        }

        pricePerContainer = newPrice;
    }

    public int getStock() {
        return stock;
    }

    public double getPricePerContainer() {
        return pricePerContainer;
    }

    public double getTotalSales() {
        return totalSales;
    }

    public List<WaterOrder> getOrderHistory() {
        return repository.findAll();
    }
}

