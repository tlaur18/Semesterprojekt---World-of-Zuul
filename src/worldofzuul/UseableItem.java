package worldofzuul;

public abstract class UseableItem extends Item{
    
    public UseableItem(String name, String description, String imgURL) {
        super(name, description, imgURL);
    }
    
    public abstract String use(Player player);
}
