/**
 * @author  Gregory McNutt
 * @date    10-10-25
 * @purpose Create a class to manage the items contained in the store.
 */

import java.util.ArrayList;

public class StoreManager {
    private final ArrayList<StoreItem> listOfAllItems;
    private final ArrayList<StoreItem> listOfItemsInCart;
    public StoreManager() {
        listOfAllItems = new ArrayList<StoreItem>();
        listOfItemsInCart = new ArrayList<StoreItem>();


        //JUST FOR TESTING PURPOSES, REMOVE WHEN FINISHED.
        StoreItem item = new Fruit(10, "apple", 1.99, 30, 230, true);
        addItemToList(item);
        StoreItem item2 = new Phone(100, "iPhone", 999.99, 20, "Apple", 20, "ATT", 64);
        addItemToList(item2);
        //END TESTING CODE
    }

    public StoreItem copyItem(StoreItem original){
        return original.copy();
    }

    public ArrayList<StoreItem> getItemsInCart(){
        return listOfItemsInCart;
    }

    public void addItemToCart(StoreItem item){
        listOfItemsInCart.add(item);
    }

    public StoreItem getItemBySku(String sku) {
        for (StoreItem item : getListOfAllItems()) {
            if (String.valueOf(item.getSkuNumber()).equals(sku)){
                return item;
            }
        }
        return null;
    }

    public StoreItem getItemBySku(int sku){
        for (StoreItem item : getListOfAllItems()) {
            if (item.getSkuNumber()== sku){
                return item;
            }
        }
        return null;
    }

    public StoreItem getCartItemBySku(String sku){
        for (StoreItem item : getItemsInCart()){
            if(String.valueOf(item.getSkuNumber()).equals(sku)){
                return item;
            }
        }
        return null;
    }

    public ArrayList<StoreItem> getListOfAllItems(){
        return listOfAllItems;
    }

    public void addItemToList(StoreItem item){
        listOfAllItems.add(item);
    }
}
