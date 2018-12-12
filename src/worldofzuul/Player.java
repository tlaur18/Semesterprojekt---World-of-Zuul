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

    public Player(Room room, String playerName) {
        stepCount = 0;
        health = 100;
        inventory = null;
        currentRoom = room;
        this.playerName = playerName;
    }

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
    
    public boolean checkForFireDamage() {
        if(currentRoom.getFire() != null) {
            takeDamage(25 * currentRoom.getFire().getLvl());
            return true;
        }
        return false;
    }
    
    public boolean checkForSmokeDamage() {
        if(currentRoom.getSmoke() != null) {
            takeDamage(5 * currentRoom.getSmoke().getLvl());
            return true;
        }
        return false;
    }

    public int takeDamage(int dmg) {
        return health -= dmg;
    }

    public boolean isDead() {
        return health <= 0;
    }

    public boolean hasWon() {
        return currentRoom.getGameComplete();
    }

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

    public void removeItem() {
        if (inventory != null) {
            inventory = null;
        }
    }

    public String goRoom(Command command, SimpleBooleanProperty changedRoom) {
        String direction = command.getSecondWord();
        nextRoom = currentRoom.getExit(direction);

        if (currentRoom.getFire() != null && nextRoom != previousRoom) {
            return "\nThe fire inside the room prevents you from getting to this door.";
        } else if (nextRoom.isLocked()) {
            return "\nThe door is locked! You need a key to open the door!";
        } else {
            previousRoom = currentRoom;
            currentRoom = nextRoom;
            addStep();
            changedRoom.set(true);
            return "\n" + currentRoom.getLongDescription();
        }
    }

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
        this.highscore = 1234 * stepCount;
    }
}
