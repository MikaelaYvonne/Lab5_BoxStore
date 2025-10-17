/**
 * @author  Mikaela Yvonne Dacanay, Gregory McNutt, Thomas Wesley
 *          Section 001-003
 *          Date 10/01/25
 *          Purpose:Represents a phone in a store inventory system,
 *                  extending the electronics item category with phone-specific
 *                  attributes such as cell provider and storage capacity.
 */


public class Phone extends ElectronicsItem {

    // properties/ attributes
    private String carrier; // phone cell provider
    private int storageGB; // storage capacity in gb

    //constructor

    /**
     * Constructs a Phone object with the specified attributes.
     *
     * @param skuNumber       unique identifier for the phone
     * @param itemName        name of the phone
     * @param price           price of the phone
     * @param itemCount       quantity of the phone in stock
     * @param brand           brand of the phone
     * @param warrantyMonths  warranty period for the phone in months
     * @param carrier         cellular carrier associated with the phone
     * @param storageGB       storage capacity of the phone in gigabytes
     */
    public Phone(int skuNumber, String itemName, double price, int itemCount, String brand,
                 int warrantyMonths, String carrier, int storageGB) {

        // initializes the inherited properties from ElectronicsItem
        super(skuNumber, itemName, price, itemCount, brand, warrantyMonths);

        // sets the phone specific attributes
        this.carrier = carrier;
        this.storageGB = storageGB;
    }


    // getters

    /**
     * Retrieves the cellular carrier associated with the phone.
     *
     * @return the name of the cellular carrier as a String.
     */
    public String getCarrier() { return carrier; }


    /**
     * Retrieves the storage capacity of the phone.
     *
     * @return the storage capacity in gigabytes as an integer.
     */
    public int getStorageGB() { return storageGB; }


    //other methods
    /**
     * Returns a string representation of the Phone object, which includes the details
     * from the superclass' toString method appended with the carrier and storage
     * capacity attributes specific to the Phone class.
     *
     * @return a string combining superclass attributes and the specific carrier
     *         and storage information of the phone.
     */
    @Override
    public String toString() {
        return super.toString() + ", Carrier: " + carrier + ", Storage: " + storageGB + "GB";
    }
}
