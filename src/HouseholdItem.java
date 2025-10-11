/**
 * @author  Mikaela Yvonne Dacanay
 *          Section 001-003
 *          Date 10/01/25
 *          Purpose:
 */


public abstract class HouseholdItem extends StoreItem{
    private final String brand;
    private final String category; // kitchen stuff; decor; idk

    public HouseholdItem(int skuNumber, String itemName, double price, int itemCount,
                         String brand, String category) {
        super(skuNumber, itemName, price, itemCount);
        this.brand = brand;
        this.category = category;
    }

    public String getBrand() { return brand; }
    public String getCategory() { return category; }

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

