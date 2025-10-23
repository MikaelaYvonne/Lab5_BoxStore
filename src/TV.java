/**
 * @author  Mikaela Yvonne Dacanay, Gregory McNutt, Thomas Wesley
 *          Section 001-003
 *          Date 10/01/25
 *          Purpose:
 */


public class TV extends ElectronicsItem {

    private double screenSize;
    private boolean isSmart;

    public TV(int skuNumber, String itemName, double price, int itemCount, String brand, int warrantyMonths, double screenSize, boolean isSmart) {
        super(skuNumber, itemName, price, itemCount, brand, warrantyMonths);
        this.screenSize = screenSize;
        this.isSmart = isSmart;
    }

    public double getScreenSize() { return screenSize; }
    public boolean isSmart() { return isSmart; }

    @Override
    public String toString() {
        return super.toString() + ", Screen: " + screenSize + "\", Smart: " + isSmart;
    }
}

