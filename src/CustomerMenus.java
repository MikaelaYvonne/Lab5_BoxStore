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
        int[] choices = {0,1,2};
        int choice = 0;
        //show user menu
        while (choice == 0) {
            System.out.println("~~~~Welcome to The Wilmington Quick Shop~~~~");
            System.out.println("Please select an Option: ");
            System.out.println("1. Buy Item");
            System.out.println("2. Sell Item");
            System.out.println("0. Exit");
            choice = scanner.nextInt();

            int choiceValidated = choiceValidation(choice, choices);

            switch(choiceValidated){
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
        int[] choices = {0,1,2,3,4};
        int choice = 0;

        while (choice == 0){
            System.out.println("~~~~Time To Add Items!~~~~");
            System.out.println("What type of item would you like to buy?");
            System.out.println("1. Food");
            System.out.println("2. Household");
            System.out.println("3. Electronics");
            System.out.println("4. Clothing");
            System.out.println("0. Go Back");
            choice = scanner.nextInt();
            int choiceValidated = choiceValidation(choice,choices);
            switch (choiceValidated){
                case 0:
                    showMainMenu(scanner);
                    break;
                case 1:
                    addFoodItem(scanner);
                    break;
                case 2:


            }
        }
    }

    private static void addFoodItem(Scanner scanner){
        //menu to add a specific food item
        int[] choices = {0,1,2};
        int choice = 0;

        while (choice == 0){
            System.out.println("What Kind of Food would you like to add?");
            System.out.println("1. Shelf Stable");
            System.out.println("2. Not Shelf Stable");
            choice = scanner.nextInt();

            int choiceValidated = choiceValidation(choice, choices);
        }
//        switch (choiceValidated){
//            case 0;
//        }

    }

    private static void showSellMenu(Scanner scanner){
        //TODO: add stuff here to make it work like the one above :)
    }



    private static int choiceValidation(int choice, int[] intRange) {
        try{
            boolean found = false;
            for (int i: intRange){
                if (i == choice) {
                    found = true;
                    break;
                }
            }
            if (!found){ //checks the list given of intRange array to see if the choice is within it.
                //if it's not in the list, throw the custom exception we built at the top.
                throw new CustomException("Invalid Choice, please input a value from the menu options.");
                }
        } catch (InputMismatchException e){ //catches it if the scanner.nextInt() returns an error.
            System.out.println("Please enter a valid option.");
        } catch (CustomException e) { //custom exception if the user doesn't input 1, 2, or 0
            System.out.println(e.getMessage());
        }
        return choice;
    }


}
