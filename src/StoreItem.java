/**
 * @author  Gregory McNutt
 *          Section 001-003
 *          Date 9/27/25
 *          Purpose: Create super class for the store items.
 */

public class StoreItem {
   // Properties / Attributes
        private int skuNumber;
        private double price;
        private String name;

    // Constructor

    /**
     * Constructor for StoreItem class
     *
     * @param skuNumber
     * @param price
     * @param name
     */
    public void StoreItem(int skuNumber, double price, String name){
        this.skuNumber = skuNumber;
        this.price = price;
        this.name = name;
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
    public String getName(){
        return this.name;
    }


    // Setters / Modifiers

    /**
     *
     * @param skuNumber
     */
    public void setSkuNumber(int skuNumber){
        this.skuNumber = skuNumber;
    }

    /**
     *
     * @param price
     */
    public void setPrice(double price){
        this.price = price;
    }

    /**
     *
     * @param name
     */
    public void setName(String name){
        this.name = name;
    }
}
