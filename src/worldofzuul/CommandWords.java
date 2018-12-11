package worldofzuul;

import java.util.HashMap;

public class CommandWords {

    private HashMap<String, CommandWord> validCommands;

    public CommandWords() {
        validCommands = new HashMap<String, CommandWord>();
        for (CommandWord command : CommandWord.values()) {
            validCommands.put(command.toString(), command);
        }
    }

    public CommandWord getCommandWord(String commandWord) {
        CommandWord command = validCommands.get(commandWord);
        return command;
    }

    public boolean isCommand(String aString) {
        return validCommands.containsKey(aString);
    }
}
