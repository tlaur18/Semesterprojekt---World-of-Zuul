/**
 *
 * @author Alexander Nguyen, Jacob Wowk, Morten K. Jensen and Thomas S. Laursen
 * @version 2018.12.14
 * 
 */

package worldofzuul.domain.item;

import worldofzuul.domain.Player;

public class YankieBar extends UseableItem {
    private int itemScore;

    public YankieBar(String name, String description, String imgURL) {
        super(name, description, imgURL);
        itemScore = 900;
    }

    @Override
    public String use(Player player) {
        String outputText = "";
        
        outputText = "\n*OM NOM NOM*";
        outputText += "\nYour health has been restored to 100.";
        
        player.removeItem();
        player.setHealth(100);
        player.updateHighscore(itemScore);
        return outputText;
    }
}
