package worldofzuul;

import java.util.Scanner;


public class Game {

    private Parser parser;
    private Player player;

    private Room bedroom, hallway, sistersRoom, livingRoom, lobby, wc, wc2, outside, window,
            office, kitchen, entrance, conservatory, basement, garage, masterBedroom;

    public Game() {
        System.out.println("Welcome to Fire Escape!\n");
        
        createRooms();
        createItems();
        parser = new Parser();
     
        System.out.println("\t BIP BIP BIP! There is a loud noise that woke you up, \n \t you notice the smell and the thin \n \t layer of smoke in you room.");
        System.out.println("\t The first thing you do is to take your cellphone and call for emergency,\n \t the number is 1-1-2.");
        System.out.println("112: \n- This is 1-1-2. What is your emergency? ");
        System.out.println("You: \n- There is smoke in the room and I am all alone in the house.");
        System.out.println("Falck: \n- Okay, just stay calm and lets get you to safety.\n- It doesn't help to panic."
                + "- What is your name?  \n");
        Scanner pn = new Scanner(System.in);
        String playerName = pn.nextLine();
        
        
        
        
           player = new Player(bedroom, playerName);
        
       
        
        
    }

    private void createRooms() {
        bedroom = new Room("in your smokefilled bedroom and you hear the fire cracking");
        hallway = new Room("in the hallway with your sisters room, the door to the toilet and the staircase to downstairs");
        sistersRoom = new Room("in your sister's room");
        livingRoom = new Room("in the living room");
        lobby = new Room("in the lobby facing the front door");
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
        
        office.setExit("livingroom", livingRoom);
        office.setExit("master-bedroom", masterBedroom);
        
        masterBedroom.setExit("office", office);
        masterBedroom.setExit("toilet", wc2);
        
        wc2.setExit("master-bedroom", masterBedroom);
    }

    private void createItems() {
        Item bucket = new Item("Bucket", "Holds liquid well.");
        Item toothbrush = new Item("Toothbrush", "Makes your teeth shiny.");
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
        System.out.println("Thank you for playing. Good bye.");
    }
    
    private void printWelcome() {
        
      
        //Dette er et eksempel på hvordan vi kunne starte ud.
//        System.out.println("A lightbulb somewhere in the house exploded and started a fire."
//                + " \nThe smoke from the fire spread throughout the house.");
        
        System.out.println(player.getPlayerName() + ": \n- okay, i will try my best with your help.\n");
        System.out.println("Falck: \n- You need to get to safety and thats your primary objektive you\n "
                + " will meet some obstacles and you will need to figure a way out of the house\n"
                + "- if you have any questions just ask for '"+ CommandWord.HELP +"'.\n");
        
        
        
        
        
        System.out.println("");
        System.out.println(player.getCurrentRoom().getLongDescription());
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
}
