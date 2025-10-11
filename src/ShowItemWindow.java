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

public class ShowItemWindow {
    private final StoreManager storeManager;
    private final DefaultTableModel tableModel;
    private final JTable itemTable;

    public ShowItemWindow(JFrame parent, StoreManager manager){
        this.storeManager = manager;
        JFrame showItems = new JFrame("Store Item Database");
        showItems.setSize(500,500);
        showItems.setLocationRelativeTo(parent);
        showItems.setResizable(false);
        showItems.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        showItems.setLayout(null);


        String[] columnNames = {"SKU", "Item Name", "Category"};
        tableModel = new DefaultTableModel(columnNames, 0){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };

        loadTableData();

        itemTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(itemTable);
        scrollPane.setBounds(0,0,500,400);

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

        JButton removeButton = new JButton("Remove Selected Item");
        removeButton.setBounds(150, 400, 200,70);
        removeButton.addActionListener(_ -> {
            int selectedRow = itemTable.getSelectedRow();
            if (selectedRow != -1) {
                storeManager.getListOfAllItems().remove(selectedRow);
                tableModel.removeRow(selectedRow);
                System.out.println("Item Successfully Removed. List Size: " + storeManager.getListOfAllItems().size());
            } else {
                JOptionPane.showMessageDialog(showItems, "Please select an item to remove from the list first!");
            }
        });

        showItems.add(scrollPane);
        showItems.add(removeButton);
        showItems.setVisible(true);
    }


    public void loadTableData(){
        ArrayList<StoreItem> items = storeManager.getListOfAllItems();
        System.out.println("Show Item Window - Number ofItems: " + items.size());
        for (StoreItem item : items) {
            Object[] row = {
                    String.valueOf(item.getSkuNumber()),
                    item.getItemName(),
            };
            tableModel.addRow(row);
        }
    }


    public void showItemDetails(StoreItem item, JFrame parentFrame){
        JDialog infoBox = new JDialog(parentFrame, "Item Details", true);
        infoBox.setSize(400,400);
        infoBox.setLocationRelativeTo(parentFrame);
        infoBox.setLayout(null);

        JLabel skuLabel = new JLabel("Sku Number: " + item.getSkuNumber());
        skuLabel.setBounds(20,20,200,25);

        JLabel nameLabel = new JLabel("Item Name: " + item.getItemName());
        nameLabel.setBounds(20,50,200,25);

        JLabel typeLabel = new JLabel("Item Type: ");
        typeLabel.setBounds(20,80,200,25);

        JLabel priceLabel = new JLabel("Price: $" + String.format("%.2f", item.getPrice()));
        priceLabel.setBounds(20,110,200,25);

        JButton closeButton = new JButton("Close");
        closeButton.setBounds(100, 150, 100, 50);
        closeButton.addActionListener(_ -> infoBox.dispose());

        infoBox.add(skuLabel);
        infoBox.add(nameLabel);
        infoBox.add(typeLabel);
        infoBox.add(priceLabel);
        infoBox.add(closeButton);

        infoBox.setVisible(true);
    }
}
