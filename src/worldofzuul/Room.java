package worldofzuul;

import java.util.HashMap;
import java.util.ArrayList;
import javafx.scene.image.ImageView;

public class Room {

    private String name;
    private String description;
    private HashMap<String, Room> exits;
    private ArrayList<Item> items = new ArrayList();
    private Fire fire = null;
    private int damage;
    private boolean isLocked;
    private boolean isGameComplete;
    private boolean isGameOver;
    private ImageView img;

    public Room(String name, String description, int damage, boolean isLocked, String imgURL) {
        this.name = name;
        this.damage = damage;
        this.description = description;
        exits = new HashMap<String, Room>();
        this.isLocked = isLocked;
        this.isGameComplete = false;
        this.isGameOver = false;
        img = new ImageView(imgURL);
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

    public int getDamage() {
        return damage;
    }

    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    public String getShortDescription() {
        return description;
    }

    public String getLongDescription() {
        return ("You are " + description + ".");
    }

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

    public void updateFire() {
        if (fire != null) {
            fire.updateLvl();
        }
    }

    public void unlockRoom() {
        this.isLocked = false;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public ImageView getImage() {
        return img;
    }
}
