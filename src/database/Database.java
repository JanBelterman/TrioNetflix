package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Database {
    private String connectionUrl;

    public Database(String connectionUrl) {
        this.connectionUrl = connectionUrl;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Return all of the tableNames
    public ArrayList<String> tableNames() {
        // Create ArrayList in which all table names from this database are gonna be stored
        ArrayList<String> tableNames = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(connectionUrl);
            DatabaseMetaData md = connection.getMetaData();

            String[] types = {"TABLE"};
            resultSet = md.getTables(null, null, null, types);

            // Filter out the "table names" containing "trace" and add the table names to the list
            while (resultSet.next()) {
                if (!resultSet.getString(3).contains("trace")) {
                    tableNames.add(resultSet.getString(3));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch (Exception e) {}
            if (statement != null) try { statement.close(); } catch (Exception e) {}
            if (connection != null) try { connection.close(); } catch (Exception e) {}
        }
        // Return ArrayList with all table names
        return tableNames;
    }

    // Return all of the columnNames for a given table
    public ArrayList<String> getcolumnNamesForTable(String tableName) {
        // Create ArrayList in which the column names for this table are gonna be stored
        ArrayList<String> columns = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(connectionUrl);
            statement = connection.createStatement();

            // Create sql query
            String sql = "SELECT *\n";
            sql += "FROM " + tableName;

            resultSet = statement.executeQuery(sql);
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            // Add all of the column names to the ArrayList
            for (int i = 1; i < resultSetMetaData.getColumnCount() + 1; i++) {
                columns.add(resultSetMetaData.getColumnName(i));
            }

            System.out.println(sql + "\n"); // Print the SQL used

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch (Exception e) {}
            if (statement != null) try { statement.close(); } catch (Exception e) {}
            if (connection != null) try { connection.close(); } catch (Exception e) {}
        }
        // Return ArrayList with column names
        return columns;
    }

    // Method that returns a list with the primary keys of a table
    public ArrayList<String> getPrimaryKeysColumnNamesForTable(String tableName) {
        // Create an ArrayList in which all primary key's column names are gonna be stored
        ArrayList<String> primaryKeys = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(connectionUrl);
            statement = connection.createStatement();

            DatabaseMetaData metaData = connection.getMetaData();
            resultSet = metaData.getPrimaryKeys(null, null, tableName);
            // Add all of the primary key's column names to the ArrayList
            while (resultSet.next()) {
                primaryKeys.add(resultSet.getString("COLUMN_NAME"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch (Exception e) {}
            if (statement != null) try { statement.close(); } catch (Exception e) {}
            if (connection != null) try { connection.close(); } catch (Exception e) {}
        }
        // Return an ArrayList with all of the table's primary key's column names
        return primaryKeys;
    }

     // Return an entire table
    public ArrayList<Object> getTable(String tableName) {
        // Create an ArrayList in which all records from the parameter table are gonna be stored
        ArrayList<Object> toReturn = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(connectionUrl);
            statement = connection.createStatement();
            // Create the sql statement
            String sql = "SELECT *\n";
            sql += "FROM " + tableName;
            resultSet = statement.executeQuery(sql);
            // Add all of the record to the ArrayList
            while (resultSet.next()) {
                for (String columnName : getcolumnNamesForTable(tableName)) {
                    if (resultSet.getObject(columnName) == null) {
                        toReturn.add("");
                    } else {
                        toReturn.add(resultSet.getObject(columnName));
                    }
                }
            }
            // Print the SQL used
            System.out.println(sql + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch (Exception e) {}
            if (statement != null) try { statement.close(); } catch (Exception e) {}
            if (connection != null) try { connection.close(); } catch (Exception e) {}
        }
        // Return an ArrayList with all the table's records
        return toReturn;
    }

    // Method that returns an ArrayList with all of the unique records, with composite keys in format key:key:key etc..
    public ArrayList<Object> getUniqueRecordsOfTable(String tableName) {
        // Create an ArrayList in which all of the tables unique records are gonna be stored in format key:key:key etc..
        ArrayList<Object> toReturn = new ArrayList<>();
        // Get the primary key's column names
        ArrayList<String> primaryKeyColumnNames = getPrimaryKeysColumnNamesForTable(tableName);

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(connectionUrl);
            statement = connection.createStatement();
            // Create the query
            String sql = "SELECT *\n";
            sql += "FROM " + tableName;
            // Execute the query
            resultSet = statement.executeQuery(sql);

            // Add all of the unique record to the ArrayList
            while (resultSet.next()) {
                int i = 1; // Create counter variable
                String uniqueRecord = ""; // Create the string for every unique record | format key:key:key etc..
                for (String primaryKeyColumnName : primaryKeyColumnNames) { // Loop trough the primary key column names
                    uniqueRecord += resultSet.getObject(primaryKeyColumnName); // Add the value for current primary key column name
                    if (!(i >= primaryKeyColumnNames.size())) { // If this primaryKey is not the last one than add ":"
                        uniqueRecord += ":"; // Add ":" | is used later on to split the unique record into parts | from key:key:key to a string array
                    }
                    i++; // +1 to counter variable
                }
                toReturn.add(uniqueRecord); // Add an unique record to the ArrayList | format key:key:key etc..
            }
            // Print the SQL used
            System.out.println(sql + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch (Exception e) {}
            if (statement != null) try { statement.close(); } catch (Exception e) {}
            if (connection != null) try { connection.close(); } catch (Exception e) {}
        }
        // Return the ArrayList with all of the tables unique records in format key:key:key etc..
        return toReturn;
    }

    // Retrieve an unique record from the parameter table, matching the parameter keys
    public ArrayList<Object> getRecord(String tableName, String keys) {
        // Create an ArrayList in which a record is gonna be stored, matching the unique primary key's values
        ArrayList<Object> recordValues = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(connectionUrl);
            statement = connection.createStatement();

            // The string key looks like this: key:key:key etc.. | split the keys into an string array
            String[] primaryKeyValues = keys.split(":");

            // Create the sql query
            String sql = "SELECT *\n";
            sql += "FROM " + tableName + "\n";
            sql += createWhereStatementPart(tableName, primaryKeyValues);
            // Execute the query
            resultSet = statement.executeQuery(sql);
            // Adding the record's values to the ArrayList
            while (resultSet.next()) {
                for (String columnName : getcolumnNamesForTable(tableName)) {
                    if (resultSet.getObject(columnName) == null) {
                        recordValues.add(""); // If a column is empty OR NULL, than add an empty string
                        continue; // Continue to next loop phase
                    }
                    recordValues.add(resultSet.getObject(columnName).toString()); // Add a value
                }
            }
            // Print the SQL used
            System.out.println(sql + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch (Exception e) {}
            if (statement != null) try { statement.close(); } catch (Exception e) {}
            if (connection != null) try { connection.close(); } catch (Exception e) {}
        }
        // Return the ArrayList with all values for one record, matching the parameter primary key's values
        return recordValues;
    }

    // Creates an update SQL statement
    public void updateRecord(String tableName, ArrayList<Object> newValues, String originalKey) {
        // The string key looks like this: key:key:key etc.. | split the keys into an string array
        String[] primaryKeyValues = originalKey.split(":");
        // Create statement
        String sql = "UPDATE\n";
        sql += "SET";
        int i = 0; // Create counter variable
        for (String columnName : getcolumnNamesForTable(tableName)) { // Loop trough the tables columns
            sql += columnName + " = '" + newValues.get(i).toString() + "'"; // set new value to current column name
            if (i != getcolumnNamesForTable(tableName).size() - 1) {
                sql += ",\n"; // If the current column is not the last, than add a , and an enter
            } else {
                sql += "\n"; // Else only add an enter
            }
            i++; // 1+ the counter variable
        }
        sql += createWhereStatementPart(tableName, primaryKeyValues);
        // Execute the created statement, with helper method
        executeStatement(sql);
    }

    // Creates a delete SQL statement
    public void deleteRecord(String tableName, String originalKey) {
        // The string key looks like this: key:key:key etc.. | split the keys into an string array
        String[] primaryKeyValues = originalKey.split(":");
        // Create the sql statement
        String sql = "DELETE\n";
        sql += "FROM " + tableName + "\n";
        sql += createWhereStatementPart(tableName, primaryKeyValues);
        // Execute statement with helper method
        executeStatement(sql);
    }

    // Method that creates the where part of the statement
    private String createWhereStatementPart(String tableName, String[] primaryKeyValues) {
        // Create the where part of the statement for given table name and primary key values
        int i = 0; // Create counter variable
        String whereStatementPart = "WHERE ";
        for (String primaryKeyValue : primaryKeyValues) {
            whereStatementPart += getPrimaryKeysColumnNamesForTable(tableName).get(i) + " = '" + primaryKeyValue + "'\n";
            if (!(i >= primaryKeyValues.length - 1)) {
                whereStatementPart += "AND ";
            }
            i++;
        }
        return whereStatementPart;
    }

    // Creates an insert SQL statement
    public void insertRecord(ArrayList<Object> values, String tableName) {
        // Create the sql statement
        String sql = "INSERT INTO " + tableName + "\n";
            sql += "VALUES (";
            int i = 0;
            for (Object o : values) {
                if (o.toString() == null || o.toString().length() == 0 || o.toString().equals("")) { // Check if a textField was empty and if so insert null, otherwise int's will get value 0
                    sql += "null";
                } else {
                    sql += "'" + o.toString() + "'";
                }
                if (i < values.size() - 1) {
                    sql += ", ";
                } else {
                    sql += ");";
                }
                i++;
            }
            // Execute statement with helper method
            executeStatement(sql);
    }

    // Executes the SQL statement | only suitable for update and insert statements
    private void executeStatement(String sql) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(connectionUrl);
            statement = connection.createStatement();

            System.out.println(sql + "\n"); // Print the SQL used
            statement.executeUpdate(sql); // Execute the statement
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch (Exception e) {}
            if (statement != null) try { statement.close(); } catch (Exception e) {}
            if (connection != null) try { connection.close(); } catch (Exception e) {}
        }
    }

    // Execute query given and return results in format ArrayList<HashMap<String(columnName), Object(value)>>
    public ArrayList<HashMap<String, Object>> getResultsOfQuery(String sql) {
        // Create the ArrayList<HashMap<ColumnName, value>> structure, which is gonna copy a resultSet
        ArrayList<HashMap<String, Object>> resultList = new ArrayList<>();
        HashMap<String, Object> row = null;

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(connectionUrl);
            statement = connection.createStatement();
            // Execute the sql statement gotten from the method parameter
            resultSet = statement.executeQuery(sql);

            ResultSetMetaData metaData = resultSet.getMetaData(); // To retrieve column names
            Integer columnCount = metaData.getColumnCount(); // Amount of columns
            // Add all of the resultSet's values and column names to the ArrayList<HashMap<ColumnName, value>> structure
            while (resultSet.next()) {
                row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.put(metaData.getColumnName(i), resultSet.getObject(i));
                }
                resultList.add(row);
            }
            // Print the sql code used
            System.out.println(sql + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try { resultSet.close(); } catch (Exception e) {}
            if (statement != null) try { statement.close(); } catch (Exception e) {}
            if (connection != null) try { connection.close(); } catch (Exception e) {}
        }
        // Return the ArrayList<HashMap<ColumnName, value>> structure | actually returns the resultSet
        return resultList;
    }

}