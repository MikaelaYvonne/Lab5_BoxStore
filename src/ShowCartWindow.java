import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class ShowCartWindow {
    private final StoreManager storeManager;
    private final DefaultTableModel cartModel;
    private final JTable cartTable;
    private final JFrame cartWindow;

    public ShowCartWindow(JFrame parent, StoreManager manager) {
        this.storeManager = manager;

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
            if (selectedRow != 1){
                try {
                    String amount = JOptionPane.showInputDialog("Please enter the amount you'd like to remove from the cart");
                    int amountInt = Integer.parseInt(amount);
                    StoreItem oldItem = storeManager.getListOfAllItems().get(selectedRow);
                    removeItemFromCart(oldItem, amountInt, selectedRow);
                } catch (NumberFormatException _){
                    JOptionPane.showMessageDialog(cartWindow, "Please enter a valid number.");
                }
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
            String sku = String.valueOf(itemInCart.getSkuNumber());
            StoreItem itemLeavingCart = storeManager.getItemBySku(sku);

            if (itemLeavingCart != null) {
                itemLeavingCart.setItemCount(itemLeavingCart.getItemCount() + amount);
            } else {
                itemInCart.setItemCount(amount);
                storeManager.addItemToList(itemInCart);
            }

            if (itemInCart.getItemCount() <= 0) {
                storeManager.getItemsInCart().remove(itemInCart);
                cartModel.removeRow(selectedRow);
            } else {
                cartModel.setValueAt(itemInCart.getItemCount() - amount, selectedRow, 2);
            }
        }
    }


    //TODO: Change loadCartData to not include duplicates when iterating through the arraylist.
    public void loadCartData(){
        ArrayList<StoreItem> items = storeManager.getItemsInCart();
        System.out.println("Items in cart: " + items.size());
        for (StoreItem item : items){
            Object[] row = {
                    String.valueOf(item.getItemName()),
                    String.valueOf(item.getPrice()),
                    String.valueOf(item.getItemCount())
            };
            cartModel.addRow(row);
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