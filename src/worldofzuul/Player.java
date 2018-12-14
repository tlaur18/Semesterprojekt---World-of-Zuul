/**
 * This class contains most of the accessor, mutator, attributes and lots
 * of other methods that defines the player or the players actions.
 * 
 * @author Alexander Nguyen, Jacob Wowk, Morten K. Jensen and Thomas S. Laursen
 * @version 2018.12.14
 * 
 */

package worldofzuul;

import items.UseableItem;
import items.Item;
import javafx.beans.property.SimpleBooleanProperty;

public class Player {

    private int stepCount;
    private String playerName;
    protected int health;
    private Item inventory;
    private Room currentRoom;
    private Room previousRoom;
    private Room nextRoom;
    private int highscore;
    private int progress;

    /*
    This constructor different attributes builds/defines the player.
    */
    public Player(Room room, String playerName) {
        stepCount = 0;
        health = 100;
        inventory = null;
        currentRoom = room;
        this.playerName = playerName;
        progress = 0;
    }

    public int getProgress() {
        return progress;
    }
    
    /* 
    This method raises the players progress to keep track on how far the player
    has gotten in the game, so the NPC can be used to guide the player.
    */
    public void raiseProgress() {
        progress++;
    }
    
    // This method is used to put the player in a start location.
    public Player(Room room) {
        this(room, "*N/A*");
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getStepCount() {
        return stepCount;
    }

    public int getHealth() {
        if (health < 0) {
            health = 0;
        }
        return health;
    }

    public void setStepCount(int stepCount) {
        this.stepCount = stepCount;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setInventory(Item item) {
        inventory = item;
    }

    public Item getInventory() {
        return inventory;
    }

    public Room getNextRoom() {
        return nextRoom;
    }

    public void setCurrentRoom(Room room) {
        currentRoom = room;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public Room getPreviousRoom() {
        return previousRoom;
    }

    public int addStep() {
        stepCount = stepCount + 1;
        return stepCount;
    }
    
    /*
    This method checks if the room has fire and will damage the player for 25 HP
    times the current room's fire level.
    The progress in this situation is set equals 1 if the progress is equals 0,
    so we could make the first progress work which is when the player
    encounters the first room with fire.
    */
    public boolean checkForFireDamage() {
        if(currentRoom.getFire() != null) {
            takeDamage(25 * currentRoom.getFire().getLvl());
            if (progress == 0) {
                progress = 1;
            }
            return true;
        }
        return false;
    }
    /*
    This method checks if the room has smoke and will damage the player for 5 HP
    times the current room's smoke level. But at the moment the smoke can max
    be level 1, so the damage will not increase or decrease.
    */
    public boolean checkForSmokeDamage() {
        if(currentRoom.getSmoke() != null) {
            takeDamage(5 * currentRoom.getSmoke().getLvl());
            return true;
        }
        return false;
    }

    // This method is used to grant damage upon the player.
    public int takeDamage(int dmg) {
        return health -= dmg;
    }

    // This method is used to check if the player is dead.
    public boolean isDead() {
        return health <= 0;
    }

    // This method is used to set a room as the winning room.
    public boolean hasWon() {
        return currentRoom.getGameComplete();
    }
    
    
    /*
    This method starts out by checking if there is anything in your inventory.
    If your inventory is empty, the for-each loop will check for available items
    in the current room and add the chosen item into your inventory.
    */
    public String takeItem(Command command) {
        if (inventory != null) {
            return "\nYou are already carrying a " + inventory.getName();
        }

        String itemName = command.getSecondWord();

        for (int i = 0; i < currentRoom.getItems().size(); i++) {
            if (itemName.toUpperCase().equals(currentRoom.getItems().get(i).getName().toUpperCase())) {
                inventory = currentRoom.getItems().get(i);
                currentRoom.getItems().remove(i);
                return "\nYou pick up the " + itemName + ".";
            }
        }
        return "";
    }
    
    
    /*
    This method starts out by checking if you have anything in your inventory.
    If you there is something in your inventory and you choose to drop the item
    the item will be added to the room's inventory.
    */
    public String dropItem() {
        if (inventory != null) {
            String outputString = "\nYou drop the " + inventory.getName();
            currentRoom.getItems().add(inventory);
            inventory = null;
            return outputString;
        } else {
            return "\nYou do not carry anything to drop.";
        }
    }

    // This method removes the item e.g when you have consumed the Yankie Bar.
    public void removeItem() {
        if (inventory != null) {
            inventory = null;
        }
    }

    /*
    This method is used to process the 'Go' command which contains an attribute
    that is used to set an exit to a certain room.
    The method starts by checking if the room contains fire, if it does, you
    won't be able to move any further.
    It will then check if the room is locked, if the room is locked, you would
    also not be able to move any further and will set the progress to 5, which
    is when you reach the door to the outside.
    At the end of the if-statement, the else, the player will be able to move
    to the next room.
    */
    public String goRoom(Command command, SimpleBooleanProperty changedRoom) {
        String direction = command.getSecondWord();
        nextRoom = currentRoom.getExit(direction);

        if (currentRoom.getFire() != null && nextRoom != previousRoom) {
            return "\nThe fire inside the room prevents you from getting to this door.";
        } else if (nextRoom.isLocked()) {
            progress = 5;
            return "\nThe door is locked.";
        } else {
            previousRoom = currentRoom;
            currentRoom = nextRoom;
            addStep();
            changedRoom.set(true);
            return "\n" + currentRoom.getLongDescription();
        }
    }

    
    /*
    This method starts by checking if the inventory is empty.
    If there is an item in the inventory, the method will check if the item is
    usesable, if the item is useable it will be used by the players command.
    If the item is unuseable a string will be returned that tells the player
    about it.
    */
    public String useItem() {
        if (inventory == null) {
            return "\nYou do not carry anything to use";
        } else if (inventory instanceof UseableItem) {
            return ((UseableItem) inventory).use(this);
        } else {
            return "\nThere is nothing interesting to use this item for.";
        }
    }

    public int getPlayerScore() {
        return highscore;
    }

    public void setPlayerScore() {
        this.highscore += (int) (100.0 /  ((1.0/5000.0) * stepCount + 1.0 / 100.0) - 5000.0);
    }
    
    // This method updates the highscore by adding your score/amount to highscore.
    public void updateHighscore(int amount){
        highscore += amount;
    }
}
