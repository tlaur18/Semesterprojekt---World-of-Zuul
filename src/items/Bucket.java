/**
 *
 * The Bucket is a subclass of UseableItem.
 * The player is able to pick the bucket up and use it in rooms that contain water.
 * This fills the bucket. A filled bucket is able to lower the level of fire by one.
 *
 * @author Alexander Nguyen, Jacob Wowk, Morten K. Jensen, Thomas S. Laursen
 * @version 2018.12.14
 *
 */
package items;

import javafx.scene.image.Image;
import worldofzuul.Player;

public class Bucket extends UseableItem {

    private boolean isFilled;
    private boolean isUsed;
    private int lvl;
    private int itemScore;

    /*
    * This constructor takes the same parametres as Item.
    * Here the attributes are initialized.
    * isFilled tells if the bucket is filled with water.
    * isUsed tells if the bucket has been used to put out fire.
    * lvl tells how effective it performs against Fire.
    * Itemscore tells how many points the player gets by using this Item.
     */
    public Bucket(String name, String description, String imgURL, int imgX, int imgY) {
        super(name, description, imgURL, imgX, imgY);
        isFilled = false;
        isUsed = false;
        lvl = 1;
        itemScore = 450;
    }

    /*
    * Bucket Overrides the abstract method 'use' from UseableItem.
    * Returns the outcome of the this action to be printed to the user.
     */
    @Override
    public String use(Player player) {
        String outputText = "";
        Image emptyBucket = new Image("/worldofzuul/res/EmptyBucket.png");
        Image filledBucket = new Image("/worldofzuul/res/FilledBucket.png");
        Image meltedBucket = new Image("/worldofzuul/res/MeltedBucket.png");

        //This if-statement makes sure that you are only able to use the bucket once.
        if (!isUsed) {
            //This if-statement is enteren when the player stands in a room with water, like the wc.
            if (player.getCurrentRoom().hasWater()) {
                if (!isFilled) {
                    getImage().setImage(filledBucket);
                    outputText = "\nYou fill the bucket with water from the sink.";
                    isFilled = true;
                    player.updateHighscore(itemScore);
                } else {
                    outputText = "\nThe bucket is already full.";
                }
            } else if (player.getCurrentRoom().getFire() == null) {  // This else-if-statement is entered when the player uses a bucket in a room withot water or fire.
                if (isFilled) {
                    outputText = "\nYou emptied the bucket in a room with no fire!";
                    getImage().setImage(emptyBucket);
                    isFilled = false;
                    player.updateHighscore(-500);
                } else {
                    outputText = "\nYour bucket is empty!";
                }
            }

            //This if-statement is entered when the player uses the bucket inside a Room with Fire 
            if (player.getCurrentRoom().getFire() != null) {
                //The fire is put out if the bucket contains water.
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
