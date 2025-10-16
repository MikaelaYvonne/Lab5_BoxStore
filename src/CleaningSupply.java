/**
 * @author  Mikaela Yvonne Dacanay
 *          Section 001-003
 *          Date 10/01/25
 *          Purpose:
 */


public class CleaningSupply extends HouseholdItem {
    private final boolean isToxic;

    public CleaningSupply(int skuNumber, String itemName, double price, int itemCount,
                          String brand, String category, boolean isToxic) {
        super(skuNumber, itemName, price, itemCount, brand, category);
        this.isToxic = isToxic;
    }

    public boolean getIsToxic() { return isToxic; }

    @Override
    public String toString() {
        return super.toString() + ", Toxic: " + isToxic;
    }

    @Override
    public StoreItem copy(){
        return new CleaningSupply(getSkuNumber(), getItemName(), getPrice(), getItemCount(), getBrand(), getCategory(), getIsToxic());
    }
}
