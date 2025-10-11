/**
 * @author  Mikaela Yvonne Dacanay
 *          Section 001-003
 *          Date 10/01/25
 *          Purpose:Represents clothing items in the store inventory system.
 *                  This abstract class extends StoreItem and adds clothing-specific
 *                  attributes such as size and color.
 */


public abstract class ClothingItem extends StoreItem {

    // properties
    private String size; // clothing size
    private String color; // color of the clothing

    /**
     * Constructs a ClothingItem object with the specified parameters.
     *
     * @param skuNumber unique identifier of the clothing item
     * @param itemName  name of the clothing item
     * @param price     price of the clothing item
     * @param itemCount quantity of the clothing item in stock
     * @param size      size of the clothing item
     * @param color     color of the clothing item
     */
    public ClothingItem(int skuNumber, String itemName, double price,
                        int itemCount, String size, String color) {
        // Initialize inherited HouseholdItems properties
        super(skuNumber, itemName, price, itemCount);

        // sets clothing specific properties
        this.size = size;
        this.color = color;
    }


    //getters
    /**
     * Retrieves the size of the clothing item.
     *
     * @return the size of the clothing item as a String
     */
    public String getSize() { return size; }


    /**
     * Retrieves the color of the clothing item.
     *
     * @return the color of the clothing item as a String
     */
    public String getColor() { return color; }


    // methods

    /**
     * Calculates the tax for the item based on its price and a general tax rate.
     *
     * @return the calculated tax as a double value, which is the product of the item's price
     *         and the general tax rate.
     */
    @Override
    public double calculateTax() {
        return getPrice() * GENERAL_TAX;
    }


    /**
     * Provides the return policy for clothing items.
     *
     * @return a string specifying that clothing items can be returned within 90 days,
     *         provided they are unworn and have their tags attached.
     */
    @Override
    public String getReturnPolicy() {
        return "Clothing items can be returned within 90 days, unworn with tags.";
    }


    /**
     * Returns a string representation of the ClothingItem object.
     * The string combines the details from the superclass and includes
     * additional attributes such as the size and color of the clothing item.
     *
     * @return a string containing the formatted details of the clothing item,
     *         including its size and color.
     */
    @Override
    public String toString() {
        return super.toString() + ", Size: " + size + ", Color: " + color;
    }

}
