/*
@author Alexander Nguyen, Jacob Wowk, Morten K. Jensen and Thomas S. Laursen
* @version 2018.12.14

Class for making and controlling fire object.
*/

package worldofzuul;

import javafx.scene.image.Image;

public class Fire {

    private int lvl;
    //Fire image URL
    public static final Image IMAGE_FIRE = new Image("imgs/Fire.png");
    /*
    Constructor for Fire wich set the fire level.
    */
    public Fire(int lvl) {
        this.lvl = lvl;
    }
    
    public int getLvl() {
        return lvl;
    }
    
    /*
    Method to raise Fire level, with an if statement ot make sure the fire
    never excides lvl 3.
    */
    public void raiseLvl() {
        lvl++;
        if (lvl > 3) {
            lvl = 3;
        }
    }
    /*
    Method to lower the fire level according to the int argument.
    */
    public void lowerLvl(int lvlsToLower) {
        lvl = lvl - lvlsToLower;
    }
}
