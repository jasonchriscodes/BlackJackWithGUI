/*
 * Class represents data (name and bet of each player) saving into text file
 */
package File;

import Players.HumanPlayer;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Jason Christian - 21136899
 */
public class FileManagement {

    private final HashMap<String, HumanPlayer> humans;
    private RetrieveAll retrieve = new RetrieveAll();
    private List<HumanPlayer> humanList;
    private DBOperations dboperations = new DBOperations();

    public FileManagement() throws IOException {
        humans = new HashMap();
        getUsers();
    }

    public void getUsers() {
        humanList = retrieve.getAllHumans();
        for (HumanPlayer human : humanList) {
            this.humans.put(human.getName(), human);
//            System.out.println("Name: " + human.getName() + ", Chips: " + human.getTotalGain());
        }
        retrieve.dbManager.closeConnections();
    }

    public void savedPlayer() {
        for (HumanPlayer u : this.humans.values()) {
            System.out.println("- " + u.getName() + " with " + u.getTotalGain() + " chips.");
        }
        System.out.println("\n");
    }

    /**
     * checking whether the participant has been registered
     *
     * @param humanTemp
     * @return
     */
    public HumanPlayer checkUser(String humanTemp) {
        HumanPlayer human;
        if (humans.containsKey(humanTemp)) {
            human = humans.get(humanTemp);
            System.out.println("Your current chips: " + human.getTotalGain() + " chips");
        } else {
            System.out.println("You do not have saved data named: " + humanTemp + " and you start with: 100 chips");
            human = new HumanPlayer(humanTemp, 100);
            humans.put(humanTemp, human);
        }
        return human;
    }

    /**
     * Update score
     *
     * @param human
     * @throws IOException
     */
    public void updateScore(HumanPlayer human) throws IOException {
        this.humans.put(human.getName(), human);
        createNewFile();
        for (HumanPlayer u : this.humans.values()) {
            dboperations.addData(u.getName(), u.getTotalGain());
        }
        System.out.println("Data that has been saved: \n");
        savedPlayer();
    }

    /**
     * Create new empty file
     */
    public void createNewFile() {
        DBOperations dboperations = new DBOperations();
        dboperations.createTable();
        humanList.clear();
    }

    /**
     * Add human to HashMap
     */
    public void addHumanToMap(HumanPlayer human) {
        humans.clear();
        this.humans.put(human.getName(), human);
    }
}
