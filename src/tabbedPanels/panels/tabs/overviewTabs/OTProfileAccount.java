package tabbedPanels.panels.tabs.overviewTabs;

import tabbedPanels.panels.OverviewPanel;
import tables.TableCreator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class OTProfileAccount extends JPanel {
    private OverviewPanel overviewPanel;
    private TableCreator tableCreator;

    private JLabel errorLabel;
    private JScrollPane scrollPane;
    private JTable table;

    public OTProfileAccount(OverviewPanel overviewPanel) {
        this.overviewPanel = overviewPanel;
        this.tableCreator = new TableCreator(this.overviewPanel.getDatabase());

        createComponents();
    }

    private void createComponents() {
        setLayout(new BorderLayout());

        JLabel infoLabel = new JLabel("The accounts with only one profile:");
        infoLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
        add(infoLabel, BorderLayout.NORTH);

        errorLabel = new JLabel("");
        errorLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        errorLabel.setForeground(Color.red);
        add(errorLabel, BorderLayout.SOUTH);

        createTable();
    }

    private void createTable() {
        String sql = "SELECT Subscription.Email\n";
        sql += "FROM Subscription\n";
        sql += "INNER JOIN [Profile] ON [Profile].Email = Subscription.Email\n";
        sql += "GROUP BY Subscription.Email\n";
        sql += "HAVING COUNT([Profile].Email) = 1";

        ArrayList<HashMap<String, Object>> resultList = overviewPanel.getDatabase().getResultsOfQuery(sql);

        if (resultList.size() == 0) {
            putError("There are no accounts with only one profile");
            updateTable(new ArrayList<HashMap<String, Object>>());
        } else {
            updateTable(resultList);
            deleteError();
        }
    }

    public void putError(String errorText) {
        errorLabel.setText(errorText);
        updatePanel();
    }

    public void deleteError() {
        errorLabel.setText("");
        updatePanel();
    }

    private void updatePanel() {
        revalidate();
        repaint();
    }

    public void updateTable(ArrayList<HashMap<String, Object>> resultList) {
        if (scrollPane != null) { remove(scrollPane); }

        ArrayList<String> columnNames = new ArrayList<>();
        columnNames.add("SubscriptionEmail");

        // Transform ArrayList<HashMap<String, Object>> to ArrayList<String>
        ArrayList<String> results = new ArrayList<>();
        for (HashMap<String, Object> result : resultList) {
            results.add(result.get("Email").toString());
        }

        table = tableCreator.createTable(results, columnNames);
        scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        updatePanel();
    }

}