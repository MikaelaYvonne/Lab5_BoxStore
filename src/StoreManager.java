/**
 * @author  Gregory McNutt
 * @date    10-10-25
 * @purpose Create a class to manage the items contained in the store.
 */

import java.util.ArrayList;

public class StoreManager {
    private final ArrayList<StoreItem> listOfAllItems;
    private final ArrayList<StoreItem> listOfItemsInCart;

    /**
     * Method to create a Store Manager. A set of items to be used within the store table, and the cart table.
     */
    public StoreManager() {
        listOfAllItems = new ArrayList<StoreItem>(); //store items
        listOfItemsInCart = new ArrayList<StoreItem>(); //cart items.


        //JUST FOR TESTING PURPOSES, REMOVE WHEN FINISHED.
        StoreItem item = new Fruit(10, "apple", 1.99, 30, 230, true);
        addItemToList(item);
        StoreItem item2 = new Phone(100, "iPhone", 999.99, 20, "Apple", 20, "ATT", 64);
        addItemToList(item2);
        //END TESTING CODE
        // ^^ i put those there because i didn't want to keep adding them manually :P
    }

    /**
     * Method to check the list for duplicate sku's
     * @param skuNumber Int the skuNumber to check within the list of items.
     * @return Boolean True or false, true if duplicate, else false.
     */
    public Boolean duplicateSkuChecker(int skuNumber){
        for (StoreItem item : listOfAllItems){
            if (item.getSkuNumber() == skuNumber){
                System.out.println("Duplicate item found: " + item.getItemName());
                return true;
            }
        }
        System.out.println("No duplicates found.");
        return false;
    }

    /**
     * Method to copy the entire item when moving it from the store list to the cart list, and vice versa.
     * @param original StoreItem the original item of which to be copied.
     * @return StoreItem the copied item.
     */
    public StoreItem copyItem(StoreItem original){
        return original.copy();
    }

    /**
     * Method to get the items currently in the cart list.
     * @return ArrayList a list of all the current items in the cart.
     */
    public ArrayList<StoreItem> getItemsInCart(){
        return listOfItemsInCart;
    }

    /**
     * Method to add an item into the customer's cart list.
     * @param item StoreItem the item to be added.
     */
    public void addItemToCart(StoreItem item){
        listOfItemsInCart.add(item);
    }

    /**
     * Method to search through the list of STORE items for a string representation of the SKU number.
     * @param sku STRING of the sku number to be checked within the list of store items.
     * @return StoreItem the item with the matching SKU number, if none found will return NULL.
     */
    public StoreItem getItemBySku(String sku) {
        for (StoreItem item : getListOfAllItems()) {
            if (String.valueOf(item.getSkuNumber()).equals(sku)){
                return item;
            }
        }
        return null;
    }


    /**
     * Method to search through the list of CART items for a string representation of the SKU number.
     * @param sku STRING of the sku number to be checked within the list of cart items.
     * @return StoreItem object of the item with the matching SKU if found. If not found will return NULL.
     */
    public StoreItem getCartItemBySku(String sku){
        for (StoreItem item : getItemsInCart()){
            if(String.valueOf(item.getSkuNumber()).equals(sku)){
                return item;
            }
        }
        return null;
    }

    /**
     * Gets the current list of all items within the manager class that have been added to the ArrayList.
     * @return the current items in the list of storeItems.
     */
    public ArrayList<StoreItem> getListOfAllItems(){
        return listOfAllItems;
    }

    /**
     * Adds an item to the list of store items.
     * @param item StoreItem of which to be added to the list of store items.
     */
    public void addItemToList(StoreItem item){
        listOfAllItems.add(item);
    }
}
