/**
 * Author:  Mikaela Yvonne Dacanay;
 *          CSC 331 - 003
 * Date:    October 1, 2025
 * Purpose: This driver class serves as the main controller and entry point for the
 *          Wilmington Quick Shop (WQS) inventory and sales management system.
 *
 *          Key Functionalities:
 *          - Manages a polymorphic inventory system containing Food, Electronics,
 *            Clothing, and Household items
 *          - Provides a menu-driven interface for selling items and managing stock
 *          - Implements a shopping cart system with checkout and tax calculation
 *          - Demonstrates object-oriented programming principles including inheritance
 *            and polymorphism through unified handling of diverse product types
 *          - Processes sales transactions with order summaries, inventory updates,
 *            and return policy information
 *          - Pre-populates sample inventory data for system demonstration
 *
 *         This application showcases how polymorphism enables flexible management
 *         of different item categories through a single ArrayList<StoreItem>
 *         collection, with category-specific behavior invoked dynamically at runtime.
 *
 **/

import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

/**
 * Driver class for the Wilmington Quick Shop (WQS) inventory and sales system.
 * This program demonstrates inheritance and polymorphism to manage various store items.
 *
 * NOTE: Replace LastName1, LastName2, LastName3 with your group members' last names.
 */
public class WQSDacanayMcNuttWesley {

    // The main inventory list. POLYMORPHISM allows us to store all item types here.
    private static ArrayList<StoreItem> inventory = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        populateInitialInventory();

        boolean running = true;
        while (running) {
            System.out.println("\n--- Wilmington Quick Shop Main Menu ---");
            System.out.println("1. Sell an Item");
            System.out.println("2. Add Item to Inventory");
            System.out.println("3. Exit");
            System.out.print("Please choose an option: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        sellItemProcess();
                        break;
                    case 2:
                        addItemProcess();
                        break;
                    case 3:
                        running = false;
                        System.out.println("Thank you for using the WQS system. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the bad input
            }
        }
        scanner.close();
    }

    /**
     * Handles the entire process of selling items to a customer.
     */
    private static void sellItemProcess() {
        ArrayList<StoreItem> shoppingCart = new ArrayList<>();
        boolean isShopping = true;

        while(isShopping) {
            System.out.println("\n--- Customer Shopping ---");
            int categoryChoice = selectCategory();
            if (categoryChoice == 5) { // Exit option
                isShopping = false;
                continue;
            }

            displayCategoryItemsForSale(categoryChoice);

            System.out.print("Enter the ID of the item to add to cart (or 'done' to checkout): ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("done")) {
                isShopping = false;
            } else {
                StoreItem selectedItem = findItemById(input);
                if (selectedItem != null && selectedItem.getItemCount() > 0) {
                    shoppingCart.add(selectedItem);
                    System.out.println("'" + selectedItem.getItemName() + "' added to cart.");
                } else {
                    System.out.println("Item not found or out of stock.");
                }
            }
        }

        if (!shoppingCart.isEmpty()) {
            checkout(shoppingCart);
        } else {
            System.out.println("No items in cart. Returning to main menu.");
        }
    }

    /**
     * Handles the checkout process, including summary, payment, and inventory update.
     * @param cart The list of items the customer wishes to buy.
     */
    private static void checkout(ArrayList<StoreItem> cart) {
        System.out.println("\n--- Order Summary ---");
        double subtotal = 0.0;
        double totalTax = 0.0;

        // Group items by type for display
        System.out.println("Food Items:");
        cart.stream().filter(item -> item instanceof FoodItem).forEach(item -> System.out.println("  - " + item.getItemName() + ": $" + String.format("%.2f", item.getPrice())));

        System.out.println("Electronics Items:");
        cart.stream().filter(item -> item instanceof ElectronicsItem).forEach(item -> System.out.println("  - " + item.getItemName() + ": $" + String.format("%.2f", item.getPrice())));

        System.out.println("Clothing Items:");
        cart.stream().filter(item -> item instanceof ClothingItem).forEach(item -> System.out.println("  - " + item.getItemName() + ": $" + String.format("%.2f", item.getPrice())));

        System.out.println("Household Items:");
        cart.stream().filter(item -> item instanceof HouseholdItem).forEach(item -> System.out.println("  - " + item.getItemName() + ": $" + String.format("%.2f", item.getPrice())));


        for (StoreItem item : cart) {
            subtotal += item.getPrice();
            // *** POLYMORPHISM IN ACTION ***
            // The correct calculateTax() method is called based on the actual object type.
            totalTax += item.calculateTax();
        }

        double total = subtotal + totalTax;
        System.out.println("---------------------");
        System.out.printf("Subtotal: $%.2f%n", subtotal);
        System.out.printf("Tax:      $%.2f%n", totalTax);
        System.out.printf("Total:    $%.2f%n", total);
        System.out.print("Confirm checkout? (yes/no): ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("yes")) {
            Set<String> policies = new HashSet<>();
            for (StoreItem item : cart) {
                item.sellInventory(1); // Decrease stock by 1
                // *** POLYMORPHISM IN ACTION ***
                // The correct getReturnPolicy() is retrieved for each item's category.
                policies.add(item.getReturnPolicy());
            }
            System.out.println("\n--- Purchase Complete ---");
            System.out.println("Updated inventory for sold items:");
            cart.forEach(item -> System.out.println(" - " + findItemById(String.valueOf(item.getSkuNumber())).toString()));

            System.out.println("\n--- Return Policies for Your Purchase ---");
            policies.forEach(System.out::println);

        } else {
            System.out.println("Checkout cancelled.");
        }
    }

    /**
     * Handles the process of adding new or existing items to the inventory.
     */
    private static void addItemProcess() {
        System.out.println("\n--- Add to Inventory ---");
        int categoryChoice = selectCategory();
        if (categoryChoice == 5) return;

        displayCategoryItems(categoryChoice);
        System.out.print("Enter item ID to add stock, or 'new' to create a new item: ");
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("new")) {
            System.out.println("New item creation is not implemented in this demo.");
            // In a full app, you would ask for all details and create a new object.
        } else {
            StoreItem item = findItemById(input);
            if (item != null) {
                System.out.print("How many to add? ");
                try {
                    int quantity = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    item.addInventory(quantity);
                    System.out.println("Inventory updated:");
                    System.out.println(item.toString());
                } catch (InputMismatchException e) {
                    System.out.println("Invalid quantity.");
                    scanner.nextLine();
                }
            } else {
                System.out.println("Item ID not found.");
            }
        }
    }

    /**
     * Prompts the user to select an item category.
     * @return The integer choice representing the category.
     */
    private static int selectCategory() {
        System.out.println("Select a category:");
        System.out.println("1. Food");
        System.out.println("2. Electronics");
        System.out.println("3. Clothing");
        System.out.println("4. Household");
        System.out.println("5. Back to Main Menu");
        System.out.print("Choice: ");
        try {
            int choice = scanner.nextInt();
            scanner.nextLine();
            return choice;
        } catch (InputMismatchException e) {
            scanner.nextLine();
            return -1; // Invalid choice
        }
    }

    /**
     * Displays items of a specific category in a table format for sale.
     * @param categoryChoice The chosen category.
     */
    private static void displayCategoryItemsForSale(int categoryChoice) {
        System.out.println("\n--- Available Items ---");
        System.out.printf("%-5s | %-25s | %-10s | %-15s | %-10s | %s%n", "ID", "Name", "Price", "Brand", "In Stock", "Description");
        System.out.println("------------------------------------------------------------------------------------------------------");

        inventory.stream()
                .filter(item -> isItemInCategory(item, categoryChoice))
                .filter(item -> item.getItemCount() > 0)
                .forEach(item -> {
                    String brand = "-";
                    String description = "";
                    if (item instanceof ElectronicsItem) brand = ((ElectronicsItem) item).getBrand();
                    if (item instanceof HouseholdItem) brand = ((HouseholdItem) item).getBrand();
                    if (item instanceof Laptop) description = "Screen: " + ((Laptop) item).getScreenSize() + "\", RAM: " + ((Laptop) item).getRamGB() + "GB";
                    if (item instanceof Phone) description = "Storage: " + ((Phone) item).getStorageGB() + "GB";
                    if (item instanceof Shirt) description = "Size: " + ((ClothingItem)item).getSize() + ", Color: " + ((ClothingItem)item).getColor();

                    System.out.printf("%-5s | %-25s | $%-9.2f | %-15s | %-10d | %s%n",
                            item.getSkuNumber(), item.getItemName(), item.getPrice(), brand, item.getItemCount(), description);
                });
    }

    /**
     * Displays the full details of items in a given category.
     * @param categoryChoice The integer representing the category.
     */
    private static void displayCategoryItems(int categoryChoice) {
        System.out.println("\n--- Current Inventory ---");
        inventory.stream()
                .filter(item -> isItemInCategory(item, categoryChoice))
                .forEach(item -> System.out.println(item.toString()));
    }

    /**
     * Checks if an item belongs to a chosen category using instanceof.
     * @param item The StoreItem to check.
     * @param categoryChoice The category to check against.
     * @return true if the item is in the category, false otherwise.
     */
    private static boolean isItemInCategory(StoreItem item, int categoryChoice) {
        switch (categoryChoice) {
            case 1: return item instanceof FoodItem;
            case 2: return item instanceof ElectronicsItem;
            case 3: return item instanceof ClothingItem;
            case 4: return item instanceof HouseholdItem;
            default: return false;
        }
    }

    /**
     * Finds an item in the main inventory by its ID.
     * @param sku The ID to search for.
     * @return The StoreItem if found, otherwise null.
     */
    private static StoreItem findItemById(String sku) {
        for (StoreItem item : inventory) {
            if (String.valueOf(item.getSkuNumber()).equalsIgnoreCase(sku)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Pre-populates the inventory with at least one of each bottom-level class.
     */
    private static void populateInitialInventory() {
        // Food
        inventory.add(new Fruit(1, "Organic Banana", 0.79, 150, 105, true));
        inventory.add(new Vegetable(2, "Heirloom Tomato", 2.99, 80, 22, "Brandywine"));
        inventory.add(new ShelfStable(3, "Canned Corn", 1.29, 200, 120, "12/2026"));

        // Electronics
        inventory.add(new Laptop(545, "ProBook 15", 1299.99, 15, "TechBrand", 24, 15.6, 16));
        inventory.add(new TV(26456, "SmartVision 4K TV", 799.50, 25, "ScreenCorp", 12, 55.0, true));
        inventory.add(new Phone(25245, "Galaxy S25", 999.00, 50, "Samsung", 12, "Unlocked", 256));

        // Clothing
        inventory.add(new Outerwear(5425, "Rain Jacket", 89.99, 30, "L", "Navy", true));
        inventory.add(new Shoe(5425, "Running Sneakers", 119.95, 40, "10", "Red", "Sneaker"));
        inventory.add(new Shirt(2454, "V-Neck T-Shirt", 24.50, 100, "M", "White", "Short"));

        // Household
        inventory.add(new CleaningSupply(24525, "All-Purpose Cleaner", 4.99, 75, "CleanCo", "false", false));
        inventory.add(new Furniture(2452, "Oak Coffee Table", 249.00, 10, "oakey", "HomeStyle","24\"H x 48\"W x 24\"D"));

        System.out.println("Initial inventory has been populated.");
    }
}