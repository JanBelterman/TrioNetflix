package tabbedPanels.panels.menus;

import database.Database;
import listeners.ResultListener;
import tabbedPanels.panels.tabs.overviewTabs.OTPercentageWatchedForSeriesAndAccount;

import javax.swing.*;
import java.awt.*;

public class OverviewMenu extends JPanel {
    private OTPercentageWatchedForSeriesAndAccount otPercentageWatchedForSeriesAndAccount;
    private JComboBox comboBoxSeries;
    private JComboBox comboBoxAccount;

    public OverviewMenu(OTPercentageWatchedForSeriesAndAccount otPercentageWatchedForSeriesAndAccount) {
        super(new GridLayout(3, 2));
        this.otPercentageWatchedForSeriesAndAccount = otPercentageWatchedForSeriesAndAccount;
        createComponents();
    }

    private void createComponents() {
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

        JLabel label1 = new JLabel("% watched");
        label1.setFont(new Font("Verdana", Font.PLAIN, 12));
        add(label1, BorderLayout.BEFORE_FIRST_LINE);

        // Add a button which on press gets the combobox values and creates a query with them
        JButton resultButton = new JButton("Get results");
        resultButton.setFocusPainted(false);
        resultButton.setFont(new Font("Verdana", Font.PLAIN, 12));
        resultButton.addActionListener(new ResultListener(this));
        add(resultButton);
    }

    public void updateComboBoxSeries() {
        for (Object o : this.otPercentageWatchedForSeriesAndAccount.getDatabase().getUniqueRecordsOfTable("Series")) {
            comboBoxSeries.addItem(o.toString());
        }
        updatePanel();
    }

    public void updateComboBoxAccount() {
        for (Object o : this.otPercentageWatchedForSeriesAndAccount.getDatabase().getUniqueRecordsOfTable("Subscription")) {
            comboBoxAccount.addItem(o.toString());
        }
        updatePanel();
    }

    private void updatePanel() {
        revalidate();
        repaint();
    }

    public Database getDatabase() {
        return otPercentageWatchedForSeriesAndAccount.getDatabase();
    }

    public String getComboBoxSeriesValue() {
        return comboBoxSeries.getSelectedItem().toString();
    }

    public String getComboBoxAccountValue() {
        return comboBoxAccount.getSelectedItem().toString();
    }

    public OTPercentageWatchedForSeriesAndAccount getOtPercentageWatchedForSeriesAndAccount() {
        return otPercentageWatchedForSeriesAndAccount;
    }

}