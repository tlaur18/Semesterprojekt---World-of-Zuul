package worldofzuul;

public class Game {

    private Parser parser;
    private Room currentRoom;

    public Game() {
        createRooms();
        parser = new Parser();
    }

    private void createRooms() {
        Room startRoom, hallway, deathRoom, closet, office, wc;

        startRoom = new Room("inside your own bedroom"
                + " A thick layer of smoke fills the upper part of the room");
        hallway = new Room("in the hallway");
        deathRoom = new Room("dead");
        closet = new Room("sitting in your closet");
        office = new Room("in the computing admin office");
        wc = new Room("on the toilet");

        startRoom.setExit("east", hallway);         //Gangen (Mød dør)
        startRoom.setExit("north", closet);         //Skab (Rum i sig selv)
        startRoom.setExit("west", deathRoom);       //Vindue (Eventuelt dø-rum)

        hallway.setExit("west", startRoom);

        deathRoom.setExit("east", startRoom);
        deathRoom.setExit("west", wc);

        wc.setExit("east", deathRoom);

        closet.setExit("north", startRoom);
        closet.setExit("south", office);

        office.setExit("north", closet);

        currentRoom = startRoom;
    }

    public void play() {
        printWelcome();

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Goodbye.");
    }

    private void printWelcome() {
        System.out.println("Welcome to Fire Escape!");
        System.out.println("Get ready to get your fire escaping abilities tested!");
        System.out.println("The goal of this game is to get out of the burning building alive.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println("You wake up by the horrible smell of smoke.");
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
        } else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        }
        return wantToQuit;
    }

    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
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
    }

    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;
        }
    }
}
