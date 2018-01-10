package listeners;

import database.Database;
import tabbedPanels.panels.menus.OverviewMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class ResultListener implements ActionListener {
    private OverviewMenu overviewMenu;

    public ResultListener(OverviewMenu overviewMenu) {
        this.overviewMenu = overviewMenu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Database database = overviewMenu.getDatabase();

        String selectedSeries = overviewMenu.getComboBoxSeriesValue();
        String selectedAccount = overviewMenu.getComboBoxAccountValue();

        String sql = "SELECT AVG(PercentageWatched) AS [%_Watched], Content.ContentNr, MAX(Content.Title) AS Title\n";
        sql += "FROM Stream\n";
        sql += "RIGHT JOIN Content ON Content.ContentNr = Stream.ContentNr\n";
        sql += "RIGHT JOIN Subscription ON Subscription.Email = Stream.SubscriptionEmail\n";
        sql += "GROUP BY Content.ContentNr\n";
        sql += "HAVING MAX(Content.Series) = '" + selectedSeries + "'\n";
        sql += "AND MAX(Subscription.Email) = '" + selectedAccount + "'";

        ArrayList<HashMap<String, Object>> resultList = database.getResultsOfQuery(sql);

        if (resultList.size() == 0) {
            this.overviewMenu.getOtPercentageWatchedForSeriesAndAccount().putError("There are no results for this series");
            this.overviewMenu.getOtPercentageWatchedForSeriesAndAccount().updateTable(new ArrayList<HashMap<String, Object>>());
        } else {
            this.overviewMenu.getOtPercentageWatchedForSeriesAndAccount().updateTable(resultList);
            this.overviewMenu.getOtPercentageWatchedForSeriesAndAccount().putError("");
        }

    }

}