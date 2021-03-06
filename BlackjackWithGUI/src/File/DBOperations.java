/*
 * Class represents data base operations
 */
package File;

import View.Message;
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
    private String stringTable = "MESSAGE";

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

    public void createMessageTable() {
        try {
            Statement statement = dbManager.getConnection().createStatement();
            // DO NOT use "USER" name as table because it is a built-in function in Derby. 
            // You'd have to specify a different table name for the JPA entity 
            // (usually done via the @Table annotation).

            // check if table is exist
            this.checkExistedTable(stringTable);

            String sqlCreateMessage = "create table " + stringTable + " (Title varchar(20) not null, "
                    + "Message varchar(100), PRIMARY KEY (TITLE))";

            statement.executeUpdate(sqlCreateMessage);

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
                    + "'" + name + "', " + chips + ")";// remember use 'NAME'
            statement.executeUpdate(sqlInsert);
//            System.out.println("Data has been saved");
        } catch (SQLException ex) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addMessage(String title, String message) {
        try {
            statement = dbManager.getConnection().createStatement();
            String sqlInsert = "insert into " + stringTable + "(Title, Message) " + " values("
                    + "'" + title + "', '" + message + "')";// remember use 'NAME'
            statement.executeUpdate(sqlInsert);
//            System.out.println("Data has been saved");
        } catch (SQLException ex) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean addUser(String name) {
        try {
            statement = dbManager.getConnection().createStatement();
            String sqlInsert = "insert into " + newTableName + "(name, chips) " + " values("
                    + "'" + name + "', 100)";
            statement.executeUpdate(sqlInsert);
//            System.out.println("Data has been saved");
        } catch (SQLException ex) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public void updateTable(String name, Double Bankroll) {
        try {
            Statement statement = dbManager.getConnection().createStatement();
            String newTableName = "PDC.PLAYERS";

            String sqlUpdateTable = "update " + newTableName + " set chips=" + Bankroll
                    + " where name=" + name;
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

    /**
     * Check username.
     *
     * @param name the name
     * @return true, if the username is illegal
     */
    public static boolean checkName(String name) {
        for (char aChar : name.toCharArray()) {
            if (!((aChar >= 'a' && aChar <= 'z') || (aChar >= 'A' && aChar <= 'Z'))) {
                System.out.println(aChar);
                return false;
            }
        }
        return true;
    }

    /**
     * Checks for user in database.
     *
     * @param name the name
     * @return true, if the user exists in database
     */
    public boolean hasUser(String name) {
        try {
            Statement statement = dbManager.getConnection().createStatement();
            String sqlCheckUser = "SELECT NAME FROM PLAYERS";

            ResultSet nameSet = statement.executeQuery(sqlCheckUser);

            while (nameSet.next()) {
                String s = nameSet.getString("NAME");
                if (s.toLowerCase().equals(name.toLowerCase())) {
                    return true;
                }
                System.out.println(s);
                System.out.println(name);
            }
        } catch (SQLException sqle) {
            return true;
        }
        return false;
    }

    /**
     * Checks for message in database.
     *
     * @param title
     * @return true, if the message exists in database
     */
    public boolean hasMessage(String title) {
        try {
            Statement statement = dbManager.getConnection().createStatement();
            String sqlCheckMessage = "SELECT TITLE FROM MESSAGE";

            ResultSet nameSet = statement.executeQuery(sqlCheckMessage);

            while (nameSet.next()) {
                String s = nameSet.getString("TITLE");
                if (s.toLowerCase().equals(title.toLowerCase())) {
                    return true;
                }
                System.out.println(s);
                System.out.println(title);
            }
        } catch (SQLException sqle) {
            return true;
        }
        return false;
    }

    public void deleteUser(String name) throws SQLException {
        try {
            statement = dbManager.getConnection().createStatement();
            String sqlDeleteUser = "DELETE FROM " + newTableName + " WHERE NAME =" + name;

            statement.executeUpdate(sqlDeleteUser);
        } catch (SQLException ex) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Testing Purpose
//    public static void main(String[] args) {
//        DBOperations dboperations = new DBOperations();
////        try {
////            dboperations.createTable();
//        dboperations.createMessageTable();
//        dboperations.addMessage("Rule", Message.opening());
//        dboperations.addData("Jason", 50);
//        dboperations.getQuery();
//            dboperations.deleteUser("'Jason'"); // remember 'NAME'
//        dboperations.updateTable("'Jason'", 80.0);
//        dboperations.getQuery();
//        if (dboperations.hasUser("Jason")) {
//            System.out.println("User already exist");
//        }
//        System.out.println("User NOT exist");
//        dboperations.dbManager.closeConnections();
//        } catch (SQLException ex) {
//            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
