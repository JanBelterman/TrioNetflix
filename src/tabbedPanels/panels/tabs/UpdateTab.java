package tabbedPanels.panels.tabs;

import database.Database;
import listeners.UpdateComboBoxListener;
import listeners.DeleteListener;
import listeners.UpdateListener;
import tabbedPanels.panels.UpdatePanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UpdateTab extends JPanel {
    private UpdatePanel updatePanel;
    private JComboBox comboBox;
    private String tableName;
    private ArrayList<String> columnNames;
    private ArrayList<String> primaryKeys;
    private ArrayList<JTextField> textFields;

    // Constructor
    public UpdateTab(UpdatePanel updatePanel, String tableName) {
        this.updatePanel = updatePanel;
        this.tableName = tableName;
        this.columnNames = this.updatePanel.getDatabase().getcolumnNamesForTable(tableName);
        this.primaryKeys = getDatabase().getPrimaryKeysColumnNamesForTable(tableName);
        this.textFields = new ArrayList<JTextField>();
        createComponents();
    }

    // Create the components inside the JPanel
    public void createComponents() {
        setLayout (new BoxLayout (this, BoxLayout.Y_AXIS)); // Setting the layout of this JPanel
        JLabel headingLabel = new JLabel(tableName);
        headingLabel.setFont(new Font("Verdana", Font.BOLD, 14));
        add(headingLabel); // Adding a label to display the table name on top op the JPanel

        // Adding and updating the comboBox with all record's primary keys
        comboBox = new JComboBox();
        comboBox.setFont(new Font("Verdana", Font.PLAIN, 12));
        add(comboBox);
        updateComboBox(); // the updateComboBox() update's the comboBox to show all (updated) record's primary keys

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

        // Adding and customizing the update button
        JButton updateButton = new JButton("Update");
        updateButton.setFocusPainted(false);
        updateButton.setFont(new Font("Verdana", Font.PLAIN, 12));
        updateButton.addActionListener(new UpdateListener(this));
        add(updateButton);
        comboBox.addActionListener(new UpdateComboBoxListener(this, this.comboBox));

        // Adding and customizing the delete button
        JButton deleteButton = new JButton("Delete");
        deleteButton.setFocusPainted(false);
        deleteButton.setFont(new Font("Verdana", Font.PLAIN, 12));
        deleteButton.addActionListener(new DeleteListener(this));
        add(deleteButton);
    }

    // Update comboBox | If a new record is added | If a record is updated
    public void updateComboBox() {
        for (Object o : this.updatePanel.getDatabase().getUniqueRecordsOfTable(tableName)) {
            comboBox.addItem(o.toString());
        }
        revalidate();
        repaint();
    }

    public String getComboBoxValue() {
        return this.comboBox.getSelectedItem().toString();
    }

    public String getTableName() {
        return this.tableName;
    }

    public ArrayList<String> getColumnNames() {
        return this.columnNames;
    }

    public ArrayList<String> getPrimaryKeys() {
        return this.primaryKeys;
    }

    public ArrayList<JTextField> getTextFields() {
        return this.textFields;
    }

    public Database getDatabase() {
        return this.updatePanel.getDatabase();
    }

}