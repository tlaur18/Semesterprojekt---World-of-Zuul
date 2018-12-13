/**
 *
 * This class is created to retain all the known and created command words in
 * the CommandWord-class and have methods that is specially made for inputs.
 * 
 * @author Alexander Nguyen, Jacob Wowk, Morten K. Jensen and Thomas S. Laursen
 * @version 2018.12.14
 * 
 */

package worldofzuul;

import java.util.HashMap;

public class CommandWords {

    private HashMap<String, CommandWord> validCommands;

    
    /*
    The constructor has a HashMap with a for-each loop that checks through
    the CommandWord-class and checks all its values/enum commands and puts all
    the commands/values into the HashMap.
    */
    public CommandWords() {
        validCommands = new HashMap<String, CommandWord>();
        for (CommandWord command : CommandWord.values()) {
            validCommands.put(command.toString(), command);
        }
    }

    // This method is used to get the String input from the player.

    public CommandWord getCommandWord(String commandWord) {
        CommandWord command = validCommands.get(commandWord);
        return command;
    }

    /* 
    This method checks if the player input is a key for one of 
    the command words.
    */
    public boolean isCommand(String aString) {
        return validCommands.containsKey(aString);
    }
}
