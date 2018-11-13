package worldofzuul;

public class Key extends UseableItems {

    private Room[] useableRooms;

    public Key(String name, String description, Room r1) {
        super(name, description);
        useableRooms = new Room[1];
        useableRooms[0] = r1;
    }

    @Override
    public void use(Player player) {
        System.out.println("To use this key, you need to go to the locked door!");
        
    }

}
