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
        removeButton.setBounds(50, 400, 200,70);
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

        JButton closeButton = new JButton("Close");
        closeButton.setBounds(250, 400, 200, 70);
        closeButton.addActionListener(_ ->{showItems.dispose();});

        showItems.add(closeButton);
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
