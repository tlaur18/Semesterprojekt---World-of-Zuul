/*
 * The non-useable items is the items that dont have a purpose, and therefore havent given a use function. 
 * It is a sub-class from item.

 * @author Alexander Nguyen, Jacob Wowk, Morten K. Jensen and Thomas S. Laursen
 * @version 2018.12.14

 */
package items;

public class NonUseableItem extends Item {
    
    public NonUseableItem(String name, String description, String imgURL, int imgX, int imgY) {
        super(name, description, imgURL, imgX, imgY);
    }
    
}
