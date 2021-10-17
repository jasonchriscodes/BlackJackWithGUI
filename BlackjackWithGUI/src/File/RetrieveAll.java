/*
 * Class represents a converter the result set to an object / object list.
 * Sometimes, methods require an object reference, such as car, you may
 * not able to pass the result set as the argument for this method, therefore,
 * we normally "convert" the result set to an object / object list
 */
package File;

import Model.BlackjackPlayer;
import Model.Player;
//import Model.Player;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jason Christian - 21136899
 */
public class RetrieveAll {

    DBManager dbManager;

    public RetrieveAll() {
        dbManager = new DBManager();
    }

    public List<BlackjackPlayer> getAllHumans() {
        List<BlackjackPlayer> humanList = new ArrayList<>();
        ResultSet rs = dbManager.myQuery("select * from Players");
        try {
            while (rs.next()) {
                String name = rs.getString("name");
                double gain = rs.getDouble("chips");

                BlackjackPlayer human = new BlackjackPlayer(name, gain);
                humanList.add(human);
            }

        } catch (SQLException ex) {
            Logger.getLogger(RetrieveAll.class.getName()).log(Level.SEVERE, null, ex);
        }

        return humanList;
    }

    public BlackjackPlayer getPlayerByName(String name) {
        BlackjackPlayer human = new BlackjackPlayer(name);
        ResultSet rs = dbManager.myQuery("select * from Players where name=" + name);

        if (rs == null) {
            return null;
        }

        try {
            while (rs.next()) {
                human.setBankroll(rs.getDouble("chips"));
            }

        } catch (SQLException ex) {
            return null;
        }

        return human;
    }

    public Double getChipsByName(String name) {
        BlackjackPlayer human = new BlackjackPlayer(name);
        ResultSet rs = dbManager.myQuery("select * from Players where name=" + name);

        if (rs == null) {
            return null;
        }

        try {
            while (rs.next()) {
                human.setBankroll(rs.getDouble("chips"));
            }

        } catch (SQLException ex) {
            return null;
        }

        return human.getBankroll();
    }

    public void deletePlayerByName(String name) {
        BlackjackPlayer human = new BlackjackPlayer(name);
        ResultSet rs = dbManager.myQuery("delete from Players where name=" + name);
    }

    public static void main(String args[]) {
        RetrieveAll retrieve = new RetrieveAll();
        List<BlackjackPlayer> humanList = retrieve.getAllHumans();
        for (BlackjackPlayer human : humanList) {
            System.out.println("Name: " + human.getName() + ", Chips: " + human.getBankroll());
        }
        retrieve.dbManager.closeConnections();
    }
}
