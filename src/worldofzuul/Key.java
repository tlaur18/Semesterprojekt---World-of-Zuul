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
public class Key extends UseableItems {

    private Room[] useableRooms;

    public Key(String name, String description, Room r1) {
        super(name, description);
        useableRooms = new Room[1];
        useableRooms[0] = r1;
    }

    @Override
    public void use(Player player) {
        System.out.println("To use this key, you need to go to the locked door!");
        
    }

}
