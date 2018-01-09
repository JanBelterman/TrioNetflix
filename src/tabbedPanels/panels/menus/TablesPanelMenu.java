package tabbedPanels.panels.menus;

import database.Database;
import listeners.SwitchTableListener;
import tabbedPanels.panels.TablesPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TablesPanelMenu extends JPanel {
    private TablesPanel tablesPanel;

    public TablesPanelMenu(TablesPanel tablesPanel) {
        super(new GridLayout(1, 2));
        this.tablesPanel = tablesPanel;
        createComponents();
    }

    public void createComponents() {
        ArrayList<JButton> buttons = new ArrayList<JButton>(); // Make a button list
        for (String tableName : this.tablesPanel.getDatabase().tableNames()) { // Iterate trough the column names
            buttons.add(new JButton(tableName)); // Add a button for every column name
        }

        for (JButton b : buttons) { // Iterate trough the button list
            b.setFocusPainted(false); // Get rid of the square around the button when in focus
            b.addActionListener(new SwitchTableListener(this.tablesPanel, b.getText())); // Add actionListener
            b.setFont(new Font("Verdana", Font.PLAIN, 14));
        }

        for (JButton b : buttons) { // Iterate trough the button list
            add(b); // Add the buttons to the JPanel
        }
    }

    public Database getDatabase() {
        return this.tablesPanel.getDatabase();
    }

}