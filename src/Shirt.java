/**
 * @author  Mikaela Yvonne Dacanay
 *          Section 001-003
 *          Date 10/01/25
 *          Purpose: Represents a shirt in a store inventory system,
 *                   extending the clothing item category with a sleeve type
 *                   attribute
 */


public class Shirt extends ClothingItem {

    // properties/attributes
    private String sleeveType;

    // constructor
    public Shirt(int skuNumber, String itemName, double price, int itemCount,
                 String size, String color, String sleeveType){

        // initializes the inherited properties from ClothingItem
        super(skuNumber, itemName, price, itemCount, size, color);

        // sets shirt specific attribute
        this.sleeveType = sleeveType;
    }

    //getters

    /**
     * Retrieves the type of sleeve associated with the shirt.
     *
     * @return the sleeve type as a String.
     */
    public String getSleeveType() { return sleeveType; }

    // other methods

    /**
     * Returns a string representation of the Shirt object. This string is a
     * combination of the details provided by the superclass's toString method
     * and the specific sleeve type attribute of the Shirt class.
     *
     * @return a string combining the details of the Shirt object, including
     *         the sleeve type and other attributes inherited from its superclass.
     */
    @Override
    public String toString() {
        return super.toString() + ", Sleeves: " + sleeveType;
    }

}
