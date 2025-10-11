/**
 * @author  Mikaela Yvonne Dacanay
 *          Section 001-003
 *          Date 10/01/25
 *          Purpose:
 */


public abstract class ClothingItem extends StoreItem {
    private final String size;
    private final String color;

    public ClothingItem(int skuNumber, String itemName, double price,
                        int itemCount, String size, String color) {
        super(skuNumber, itemName, price, itemCount);
        this.size = size;
        this.color = color;
    }

    public String getSize() { return size; }
    public String getColor() { return color; }

    @Override
    public double calculateTax() {
        return getPrice() * GENERAL_TAX;
    }

    @Override
    public String getReturnPolicy() {
        return "Clothing items can be returned within 90 days, unworn with tags.";
    }

    @Override
    public String toString() {
        return super.toString() + ", Size: " + size + ", Color: " + color;
    }

}
