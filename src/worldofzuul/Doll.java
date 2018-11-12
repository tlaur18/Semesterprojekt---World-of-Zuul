/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul;

/**
 *
 * @author Firefighter 007
 */
public class Doll extends UseableItems {

    public Doll(String name, String description) {
        super(name, description);
    }

    @Override
    public void use(Player player) {
        System.out.println("You play with the doll, but get bored quick.");
    }
    
    
}
