/**
 * Author:  Mikaela Yvonne Dacanay;
 *          CSC 331 - 003
 * Date:    October 1, 2025
 * Purpose: Represents electronics items in the store inventory.
 *          Electronics have a higher tax rate and are refundable
 *          within 30 days with receipt
 *
 *
 */

public abstract class ElectronicsItem extends StoreItem {
    private final String brand;
    private final int warrantyMonths;

    public ElectronicsItem(int skuNumber, String itemName, double price, int itemCount,  String itemType,
                           String brand, int warrantyMonths) {
        super(skuNumber, itemName, price, itemCount, itemType);
        this.brand = brand;
        this.warrantyMonths = warrantyMonths;
    }


    public String getBrand() { return brand; }



    public int getWarrantyMonths() { return warrantyMonths; }


    @Override
    public double calculateTax() {
        return getPrice() * GENERAL_TAX;
    }

    @Override
    public String getReturnPolicy() {
        return "Electronics can be returned within 30 days with receipt.";
    }

    @Override
    public String toString() {
        return super.toString() + ", Brand: " + brand + ", Warranty: " + warrantyMonths + " months";
    }
}

