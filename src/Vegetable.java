/**
 * @author  Mikaela Yvonne Dacanay
 *          Section 001-003
 *          Date 10/01/25
 *          Purpose:
 */

public class Vegetable extends FoodItem{
    private String variety;

    /**
     * Constructor for the Vegetable class.
     * Creates a vegetable item with the specified properties.
     *
     * @param skuNumber unique identifier of the vegetable item
     * @param itemName  name of the vegetable item
     * @param price     price of the vegetable item
     * @param itemCount quantity of the vegetable item in stock
     * @param calories  calorie content of the vegetable item in kilocalories
     * @param variety   specific variety or type of the vegetable
     */
    public Vegetable(int skuNumber, String itemName, double price, int itemCount, int calories, String variety) {
        super(skuNumber, itemName, price, itemCount, calories);
        this.variety = variety;
    }

    /**
     * Retrieves the specific variety of the vegetable.
     *
     * @return the variety of the vegetable as a String.
     */
    public String getVariety() { return variety; }


    /**
     * Generates a string representation of the Vegetable object.
     * the specific variety of the vegetable.
     *
     * @return a string combining the formatted details of the superclass
     *         and the variety information of the Vegetable.
     */
    @Override
    public String toString() {
        return super.toString() + ", Variety: " + variety;
    }
}
