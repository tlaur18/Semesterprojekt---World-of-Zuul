package items;

import javafx.scene.image.Image;
import worldofzuul.Player;
import worldofzuul.Room;

public class Bucket extends UseableItem {

    private boolean isFilled;
    private Room[] useableRooms;
    private boolean isUsed;

    public Bucket(String name, String description, Room r1, Room r2, String imgURL) {
        super(name, description, imgURL);
        isFilled = false;
        isUsed = false;
        useableRooms = new Room[2];
        useableRooms[0] = r1;
        useableRooms[1] = r2;
    }

    @Override
    public String use(Player player) {
        String outputText = "";
        Image filledBucket = new Image("Imgs/FilledBucket.png");
        Image meltedBucket = new Image("Imgs/MeltedBucket.png");

        if (!(isUsed)) {
            for (int i = 0; i < useableRooms.length; i++) {
                if (player.getCurrentRoom().equals(useableRooms[i])) {
                    if (!isFilled) {
                        getImage().setImage(filledBucket);
                        outputText = "\nYou fill the bucket with water from the sink.";
                        isFilled = true;
                        break;
                    } else {
                        outputText = "\nThe bucket is already full.";
                        break;
                    }
                } else if (i == useableRooms.length - 1 && player.getCurrentRoom().getFire() == null) {
                    outputText = "\nNothing interesting happens...";
                }
            }

            if (player.getCurrentRoom().getFire() != null) {
                if (isFilled) {
                    outputText = "\nYou empty the bucket over the fire and it goes out.";
                    getImage().setImage(meltedBucket);
                    player.getCurrentRoom().removeFire();
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
