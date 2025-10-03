
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class WQSDacanayMcNuttWesley {
    public static ArrayList<StoreItem> inventory = new ArrayList<StoreItem>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean terminate = false;


        // Testing the Arraylist to make sure it can store stuff.
        FoodItem item1 = new FoodItem(1, "item0", 2.00, 55);
        inventory.add(new FoodItem(2, "item1", 1.00, 1));
        inventory.add(item1);

        printInventoryArrays(inventory);



        while (!terminate){
            int choice = CustomerMenus.showMainMenu(scanner);
            if (choice == 0){
                terminate = true;
            }
        }
    }

    //Prints the array of whatever inventory is passed to it as an ArrayList<object>. Ex: ArrayList<FoodItem>, ArrayList<StoreItem>
    public static void printInventoryArrays(ArrayList<StoreItem> inventoryToPrint){
        for (int i = 0; i < inventoryToPrint.toArray().length; i++){
            System.out.println(inventoryToPrint.get(i));
        }
    }

}
