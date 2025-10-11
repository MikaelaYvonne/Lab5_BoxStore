/**
 * @author  Mikaela Yvonne Dacanay
 *          Section 001-003
 *          Date 10/01/25
 *          Purpose:
 */


public class Furniture extends HouseholdItem {

    //properties
    private String dimensions;

    // Constructor
    public Furniture(int skuNumber, String itemName, double price, int itemCount,
                     String brand, String category, String dimensions) {

        // initializes the inherited properties from StoreItem
        super(skuNumber, itemName, price, itemCount, brand, category);

        // sets furniture specific properties
        this.dimensions = dimensions;
    }

    //getters
    /**
     * Retrieves the dimensions of the furniture item.
     *
     * @return a string representing the dimensions of the furniture in the format defined by the implementation.
     */
    public String getDimensions() { return dimensions; }


    // other methods
    /**
     * Returns a string representation of the Furniture object, including its superclass details
     * and the dimensions of the furniture.
     *
     * @return a string combining the formatted details of the superclass and the dimensions
     *         of the furniture.
     */
    @Override
    public String toString() {
        return super.toString() + ", Dimensions: " + dimensions;
    }
}