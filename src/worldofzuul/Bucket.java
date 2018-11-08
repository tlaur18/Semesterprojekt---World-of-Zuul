package worldofzuul;

public class Bucket extends UseableItems {
    
    private boolean isFilled;
    private Room[] useableRooms;
    private Player player;
    
    public Bucket(String name, String description, Room r1, Room r2, Player player){
        super(name, description);
        isFilled = false;
        useableRooms = new Room[2];
        useableRooms[0] = r1;
        useableRooms[1] = r2;
        this.player = player;
    }

    @Override
    public void use() {        
        if (!isFilled) {
            for (int i = 0; i < useableRooms.length; i++) {
                if (player.getCurrentRoom().equals(useableRooms[i])) {
                    System.out.println("Ye boi");
                    break;
                }
            }
        }
    }

}
