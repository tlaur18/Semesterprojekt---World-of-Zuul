package items;

import javafx.scene.image.Image;
import worldofzuul.Player;
import worldofzuul.Room;

public class Bucket extends UseableItem {

    private boolean isFilled;
    private boolean isUsed;
    private int lvl;

    public Bucket(String name, String description, Room r1, Room r2, String imgURL) {
        super(name, description, imgURL);
        isFilled = false;
        isUsed = false;
        lvl = 1;
    }

    @Override
    public String use(Player player) {
        String outputText = "";
        Image filledBucket = new Image("Imgs/FilledBucket.png");
        Image meltedBucket = new Image("Imgs/MeltedBucket.png");

        if (!isUsed) {
            if (player.getCurrentRoom().hasWater()) {
                if (!isFilled) {
                    getImage().setImage(filledBucket);
                    outputText = "\nYou fill the bucket with water from the sink.";
                    isFilled = true;
                } else {
                    outputText = "\nThe bucket is already full.";
                }
            } else if (player.getCurrentRoom().getFire() == null) {
                outputText = "\nNothing interesting happens...";
            }

            if (player.getCurrentRoom().getFire() != null) {
                if (isFilled) {
                    outputText = "\nYou empty the bucket over the fire.";
                    getImage().setImage(meltedBucket);
                    outputText += player.getCurrentRoom().lowerFireLvl(lvl);
                    isFilled = false;
                    isUsed = true;
                } else {
                    outputText = "\nThe empty bucket is no match for this fire.";
                }
            }
        } else {
            outputText = "\nThe bucket got destroyed when you you put out the fire.";
            outputText += "\nYou cant use it anymore";
        }

        return outputText;
    }
}
