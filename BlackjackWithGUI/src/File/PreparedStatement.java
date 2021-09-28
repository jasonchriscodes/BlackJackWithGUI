/*
 * Class represents for object can send a SQL statement to DVMS when it is create
 * The statement is pre-compiled in the DBMS
 */
package File;

import Players.HumanPlayer;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Jason Christian - 21136899
 */
public class PreparedStatement {

    DBManager dbManager;

    public PreparedStatement() {
        dbManager = new DBManager();
    }

    public int updateHuman(HumanPlayer human) {
        int rowCount = 0;
        java.sql.PreparedStatement pstmt;
        String sql = "UPDATE PLAYERS SET CHIPS=? WHERE NAME=?";
        try {
            pstmt = dbManager.getConnection().prepareStatement(sql);
            pstmt.setString(2, human.getName());
            pstmt.setDouble(1, human.getTotalGain());
            //How many rows are affected
            int executeUpdate = pstmt.executeUpdate();
            return executeUpdate;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rowCount;
    }

    public static void main(String args[]) {
        RetrieveAll retrieve = new RetrieveAll();
        List<HumanPlayer> humanList = retrieve.getAllHumans();

        //Update the third element of carList, id=3
        HumanPlayer humanToUpdate = humanList.get(1);
        humanToUpdate.setTotalGain(40);

        //Fire update
        PreparedStatement prepared = new PreparedStatement();
        int updateResult = prepared.updateHuman(humanToUpdate);
        System.out.println("Update Result: " + updateResult);
        prepared.dbManager.closeConnections();

        //retrieve update
        HumanPlayer humanUpdated = retrieve.getPlayerByName("'Benny'");
        retrieve.dbManager.closeConnections();
        System.out.println("Update player name: " + humanUpdated.getName() + " total chips to: " + humanUpdated.getTotalGain());
        for (HumanPlayer human : humanList) {
            System.out.println("Name: " + human.getName() + ", Chips: " + human.getTotalGain());
        }
    }
}
