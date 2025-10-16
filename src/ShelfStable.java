/**
 * @author  Mikaela Yvonne Dacanay, Gregory McNutt
 *          Section 001-003
 *          Date 10/01/25
 *          Purpose: Create ShelfStable child class from FoodItem
 */
public class ShelfStable extends FoodItem {
    /**
     * Constructor for ShelfStable class.
     *
     * @param skuNumber unique identifier of items
     * @param itemName  name of the food item
     * @param price     price of the food item
     * @param itemCount quantity of the food item in stock
     */
    public ShelfStable(int skuNumber, String itemName, double price, int itemCount) {
        super(skuNumber, itemName, price, itemCount);
    }

// Behaviors / Methods

    //Getters / Accessors


    // Setters / Modifiers


    //TODO: add override method to this class
}
