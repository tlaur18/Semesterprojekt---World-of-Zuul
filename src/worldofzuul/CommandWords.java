package worldofzuul;

import java.util.HashMap;

public class CommandWords {

    private HashMap<String, CommandWord> validCommands;
    private String[] desc = new String[7];

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

        desc[0] = "drop:    |   " + "used to drop an item in the current room";
        desc[1] = "help:    |   " + "used to open this tab";
        desc[2] = "take:    |   " + "used to pick up an item in the current room";
        desc[3] = "search:  |   " + "used for item(s) in the current room";
        desc[4] = "go:      |   " + "used to move to the next room";
        desc[5] = "inspect: |   " + "used to inspect the item in your inventory";
        desc[6] = "quit:    |   " + "used to quit the game";

      for (int i = 0; i < desc.length; i++) {
            System.out.printf(desc[i] + "\n");
        }
        System.out.println();
    }

}
