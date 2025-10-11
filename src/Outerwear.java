/**
 * @author  Mikaela Yvonne Dacanay
 *          Section 001-003
 *          Date 10/01/25
 *          Purpose:
 */


public class Outerwear extends ClothingItem{
    private final boolean isWaterproof; // I cant think of a different identifier lol

    public Outerwear(int skuNumber, String itemName, double price, int itemCount, String itemType,
                     String size, String color, boolean isWaterproof){
        super(skuNumber, itemName, price, itemCount, itemType, size, color);
        this.isWaterproof = isWaterproof;
    }

    public boolean isWaterproof() { return isWaterproof; }

    @Override
    public String toString() {
        return super.toString() + ", Waterproof: " + isWaterproof;
    }
}
