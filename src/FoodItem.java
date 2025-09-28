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
    FoodItem(int skuNumber, double price, String name, String foodCategory, boolean isShelfStable){
        super(skuNumber, price, name);
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
     * @return isShelfStable
     */
    public boolean getIsShelfStable(){
        return this.isShelfStable;
    }


    // Setters / Modifiers

    /**
     *
     * @param foodCategory
     */
    public void setFoodCategory(String foodCategory){
        this.foodCategory = foodCategory;
    }

    /**
     *
     * @param isShelfStable
     */
    public void setIsShelfStable(Boolean isShelfStable){
        this.isShelfStable = isShelfStable;
    }


    // Other Methods

}
