package worldofzuul;

import items.Item;
import java.util.HashMap;
import java.util.ArrayList;
import javafx.scene.image.ImageView;

public class Room {

    private String name;
    private String description;
    private HashMap<String, Room> exits;
    private ArrayList<Item> items = new ArrayList();
    private Fire fire = null;
    private Smoke smoke = null;
    private boolean isLocked;
    private boolean isGameComplete;
    private boolean isGameOver;
    private ImageView img;
    private boolean hasWater;
    
    public Room(String name, String description, String imgURL) {
        this.name = name;
        this.description = description;
        exits = new HashMap<String, Room>();
        this.isGameComplete = false;
        this.isGameOver = false;
        img = new ImageView(imgURL);
        hasWater = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGameComplete() {
        isGameComplete = true;
    }

    public boolean getGameComplete() {
        return isGameComplete;
    }

    public void setGameOver() {
        isGameOver = true;
    }

    public boolean getGameOver() {
        return isGameOver;
    }
    //neighbor  ?? 
    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    public String getShortDescription() {
        return description;
    }

    public String getLongDescription() {
        return ("You are " + description + ".");
    }
    // ? hvad var det nu det betød?
    public String getItemDescription() {
        return (!items.isEmpty() ? "Items: " + getItemsString() : "There are no items in this room.");
    }

    public HashMap<String, Room> getExits() {
        return exits;
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public String getItemsString() {
        String itemString = "";

        for (int i = 0; i < items.size(); i++) {
            itemString += items.get(i).getName() + (i + 1 == items.size() ? "" : ", ");
        }

        return itemString;
    }
    
    public void addFire(int lvl) {
        fire = new Fire(lvl);
    }

    public Fire getFire() {
        return fire;
    }

    public void removeFire() {
        fire = null;
    }
   
    public void raiseFireLvl() {
        if (fire != null) {
            fire.raiseLvl();
        }
    }
    
    public String lowerFireLvl(int lvlsToLower) {
        fire.lowerLvl(lvlsToLower);
        if (fire.getLvl() <= 0) {
            fire = null;
            return " The fire was put out.";
        }
        return " The fire was weakened.";
    }
    
    public void addSmoke(int lvl) {
        smoke = new Smoke(lvl);
    }
    
    public Smoke getSmoke() {
        return smoke;
    }

    public void unlockRoom() {
        this.isLocked = false;
    }

    public boolean isLocked() {
        return isLocked;
    }
    
    public void setLocked() {
        isLocked = true;
    }

    public ImageView getImage() {
        return img;
    }
    
    public void setHasWater() {
        hasWater = true;
    }
    
    public boolean hasWater() {
        return hasWater;
    }
}
