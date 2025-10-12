/**
 * @author  Mikaela Yvonne Dacanay
 *          Section 001-003
 *          Date 10/01/25
 *          Purpose:
 */


public class TV extends ElectronicsItem {

    private final double screenSize;
    private final boolean isSmart;

    public TV(int skuNumber, String itemName, double price, int itemCount, String brand, int warrantyMonths, double screenSize, boolean isSmart) {
        super(skuNumber, itemName, price, itemCount, brand, warrantyMonths);
        this.screenSize = screenSize;
        this.isSmart = isSmart;
    }

    public double getScreenSize() { return screenSize; }
    public boolean getIsSmart() { return isSmart; }

    @Override
    public String toString() {
        return super.toString() + ", Screen: " + screenSize + "\", Smart: " + isSmart;
    }

    @Override
    public StoreItem copy(){
        return new TV(getSkuNumber(), getItemName(), getPrice(), getItemCount(), getBrand(), getWarrantyMonths(), getScreenSize(), getIsSmart());
    }
}

