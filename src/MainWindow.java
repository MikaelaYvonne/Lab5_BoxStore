import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow implements ActionListener {
    StoreManager storeManager = new StoreManager();
    JFrame mainWindow;

    //constructor for mainWindow Object
    public MainWindow(){
        //JFrame
        mainWindow = new JFrame();
        mainWindow.setTitle("Box Store Inventory Manager");
        mainWindow.setLayout(null);
        mainWindow.setSize(400,125);
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setResizable(false);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = mainWindow.getContentPane();

        //need buttons to add / remove
        //add button
        JButton addBtn = new JButton("Add Item");
        addBtn.setBounds(200,0,200,100);
        addBtn.setFocusable(false);
        addBtn.setActionCommand("add");

        //remove button
        JButton viewBtn = new JButton("Buy Items");
        viewBtn.setBounds(0,0,200,100);
        viewBtn.setFocusable(false);
        viewBtn.setActionCommand("view");

        //add it all to the content pane
        contentPane.add(addBtn);
        contentPane.add(viewBtn);

        addBtn.addActionListener(this);
        viewBtn.addActionListener(this);

        //make the window visible.
        mainWindow.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e){
        if ("add".equals(e.getActionCommand())){
            addItem();
        } else if ("view".equals(e.getActionCommand())) {
            buyItems();
        }
    }

    public void addItem(){
        new AddItemWindow(mainWindow, storeManager);
    }
    public void buyItems(){
        new BuyItemWindow(mainWindow, storeManager);
    }

}



