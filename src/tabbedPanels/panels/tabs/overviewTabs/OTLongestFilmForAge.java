package tabbedPanels.panels.tabs.overviewTabs;

import database.Database;
import listeners.LongestAgeListener;
import tabbedPanels.panels.OverviewPanel;
import tables.TableCreator;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class OTLongestFilmForAge extends JPanel {

    private OverviewPanel overviewPanel;

    private Container topContainer;
    private Container mainContainer;

    private JLabel ageText;
    private NumberFormat numberFormat;
    private JFormattedTextField ageField;
    private JButton searchButton;
    private JLabel errorLabel;

    private TableCreator tableCreator;
    private JTable table;
    private JScrollPane scrollPane;

    public OTLongestFilmForAge(OverviewPanel overviewPanel) {
        this.overviewPanel = overviewPanel;
        this.tableCreator = new TableCreator(overviewPanel.getDatabase());
        createComponents();
    }

    public void createComponents() {
        setLayout(new BorderLayout());

        //Dividing panel into seperate containers
        topContainer = new Container();
        mainContainer = new Container();
        topContainer.setLayout(new GridLayout(2, 2));
        mainContainer.setLayout(new BorderLayout());

        ageText = new JLabel("Set age: ");
        ageText.setFont(new Font("Verdana", Font.BOLD, 12));
        numberFormat = NumberFormat.getNumberInstance();
        ageField = new JFormattedTextField(numberFormat);
        ageField.setFont(new Font("Verdana", Font.PLAIN, 12));
        searchButton = new JButton("Search");
        searchButton.setFont(new Font("Verdana", Font.BOLD, 12));
        searchButton.addActionListener(new LongestAgeListener(this));
        errorLabel = new JLabel("");
        errorLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        errorLabel.setForeground(Color.red);

        //add everything to JPanel
        add(topContainer, BorderLayout.NORTH);
        add(mainContainer, BorderLayout.CENTER);

        topContainer.add(ageText);
        topContainer.add(ageField);
        topContainer.add(new JLabel(""));
        topContainer.add(searchButton);

        add(errorLabel, BorderLayout.SOUTH);

        updateTable(new ArrayList<HashMap<String, Object>>());
    }

    public void updateTable(ArrayList<HashMap<String, Object>> resultList) {
        if (scrollPane != null) {
            remove(scrollPane);
        }

        ArrayList<String> columnNames = new ArrayList<>();
        columnNames.add("ContentNr");
        columnNames.add("Title");
        columnNames.add("Duration");

        ArrayList<String> results = new ArrayList<>();
        for (HashMap<String, Object> result : resultList) {
            results.add(result.get("ContentNr").toString());
            results.add(result.get("Title").toString());
            results.add(result.get("Duration").toString());
        }

        table = tableCreator.createTable(results, columnNames);
        scrollPane = new JScrollPane(table);
        add(scrollPane);
        updatePanel();

    }

    public int getAgeFieldValue() {
        return Integer.parseInt(ageField.getText());
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

    public void updatePanel() {
        revalidate();
        repaint();
    }
}
