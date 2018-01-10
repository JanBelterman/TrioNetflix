package tabbedPanels.panels.menus;

import database.Database;
import listeners.OverviewComboBoxListener;
import tabbedPanels.panels.tabs.overviewTabs.OverviewTab;

import javax.swing.*;
import java.awt.*;

public class ButtonComboBoxMenu extends JPanel {
    private OverviewTab overviewTab;
    private JComboBox comboBoxAccount;

    public ButtonComboBoxMenu(OverviewTab overviewTab) {
        this.overviewTab = overviewTab;
        createComponents();
    }

    private void createComponents() {
        this.setLayout(new GridLayout(1, 2));
        JLabel accountLabel = new JLabel("Select an account");
        accountLabel.setFont(new Font("Verdana", Font.BOLD, 12));
        add(accountLabel);

        comboBoxAccount = new JComboBox();
        comboBoxAccount.setFont(new Font("Verdana", Font.PLAIN, 12));
        updateComboBoxAccount();
        comboBoxAccount.addActionListener(new OverviewComboBoxListener(overviewTab));
        add(comboBoxAccount);
    }

    public void updateComboBoxAccount() {
        for (Object o : this.overviewTab.getDatabase().getUniqueRecordsOfTable("Subscription")) {
            comboBoxAccount.addItem(o.toString());
        }
        updatePanel();
    }

    private void updatePanel() {
        revalidate();
        repaint();
    }

    public String getComboBoxValue() {
        return comboBoxAccount.getSelectedItem().toString();
    }

}