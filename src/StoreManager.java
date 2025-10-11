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
