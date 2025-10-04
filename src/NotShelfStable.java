/**
 * @author  Gregory McNutt
 *          Section 001-003
 *          Date 10/01/25
 *          Purpost: Create NotShelfStable child class from FoodItem
 */
public class NotShelfStable extends FoodItem {
    /**
     * Constructor for NotShelfStable class
     *
     * @param skuNumber unique identifier of items
     * @param itemName  name of food item
     * @param price     price of food item
     * @param itemCount quantity of food item in stock
     */
    NotShelfStable(int skuNumber, String itemName, double price, int itemCount){
        super(skuNumber, itemName, price, itemCount);
    }

// Behaviors / Methods

    //Getters / Accessors


    // Setters / Modifiers


    //TODO: add override method to this class
}
