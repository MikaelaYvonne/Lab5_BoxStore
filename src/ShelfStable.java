/**
 * @author  Mikaela Yvonne Dacanay
 *          Section 001-003
 *          Date 10/01/25
 *          Purpose: Represents shelf-stable food items in a store inventory system,
 *                   extending the food item category with an expiration date property
 */
public class ShelfStable extends FoodItem {

    // properties/ attributes
    private String expirationDate;

    // constructor

    /**
     * Constructor for the ShelfStable class.
     * Creates a shelf-stable food item with the specified properties,
     * including its expiration date.
     *
     * @param skuNumber       unique identifier of the shelf-stable food item
     * @param itemName        name of the shelf-stable food item
     * @param price           price of the shelf-stable food item
     * @param itemCount       quantity of the shelf-stable food item in stock
     * @param kCal            calorie content of the shelf-stable food item in kilocalories
     * @param expirationDate  expiration date of the shelf-stable food item
     */
    public ShelfStable(int skuNumber, String itemName, double price, int itemCount, int kCal,
                       String expirationDate) {
        // initializes the inherited properties from FoodItem
        super(skuNumber, itemName, price, itemCount, kCal);
        this.expirationDate = expirationDate;
    }

    // getters
    /**
     * Retrieves the expiration date of the shelf-stable food item.
     *
     * @return the expiration date as a String.
     */
    public String getExpirationDate() { return expirationDate; }


    // other methods
    /**
     * Returns a string representation of the ShelfStable item, combining details
     * from its superclass and the specific expiration date of the shelf-stable item.
     *
     * @return a formatted string including superclass details and the expiration date.
     */
    @Override
    public String toString() {
        return super.toString() + ", Expires: " + expirationDate;
    }

}