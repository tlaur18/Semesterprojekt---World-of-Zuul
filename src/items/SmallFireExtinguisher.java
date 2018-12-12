package items;

import worldofzuul.Player;

public class SmallFireExtinguisher extends UseableItem {

    private boolean isEmpty;
    private int lvl;

    public SmallFireExtinguisher(String name, String description, String imgURL) {
        super(name, description, imgURL);
        isEmpty = false;
        lvl = 2;
    }

    @Override
    public String use(Player player) {
        String outputText = "";

        if (!(isEmpty)) {
            if (player.getCurrentRoom().getFire() != null) {
                outputText = "\nYou used the fireextinguisher on the fire.";
                outputText += player.getCurrentRoom().lowerFireLvl(lvl);
                isEmpty = false;
            } else {
                isEmpty = true;
                outputText = "\nYou emptied the fireextinguisher in a room with no fire...";
                outputText += "\nWhat a waste!";
            }
        } else {
            outputText = "\nThis Fireextinguisher is empty!";
        }
        return outputText;
    }
}
