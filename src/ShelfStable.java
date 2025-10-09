/**
 * @author  Mikaela Yvonne Dacanay
 *          Section 001-003
 *          Date 10/01/25
 *          Purpose: Create ShelfStable child class from FoodItem
 */
public class ShelfStable extends FoodItem {
    private String expirationDate;

    public ShelfStable(int skuNumber, String itemName, double price, int itemCount, int kCal, String expirationDate) {
        super(skuNumber, itemName, price, itemCount, kCal);
        this.expirationDate = expirationDate;
    }



}