/**
 * @author  Gregory McNutt
 * @date    10-10-25
 * @purpose Create a class to manage the items contained in the store.
 */

import java.util.ArrayList;

public class StoreManager {
    private final ArrayList<StoreItem> listOfAllItems;
    public StoreManager() {
        listOfAllItems = new ArrayList<StoreItem>();
    }

    public ArrayList<StoreItem> getListOfAllItems(){
        return listOfAllItems;
    }

    public void addItemToList(StoreItem item){
        listOfAllItems.add(item);
    }
}
