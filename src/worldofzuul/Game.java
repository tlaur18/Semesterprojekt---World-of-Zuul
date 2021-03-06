/**
 *
 * This class contains most of the other classes inside it to create the game.
 * All of the rooms and objects are created in this class. The objects are
 * created in this class so it can be possible to assign each object to each
 * room. Every room also have one or multiple exits which is also defined here.
 *
 *
 * @author Alexander Nguyen, Jacob Wowk, Morten K. Jensen and Thomas S. Laursen
 * @version 2018.12.14
 *
 */
package worldofzuul;

import items.Key;
import items.Doll;
import items.SmallFireExtinguisher;
import items.BigFireExtinguisher;
import items.Item;
import items.YankieBar;
import items.NonUseableItem;
import items.Bucket;
import java.util.ArrayList;
import java.util.Arrays;

public class Game {
    
    private Player player;
    private Item bucket, toothbrush, smallFireExtinguisher, bigFireExtinguisher, towel, doll, key, football, yankieBar, smallFireExtinguisher2;
    private Room bedroom, hallway, sistersRoom, livingRoom, wc, wc2, outside, window,
            office, kitchen, entrance, conservatory, basement, garage, masterBedroom;
    private ArrayList<Room> rooms;
    private HighscoreDatabase highscoreDB;

    /*
    This is the constructor where all the rooms, items, fire, smoke and 
    highscore database is created by using the methods inside the class.
     */
    public Game() {
        createRooms();
        createItems();
        createFire();
        createSmoke();
        player = new Player(bedroom);
        rooms = new ArrayList(Arrays.asList(bedroom, hallway, sistersRoom, livingRoom, wc, wc2, outside, window,
                office, kitchen, entrance, conservatory, basement, garage, masterBedroom));
        highscoreDB = new HighscoreDatabase();
    }

    /*
    This method creates different rooms and grants them different parameters.
    These parameters are unique for each room.
    
    This method also uses other methods to give each room different functionality
    such as granting the room water, exits, a lock in the room or a room
    specially for game over e.g when you enter "window" which should be a dead path.
     */
    private void createRooms() {
        bedroom = new Room("Bedroom", "in your smokefilled bedroom and you hear the fire cracking", "Imgs/bedroom.png");
        hallway = new Room("Hallway", "in the hallway with doors to your sisters room, the toilet and downstairs.", "Imgs/hallway.png");
        sistersRoom = new Room("Sisters Room", "in your sister's room", "Imgs/sistersRoom.png");
        livingRoom = new Room("Living Room", "in the living room", "Imgs/livingroom.png");
        wc = new Room("Toilet", "on the toilet", "Imgs/bathroom.png");
        wc2 = new Room("Toilet", "on the toilet", "Imgs/bathroom2.png");
        outside = new Room("Outside", "outside", "Imgs/outside.png");
        window = new Room("Window", "jumping out of the window! \nYou took a fatal hit to your head", "Imgs/bedroom.png");
        office = new Room("Office", "in the office", "Imgs/office.png");
        kitchen = new Room("Kitchen", "in the kitchen", "Imgs/kitchen.png");
        entrance = new Room("Entrance", "in the entrace", "Imgs/entrance.png");
        conservatory = new Room("Conservatory", "in the conservatory", "Imgs/conservatory.png");
        basement = new Room("Basement", "in the basement", "Imgs/basement.png");
        garage = new Room("Garage", "in the garage", "Imgs/garage.png");
        masterBedroom = new Room("Master Bedroom", "in the master bedroom", "Imgs/masterbedroom.png");

        bedroom.setExit("east", hallway);
        bedroom.setExit("north", window);

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

        wc.setHasWater();
        wc2.setHasWater();

        outside.setLocked();

        //Sætter rum man skal nå for at vinde:
        outside.setGameComplete();

        //Sætter rum hvor spillet tabes så snart man går derind
        window.setGameOver();
    }

    /*
    This method creates different items and grants them different parameters.
    These parameters are unique for each item.
    
    This method also adds these items to their specific room, so the rooms
    will have these item in them.
    */
    private void createItems() {
        bucket = new Bucket("Bucket", "Holds liquid well.", "Imgs/EmptyBucket.png", 430, 340);
        toothbrush = new NonUseableItem("Toothbrush", "Makes your teeth shiny.", "Imgs/Toothbrush.png", 400, 50);
        smallFireExtinguisher = new SmallFireExtinguisher("SmallFireExtinguisher1", "Used to extinguish small fire.", "Imgs/SmallFireExtinguisher.png", 50, 120);
        smallFireExtinguisher2 = new SmallFireExtinguisher("SmallFireExtinguisher2", "Used to extinguish small fire.", "Imgs/SmallFireExtinguisher.png", 120, 120);
        bigFireExtinguisher = new BigFireExtinguisher("BigFireExtinguisher", "Used to extinguish big fire.", "Imgs/BigFireExtinguisher.png", 300, 300);
        towel = new NonUseableItem("Towel", "Used to dry yourself", "Imgs/Towel.png", 150, 200);
        doll = new Doll("Doll", "A girly play doll", "Imgs/Doll.png", 400, 400);
        key = new Key("Key", "Used to unlock things.", "Imgs/Key.png", 200, 200);
        football = new NonUseableItem("Football", "A round toy used to being kicked", "Imgs/Football.png", 200, 100);
        yankieBar = new YankieBar("YankieBar", "This delicious caramel, nougat, and milk chocolate bar is a great way to restore your Health!", "Imgs/YankieBar.png", 280, 70);
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

    // This method is used to add fire to a specific room.
    private void createFire() {
        kitchen.addFire(3);
        office.addFire(1);
        livingRoom.addFire(1);
    }

    // This method is used to add smoke to a specific room.
    private void createSmoke() {
        hallway.addSmoke(1);
        basement.addSmoke(1);
    }

    public Player getPlayer() {
        return player;
    }

    /*
    This method updates the fire in each room based on the amount of steps
    the player have taken.
     */
    public String updateFire() {
        if (player.getStepCount() % 5 == 0) {
            for (Room room : rooms) {
                room.raiseFireLvl();
            }
            return "\nYou feel the fire inside the buidling getting worse...";
        }
        return "";
    }

    public HighscoreDatabase getHighscoreDatabase() {
        return highscoreDB;
    }

    // This method saves the players highscore.
    public void saveHighscore() {
        highscoreDB.saveHighscore(player);
    }

    // This method updates the players highscore.
    public void highscoreUpdater(Player player) {
        player.getPlayerScore();
    }
}
