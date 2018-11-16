package worldofzuul;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class YankieBar extends UseableItem {

    public YankieBar(String name, String description) {
        super(name, description);
    }

    @Override
    public void use(Player player) {
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
        System.out.println("Your health has been restored to 100.");
        player.removeItem();
        player.setHealth(100);
    }
}
