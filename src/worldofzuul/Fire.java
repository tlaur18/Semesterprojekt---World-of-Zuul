package worldofzuul;

import javafx.scene.image.Image;

public class Fire {

    private int lvl;
    public static final Image IMAGE_FIRE = new Image("imgs/Fire.png");

    public Fire(int lvl) {
        this.lvl = lvl;
    }
    
    public int getLvl() {
        return lvl;
    }

    public void raiseLvl() {
        lvl++;
        if (lvl > 3) {
            lvl = 3;
        }
    }
    
    public void lowerLvl(int lvlsToLower) {
        lvl = lvl - lvlsToLower;
    }
}
