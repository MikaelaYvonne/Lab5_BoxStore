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

    private final JLabel optionalLabel1;
    private final JLabel optionalLabel2;
    private final JLabel optionalLabel3;
    private final JLabel optionalLabel4;
    private final JTextField optionalText1;
    private final JTextField optionalText2;
    private final JTextField optionalText3;
    private final JTextField optionalText4;
    private final JComboBox<Boolean> optionalTF1;
    private final JComboBox<Boolean> optionalTF2;
    private final JComboBox<Boolean> optionalTF3;
    private final JComboBox<Boolean> optionalTF4;

    public AddItemWindow(JFrame parent, StoreManager manager){
        //utilize StoreManager class in order to add / view items.
        this.storeManager = manager;

        //creating the main window to add items.
        addItemFrame = new JFrame("Add New Item");
        addItemFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addItemFrame.setSize(400,525);
        addItemFrame.setLocationRelativeTo(parent);
        addItemFrame.setLayout(null);
        addItemFrame.setVisible(true);

        //Label and combo box for the item type
        JLabel typeLabel = new JLabel("Item Type: ");
        typeLabel.setBounds(25,15,100, 15);

        String [] typesOfItems = {"Pick an Item Type","Cleaning Supplies", "Furniture", "Fruit", "Vegetable",
        "Shelf Stable Food", "Laptop", "TV", "Phone", "Shirt", "Outerwear", "Shoes"};

        typeItems = new JComboBox<>(typesOfItems);
        typeItems.setSelectedIndex(0);
        typeItems.setBounds(150, 0, 250, 50);
        typeItems.setVisible(true);
        typeItems.addItemListener(e -> {
            String selected = (String) e.getItem();
            switch (selected) {
                case "Pick an Item Type" -> {
                    hideOptionalInputFields();
                }
                case "Cleaning Supplies" -> {
                    String [] optionalFields = {"Brand:", "Category:", "Toxic:"};
                    addOptionalInputFields(optionalFields);
                }
                case "Furniture" -> {
                    String[] optionalFields = {"Brand", "Category", "Dimensions"};
                    addOptionalInputFields(optionalFields);
                }
                case "Fruit" -> {
                    String [] optionalFields = {"Calories: ", "Is Ripe: "};
                    addOptionalInputFields(optionalFields);
                }
                case "Vegetable" -> {
                    String [] optionalFields = {"Calories: ", "Variety"};
                    addOptionalInputFields(optionalFields);
                }
                case "Shelf Stable Food" -> {
                    String[] optionalFields = {"Calories: ", "Expiration Date:" };
                    addOptionalInputFields(optionalFields);
                }
                case "Laptop" -> {
                    String[] optionalFields = {"Brand: ", "Warranty Months:", "Screen Size:", "GB of RAM: "};
                    addOptionalInputFields(optionalFields);
                }
                case "TV" -> {
                    String[] optionalFields = {"Brand: ", "Warranty Months: ", "Screen Size: ", "SmartTv: "};
                    addOptionalInputFields(optionalFields);
                }
                case "Phone" -> {
                    String[] optionalFields = {"Brand: ", "Warranty Months: ", "Carrier: ", "StorageGB: "};
                    addOptionalInputFields(optionalFields);
                }
                case "Shirt" -> {
                    String[] optionalFields = {"Size: ", "Color: ", "Sleeve Type: "};
                    addOptionalInputFields(optionalFields);
                }
                case "Outerwear" -> {
                    String[] optionalFields = {"Size: ", "Color: ", "Waterproof: "};
                    addOptionalInputFields(optionalFields);
                }
                case "Shoes" -> {
                    String[] optionalFields = {"Size: ", "Color: ", "Style: "};
                    addOptionalInputFields(optionalFields);
                }
            }
        });


        //Label and text field for the Sku Number input
        JLabel skuLabel = new JLabel("SKU Number:");
        skuLabel.setBounds(25, 50, 100, 15);

        skuInput = new JTextField("Enter Sku Number");
        skuInput.setBounds(150, 45,250, 25);
        skuInput.setForeground(Color.GRAY);
        setPlaceholderBehavior(skuInput, "Enter SKU Number");

        //label and input for item name
        JLabel nameLabel = new JLabel("Item Name:");
        nameLabel.setBounds(25, 90, 100, 15);

        itemNameInput = new JTextField("Enter Item Name");
        itemNameInput.setBounds(150, 85,250, 25);
        itemNameInput.setForeground(Color.GRAY);
        setPlaceholderBehavior(itemNameInput, "Enter Item Name");

        //Label and Text input for price
        JLabel priceLabel = new JLabel("Item Price:");
        priceLabel.setBounds(25, 130, 100, 15);

        itemPriceInput = new JTextField("Enter Item Price");
        itemPriceInput.setBounds(150, 125, 250, 25);
        itemPriceInput.setForeground(Color.GRAY);
        setPlaceholderBehavior(itemPriceInput, "Enter Item Price");

        //Label and text input for item count
        JLabel itemCountLabel = new JLabel("Item Count: ");
        itemCountLabel.setBounds(25, 170, 100, 15);

        itemCountInput = new JTextField("Enter amount");
        itemCountInput.setBounds(150, 165, 250, 25);
        itemCountInput.setForeground(Color.GRAY);
        setPlaceholderBehavior(itemCountInput, "Enter amount");


        //Optional Input Fields
        optionalLabel1 = new JLabel("Optional1");
        optionalLabel1.setBounds(25, 210, 100, 15);
        optionalLabel1.setVisible(false);
        optionalText1 = new JTextField("Optional Field1");
        optionalText1.setBounds(150, 205, 250, 25);
        optionalText1.setVisible(false);

        optionalLabel2 = new JLabel("Optional2");
        optionalLabel2.setBounds(25, 250, 100, 15);
        optionalLabel2.setVisible(false);
        optionalText2 = new JTextField("Optional Field 2");
        optionalText2.setBounds(150, 245, 250, 25);
        optionalText2.setVisible(false);

        optionalLabel3 = new JLabel("Optional3");
        optionalLabel3.setBounds(25, 290, 100, 15);
        optionalLabel3.setVisible(false);
        optionalText3 = new JTextField("Optional Field3");
        optionalText3.setBounds(150, 285, 250, 25);
        optionalText3.setVisible(false);

        optionalLabel4 = new JLabel("Optional4");
        optionalLabel4.setBounds(25, 330, 100, 15);
        optionalLabel4.setVisible(false);
        optionalText4 = new JTextField("Optional Field 4");
        optionalText4.setBounds(150, 325, 250, 25);
        optionalText4.setVisible(false);

        optionalTF1 = new JComboBox<Boolean>();
        optionalTF1.setVisible(false);
        optionalTF1.setBounds(150, 205, 250, 25);
        optionalTF2 = new JComboBox<Boolean>();
        optionalTF2.setVisible(false);
        optionalTF2.setBounds(150, 245, 250, 25);
        optionalTF3 = new JComboBox<Boolean>();
        optionalTF3.setVisible(false);
        optionalTF3.setBounds(150, 285, 250, 25);
        optionalTF4 = new JComboBox<Boolean>();
        optionalTF4.setBounds(150, 325, 250, 25);
        optionalTF4.setVisible(false);



        //Buttons
        JButton addButton = new JButton("Add");
        addButton.setBounds(25, 400, 150,75);
        addButton.setVisible(true);
        addButton.addActionListener(e -> {
            addItem();
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds( 225, 400, 150, 75);
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

        addItemFrame.add(optionalLabel1);
        addItemFrame.add(optionalLabel2);
        addItemFrame.add(optionalLabel3);
        addItemFrame.add(optionalLabel4);
        addItemFrame.add(optionalText1);
        addItemFrame.add(optionalText2);
        addItemFrame.add(optionalText3);
        addItemFrame.add(optionalText4);


        //main frame visible
        addItemFrame.setVisible(true);
    }

    public void setPlaceholderBehavior(JTextField field, String placeholder){
        field.setForeground(Color.GRAY);
        field.setText(placeholder);
        field.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(placeholder)){
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setForeground(Color.GRAY);
                    field.setText(placeholder);
                }
            }
        });
    }

    public void hideOptionalInputFields(){
        optionalLabel1.setVisible(false);
        optionalLabel1.setText("");
        optionalText1.setVisible(false);
        setPlaceholderBehavior(optionalText1, "");

        optionalLabel2.setVisible(false);
        optionalLabel2.setText("");
        optionalText2.setVisible(false);
        setPlaceholderBehavior(optionalText2, "");

        optionalLabel3.setVisible(false);
        optionalLabel3.setText("");
        optionalText3.setVisible(false);
        setPlaceholderBehavior(optionalText3, "");

        optionalLabel4.setVisible(false);
        optionalLabel4.setText("");
        optionalText4.setVisible(false);
        setPlaceholderBehavior(optionalText4, "");


        optionalTF1.setVisible(false);
        optionalTF2.setVisible(false);
        optionalTF3.setVisible(false);
        optionalTF4.setVisible(false);

        addItemFrame.revalidate();
    }

    public void addOptionalInputFields(String[] fields) {
        hideOptionalInputFields();
        //TODO: make this switch like the other one but include the optional boolean true/false boxes.
        switch (fields.length){
            case 1 -> {
                optionalLabel1.setVisible(true);
                optionalLabel1.setText(fields[0]);
                optionalText1.setVisible(true);
                setPlaceholderBehavior(optionalText1, "Enter " + fields[0]);
                addItemFrame.revalidate();
            }
            case 2 -> {
                optionalLabel1.setVisible(true);
                optionalLabel1.setText(fields[0]);
                optionalText1.setVisible(true);
                setPlaceholderBehavior(optionalText1, "Enter " + fields[0]);
                optionalLabel2.setVisible(true);
                optionalLabel2.setText(fields[1]);
                optionalText2.setVisible(true);
                setPlaceholderBehavior(optionalText2, "Enter " + fields[1]);
                addItemFrame.revalidate();
            }
            case 3 -> {
                optionalLabel1.setVisible(true);
                optionalLabel1.setText(fields[0]);
                optionalText1.setVisible(true);
                setPlaceholderBehavior(optionalText1, "Enter " + fields[0]);

                optionalLabel2.setVisible(true);
                optionalLabel2.setText(fields[1]);
                optionalText2.setVisible(true);
                setPlaceholderBehavior(optionalText2, "Enter " + fields[1]);

                optionalLabel3.setVisible(true);
                optionalLabel3.setText(fields[2]);
                optionalText3.setVisible(true);
                setPlaceholderBehavior(optionalText3, "Enter " + fields[2]);
                addItemFrame.revalidate();
            }
            case 4 -> {
                optionalLabel1.setVisible(true);
                optionalLabel1.setText(fields[0]);
                optionalText1.setVisible(true);
                setPlaceholderBehavior(optionalText1, "Enter " + fields[0]);

                optionalLabel2.setVisible(true);
                optionalLabel2.setText(fields[1]);
                optionalText2.setVisible(true);
                setPlaceholderBehavior(optionalText2, "Enter " + fields[1]);

                optionalLabel3.setVisible(true);
                optionalLabel3.setText(fields[2]);
                optionalText3.setVisible(true);
                setPlaceholderBehavior(optionalText3, "Enter " + fields[2]);

                optionalLabel4.setVisible(true);
                optionalLabel4.setText(fields[3]);
                optionalText4.setVisible(true);
                setPlaceholderBehavior(optionalText4, "Enter " + fields[3]);
                addItemFrame.revalidate();
            }
        }



    }

    public void addItem(){
        String sku = skuInput.getText();
        String name = itemNameInput.getText();
        String price = itemPriceInput.getText();
        String type = (String) typeItems.getSelectedItem();
        String amount = itemCountInput.getText();
        String optional1 = optionalText1.getText();
        String optional2 = optionalText2.getText();
        String optional3 = optionalText3.getText();
        String optional4 = optionalText4.getText();

        try {
            int skuValid = Integer.parseInt(sku);
            double priceValid = Double.parseDouble(price);
            int amountValid = Integer.parseInt(amount);

            switch (type){
                case null -> {System.out.println("Something fucked up really bad");}
                case "Pick an Item Type" -> {
                    JOptionPane.showMessageDialog(addItemFrame, "Select an item type.");
                }
                case "Cleaning Supplies" -> {
                    try {
                        boolean isToxic = Boolean.parseBoolean(optional3);
                        StoreItem item = new CleaningSupply(skuValid, name, priceValid, amountValid, optional1, optional2, isToxic);
                        addValidatedItem(item);
                    } catch (NumberFormatException e){
                        JOptionPane.showMessageDialog(addItemFrame, "Please enter True or False for the toxicity.");
                    }

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
        resetAllFields();
        System.out.println("Item Added!: List Size: " + storeManager.getListOfAllItems().size());
    }

    public void resetAllFields(){
        typeItems.setSelectedIndex(0);
        setPlaceholderBehavior(itemNameInput, "Enter Item Name");
        setPlaceholderBehavior(itemPriceInput, "Enter Item Price");
        setPlaceholderBehavior(skuInput, "Enter SKU Number");
        setPlaceholderBehavior(itemCountInput, "Enter Amount");
    }


}
