package worldofzuul;

public class Bucket extends UseableItem {

    private boolean isFilled;
    private Room[] useableRooms;
    private boolean isUsed;

    public Bucket(String name, String description, Room r1, Room r2) {
        super(name, description);
        isFilled = false;
        isUsed = false;
        useableRooms = new Room[2];
        useableRooms[0] = r1;
        useableRooms[1] = r2;
    }

    @Override
    public void use(Player player) {
        if (!(isUsed)) {
        for (int i = 0; i < useableRooms.length; i++) {
            if (player.getCurrentRoom().equals(useableRooms[i])) {
                if (!isFilled) {
                    System.out.println("You fill the bucket with water from the sink.");
                    isFilled = true;
                    break;
                } else {
                    System.out.println("The bucket is already full.");
                    break;
                } 
            } else if (i == useableRooms.length - 1 && player.getCurrentRoom().getFire() == null) {
                System.out.println("Nothing interesting happens...");
            }
        }
        
        if (player.getCurrentRoom().getFire() != null) {
            if (isFilled) {
                System.out.println("You empty the bucket over the fire and it goes out.");
                player.getCurrentRoom().removeFire();
                isFilled = false;
                isUsed = true;
            } else {
                System.out.println("The empty bucket is no match for this fire.");
            }
        }
    }
        else {
            System.out.println("The bucket got destroyed when you you put out the fire.\n"
                    + "You cant use it anymore");
        }
    }

}
