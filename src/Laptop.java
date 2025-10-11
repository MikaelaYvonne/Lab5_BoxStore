/**
 * @author  Mikaela Yvonne Dacanay
 *          Section 001-003
 *          Date 10/01/25
 *          Purpose: Represents a laptop in a store inventory system,
 *                   extending the electronics item category with laptop-specific
 *                   attributes
 */


public class Laptop extends ElectronicsItem {

    // properties
    private double screenSize; // screen size in inches
    private int ramGB;         // RAM capacity in GB

    // Constructor
    /**
     * Constructs a Laptop object with the specified attributes.
     *
     * @param skuNumber       unique identifier for the laptop
     * @param itemName        name of the laptop
     * @param price           price of the laptop
     * @param itemCount       quantity of the laptop in stock
     * @param brand           brand of the laptop
     * @param warrantyMonths  warranty period for the laptop in months
     * @param screenSize      size of the laptop's screen in inches
     * @param ramGB           amount of RAM in the laptop in gigabytes
     */
    public Laptop(int skuNumber, String itemName, double price, int itemCount, String brand,
                  int warrantyMonths, double screenSize, int ramGB) {

        // initializes the inherited properties from ElectronicItems
        super(skuNumber, itemName, price, itemCount, brand, warrantyMonths);

        // sets laptop specific attributes
        this.screenSize = screenSize;
        this.ramGB = ramGB;
    }

    //getters

    /**
     * Retrieves the screen size of the laptop.
     *
     * @return the screen size in inches as a double.
     */
    public double getScreenSize() { return screenSize; }

    /**
     * Retrieves the amount of RAM in the laptop.
     *
     * @return the amount of RAM in gigabytes as an integer.
     */
    public int getRamGB() { return ramGB; }


    // other methods

    /**
     * Returns a string representation of the Laptop object, including its
     * superclass details, screen size, and RAM size.
     *
     * @return a string combining the formatted details of the superclass
     *         and the specific screen size and RAM information of the laptop.
     */
    @Override
    public String toString() {
        return super.toString() + ", Screen: " + screenSize + "\", RAM: " + ramGB + "GB";
    }
}
