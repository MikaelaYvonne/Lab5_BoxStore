import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class ShowCartWindow {
    private final StoreManager storeManager;
    private final DefaultTableModel cartModel;
    private final JTable cartTable;
    private final JFrame cartWindow;
    private final BuyItemWindow buyItemWindow;

    /**
     * Shows the main cart window so the user can view what they have stored in the cart at that time.
     * @param parent JFrame of which you would like the window to spawn from.
     * @param manager StoreManager which store manager of list items you would like to use with the cart window.
     * @param buyItemWindow BuyItemWindow defines which BuyItemWindow to use when adding/removing when you update the tables.
     */
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
        //get the cart's data from the list of cartItems.
        loadCartData();

        //create the table for the data to be output
        cartTable = new JTable(cartModel);
        JScrollPane scrollPane = new JScrollPane(cartTable);
        scrollPane.setBounds(0,0, 300,200);

        //create the button to remove an item from the cart.
        JButton removeButton = new JButton("Remove Item");
        removeButton.setBounds(15, 210, 100,50);
        removeButton.addActionListener(_ ->{
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


        //Add the subframes to the cart's window.
        cartWindow.add(removeButton);
        cartWindow.add(scrollPane);
        cartWindow.setVisible(true);
    }

    /**
     * Method to remove and item from the cart. Happens when you click the remove button.
     * @param itemInCart StoreItem which object you want to interact with when removing from the cart.
     * @param amount Int of the amount to be removed.
     * @param selectedRow Int which row on the table that is going to be modified when you remove the item from the table.
     */
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
            } else { //item returns null (item doesn't exist already in store list)
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

    /**
     * Method to fetch the data from the StoreManager used in the ShowCartWindow and update the cart's table to reflect it.
     */
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

    /**
     * A method to show or hide the active cart window. Needs this because you cannot instantiate a new cart object else you'll have incorrect data.
     * @param visible Boolean true or false to show or hide the window.
     */
    public void setVisible(boolean visible){
        cartWindow.setVisible(visible);
    }

    /**
     * Method to refresh the cart's display whenever you modify an item. Clears the current row out then reloads cart data.
     */
    public void refreshCartDisplay(){
        cartModel.setRowCount(0);
        loadCartData();
    }
}