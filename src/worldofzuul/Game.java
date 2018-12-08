package worldofzuul;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import worldofzuul.dataaccess.DataAccess;

public class Game {
    private Parser parser;
    private Player player;
    private Highscore highscore;
    private Item bucket, toothbrush, smallFireExtinguisher, bigFireExtinguisher, towel, doll, key, football, yankieBar, smallFireExtinguisher2;
    private Room bedroom, hallway, sistersRoom, livingRoom, wc, wc2, outside, window,
            office, kitchen, entrance, conservatory, basement, garage, masterBedroom;
    private ArrayList<Room> rooms;

    public Game() {
        createRooms();
        createItems();
        createFire();
        highscore = new Highscore();
        player = new Player(bedroom);
        parser = new Parser();
        rooms = new ArrayList(Arrays.asList(bedroom, hallway, sistersRoom, livingRoom, wc, wc2, outside, window,
                office, kitchen, entrance, conservatory, basement, garage, masterBedroom));
        
    }

    private void createRooms() {
        bedroom = new Room("Bedroom", "in your smokefilled bedroom and you hear the fire cracking", false, "Imgs/bedroom.png");
        hallway = new Room("Hallway", "in the hallway with doors to your sisters room, the toilet and downstairs.", false, "Imgs/hallway.png");
        sistersRoom = new Room("Sisters Room", "in your sister's room", false, "Imgs/sistersRoom.png");
        livingRoom = new Room("Living Room", "in the living room", false, "Imgs/Livingroom.png");
        wc = new Room("Toilet", "on the toilet", false, "Imgs/bathroom.png");
        wc2 = new Room("Toilet", "on the toilet", false, "Imgs/bathroom.png");
        outside = new Room("Outside", "outside", true, "Imgs/outside.png");
        window = new Room("Window", "jumping out of the window! \nYou took a fatal hit to your head", false, "Imgs/bedroom.png");
        office = new Room("Office", "in the office", false, "Imgs/office.png");
        kitchen = new Room("Kitchen", "in the kitchen", false, "Imgs/kitchen.png");
        entrance = new Room("Entrance", "in the entrace", false, "Imgs/basement.png");
        conservatory = new Room("Conservatory", "in the conservatory", false, "Imgs/conservatory.png");
        basement = new Room("Basement", "in the basement", false, "Imgs/basement.png");
        garage = new Room("Garage", "in the garage", false, "Imgs/garage.png");
        masterBedroom = new Room("Master Bedroom", "in the master bedroom", false, "Imgs/masterbedroom.png");

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
        basement.setExit("east", garage);

        garage.setExit("west", basement);

        kitchen.setExit("west", livingRoom);
        kitchen.setExit("east", entrance);
        kitchen.setExit("north", conservatory);

        conservatory.setExit("south", kitchen);

        entrance.setExit("west", kitchen);
        entrance.setExit("east", outside);

        office.setExit("south", livingRoom);
        office.setExit("north", masterBedroom);

        masterBedroom.setExit("south", office);
        masterBedroom.setExit("east", wc2);

        wc2.setExit("west", masterBedroom);

        //Sætter rum man skal nå for at vinde:
        outside.setGameComplete();

        //Sætter rum hvor spillet tabes så snart man går derind
        window.setGameOver();
    }

    private void createItems() {
        bucket = new Bucket("Bucket", "Holds liquid well.", wc, wc2, "Imgs/EmptyBucket.png");
        toothbrush = new NonUseableItem("Toothbrush", "Makes your teeth shiny.", "Imgs/Toothbrush.png");
        smallFireExtinguisher = new SmallFireExtinguisher("SmallFireExtinguisher1", "Used to extinguish small fire.", office, "Imgs/SmallFireExtinguisher.png");
        smallFireExtinguisher2 = new SmallFireExtinguisher("SmallFireExtinguisher2", "Used to extinguish small fire.", office, "Imgs/SmallFireExtinguisher.png");
        bigFireExtinguisher = new BigFireExtinguisher("BigFireExtinguisher", "Used to extinguish big fire.", kitchen, "Imgs/BigFireExtinguisher.png");
        towel = new NonUseableItem("Towel", "Used to dry yourself", "Imgs/Towel.png");
        doll = new Doll("Doll", "A girly play doll", "Imgs/Doll.png");
        key = new Key("Key", "Used to unlock things.", "Imgs/Key.png");
        football = new NonUseableItem("Football", "A round toy used to being kicked", "Imgs/Football.png");
        yankieBar = new YankieBar("YankieBar", "This delicious caramel, nougat, and milk chocolate bar is a great way to restore your Health!", "Imgs/YankieBar.png");
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
    public Highscore getHighscore() {
        return highscore;
    }
    public void saveHighscore(Player player){
        highscore.saveHighscore(player);
    }
    
}
