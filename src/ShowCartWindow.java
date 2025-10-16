import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class ShowCartWindow {
    private final StoreManager storeManager;
    private final DefaultTableModel cartModel;
    private final JTable cartTable;
    private final JFrame cartWindow;
    private final BuyItemWindow buyItemWindow;
    private final JLabel totalPriceNumber;
    private final JLabel salesTaxNumber;
    private final JLabel priceWSalesTaxNum;
    private final DecimalFormat decimalFormat;

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
        scrollPane.setBounds(0,0, 300,195);

        //create the button to remove an item from the cart.
        JButton removeButton = new JButton("Remove Item");
        removeButton.setBounds(15, 195, 100,40);
        removeButton.setFocusable(false);
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

        decimalFormat = new DecimalFormat("0.00");
        JLabel totalPriceLabel = new JLabel();
        totalPriceLabel.setText("Item's Cost: $");
        totalPriceLabel.setBounds(125, 200, 100, 20);
        totalPriceLabel.setVisible(true);

        totalPriceNumber = new JLabel();
        double totalPrice = getTotalPrice(manager);
        totalPriceNumber.setText(decimalFormat.format(totalPrice));
        totalPriceNumber.setBounds(215, 200, 150, 20);
        totalPriceNumber.setVisible(true);

        JLabel salesTaxLabel = new JLabel("Sales Tax: $");
        salesTaxLabel.setBounds(125, 225, 100, 20);
        salesTaxLabel.setVisible(true);

        salesTaxNumber = new JLabel();
        double totalTax = getSalesTax(manager);
        totalPriceNumber.setText(decimalFormat.format(totalTax));
        salesTaxNumber.setBounds(215, 225, 150, 20);
        salesTaxNumber.setVisible(true);

        JLabel priceWSalesTaxLabel = new JLabel();
        priceWSalesTaxLabel.setText("Total Price: $");
        priceWSalesTaxLabel.setBounds(125, 250, 100, 20);
        priceWSalesTaxLabel.setVisible(true);

        priceWSalesTaxNum = new JLabel();
        priceWSalesTaxNum.setBounds(215, 250, 150, 20);
        double overallPrice = getItemTaxPrice(manager);
        priceWSalesTaxNum.setText(decimalFormat.format(overallPrice));
        priceWSalesTaxNum.setVisible(true);


        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.setBounds(15, 230, 100,40);
        checkoutButton.setFocusable(false);
        checkoutButton.addActionListener( _ ->{
            if (cartTable.getRowCount() != 0){
                checkout(storeManager);
            } else {
                JOptionPane.showMessageDialog(cartWindow, "You need items in your cart to check out.");
            }
        });

        //TODO: Add a "total" label at the bottom of the cart so the customer can view the total amount they need to pay.
        //TODO: update the price field to include 2 decimal places.

        //Add the subframes, labels, and buttons to the cart's window.
        cartWindow.add(totalPriceLabel);
        cartWindow.add(totalPriceNumber);
        cartWindow.add(salesTaxLabel);
        cartWindow.add(salesTaxNumber);
        cartWindow.add(priceWSalesTaxLabel);
        cartWindow.add(priceWSalesTaxNum);
        cartWindow.add(checkoutButton);
        cartWindow.add(removeButton);
        cartWindow.add(scrollPane);
        cartWindow.setVisible(true);
    }

    /**
     * removes all items from cart and cart list indicating the customer has checked out.
     * @param manager StoreManager to use for item handling.
     */
    public void checkout(StoreManager manager){
        ArrayList<StoreItem> items = manager.getItemsInCart();
        items.removeAll(manager.getItemsInCart());
        JOptionPane.showMessageDialog(cartWindow,"Success! Enjoy your items!");
        cartModel.setRowCount(0);
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
        String itemPriceFormatted;
        double totalPrice = getTotalPrice(storeManager);
        double totalSalesTax = getSalesTax(storeManager);
        double overallPrice = getItemTaxPrice(storeManager);
        ArrayList<StoreItem> items = storeManager.getItemsInCart();
        System.out.println("Items in cart: " + items.size());
        for (StoreItem item : items){
            itemPriceFormatted = String.valueOf(item.getPrice());
            if (decimalFormat != null){
                itemPriceFormatted = decimalFormat.format(item.getPrice());
            }
            Object[] row = {
                    String.valueOf(item.getItemName()),
                    itemPriceFormatted,
                    String.valueOf(item.getItemCount())
            };
            cartModel.addRow(row);
            buyItemWindow.loadTableData();
        }
        if (totalPriceNumber != null && salesTaxNumber!= null && priceWSalesTaxNum != null) {
            if (decimalFormat != null) {
                totalPriceNumber.setText(decimalFormat.format(totalPrice));
                salesTaxNumber.setText(decimalFormat.format(totalSalesTax));
                priceWSalesTaxNum.setText(decimalFormat.format(overallPrice));
            }
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

    /**
     * Method to get the total prices of all the items in the cart.
     * @param manager StoreManager to use for item handling
     * @return Double total price of the items in the cart.
     */
    public double getTotalPrice(StoreManager manager){
        double totalPrice = 0;
        for (StoreItem item : manager.getItemsInCart()){
            totalPrice += item.getPrice() * item.getItemCount();
        }
        return totalPrice;
    }

    /**
     * Method to get sales tax of the current total in the cart.
     * @param manager StoreManager to handle items
     * @return Double total sales tax to be taken.
     */
    public double getSalesTax(StoreManager manager){
        double totalTax = 0;
        for (int i = 0; i < manager.getItemsInCart().size(); i++){
            StoreItem item = manager.getItemsInCart().get(i);
            totalTax += item.calculateTax() * item.getItemCount();
        }
        return totalTax;
    }

    public double getItemTaxPrice(StoreManager manager){
        return getTotalPrice(manager) + getSalesTax(manager);
    }

    //last line before end
}