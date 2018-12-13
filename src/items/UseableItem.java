package items;

import worldofzuul.Player;

public abstract class UseableItem extends Item{
    
    public UseableItem(String name, String description, String imgURL, int imgX, int imgY) {
        super(name, description, imgURL, imgX, imgY);
    }
    
    public abstract String use(Player player);
}
