/**
 *
 * NameInputException is thrown when the player enter an invalid sign.
 * 
 * @author Alexander Nguyen, Jacob Wowk, Morten K. Jensen and Thomas S. Laursen
 * @version 2018.12.14
 * 
 */

package exceptions;

public class NameInputException extends Exception{
    
    public NameInputException() {
        super("You cant input an ','!");
    }
}
