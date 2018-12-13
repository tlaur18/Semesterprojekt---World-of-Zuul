/**
 *
 * @author Alexander Nguyen, Jacob Wowk, Morten K. Jensen and Thomas S. Laursen
 * @version 2018.12.14
 * 
 */

package worldofzuul.domain.item;

import worldofzuul.domain.Player;

public class SmallFireExtinguisher extends UseableItem {

    private boolean isEmpty;
    private int lvl;
    private int itemScore;

    public SmallFireExtinguisher(String name, String description, String imgURL) {
        super(name, description, imgURL);
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
