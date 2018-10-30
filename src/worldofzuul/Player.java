/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul;

/**
 *
 * @author Morten K. Jensen
 */
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
        health -= 25;
        return health;
    }

    public int lostHealth() {
        return this.lostHealth;
    }
    
    public boolean isDead() {
        if (health <= 0) {
            return true;
        } else {
            return false;
        }
    }
}
