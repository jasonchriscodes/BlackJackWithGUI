/*
 * Class represents data (name and bet of each player) saving into text file
 */
package File;

import Players.HumanPlayer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Jason Christian - 21136899
 */
public class FileManagement {

    private final HashMap<String, HumanPlayer> humans;
    private String file;

    public FileManagement() throws IOException {
        DBOperations dboperations = new DBOperations();
        dboperations.createTable();
        file = "./resources/User.txt";
        humans = new HashMap();
        getUsers(file);
    }

    public void getUsers(String fn) {
        try {
            FileReader fr = new FileReader(fn);
            BufferedReader bufferedReader = new BufferedReader(fr);
            Scanner fileScanner = new Scanner(bufferedReader);

            // scan file line by line and separate each name and score
            while (fileScanner.hasNextLine()) {
                String fileContent = fileScanner.nextLine();
                String[] tempData = fileContent.split(" ");
                String newName = tempData[0];
                String newGain = tempData[1];
                HumanPlayer human = new HumanPlayer(newName, Double.parseDouble(newGain));
                this.humans.put(human.getName(), human);
            }
            fr.close();
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
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
        try {
            // overwrite the file
            FileWriter fileReader = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileReader);
            for (HumanPlayer u : this.humans.values()) {
                bufferedWriter.write(u.getName() + " " + u.getTotalGain() + "\n");
            }
            bufferedWriter.close();
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Create new empty file
     */
    public void createNewFile() {
        File tempFile = new File("./resources/User.txt");
        boolean exists = tempFile.exists();
        if (exists == true) {
            tempFile.delete();
            humans.clear();
        }
        try {
            tempFile.createNewFile();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
