package worldofzuul;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import worldofzuul.dataaccess.DataAccess;

public class HighscoreDatabase {

    private List<Highscore> highscores;
    private DataAccess da;

    public HighscoreDatabase() {
        highscores = new ArrayList<>();
        da = new DataAccess("Highscore.txt");
    }

    public void loadHighscores() {
        highscores.clear();
        for (String playerString : da.load()) {
            String[] contents = playerString.split(",");
            highscores.add(new Highscore(contents[0], Integer.parseInt(contents[1])));
        }
    }

    public List<Highscore> getHighscores() {
        loadHighscores();
        return highscores;
    }

    public void sortHighscores() {
        Collections.sort(highscores);
        
        List<Highscore> temp = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            temp.add(highscores.get(i));
        }
        
        highscores = temp;
    }

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