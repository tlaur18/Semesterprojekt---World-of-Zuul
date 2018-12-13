/**
 *
 * @author Alexander Nguyen, Jacob Wowk, Morten K. Jensen and Thomas S. Laursen
 * @version 2018.12.14
 * 
 */

package worldofzuul.presentation;

import exceptions.NameInputException;
import java.util.List;
import worldofzuul.domain.Command;
import worldofzuul.domain.CommandWord;
import worldofzuul.domain.Game;
import worldofzuul.domain.Parser;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.util.Duration;
import worldofzuul.domain.Highscore;

public class TextUI {

    private Game game;
    private Parser parser;
    private TextArea txtAreaOutput;
    private TextArea txtAreaHelp;

    public TextUI(Game game, TextArea txtAreaOutput) {
        this.game = game;
        this.txtAreaOutput = txtAreaOutput;
        parser = new Parser();
    }
    
    public void setLblHelp(TextArea txtAreaHelp) {
        this.txtAreaHelp = txtAreaHelp;
    }

    public Game getGame() {
        return game;
    }
    
    public void setOutput(TextArea txtAreaOutput) {
        this.txtAreaOutput = txtAreaOutput;
    }

    public boolean processCommand(Command command) {
        CommandWord commandWord = command.getCommandWord();
        boolean changedRoom = false;

        if (commandWord == CommandWord.GO) {
            changedRoom = processGoRoom(command);
        } else if (commandWord == CommandWord.TAKE) {
            processTakeItem(command);
        } else if (commandWord == CommandWord.DROP) {
            processDropItem(command);
        } else if (commandWord == CommandWord.INSPECT) {
            processInspectInventory();
        } else if (commandWord == CommandWord.USE) {
            processUseItem();
        }

        return changedRoom;
    }

    public void printHelp() {
        txtAreaHelp.clear();
        switch (game.getPlayer().getProgress()) {
            case 0:
                txtAreaHelp.appendText("Start by exploring the house"
                        + "\nfor a way out.");
                txtAreaHelp.appendText("\nBe careful of smoke and fire"
                        + "\nas it will hurt you.");
                break;
            case 1:
                txtAreaHelp.appendText("The fire in the living room"
                        + "\nblocks the way.");
                txtAreaHelp.appendText("\nMaybe you should try finding"
                        + "\nsomething that holds water.");
                break;
            case 2:
                txtAreaHelp.appendText("The exit is still blocket by fire.");
                txtAreaHelp.appendText("\nYou should find something"
                        + "\nto put it out.");
                txtAreaHelp.appendText("\nBut be careful with big fires.");
                txtAreaHelp.appendText("\nNot all pieces of fire"
                        + "\nequipment work equally well.");
                break;
            case 3:
                txtAreaHelp.appendText("Explore further but there"
                        + "\nmight still be fire"
                        + "\nsomwhere inside the house.");
                txtAreaHelp.appendText("\nBe sure to bring any unused"
                        + "\nfire equipment you find.");
                break;
            case 4:
                txtAreaHelp.appendText("The fire is under control.");
                txtAreaHelp.appendText("\nHowever, you still need to find"
                        + "\nyour way out because of"
                        + "\nthe smoke.");
                break;
            case 5:
                txtAreaHelp.appendText("The door to outside seems"
                        + "\nto be locked.");
                txtAreaHelp.appendText("\nI bet a key is stored somewhere"
                        + "\nnearby.");
                break;
        }
    }

    public void printWelcome(TextArea txtArea) {
        String welcomeText = "";

        welcomeText += "Welcome to Fire Escape!";

        welcomeText += "\n";
        welcomeText += "\n\tBEEEP! BEEEP! BEEEP!... A loud noise woke you up.";
        welcomeText += "\n\tYou notice the smell and see a thin layer of smoke in you room.";
        welcomeText += "\n\tThe first thing you do is to take your cellphone and call for emergency.";
        welcomeText += "\n\tThe number is 1-1-2.";

        welcomeText += "\n";
        welcomeText += "\n112:";
        welcomeText += "\n- This is 1-1-2. What is your emergency?";

        welcomeText += "\n";
        welcomeText += "\nYou:";
        welcomeText += "\n- There is smoke in the room and I am all alone in the house.";

        welcomeText += "\n";
        welcomeText += "\n112:";
        welcomeText += "\n- Okay, just stay calm and lets get you to safety.";
        welcomeText += "\n- It doesn't help to panic.";
        welcomeText += "\n- What is your name?";

        welcomeText += "\n";
        welcomeText += "\n" + game.getPlayer().getPlayerName() + ": ";
        welcomeText += "\n- My name is " + game.getPlayer().getPlayerName() + ". I will try my best with your help.";

        welcomeText += "\n";
        welcomeText += "\n112:";
        welcomeText += "\n- You need to get to safety and thats your primary objective.";

        welcomeText += "\n- You will most likely encoutner some obstacles on your way out.";

        welcomeText += "\n- If you have any questions just ask for '" + CommandWord.HELP + "'.";

        welcomeText += "\n";

        printWithPacing(welcomeText, txtArea);
    }

    private void printWithPacing(String textToPrint, TextArea txtArea) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(35), new printWithPacingEventHandler(textToPrint, txtArea)));
        timeline.setCycleCount(textToPrint.length());
        timeline.play();
    }

    private boolean processGoRoom(Command command) {
        //SimpleBooleanProperty er en slags boolean der bare er en kompleks type. Andre variable der sættes lig med denne vil derfor henvise til den samme hukommelse.
        SimpleBooleanProperty changedRoom = new SimpleBooleanProperty(false);

        //Udskriver resultatet af goRoom()-metoden.
        txtAreaOutput.appendText(game.getPlayer().goRoom(command, changedRoom));

        if (changedRoom.get()) {
            txtAreaOutput.appendText(game.updateFire());

            //Tjekker om spilleren går ind i et rum der forsager øjeblikkelig nederlag
            if (game.getPlayer().getCurrentRoom().getGameOver()) {
                game.getPlayer().setHealth(0);
            }

            //Sørger for at spilleren mister liv af ild.
            txtAreaOutput.appendText((game.getPlayer().checkForFireDamage() ? "\nYou have been damaged by the fire" : ""));
            txtAreaOutput.appendText((game.getPlayer().checkForSmokeDamage() ? "\nYou are slowly being suffocated by the smoke" : ""));
        }

        return changedRoom.get();
    }

    private void processTakeItem(Command command) {
        txtAreaOutput.appendText(game.getPlayer().takeItem(command));
    }

    private void processDropItem(Command command) {
        txtAreaOutput.appendText(game.getPlayer().dropItem());
    }

    private void processInspectInventory() {
        if (game.getPlayer().getInventory() != null) {
            txtAreaOutput.appendText("\nYou are carrying a " + game.getPlayer().getInventory().getName() + ".");
            txtAreaOutput.appendText("\n" + game.getPlayer().getInventory().getDescription());
        } else {
            txtAreaOutput.appendText("\nYou are not carrying anything.");
        }
    }

    private void processUseItem() {
        txtAreaOutput.appendText(game.getPlayer().useItem());
    }
    
    public void printHighscore(TextArea txtArea) {
        String highscoretxt = "";
        List<Highscore> highscores = game.getHighscoreDatabase().getHighscores();
        for (Highscore highscore : highscores) {
            highscoretxt += "Name: " + highscore.getName() + "\t\t";
            highscoretxt += " Score: " + highscore.getScore() + "\n";
        }
        printWithPacing(highscoretxt, txtArea);
    }

    public void validName(String str) throws NameInputException {
        char[] charArray = str.toCharArray();
        for (char c : charArray) {
            if (c == ',') {
                throw new NameInputException();
            }
        }
        if (str.isEmpty()) {
            throw new NameInputException();
        }
    }
}
