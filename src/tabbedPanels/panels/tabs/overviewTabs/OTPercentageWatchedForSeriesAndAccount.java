package tabbedPanels.panels.tabs.overviewTabs;

import database.Database;
import listeners.OverviewComboBoxListener;
import tabbedPanels.panels.OverviewPanel;
import tabbedPanels.panels.menus.OverviewMenu;
import tables.TableCreator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class OTPercentageWatchedForSeriesAndAccount extends JPanel {
    private OverviewPanel overviewPanel;
    private JLabel errorLabel;

    private TableCreator tableCreator;
    private JTable table;
    private JScrollPane scrollPane;

    public OTPercentageWatchedForSeriesAndAccount(OverviewPanel overviewPanel) {
        this.overviewPanel = overviewPanel;
        this.tableCreator = new TableCreator(overviewPanel.getDatabase());
        createComponents();
    }

    private void createComponents() {
        setLayout (new BorderLayout()); // Setting the layout of this JPanel

        add(new OverviewMenu(this), BorderLayout.NORTH);

        errorLabel = new JLabel("");
        errorLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        errorLabel.setForeground(Color.red);
        add(errorLabel, BorderLayout.SOUTH);

        updateTable(new ArrayList<HashMap<String, Object>>());
    }


    public void updateTable(ArrayList<HashMap<String, Object>> resultList) {
        if (scrollPane != null) { remove(scrollPane); }

        ArrayList<String> columnNames = new ArrayList<>();
        columnNames.add("% watched");
        columnNames.add("ContentNr");
        columnNames.add("Title");

        // Transform ArrayList<HashMap<String, Object>> to ArrayList<String>
        ArrayList<String> results = new ArrayList<>();
        for (HashMap<String, Object> result : resultList) {
            results.add(result.get("%_Watched").toString());
            results.add(result.get("ContentNr").toString());
            results.add(result.get("Title").toString());
        }

        table = tableCreator.createTable(results, columnNames);
        scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        updatePanel();
    }

    private void updatePanel() {
        revalidate();
        repaint();
    }

    public Database getDatabase() {
        return this.overviewPanel.getDatabase();
    }

    public void putError(String errorText) {
        errorLabel.setText(errorText);
        updatePanel();
    }

    public void deleteError() {
        errorLabel.setText("");
        updatePanel();
    }

}