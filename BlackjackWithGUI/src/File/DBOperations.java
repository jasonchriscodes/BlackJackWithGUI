/*
 * Class represents data base operations
 */
package File;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jason Christian - 21136899
 */
public class DBOperations {

    private DBManager dbManager;
    private boolean tableExistsSQL;

    public DBOperations() {
        dbManager = new DBManager();
    }

    public void createTable() {
        try {
            Statement statement = dbManager.getConnection().createStatement();
            String newTableName = "PLAYERS";
            // DO NOT use "USER" name as table because it is a built-in function in Derby. 
            // You'd have to specify a different table name for the JPA entity 
            // (usually done via the @Table annotation).

            if (isTableExistsSQL() == false) {
                // delete table
                // must be used in order to create a new table
                statement.executeUpdate("drop table " + newTableName);
            }
            String sqlCreate = "create table " + newTableName + " (ID int not null,"
                    + "Name varchar(20), "
                    + "Chip int, PRIMARY KEY (ID))";

            statement.executeUpdate(sqlCreate);

            String sqlInsert = "insert into " + newTableName + " values("
                    + "1, 'Jason', 100),"
                    + "("
                    + "2, 'Benny', 90),"
                    + "("
                    + "3, 'Susi', 60)";

            statement.executeUpdate(sqlInsert);

            //statement.close();
            System.out.println("Table created");
        } catch (SQLException ex) {
            Logger.getLogger(DBOperations.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getQuery() {
        ResultSet rs = null;

        try {

            System.out.println("Please wait.... getting query....");
            Statement statement = dbManager.getConnection().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            String nm = "Jason";
            String sqlQuery = "select chip from players "
                    + "where name ='" + nm + "'";

            rs = statement.executeQuery(sqlQuery);
            rs.beforeFirst();
            while (rs.next()) {
                int chip = rs.getInt(1);
                System.out.println(nm + ":  " + chip + " chips");
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBOperations.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateTable() {
        try {
            Statement statement = dbManager.getConnection().createStatement();
            String newTableName = "PLAYERS";

            String sqlUpdateTable = "update " + newTableName + " set chip=20 "
                    + "where name='Jason'";
            statement.executeUpdate(sqlUpdateTable);

            //statement.close();
            System.out.println("Table updated");

        } catch (SQLException ex) {
            Logger.getLogger(DBOperations.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isTableExistsSQL() {
        return tableExistsSQL;
    }

    public boolean tableExistsSQL(Connection connection, String tableName) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT count(*) "
                + "FROM information_schema.tables "
                + "WHERE table_name = ?"
                + "LIMIT 1;");
        preparedStatement.setString(1, tableName);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt(1) != 0;
    }

    public static void main(String[] args) {
        DBOperations dboperations = new DBOperations();
        dboperations.createTable();
        dboperations.getQuery();
        dboperations.updateTable();
        dboperations.getQuery();
        dboperations.dbManager.closeConnections();
    }
}
