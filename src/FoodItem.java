/**
 * @author  Gregory McNutt
 *          Section 001-003
 *          Date 9/27/25
 *          Purpose: Create FoodItem child class from StoreItem
 */
class FoodItem extends StoreItem{
    // Properties / Attributes
    protected String foodCategory;
    protected boolean isShelfStable;


    // Constructor
    FoodItem(int skuNumber, double price, String name, String foodCategory, boolean isShelfStable){
        super(skuNumber, price, name);
        this.foodCategory = foodCategory;
        this.isShelfStable = isShelfStable;
    }

// Behaviors / Methods


    //Getters / Accessors



    // Setters / Modifiers



    // Other Methods

}
