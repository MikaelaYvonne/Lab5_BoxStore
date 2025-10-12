/**
 * @author  Gregory McNutt
 * @date    10-10-25
 * @purpose Create a window to show all items contained in the Food Store.
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class BuyItemWindow {
    private final StoreManager storeManager;
    private final DefaultTableModel tableModel;
    private final JTable itemTable;
    private ShowCartWindow cartWindow;

    public BuyItemWindow(JFrame parent, StoreManager manager){
        this.storeManager = manager;
        JFrame showItems = new JFrame("Store Item Database");
        showItems.setSize(400,500);
        showItems.setLocationRelativeTo(parent);
        showItems.setResizable(false);
        showItems.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        showItems.setLayout(null);


        String[] columnNames = {"SKU", "Item Name", "Amount In Stock"};
        tableModel = new DefaultTableModel(columnNames, 0){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };

        loadTableData();

        itemTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(itemTable);
        scrollPane.setBounds(0,0,400,325);

        itemTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                int selectedRow = itemTable.getSelectedRow();
                if (selectedRow != -1) {
                    StoreItem item = storeManager.getListOfAllItems().get(selectedRow);
                    if (evt.getClickCount() == 2){
                        showItemDetails(item, showItems);
                    }
                }
            }
        });

        JButton addToCart = new JButton("Add to Cart");
        addToCart.setBounds(10, 325, 175,65);
        addToCart.addActionListener(_ -> {
            int selectedRow = itemTable.getSelectedRow();
            if (selectedRow != -1) {
                try {
                    String amount = JOptionPane.showInputDialog("Please enter the amount you would like to add to cart.");
                    int amountInt = Integer.parseInt(amount);
                    StoreItem oldItem = storeManager.getListOfAllItems().get(selectedRow);
                    addItemToCart(oldItem, amountInt, selectedRow);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(showItems, "Please input a valid amount.");
                }
            } else {
                JOptionPane.showMessageDialog(showItems, "Please select an item to add it to cart!");
            }
        });

        JButton showDetailsButton = new JButton("Show Item Info");
        showDetailsButton.setBounds(215, 325, 175, 65);
        showDetailsButton.addActionListener(_ -> {
            int selectedRow = itemTable.getSelectedRow();
            if (selectedRow != -1) {
                StoreItem item = storeManager.getListOfAllItems().get(selectedRow);
                showItemDetails(item, showItems);
            } else {
                JOptionPane.showMessageDialog(showItems, "Please select an item to show it's information!");
            }
        });

        JButton showCartButton = new JButton("Show Cart");
        showCartButton.setBounds(10, 395, 175, 65);
        showCartButton.addActionListener(_ ->{
            if (cartWindow == null){
                cartWindow = new ShowCartWindow(showItems, storeManager);
            } else {
                cartWindow.refreshCartDisplay();
                cartWindow.setVisible(true);
            }
        });

        JButton closeButton = new JButton("Close");
        closeButton.setBounds(215, 395, 175, 65);
        closeButton.addActionListener(_ ->{showItems.dispose();});

        showItems.add(showDetailsButton);
        showItems.add(closeButton);
        showItems.add(scrollPane);
        showItems.add(showCartButton);
        showItems.add(addToCart);
        showItems.setVisible(true);
    }



    //TODO: Test this more to make sure it actually works correctly.
    public void addItemToCart(StoreItem oldStoreItem, int amount, int selectedRow){
        if (amount <= 0) {
            JOptionPane.showMessageDialog(itemTable, "Please enter a valid amount greater than 0.");
            return;
        }
        if (oldStoreItem.getItemCount() < amount){
            JOptionPane.showMessageDialog(itemTable, "You can't buy more than what is available.");
        } else {
            String sku = String.valueOf(oldStoreItem.getSkuNumber()); //get the value of the SkuNumber of item passed to method
            StoreItem storeItem = storeManager.getItemBySku(sku); //create new StoreItem based on the SKU of the passed item.

            if (storeItem != null){ //if the newly created item is already in the list
                storeItem.setItemCount(storeItem.getItemCount() - amount); //subtract the amount from that item
                StoreItem existingCartItem = storeManager.getCartItemBySku(sku); //and create a new item that will have the new value of the existing item.
                if (existingCartItem!= null){ //if it successfully creates an item (Which mean it does not exist already)
                    existingCartItem.setItemCount(existingCartItem.getItemCount()+amount); //add the amount to the cart item created.
                } else { //it doesnt exist in the cart, so we need to
                    StoreItem newCartItem = storeManager.copyItem(storeItem); //make a new item for the cart
                    newCartItem.setItemCount(amount); //then set the new amount to the amount entered.
                    storeManager.addItemToCart(newCartItem); //and finally add it to the cart array.
                }

                if (storeItem.getItemCount() <= 0){ //if the store item in the store list = 0
                    storeManager.getListOfAllItems().remove(storeItem); //remove the item from the arraylist
                    tableModel.removeRow(selectedRow); //and remove the row from the table.
                } else { //if it does not equal 0, update the table row
                    loadTableData();
//                    tableModel.setValueAt(oldStoreItem.getItemCount(), selectedRow, 2);
                }
            }
        }
    }



    //TODO: change loadTableData to not include duplicates when iterating through the arraylist.
    public void loadTableData(){
        ArrayList<StoreItem> items = storeManager.getListOfAllItems();
        System.out.println("Show Item Window - Number ofItems: " + items.size());
        for (StoreItem item : items) {
            Object[] row = {
                String.valueOf(item.getSkuNumber()),
                item.getItemName(),
                item.getItemCount()
            };
            tableModel.addRow(row);
        }
    }


    public void showItemDetails(StoreItem item, JFrame parentFrame){
        JDialog infoBox = new JDialog(parentFrame, "Item Details", true);
        infoBox.setSize(200,200);
        infoBox.setLocationRelativeTo(parentFrame);
        infoBox.setLayout(null);

        JTextArea infoArea = new JTextArea();
        infoArea.setBounds(0,0,200, 125);
        infoArea.setEditable(false);
        infoArea.setLineWrap(true);
        infoArea.setWrapStyleWord(true);
        if (item == null){
            infoArea.setText("");
        } else {
            infoArea.setText(item.toString());
        }

        JButton closeButton = new JButton("Close");
        closeButton.setBounds(50, 125, 100, 45);
        closeButton.addActionListener(_ -> infoBox.dispose());

        infoBox.add(infoArea);
        infoBox.add(closeButton);
        infoBox.setVisible(true);
    }
}
