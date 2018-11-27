package worldofzuul;

import java.util.ArrayList;
import java.util.Arrays;

public class Game {

    private Parser parser;
    private Player player;
    private Item bucket, toothbrush, smallFireExtinguisher, bigFireExtinguisher, towel, doll, key, football, yankieBar, smallFireExtinguisher2;
    private Room bedroom, hallway, sistersRoom, livingRoom, wc, wc2, outside, window,
            office, kitchen, entrance, conservatory, basement, garage, masterBedroom;
    private ArrayList<Room> rooms;

    public Game() {
        createRooms();
        createItems();
        createFire();
        player = new Player(bedroom);
        parser = new Parser();

        rooms = new ArrayList(Arrays.asList(bedroom, hallway, sistersRoom, livingRoom, wc, wc2, outside, window,
                office, kitchen, entrance, conservatory, basement, garage, masterBedroom));
    }

    private void createRooms() {
        bedroom = new Room("Bedroom", "in your smokefilled bedroom and you hear the fire cracking", 0, false);
        hallway = new Room("Hallway", "in the hallway with doors to your sisters room, the toilet and downstairs.", 0, false);
        sistersRoom = new Room("Sisters Room", "in your sister's room", 0, false);
        livingRoom = new Room("Living Room", "in the living room", 0, false);
        wc = new Room("Toilet", "on the toilet", 0, false);
        wc2 = new Room("Toilet", "on the toilet", 0, false);
        outside = new Room("Outside", "outside", 0, true);
        window = new Room("Window", "jumping out of the window! \nYou took a fatal hit to your head", 0, false);
        office = new Room("Office", "in the office", 0, false);
        kitchen = new Room("Kitchen", "in the kitchen", 0, false);
        entrance = new Room("Entrance", "in the entrace", 0, false);
        conservatory = new Room("Conservatory", "in the conservatory", 0, false);
        basement = new Room("Basement", "in the basement", 0, false);
        garage = new Room("Garage", "in the garage", 0, false);
        masterBedroom = new Room("Master Bedroom", "in the master bedroom", 0, false);

        bedroom.setExit("east", hallway);
        bedroom.setExit("west", window);

        hallway.setExit("west", bedroom);
        hallway.setExit("south", sistersRoom);
        hallway.setExit("east", livingRoom);
        hallway.setExit("north", wc);

        sistersRoom.setExit("north", hallway);

        wc.setExit("south", hallway);

        livingRoom.setExit("west", hallway);
        livingRoom.setExit("south", basement);
        livingRoom.setExit("east", kitchen);
        livingRoom.setExit("north", office);

        basement.setExit("north", livingRoom);
        basement.setExit("west", garage);

        garage.setExit("east", basement);

        kitchen.setExit("west", livingRoom);
        kitchen.setExit("east", entrance);
        kitchen.setExit("south", conservatory);

        conservatory.setExit("north", kitchen);

        entrance.setExit("west", kitchen);
        entrance.setExit("east", outside);

        office.setExit("south", livingRoom);
        office.setExit("east", masterBedroom);

        masterBedroom.setExit("west", office);
        masterBedroom.setExit("east", wc2);

        wc2.setExit("west", masterBedroom);

        //Sætter rum man skal nå for at vinde:
        outside.setGameComplete();

        //Sætter rum hvor spillet tabes så snart man går derind
        window.setGameOver();
    }

    private void createItems() {
        bucket = new Bucket("Bucket", "Holds liquid well.", wc, wc2);
        toothbrush = new NonUseableItem("Toothbrush", "Makes your teeth shiny.");
        smallFireExtinguisher = new SmallFireExtinguisher("FireEx", "Used to extinguish small fire.", office);
        smallFireExtinguisher2 = new SmallFireExtinguisher("FireEx", "Used to extinguish small fire.", office);
        bigFireExtinguisher = new BigFireExtinguisher("FireExXL", "Used to extinguish big fire.", kitchen);
        towel = new NonUseableItem("Towel", "Used to dry yourself");
        doll = new Doll("Doll", "A girly play doll");
        key = new Key("Key", "Used to unlock things.");
        football = new NonUseableItem("Football", "A round toy used to being kicked");
        yankieBar = new YankieBar("Yankie", "This delicious caramel, nougat, and milk chocolate bar is a great way to restore your Health!");
        wc.addItem(bucket);
        wc.addItem(toothbrush);
        wc2.addItem(towel);
        bedroom.addItem(football);
        sistersRoom.addItem(doll);
        office.addItem(yankieBar);
        garage.addItem(smallFireExtinguisher);
        garage.addItem(smallFireExtinguisher2);
        masterBedroom.addItem(bigFireExtinguisher);
        conservatory.addItem(key);
    }

    private void createFire() {
        kitchen.addFire(1);
        office.addFire(1);
        livingRoom.addFire(1);
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public Player getPlayer() {
        return player;
    }

}
