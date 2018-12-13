/*
    * this class is a specific item and is a sub-class of useableItem.

    * @author Alexander Nguyen, Jacob Wowk, Morten K. Jensen and Thomas S. Laursen
    * @version 2018.12.14

 */
package items;

import worldofzuul.Player;
import worldofzuul.Room;

public class Key extends UseableItem {

    private int itemScore;
    //the contructor is a extend of useableItems and is additional attribute of itemScore.
    public Key(String name, String description, String imgURL, int imgX, int imgY) {
        super(name, description, imgURL, imgX, imgY);
        itemScore = 740;
    }
    /*
        here we override the abstract method in useableItem. 
        for each 
    */
    @Override
    public String use(Player player) {
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
