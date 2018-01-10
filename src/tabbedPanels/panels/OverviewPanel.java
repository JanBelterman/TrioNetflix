package tabbedPanels.panels;

import database.Database;
import tabbedPanels.panels.tabs.overviewTabs.OTMovieWatchedByAccount;
import tabbedPanels.panels.tabs.overviewTabs.OTPercentageWatchedForSeries;
import tabbedPanels.panels.tabs.overviewTabs.OTPercentageWatchedForSeriesAndAccount;

import javax.swing.*;
import java.awt.*;

public class OverviewPanel extends JPanel {
    private Database database;

    public OverviewPanel(Database database) {
        super(new BorderLayout());
        this.database = database;
        createComponents();
    }

    private void createComponents() {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Verdana", Font.BOLD, 15));
        tabbedPane.setTabPlacement(JTabbedPane.LEFT);

        tabbedPane.addTab("Percent watched for series", new OTPercentageWatchedForSeries(this));
        tabbedPane.addTab("Percentage watched for series for account", new OTPercentageWatchedForSeriesAndAccount(this));
        tabbedPane.addTab("Movies watched by account", new OTMovieWatchedByAccount(this));

        add(tabbedPane);
    }

    public Database getDatabase() {
        return database;
    }

}