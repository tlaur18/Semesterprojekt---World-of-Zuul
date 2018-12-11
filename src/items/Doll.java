package items;

import worldofzuul.Player;

public class Doll extends UseableItem {

    public Doll(String name, String description, String imgURL) {
        super(name, description, imgURL);
    }

    @Override
    public String use(Player player) {
        String outputText = "";
        
        outputText = "\nYou play with the doll, but quickly get bored.";
        outputText += "\nYou realize that you probably shoudl focus on getting to safety.";
        
        return outputText;
    }
    
    
}
