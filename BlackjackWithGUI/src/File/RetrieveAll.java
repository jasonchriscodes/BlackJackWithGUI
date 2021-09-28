/*
 * Class represents a converter the result set to an object / object list.
 * Sometimes, methods require an object reference, such as car, you may
 * not able to pass the result set as the argument for this method, therefore,
 * we normally "convert" the result set to an object / object list
 */
package File;

import Players.HumanPlayer;
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

    public List<HumanPlayer> getAllHumans() {
        List<HumanPlayer> humanList = new ArrayList<>();
        ResultSet rs = dbManager.myQuery("select * from Players");
        try {
            while (rs.next()) {
                String name = rs.getString("name");
                double gain = rs.getDouble("chips");

                HumanPlayer human = new HumanPlayer(name, gain);
                humanList.add(human);
            }

        } catch (SQLException ex) {
            Logger.getLogger(RetrieveAll.class.getName()).log(Level.SEVERE, null, ex);
        }

        return humanList;
    }

    public HumanPlayer getPlayerByName(String name) {
        HumanPlayer human = new HumanPlayer(name);
        ResultSet rs = dbManager.myQuery("select * from Players where name=" + name);

        if (rs == null) {
            return null;
        }

        try {
            while (rs.next()) {
                human.setTotalGain(rs.getDouble("chips"));
            }

        } catch (SQLException ex) {
            return null;
        }

        return human;
    }

    public static void main(String args[]) {
        RetrieveAll retrieve = new RetrieveAll();
        List<HumanPlayer> humanList = retrieve.getAllHumans();
        for (HumanPlayer human : humanList) {
            System.out.println("Name: " + human.getName() + ", Chips: " + human.getTotalGain());
        }
        retrieve.dbManager.closeConnections();
    }
}
