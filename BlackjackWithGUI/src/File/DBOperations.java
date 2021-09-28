/*
 * Class represents data base operations
 */
package File;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
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

    DBManager dbManager;
    private final Connection conn;
    private Statement statement;
    private String newTableName = "PLAYERS";

    public DBOperations() {
        dbManager = new DBManager();
        conn = dbManager.getConnection();
    }

    // REMEMBER: after check the table by connecting to PlayersDB, always 
    // disconnect data base first if you want to execute netbeans command or 
    // to change data in the table because embeeded data base only have one 
    // connection at the time
    public void createTable() {
        try {
            Statement statement = dbManager.getConnection().createStatement();
            // DO NOT use "USER" name as table because it is a built-in function in Derby. 
            // You'd have to specify a different table name for the JPA entity 
            // (usually done via the @Table annotation).

            // check if table is exist
            this.checkExistedTable(newTableName);

            String sqlCreate = "create table " + newTableName + " (Name varchar(20) not null, "
                    + "Chips Double, PRIMARY KEY (NAME))";

            statement.executeUpdate(sqlCreate);

            //statement.close();
//            System.out.println("Table created");
        } catch (SQLException ex) {
            Logger.getLogger(DBOperations.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteTable() throws SQLException {
        statement = dbManager.getConnection().createStatement();
        String sqlDelete = "Drop table " + newTableName;
        try {
            statement.executeUpdate(sqlDelete);
        } catch (SQLException ex) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addData(String name, double chips) {
        try {
            statement = dbManager.getConnection().createStatement();
            String sqlInsert = "insert into " + newTableName + "(name, chips) " + " values("
                    + "'" + name + "', " + chips + ")";
            statement.executeUpdate(sqlInsert);
//            System.out.println("Data has been saved");
        } catch (SQLException ex) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
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
            String sqlQuery = "select chips from players "
                    + "where name ='" + nm + "'";

            rs = statement.executeQuery(sqlQuery);
            rs.beforeFirst();
            while (rs.next()) {
                Double chips = rs.getDouble(1);
                System.out.println(nm + ":  " + chips + " chips");
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateTable() {
        try {
            Statement statement = dbManager.getConnection().createStatement();
            String newTableName = "PDC.PLAYERS";

            String sqlUpdateTable = "update " + newTableName + " set chips=20 "
                    + "where name='Jason'";
            statement.executeUpdate(sqlUpdateTable);

            //statement.close();
            System.out.println("Table updated");

        } catch (SQLException ex) {
            Logger.getLogger(DBOperations.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void checkExistedTable(String name) {
        try {
            DatabaseMetaData dbmd = this.conn.getMetaData();
            String[] types = {"TABLE"};
            statement = this.conn.createStatement();
            ResultSet rs = dbmd.getTables(null, null, null, types);

            while (rs.next()) {
                String table_name = rs.getString("TABLE_NAME");
//                System.out.println(table_name);
                if (table_name.equalsIgnoreCase(name)) {
                    statement.executeUpdate("Drop table " + name);
//                    System.out.println("Table " + name + " has been deleted.");
                    break;
                }
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Checks if database is empty.
     *
     * @return true, if database is empty
     */
    public boolean isDatabaseEmpty() {
        try {
            Statement statement = dbManager.getConnection().createStatement();
            String sqlCheckEmpty = "SELECT COUNT(*) FROM PLAYERS";

            ResultSet numOfRows = statement.executeQuery(sqlCheckEmpty);
            while (numOfRows.next()) {
                if (numOfRows.getInt(1) == 0) {
                    return true;
                }
            }
        } catch (SQLException sqle) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        DBOperations dboperations = new DBOperations();
        dboperations.createTable();
        dboperations.addData("Jason", 50);
        dboperations.getQuery();
        dboperations.updateTable();
        dboperations.getQuery();
        dboperations.dbManager.closeConnections();
    }
}
