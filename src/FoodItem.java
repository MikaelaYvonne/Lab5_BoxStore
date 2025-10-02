/**
 * Author:  Mikaela Yvonne Dacanay;
 *          CSC 331 - 003
 * Date:    October 1, 2025
 * Purpose: Represents food items in the store inventory.
 *          Food items have a lower tax rate and are non-refundable
 *
 *
 */

public abstract class FoodItem extends StoreItem{

    /**
     * Constructor for FoodItem class
     * creates food item with the specified properties
     *
     * @param skuNumber unique identifier of items
     * @param itemName  name of the food item
     * @param price     price of the food item
     * @param itemCount quantity of the food item in stock
     */
    public FoodItem(int skuNumber, String itemName, double price, int itemCount){
        super(skuNumber, itemName, price, itemCount);
    }

    /**
     * Calculates food item sales tax
     * Food items have lower sales tax compared to general merchandise
     *
     * @return the calculated tax amount for one unit of food item.
     */
    @Override
    public double calculateTax() {
        return getPrice() * FOOD_TAX; // lower tax for food
    }


    /**
     * Returns the return policy for food items
     * **Health and Food safety regulations
     *
     * @return String stating that food items are non-refundable
     */
    @Override
    public String getReturnPolicy() {
        return "Food items are non-refundable";
    }
}
