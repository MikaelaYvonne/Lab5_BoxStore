/**
 * @author  Mikaela Yvonne Dacanay
 *          Section 001-003
 *          Date 10/01/25
 *          Purpose:
 */


public class Furniture extends HouseholdItem {
    private final String dimensions;

    public Furniture(int skuNumber, String itemName, double price, int itemCount,
                     String brand, String category, String dimensions) {
        super(skuNumber, itemName, price, itemCount, brand, category);
        this.dimensions = dimensions;
    }

    public String getDimensions() { return dimensions; }

    @Override
    public String toString() {
        return super.toString() + ", Dimensions: " + dimensions;
    }
}