/**
 * @author  Gregory McNutt
 * @date    10-10-25
 * @purpose Create a window with functionality to add items to the food store.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class AddItemWindow{
    private final StoreManager storeManager;
    private final JComboBox<String> typeItems;
    private final JTextField skuInput;
    private final JTextField itemNameInput;
    private final JTextField itemPriceInput;
    private final JFrame addItemFrame;

    public AddItemWindow(JFrame parent, StoreManager manager){
        this.storeManager = manager;

        addItemFrame = new JFrame("Add New Item");
        addItemFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addItemFrame.setSize(400,300);
        addItemFrame.setLocationRelativeTo(parent);
        addItemFrame.setLayout(null);
        addItemFrame.setVisible(true);

        JLabel typeLabel = new JLabel("Item Type: ");
        typeLabel.setBounds(25,15,75, 15);

        String [] typesOfItems = {"Pick an Item Type","Shelf Stable Food", "Not Shelf Stable Food", "Furniture", "Cleaning Product",
                "Phone", "TV", "Laptop", "Outer Wear", "Shirt", "Shoes"};

        typeItems = new JComboBox<>(typesOfItems);
        typeItems.setSelectedIndex(0);
        typeItems.setBounds(120, 0, 250, 50);
        typeItems.setVisible(true);


        JLabel skuLabel = new JLabel("Sku Number:");
        skuLabel.setBounds(25, 50, 100, 15);

        skuInput = new JTextField("Enter Sku Number");
        skuInput.setBounds(125, 45,250, 25);
        skuInput.setForeground(Color.GRAY);
        skuInput.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (skuInput.getText().equals("Enter Sku Number")){
                    skuInput.setText("");
                    skuInput.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (skuInput.getText().isEmpty()) {
                    skuInput.setForeground(Color.GRAY);
                    skuInput.setText("Enter Sku Number");
                }
            }
        });


        JLabel nameLabel = new JLabel("Item Name:");
        nameLabel.setBounds(25, 85, 100, 15);

        itemNameInput = new JTextField("Enter Item Name");
        itemNameInput.setBounds(125, 80,250, 25);
        itemNameInput.setForeground(Color.GRAY);
        itemNameInput.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (itemNameInput.getText().equals("Enter Item Name")){
                    itemNameInput.setText("");
                    itemNameInput.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (itemNameInput.getText().isEmpty()) {
                    itemNameInput.setForeground(Color.GRAY);
                    itemNameInput.setText("Enter Item Name");
                }
            }
        });


        JLabel priceLabel = new JLabel("Item Price:");
        priceLabel.setBounds(25, 120, 100, 15);

        itemPriceInput = new JTextField("Enter Item Price");
        itemPriceInput.setBounds(125, 115, 250, 25);
        itemPriceInput.setForeground(Color.GRAY);
        itemPriceInput.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (itemPriceInput.getText().equals("Enter Item Price")){
                    itemPriceInput.setText("");
                    itemPriceInput.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (itemPriceInput.getText().isEmpty()) {
                    itemPriceInput.setForeground(Color.GRAY);
                    itemPriceInput.setText("Enter Item Price");
                }
            }
        });

        JButton addButton = new JButton("Add");
        addButton.setBounds(25, 160, 150,75);
        addButton.setVisible(true);
        addButton.addActionListener(e -> {
            addItem();
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds( 225, 160, 150, 75);
        cancelButton.setVisible(true);
        cancelButton.addActionListener(e -> {
            addItemFrame.dispose();}
        );


        addItemFrame.add(typeLabel);
        addItemFrame.add(typeItems);
        addItemFrame.add(skuLabel);
        addItemFrame.add(skuInput);
        addItemFrame.add(nameLabel);
        addItemFrame.add(itemNameInput);
        addItemFrame.add(priceLabel);
        addItemFrame.add(itemPriceInput);
        addItemFrame.add(addButton);
        addItemFrame.add(cancelButton);

        addItemFrame.setVisible(true);
    }


    public void addItem(){
        String skuText = skuInput.getText();
        String name = itemNameInput.getText();
        String priceText = itemPriceInput.getText();
        String type = (String) typeItems.getSelectedItem();

        if (skuText.equals("Enter Sku Number") || name.equals("Enter Item Name") || priceText.equals("Enter Item Price")
                || type.equals("Pick an Item Type")){
            JOptionPane.showMessageDialog(addItemFrame, "Please fill in all fields before adding item.");
        }

        try {
            int sku = Integer.parseInt(skuText);
            double price = Double.parseDouble(priceText);
            //Shelf Stable Food", "Not Shelf Stable Food", "Furniture", "Cleaning Product",
            //                "Phone", "TV", "Laptop", "Outer Wear", "Shirt", "Shoes"}
            switch (type){
                case null -> {System.out.println("Something fucked up really bad");}
                case "Shelf Stable Food" -> {
//                    StoreItem item = new FoodItem(sku, price, name, type, true);
//                    addValidatedItem(item);
                }
                case "Not Shelf Stable Food" -> {
                    System.out.println("not shelf stable food");
                }

                case "Furniture" -> {
                    System.out.println("furniture");
                }

                case "Cleaning Product" -> {
                    System.out.println("Cleaning product");
                }

                case "Phone" -> {
                    System.out.println("Phone");
                }

                case "TV" -> {
                    System.out.println("TV");
                }

                case "Laptop" -> {
                    System.out.println("Laptop");
                }

                case "Outer Wear" -> {
                    System.out.println("Outerwear");
                }

                case "Shirt" -> {
                    System.out.println("shirt");
                }

                case "Shoes" -> {
                    System.out.println("Shoes");
                }
                default -> {System.out.println("Something fucked up");}
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(addItemFrame, "Please enter valid numbers for the SKU and Price");
        }
    }

    public void addValidatedItem(StoreItem item){
        storeManager.addItemToList(item);
        typeItems.setSelectedIndex(0);
        itemNameInput.setText("Enter Item Name");
        itemNameInput.setForeground(Color.GRAY);
        itemPriceInput.setText("Enter Item Price");
        itemPriceInput.setForeground(Color.GRAY);
        skuInput.setText("Enter Sku Number");
        skuInput.setForeground(Color.GRAY);
        System.out.println("Item Added!: List Size: " + storeManager.getListOfAllItems().size());
    }


}
