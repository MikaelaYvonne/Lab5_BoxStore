/**
 * @author  Mikaela Yvonne Dacanay
 *          Section 001-003
 *          Date 10/01/25
 *          Purpose:
 */


public class Shirt extends ClothingItem {
    private String sleeveType;

    public Shirt(int skuNumber, String itemName, double price, int itemCount,
                 String size, String color, String sleeveType){
        super(skuNumber, itemName, price, itemCount, size, color);
        this.sleeveType = sleeveType;
    }

    public String getSleeveType() { return sleeveType; }

    @Override
    public String toString() {
        return super.toString() + ", Sleeves: " + sleeveType;
    }

}
