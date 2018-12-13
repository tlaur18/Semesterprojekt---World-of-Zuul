package items;

import worldofzuul.Player;
import worldofzuul.Room;

public class Key extends UseableItem {

    private int itemScore;

    public Key(String name, String description, String imgURL) {
        super(name, description, imgURL);
        itemScore = 740;
    }

    @Override
    public String use(Player player) {
        for (Room room : player.getCurrentRoom().getExits().values()) {
            if (room.isLocked()) {
                room.unlockRoom();
                player.updateHighscore(itemScore);
                return "\nYou are able to unlock the door to the " + room.getName() + " with your key.";
            }
        }
        return "\nThis key can not be used on any of the doors around you.";
    }

    public int getItemScore() {
        return itemScore;
    }
}
