/**
 * @author  Mikaela Yvonne Dacanay
 *          Section 001-003
 *          Date 10/01/25
 *          Purpose:
 */


public class Phone extends ElectronicsItem {
    private final String carrier;
    private final int storageGB;

    public Phone(int skuNumber, String itemName, double price, int itemCount,
                 String brand, int warrantyMonths, String carrier, int storageGB) {
        super(skuNumber, itemName, price, itemCount, brand, warrantyMonths);
        this.carrier = carrier;
        this.storageGB = storageGB;
    }

    public String getCarrier() { return carrier; }
    public int getStorageGB() { return storageGB; }

    @Override
    public String toString() {
        return super.toString() + ", Carrier: " + carrier + ", Storage: " + storageGB + "GB";
    }
}
