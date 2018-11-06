package worldofzuul;

public class Fire {
    private int lvl;    
    
    public Fire(int lvl) {
        this.lvl = lvl;
    }
    
    public int getLvl() {
        return lvl;
    }
    
    public void updateFire() {
        if (Player.getStepCount() % 5 == 0) {
            lvl++;
        }
    }
}
