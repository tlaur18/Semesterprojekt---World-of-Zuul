package worldofzuul;

public class Doll extends UseableItem {

    public Doll(String name, String description) {
        super(name, description);
    }

    @Override
    public String use(Player player) {
        String outputText = "";
        
        outputText = "You play with the doll, but quickly get bored.";
        outputText += "\nYou realize that you probably shoudl focus on getting to safety.";
        
        return outputText;
    }
    
    
}
