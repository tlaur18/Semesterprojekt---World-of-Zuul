package items;

import worldofzuul.Player;

public class SmallFireExtinguisher extends UseableItem {

    private boolean isEmpty;
    private int lvl;
    private int itemScore;

    public SmallFireExtinguisher(String name, String description, String imgURL, int imgX, int imgY) {
        super(name, description, imgURL, imgX, imgY);
        isEmpty = false;
        lvl = 2;
        itemScore = 650;
        
    }

    @Override
    public String use(Player player) {
        String outputText = "";

        if (!(isEmpty)) {
            if (player.getCurrentRoom().getFire() != null) {
                outputText = "\nYou used the fireextinguisher on the fire.";
                outputText += player.getCurrentRoom().lowerFireLvl(lvl);
                isEmpty = true;
                player.updateHighscore(itemScore);
            } else {
                player.updateHighscore(-450);
                isEmpty = true;
                outputText = "\nYou emptied the fireextinguisher in a room with no fire...";
                outputText += "\nWhat a waste!";
            }
        } else {
            outputText = "\nThis Fireextinguisher is empty!";
        }
        return outputText;
    }
    
    public int getItemScore(){
        return itemScore;
    }
}
