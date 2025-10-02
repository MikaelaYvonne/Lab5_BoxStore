/**
 * @author  Gregory McNutt
 *          Section 001-003
 *          Date 10/01/25
 *          Purpose: Create ShelfStable child class from FoodItem
 */
class ShelfStable extends FoodItem {
    int shelfNumber;
    String isleCategory;

    /**
     * Constructor for StoreItem class
     *
     * @param skuNumber int - super
     * @param price double - super
     * @param name String - super
     * @param itemType String - super
     * @param foodCategory String - super
     * @param isShelfStable boolean - super
     */
    ShelfStable(int skuNumber, double price, String name, String itemType, String foodCategory, boolean isShelfStable, int shelfNumber, String isleCategory) {
        super(skuNumber, price, name, itemType, foodCategory, isShelfStable);
        this.shelfNumber = shelfNumber;
        this.isleCategory = isleCategory;
    }

// Behaviors / Methods

    //Getters / Accessors

    /**
     *
     * @return shelfNumber
     */
    public int getShelfNumber(){
        return this.shelfNumber;
    }

    /**
     *
     * @return isleCategory
     */
    public String getIsleCategory(){
        return this.isleCategory;
    }

    // Setters / Modifiers

    /**
     *
     * @param shelfNumber int
     */
    public void setShelfNumber(int shelfNumber){
        this.shelfNumber = shelfNumber;
    }

    /**
     *
     * @param isleCategory String
     */
    public void setIsleCategory(String isleCategory){
        this.isleCategory = isleCategory;
    }

    //TODO: add override method to this class
}
