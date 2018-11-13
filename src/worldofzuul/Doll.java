package worldofzuul;

public class Doll extends UseableItems {

    public Doll(String name, String description) {
        super(name, description);
    }

    @Override
    public void use(Player player) {
        System.out.println("You play with the doll, but get bored quick.");
    }
    
    
}
