package worldofzuul;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Game {

    private Parser parser;
    private Player player;
    private Room bedroom, hallway, sistersRoom, livingRoom, wc, wc2, outside, window,
            office, kitchen, entrance, conservatory, basement, garage, masterBedroom;
    private ArrayList<Room> rooms;

    public Game() throws InterruptedException {
        System.out.println("Welcome to Fire Escape!\n");

        createRooms();
        createItems();
        createFire();
        parser = new Parser();
        printWelcome();
        
        rooms = new ArrayList(Arrays.asList(bedroom, hallway, sistersRoom, livingRoom, wc, wc2, outside, window,
            office, kitchen, entrance, conservatory, basement, garage, masterBedroom));
    }

    private void createRooms() {
        bedroom = new Room("in your smokefilled bedroom and you hear the fire cracking", 0);
        hallway = new Room("in the hallway with your sisters room, the door to the toilet and the staircase to downstairs", 0);
        sistersRoom = new Room("in your sister's room", 0);
        livingRoom = new Room("in the living room", 0);
        wc = new Room("on the toilet", 0);
        wc2 = new Room("on the toilet", 0);
        outside = new Room("outside", 0);
        window = new Room("jumping out of the window! \nYou took a fatal hit to your head", 0);
        office = new Room("in the office", 0);
        kitchen = new Room("in the kitchen", 0);
        entrance = new Room("in the entrace", 0);
        conservatory = new Room("in the conservatory", 0);
        basement = new Room("in the basement", 0);
        garage = new Room("in the garage", 0);
        masterBedroom = new Room("in the master bedroom", 0);

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
        NonUseableItems toothbrush = new NonUseableItems("Toothbrush", "Makes your teeth shiny.");
        SmallFireExtinguisher smallFireExtinguisherONE = new SmallFireExtinguisher("FireEx", "Used to extinguish small fire.", office);
        SmallFireExtinguisher smallFireExtinguisherTWO = new SmallFireExtinguisher("FireEx", "Used to extinguish small fire.", office);
        BigFireExtinguisher bigFireExtinguisher = new BigFireExtinguisher("FireExXL", "Used to extinguish big fire.", kitchen);
        NonUseableItems towel = new NonUseableItems("Towel", "Used to dry yourself");
        Doll doll = new Doll("Doll", "A girly play doll");
        NonUseableItems football = new NonUseableItems("Football", "A round toy used to being kicked");
        YankieBar yankieBar = new YankieBar("Yankie", "This delicious caramel, nougat, and milk chocolate bar is a great way to restore your Health!");
        wc.addItem(bucket);
        wc.addItem(toothbrush);
        wc2.addItem(towel);
        bedroom.addItem(football);
        sistersRoom.addItem(doll);
        office.addItem(yankieBar);
        garage.addItem(smallFireExtinguisherONE);
        garage.addItem(smallFireExtinguisherTWO);
        masterBedroom.addItem(bigFireExtinguisher);
    }

    private void createFire() {
        kitchen.addFire(1);
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
        //TimeUnit.SECONDS.sleep(1);
        System.out.println("\t BIP BIP BIP! There is a loud noise that woke you up, \n \t you notice the smell and the thin \n \t layer of smoke in you room.");
        System.out.println("\t The first thing you do is to take your cellphone and call for emergency,\n \t the number is 1-1-2.");
        //TimeUnit.SECONDS.sleep(3);
        System.out.println("112: \n- This is 1-1-2. What is your emergency? ");
        //TimeUnit.SECONDS.sleep(2);
        System.out.println("");
        System.out.println("You: \n- There is smoke in the room and I am all alone in the house.");
       // TimeUnit.SECONDS.sleep(2);
        System.out.println("");
        System.out.println("112: \n- Okay, just stay calm and lets get you to safety.");
     //   TimeUnit.SECONDS.sleep(2);
        System.out.println("- It doesn't help to panic.");
      //  TimeUnit.SECONDS.sleep(2);
        System.out.println("- What is your name?");
        System.out.print("> ");
        
        Scanner pn = new Scanner(System.in);
        String playerName = pn.nextLine();
        player = new Player(bedroom, playerName);
        
        System.out.println("");
        System.out.println(player.getPlayerName() + ": \n- My name is " + player.getPlayerName() + ". I will try my best with your help.\n");
      //  TimeUnit.SECONDS.sleep(2);
        System.out.println("112: \n- You need to get to safety and thats your primary objective.");
     //   TimeUnit.SECONDS.sleep(2);
        System.out.println("- You will encoutner some obstacles, and you will need to figure a way out of the house");
     //   TimeUnit.SECONDS.sleep(2);
        System.out.println("- If you have any questions just ask for '" + CommandWord.HELP + "'.\n");
        
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
            player.goRoom(command, this);
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
        } else if (commandWord == CommandWord.EXITS) {
            player.exits();
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

    public ArrayList<Room> getRooms() {
        return rooms;
    }
}
