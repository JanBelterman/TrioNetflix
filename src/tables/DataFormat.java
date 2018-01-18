package tables;

import java.util.ArrayList;
import java.util.HashMap;

public class DataFormat {

    public static String[][] toTableData(ArrayList<HashMap<String, Object>> resultSet, int rows, int columns) {
        String[][] data = new String[rows][columns];

        int i = 0;
        for (int x = 0; x < data.length; x++) { // Loop through the rows
            for (int y = 0; y < data[x].length; y++) { // Loop trough the columns
                data[x][y] = resultSet.get(i).toString();
                i++;
            }
        }

        return data;
    }

    public static ArrayList<String> toList(ArrayList<HashMap<String, Object>> resultSet, String... columnNames) {
        ArrayList<String> resultsInList = new ArrayList<>();

        for (HashMap<String, Object> result : resultSet) {

            for (String columnName : columnNames) {
                resultsInList.add(result.get(columnName).toString());
            }

        }

        return resultsInList;
    }

}