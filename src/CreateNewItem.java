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
            case 1 : {
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
            case 2 : {
                System.out.println("Adding new Electronics Item");
                getBaseItemInformation(itemData, sc);
                System.out.println("Please Enter the Type Of Food Item: ");
                String[] categoryItems = new String[]{"Fruit", "Vegetable", "ShelfStable Food"};
                showItemsInCategory(categoryItems);
                int typeChoice = sc.nextInt();
                String brand;
                int warrantyMonths;
                System.out.println("Please Enter the item details of the Electronics Item: ");
                System.out.println("Enter the Calories: ");
                brand = sc.nextLine().trim();
            }
            case 3: {

            }
            case 4: {

            }
        }
    }

    private void getBaseItemInformation(ArrayList<Object> itemData, Scanner scanner){
        /***
         *                              1. Food");
         *         System.out.println("  2. Electronics");
         *         System.out.println("  3. Clothing");
         *         System.out.println("  4. Household");
         *         System.out.println("  5. Back");
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
