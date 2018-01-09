package tabbedPanels.panels;

import database.Database;
import tabbedPanels.panels.tabs.InsertTab;

import javax.swing.*;
import java.awt.*;

public class InsertPanel extends JPanel {
    private Database database;

    public InsertPanel(Database database) {
        super(new BorderLayout());
        this.database = database;

        createComponents();
    }

    private void createComponents() {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Verdana", Font.BOLD, 15));
        tabbedPane.setTabPlacement(JTabbedPane.LEFT);

        for (String tableName : this.database.tableNames()) {
            tabbedPane.add(tableName, new InsertTab(this, tableName));
        }
        add(tabbedPane);
    }

    public Database getDatabase() {
        return this.database;
    }

}