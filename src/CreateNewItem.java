/**
 * Author: Mikaela Yvonne Dacanay, Gregory McNutt, Thomas Wesley;
 * CSC 331 - 003
 * Date:October 1, 2025
 * Purpose: Handles the creation of new store items. The class creates new
 * items to the inventory by collecting the information from the user based on the item
 * type selected.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CreateNewItem {
    public CreateNewItem(ArrayList<StoreItem> inventory){
        ArrayList<Object> itemData = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int category = WQSDacanayMcNuttWesley.selectCategory();
        StoreItem newItem = null;
        /*
         * 1. Food
         * 2. Electronics
         * 3. clothing
         * 4. household
         * 5. back
         */
        switch(category){
            case 5 : {
                break;
            }
            //food item
            case 1 : {
                //Case handles the creation of Food Items: Fruit, Vegetable, ShelfStable
                System.out.println("Adding new Food Item");
                System.out.println("Please Enter the Type Of Food Item: ");
                String[] categoryItems = new String[]{"Fruit", "Vegetable", "ShelfStable Food"};
                showItemsInCategory(categoryItems); //just shows the available categories
                int typeChoice = sc.nextInt();
                int calories; //the overarching variable for each of the child classes of fooditem
                System.out.println("Please Enter the item details of the Fruit: ");
                getBaseItemInformation(itemData, sc); //goes to get basic StoreItem info
                System.out.println("Enter the Calories: ");
                calories = sc.nextInt();
                switch (typeChoice){
                    //Fruit
                    case 1 -> {
                        System.out.println("Is the fruit currently ripe? (Yes/No)");
                        String isRipe = sc.next();
                        boolean newRipe = false;
                        if (isRipe.equalsIgnoreCase("yes") || isRipe.equalsIgnoreCase("y")){
                            newRipe = true;}
                        newItem = new Fruit((int) itemData.get(0), (String) itemData.get(1),
                                (double) itemData.get(2), (int) itemData.get(3), calories, newRipe);
                        inventory.add(newItem);
                    }
                    //shelf stable
                    case 2 -> {
                        System.out.println("What is the products Expiration Date?");
                        String expirationDate = sc.nextLine().trim();
                        newItem = new ShelfStable((int) itemData.get(0), itemData.get(1).toString(),
                                (double) itemData.get(2), (int) itemData.get(3), calories, expirationDate);
                        inventory.add(newItem);
                    }
                    //vegetable
                    case 3 -> {
                        System.out.println("What is the variety of the vegetable? ");
                        String variety = sc.nextLine().trim();
                        newItem = new Vegetable((int) itemData.get(0), itemData.get(1).toString(),
                                (double) itemData.get(2), (int) itemData.get(3), calories, variety);
                        inventory.add(newItem);
                    }
                }
                System.out.println("New custom item: " + newItem.toString() + " added!");
                break;
            }
            //electronics item
            case 2 : {
                //Handles the creation of Electronics Items: Laptop, TV, Phone
                System.out.println("Adding new Electronics Item");
                System.out.println("Please Enter the Type Of Food Item: ");
                String[] categoryItems = new String[]{"Laptop", "TV", "Phone"};
                showItemsInCategory(categoryItems);
                int typeChoice = sc.nextInt();
                String brand;
                int warrantyMonths;
                System.out.println("Please Enter the item details of the Electronics Item: ");
                getBaseItemInformation(itemData, sc);
                System.out.println("Enter the Brand: ");
                brand = sc.nextLine().trim();
                System.out.println("Enter the amount of warranty months: ");
                warrantyMonths = sc.nextInt();
                switch (typeChoice) {
                    //laptop
                    case 1 -> {
                        System.out.println("Enter the screen size: ");
                        double screenSize = sc.nextDouble();
                        System.out.println("Enter the amount of RAM: ");
                        int ram = sc.nextInt();
                        newItem = new Laptop((int) itemData.get(0), itemData.get(1).toString(),
                                (double) itemData.get(2), (int) itemData.get(3), brand, warrantyMonths,
                                screenSize, ram);
                        inventory.add(newItem);
                    }
                    //tv
                    case 2 -> {
                        System.out.println("Enter the screen size: ");
                        double screenSize = sc.nextDouble();
                        System.out.println("Is it a smart TV? (Yes/No)");
                        String isSmart = sc.next();
                        boolean newSmart = false;
                        if (isSmart.equalsIgnoreCase("yes") || isSmart.equalsIgnoreCase("y")){
                            newSmart = true;}
                        newItem = new TV((int) itemData.get(0), itemData.get(1).toString(),
                                (double) itemData.get(2), (int) itemData.get(3), brand, warrantyMonths,
                                screenSize, newSmart);
                        inventory.add(newItem);
                    }
                    //phone
                    case 3 -> {
                        System.out.println("Enter the carrier name: ");
                        String carrier = sc.next();
                        System.out.println("Enter the amount of storage gigabytes: ");
                        int storage = sc.nextInt();
                        newItem = new Phone((int) itemData.get(0), itemData.get(1).toString(),
                                (double) itemData.get(2), (int) itemData.get(3), brand, warrantyMonths,
                                carrier, storage);
                        inventory.add(newItem);
                    }
                }
                break;
            }
            case 3: {
                // Handles the creation of Outerwear: Shirt, Shoe, Outerwear
                System.out.println("Adding new Clothing Item?");
                System.out.println("Please enter the type of Clothing Item?");
                String[] categoryItems = new String[]{"Shirt", "Shoe", "Outerwear"};
                showItemsInCategory(categoryItems); //displays available clothing types

                //Gets the users selection for type of clothing
                int typeChoice = sc.nextInt();

                //variables for color and size
                String size;
                String color;

                System.out.println("Please Enter the Clothing Item Details:");

                //collects inventory ifo ( Sku, name, price, quantity)
                getBaseItemInformation(itemData, sc);

                //prompts for the size and color
                System.out.println("Enter the Size");

                sc.nextLine();
                size = sc.nextLine().trim();

                System.out.println("Enter the Color:");
                color = sc.nextLine().trim();


                // Process the specific type of clothing item selected
                switch (typeChoice){
                    // SHIRT - requires: sleeveLength (String - "Short" or "Long")
                    case 1 -> {
                        System.out.println("Enter the Sleeve Length (Short/Long): ");
                        String sleeveLength = sc.nextLine().trim();

                        // Create new Shirt object with collected information
                        // Parameters: SKU, name, price, quantity, size, color, sleeveLength
                        newItem = new Shirt((int) itemData.get(0), itemData.get(1).toString(),
                                (double) itemData.get(2), (int) itemData.get(3), size, color, sleeveLength);

                        // Add the new shirt to the inventory
                        inventory.add(newItem);
                    }

                    // SHOE - requires: shoeType (String - "Sneaker", "Boot", "Sandal", etc.)
                    case 2 -> {
                        System.out.println("Enter the Shoe Type (Sneaker/Boot/Sandal/etc.): ");
                        String shoeType = sc.nextLine().trim();

                        // Create new Shoe object with collected information
                        // Parameters: SKU, name, price, quantity, size, color, shoeType
                        newItem = new Shoe((int) itemData.get(0), itemData.get(1).toString(),
                                (double) itemData.get(2), (int) itemData.get(3), size, color, shoeType);

                        // Add the new shoe to the inventory
                        inventory.add(newItem);
                    }


                    case 3 -> {
                        System.out.println("Is the item waterproof? (Yes/No)");
                        String waterproofInput = sc.nextLine().trim();

                        // Convert user's yes/no response to boolean
                        boolean isWaterproof = waterproofInput.equalsIgnoreCase("yes") || waterproofInput.equalsIgnoreCase("y");

                        // Create new Outerwear object with collected information
                        // Parameters: SKU, name, price, quantity, size, color, isWaterproof
                        newItem = new Outerwear((int) itemData.get(0), itemData.get(1).toString(),
                                (double) itemData.get(2), (int) itemData.get(3), size, color, isWaterproof);

                        // Add the new outerwear to the inventory
                        inventory.add(newItem);
                    }
                }

                // Confirm successful addition to inventory
                System.out.println("New custom item: " + newItem.toString() + " added!");
                break;



            }
            case 4: {
                //Handles creation of Household items: Furniture, Cleaning, Supply
                System.out.println("Adding new Household Item");
                System.out.println("Please enter the type of Household Item:");

                String[] categoryItems = new String[]{"Furniture","Cleaning Supply"};
                showItemsInCategory(categoryItems);//shows the available category types

                int typeChoice = sc.nextInt();//gets the users selection

                String brand = "";
                String categoryType;

                System.out.println("Please enter the item details of the Household Item");


                //collect the item info (SKU,name, price, quantity)
                getBaseItemInformation(itemData, sc);

                System.out.println("Enter the Category:");//category prompt for household items
                categoryType = sc.nextLine().trim();

                // Processes the types of household items selected

                switch(typeChoice){
                    //furniture dimensions
                    case 1 ->{
                    System.out.println("Enter the dimensions (ex., 24\"H x 48\"W x 24\"D):");
                    String dimensions = sc.nextLine().trim();

                    //create the furniture object will all collected data
                    newItem = new Furniture((int) itemData.get(0), itemData.get(1).toString(),
                            (double) itemData.get(2), (int) itemData.get(3), brand, categoryType, dimensions);

                    //add to inventory
                    inventory.add(newItem);
                    }
                    //cleaning supplies have a scent and are/arenot eco friendly
                    case 2 ->{
                    System.out.println("Enter the Scent");
                    String scent = sc.nextLine().trim();

                    System.out.println("Is it eco friendly? (yes/no):");
                    String ecoInput = sc.nextLine().trim();

                    //converts the response to a boolean
                    boolean isEcoFriendly = ecoInput.equalsIgnoreCase("yes") || ecoInput.equalsIgnoreCase("y");


                    //create the cleaning object with all parameters
                    newItem = new CleaningSupply((int) itemData.get(0), itemData.get(1).toString(),
                              (double) itemData.get(2), (int) itemData.get(3), brand, scent, isEcoFriendly);

                    //add the new item
                        inventory.add(newItem);

                    }
                }
                //confirm the item was added
                System.out.println("New item:" + newItem.toString() + "added!");
                break;




            }
        }
    }

    private void getBaseItemInformation(ArrayList<Object> itemData, Scanner scanner){
        /*
                  1. Food
                  2. Electronics
                  3. Clothing
                  4. Household
                  5. Back
         */

        System.out.println("Please enter the new item's SKU Number: ");
        int skuNumber = scanner.nextInt();
        System.out.println("Please Enter the new item's Name: ");
        String itemName = scanner.next();
        System.out.println("Please enter the item's Price: ");
        double price = scanner.nextDouble();
        System.out.println("Please enter the item's starting Inventory: ");
        int itemCount = scanner.nextInt();
        itemData.add(skuNumber);
        itemData.add(itemName);
        itemData.add(price);
        itemData.add(itemCount);
    }

    private void showItemsInCategory(String[] categoryItems){
        int howMany = 0;
        for (int i = 0; i < categoryItems.length; i++){
            howMany += 1;
        }
        System.out.println("Please select the type of item to add: ");
        if (howMany == 2){
            System.out.printf("1. %s \n2. %s \n0.Back", categoryItems[0], categoryItems[1]);
        } else {
            System.out.printf("1. %s \n2. %s \n3. %s \n0.Back", categoryItems[0], categoryItems[1], categoryItems[2]);
        }

    }
}
