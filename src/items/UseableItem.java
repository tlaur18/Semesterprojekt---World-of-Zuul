/*
 * this class is a sub-class of Item, it is here we implement a use function for all the items. 

 * @author Alexander Nguyen, Jacob Wowk, Morten K. Jensen and Thomas S. Laursen
 * @version 2018.12.14

 */
package items;

import worldofzuul.Player;

public abstract class UseableItem extends Item{
    //here we take the conctructor from the item and implement it to the useableItem constructor.
    public UseableItem(String name, String description, String imgURL, int imgX, int imgY) {
        super(name, description, imgURL, imgX, imgY);
    }
    //here we make the abstract method for all the items inside the Useable-items.
    public abstract String use(Player player);
}
