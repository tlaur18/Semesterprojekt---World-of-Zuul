/*
    * This class is a specific item and is a sub-class of UseableItem.
    *
    * @author Alexander Nguyen, Jacob Wowk, Morten K. Jensen and Thomas S. Laursen
    * @version 2018.12.14
    *
 */
package items;

import worldofzuul.Player;

public class Doll extends UseableItem {
    private int itemScore;

    /** The contructor takes the regular Item attributes, its additional attributes
     *  are initialized.
     *  itemScore is the point given to the player's score if used in the right situration
     */
    public Doll(String name, String description, String imgURL, int imgX, int imgY) {
        super(name, description, imgURL, imgX, imgY);
        itemScore = 800;
    }
     /*
        Overrriding the abstract method in UseableItem. 
        Returns the string, when the item is used. 
    */
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
