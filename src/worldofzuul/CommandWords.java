package worldofzuul;

import java.util.HashMap;

public class CommandWords {

    private HashMap<String, CommandWord> validCommands;
    private String[] desc = new String[8];

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

    public void showAll() {

        desc[0] = "drop    |   drop an item in the current room";
        desc[1] = "help    |   opening this tab";
        desc[2] = "take    |   pick up an item in the current room";
        desc[3] = "search  |   search for item(s) in the current room";
        desc[4] = "go      |   move to the next room";
        desc[5] = "inspect |   inspect the item in your inventory";
        desc[6] = "quit    |   quit the game";
        desc[7] = "exits   |   get the exits for the current room";

      for (int i = 0; i < desc.length; i++) {
            System.out.print(desc[i] + "\n");
        }
        System.out.println();
    }

}
