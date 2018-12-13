/**
 *
 * @author Alexander Nguyen, Jacob Wowk, Morten K. Jensen and Thomas S. Laursen
 * @version 2018.12.14
 * 
 */

package worldofzuul.domain.item;

import worldofzuul.domain.Player;

public abstract class UseableItem extends Item{
    
    public UseableItem(String name, String description, String imgURL) {
        super(name, description, imgURL);
    }
    
    public abstract String use(Player player);
}
