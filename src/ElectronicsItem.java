/**
 * Author:  Mikaela Yvonne Dacanay, Gregory McNutt, Thomas Wesley
 *          CSC 331 - 003
 * Date:    October 1, 2025
 * Purpose: Represents electronics items in the store inventory.
 *          Electronics have a higher tax rate and are refundable
 *          within 30 days with receipt
 *
 *
 */

public abstract class ElectronicsItem extends StoreItem {

    //properties
    private final String brand;
    private final int warrantyMonths; // Electronic items tend to have different warranties from manuf

    /**
     * Constructs an ElectronicsItem with the specified attributes.
     *
     * @param skuNumber       unique identifier for the electronic item
     * @param itemName        name of the electronic item
     * @param price           price of the electronic item
     * @param itemCount       quantity of the electronic item in stock
     * @param brand           brand of the electronic item
     * @param warrantyMonths  warranty period for the electronic item in months
     */
    public ElectronicsItem(int skuNumber, String itemName, double price, int itemCount,
                           String brand, int warrantyMonths) {

        // initializes the inherited properties from StoreItem
        super(skuNumber, itemName, price, itemCount);

        // Sets the brand and warranty of the the Electronic item
        this.brand = brand;
        this.warrantyMonths = warrantyMonths;
    }


    //getters
    /**
     * Retrieves the brand of the electronic item.
     *
     * @return the brand name as a string
     */
    public String getBrand() { return brand; }


    /**
     * Retrieves the warranty period of the electronic item in months.
     *
     * @return the warranty duration in months
     */
    public int getWarrantyMonths() { return warrantyMonths; }


    // other methods
    /**
     * Calculates the tax for the electronic item.
     *
     * @return the calculated tax as a double, based on the item's price and the general tax rate
     */
    @Override
    public double calculateTax() {
        return getPrice() * GENERAL_TAX;
    }


    /**
     * Retrieves the return policy for electronics items.
     *
     * @return a string detailing the return policy, which states that electronics can
     *         be returned within 30 days with a receipt.
     */
    @Override
    public String getReturnPolicy() {
        return "Electronics can be returned within 30 days with receipt.";
    }


    /**
     * Returns a string representation of the electronic item, including its
     * basic details, brand, and warranty period in months.
     *
     * @return a string combining the formatted details of the superclass
     *         with the electronic item's brand and warranty duration.
     */
    @Override
    public String toString() {
        return super.toString() + ", Brand: " + brand + ", Warranty: " + warrantyMonths + " months";
    }

}

