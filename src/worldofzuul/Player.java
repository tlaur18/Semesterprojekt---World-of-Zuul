
package worldofzuul;

public class Player {

    private int stepCount = 0;
    private int health = 100;
    private final int lostHealth = 25;


    public Player() {
    }
    
    public int getStepCount() {
        return stepCount;
    }

    public int getHealth() {
        if (health < 0) {
            health = 0;
        }
        return health;
    }

    public void setStepCount(int stepCount) {
        this.stepCount = stepCount;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int addStep() {
        stepCount = stepCount + 1;
        return stepCount;
    }

    public int looseHealth() {
        health -= lostHealth;
        return health;
    }

    public int lostHealth() {
        return lostHealth;
    }
    
    public boolean isDead() {
        if (health <= 0) {
            return true;
        } else {
            return false;
        }
    }
}
