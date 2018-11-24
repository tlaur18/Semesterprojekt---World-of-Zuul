package worldofzuul;

public abstract class UseableItem extends Item{
    
    public UseableItem(String name, String description) {
        super(name, description);
    }
    
    public abstract String use(Player player);
}
