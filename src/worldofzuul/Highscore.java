/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul;

import java.util.ArrayList;
import java.util.List;
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
        highscoreList = new ArrayList<>();

    }

    public Highscore(String name, int highscore) {
        this.highscore = highscore;
        this.name = name;
    }

   

    public ArrayList<Highscore> getHighscore() {
        return highscoreList;
    }

    public ArrayList<Highscore> loadHighscore() {
        for (String playerString : da.load()) {
            String[] contents = playerString.split(" ");
            String fillName = contents[0];
            this.name = contents[1];
            String fillName1 = contents[2];
            String fillname2 = contents[3];
            this.highscore = Integer.parseInt(contents[4]);

            highscorePlayer = new Highscore(name, highscore);
            this.highscoreList.add(highscorePlayer);
        }
        return highscoreList;
    }

    public DataAccess getDataAccess() {
        return da;
    }

    public void saveHighscore(Player player) {
        loadHighscore();
        this.highscorePlayer = new Highscore(player.getPlayerName(), player.getPlayerScore());
        this.highscoreList.add(this.highscorePlayer);
        List<String> playerStrings = new ArrayList<>();
        for (Highscore highscores : highscoreList) {
            playerStrings.add(highscores.toString());
        }
        da.save(playerStrings);
    }
    public void calculateHighscore(Player player){
        highscore =  player.getStepCount() * 1234;
        player.setPlayerScore(highscore);
    }
    @Override
    public String toString(){
        String string = "";
        string += "Name: " + this.name + " <->";
        string += " Score: " + this.highscore;
        return string;
    }
}
