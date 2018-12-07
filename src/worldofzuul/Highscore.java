/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import worldofzuul.dataaccess.DataAccess;
import worldofzuul.Player;

/**
 *
 * @author Morten K. Jensen
 */
public class Highscore {

    private String name;
    private int highscore;
    private DataAccess da;
    private Highscore highscorePlayer;
    private ArrayList<Highscore> highscoreList;

    public Highscore() {
        da = new DataAccess("Highscore.txt");

    }

    public Highscore(String name, int highscore) {
        this.highscore = highscore;
        this.name = name;
    }

    public void saveHighscore(Player player) {
//        highscoreList = FXCollections.observableArrayList();
        List<String> playerStrings = new ArrayList<>();
        playerStrings.add(player.getPlayerName());
        da.save(playerStrings);
    }

    public ArrayList<Highscore> getHighscore() {
        return highscoreList;
    }

    public ArrayList<Highscore> loadHighscore() {
        for (String playerString : da.load()) {
            String[] contents = playerString.split(" ");
            String name = contents[0];
            int highscore = Integer.parseInt(contents[1]);

            highscorePlayer = new Highscore(name, highscore);
            highscoreList.add(highscorePlayer);
        }
        return highscoreList;
    }
    public DataAccess getDataAccess(){
       return da;
    }
}
