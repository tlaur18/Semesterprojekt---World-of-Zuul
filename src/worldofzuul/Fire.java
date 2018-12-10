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

    public void updateLvl() {
        lvl++;
        if (lvl > 3) {
            lvl = 3;
        }
    }
}
