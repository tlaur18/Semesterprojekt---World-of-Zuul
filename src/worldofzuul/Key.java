package worldofzuul;

public class Key extends UseableItem {

    public Key(String name, String description, String imgURL) {
        super(name, description, imgURL);
    }

    @Override
    public String use(Player player) {
        for (Room room : player.getCurrentRoom().getExits().values()) {
            if (room.isLocked()) {
                room.unlockRoom();
                return "\nYou are able to unlock the door to the " + room.getName() + " with your key.";
            }
        }
        return "\nThis key can not be used on any of the doors around you.";
    }

}
