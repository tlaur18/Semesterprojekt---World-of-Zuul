package items;

import javafx.scene.image.Image;
import worldofzuul.Player;

public class Bucket extends UseableItem {

    private boolean isFilled;
    private boolean isUsed;
    private int lvl;
    private int itemScore;

    public Bucket(String name, String description, String imgURL) {
        super(name, description, imgURL);
        isFilled = false;
        isUsed = false;
        lvl = 1;
        itemScore = 450;
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
                    player.updateHighscore(itemScore);
                } else {
                    outputText = "\nThe bucket is already full.";
                }
            } else if (player.getCurrentRoom().getFire() == null) {
                if (isFilled){
                outputText = "\nYou emptied the bucket in a room with no fire!";
                isFilled = false;
                 player.updateHighscore(-500);
                }
                else {
                    outputText = "\nYour bucket is empty!";
                }
            }

            if (player.getCurrentRoom().getFire() != null) {
                if (isFilled) {
                    outputText = "\nYou empty the bucket over the fire.";
                    getImage().setImage(meltedBucket);
                    outputText += player.getCurrentRoom().lowerFireLvl(lvl);
                    isFilled = false;
                    isUsed = true;
                     player.updateHighscore(itemScore);
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

    public int getItemScore() {
        return itemScore;
    }
}
