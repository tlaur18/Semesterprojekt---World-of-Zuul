package worldofzuul.domain;

import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

public class Room {

    private String description;
    private HashMap<String, Room> exits;
    private ArrayList<Item> items = new ArrayList();
    private Fire fire = null;
    private int damage;
    private boolean isLocked;

    public Room(String description, int damage,boolean isLocked) {
        this.damage = damage;
        this.description = description;
        exits = new HashMap<String, Room>();
        this.isLocked = isLocked;
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
        return (!items.isEmpty() ? "Items: " + printItems() : "There are no items in this room.");
    }

    public String getExitString() {
        String returnString = "EXITS: | ";
        Set<String> keys = exits.keySet();
        for (String exit : keys) {
            returnString += exit + " | ";
        }
        return returnString;
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

    public String printItems() {
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
}
