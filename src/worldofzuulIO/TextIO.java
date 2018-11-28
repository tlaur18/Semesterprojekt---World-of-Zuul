package worldofzuulIO;

import worldofzuul.Command;
import worldofzuul.CommandWord;
import worldofzuul.Game;
import worldofzuul.Parser;
import worldofzuul.Room;
import exceptions.MovingThroughFireException;
import exceptions.MovingThroughLockedDoorException;
import exceptions.NoExitException;
import exceptions.NoItemToDropException;
import exceptions.NoSecondWordGivenException;
import exceptions.NoSuchItemInRoomException;
import exceptions.PlayerDiedException;
import exceptions.PlayerInventoryFullException;
import exceptions.UseNonUseableItemException;
import exceptions.UseWithEmptyInventoryException;
import javafx.scene.control.TextArea;

public class TextIO {

    private Game game;
    private Parser parser;
    private TextArea txtAreaOutput;

    public TextIO(Game game, TextArea txtAreaOutput) {
        this.game = game;
        this.txtAreaOutput = txtAreaOutput;
        parser = new Parser();
    }

    public Game getGame() {
        return game;
    }

    public void processCommand(Command command) throws PlayerDiedException {
        CommandWord commandWord = command.getCommandWord();

        if (commandWord == CommandWord.UNKNOWN) {
            txtAreaOutput.appendText("\nI don't know what you mean...");
        }

        if (commandWord == CommandWord.HELP) {
            printHelp();
        } else if (commandWord == CommandWord.GO) {
            processGoRoom(command);
        } else if (commandWord == CommandWord.QUIT) {
            processQuit();
        } else if (commandWord == CommandWord.TAKE) {
            processTakeItem(command);
        } else if (commandWord == CommandWord.DROP) {
            processDropItem(command);
        } else if (commandWord == CommandWord.INSPECT) {
            processInspectInventory();
        } else if (commandWord == CommandWord.SEARCH) {
            processSearchRoom();
        } else if (commandWord == CommandWord.USE) {
            processUseItem();
        }
    }

    private void printHelp() {
        txtAreaOutput.appendText("\nYou are lost. You are alone. You wander");
        txtAreaOutput.appendText("\naround at you own home.");
        txtAreaOutput.appendText("\n");
        txtAreaOutput.appendText("\nYour command words are:");
        txtAreaOutput.appendText(parser.getCommandsString());

        txtAreaOutput.appendText("\nYou have walked " + game.getPlayer().getStepCount() + " steps so far");
    }

    public void printWelcome() {
        txtAreaOutput.appendText("Welcome to Fire Escape!");

        txtAreaOutput.appendText("\n");
        txtAreaOutput.appendText("\n\tBEEEP! BEEEP! BEEEP!... A loud noise woke you up.");
        txtAreaOutput.appendText("\n\tYou notice the smell and see a thin layer of smoke in you room.");
        txtAreaOutput.appendText("\n\tThe first thing you do is to take your cellphone and call for emergency.");
        txtAreaOutput.appendText("\n\tThe number is 1-1-2.");

        txtAreaOutput.appendText("\n");
        txtAreaOutput.appendText("\n112:");
        txtAreaOutput.appendText("\n- This is 1-1-2. What is your emergency?");

        txtAreaOutput.appendText("\n");
        txtAreaOutput.appendText("\nYou:");
        txtAreaOutput.appendText("\n- There is smoke in the room and I am all alone in the house.");

        txtAreaOutput.appendText("\n");
        txtAreaOutput.appendText("\n112:");
        txtAreaOutput.appendText("\n- Okay, just stay calm and lets get you to safety.");
        txtAreaOutput.appendText("\n- It doesn't help to panic.");
        txtAreaOutput.appendText("\n- What is your name?");

        txtAreaOutput.appendText("\n");
        txtAreaOutput.appendText("\n" + game.getPlayer().getPlayerName() + ": ");
        txtAreaOutput.appendText("\n- My name is " + game.getPlayer().getPlayerName() + ". I will try my best with your help.");

        txtAreaOutput.appendText("\n");
        txtAreaOutput.appendText("\n112:");
        txtAreaOutput.appendText("\n- You need to get to safety and thats your primary objective.");

        txtAreaOutput.appendText("\n- You will most likely encoutner some obstacles on your way out.");

        txtAreaOutput.appendText("\n- If you have any questions just ask for '" + CommandWord.HELP + "'.");

        txtAreaOutput.appendText("\n");
    }

    private void processQuit() {
        txtAreaOutput.appendText("\nTo quit, simply press the 'X' button in the top right corner of the window.");
    }

    private void processGoRoom(Command command) throws PlayerDiedException {
        try {
            game.getPlayer().goRoom(command);
            txtAreaOutput.appendText("\n" + game.getPlayer().getCurrentRoom().getLongDescription());
            updateFire();

            //Tjekker om spilleren har vundet.
            if (game.getPlayer().hasWon()) {
                txtAreaOutput.appendText("\nYOU WON THE GAME!");
                return;
            }

            //Tjeker om spilleren går ind i et rum der forsager øjeblikkelig nederlag
            if (game.getPlayer().getCurrentRoom().getGameOver()) {
                throw new PlayerDiedException();
            }

            //Sørger for at spilleren mister liv af ild.
            if (game.getPlayer().getCurrentRoom().getFire() != null) {
                game.getPlayer().takeDamage((game.getPlayer().getCurrentRoom().getDamage() + (25 * game.getPlayer().getCurrentRoom().getFire().getLvl())));
                txtAreaOutput.appendText("\nYou have been damaged by the fire and lost " + (game.getPlayer().getCurrentRoom().getDamage() + (25 * game.getPlayer().getCurrentRoom().getFire().getLvl())) + " health!");
                if (game.getPlayer().isDead() == true) {
                    throw new PlayerDiedException();
                }
            }

            txtAreaOutput.appendText("\nYour health is: " + game.getPlayer().getHealth());
        } catch (NoSecondWordGivenException ex) {
            txtAreaOutput.appendText("\nGo where?");
        } catch (NoExitException ex) {
            txtAreaOutput.appendText("\nThere is no exit.");
        } catch (MovingThroughFireException ex) {
            txtAreaOutput.appendText("\nThe fire inside the room prevents you from getting to this door.");
        } catch (MovingThroughLockedDoorException ex) {
            txtAreaOutput.appendText("\nThe door is locked! You need a key to open the door!");
        }
    }

    public void updateFire() {
        if (game.getPlayer().getStepCount() % 5 == 0) {
            txtAreaOutput.appendText("\nYou feel the fire inside the buidling getting worse...");
            for (Room room : game.getRooms()) {
                room.updateFire();
            }
        }

    }

    private void processTakeItem(Command command) {
        try {
            game.getPlayer().takeItem(command);
            txtAreaOutput.appendText("\nYou pick up the " + game.getPlayer().getInventory().getName() + ".");
        } catch (NoSecondWordGivenException ex) {
            txtAreaOutput.appendText("\nTake what?");
        } catch (PlayerInventoryFullException ex) {
            txtAreaOutput.appendText("\nYou are already carrying a " + game.getPlayer().getInventory().getName());
        } catch (NoSuchItemInRoomException ex) {
            txtAreaOutput.appendText("\nThe room contains no such thing.");
        }
    }

    private void processDropItem(Command command) {
        try {
            game.getPlayer().dropItem();
            txtAreaOutput.appendText("\nYou drop the ");
            String nameOfDroppedItem = "";
            for (int i = 0; i < game.getPlayer().getCurrentRoom().getItems().size(); i++) {
                if (i == game.getPlayer().getCurrentRoom().getItems().size() - 1) {
                    nameOfDroppedItem = game.getPlayer().getCurrentRoom().getItems().get(i).getName();
                }
            }
            txtAreaOutput.appendText(nameOfDroppedItem + ".");
        } catch (NoItemToDropException ex) {
            txtAreaOutput.appendText("\nYou do not carry anything to drop.");
        }
    }

    private void processInspectInventory() {
        if (game.getPlayer().getInventory() != null) {
            txtAreaOutput.appendText("\nYou are carrying a " + game.getPlayer().getInventory().getName() + ".");
            txtAreaOutput.appendText("\n" + game.getPlayer().getInventory().getDescription());
        } else {
            txtAreaOutput.appendText("\nYou are not carrying anything.");
        }
    }

    private void processSearchRoom() {
        txtAreaOutput.appendText("\n" + game.getPlayer().getCurrentRoom().getItemsString());
    }

    private void processUseItem() {
        try {
            txtAreaOutput.appendText("\n" + game.getPlayer().useItem());
        } catch (UseWithEmptyInventoryException ex) {
            txtAreaOutput.appendText("\nYou do not carry anything to use");
        } catch (UseNonUseableItemException ex) {
            txtAreaOutput.appendText("\nThere is nothing interesting to use this item for.");
        }
    }
}
