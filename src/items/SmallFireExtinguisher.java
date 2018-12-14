/**
  * This class is a specific Item and is a sub-class of UseableItem.
  *
  * @author Alexander Nguyen, Jacob Wowk, Morten K. Jensen and Thomas S. Laursen
  * @version 2018.12.14
  *
  */ 

package items;

import worldofzuul.Player;

public class SmallFireExtinguisher extends UseableItem {

    private boolean isEmpty;
    private int lvl;
    private int itemScore;
    /* 
     *   The contructor takes the regular Item attributes, its additional attributes
     *   are initialized.
     *   isEmpty is set to false. It is used to determine if the FireExtinguisher
     *   can be used by the player.
     *   lvl is set to 2 so it can not handle the biggest fires.
     *   itemScore is the point given to the player's score if used in the right situration
     *
     */
    public SmallFireExtinguisher(String name, String description, String imgURL, int imgX, int imgY) {
        super(name, description, imgURL, imgX, imgY);
        isEmpty = false;
        lvl = 2;
        itemScore = 650;
        
    }
    /*
     *   Overrriding the abstract method in UseableItem. 
     *   Returns the string describing the outcome of the if statements. 
    */
    @Override
    public String use(Player player) {
        String outputText = "";
            
        /*
         *   If the SmallFireExtinguisher's attribute isEmpty is false, it calls for another if statement that 
         *   checks if the player is inside a room containing fire. 
         *   If there is, it will put the out fire and set isEmpty to true.
         *   If the player is not in a room with fire, isEmpty is set to true 
         *   and the player will get negative itemScore for wasting it.
         *   
        */
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
