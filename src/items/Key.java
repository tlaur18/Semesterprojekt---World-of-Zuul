/*
    * This class is a specific item and is a sub-class of UseableItem.
    *
    * @author Alexander Nguyen, Jacob Wowk, Morten K. Jensen and Thomas S. Laursen
    * @version 2018.12.14
    *
 */
package items;

import worldofzuul.Player;
import worldofzuul.Room;

public class Key extends UseableItem {

    private int itemScore;
    /** The contructor takes the regular Item attributes, its additional attributes
     *  are initialized.
     *  itemScore is the point given to the player's score if used in the right situration
     */
    public Key(String name, String description, String imgURL, int imgX, int imgY) {
        super(name, description, imgURL, imgX, imgY);
        itemScore = 740;
    }
    /* 
        Overrriding the abstract method in UseableItem. 
        Returns the string describing the outcome of the method.
        
    */
    @Override
    public String use(Player player) {
        /*Loops through the exits of the current room to check if any 
         *neightbor rooms are locked
         */
        for (Room room : player.getCurrentRoom().getExits().values()) {
            if (room.isLocked()) {
                room.unlockRoom();
                player.updateHighscore(itemScore);
                return "\nYou are able to unlock the door to the " + room.getName() + " with your key.";
            }
        }
        return "\nThis key can not be used on any of the doors around you.";
    }

    public int getItemScore() {
        return itemScore;
    }
}
