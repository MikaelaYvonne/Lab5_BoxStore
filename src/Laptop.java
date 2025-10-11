/**
 * @author  Mikaela Yvonne Dacanay
 *          Section 001-003
 *          Date 10/01/25
 *          Purpose:
 */


public class Laptop extends ElectronicsItem {
    private final double screenSize;
    private final int ramGB;

    public Laptop(int skuNumber, String itemName, double price, int itemCount, String itemType, String brand, int warrantyMonths, double screenSize, int ramGB) {
        super(skuNumber, itemName, price, itemCount, itemType, brand, warrantyMonths);
        this.screenSize = screenSize;
        this.ramGB = ramGB;
    }

    public double getScreenSize() { return screenSize; }
    public int getRamGB() { return ramGB; }

    @Override
    public String toString() {
        return super.toString() + ", Screen: " + screenSize + "\", RAM: " + ramGB + "GB";
    }
}
