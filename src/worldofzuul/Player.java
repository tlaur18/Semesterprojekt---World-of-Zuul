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

    int stepCount = 0;
    int health = 100;
    

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
    public void looseHealth() {
        health = health - 50;
    }
    

}
