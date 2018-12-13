package items;

import worldofzuul.Player;

public class Doll extends UseableItem {
    private int itemScore;

    public Doll(String name, String description, String imgURL, int imgX, int imgY) {
        super(name, description, imgURL, imgX, imgY);
        itemScore = 800;
    }

    @Override
    public String use(Player player) {
        String outputText = "";
        
        outputText = "\nYou play with the doll, but quickly get bored.";
        outputText += "\nYou realize that you probably should focus on getting to safety.";
        player.updateHighscore(itemScore);
        
        return outputText;
    }
    public int getItemScore(){
        return itemScore;
    }
}
