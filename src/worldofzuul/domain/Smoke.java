/**
 *
 * @author Alexander Nguyen, Jacob Wowk, Morten K. Jensen and Thomas S. Laursen
 * @version 2018.12.14
 * 
 */

package worldofzuul.domain;

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
