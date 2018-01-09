package tables;

import database.Database;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TableCreator {
    private Database database;

    public TableCreator(Database database) {
        this.database = database;
    }

    public JTable updateTable(String tableName) {
        // Create an String array with the columnNames
        ArrayList<String> columnNamesList = database.getcolumnNamesForTable(tableName); // Get all of the columns for a specific tableName from the database
        String[] columnNames = new String[columnNamesList.size()]; // Create a String array, sized to the amount of columns
        columnNames = columnNamesList.toArray(columnNames); // Transform the columns gotten from the database in a List form to an array

        // The number of rows is the the length of the tuple's from the table divided by the amount of columns
        int rows = database.getTable(tableName).size() / columnNames.length;
        // The number of columns is the amount of columns
        int columns = columnNames.length;
        // Create a two-dimensional String array, with the number of rows and columns
        String[][] data = new String[rows][columns];
        // Get all of the records for a specific tableName from the database
        ArrayList<Object> records = database.getTable(tableName);

        // Create a two-dimensional String array with all of the records, each record in their own String array inside the main String array
        int i = 0;
        for (int x = 0; x < data.length; x++) { // Loop through the rows
            for (int y = 0; y < data[x].length; y++) { // Loop trough the columns
                data[x][y] = records.get(i).toString(); // Put data from the records-list in the data-array
                i++;
            }
        }

        // Create table with columnNames and all records
        JTable table = new JTable(data, columnNames);

        // Customize the table
        table.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 14)); // Font of the header
        table.setFont(new Font("Verdana", Font.PLAIN, 12)); // Font of the actual data
        table.getTableHeader().setReorderingAllowed(false); // Disable dragging of the columns
        table.setEnabled(false); // Disable the user to edit the data of the table in any way

        // Return the created and customized table
        return table;
    }

    public JTable createTable(ArrayList<String> results, ArrayList<String> columnNames) {
        String[] columns = new String[columnNames.size()];
        columns = columnNames.toArray(columns);

        int rowsAmount = results.size() / columns.length;
        int columnsAmount = columns.length;

        // Create a two-dimensional String array with all of the records, each record in their own String array inside the main String array
        String[][] data = new String[rowsAmount][columnsAmount];
        int i = 0;
        for (int x = 0; x < data.length; x++) { // Loop through the rows
            for (int y = 0; y < data[x].length; y++) { // Loop trough the columns
                data[x][y] = results.get(i);
                i++;
            }
        }

        // Create table with columnNames and all records
        JTable table = new JTable(data, columns);

        // Customize the table
        table.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 14)); // Font of the header
        table.setFont(new Font("Verdana", Font.PLAIN, 12)); // Font of the actual data
        table.getTableHeader().setReorderingAllowed(false); // Disable dragging of the columns
        table.setEnabled(false); // Disable the user to edit the data of the table in any way

        // Return the created and customized table
        return table;
    }

}