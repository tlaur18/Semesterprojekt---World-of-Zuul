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
