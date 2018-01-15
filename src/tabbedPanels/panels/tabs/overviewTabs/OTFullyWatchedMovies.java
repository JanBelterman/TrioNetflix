package tabbedPanels.panels.tabs.overviewTabs;

import database.Database;
import tabbedPanels.panels.OverviewPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class OTFullyWatchedMovies extends JPanel {
    private OverviewPanel overviewPanel;
    private Container topContainer;

    private JLabel movieLabel;
    private JComboBox movieComboBox;
    private JLabel descriptionLabel;
    private JLabel movieCount;

    public OTFullyWatchedMovies(OverviewPanel overviewPanel) {
        this.overviewPanel = overviewPanel;
        createComponents();
    }

    public void createComponents() {
        setLayout(new BorderLayout());

        topContainer = new Container();
        topContainer.setLayout(new GridLayout(2,2));

        //Creating UI
        movieLabel = new JLabel("Select a movie: ");
        movieLabel.setFont(new Font("Verdana", Font.BOLD, 12));

        movieComboBox = new JComboBox();
        movieComboBox.setFont(new Font("Verdana", Font.PLAIN, 12));
        updateComboBox(movieComboBox);
        movieComboBox.addActionListener(this::actionPerformed);
        descriptionLabel = new JLabel("Amount of people who fully watched selected film:");
        descriptionLabel.setFont(new Font("Verdana", Font.BOLD, 12));
        movieCount = new JLabel("Select Movie");
        movieCount.setFont(new Font("Verdana", Font.BOLD, 12));

        //Add items to panel
        topContainer.add(movieLabel);
        topContainer.add(movieComboBox);
        topContainer.add(descriptionLabel);
        topContainer.add(movieCount);

        add(topContainer, BorderLayout.NORTH);
    }

    //Update combobox with appropriate content
    public void updateComboBox(JComboBox comboBox) {
        String sql = "SELECT Content.Title FROM Content WHERE Content.ContentType = 'Movie';";

        for (Object o : this.overviewPanel.getDatabase().getResultsOfQuery(sql)) {
            //This should filter out the "{Title=" stuff.
            String output = o.toString().substring(7,(o.toString().length() - 1));

            comboBox.addItem(output);
        }

        updatePanel();
    }

    public void actionPerformed(ActionEvent e) {
        String selectedMovie = movieComboBox.getSelectedItem().toString();

        String sql = "SELECT COUNT(DISTINCT Stream.ProfileName)\n";
        sql += "FROM Stream\n";
        sql += "INNER JOIN Content ON Stream.ContentNr = Content.ContentNr\n";
        sql += "WHERE Content.Title = '" + selectedMovie + "' AND Stream.PercentageWatched = 100;";

        //Filter the count out of the sql query result.
        String sqlresult = getDatabase().getResultsOfQuery(sql).toString();
        String result = sqlresult.substring(3,(sqlresult.length() - 2));

        movieCount.setText(result);
    }

    public void updatePanel() {
        revalidate();
        repaint();
    }

    public Database getDatabase() {
        return overviewPanel.getDatabase();
    }
}
