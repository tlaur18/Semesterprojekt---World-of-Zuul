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
public class UseableItems extends Items implements Usable{
    
    public UseableItems(String name, String description) {
        super(name, description);
    }

    @Override
    public void use() {
        System.out.println("You used the: " + getName());
    }
    
}
