/*
 * this class is a specific item and is a sub-class of useable item.

 * @author Alexander Nguyen, Jacob Wowk, Morten K. Jensen and Thomas S. Laursen
 * @version 2018.12.14

 */
package items;

import worldofzuul.Player;


public class BigFireExtinguisher extends UseableItem {

    private boolean isEmpty;
    private int lvl;
    private int itemScore;
    /* 
        the contructor is a extend of useableItems and has been given addional attributes
        isEmpty is set to not be empty and there for i can be used.
        lvl is set to 3 so it can handle the bigger fire in the kitchen.
        itemScore is the point given to your score if used in the right situration
    
    */
    public BigFireExtinguisher(String name, String description, String imgURL, int imgX, int imgY) {
        super(name, description, imgURL, imgX, imgY);
        isEmpty = false;
        lvl = 3;
        itemScore = 700;
    }
    /*
        here we override the abstract method in useableItem, at give it the functions that is needed. 
        we initialize the OutputText, and make a if statement and another if statemanet inside it. 
    */
    @Override
    public String use(Player player) {
        String outputText;
        /*
            if the BigFireExtinguisher's isEmpty is false, it calls for another if statement that 
            checks if the player is inside a room where there is a fire. 
            if there is it will put the fire out and set your isEmpty to true.
            if you aint in a room with fire, you will set your isEmpty to true and you will get 
            negative itemScore for wasting it.
            if you know use it printout is is empty.
            
        
        */
        if (!isEmpty) {
            if (player.getCurrentRoom().getFire() != null) {
                outputText = "\nYou used the fireextinguisher on the fire.";
                outputText += player.getCurrentRoom().lowerFireLvl(lvl);
                isEmpty = true;
                 player.updateHighscore(itemScore);
            } else {
                isEmpty = true;
                outputText = "\nYou emptied the fireextinguisher in a room with no fire.\nWhat a waste!";
                player.updateHighscore(-600);
            }
        } else {
            outputText = "\nThis Fireextinguisher is empty.";
        }
        return outputText;
    }
    
    public int getItemScore(){
        return itemScore;
    }
}
