package tabbedPanels.panels;

import database.Database;
import tabbedPanels.panels.tabs.UpdateTab;

import javax.swing.*;
import java.awt.*;

public class UpdatePanel extends JPanel {
    private Database database;

    public UpdatePanel(Database database) {
        super(new BorderLayout());
        this.database = database;
        createComponents();
    }

    public void createComponents() {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Verdana", Font.BOLD, 15));
        tabbedPane.setTabPlacement(JTabbedPane.LEFT);

        for (String tableName : this.database.tableNames()) {
            tabbedPane.addTab(tableName, new UpdateTab(this, tableName));
        }
        add(tabbedPane);
    }

    public Database getDatabase() {
        return database;
    }

}