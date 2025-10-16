/**
 * @author  Gregory McNutt, Mikaela Yvonne Dacanay
 * @date    10-10-25
 * @purpose Create a window with functionality to add items to the food store.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * A Swing-based window that allows users to create and add StoreItem objects to the inventory.
 * <p>
 * The window presents a set of base inputs (SKU, Name, Price, Count) and a dynamic set of optional
 * fields that change based on the selected item type. Optional inputs can be either text fields or
 * boolean selectors (true/false) depending on the selected type.
 * </p>
 *
 * <h2>Threading</h2>
 * All interactions with this window must occur on the Swing Event Dispatch Thread (EDT).
 * Construct and show this class using SwingUtilities.invokeLater if needed.
 *
 * <h2>Validation and UX</h2>
 * - The SKU, Price, and Count fields are validated numerically when adding an item.
 * - For item-type-specific fields, numeric parsing is performed where applicable; users are
 *   notified via dialog if parsing fails.
 * - Duplicate SKU values are blocked; users are notified via dialog.
 *
 * <h2>State Management</h2>
 * - Placeholder text is used for all text inputs. Placeholders are gray and cleared on focus;
 *   empty fields revert to placeholders on focus loss.
 * - Changing the item type resets the optional fields to the appropriate configuration.
 *
 * <h2>Visibility</h2>
 * - Optional fields are hidden by default and are revealed to match the selected item type.
 *
 * @see JOptionPane
 * @see JFrame
 */

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

    /**
     * Method that Creates the main "AddItem" Window and all of its buttons / text fields.
     * @param parent the parent frame the frame should spawn from.
     * @param manager the store manager to use to modify items.
     */
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
        //create the "headers" for each selectable item in the combo box.
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
                    addOptionalInputFields(optionalFields, 2);
                }
                case "Furniture" -> {
                    String[] optionalFields = {"Brand", "Category", "Dimensions"};
                    addOptionalInputFields(optionalFields);
                }
                case "Fruit" -> {
                    String [] optionalFields = {"Calories: ", "Is Ripe: "};
                    addOptionalInputFields(optionalFields, 1);
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
                    addOptionalInputFields(optionalFields, 3);
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
                    addOptionalInputFields(optionalFields, 2);
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

        // true/false input fields that will only activate under certain type choices.
        Boolean[] tf = (new Boolean[]{true, false});
        optionalTF1 = new JComboBox<>(tf);
        optionalTF1.setVisible(false);
        optionalTF1.setBounds(150, 205, 250, 25);
        optionalTF2 = new JComboBox<Boolean>(tf);
        optionalTF2.setVisible(false);
        optionalTF2.setBounds(150, 245, 250, 25);
        optionalTF3 = new JComboBox<Boolean>(tf);
        optionalTF3.setVisible(false);
        optionalTF3.setBounds(150, 285, 250, 25);
        optionalTF4 = new JComboBox<Boolean>(tf);
        optionalTF4.setBounds(150, 325, 250, 25);
        optionalTF4.setVisible(false);



        //Buttons
        JButton addButton = new JButton("Add");
        addButton.setBounds(25, 400, 150,75);
        addButton.setVisible(true);
        addButton.addActionListener(e -> {
            addItem();
        });

        JButton cancelButton = new JButton("Close");
        cancelButton.setBounds( 225, 400, 150, 75);
        cancelButton.setVisible(true);
        cancelButton.addActionListener(e -> {
            addItemFrame.dispose();}
        );

        //Adding everything to the main JFrame window.
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

        addItemFrame.add(optionalTF1);
        addItemFrame.add(optionalTF2);
        addItemFrame.add(optionalTF3);
        addItemFrame.add(optionalTF4);

        //main frame visible
        addItemFrame.setVisible(true);
    }

    /**
     * Function to get whatever value is within the True/False input fields.
     * @param comboBox whatever combo box you are trying to get a value from.
     * @return the boolean value true or false of the combo box.
     */
    private static boolean getSelectedTF(JComboBox<Boolean> comboBox){
        return Boolean.TRUE.equals(comboBox.getSelectedItem());
    }

    /**
     * Sets placeholder behavior on a text field: shows gray placeholder text when empty or unfocused,
     * and clears it with black text on focus.
     * <p>
     * Behavior:
     * - On focus gained: if current text equals the placeholder, clears it and sets foreground to black.
     * - On focus lost: if empty, restores the gray placeholder.
     * </p>
     *
     * @param field the JTextField to configure.
     * @param placeholder text to display when the field is empty.
     *
     * @implNote If you subsequently programmatically set the text to a non-empty value,
     *           it will remain as the user's input (no automatic placeholder restoration).
     */

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

    /**
     * Hides all optional input fields (labels, text fields, and boolean combo boxes) and clears their labels.
     * <p>
     * This also reinitializes placeholder behavior for the optional text fields with empty placeholders
     * to ensure they appear blank and inactive until reconfigured by addOptionalInputFields.
     * </p>
     */

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

    /**
     * Configures and shows optional text fields corresponding to the provided labels.
     * <p>
     * The number of visible optional fields is determined by the length of the fields array:
     * - 1..4 labels are supported and will be shown in order.
     * - Each visible text field is assigned a placeholder: "Enter " + fields[i]
     * </p>
     *
     * @param fields an array of field labels to display; its length (1–4) determines the number of fields shown.
     *
     * @implNote Any previously visible boolean (true/false) inputs are hidden by hideOptionalInputFields()
     *           before these fields are shown. Use the overloaded method to replace a specific index with a boolean selector.
     */

    public void addOptionalInputFields(String[] fields) {
        hideOptionalInputFields();
        //Each case corresponds to the length of the "fields" String list.
        //So, depending on how many fields you need, that's how many optional fields are shown.
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

    /**
     * Overload of addOptionalInputFields to configure one of the optional positions as a boolean (true/false) selector.
     * <p>
     * After showing the text fields via addOptionalInputFields(String[]), this method hides the text field at
     * the provided index and shows the corresponding JComboBox<Boolean> at the same position.
     * </p>
     *
     * @param fields labels for the optional fields; length 1–4.
     * @param whichIndex zero-based index (0..3) indicating which optional field should be a boolean selector.
     *
     * @implNote If whichIndex is outside the bounds of fields.length, the switch below will simply not match any case,
     *           and no boolean selector will be shown.
     */

    public void addOptionalInputFields(String[] fields, int whichIndex){
        addOptionalInputFields(fields);
        switch (whichIndex) {
            case 0 -> {
                optionalText1.setVisible(false);
                optionalTF1.setVisible(true);
            }
            case 1 -> {
                optionalText2.setVisible(false);
                optionalTF2.setVisible(true);
            }
            case 2 -> {
                optionalText3.setVisible(false);
                optionalTF3.setVisible(true);
            }
            case 3 -> {
                optionalText4.setVisible(false);
                optionalTF4.setVisible(true);
            }
        }
    }

    /**
     * Handles the Add button action. Parses, validates, and constructs the appropriate StoreItem
     * based on current inputs, then attempts to add it to the inventory via StoreManager.
     * <p>
     * Validation:
     * - SKU must be an integer.
     * - Price must be a double.
     * - Count must be an integer.
     * - Certain item types require additional numeric values (e.g., calories, warranty months,
     *   screen size, storage). Parsing errors show user-friendly dialogs and abort the add.
     * - The selected item type must not be "Pick an Item Type".
     * - Duplicate SKUs are rejected with a dialog.
     * </p>
     *
     * <p>
     * Side effects:
     * - On success, fields are reset to their placeholders and the type resets to the initial selection.
     * - On failure, a dialog explains the issue and the form remains for correction.
     * </p>
     */

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
        Boolean tf1 = getSelectedTF(optionalTF1);
        Boolean tf2 = getSelectedTF(optionalTF2);
        Boolean tf3 = getSelectedTF(optionalTF3);
        Boolean tf4 = getSelectedTF(optionalTF4);

        try {
            int skuValid = Integer.parseInt(sku);
            double priceValid = Double.parseDouble(price);
            int amountValid = Integer.parseInt(amount);

            /*This switch statement grabs what the user picks from the "type" combo box and gathers everything else the user inputs
            then validates it to make sure its applicable to the object type and then adds it to the store list to later be shown
            by the table. It wont allow items with the same SKU to be added.
            */
            switch (type){
                case null -> {System.out.println("Something fucked up really bad");} //it should never return null.
                case "Pick an Item Type" -> {
                    JOptionPane.showMessageDialog(addItemFrame, "Select an item type.");
                }
                case "Cleaning Supplies" -> {
                    boolean toxic = getSelectedTF(optionalTF3);
                    String brand = optionalText1.getText();
                    String category = optionalText2.getText();
                    StoreItem item = new CleaningSupply(skuValid, name, priceValid, amountValid, brand, category, toxic);
                    addValidatedItem(item);
                }
                case "Furniture" -> {
                    String brand = optionalText1.getText();
                    String category = optionalText2.getText();
                    String dimensions = optionalText3.getText();
                    StoreItem item = new Furniture(skuValid, name, priceValid, amountValid, brand, category, dimensions);
                    addValidatedItem(item);
                }
                case "Fruit" -> {
                    try {
                        int calories = Integer.parseInt(optionalText1.getText());
                        boolean isRipe = getSelectedTF(optionalTF2);
                        StoreItem item = new Fruit(skuValid, name, priceValid, amountValid, calories, isRipe);
                        addValidatedItem(item);
                    } catch (NumberFormatException _){
                        JOptionPane.showMessageDialog(addItemFrame, "Please enter a valid calorie number.");
                    }
                }
                case "Vegetable" -> {
                    try{
                        int calories = Integer.parseInt(optionalText1.getText());
                        String variety = optionalText2.getText();
                        StoreItem item = new Vegetable(skuValid, name, priceValid, amountValid, calories, variety);
                        addValidatedItem(item);
                    } catch (NumberFormatException _) {
                        JOptionPane.showMessageDialog(addItemFrame, "Please enter a valid calorie number.");
                    }
                }
                case "Shelf Stable Food" -> {
                    try{
                        int calories = Integer.parseInt(optionalText1.getText());
                        String expDate = optionalText2.getText();
                        StoreItem item = new ShelfStable(skuValid, name, priceValid, amountValid, calories, expDate);
                        addValidatedItem(item);
                    } catch (NumberFormatException _){
                        JOptionPane.showMessageDialog(addItemFrame, "Please enter a valid calorie number.");
                    }
                }
                case "Laptop" -> {
                    try {
                        String brand = optionalText1.getText();
                        int warrantyMonths = Integer.parseInt(optionalText2.getText());
                        double screenSize = Double.parseDouble(optionalText3.getText());
                        int ramGB = Integer.parseInt(optionalText4.getText());
                        StoreItem item = new Laptop(skuValid, name, priceValid, amountValid, brand, warrantyMonths, screenSize, ramGB);
                        addValidatedItem(item);
                    } catch (NumberFormatException _) {
                        JOptionPane.showMessageDialog(addItemFrame, "Please enter a valid number of Warranty Months, Screen Size" +
                                "in inches, and a valid amount of RAM in gigabytes.");
                    }
                }
                case "TV" -> {
                    try {
                        String brand = optionalText1.getText();
                        int warrantyMonths = Integer.parseInt(optionalText2.getText());
                        double screenSize = Double.parseDouble(optionalText3.getText());
                        boolean smartTv = getSelectedTF(optionalTF4);
                        StoreItem item = new TV(skuValid, name, priceValid, amountValid, brand, warrantyMonths, screenSize, smartTv);
                        addValidatedItem(item);
                    } catch (NumberFormatException _){
                        JOptionPane.showMessageDialog(addItemFrame, "Please enter a valid number of Warranty Months and Screen Size in inches.");
                    }
                }
                case "Phone" -> {
                    try {
                        String brand = optionalText1.getText();
                        int warrantyMonths = Integer.parseInt(optionalText2.getText());
                        String carrier = optionalText3.getText();
                        int storageGB = Integer.parseInt(optionalText4.getText());
                        StoreItem item = new Phone(skuValid, name, priceValid, amountValid, brand, warrantyMonths, carrier, storageGB);
                        addValidatedItem(item);
                    } catch (NumberFormatException _) {
                        JOptionPane.showMessageDialog(addItemFrame, "Please enter a valid number of Warranty Months and Screen Size in inches.");
                    }
                }
                case "Shirt" -> {
                    String size = optionalText1.getText();
                    String color = optionalText2.getText();
                    String sleeveType = optionalText3.getText();
                    StoreItem item = new Shirt(skuValid, name, priceValid, amountValid, size, color, sleeveType);
                    addValidatedItem(item);
                }
                case "Outerwear" -> {
                    String size = optionalText1.getText();
                    String color = optionalText2.getText();
                    boolean isWaterproof = getSelectedTF(optionalTF3);
                    StoreItem item = new Outerwear(skuValid, name, priceValid, amountValid, size, color, isWaterproof);
                    addValidatedItem(item);
                }
                case "Shoes" -> {
                    String size = optionalText1.getText();
                    String color = optionalText2.getText();
                    String style = optionalText3.getText();
                    StoreItem item = new Shoe(skuValid, name, priceValid, amountValid, size, color, style);
                    addValidatedItem(item);
                }
                default -> {System.out.println("Something fucked up. It shouldn't ever make it here.");}
            }

        } catch (NumberFormatException _) {
            JOptionPane.showMessageDialog(addItemFrame, "Please enter valid numbers for the SKU and Price");
        }
    }

    /**
     * Attempts to add a fully constructed StoreItem to the inventory and resets the form on success.
     *
     * @param item the StoreItem to add.
     *
     * @implNote If the SKU already exists (duplicate), the item is not added and a dialog is displayed.
     *           On successful add, all input fields are reset to their placeholders and the type resets to default.
     */

    public void addValidatedItem(StoreItem item){
        Boolean isDupe = storeManager.duplicateSkuChecker(item.getSkuNumber());
        if (!isDupe){
            storeManager.addItemToList(item);
            resetAllFields();
            System.out.println("Item Added!: List Size: " + storeManager.getListOfAllItems().size());
        } else {
            JOptionPane.showMessageDialog(addItemFrame,"That SKU number is already in use.");
        }
    }

    /**
     * Resets base inputs and selection to their default placeholder states and hides all optional inputs.
     * <p>
     * This sets:
     * - Type selector back to the default option.
     * - Placeholders for Name, Price, SKU, and Count.
     * - All optional labels/fields/booleans hidden via hideOptionalInputFields (indirectly through type reset).
     * </p>
     */
    public void resetAllFields(){
        typeItems.setSelectedIndex(0);
        setPlaceholderBehavior(itemNameInput, "Enter Item Name");
        setPlaceholderBehavior(itemPriceInput, "Enter Item Price");
        setPlaceholderBehavior(skuInput, "Enter SKU Number");
        setPlaceholderBehavior(itemCountInput, "Enter Amount");
    }


}
