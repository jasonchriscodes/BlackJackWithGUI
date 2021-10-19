/*
 * Class represents for object can send a SQL statement to DVMS when it is create
 * The statement is pre-compiled in the DBMS
 */
package File;

import Model.BlackjackPlayer;
import java.sql.SQLException;

/**
 *
 * @author Jason Christian - 21136899
 */
public class PreparedStatement {

    DBManager dbManager;

    public PreparedStatement() {
        dbManager = new DBManager();
    }

    public int updateHuman(BlackjackPlayer player) {
        int rowCount = 0;
        java.sql.PreparedStatement pstmt;
        String sql = "UPDATE PLAYERS SET CHIPS=? WHERE NAME=?";
        try {
            pstmt = dbManager.getConnection().prepareStatement(sql);
            pstmt.setString(2, player.getName());
            pstmt.setDouble(1, player.getBankroll());
            //How many rows are affected
            int executeUpdate = pstmt.executeUpdate();
            return executeUpdate;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rowCount;
    }

    // Testing Purpose
//    public static void main(String args[]) {
//        RetrieveAll retrieve = new RetrieveAll();
//        List<BlackjackPlayer> humanList = retrieve.getAllHumans();
//
////        Update the third element of carList, id = 3
//        BlackjackPlayer humanToUpdate = humanList.get(0);
//        humanToUpdate.setBankroll(70);
////        Fire update
//        PreparedStatement prepared = new PreparedStatement();
//        int updateResult = prepared.updateHuman(humanToUpdate);
//        System.out.println("Update Result: " + updateResult);
//        prepared.dbManager.closeConnections();
////        retrieve update
//        BlackjackPlayer humanUpdated = retrieve.getPlayerByName("'Jason'");
//        humanUpdated.setBankroll(70);
//        System.out.println(retrieve.getChipsByName("'Susi'"));
//        retrieve.dbManager.closeConnections();
////        System.out.println("Update player name: " + humanUpdated.getName() + " total chips to: " + humanUpdated.getBankroll());
////        for (BlackjackPlayer human : humanList) {
////            System.out.println("Name: " + human.getName() + ", Chips: " + human.getBankroll());
////        }
//    }
}
