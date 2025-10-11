/**
 * @author  Mikaela Yvonne Dacanay
 *          Section 001-003
 *          Date 10/01/25
 *          Purpose:Represents outerwear items (such as jackets and coats)
 *                  in a store inventory system, extending the clothing item
 *                  category
 */


public class Outerwear extends ClothingItem{

    // properties
    private boolean isWaterproof; // material of the clothing item

    // constructor

    /**
     * Constructs an Outerwear object with the specified attributes.
     *
     * @param skuNumber     unique identifier for the outerwear item
     * @param itemName      name of the outerwear item
     * @param price         price of the outerwear item
     * @param itemCount     quantity of the outerwear item in stock
     * @param size          size of the outerwear item
     * @param color         color of the outerwear item
     * @param isWaterproof  indicates whether the outerwear item is waterproof
     */
    public Outerwear(int skuNumber, String itemName, double price, int itemCount,
                     String size, String color, boolean isWaterproof){

        // initializes the inherited properties from ClothingItem
        super(skuNumber, itemName, price, itemCount, size, color);

        // sets outerwear specific property/attribute
        this.isWaterproof = isWaterproof;
    }

    //getters
    /**
     * Indicates if the outerwear item is waterproof.
     *
     * @return true if the item is waterproof, false otherwise.
     */
    public boolean getIsWaterproof() { return isWaterproof; }

    // other methods

    /**
     * Returns the string representation of the Outerwear object, combining
     * inherited properties from the superclass and the waterproof status
     * specific to the Outerwear class.
     *
     * @return a string containing the details of the outerwear item,
     *         including whether it is waterproof.
     */
    @Override
    public String toString() {
        return super.toString() + ", Waterproof: " + isWaterproof;
    }
}
