package worldofzuul.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Game {

    private Parser parser;
    private Player player;
    private Item bucket, toothbrush, smallFireExtinguisher, bigFireExtinguisher, towel, doll, key, football, yankieBar, smallFireExtinguisher2;
   
    private ArrayList<Room> rooms;
    private ArrayList<Item> items;

    public Game() throws InterruptedException {
        System.out.println("Welcome to Fire Escape!\n");

       
        parser = new Parser();
        
//      rooms = new ArrayList(Arrays.asList(bedroom, hallway, sistersRoom, livingRoom, wc, wc2, outside, window,
//            office, kitchen, entrance, conservatory, basement, garage, masterBedroom));
//            items = new ArrayList(Arrays.asList(bucket, toothbrush, smallFireExtinguisher, bigFireExtinguisher, towel, doll, key, football, yankieBar, smallFireExtinguisher2));
    }



//    public void createItems() {
//        bucket = new Bucket("Bucket", "Holds liquid well.", wc, wc2);
//        toothbrush = new NonUseableItem("Toothbrush", "Makes your teeth shiny.");
//        smallFireExtinguisher = new SmallFireExtinguisher("FireEx", "Used to extinguish small fire.", office);
//        smallFireExtinguisher2 = new SmallFireExtinguisher("FireEx", "Used to extinguish small fire.", office);
//        bigFireExtinguisher = new BigFireExtinguisher("FireExXL", "Used to extinguish big fire.", kitchen);
//        towel = new NonUseableItem("Towel", "Used to dry yourself");
//        doll = new Doll("Doll", "A girly play doll");
//        key = new Key("Key", "Used to unlock things.");
//        football = new NonUseableItem("Football", "A round toy used to being kicked");
//        yankieBar = new YankieBar("Yankie", "This delicious caramel, nougat, and milk chocolate bar is a great way to restore your Health!");
//        wc.addItem(bucket);
//        wc.addItem(toothbrush);
//        wc2.addItem(towel);
//        bedroom.addItem(football);
//        sistersRoom.addItem(doll);
//        office.addItem(yankieBar);
//        garage.addItem(smallFireExtinguisher);
//        garage.addItem(smallFireExtinguisher2);
//        masterBedroom.addItem(bigFireExtinguisher);
//        conservatory.addItem(key);
//    }
    public ArrayList<Item> getItems() {
        return items;
    }

//    private void createFire() {
//        kitchen.addFire(1);
//        office.addFire(1);
//        livingRoom.addFire(1);
//    }

    public void play() {
        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing. Good bye.");
    }




    public boolean processCommand(Command command) {
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

    public void printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at you own home.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();

        System.out.println("\n you have walked " + player.getStepCount() + " steps so far");

    }

    public boolean quit(Command command) {
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
