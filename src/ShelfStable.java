/**
 * @author  Mikaela Yvonne Dacanay
 *          Section 001-003
 *          Date 10/01/25
 *          Purpose: Create ShelfStable child class from FoodItem
 */
public class ShelfStable extends FoodItem {
    private final String expirationDate;

    public ShelfStable(int skuNumber, String itemName, double price, int itemCount, int kCal, String expirationDate) {
        super(skuNumber, itemName, price, itemCount, kCal);
        this.expirationDate = expirationDate;
    }

    /**
     * Retrieves the expiration date of the shelf-stable food item.
     *
     * @return the expiration date as a String.
     */
    public String getExpirationDate() { return expirationDate; }


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