/**
 * @author  Mikaela Yvonne Dacanay
 *          Section 001-003
 *          Date 10/01/25
 *          Purpose: Create ShelfStable child class from FoodItem
 */

public class Fruit extends FoodItem {
    private boolean isRipe;


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
    public Fruit(int skuNumber, String itemName, double price, int itemCount, int kCal, boolean isRipe) {
        super(skuNumber, itemName, price, itemCount, kCal);
        this.isRipe = isRipe;
    }

    public boolean isRipe() { return isRipe; }

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
