package tabbedPanels.panels.tabs.overviewTabs;

import database.Database;
import listeners.OverviewComboBoxListener;
import tabbedPanels.panels.OverviewPanel;
import tables.TableCreator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class OTPercentageWatchedForSeries extends JPanel implements OverviewTab {
    private OverviewPanel overviewPanel;
    private JComboBox comboBox;
    private JLabel errorLabel;

    private TableCreator tableCreator;
    private JTable table;
    private JScrollPane scrollPane;

    public OTPercentageWatchedForSeries(OverviewPanel overviewPanel) {
        this.overviewPanel = overviewPanel;
        this.tableCreator = new TableCreator(overviewPanel.getDatabase());
        createComponents();
    }

    private void createComponents() {
        setLayout (new BoxLayout (this, BoxLayout.Y_AXIS)); // Setting the layout of this JPanel

        JLabel label = new JLabel("Select a series");
        label.setFont(new Font("Verdana", Font.BOLD, 12));
        add(label);

        comboBox = new JComboBox();
        comboBox.setFont(new Font("Verdana", Font.PLAIN, 12));
        updateComboBox();
        comboBox.addActionListener(new OverviewComboBoxListener(this));
        add(comboBox);

        JLabel label1 = new JLabel("Average percentage watched for each episode");
        label1.setFont(new Font("Verdana", Font.PLAIN, 12));
        add(label1);

        errorLabel = new JLabel("");
        errorLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        errorLabel.setForeground(Color.red);
        add(errorLabel);

        updateTable(new ArrayList<HashMap<String, Object>>());
    }

    public void updateComboBox() {
        for (Object o : this.overviewPanel.getDatabase().getUniqueRecordsOfTable("Series")) {
            comboBox.addItem(o.toString());
        }
        updatePanel();
    }

    public void updateTable(ArrayList<HashMap<String, Object>> resultList) {
        if (scrollPane != null) { remove(scrollPane); }

        ArrayList<String> columnNames = new ArrayList<>();
        columnNames.add("Average % Watched");
        columnNames.add("ContentNr");
        columnNames.add("Title");

        // Transform ArrayList<HashMap<String, Object>> to ArrayList<String>
        ArrayList<String> results = new ArrayList<>();
        for (HashMap<String, Object> result : resultList) {
            results.add(result.get("AveragePercentageWatched").toString());
            results.add(result.get("ContentNr").toString());
            results.add(result.get("Title").toString());
        }

        table = tableCreator.createTable(results, columnNames);
        scrollPane = new JScrollPane(table);
        add(scrollPane);
        updatePanel();
    }

    @Override
    public void handleActionEvent() {
        String selectedSeries = getComboBox().getSelectedItem().toString();
        String sql = "SELECT AVG(PercentageWatched) AS AveragePercentageWatched, Content.ContentNr, MAX(Content.Title) AS Title\n";
        sql += "FROM Stream\n";
        sql += "RIGHT JOIN Content ON Content.ContentNr = Stream.ContentNr\n";
        sql += "GROUP BY Content.ContentNr\n";
        sql += "HAVING MAX(Content.Series) = '" + selectedSeries + "'";

        ArrayList<HashMap<String, Object>> resultList = getDatabase().getResultsOfQuery(sql);

        if (resultList.size() == 0) {
            putError("There are no results for this series");
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

    public Database getDatabase() {
        return overviewPanel.getDatabase();
    }

    public JComboBox getComboBox() {
        return comboBox;
    }

    private void updatePanel() {
        revalidate();
        repaint();
    }

}