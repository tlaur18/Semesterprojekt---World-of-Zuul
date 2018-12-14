/**
 *
 * This is a class created specially for commands where a constructor is created
 * and can be called and used by other classes.
 * 
 * @author Alexander Nguyen, Jacob Wowk, Morten K. Jensen and Thomas S. Laursen
 * @version 2018.12.14
 * 
 */

package worldofzuul;

public class Command {

    private CommandWord commandWord;
    private String secondWord;

    /*
    The constructor is created by using a CommandWord and a String.
    */
    public Command(CommandWord commandWord, String secondWord) {
        this.commandWord = commandWord;
        this.secondWord = secondWord;
    }

    public CommandWord getCommandWord() {
        return commandWord;
    }

    public String getSecondWord() {
        return secondWord;
    }
    
    public boolean hasSecondWord() {
        return (secondWord != null);
    }
}
