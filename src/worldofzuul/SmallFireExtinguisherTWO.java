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
public class SmallFireExtinguisherTWO extends UseableItems {
    boolean isEmpty = false;

    public SmallFireExtinguisherTWO(String name, String description) {
        super(name, description);
    }

    @Override
    public void use(Player player) {
        if (!(isEmpty)) {
            if (player.getCurrentRoom().getFire() != null) {

                System.out.println("You used the fireextinguisher and put out the fire.");
                player.getCurrentRoom().removeFire();
                isEmpty = true;
            } else {
                isEmpty = true;
                System.out.println("You emptied the fireextinguisher in a room with no fire...\nWhat a waste!");
            }
        } else if (isEmpty) {
            System.out.println("This Fireextinguisher is empty!");
        }
    }
}
