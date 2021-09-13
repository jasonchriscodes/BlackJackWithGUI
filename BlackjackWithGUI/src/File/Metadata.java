/*
 * Class represents a description about database data
 */
package File;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 *
 * @author Jason Christian - 21136899
 */
public class Metadata {

    public static void main(String args[]) {
        DBManager dbManager = new DBManager();
        Connection conn = dbManager.getConnection();
        ResultSet rs = dbManager.myQuery("Select * from players");
        DatabaseMetaData dbmd;
        try {

            //Database Metadata
            dbmd = conn.getMetaData();
            System.out.println("Database product name : " + dbmd.getDatabaseProductName());
            System.out.println("Database version : " + dbmd.getDatabaseProductVersion());
            System.out.println("URL: " + dbmd.getURL());
            System.out.println("driver name: " + dbmd.getDriverName());
            System.out.println("driver version: " + dbmd.getDriverVersion());

            //ResultSet Metadata
            ResultSetMetaData rsmd = rs.getMetaData();
            System.out.println("Number of Columns:" + rsmd.getColumnCount());
            System.out.println("Table Name (column 2):" + rsmd.getTableName(2));
            System.out.println("Data Type (column 2):" + rsmd.getColumnType(2));

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
