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
        int mMChoice = 0;
        //show user menu
        while (mMChoice == 0) {
            System.out.println("~~~~Welcome to The Wilmington Quick Shop~~~~");
            System.out.println("Please select an Option: ");
            System.out.println("1. Buy Item");
            System.out.println("2. Sell Item");
            System.out.println("0. Exit");
            mMChoice = scanner.nextInt();

            int choiceValidated = choiceValidation(mMChoice, choices);

            switch(choiceValidated){
                case 0:
                    System.exit(1);
                    break;
                case 1, 2:
                    showAddRemoveItemMenu(scanner, mMChoice);
                    break;
                default:
                    System.out.println("Hmm something broke, run the program again please bc the coders messed up :)");
            }
        }
        return mMChoice;
    }


    private static void showAddRemoveItemMenu(Scanner scanner, int mMChoice){
        int[] choices = {0,1,2,3,4};
        int aRIChoice = 0;

        while (aRIChoice == 0){
            System.out.println("~~~~Time To Add Items!~~~~");
            if (mMChoice == 1) {
                System.out.println("What type of item would you like to add?");
            } else {
                System.out.println("What type of item would you like to remove?");
            }
            System.out.println("1. Food");
            System.out.println("2. Household");
            System.out.println("3. Electronics");
            System.out.println("4. Clothing");
            System.out.println("0. Go Back");

            aRIChoice = scanner.nextInt();
            int choiceValidated = choiceValidation(aRIChoice,choices);

            switch (choiceValidated){
                case 0:
                    showMainMenu(scanner);
                    break;
                case 1:
                    addRemoveFoodItem(scanner, aRIChoice);
                    break;
                case 2:
                    addRemoveHouseholdItem(scanner, aRIChoice);
                    break;
                default:
                    System.out.println("Hmm something broke, run the program again please bc the coders messed up :)");
            }
        }
    }


    private static void addRemoveFoodItem(Scanner scanner, int aRIChoice){
        //menu to add a specific food item
        int[] choices = {0,1,2};
        int aRFIchoice = 0;

        while (aRFIchoice == 0){
            System.out.println("What Kind of Food would you like to add?");
            System.out.println("1. Shelf Stable");
            System.out.println("2. Not Shelf Stable");
            System.out.println("0. Back");

            aRFIchoice = scanner.nextInt();

            int choiceValidated = choiceValidation(aRFIchoice, choices);
        }
//        switch (choiceValidated){
//          case 0;
//
//          default:
//          System.out.println("Hmm something broke, run the program again please bc the coders messed up :)");

//        }
    }

    private static void vegFruitAddRemove(Scanner scanner, int aRFIchoice){
        int[] choices = {0,1,2};
        int vFRChoice = 0;

        while (vFRChoice == 0) {
            System.out.println("Fruit or Vegetable?");
            System.out.println("1. Fruit");
            System.out.println("2. Vegetable");
            System.out.println("0. Back");

            vFRChoice = scanner.nextInt();

            int choiceValidated = choiceValidation(vFRChoice, choices);

        }
    }

    private static void addRemoveHouseholdItem(Scanner scanner, int aIChoice){
        //menu to add a specific food item
        int[] choices = {0,1,2};
        int aRHHChoice = 0;

        while (aRHHChoice == 0){
            System.out.println("What Kind of Food would you like to add?");
            System.out.println("1. Shelf Stable");
            System.out.println("2. Not Shelf Stable");
            aRHHChoice = scanner.nextInt();

            int choiceValidated = choiceValidation(aRHHChoice, choices);
        }
//        switch (choiceValidated){
//            case 0;
//        }

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
