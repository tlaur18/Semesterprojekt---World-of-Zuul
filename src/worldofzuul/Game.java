package worldofzuul;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Game {

    private Parser parser;
    private Player player;
    private static Room bedroom, hallway, sistersRoom, livingRoom, wc, wc2, outside, window,
            office, kitchen, entrance, conservatory, basement, garage, masterBedroom;

    public Game() throws InterruptedException {
        System.out.println("Welcome to Fire Escape!\n");

        createRooms();
        createItems();
        createFire();
        parser = new Parser();
        printWelcome();

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
        Bucket bucket = new Bucket("Bucket", "Holds liquid well.", wc, wc2);
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
        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing. Good bye.");
    }


    private void printWelcome() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        System.out.println("\t BIP BIP BIP! There is a loud noise that woke you up, \n \t you notice the smell and the thin \n \t layer of smoke in you room.");
        System.out.println("\t The first thing you do is to take your cellphone and call for emergency,\n \t the number is 1-1-2.");
        TimeUnit.SECONDS.sleep(3);
        System.out.println("112: \n- This is 1-1-2. What is your emergency? ");
        TimeUnit.SECONDS.sleep(2);
        System.out.println("");
        System.out.println("You: \n- There is smoke in the room and I am all alone in the house.");
        TimeUnit.SECONDS.sleep(2);
        System.out.println("");
        System.out.println("112: \n- Okay, just stay calm and lets get you to safety.");
        TimeUnit.SECONDS.sleep(2);
        System.out.println("- It doesn't help to panic.");
        TimeUnit.SECONDS.sleep(2);
        System.out.println("- What is your name?");
        System.out.print("> ");
        
        Scanner pn = new Scanner(System.in);
        String playerName = pn.nextLine();
        player = new Player(bedroom, playerName);
        
        System.out.println("");
        System.out.println(player.getPlayerName() + ": \n- My name is " + player.getPlayerName() + ". I will try my best with your help.\n");
        TimeUnit.SECONDS.sleep(2);
        System.out.println("112: \n- You need to get to safety and thats your primary objective.");
        TimeUnit.SECONDS.sleep(2);
        System.out.println("- You will meet some obstacles, and you will need to figure a way out of the house");
        TimeUnit.SECONDS.sleep(2);
        System.out.println("- if you have any questions just ask for '" + CommandWord.HELP + "'.\n");
        
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
        } else if (commandWord == CommandWord.USE) {
            player.useItem();
        }
        return wantToQuit;
    }

    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at you own home.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();

        System.out.println("\n you have walked " + player.getStepCount() + " steps so far");

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
