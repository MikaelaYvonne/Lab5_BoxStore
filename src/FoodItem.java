/**
 * @author  Gregory McNutt
 *          Section 001-003
 *          Date 9/27/25
 *          Purpose: Create FoodItem child class from StoreItem
 */
class FoodItem extends StoreItem{
    // Properties / Attributes
    private String foodCategory;
    private boolean isShelfStable;


    // Constructor

    /**
     *
     * @param skuNumber
     * @param price
     * @param name
     * @param foodCategory
     * @param isShelfStable
     */
    FoodItem(int skuNumber, double price, String name, String itemType, String foodCategory, boolean isShelfStable){
        super(skuNumber, price, name, itemType);
        this.foodCategory = foodCategory;
        this.isShelfStable = isShelfStable;
    }

// Behaviors / Methods

    //Getters / Accessors

    /**
     *
     * @return String foodCategory
     */
    public String getFoodCategory(){
        return this.foodCategory;
    }

    /**
     *
     * @return boolean isShelfStable
     */
    public boolean getIsShelfStable(){
        return this.isShelfStable;
    }


    // Setters / Modifiers

    /**
     *
     * @param foodCategory String
     */
    public void setFoodCategory(String foodCategory){
        this.foodCategory = foodCategory;
    }

    /**
     *
     * @param isShelfStable Boolean
     */
    public void setIsShelfStable(Boolean isShelfStable){
        this.isShelfStable = isShelfStable;
    }


    // Other Methods
    @Override public void printItem(){
        System.out.printf("Sku Number: %d, Price: $%.2f, Name: %s, Item Type: %s", this.getSkuNumber(), this.getPrice(), this.getName(), this.getItemType());
        System.out.printf("%nFood Category: %s, Shelf Stable: %b. %n",this.getItemType(), this.getIsShelfStable());
    }
}
