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

    public List<HumanPlayer> getAllCars() {
        List<HumanPlayer> humanList = new ArrayList<>();
        ResultSet rs = dbManager.myQuery("select * from Players");
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double gain = rs.getInt("chip");

                HumanPlayer human = new HumanPlayer(name, gain);
                humanList.add(human);
            }

        } catch (SQLException ex) {
            Logger.getLogger(RetrieveAll.class.getName()).log(Level.SEVERE, null, ex);
        }

        return humanList;
    }

    public HumanPlayer getPlayerByName(String name) {
        HumanPlayer car = new HumanPlayer(name);
        ResultSet rs = dbManager.myQuery("select * from Car where id=" + name);

        if (rs == null) {
            return null;
        }

        try {
            while (rs.next()) {
                car.setTotalGain(rs.getDouble("chip"));
            }

        } catch (SQLException ex) {
            return null;
        }

        return car;
    }

    public static void main(String args[]) {
        RetrieveAll retrieve = new RetrieveAll();
        List<HumanPlayer> humanList = retrieve.getAllCars();
        for (HumanPlayer human : humanList) {
            System.out.println("Name: " + human.getName() + ", Chips: " + human.getTotalGain());
        }
        retrieve.dbManager.closeConnections();
    }
}
