import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class ShowCartWindow {
    private final StoreManager storeManager;
    private final DefaultTableModel cartModel;
    private final JTable cartTable;
    private final JFrame cartWindow;
    private final BuyItemWindow buyItemWindow;

    public ShowCartWindow(JFrame parent, StoreManager manager, BuyItemWindow buyItemWindow) {
        this.storeManager = manager;
        this.buyItemWindow = buyItemWindow;
        cartWindow = new JFrame("Customer Cart");
        cartWindow.setResizable(false);
        cartWindow.setSize(300,300);
        cartWindow.setLocationRelativeTo(parent);
        cartWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cartWindow.setLayout(null);


        String[] cartHeader = {"Item Name", "Price", "Amount in Cart"};
        cartModel = new DefaultTableModel(cartHeader, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        loadCartData();

        cartTable = new JTable(cartModel);
        JScrollPane scrollPane = new JScrollPane(cartTable);
        scrollPane.setBounds(0,0, 300,200);

        JButton removeFromCart = new JButton("Remove Item");
        removeFromCart.setBounds(15, 210, 100,50);
        removeFromCart.addActionListener(_ ->{
            int selectedRow = cartTable.getSelectedRow();
            if (selectedRow != -1){
                try {
                    String amount = JOptionPane.showInputDialog("Please enter the amount you'd like to remove from the cart");
                    int amountInt = Integer.parseInt(amount);
                    StoreItem oldItem = storeManager.getItemsInCart().get(selectedRow);
                    removeItemFromCart(oldItem, amountInt, selectedRow);
                } catch (NumberFormatException _){
                    JOptionPane.showMessageDialog(cartWindow, "Please enter a valid number.");
                }
            } else {
                JOptionPane.showMessageDialog(cartWindow, "Please select an item before trying to remove it.");
            }
        });






        cartWindow.add(removeFromCart);
        cartWindow.add(scrollPane);
        cartWindow.setVisible(true);

    }
    //TODO: Fix this to properly update the cart data when adding and removing the items / number of items.
    public void removeItemFromCart(StoreItem itemInCart, int amount, int selectedRow) {
        if (amount <= 0) {
            JOptionPane.showMessageDialog(cartTable, "Please enter an amount greater than 0.");
            return;
        }
        if (itemInCart.getItemCount() < amount) {
            JOptionPane.showMessageDialog(cartTable, "You cannot remove more than what you have in your cart.");
        } else {
            String sku = String.valueOf(itemInCart.getSkuNumber()); //get the sku of the current item for lookup later

            StoreItem itemToAdd = storeManager.getItemBySku(sku); //check to see if item exists in the store list.

            if (itemToAdd != null) { //if the item doesn't return null (item already exists in store list)(returns the item)
                itemToAdd.setItemCount(itemToAdd.getItemCount() + amount); //so we add the amount to that item.
            } else { //item returns null (item doesnt exist already in store list)
                StoreItem newItemToAdd = storeManager.copyItem(itemInCart); //create a new item that will be added to the list.
                newItemToAdd.setItemCount(amount); //set the amount to the new amount being added to the store item list.
                storeManager.getListOfAllItems().add(newItemToAdd); //add the new item to the store list.
            }

            itemInCart.setItemCount(itemInCart.getItemCount() - amount); //update the current item in the cart

            if (itemInCart.getItemCount() <= 0){ //check to see if the amount in the car has reached 0, and if it has,
                storeManager.getItemsInCart().remove(itemInCart); //remove it from the list of cart items and
                cartModel.removeRow(selectedRow); //remove it from the table.
            }



        }
        loadCartData(); //then re-fetch the cart data.
        buyItemWindow.loadTableData();
    }


    public void loadCartData(){
        cartModel.setRowCount(0);
        ArrayList<StoreItem> items = storeManager.getItemsInCart();
        System.out.println("Items in cart: " + items.size());
        for (StoreItem item : items){
            Object[] row = {
                    String.valueOf(item.getItemName()),
                    String.valueOf(item.getPrice()),
                    String.valueOf(item.getItemCount())
            };
            cartModel.addRow(row);
            buyItemWindow.loadTableData();
        }
    }

    public void setVisible(boolean visible){
        cartWindow.setVisible(visible);
    }

    public void refreshCartDisplay(){
        cartModel.setRowCount(0);
        loadCartData();
    }
}