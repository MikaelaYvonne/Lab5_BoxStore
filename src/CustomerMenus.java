import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

//Custom class to throw our own exceptions for invalid data input by user.
class CustomException extends Exception {
    public CustomException (String message){
        super(message);
        }
}

public class CustomerMenus {
    public static int showMainMenu(Scanner scanner){
        int choice = 0;

        //show user menu
        while (choice == 0) {
            System.out.println("~~~~Welcome to The Wilmington Quick Shop~~~~");
            System.out.println("Please select an Option: ");
            System.out.println("1. Buy Item");
            System.out.println("2. Sell Item");
            System.out.println("0. Exit");
            try{
                choice = scanner.nextInt();
                if (choice != 0 && choice != 1 && choice != 2){
                    throw new CustomException("Invalid Choice, please input a value from the menu options.");
                }
            } catch (InputMismatchException e){ //catches it if the scanner.nextInt() returns an error.
                System.out.println("Please enter a valid option.");
                scanner.next();
            } catch (CustomException e) { //custom exception if the user doesn't input 1, 2, or 0
                System.out.println(e.getMessage());
            }

            switch(choice){
                case 0:
                    System.exit(1);
                    break;
                case 1:
                    showAddItemMenu(scanner);
                    break;
                case 2:
                    showSellMenu(scanner);
                    break;
            }
        }
        return choice;
    }


    private static void showAddItemMenu(Scanner scanner){
        int choice = 0;

        while (choice == 0){
            System.out.println("~~~~Time To Add Items!~~~~");
            System.out.println("What type of item would you like to buy?");
            System.out.println("1. Food");
            System.out.println("2. Household");
            System.out.println("3. Electronics");
            System.out.println("4. Clothing");
            System.out.println("0. Go Back");

            try{
                choice = scanner.nextInt();
            } catch (Exception e){
                System.out.println("Please select an item on the menu");
            }

            switch (choice){
                case 0:
                    showMainMenu(scanner);
                    break;
                case 1:
                    addFoodItem(scanner);
                    break;

                /**
                 * Ok hear me out, what if we make a thing to grab the items from teh StoreItem array that we have
                 * so that way we can not have to show like 50 bazillion menu's. We just use the buyMenu and
                 * use it while working with the objects / data in the StoreItem classes so it works for everything.
                 */
//                case 1:
                    //TODO: create a showBuyFoodMenu method to show options when buying food.
//                    showBuyFoodMenu(scanner);
//                    break;
//                case 2:
//                    showBuyHouseholdMenu(scanner);
//                    break;
//                case 3:
//                    showBuyElectronicsMenu(scanner);


            }
        }
    }

    private static void addFoodItem(Scanner scanner, FoodItem[] foodList){
        //need to create new food item, and add it to arrayList of foodItems

    }

    private static void showSellMenu(Scanner scanner){
        //TODO: add stuff here to make it work like the one above :)
    }

}
