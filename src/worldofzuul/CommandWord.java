/**
 *
 * This class/enum is created for command words which is the input from the player
 * that is used to control and play the game.
 * 
 * @author Alexander Nguyen, Jacob Wowk, Morten K. Jensen and Thomas S. Laursen
 * @version 2018.12.14
 * 
 */

package worldofzuul;

public enum CommandWord {
    GO("go"), HELP("help"), TAKE("take"), DROP("drop"), INSPECT("inspect"), USE("use");
    
    // This method is made so the command words can be read and used as strings.
    private String commandString;

    CommandWord(String commandString) {
        this.commandString = commandString;
    }
    /*
    Without this toString method the command word string can not be called or
    read by the program.
    */
    public String toString() {
        return commandString;
    }
}
