/*
 * This class is a specific Item and is a sub-class of UseableItem.
 * 
 * @author Alexander Nguyen, Jacob Wowk, Morten K. Jensen and Thomas S. Laursen
 * @version 2018.12.14
 * 
 */
package items;

import worldofzuul.Player;


public class BigFireExtinguisher extends UseableItem {

    private boolean isEmpty;
    private int lvl;
    private int itemScore;
    /* 
        The contructor takes the regular Item attributes, its additional attributes
        are initialized.
        isEmpty is set to false. It is used to determine if the FireExtinguisher
        can be used by the player.
        lvl is set to 3 so it can handle the bigger fire in the kitchen.
        itemScore is the point given to your score if used in the right situration
    
    */
    public BigFireExtinguisher(String name, String description, String imgURL, int imgX, int imgY) {
        super(name, description, imgURL, imgX, imgY);
        isEmpty = false;
        lvl = 3;
        itemScore = 700;
    }
    /*
        Overrriding the abstract method in UseableItem. 
        Returns the string describing the outcome of the if statements. 
    */
    @Override
    public String use(Player player) {
        String outputText;
        /*
            If the BigFireExtinguisher's attribute isEmpty is false, it calls for another if statement that 
            checks if the player is inside a room containing fire. 
            If there is, it will put the out fire and set isEmpty to true.
            If the player is not in a room with fire, isEmpty is set to true 
            and the player will get negative itemScore for wasting it.
            
        */
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
