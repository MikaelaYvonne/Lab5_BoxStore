/**
 * @author  Mikaela Yvonne Dacanay
 *          Section 001-003
 *          Date 10/01/25
 *          Purpose:
 */


public class CleaningSupply extends HouseholdItem {
    private final boolean isToxic;

    public CleaningSupply(int skuNumber, String itemName, double price, int itemCount, String itemType,
                          String brand, String category, boolean isToxic) {
        super(skuNumber, itemName, price, itemCount, itemType, brand, category);
        this.isToxic = isToxic;
    }

    public boolean isToxic() { return isToxic; }

    @Override
    public String toString() {
        return super.toString() + ", Toxic: " + isToxic;
    }
}
