/**
 *
 * The Room class defines how the Rooms in the Game are built and behave.
 * The Game consists of many Rooms that the Player is able to move between.
 * A Room is connected to other rooms with the HashMap 'exits'.
 * These 'exits' are the rooms that the Player is able to move to from the current Room.
 * A Room is able to contain Items in its ArrayList 'items'. These can be picket up.
 * A Room is able to contain Fire. This will damage the player and work as a barrier
 * A Room is able to contain Smoke. This will damage the player.
 * to prevent the player from getting to a Room's exits.
 * A Room can be locked, preventing the player from entering it from the Room's neighbors.
 *
 * @author Alexander Nguyen, Jacob Wowk, Morten K. Jensen, Thomas S. Laursen
 * @version 2018.12.14
 *
 */
package worldofzuul;

import items.Item;
import java.util.HashMap;
import java.util.ArrayList;
import javafx.scene.image.ImageView;

public class Room {

    private String name;
    private String description;
    private HashMap<String, Room> exits;
    private ArrayList<Item> items = new ArrayList();
    private Fire fire = null;
    private Smoke smoke = null;
    private boolean isLocked;
    private boolean isGameComplete;
    private boolean isGameOver;
    private ImageView img;
    private boolean hasWater;

    /*
    * The constructor three Strings as parameters. The first decides the name of the Room.
    * The seconds decides the description. The description is printed to the user when the player changes Rooms.
    * The third String decides the path to the Room's background image. 
    * The Room contains an ImageView which image is set to the image at the given path.
    * This ImageView is shown in the background of the programme's window when playing.
    */
    public Room(String name, String description, String imgURL) {
        this.name = name;
        this.description = description;
        exits = new HashMap<String, Room>();
        this.isGameComplete = false;
        this.isGameOver = false;
        img = new ImageView(imgURL);
        hasWater = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    /*
    * The game is won when the Player gets to the final Room.
    * The attribute 'isGameComplete' decides if the Player wins when getting to this Room.
    * The method is called on one single Room in the method 'createRooms' in Game.java.
    */
    public void setGameComplete() {
        isGameComplete = true;
    }
    
    /*
    * Used to check if the game is won when entering a new Room.
    */
    public boolean getGameComplete() {
        return isGameComplete;
    }

    /*
    * Some Rooms will cause the Player to lose instantly.
    * This is decided by the attribute 'isGameOver' which is set to 'true' by this method.
    * The method is called on one single Room in the method 'createRooms' in Game.java.
    */
    public void setGameOver() {
        isGameOver = true;
    }

    /*
    * Used to check if the game is lost when entering a new Room.
    */
    public boolean getGameOver() {
        return isGameOver;
    }

    /*
    * The HashMap 'exits' connects the Rooms to each other.
    * A Player is able to move from one room to a room contained in the HashMap.
    * The method 'setExit' creates new entries in this HashMap.
    * The methods takes a String direction and a Room neighbor as parametres.
    * 'direction' decides what input is needed to get to the concerned neighbor.
    * 'neighbor' decides what Rooms can be accesed from the concerned Room.
    * This method is called in the method 'createRooms' in Game.java.
    */
    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    public String getShortDescription() {
        return description;
    }
    
    public String getLongDescription() {
        return ("You are " + description + ".");
    }
    
    public HashMap<String, Room> getExits() {
        return exits;
    }

    /*
    * Returs a Room based on a String. 
    * Used to decide what neighbor the player is moving to.
    */
    public Room getExit(String direction) {
        return exits.get(direction);
    }

    /*
    * Adds an Item to the list of Items.
    * Called in the method 'createItems' in Game.java.
    */
    public void addItem(Item item) {
        items.add(item);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    /*
    * Adds fire to the Room.
    * Called in the method 'createFire' in Game.java.
    */
    public void addFire(int lvl) {
        fire = new Fire(lvl);
    }

    public Fire getFire() {
        return fire;
    }    

    /*
    * Raises the level og the Fire inside the Room by one.
    */
    public void raiseFireLvl() {
        if (fire != null) {
            fire.raiseLvl();
        }
    }

    /*
    * Lowers the level of the fire inside the room.
    * Called when the player puts out a Fire.
    * If the level is lowered to zero or less, the fire will be removed.
    * Returns a String describing the outcome of the level-lowering.
    */
    public String lowerFireLvl(int lvlsToLower) {
        fire.lowerLvl(lvlsToLower);
        if (fire.getLvl() <= 0) {
            fire = null;
            return " The fire was put out.";
        }
        return " The fire was weakened.";
    }

    /*
    * Adds smoke to the Room.
    * Called in the method 'createSmoke' in Game.java.
    */
    public void addSmoke(int lvl) {
        smoke = new Smoke(lvl);
    }

    public Smoke getSmoke() {
        return smoke;
    }

    /*
    * Sets the attribute 'isLocked' to false, which allows the player to access this Room.
    * Called when the Player uses the UseableItem Key.
    */
    public void unlockRoom() {
        this.isLocked = false;
    }

    /*
    * Returns the attribute 'isLocked'.
    * Used to determine whether the player has access to the Room.
    */
    public boolean isLocked() {
        return isLocked;
    }

    /*
    * Sets the attribute 'isLocked' to true.
    * Called when the Rooms are created in 'createRooms' in Game.java.
    */
    public void setLocked() {
        isLocked = true;
    }

    public ImageView getImage() {
        return img;
    }
    
    /*
    * Returns the boolean value 'hasWater'.
    * Used to determine if the Player is allowed to fill the bucket in the concerning Room.
    */
    public boolean hasWater() {
        return hasWater;
    }

    /*
    * Sets the attribute 'hasWater'.
    * Called in the method 'createRooms' in Game.java.
    */
    public void setHasWater() {
        hasWater = true;
    }
}
