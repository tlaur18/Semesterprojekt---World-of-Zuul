package worldofzuul;

import java.util.Scanner;

public class Game {

    private Parser parser;
    private Room currentRoom;
    private Player player;
    private Item inventory = null;
    private Room bedroom, hallway, sistersRoom, livingRoom, lobby, wc, outside, window;

    public Game() {
        createRooms();
        createItems();
        parser = new Parser();
        player = new Player();

    }

    private void createRooms() {
        bedroom = new Room("in your smokefilled bedroom and you hear the fire cracking");
        hallway = new Room("in the hallway with your sisters room, the door to the toilet and the staircase to downstairs");
        sistersRoom = new Room("in your sister's room");
        livingRoom = new Room("in the living room");
        lobby = new Room("in the lobby facing the front door");
        wc = new Room("on the toilet, the room is filled with smoke and fire - GET OUT!");
        outside = new Room("outside");
        window = new Room("jumping out of the window! \nYou took a fatal hit to your head");

        bedroom.setExit("hallway", hallway);
        bedroom.setExit("window", window);

        hallway.setExit("bedroom", bedroom);
        hallway.setExit("sister-room", sistersRoom);
        hallway.setExit("downstairs", livingRoom);
        hallway.setExit("toilet", wc);

        sistersRoom.setExit("hallway", hallway);

        wc.setExit("hallway", hallway);

        livingRoom.setExit("upstairs", hallway);
        livingRoom.setExit("lobby", lobby);

        lobby.setExit("livingroom", livingRoom);
        lobby.setExit("outside", outside);

        currentRoom = bedroom;
    }

    private void createItems() {
        Item bucket = new Item("Bucket", "A bucket to hold liquid");
        Item toothbrush = new Item("Toothbrush", "Makes your teeth shiny");
        wc.addItem(bucket);
        wc.addItem(toothbrush);
    }

    public void play() {
        printWelcome();

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    public void restart() {
        Game game = new Game();
        game.play();
    }

    private void printWelcome() {
        System.out.println("Welcome to Fire Escape!");
        System.out.println("Get ready to get your fire escaping abilities tested!");
        System.out.println("The goal of this game is to get out of the burning building alive.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();

        //Dette er et eksempel på hvordan vi kunne starte ud.
//        System.out.println("A lightbulb somewhere in the house exploded and started a fire."
//                + " \nThe smoke from the fire spread throughout the house.");
        System.out.println("The horrible smell of smoke has awoken you.");
        System.out.println(currentRoom.getLongDescription());
    }

    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if (commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        if (commandWord == CommandWord.HELP) {
            printHelp();
        } else if (commandWord == CommandWord.GO) {
            goRoom(command);
            player.addStep();
        } else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        } else if (commandWord == CommandWord.TAKE) {
            takeItem(command);
        } else if (commandWord == CommandWord.DROP) {
            dropItem();
        }
        return wantToQuit;
    }

    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at you own home.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println("you have walked " + player.getStepCount() + " steps so far");
        parser.showCommands();
    }

    private void goRoom(Command command) {
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

        }

        if (currentRoom == wc) {
            /**
             * Eksempel på hvis man fandt rummet, hvor ilden startede. Man kan
             * derfor bruge dette til at senere hen tilføje noget med stepcount
             * + spredning af ild.
             */
            //   System.out.println("You found the room where the fire started.");
            player.looseHealth();
            System.out.println("You have been damaged by the fire. \nYou lost " + player.lostHealth() + " health!");

        } else if (currentRoom == window) {
            for (int i = 1; i <= 4; i++) {
                player.looseHealth();
            }
            System.out.println("You lost " + (player.lostHealth() * 4) + " health!");

            //Her spreder ilden sig til hallway når man har gået 5+ skridt
        } else if (currentRoom == hallway && player.getStepCount() > 5) {
            player.looseHealth();
            System.out.println("You lost " + player.lostHealth() + " health!");
        }
        System.out.println("Your health is: " + player.getHealth());

        if (player.isDead() == true) {
            System.out.println("You died...");
            System.out.println("Do you wanna try agian?[Y/N]");
            Scanner option = new Scanner(System.in);
            String userInput = option.nextLine();

            if (userInput.equals("Y") || userInput.equals("y")) {
               System.out.println("Nice!");
                restart();
            }   
             else if (userInput.equals("N") || userInput.equals("n")) {
                 System.out.println("Thanks for playing!");
                System.exit(0);
            }
        }
    }

    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;
        }
    }

    private void takeItem(Command command) {
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

    private void dropItem() {
        if (inventory != null) {
            System.out.println("You drop the " + inventory.getName() + ".");
            currentRoom.getItems().add(inventory);
            inventory = null;
        } else {
            System.out.println("You do not carry anything to drop.");
        }
    }
}
