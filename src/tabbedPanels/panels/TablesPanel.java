package tabbedPanels.panels;

import database.Database;
import tabbedPanels.panels.menus.TablesPanelMenu;
import tables.TableCreator;

import javax.swing.*;
import java.awt.*;

public class TablesPanel extends JPanel {
    private Database database;
    private TableCreator TableCreator;

    private JTable table;
    private JScrollPane scrollPane;
    private JLabel label;

    public TablesPanel(Database database) {
        super(new BorderLayout());
        this.database = database;
        this.TableCreator = new TableCreator(this.database);
        createComponents();
    }

    private void createComponents() {
        TablesPanelMenu tablesPanelMenu = new TablesPanelMenu(this);
        add(tablesPanelMenu, BorderLayout.SOUTH);

        updateTable(database.tableNames().get(0));
    }

    public void updateTable(String tableName) {
        if (label != null) {
            remove(label);
        }
        label = new JLabel(tableName, SwingConstants.CENTER);
        label.setFont(new Font("Verdana", Font.BOLD, 20));
        add(label, BorderLayout.BEFORE_FIRST_LINE);

        if (scrollPane != null) {
            remove(scrollPane);
        }
        table = TableCreator.updateTable(tableName);
        scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    public Database getDatabase() {
        return this.database;
    }

}