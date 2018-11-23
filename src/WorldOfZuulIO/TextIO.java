package WorldOfZuulIO;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import worldofzuul.Command;
import worldofzuul.CommandWord;
import worldofzuul.Game;
import worldofzuul.Parser;
import worldofzuul.Player;

public class TextIO {

    private Game game;
    private Parser parser;

    public TextIO(Game game) {
        System.out.println("Welcome to Fire Escape!\n");
        this.game = game;
        parser = new Parser();
    }

    public void play() throws InterruptedException {
        printWelcome();
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
            wantToQuit = quit(command);
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

    private void printWelcome() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        System.out.println("\t BIP BIP BIP! There is a loud noise that woke you up, \n \t you notice the smell and the thin \n \t layer of smoke in you room.");
        System.out.println("\t The first thing you do is to take your cellphone and call for emergency,\n \t the number is 1-1-2.");
        TimeUnit.SECONDS.sleep(3);
        System.out.println("112: \n- This is 1-1-2. What is your emergency? ");
        TimeUnit.SECONDS.sleep(2);
        System.out.println("");
        System.out.println("You: \n- There is smoke in the room and I am all alone in the house.");
        TimeUnit.SECONDS.sleep(2);
        System.out.println("");
        System.out.println("112: \n- Okay, just stay calm and lets get you to safety.");
        TimeUnit.SECONDS.sleep(2);
        System.out.println("- It doesn't help to panic.");
        TimeUnit.SECONDS.sleep(2);
        System.out.println("- What is your name?");
        System.out.print("> ");

        Scanner pn = new Scanner(System.in);
        game.getPlayer().setPlayerName(pn.nextLine());

        System.out.println("");
        System.out.println(game.getPlayer().getPlayerName() + ": \n- My name is " + game.getPlayer().getPlayerName() + ". I will try my best with your help.\n");
        TimeUnit.SECONDS.sleep(2);
        System.out.println("112: \n- You need to get to safety and thats your primary objective.");
        TimeUnit.SECONDS.sleep(2);
        System.out.println("- You will encoutner some obstacles, and you will need to figure a way out of the house");
        TimeUnit.SECONDS.sleep(2);
        System.out.println("- If you have any questions just ask for '" + CommandWord.HELP + "'.\n");

        System.out.println(game.getPlayer().getCurrentRoom().getExitString());
    }

    public boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;
        }
    }
}
