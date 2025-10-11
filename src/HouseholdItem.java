/**
 * @author  Mikaela Yvonne Dacanay
 *          Section 001-003
 *          Date 10/01/25
 *          Purpose:
 */


public abstract class HouseholdItem extends StoreItem{

    //properties
    private String brand;
    private String category; // kitchen stuff; decor; idk


    /**
     * Constructs a HouseholdItem object with the specified details.
     *
     * @param skuNumber unique identifier of the household item
     * @param itemName  name of the household item
     * @param price     price of the household item
     * @param itemCount quantity of the household item in stock
     * @param brand     brand name of the household item
     * @param category  category to which the household item belongs
     */
    public HouseholdItem(int skuNumber, String itemName, double price, int itemCount,
                         String brand, String category) {

        // initializes the inherited properties from StoreItem
        super(skuNumber, itemName, price, itemCount);

        // sets the HouseholdItem specific properties

        this.brand = brand;
        this.category = category;
    }

    // getters
    /**
     * Retrieves the brand of the household item.
     *
     * @return the brand name as a String.
     */
    public String getBrand() { return brand; }

    /**
     * Retrieves the category of the household item.
     *
     * @return the category of the item as a String.
     */
    public String getCategory() { return category; }


    // other methods

    /**
     * Calculates the tax for a household item based on its price and the general tax rate.
     *
     * @return the calculated tax as a double value.
     */
    @Override
    public double calculateTax() {
        return getPrice() * GENERAL_TAX;
    }

    @Override
    public String getReturnPolicy() {
        return "Household items can be returned within 30 days in original packaging.";
    }

    @Override
    public String toString() {
        return super.toString() + ", Brand: " + brand + ", Category: " + category;
    }
}

