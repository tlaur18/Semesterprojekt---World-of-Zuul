/*
@author Alexander Nguyen, Jacob Wowk, Morten K. Jensen and Thomas S. Laursen
* @version 2018.12.14

Highscore Database for collecting and sorting Highscore objects.
*/
package worldofzuul;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import worldofzuul.dataaccess.DataAccess;

public class HighscoreDatabase {

    private List<Highscore> highscores;
    private DataAccess da;
    /*
     Constructor wich creates the Highscore Database with an arrayList,
     and the Dataaccess Object.
    */
    public HighscoreDatabase() {
        highscores = new ArrayList<>();
        da = new DataAccess("Highscore.txt");
    }
    
    /* 
    Method uses the string from DataAccess and adds them to the ArrayList
    created in the constructor.
    */
    public void loadHighscores() {
        highscores.clear();
        for (String playerString : da.load()) {
            String[] contents = playerString.split(",");
            highscores.add(new Highscore(contents[0], Integer.parseInt(contents[1])));
        }
    }

    /*
    Loads the highscore before returning it.
    */
    public List<Highscore> getHighscores() {
        loadHighscores();
        return highscores;
    }
    
    /*
    Sorts the highscores using the compareTo in Highscore.java
    If statement to avoid ArrayIndexOutOfBounds if the the amount of highscores
    is under 10 players.
    Takes the 10 first highscores in the sorted arrayList and places them in an
    temporary ArrayList
    */
    public void sortHighscores() {
        Collections.sort(highscores);
        int arraySize = highscores.size();
        List<Highscore> temp = new ArrayList<>();
        if (arraySize > 10){
        for (int i = 0; i < 10; i++) {
            temp.add(highscores.get(i));
        }
        }
        if (arraySize <= 10){
            for (Highscore highscores : highscores) {
                temp.add(highscores);
            }
        }
        
        highscores = temp;
    }
    /*
    Loads the highscores ArrayList and add a new highscore player to it.
    Sorts the list to make sure the right score is places at the top.
    Converts the ArrayList to a string, to be able for DataAccess to save it.
    */
    public void saveHighscore(Player player) {
        loadHighscores();
        
        Highscore highscorePlayer = new Highscore(player.getPlayerName(), player.getPlayerScore());
        highscores.add(highscorePlayer);
        
        sortHighscores();
        
        List<String> playerStrings = new ArrayList<>();
        for (Highscore highscores : highscores) {
            playerStrings.add(highscores.toString());
        }
        
        da.save(playerStrings);
    }
    
}