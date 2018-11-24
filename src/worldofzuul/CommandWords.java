package worldofzuul;

import java.util.HashMap;

public class CommandWords {

    private HashMap<String, CommandWord> validCommands;

    public CommandWords() {
        validCommands = new HashMap<String, CommandWord>();
        for (CommandWord command : CommandWord.values()) {
            if (command != CommandWord.UNKNOWN) {
                validCommands.put(command.toString(), command);
            }
        }
    }

    public CommandWord getCommandWord(String commandWord) {
        CommandWord command = validCommands.get(commandWord);
        if (command != null) {
            return command;
        } else {
            return CommandWord.UNKNOWN;
        }
    }

    public boolean isCommand(String aString) {
        return validCommands.containsKey(aString);
    }

    public String getCommandsString() {
        String commandsString = "";

        commandsString += "\t'go'      |   Move to a new room\n";
        commandsString += "\t'search'  |   Search for items in the current room\n";
        commandsString += "\t'take'    |   Pick up an item in the current room\n";
        commandsString += "\t'drop'    |   Drop the item you are holding\n";
        commandsString += "\t'inspect' |   Get a description of the item you are holding\n";
        commandsString += "\t'exits'   |   Get the exits for the current room\n";
        commandsString += "\t'help'    |   Show the list of available commands and amount of steps walked\n";
        commandsString += "\t'quit'    |   Quit the game\n";

        return commandsString;
    }

}
