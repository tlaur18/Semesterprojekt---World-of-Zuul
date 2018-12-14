/**
 * 
 * This class contains a Smoke-constructor and a method to get the level.
 * It is used to create a smoke-object in the game. At the moment the smoke in
 * the game only contains one level, so it is not necessary to use a level
 * attribute, but it is there for future purposes, because we had an idea of
 * make multiply difficulties for different age range.
 * 
 * An image of the smoke is created inside this class.
 * 
 * @author Alexander Nguyen, Jacob Wowk, Morten K. Jensen and Thomas S. Laursen
 * @version 2018.12.14
 * 
 */
 
package worldofzuul;

import javafx.scene.image.Image;

public class Smoke {

    private int lvl;
    public static final Image IMAGE_SMOKE = new Image("imgs/Smoke.png");

    public Smoke(int lvl) {
        this.lvl = lvl;
    }
    
    public int getLvl() {
        return lvl;
    }

}
