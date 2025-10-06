public class Vegetable extends NotShelfStable{
    /**
     * Constructor for NotShelfStable class
     *
     * @param skuNumber unique identifier of items
     * @param itemName  name of food item
     * @param price     price of food item
     * @param itemCount quantity of food item in stock
     */
    Vegetable(int skuNumber, String itemName, double price, int itemCount) {
        super(skuNumber, itemName, price, itemCount);
    }
}
