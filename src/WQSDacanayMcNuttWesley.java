
import java.util.Scanner;

public class WQSDacanayMcNuttWesley {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean terminate = false;

        while (!terminate){
            int choice = showMainMenu(scanner);
            if (choice == 0){
                terminate = true;
            }
        }



    }
    public static int showMainMenu(Scanner scanner){
        int choice = 0;

        while (choice == 0) {
            System.out.println("~~~~Welcome to The Wilmington Quick Shop~~~~");
            System.out.println("Please select an Option: ");
            System.out.println("1. Buy Item");
            System.out.println("2. Sell Item");
            System.out.println("0. Exit");
            try{
                choice = scanner.nextInt();
            } catch (Exception e){
                System.out.println("Please enter a menu option.");
            }
            switch(choice){
                case 0:
                    System.exit(1);
                    break;
                case 1:
                    showBuyMenu(scanner);
            }
        }
        return choice;
    }


    public static void showBuyMenu(Scanner scanner){
        int choice = 0;

        while (choice == 0){
            System.out.println("~~~~Time To Buy Stuff!~~~~");
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
//                case 1:
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
}
