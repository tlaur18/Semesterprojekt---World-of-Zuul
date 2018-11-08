package worldofzuul;

public class Game {

    private Parser parser;
    private Player player;
    private static Room bedroom, hallway, sistersRoom, livingRoom, wc, wc2, outside, window,
            office, kitchen, entrance, conservatory, basement, garage, masterBedroom;

    public Game() {
        createRooms();
        createItems();
        createFire();
        parser = new Parser();
        player = new Player(bedroom);
    }

    private void createRooms() {
        bedroom = new Room("in your smokefilled bedroom and you hear the fire cracking");
        hallway = new Room("in the hallway with your sisters room, the door to the toilet and the staircase to downstairs");
        sistersRoom = new Room("in your sister's room");
        livingRoom = new Room("in the living room");
        wc = new Room("on the toilet, the room is filled with smoke and fire - GET OUT!");
        wc2 = new Room("on the toilet");
        outside = new Room("outside");
        window = new Room("jumping out of the window! \nYou took a fatal hit to your head");
        office = new Room("in the office");
        kitchen = new Room("in the kitchen");
        entrance = new Room("in the entrace");
        conservatory = new Room("in the conservatory");
        basement = new Room("in the basement");
        garage = new Room("in the garage");
        masterBedroom = new Room("in the master bedroom");

        bedroom.setExit("hallway", hallway);
        bedroom.setExit("window", window);

        hallway.setExit("bedroom", bedroom);
        hallway.setExit("sister-room", sistersRoom);
        hallway.setExit("downstairs", livingRoom);
        hallway.setExit("toilet", wc);

        sistersRoom.setExit("hallway", hallway);

        wc.setExit("hallway", hallway);

        livingRoom.setExit("upstairs", hallway);
        livingRoom.setExit("basement", basement);
        livingRoom.setExit("kitchen", kitchen);
        livingRoom.setExit("office", office);

        basement.setExit("livingroom", livingRoom);
        basement.setExit("garage", garage);

        garage.setExit("basement", basement);

        kitchen.setExit("livingroom", livingRoom);
        kitchen.setExit("entrance", entrance);
        kitchen.setExit("conservatory", conservatory);

        conservatory.setExit("kitchen", kitchen);

        entrance.setExit("kitchen", kitchen);
        entrance.setExit("outside", outside);

        office.setExit("livingroom", livingRoom);
        office.setExit("master-bedroom", masterBedroom);

        masterBedroom.setExit("office", office);
        masterBedroom.setExit("toilet", wc2);

        wc2.setExit("master-bedroom", masterBedroom);
    }

    private void createItems() {
        Items bucket = new Items("Bucket", "Holds liquid well.");
        Items toothbrush = new Items("Toothbrush", "Makes your teeth shiny.");
        Items smallFireExtinguisherONE = new Items("FirstFireExtinguisher", "Used to extinguish small fire.");
        Items smallFireExtinguisherTWO = new Items("SecondFireExtinguisher", "Used to extinguish small fire");
        Items bigFireExtinguisher = new Items("FireExtinguisher", "Used to extinguish big fire.");
        Items towel = new Items("Towel", "Used to dry yourself");
        Items doll = new Items("Doll", "A girly play doll");
        Items football = new Items("Football", "A round toy used to being kicked");
        wc.addItem(bucket);
        wc.addItem(toothbrush);
        wc2.addItem(towel);
        bedroom.addItem(football);
        sistersRoom.addItem(doll);
        garage.addItem(smallFireExtinguisherONE);
        garage.addItem(smallFireExtinguisherTWO);
        masterBedroom.addItem(bigFireExtinguisher);
    }

    private void createFire() {
        kitchen.addFire(3);
        office.addFire(1);
        livingRoom.addFire(1);
    }

    public void play() {
        printWelcome();

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing. Good bye.");
    }

    private void printWelcome() {
        System.out.println("Welcome to Fire Escape!");
        System.out.println("Get ready to get your fire escaping abilities tested!");
        System.out.println("The goal of this game is to get out of the burning building alive.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println("You wake up by the horrible smell of smoke.");
        System.out.println(player.getCurrentRoom().getLongDescription());
        System.out.println(player.getCurrentRoom().getExitString());
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
            player.goRoom(command);
        } else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        } else if (commandWord == CommandWord.TAKE) {
            player.takeItem(command);
        } else if (commandWord == CommandWord.DROP) {
            player.dropItem();
        } else if (commandWord == CommandWord.INSPECT) {
            player.inspectInventory();
        } else if (commandWord == CommandWord.SEARCH) {
            player.searchRoom();
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

    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;
        }
    }

    public static Room[] getRooms() {
        Room[] rooms = {bedroom, hallway, sistersRoom, livingRoom, wc, wc2, outside, window, 
            office, kitchen, entrance, conservatory, basement, garage, masterBedroom};
        return rooms;
    }
}
