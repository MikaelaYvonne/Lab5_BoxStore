/**
 * @author  Mikaela Yvonne Dacanay; Gregory McNutt;
 *          Section 001-003
 *          Date 9/27/25
 *          Purpose: Create super class for the store items.
 */

public class StoreItem {
   // Properties / Attributes
        private int skuNumber;
        private double price;
        private String itemName;
        private int itemCount;

    // Constructor

    /**
     * Constructor for StoreItem class
     *
     * @param skuNumber unique identifier of items
     * @param price     price of the item
     * @param itemName  name of the item
     * @param itemCount quantity left of the stock item
     */
    public StoreItem(int skuNumber, String itemName, double price, int itemCount){
        this.skuNumber = skuNumber;
        this.price = price;
        this.itemName = itemName;
        this.itemCount = itemCount;
    }

// Behaviors / Methods

    //Getters / Accessors

    /**
     *
     * @return String
     */
    public int getSkuNumber(){
        return this.skuNumber;
    }

    /**
     *
     * @return double price
     */
    public double getPrice(){
        return this.price;
    }

    /**
     *
     * @return String Name
     */
    public String getItemName(){
        return this.itemName;
    }

    /**
     *
     * @return int itemCount
     */
    public int getItemCount() { return this.itemCount; }

    // Setters / Modifiers

    /**
     *
     * @param skuNumber int
     */
    public void setSkuNumber(int skuNumber){
        this.skuNumber = skuNumber;
    }

    /**
     *
     * @param price double
     */
    public void setPrice(double price){
        this.price = price;
    }

    /**
     *
     * @param itemName String
     */
    public void setItemName(String itemName){
        this.itemName = itemName;
    }

    /**
     *
     * @param itemCount int
     */
    public void setItemCount(int itemCount) { this.itemCount = itemCount; }

    /**
     * adds quantity to the item's inventory count
     *
     * @param qty the number of items to be added
     */
    public void addInventory(int qty){
        if (qty > 0) {
            this.itemCount += qty;
        }
    }


    /**
     * sells an item and removes/subtracts it from item's inventory count
     *
     * @param qty the number of items to sell/sold
     * @return true if sale was done; false if stock is not enough
     */
    public boolean sellInventory(int qty) {
        if (qty > 0 && this.itemCount >= qty) {
            this.itemCount -= qty;
            return true;
        }
        return false;
    }


    /**
     * String representation of the item details
     * @return formatted string with item details
     */
    @Override
    public String toString() {
        return String.format("%-10s |   %-25s   |   $%9.2f  |   %8d",
                this.skuNumber, this.itemName, this.price, this.itemCount);
    }

    /**
     *  Method for subclasses to calculate sales tax
     *
     * @return the calculated tax amount for one unit of item
     */
    public abstract double calculateTax();

    /**
     * Method for subclasses to get return policy for specific item category when sold
     *
     * @return string containing the return policy
     */
    public abstract String getReturnPolicy();
}

