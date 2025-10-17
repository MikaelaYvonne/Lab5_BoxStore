/**
 * @author  Mikaela Yvonne Dacanay
 *          Section 001-003
 *          Date 10/01/25
 *          Purpose: Represents fruit items in a store inventory system,
 *                   extending the food item category with a ripeness indicator
 *                   to help manage perishable produce
 */

public class Fruit extends FoodItem {

    //properties / attributes
    private final boolean isRipe;

    //Constructor
    /**
     * Constructor for the Fruit class.
     * Creates a fruit item with the specified properties.
     *
     * @param skuNumber unique identifier of the fruit item
     * @param itemName  name of the fruit item
     * @param price     price of the fruit item
     * @param itemCount quantity of the fruit item in stock
     * @param kCal      calorie content of the fruit item in kilocalories
     * @param isRipe    indicates whether the fruit item is ripe
     */
    public Fruit(int skuNumber, String itemName, double price,
                 int itemCount, int kCal, boolean isRipe) {

        // initializes the inherited properties from FoodItem
        super(skuNumber, itemName, price, itemCount, kCal);

        // sets fruit specific property
        this.isRipe = isRipe;
    }

    //getters
    /**
     * Determines if the fruit is ripe.
     *
     * @return true if the fruit is ripe, false otherwise.
     */
    public boolean getIsRipe() { return isRipe; }


    // other methods
    /**
     * Returns a string representation of the Fruit object, including its superclass details
     * and the ripeness status of the fruit.
     *
     * @return a string combining the formatted details of the superclass and the ripeness
     *         status of the fruit.
     */
    @Override
    public String toString() {
        return super.toString() + ", Ripe: " + isRipe;
    }


}
