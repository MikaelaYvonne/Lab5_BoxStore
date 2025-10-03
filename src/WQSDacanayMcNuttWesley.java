
import java.util.Scanner;

public class WQSDacanayMcNuttWesley {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean terminate = false;

        while (!terminate){
            int choice = CustomerMenus.showMainMenu(scanner);
            if (choice == 0){
                terminate = true;
            }
        }
    }
}
