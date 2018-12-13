/**
 *
 * @author Alexander Nguyen, Jacob Wowk, Morten K. Jensen and Thomas S. Laursen
 * @version 2018.12.14
 * 
 */

package worldofzuul.domain.item;

import worldofzuul.domain.Player;

public class Doll extends UseableItem {
    private int itemScore;

    public Doll(String name, String description, String imgURL) {
        super(name, description, imgURL);
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
