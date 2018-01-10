package tabbedPanels.panels.tabs.overviewTabs;

import database.Database;
import tabbedPanels.panels.OverviewPanel;
import tabbedPanels.panels.menus.ButtonComboBoxMenu;
import tables.TableCreator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class OTMovieWatchedByAccount extends JPanel implements OverviewTab {
    private OverviewPanel overviewPanel;
    private ButtonComboBoxMenu buttonComboBoxMenu;

    private JLabel errorLabel;

    private TableCreator tableCreator;
    private JTable table;
    private JScrollPane scrollPane;

    public OTMovieWatchedByAccount(OverviewPanel overviewPanel) {
        this.overviewPanel = overviewPanel;
        this.tableCreator = new TableCreator(overviewPanel.getDatabase());
        createComponents();
    }

    private void createComponents() {
        setLayout (new BorderLayout()); // Setting the layout of this JPanel

        buttonComboBoxMenu = new ButtonComboBoxMenu(this);
        add(buttonComboBoxMenu, BorderLayout.NORTH);

        errorLabel = new JLabel("");
        errorLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        errorLabel.setForeground(Color.red);
        add(errorLabel, BorderLayout.SOUTH);

        updateTable(new ArrayList<HashMap<String, Object>>());
    }

    public void updateTable(ArrayList<HashMap<String, Object>> resultList) {
        if (scrollPane != null) { remove(scrollPane); }

        ArrayList<String> columnNames = new ArrayList<>();
        columnNames.add("Movies");

        // Transform ArrayList<HashMap<String, Object>> to ArrayList<String>
        ArrayList<String> results = new ArrayList<>();
        for (HashMap<String, Object> result : resultList) {
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

    public void putError(String errorText) {
        this.errorLabel.setText(errorText);
        updatePanel();
    }

    @Override
    public void handleActionEvent() {
        String account = buttonComboBoxMenu.getComboBoxValue();

        String sql = "SELECT Content.Title\n";
        sql += "FROM Content\n";
        sql += "INNER JOIN Stream ON Stream.ContentNr = Content.ContentNr\n";
        sql += "GROUP BY Content.Title\n";
        sql += "HAVING MAX(Content.ContentType) = 'Movie'\n";
        sql += "AND MAX(Stream.SubscriptionEmail) = '" + account + "'\n";

        ArrayList<HashMap<String, Object>> resultList = getDatabase().getResultsOfQuery(sql);

        if (resultList.size() == 0) {
            putError("No movies have been watched by this account");
            updateTable(new ArrayList<HashMap<String, Object>>());
        } else {
            updateTable(resultList);
            putError("");
        }

    }

    @Override
    public Database getDatabase() {
        return overviewPanel.getDatabase();
    }

}