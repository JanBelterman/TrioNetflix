package tabbedPanels.panels.tabs;

import database.Database;
import listeners.InsertListener;
import tabbedPanels.panels.InsertPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class InsertTab extends JPanel {
    private InsertPanel insertPanel;
    private String tableName;
    private ArrayList<String> columnNames;
    private ArrayList<JTextField> textFields;

    public InsertTab(InsertPanel insertPanel, String tableName) {
        this.insertPanel = insertPanel;
        this.tableName = tableName;
        this.columnNames = this.insertPanel.getDatabase().getcolumnNamesForTable(tableName);
        this.textFields = new ArrayList<>();
        createComponents();
    }

    public void createComponents() {
        setLayout (new BoxLayout(this, BoxLayout.Y_AXIS)); // Setting the layout of this JPanel

        ArrayList<JLabel> labels = new ArrayList<>();
        // Creating a textField for every column name of this table
        for (String s : columnNames) {
            textFields.add(new JTextField());
            labels.add(new JLabel(s));
        }

        int i = 0;
        // Adding all of the labels and textFields | changing the font of all of the labels and textFields
        for (JLabel label : labels) {
            label.setFont(new Font("Verdana", Font.PLAIN, 12));
            add(label);
            textFields.get(i).setFont(new Font("Verdana", Font.PLAIN, 12));
            add(textFields.get(i));
            i++;
        }

        JButton insertButton = new JButton("Insert");
        insertButton.setFocusPainted(false);
        insertButton.setFont(new Font("Verdana", Font.PLAIN, 12));
        insertButton.addActionListener(new InsertListener(this));
        add(insertButton);
    }

    public Database getDatabase() {
        return this.insertPanel.getDatabase();
    }

    public String getTableName() {
        return this.tableName;
    }

    public ArrayList<JTextField> getTextFields() {
        return this.textFields;
    }

}