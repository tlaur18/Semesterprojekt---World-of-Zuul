/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Morten K. Jensen
 */
public class YankieBar extends UseableItems {
    
    private boolean isUsed = false;
    

    public YankieBar(String name, String description) {
        super(name, description);
    }

    @Override
    public void use(Player player) {
        if (!(isUsed)) {
            System.out.println("*OM NOM NOM*");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(YankieBar.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("*OM NOM NOM*");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(YankieBar.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("*OM NOM NOM*");
        player.setHealth(100);
        isUsed = true;
        }
        else {
            System.out.println("You already ate the Yankie bar, and throw away the paper");
            
            player.removeItem();
        }
    }

}

   
