package worldofzuul;

public class YankieBar extends UseableItem {

    public YankieBar(String name, String description, String imgURL) {
        super(name, description, imgURL);
    }

    @Override
    public String use(Player player) {
        String outputText = "";
        
        outputText = "*OM NOM NOM*";
        outputText += "\nYour health has been restored to 100.";
        
        player.removeItem();
        player.setHealth(100);
        
        return outputText;
    }
}
