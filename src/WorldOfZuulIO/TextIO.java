package WorldOfZuulIO;

import worldofzuul.Command;
import worldofzuul.CommandWord;
import worldofzuul.Game;
import worldofzuul.Parser;

public class TextIO {

    private Game game;
    private Parser parser;

    public TextIO(Game game) {
        System.out.println("Welcome to Fire Escape!\n");
        this.game = game;
        parser = new Parser();
    }

    public void play() {
        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing. Good bye.");
    }

    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if (commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        if (commandWord == CommandWord.HELP) {
            printHelp();
        } else if (commandWord == CommandWord.GO) {
            game.getPlayer().goRoom(command, game);
        } else if (commandWord == CommandWord.QUIT) {
            wantToQuit = game.quit(command);
        } else if (commandWord == CommandWord.TAKE) {
            game.getPlayer().takeItem(command);
        } else if (commandWord == CommandWord.DROP) {
            game.getPlayer().dropItem();
        } else if (commandWord == CommandWord.INSPECT) {
            game.getPlayer().inspectInventory();
        } else if (commandWord == CommandWord.SEARCH) {
            game.getPlayer().searchRoom();
        } else if (commandWord == CommandWord.USE) {
            game.getPlayer().useItem();
        } else if (commandWord == CommandWord.EXITS) {
            printExits();
        }
        return wantToQuit;
    }

    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at you own home.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();

        System.out.println("\n you have walked " + game.getPlayer().getStepCount() + " steps so far");
    }
    
    private void printExits() {
        System.out.println(game.getPlayer().getCurrentRoom().getExitString());
    }
}
