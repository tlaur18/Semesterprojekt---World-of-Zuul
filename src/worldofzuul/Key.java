package worldofzuul;

public class Key extends UseableItem {

    public Key(String name, String description) {
        super(name, description);
    }

    @Override
    public void use(Player player) {
        System.out.println("To use this key, you need to go to the locked door!");
    }

}
