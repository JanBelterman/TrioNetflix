package tabbedPanels.panels.tabs.overviewTabs;

import listeners.OverviewComboBoxListener;
import tabbedPanels.panels.OverviewPanel;
import tables.TableCreator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class OTPercentageWatchedForSeriesAndAccount extends JPanel implements OverviewTab {
    private OverviewPanel overviewPanel;
    private JComboBox comboBoxSeries;
    private JComboBox comboBoxAccount;
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
        setLayout (new BoxLayout(this, BoxLayout.Y_AXIS)); // Setting the layout of this JPanel

        JLabel seriesLabel = new JLabel("Select a series");
        seriesLabel.setFont(new Font("Verdana", Font.BOLD, 12));
        add(seriesLabel);

        // No need for an actionListener, one button will get both comboBox values and use them for the query
        comboBoxSeries = new JComboBox();
        comboBoxSeries.setFont(new Font("Verdana", Font.PLAIN, 12));
        updateComboBoxSeries();
        add(comboBoxSeries);

        JLabel accountLabel = new JLabel("Select an account");
        accountLabel.setFont(new Font("Verdana", Font.BOLD, 12));
        add(accountLabel);

        comboBoxAccount = new JComboBox();
        comboBoxAccount.setFont(new Font("Verdana", Font.PLAIN, 12));
        updateComboBoxAccount();
        add(comboBoxAccount);

        // Add a button which on press gets the combobox values and creates a query with them

        JLabel label1 = new JLabel("Average percentage watched for each episode");
        label1.setFont(new Font("Verdana", Font.PLAIN, 12));
        add(label1);

        errorLabel = new JLabel("");
        errorLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        errorLabel.setForeground(Color.red);
        add(errorLabel);

        updateTable(new ArrayList<HashMap<String, Object>>());
    }

    public void updateComboBoxSeries() {
        for (Object o : this.overviewPanel.getDatabase().getUniqueRecordsOfTable("Series")) {
            comboBoxSeries.addItem(o.toString());
        }
        updatePanel();
    }

    public void updateComboBoxAccount() {
        for (Object o : this.overviewPanel.getDatabase().getUniqueRecordsOfTable("Subscription")) {
            comboBoxAccount.addItem(o.toString());
        }
        updatePanel();
    }


    public void updateTable(ArrayList<HashMap<String, Object>> resultList) {
        if (scrollPane != null) { remove(scrollPane); }

        ArrayList<String> columnNames = new ArrayList<>();
        columnNames.add("");
        columnNames.add("");
        columnNames.add("");

        // Transform ArrayList<HashMap<String, Object>> to ArrayList<String>
        ArrayList<String> results = new ArrayList<>();
        for (HashMap<String, Object> result : resultList) {
            results.add(result.get("").toString());
            results.add(result.get("").toString());
            results.add(result.get("").toString());
        }

        table = tableCreator.createTable(results, columnNames);
        scrollPane = new JScrollPane(table);
        add(scrollPane);
        updatePanel();
    }

    @Override
    public void handleActionEvent() {

    }

    private void updatePanel() {
        revalidate();
        repaint();
    }

}