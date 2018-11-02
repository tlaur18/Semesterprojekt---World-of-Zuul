package worldofzuul;

import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

public class Room {

    private String description;
    private HashMap<String, Room> exits;
    private ArrayList<Item> items = new ArrayList();

    public Room(String description) {
        this.description = description;
        exits = new HashMap<String, Room>();
    }

    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    public String getShortDescription() {
        return description;
    }

    public String getLongDescription() {
        return ("You are " + description + ".\n" + getExitString() + ".") + (!items.isEmpty() ? "\nItems: " + printItems() : "");
    }

    private String getExitString() {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for (String exit : keys) {
            returnString += " " + exit;
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
}
