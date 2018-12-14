/**
 *
 * TextUI handles every piece of text printet to the user.
 * Commands from the user are also processed here.
 * TextUI is the basis of the application. The instans of Game is contained here.
 * The TextUI is instantiated as the StartMenuController controller initializes.
 *
 * @author Alexander Nguyen, Jacob Wowk, Morten K. Jensen, Thomas S. Laursen
 * @version 2018.12.14
 *
 */
package worldofzuulIO;

import exceptions.NameInputException;
import worldofzuul.Command;
import worldofzuul.CommandWord;
import worldofzuul.Game;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.TextArea;
import javafx.util.Duration;

public class TextUI {

    private Game game;
    private TextArea txtAreaOutput;
    private TextArea txtAreaHelp;

    /*
    * The constructor receives a TextArea. It gets this from the controller when instantiated.
    * 'textAreaOutput' is where the primary text output to the user will happen.
    */
    public TextUI() {
        this.game = new Game();
    }
    
    /*
    * Recieves a TextArea from a controller and assigns the variable 'txtAreaHelp' to it.
    * This is where the helping text is printed to the user.
    */
    public void setTextAreaHelp(TextArea txtAreaHelp) {
        this.txtAreaHelp = txtAreaHelp;
    }

    public Game getGame() {
        return game;
    }
    
    /*
    * Sets the main TextArea for printing messages to the user.
    * Called from the main controller that contains this TextArea.
    */
    public void setOutput(TextArea txtAreaOutput) {
        this.txtAreaOutput = txtAreaOutput;
    }

    /*
    * Processes the incoming user commands.
    * The Controller calls this methods with different commands depending on the buttons pressed by the user.
    * Returns a boolean value that states if the command caused the player to change room.
    */
    public boolean processCommand(Command command) {
        CommandWord commandWord = command.getCommandWord();
        boolean changedRoom = false;
        
        /*
        * Calls the appropriate methods according to the the recived command.
        */
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

    /*
    * Decides what help-text to print to the user according to the players progress in the game.
    * The progress is determined by how far into the game the player is (e.g. how much fire the player has put out).
    * Called when 'processCommand' receives the command 'help' (When pressing the button 'help').
    */
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

    /*
    * Prints the welcoming text in a TextArea given in the parametres.
    * Called in StartMenuController after the player enters a username.
    * TextArea is not the main output TextArea as this intro has an entire scene for itself.
    */
    public void printWelcome(TextArea txtArea) {
        //Starts by constructing the entire text as one string; 'welcomeText'.
        String welcomeText = "";

        welcomeText += "Welcome to Fire Escape!";

        welcomeText += "\n";
        welcomeText += "\n\tBEEEP! BEEEP! BEEEP!... A loud noise woke you up.";
        welcomeText += "\n\tYou notice the smell and see a thin layer of smoke in your room.";
        welcomeText += "\n\tThe first thing you do is to take your cellphone and call for emergency.";
        welcomeText += "\n\tThe number is 1-1-2.";

        welcomeText += "\n";
        welcomeText += "\n112:";
        welcomeText += "\n- This is 1-1-2. What is your emergency?";

        welcomeText += "\n";
        welcomeText += "\nYou:";
        welcomeText += "\n- There is smoke in my room and I am all alone in the house.";

        welcomeText += "\n";
        welcomeText += "\n112:";
        welcomeText += "\n- Okay, just stay calm and let us get you to safety.";
        welcomeText += "\n- It does not help to panic.";
        welcomeText += "\n- What is your name?";

        welcomeText += "\n";
        welcomeText += "\n" + game.getPlayer().getPlayerName() + ": ";
        welcomeText += "\n- My name is " + game.getPlayer().getPlayerName() + ". I will try my best with your help.";

        welcomeText += "\n";
        welcomeText += "\n112:";
        welcomeText += "\n- You need to get to safety and that is your primary objective.";

        welcomeText += "\n- You will most likely encounter some obstacles on your way out.";

        welcomeText += "\n- If you have any questions just ask for '" + CommandWord.HELP + "'.";

        welcomeText += "\n";
        
        /*
        * Calls the method 'printWithPacing' and gives the String and the TextArea as arguments.
        * Prints 'welcomeText' with small delays after each character.
        */
        printWithPacing(welcomeText, txtArea);
    }

    /*
    * This method takes a String and a TextArea as arguments.
    * The String is the text to be printed.
    * The TextArea is where it should be printed.
    * The text is printed with delays between every character.
    * Makes the text appear flowing and easier to read.
    * Inspiration drawn from following site:
    * https://stackoverflow.com/questions/9966136/javafx-periodic-background-task/9966213#9966213
    */
    private void printWithPacing(String textToPrint, TextArea txtArea) {
        //Timeline is created. Contains a KeyFrame with a delay of 35 ms (Delay between each character) and an eventhandler.
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(35), new printWithPacingEventHandler(textToPrint, txtArea)));
        
        //Sets the amount of cycles of the eventhandler's 'handle'-method. In this case, the length of the message.
        timeline.setCycleCount(textToPrint.length());
        
        //Begins the printing of the message ('handle'-method in event-handler).
        timeline.play();
    }

    /*
    * Decides how the game behaves when the Player tries to change Room.
    * Called when 'processCommand' receives the command 'go ####' (When pressing any of the direction buttons).
    * Returns a boolean value that indicates whether the player changed Room or not.
    */
    private boolean processGoRoom(Command command) {
        /*
        * A SimpleBooleanProperty is created. This acts as a complex type
        * And allows the game to pass it through a method where it can be changed.
        */
        SimpleBooleanProperty changedRoom = new SimpleBooleanProperty(false);
        
        /*
        * The 'goRoom'-method in the 'Player'-class is called with the
        * input Command and the SimpleBooleanProperty passed with it.
        * This method returns a String describing the outcome of the method.
        * This String is printed in the main TextAreaOutput.
        * The SimpleBooleanProperty might be changed inside this method
        * depending on whether the player changed room or not.
        */
        txtAreaOutput.appendText(game.getPlayer().goRoom(command, changedRoom));
        
        /*
        * Only if the player chanegd Room shall the method 'updateFire' in the Game be called.
        * This method returns a String describing the outcome. This is printed.
        */
        if (changedRoom.get()) {
            txtAreaOutput.appendText(game.updateFire());
            
            /*
            * Kills the Player if he entered a Room that caused him to lose instantly.
            */
            if (game.getPlayer().getCurrentRoom().getGameOver()) {
                game.getPlayer().setHealth(0);
            }
            
            /*
            * Makes sure the Player gets damaged by Smoke and Fire.
            * These methods returns a boolean value depending on the outcome.
            * Text is printed to the user depending on the outcome.
            */
            txtAreaOutput.appendText((game.getPlayer().checkForFireDamage() ? "\nYou have been damaged by the fire" : ""));
            txtAreaOutput.appendText((game.getPlayer().checkForSmokeDamage() ? "\nYou are slowly being suffocated by the smoke" : ""));
        }

        /*
        * Returns the value of the SimpleBooleanProperty 'changedRoom' to 'processCommand'.
        */
        return changedRoom.get();
    }

    /*
    * Calls the method 'takeItem' on the Player.
    * Called when 'processCommand' receives the command 'take ####' (When clicking on Items).
    * 'takeItem' returns a String describing the outcome. This is printed.
    */
    private void processTakeItem(Command command) {
        txtAreaOutput.appendText(game.getPlayer().takeItem(command));
    }

    /*
    * Calls the method 'dropItem' on the Player.
    * Called when 'processCommand' receives the command 'drop' (When pressing the 'drop' button).
    * 'dropItem' returns a String describing the outcome. This is printed.
    */
    private void processDropItem(Command command) {
        txtAreaOutput.appendText(game.getPlayer().dropItem());
    }

    /*
    * Tells the player what Item he is carrying and prints its description.
    * Called when 'processCommand' receives the command 'inspect' (When pressing the 'inspect' button).
    */
    private void processInspectInventory() {
        if (game.getPlayer().getInventory() != null) {
            txtAreaOutput.appendText("\nYou are carrying a " + game.getPlayer().getInventory().getName() + ".");
            txtAreaOutput.appendText("\n" + game.getPlayer().getInventory().getDescription());
        } else {
            txtAreaOutput.appendText("\nYou are not carrying anything.");
        }
    }

    /*
    * Calls the method 'useItems' on the Player.
    * Called when 'processCommand' receives the command 'use' (When pressing the 'use' button).
    * 'useItem' returns a String describing the outcome. This is printed.
    */
    private void processUseItem() {
        txtAreaOutput.appendText(game.getPlayer().useItem());
    }
    
    /*
    * Used to determine if the username inputted from the user is valid.
    * Throws the custom NameInputException when username contains commas or is empty.
    * Called in StartMenuController when the user types a username.
    */
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
