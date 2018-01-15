package listeners;

import database.Database;
import tabbedPanels.panels.tabs.overviewTabs.OTLongestFilmForAge;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class LongestAgeListener implements ActionListener {
    private OTLongestFilmForAge otLongest;

    public LongestAgeListener(OTLongestFilmForAge otLongest) {
        this.otLongest = otLongest;
    }

    //This method handles the action where it builds the sql query and updates the table in the panel with the results.
    @Override
    public void actionPerformed(ActionEvent e) {
        Database database = otLongest.getDatabase();

        int selectedAge = otLongest.getAgeFieldValue();

        //Building the SQL query here.
        String sql = "SELECT Content.ContentNr, Content.Title, Content.Duration ";
        sql += "FROM Content ";
        sql += "WHERE Content.ContentType = 'Movie' AND Content.MinimumAge <= " + selectedAge + " ";
        sql += "ORDER BY Duration DESC;";

        ArrayList<HashMap<String, Object>> resultList = database.getResultsOfQuery(sql);

        //Error handling and updating the table with results. If there are no results an empty table is build.
        if (resultList.size() == 0) {
            this.otLongest.putError("There are no results for this series");
            this.otLongest.updateTable(new ArrayList<HashMap<String, Object>>());
        } else {
            this.otLongest.updateTable(resultList);
            this.otLongest.deleteError();
        }
    }
}
