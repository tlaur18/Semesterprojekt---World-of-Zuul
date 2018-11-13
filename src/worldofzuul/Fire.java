package worldofzuul;

public class Fire {

    private int lvl;

    public Fire(int lvl) {
        this.lvl = lvl;
    }

    public int getLvl() {
        return lvl;
    }

    public void updateLvl(Player player) {
        lvl++;
        if (lvl > 3) {
            lvl = 3;
        }
    }
}
