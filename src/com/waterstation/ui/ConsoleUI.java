
package com.waterstation.ui;

import java.util.List;
import java.util.Scanner;

import com.waterstation.model.WaterOrder;
import com.waterstation.service.WaterStationService;

public class ConsoleUI {

    private Scanner scanner = new Scanner(System.in);
    private WaterStationService service;

    public ConsoleUI(WaterStationService service) {
        this.service = service;
    }

    public void start() {

        int choice;

        do {
            System.out.println("\n================================");
            System.out.println("   WATER REFILLING STATION");
            System.out.println("================================");
            System.out.println("1. Place Water Order");
            System.out.println("2. View Available Stock");
            System.out.println("3. Add Water Stock");
            System.out.println("4. Change Water Price");
            System.out.println("5. View Order History");
            System.out.println("6. View Total Sales");
            System.out.println("7. Exit");
            System.out.println("================================");

            choice = readInt("Enter your choice: ");

            switch (choice) {

                case 1:
                    placeOrder();
                    break;

                case 2:
                    viewStock();
                    break;

                case 3:
                    addStock();
                    break;

                case 4:
                    changePrice();
                    break;

                case 5:
                    viewOrderHistory();
                    break;

                case 6:
                    viewTotalSales();
                    break;

                case 7:
                    System.out.println("Thank you for using our system!");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 7);
    }

    private void placeOrder() {

        scanner.nextLine();

        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();

        int quantity = readInt("Enter number of water containers: ");

        System.out.println("1. Delivery");
        System.out.println("2. Pick-up");

        int type = readInt("Choose order type: ");

        if (type != 1 && type != 2) {
            System.out.println("Invalid order type.");
            return;
        }

        boolean delivery = (type == 1);

        try {
            WaterOrder order = service.placeOrder(
                    name, quantity, delivery);

            System.out.println("\nOrder placed successfully!");
            order.displayOrder();

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void viewStock() {

        System.out.println("\nAvailable stock: "
                + service.getStock() + " containers");

        System.out.println("Price per container: P"
                + service.getPricePerContainer());
    }

    private void addStock() {

        int quantity = readInt("Enter quantity to add: ");

        try {
            service.addStock(quantity);
            System.out.println("Stock added successfully!");

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void changePrice() {

        System.out.print("Enter new price per container: P");

        if (!scanner.hasNextDouble()) {
            System.out.println("Invalid price.");
            scanner.nextLine();
            return;
        }

        double price = scanner.nextDouble();

        try {
            service.setPrice(price);
            System.out.println("Price updated successfully!");

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void viewOrderHistory() {

        List<WaterOrder> orders = service.getOrderHistory();

        if (orders.isEmpty()) {
            System.out.println("No orders recorded yet.");
            return;
        }

        System.out.println("\n========== ORDER HISTORY ==========");

        for (WaterOrder order : orders) {
            order.displayOrder();
        }
    }

    private void viewTotalSales() {

        System.out.println("\nTotal Sales: P"
                + service.getTotalSales());
    }

    private int readInt(String message) {

        while (true) {

            System.out.print(message);

            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            }

            System.out.println("Please enter a valid whole number.");
            scanner.nextLine();
        }
    }
}
