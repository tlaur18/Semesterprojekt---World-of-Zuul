package worldofzuul;

public class BigFireExtinguisher extends UseableItem {

    boolean isEmpty = false;
    private Room[] useableRooms;

    public BigFireExtinguisher(String name, String description, Room r1) {
        super(name, description);
        useableRooms = new Room[1];
        useableRooms[0] = r1;
    }

    @Override
    public String use(Player player) {
        String outputText = "";
        if (!isEmpty) {
            if (player.getCurrentRoom().getFire() != null) {

                for (int i = 0; i < useableRooms.length; i++) {
                    if (player.getCurrentRoom().equals(useableRooms[i])) {

                        outputText = "You used the fireextinguisher and put out the fire.";
                        player.getCurrentRoom().removeFire();
                        isEmpty = true;
                    } else {
                        outputText = "This small fireextinguisher is no match for that big fire!";
                        isEmpty = true;
                    }
                }
            } else {
                isEmpty = true;
                outputText = "You emptied the fireextinguisher in a room with no fire...\nWhat a waste!";
            }
        } else {
            outputText = "This Fireextinguisher is empty!";
        }
        return outputText;
    }
}
