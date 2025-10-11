/**
 * @author  Gregory McNutt
 * @date    10-10-25
 * @purpose Create a window with functionality to add items to the food store.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Objects;

public class AddItemWindow{
    private final StoreManager storeManager;
    private final JComboBox<String> typeItems;
    private final JTextField skuInput;
    private final JTextField itemNameInput;
    private final JTextField itemPriceInput;
    private final JTextField itemCountInput;
    private final JFrame addItemFrame;

    private JTextField optionalText1;
    private JTextField optionalText2;
    private JTextField optionalText3;
    private JTextField optionalText4;

    public AddItemWindow(JFrame parent, StoreManager manager){
        //utilize StoreManager class in order to add / view items.
        this.storeManager = manager;

        //creating the main window to add items.
        addItemFrame = new JFrame("Add New Item");
        addItemFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addItemFrame.setSize(400,300);
        addItemFrame.setLocationRelativeTo(parent);
        addItemFrame.setLayout(null);
        addItemFrame.setVisible(true);

        //Label and combo box for the item type
        JLabel typeLabel = new JLabel("Item Type: ");
        typeLabel.setBounds(25,15,75, 15);

        String [] typesOfItems = {"Pick an Item Type","Household Item", "Food Item",
                "Electronics", "Clothes"};

        typeItems = new JComboBox<>(typesOfItems);
        typeItems.setSelectedIndex(0);
        typeItems.setBounds(120, 0, 250, 50);
        typeItems.setVisible(true);
        typeItems.addActionListener(_ -> {
            if (typeItems.getSelectedItem() == "Household Item"){
                String [] optionalFields = {"Brand:", "Category:", "Toxic:"};
                addOptionalInputFields(addItemFrame, optionalFields);
            }
        });


        //Label and text field for the Sku Number input
        JLabel skuLabel = new JLabel("Sku Number:");
        skuLabel.setBounds(25, 50, 100, 15);

        skuInput = new JTextField("Enter Sku Number");
        skuInput.setBounds(125, 45,250, 25);
        skuInput.setForeground(Color.GRAY);
        skuInput.addFocusListener(new FocusListener() {
            //changes the text box when interacted with
            @Override
            public void focusGained(FocusEvent e) {
                if (skuInput.getText().equals("Enter Sku Number")){
                    skuInput.setText("");
                    skuInput.setForeground(Color.BLACK);
                }
            }
            //changes text box once non-interacted with
            @Override
            public void focusLost(FocusEvent e) {
                if (skuInput.getText().isEmpty()) {
                    skuInput.setForeground(Color.GRAY);
                    skuInput.setText("Enter Sku Number");
                }
            }
        });

        //label and input for item name
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

        //Label and Text input for price
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

        //Label and text input for item count
        JLabel itemCountLabel = new JLabel("Item Count: ");
        itemCountLabel.setBounds(25, 130, 100, 15);
        itemCountInput = new JTextField("Enter amount");
        itemCountInput.setBounds(125, 115, 250, 25);
        itemCountInput.setForeground(Color.GRAY);
        itemCountInput.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (itemCountInput.getText().equals("Enter Item Price")){
                    itemCountInput.setText("");
                    itemCountInput.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (itemCountInput.getText().isEmpty()) {
                    itemCountInput.setForeground(Color.GRAY);
                    itemCountInput.setText("Enter Item Price");
                }
            }
        });


        //Optional Input Fields
        JLabel optionalLabel1 = new JLabel();
        optionalLabel1.setBounds(25, 130, 100, 15);
        optionalText1 = new JTextField("Optional Field1");
        optionalText1.setBounds(125, 115, 250, 25);

        JLabel optionalLabel2 = new JLabel();



        //Buttons
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

        //Adding everything to the main frame
        addItemFrame.add(typeLabel);
        addItemFrame.add(typeItems);
        addItemFrame.add(skuLabel);
        addItemFrame.add(skuInput);
        addItemFrame.add(nameLabel);
        addItemFrame.add(itemNameInput);
        addItemFrame.add(priceLabel);
        addItemFrame.add(itemPriceInput);
        addItemFrame.add(itemCountLabel);
        addItemFrame.add(itemCountInput);
        addItemFrame.add(addButton);
        addItemFrame.add(cancelButton);
        //main frame visible
        addItemFrame.setVisible(true);
    }

    public void addOptionalInputFields(JFrame parent, String[] fields){
        switch (fields.length){

            case 1 -> {


            }
            case 2 -> {

            }
        }



    }

    public void addItem(){
        String sku = skuInput.getText();
        String name = itemNameInput.getText();
        String price = itemPriceInput.getText();
        String type = (String) typeItems.getSelectedItem();
        String amount = itemCountInput.getText();

        if (sku.equals("Enter Sku Number") || name.equals("Enter Item Name") || price.equals("Enter Item Price")
                || Objects.equals(type, "Pick an Item Type")){
            JOptionPane.showMessageDialog(addItemFrame, "Please fill in all fields before adding item.");
        }

        try {
            int skuValid = Integer.parseInt(sku);
            double priceValid = Double.parseDouble(price);
            int amountValid = Integer.parseInt(amount);
//            {"Pick an Item Type","Cleaning Supply", "Fruit", "Shelf Stable", "Vegetable",
//                    "Laptop", "TV", "Phone", "Shirt", "Outerwear", "Shoe"};

            switch (type){
                case null -> {System.out.println("Something fucked up really bad");}
                case "Cleaning Supply" -> {
                    StoreItem item = new ShelfStable(skuValid, name, priceValid, amountValid, 10, "Ex Date");
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

    public int checkAmountInStock(StoreManager manager, int amount, StoreItem item) {
        int newCount = 0;
        if( manager.getListOfAllItems().contains(item)){
            int itemCount = item.getItemCount();
            newCount += itemCount + amount;
        }
        return newCount;
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
