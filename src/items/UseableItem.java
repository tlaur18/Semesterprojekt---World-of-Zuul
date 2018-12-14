/*
  * This class is a sub-class of Item, it is here we force
  * the sub-classes to Overrride the use method 
  *
  * @author Alexander Nguyen, Jacob Wowk, Morten K. Jensen and Thomas S. Laursen
  * @version 2018.12.14
  *
  */
package items;

import worldofzuul.Player;

public abstract class UseableItem extends Item{
   
    public UseableItem(String name, String description, String imgURL, int imgX, int imgY) {
        super(name, description, imgURL, imgX, imgY);
    }
    /**
      * The abstract method use is declared.
      * The return type is a string meant to describe the outcome of the method
      * It uses the player as argument.
      */
    public abstract String use(Player player);
}
