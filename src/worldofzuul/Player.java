package worldofzuul;

public class Player {

    private int stepCount;
    private int health;
    private Item inventory;
    private Room currentRoom;

    public Player(Room room) {
        stepCount = 0;
        health = 100;
        inventory = null;
        currentRoom = room;
    }

    public int getStepCount() {
        return stepCount;
    }

    public int getHealth() {        
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

    public void looseHealth() {
        health = health - 50;
    }
    
    public void takeItem(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Take what?");
            return;
        }

        String itemName = command.getSecondWord();

        for (int i = 0; i < currentRoom.getItems().size(); i++) {
            if (itemName.toUpperCase().equals(currentRoom.getItems().get(i).getName().toUpperCase())) {
                inventory = currentRoom.getItems().get(i);
                System.out.println("You pick up the " + currentRoom.getItems().get(i).getName() + ".");
                currentRoom.getItems().remove(i);
                return;
            }
        }
        System.out.println("The room contains no such thing.");
    }
    
    public void dropItem() {
        if (inventory != null) {
            System.out.println("You drop the " + inventory.getName() + ".");
            currentRoom.getItems().add(inventory);
            inventory = null;
        } else {
            System.out.println("You do not carry anything to drop.");
        }
    }
    
    public void inspectInventory() {
        if (inventory != null) {
            System.out.println("You are carrying a " + inventory.getName() + ".");
            System.out.println(inventory.getDescription());
        } else {
            System.out.println("You are not carrying anything.");
        }
    }
    
    public void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
            System.out.println("your health is: " + health);
        }
    }
}
