/**
 *
 * BigFireExtinguisher is a big fire extinguisher that is used to extinguish
 * EVEN the biggest fire.
 * 
 * @author Alexander Nguyen, Jacob Wowk, Morten K. Jensen and Thomas S. Laursen
 * @version 2018.12.14
 * 
 */

package worldofzuul.domain.item;

import worldofzuul.domain.Player;

public class BigFireExtinguisher extends UseableItem {

    private boolean isEmpty;
    private int lvl;
    private int itemScore;

    public BigFireExtinguisher(String name, String description, String imgURL, int imgX, int imgY) {
        super(name, description, imgURL, imgX, imgY);
        isEmpty = false;
        lvl = 3;
        itemScore = 700;
    }

    @Override
    public String use(Player player) {
        String outputText;
        if (!isEmpty) {
            if (player.getCurrentRoom().getFire() != null) {
                outputText = "\nYou used the fireextinguisher on the fire.";
                outputText += player.getCurrentRoom().lowerFireLvl(lvl);
                isEmpty = true;
                 player.updateHighscore(itemScore);
            } else {
                isEmpty = true;
                outputText = "\nYou emptied the fireextinguisher in a room with no fire.\nWhat a waste!";
                player.updateHighscore(-600);
            }
        } else {
            outputText = "\nThis Fireextinguisher is empty.";
        }
        return outputText;
    }
    public int getItemScore(){
        return itemScore;
    }
}
