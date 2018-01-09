package listeners;

import tabbedPanels.panels.tabs.overviewTabs.OverviewTab;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OverviewComboBoxListener implements ActionListener {
    private OverviewTab overviewTab;

    public OverviewComboBoxListener(OverviewTab overviewTab) {
        this.overviewTab = overviewTab;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Each overviewTab needs to do something different when the comboBox is used, so the action is handled inside the overviewTab itself
        overviewTab.handleActionEvent();
    }

}