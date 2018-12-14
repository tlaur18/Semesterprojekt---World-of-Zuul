/**
 *
 * The YankieBar is a subclass of UseableItem.
 * The player is able to pick the YakieBar up and use it to regain lost hit points.
 *
 * @author Alexander Nguyen, Jacob Wowk, Morten K. Jensen, Thomas S. Laursen
 * @version 2018.12.14
 *
 */
package items;

import worldofzuul.Player;

public class YankieBar extends UseableItem {

    private int itemScore;

    /*
    * The constructor is used to set the regualar Item attributes and the amount of points the Player receives from using this Item.
     */
    public YankieBar(String name, String description, String imgURL, int imgX, int imgY) {
        super(name, description, imgURL, imgX, imgY);
        itemScore = 900;
    }

    /*
    * YankieBar Overrides the abstract method 'use' from UseableItem.
    * The player recieves full health.
    * This UseableItem is removed from the player's inventory when used.
    * Returns the outcome of the this action to be printed to the user.
     */
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
