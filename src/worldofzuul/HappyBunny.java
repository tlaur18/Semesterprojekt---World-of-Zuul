package worldofzuul;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HappyBunny extends UseableItems {

    public HappyBunny(String name, String description) {
        super(name, description);
    }

    @Override
    public void use(Player player) {
        System.out.println("*PET*");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(HappyBunny.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("*PET*");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(HappyBunny.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("*PET*");
        player.setHealth(100);
        System.out.println("You got 100 health!");
    }
}
