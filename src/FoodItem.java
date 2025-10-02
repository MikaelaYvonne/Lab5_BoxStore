/**
 * Author: Mikaela Yvonne Dacanay
 *
 */

public abstract class FoodItem extends StoreItem{
    public FoodItem(int skuNumber, String itemName, double price, int itemCount){
        super(skuNumber, itemName, price, itemCount);
    }

    @Override
    public double calculateTax() {
        return getPrice() * FOOD_TAX; // lower tax for food
    }

    @Override
    public String getReturnPolicy() {
        return "Food items are non-refundable";
    }
}
