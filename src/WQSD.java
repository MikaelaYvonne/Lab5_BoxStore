/**
 * Author:  Mikaela Yvonne Dacanay, Gregory McNutt, Thomas Wesley;
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
 */
public class WQSD {

    // The main inventory list. POLYMORPHISM allows us to store all item types here.
    private static ArrayList<StoreItem> inventory = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("   Welcome to Wilmington Quick Shop (WQS)");
        System.out.println("==================================================");
        populateInitialInventory();

        boolean running = true;
        while (running) {
            displayMainMenu();

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
                        System.out.println("\n==================================================");
                        System.out.println("Thank you for using the WQS system. Goodbye!");
                        System.out.println("==================================================");
                        break;
                    default:
                        System.out.println("\n Invalid option. Please select 1, 2, or 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\n Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the bad input
            }
        }
        scanner.close();
    }

    /**
     * Displays the main menu in a clean, formatted style.
     */
    private static void displayMainMenu() {
        System.out.println("\n==================================================");
        System.out.println("              MAIN MENU");
        System.out.println("==================================================");
        System.out.println("  1. Sell an Item");
        System.out.println("  2. Add Item to Inventory");
        System.out.println("  3. Exit");
        System.out.println("==================================================");
        System.out.print("Please choose an option [1-3]: ");
    }

    /**
     * Handles the entire process of selling items to a customer.
     */
    private static void sellItemProcess() {
        ArrayList<StoreItem> shoppingCart = new ArrayList<>();
        boolean continueShopping = true;

        System.out.println("\n==================================================");
        System.out.println("           CUSTOMER SHOPPING MODE");
        System.out.println("==================================================");

        while (continueShopping) {
            int categoryChoice = selectCategory();

            if (categoryChoice == 5) {
                if (shoppingCart.isEmpty()) {
                    System.out.println("\nReturning to main menu...");
                    return;
                }
                continueShopping = false;
                continue;
            }

            if (categoryChoice < 1 || categoryChoice > 4) {
                System.out.println("\n Invalid category. Please try again.");
                continue;
            }

            displayCategoryItemsForSale(categoryChoice);

            System.out.print("\nEnter the item ID to add to cart (or 'done' to proceed to checkout): ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("done")) {
                continueShopping = false;
            } else {
                StoreItem selectedItem = findItemById(input);
                if (selectedItem != null && selectedItem.getItemCount() > 0) {
                    shoppingCart.add(selectedItem);
                    System.out.println("\nSUCCESS '" + selectedItem.getItemName() + "' added to cart.");
                    System.out.println("          Cart now contains " + shoppingCart.size() + " item(s).");
                } else if (selectedItem != null && selectedItem.getItemCount() == 0) {
                    System.out.println("\n Item is out of stock.");
                } else {
                    System.out.println("\n Item ID not found.");
                }
            }

            if (continueShopping && !shoppingCart.isEmpty()) {
                System.out.print("\nWould you like to continue shopping? (yes/no): ");
                String continueChoice = scanner.nextLine().trim();
                if (continueChoice.equalsIgnoreCase("no")) {
                    continueShopping = false;
                }
            }
        }

        if (!shoppingCart.isEmpty()) {
            checkout(shoppingCart);
        } else {
            System.out.println("\n[INFO] No items in cart. Returning to main menu.");
        }
    }

    /**
     * Handles the checkout process, including summary, payment, and inventory update.
     * @param cart The list of items the customer wishes to buy.
     */
    private static void checkout(ArrayList<StoreItem> cart) {
        System.out.println("\n==================================================");
        System.out.println("              ORDER SUMMARY");
        System.out.println("==================================================");

        double subtotal = 0.0;
        double totalTax = 0.0;

        // Group items by type for display
        boolean hasFood = cart.stream().anyMatch(item -> item instanceof FoodItem);
        boolean hasElectronics = cart.stream().anyMatch(item -> item instanceof ElectronicsItem);
        boolean hasClothing = cart.stream().anyMatch(item -> item instanceof ClothingItem);
        boolean hasHousehold = cart.stream().anyMatch(item -> item instanceof HouseholdItem);

        if (hasFood) {
            System.out.println("\nFood Items:");
            cart.stream()
                    .filter(item -> item instanceof FoodItem)
                    .forEach(item -> System.out.printf("  - %-30s $%8.2f%n", item.getItemName(), item.getPrice()));
        }

        if (hasElectronics) {
            System.out.println("\nElectronics Items:");
            cart.stream()
                    .filter(item -> item instanceof ElectronicsItem)
                    .forEach(item -> System.out.printf("  - %-30s $%8.2f%n", item.getItemName(), item.getPrice()));
        }

        if (hasClothing) {
            System.out.println("\nClothing Items:");
            cart.stream()
                    .filter(item -> item instanceof ClothingItem)
                    .forEach(item -> System.out.printf("  - %-30s $%8.2f%n", item.getItemName(), item.getPrice()));
        }

        if (hasHousehold) {
            System.out.println("\nHousehold Items:");
            cart.stream()
                    .filter(item -> item instanceof HouseholdItem)
                    .forEach(item -> System.out.printf("  - %-30s $%8.2f%n", item.getItemName(), item.getPrice()));
        }

        for (StoreItem item : cart) {
            subtotal += item.getPrice();
            // *** POLYMORPHISM IN ACTION ***
            // The correct calculateTax() method is called based on the actual object type.
            totalTax += item.calculateTax();
        }

        double total = subtotal + totalTax;
        System.out.println("\n==================================================");
        System.out.printf("Subtotal:                            $%10.2f%n", subtotal);
        System.out.printf("Tax:                                 $%10.2f%n", totalTax);
        System.out.println("--------------------------------------------------");
        System.out.printf("Total:                               $%10.2f%n", total);
        System.out.println("==================================================");

        System.out.print("\nConfirm checkout? (yes/no): ");
        String confirm = scanner.nextLine().trim();

        if (confirm.equalsIgnoreCase("yes")) {
            Set<String> policies = new HashSet<>();
            for (StoreItem item : cart) {
                item.sellInventory(1); // Decrease stock by 1
                // *** POLYMORPHISM IN ACTION ***
                // The correct getReturnPolicy() is retrieved for each item's category.
                policies.add(item.getReturnPolicy());
            }

            System.out.println("\n==================================================");
            System.out.println("           PURCHASE COMPLETE");
            System.out.println("==================================================");
            System.out.println("\nUpdated Inventory for Sold Items:");
            cart.forEach(item -> {
                StoreItem updated = findItemById(String.valueOf(item.getSkuNumber()));
                System.out.printf("  - %-30s (Stock: %d)%n", updated.getItemName(), updated.getItemCount());
            });

            System.out.println("\n==================================================");
            System.out.println("         RETURN POLICIES FOR YOUR PURCHASE");
            System.out.println("==================================================");
            policies.forEach(policy -> System.out.println("  • " + policy));
            System.out.println("==================================================");

        } else {
            System.out.println("\n[INFO] Checkout cancelled. Returning to main menu.");
        }
    }

    /**
     * Handles the process of adding new or existing items to the inventory.
     */
    private static void addItemProcess() {
        System.out.println("\n==================================================");
        System.out.println("           ADD TO INVENTORY");
        System.out.println("==================================================");

        boolean continueAdding = true;

        while (continueAdding) {
            int categoryChoice = selectCategory();

            if (categoryChoice == 5) {
                System.out.println("\nReturning to main menu...");
                return;
            }

            if (categoryChoice < 1 || categoryChoice > 4) {
                System.out.println("\n[ERROR] Invalid category. Please try again.");
                continue;
            }

            displayCategoryItems(categoryChoice);

            System.out.print("\nEnter item ID to add stock, or 'new' to create a new item: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("new")) {
                System.out.println("\n[INFO] New item creation is not implemented in this demo.");
            } else {
                StoreItem item = findItemById(input);
                if (item != null) {
                    System.out.print("How many units to add? ");
                    try {
                        int quantity = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        if (quantity <= 0) {
                            System.out.println("\n[ERROR] Quantity must be positive.");
                        } else {
                            item.addInventory(quantity);
                            System.out.println("\n[SUCCESS] Inventory updated!");
                            System.out.printf("  - %-30s (New Stock: %d)%n", item.getItemName(), item.getItemCount());
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("\n[ERROR] Invalid quantity. Please enter a number.");
                        scanner.nextLine();
                    }
                } else {
                    System.out.println("\n[ERROR] Item ID not found.");
                }
            }

            System.out.print("\nWould you like to add more items? (yes/no): ");
            String continueChoice = scanner.nextLine().trim();
            if (continueChoice.equalsIgnoreCase("no")) {
                continueAdding = false;
                System.out.println("\nReturning to main menu...");
            }
        }
    }

    /**
     * Prompts the user to select an item category.
     * @return The integer choice representing the category.
     */
    private static int selectCategory() {
        System.out.println("\n--------------------------------------------------");
        System.out.println("Select a category:");
        System.out.println("--------------------------------------------------");
        System.out.println("  1. Food");
        System.out.println("  2. Electronics");
        System.out.println("  3. Clothing");
        System.out.println("  4. Household");
        System.out.println("  5. Back");
        System.out.println("--------------------------------------------------");
        System.out.print("Choice [1-5]: ");
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
        System.out.println("\n==================================================");
        System.out.println("           AVAILABLE ITEMS");
        System.out.println("==================================================");
        System.out.printf("%-6s | %-25s | %-10s | %-15s | %-8s | %s%n",
                "ID", "Name", "Price", "Brand", "Stock", "Details");
        System.out.println("------------------------------------------------------------------------------------------------------------------");

        long itemCount = inventory.stream()
                .filter(item -> isItemInCategory(item, categoryChoice))
                .filter(item -> item.getItemCount() > 0)
                .count();

        if (itemCount == 0) {
            System.out.println("  No items available in this category.");
            System.out.println("==================================================");
            return;
        }

        inventory.stream()
                .filter(item -> isItemInCategory(item, categoryChoice))
                .filter(item -> item.getItemCount() > 0)
                .forEach(item -> {
                    String brand = "-";
                    String details = "";

                    if (item instanceof ElectronicsItem) {
                        brand = ((ElectronicsItem) item).getBrand();
                    }
                    if (item instanceof HouseholdItem) {
                        brand = ((HouseholdItem) item).getBrand();
                    }
                    if (item instanceof Laptop) {
                        details = ((Laptop) item).getScreenSize() + "\" | " + ((Laptop) item).getRamGB() + "GB RAM";
                    }
                    if (item instanceof Phone) {
                        details = ((Phone) item).getStorageGB() + "GB Storage";
                    }
                    if (item instanceof Shirt) {
                        details = "Size " + ((ClothingItem)item).getSize() + " | " + ((ClothingItem)item).getColor();
                    }
                    if (item instanceof ClothingItem && !(item instanceof Shirt)) {
                        details = "Size " + ((ClothingItem)item).getSize() + " | " + ((ClothingItem)item).getColor();
                    }

                    System.out.printf("%-6s | %-25s | $%-9.2f | %-15s | %-8d | %s%n",
                            item.getSkuNumber(), item.getItemName(), item.getPrice(),
                            brand, item.getItemCount(), details);
                });
        System.out.println("==================================================");
    }

    /**
     * Displays the full details of items in a given category.
     * @param categoryChoice The integer representing the category.
     */
    private static void displayCategoryItems(int categoryChoice) {
        System.out.println("\n--------------------------------------------------");
        System.out.println("Current Inventory:");
        System.out.println("--------------------------------------------------");

        long itemCount = inventory.stream()
                .filter(item -> isItemInCategory(item, categoryChoice))
                .count();

        if (itemCount == 0) {
            System.out.println("  No items in this category.");
            return;
        }

        inventory.stream()
                .filter(item -> isItemInCategory(item, categoryChoice))
                .forEach(item -> {
                    System.out.printf("  ID: %-6s | %-25s | Stock: %-4d | $%.2f%n",
                            item.getSkuNumber(), item.getItemName(), item.getItemCount(), item.getPrice());
                });
        System.out.println("--------------------------------------------------");
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
        inventory.add(new Shoe(5426, "Running Sneakers", 119.95, 40, "10", "Red", "Sneaker"));
        inventory.add(new Shirt(2454, "V-Neck T-Shirt", 24.50, 100, "M", "White", "Short"));

        // Household
        inventory.add(new CleaningSupply(24525, "All-Purpose Cleaner", 4.99, 75, "CleanCo", "false", false));
        inventory.add(new Furniture(2452, "Oak Coffee Table", 249.00, 10, "HomeStyle", "Oak", "24\"H x 48\"W x 24\"D"));

        System.out.println("\n[SUCCESS] Initial inventory has been populated.");
        System.out.println("          Total items: " + inventory.size());
    }
}
