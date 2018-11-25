package worldofzuul;

import exceptions.MovingThroughFireException;
import exceptions.MovingThroughLockedDoorException;
import exceptions.NoExitException;
import exceptions.NoItemToDropException;
import exceptions.NoSecondWordGivenException;
import exceptions.NoSuchItemInRoomException;
import exceptions.PlayerInventoryFullException;
import exceptions.UseNonUseableItemException;
import exceptions.UseWithEmptyInventoryException;

public class Player {

    private int stepCount;
    private String playerName;
    protected int health;
    private Item inventory;
    private Room currentRoom;
    private Room previousRoom;
    private Room nextRoom;

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

    public int addStep() {
        stepCount = stepCount + 1;
        return stepCount;
    }

    public int takeDamage(int dmg) {
        return health -= dmg;
    }

    public boolean isDead() {
        return health <= 0;
    }

    public void takeItem(Command command) throws NoSecondWordGivenException, PlayerInventoryFullException, NoSuchItemInRoomException {
        if (!command.hasSecondWord()) {
            throw new NoSecondWordGivenException();
        }

        if (inventory != null) {
            throw new PlayerInventoryFullException();
        }

        String itemName = command.getSecondWord();

        for (int i = 0; i < currentRoom.getItems().size(); i++) {
            if (itemName.toUpperCase().equals(currentRoom.getItems().get(i).getName().toUpperCase())) {
                inventory = currentRoom.getItems().get(i);
                currentRoom.getItems().remove(i);
                return;
            }
        }
        throw new NoSuchItemInRoomException();
    }

    public void dropItem() throws NoItemToDropException {
        if (inventory != null) {
            currentRoom.getItems().add(inventory);
            inventory = null;
        } else {
            throw new NoItemToDropException();
        }
    }

    public void removeItem() {
        if (inventory != null) {
            inventory = null;
        }
    }

    public void goRoom(Command command) throws NoSecondWordGivenException, NoExitException, MovingThroughFireException, MovingThroughLockedDoorException {
        if (!command.hasSecondWord()) {
            throw new NoSecondWordGivenException();
        }

        String direction = command.getSecondWord();
        nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            throw new NoExitException();
        } else if (currentRoom.getFire() != null && nextRoom != previousRoom) {
            throw new MovingThroughFireException();
        } else if (nextRoom.isLocked()) {
            throw new MovingThroughLockedDoorException();
        } else {
            previousRoom = currentRoom;
            currentRoom = nextRoom;
            addStep();
        }
    }

    public String useItem() throws UseWithEmptyInventoryException, UseNonUseableItemException {
        String outputText = "";
        if (inventory == null) {
            throw new UseWithEmptyInventoryException();
        } else if (inventory instanceof UseableItem) {
            outputText = ((UseableItem) inventory).use(this);
        } else {
            throw new UseNonUseableItemException();
        }
        return outputText;
    }

    public boolean hasWon() {
        return currentRoom.getGameComplete();
    }
}
