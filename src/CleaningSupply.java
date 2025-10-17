/**
 * @author  Mikaela Yvonne Dacanay, Gregory McNutt, Thomas Wesley
 *          Section 001-003
 *          Date 10/01/25
 *          Purpose: This program represents a **cleaning
 *          supply item** in a store inventory system,
 *          extending the household item category with an
 *          additional property to track whether the product is toxic.
 */


public class CleaningSupply extends HouseholdItem {

    // properties
    private boolean isToxic;

    /**
     * Constructor for the CleaningSupply class.
     * Creates a cleaning supply item with the specified properties.
     *
     * @param skuNumber unique identifier of the cleaning supply item
     * @param itemName  name of the cleaning supply item
     * @param price     price of the cleaning supply item
     * @param itemCount quantity of the cleaning supply item in stock
     * @param brand     brand of the cleaning supply item
     * @param category  category of the cleaning supply item
     * @param isToxic   indicates whether the cleaning supply item is toxic
     */
    public CleaningSupply(int skuNumber, String itemName, double price, int itemCount,
                          String brand, String category, boolean isToxic) {
        //Initialize inherited HouseholdItems properties
        super(skuNumber, itemName, price, itemCount, brand, category);

        //sets the toxicity of a cleaning supply
        this.isToxic = isToxic;
    }


    /**
     * Determines whether the cleaning supply item is toxic.
     *
     * @return true if the item is toxic, false otherwise.
     */
    public boolean getIsToxic() { return isToxic; }

    /**
     * Returns a string representation of the CleaningSupply object, including
     * details from its superclass and the toxicity status of the cleaning supply.
     *
     * @return a string combining the formatted details of the superclass and
     *         the toxicity status of the cleaning supply.
     */
    @Override
    public String toString() {
        return super.toString() + ", Toxic: " + isToxic;
    }
}
