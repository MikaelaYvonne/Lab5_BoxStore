/**
 * Author:  Mikaela Yvonne Dacanay, Gregory McNutt;
 *          CSC 331 - 003
 * Date:    October 1, 2025.
 * Purpose: Represents food items in the store inventory.
 *          Food items have a lower tax rate and are non-refundable
 *
 *
 */

public class FoodItem extends StoreItem{
    private final int kCal;
    /**
     * Constructor for FoodItem class
     * creates food item with the specified properties
     *
     * @param skuNumber unique identifier of items
     * @param itemName  name of the food item
     * @param price     price of the food item
     * @param itemCount quantity of the food item in stock
     * @param itemType type of storeItem
     */
    public FoodItem(int skuNumber, String itemName, double price, int itemCount, int kCal, String itemType){
        super(skuNumber, itemName, price, itemCount, itemType);
        this.kCal = kCal;
    }

    /**
     * Retrieves the calorie content of the food item.
     *
     * @return the calorie content in kilocalories (kCal) as an integer.
     */
    public int getKCal() { return kCal; }

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

    /**
     * Returns a string representation of the FoodItem, including its base class details
     * and its calorie content.
     *
     * @return a string combining the formatted details of the super class along with
     *         the calorie information of the FoodItem.
     */
    @Override
    public String toString() {
        return super.toString() + ", Calories: " + kCal;
    }


}
