package worldofzuul;

public class Player {

    private int stepCount;
    private int health;
    private final int lostHealth = 25;
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

    public int looseHealth() {
        health -= lostHealth;
        return health;
    }

    public int lostHealth() {
        return lostHealth;
    }

    public boolean isDead() {
        if (health <= 0) {
            return true;
        } else {
            return false;
        }
    }

    public void takeItem(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Take what?");
            return;
        }
        
        if (inventory != null) {
            System.out.println("You are already carrying a " + inventory.getName());
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
            addStep();
            System.out.println(currentRoom.getLongDescription());
        }

        if (currentRoom.getShortDescription().equals("on the toilet, the room is filled with smoke and fire - GET OUT!")) {  //FIX DET HER DET ER NOGET MØG
            /**
             * Eksempel på hvis man fandt rummet, hvor ilden startede. Man kan
             * derfor bruge dette til at senere hen tilføje noget med stepcount
             * + spredning af ild.
             */
            //   System.out.println("You found the room where the fire started.");
            looseHealth();
            System.out.println("You have been damaged by the fire. \nYou lost " + lostHealth() + " health!");
            
        } else if (currentRoom.getShortDescription().equals("jumping out of the window! \nYou took a fatal hit to your head")) {
            for (int i = 1; i <= 4; i++) {
                looseHealth();
            }
            System.out.println("You lost " + (lostHealth() * 4) + " health!");

            //Her spreder ilden sig til hallway når man har gået 5+ skridt
        } else if (currentRoom.getShortDescription().equals("in the hallway with your sisters room, the door to the toilet and the staircase to downstairs") && stepCount > 5) {
            looseHealth();
            System.out.println("You lost " + lostHealth() + " health!");
        }
        System.out.println("Your health is: " + getHealth());
        
        if (isDead() == true) {
            System.out.println("You died...");
            System.exit(0);
        }
    }
}
