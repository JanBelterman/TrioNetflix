package tabbedPanels.panels.tabs.overviewTabs;

import database.Database;

public interface OverviewTab {
    void handleActionEvent();
    Database getDatabase();
}