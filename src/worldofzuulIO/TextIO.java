package worldofzuulIO;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import worldofzuul.Command;
import worldofzuul.CommandWord;
import worldofzuul.Game;
import worldofzuul.Parser;
import worldofzuul.Room;
import worldofzuul.exceptions.MovingThroughFireException;
import worldofzuul.exceptions.MovingThroughLockedDoorException;
import worldofzuul.exceptions.NoExitException;
import worldofzuul.exceptions.NoItemToDropException;
import worldofzuul.exceptions.NoSecondWordGivenException;
import worldofzuul.exceptions.NoSuchItemInRoomException;
import worldofzuul.exceptions.PlayerInventoryFullException;
import worldofzuul.exceptions.UseNonUseableItemException;
import worldofzuul.exceptions.UseWithEmptyInventoryException;

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
            wantToQuit = processGoRoom(command);
        } else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
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
        System.out.println(parser.getCommandsString());

        System.out.println("You have walked " + game.getPlayer().getStepCount() + " steps so far");
    }

    private void printExits() {
        System.out.println(game.getPlayer().getCurrentRoom().getExitString());
    }

    private void printWelcome() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        System.out.println("\tBEEEP! BEEEP! BEEEP!... A loud noise woke you up.");
        System.out.println("\tYou notice the smell and see a thin layer of smoke in you room.");
        System.out.println("\tThe first thing you do is to take your cellphone and call for emergency.");
        System.out.println("\tThe number is 1-1-2.");
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
        TimeUnit.SECONDS.sleep(2);

        System.out.println(game.getPlayer().getCurrentRoom().getExitString());
    }

    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;
        }
    }

    private boolean processGoRoom(Command command) {
        try {
            game.getPlayer().goRoom(command);
            System.out.println(game.getPlayer().getCurrentRoom().getLongDescription());
            updateFire();
            
            //Tjekker om spilleren har vundet.
            if (game.getPlayer().hasWon()) {
                System.out.println("YOU WON THE GAME!");
                return true;
            }
            
            //Tjeker om spilleren går ind i et rum der forsager øjeblikkelig nederlag
            if (game.getPlayer().getCurrentRoom().getGameOver()) {
                System.out.println("You died...");
                return true;
            }
            
            //Sørger for at spilleren mister liv af ild.
            if (game.getPlayer().getCurrentRoom().getFire() != null) {
                game.getPlayer().takeDamage((game.getPlayer().getCurrentRoom().getDamage() + (25 * game.getPlayer().getCurrentRoom().getFire().getLvl())));
                System.out.println("You have been damaged by the fire and lost " + (game.getPlayer().getCurrentRoom().getDamage() + (25 * game.getPlayer().getCurrentRoom().getFire().getLvl())) + " health!");
                if (game.getPlayer().isDead() == true) {
                    System.out.println("Your health is: " + game.getPlayer().getHealth());
                    System.out.println("You died...");
                    return true;
                }
            }

            System.out.println("Your health is: " + game.getPlayer().getHealth());

            System.out.println(game.getPlayer().getCurrentRoom().getExitString());
        } catch (NoSecondWordGivenException ex) {
            System.out.println("Go where?");
        } catch (NoExitException ex) {
            System.out.println("There is no exit.");
        } catch (MovingThroughFireException ex) {
            System.out.println("The fire inside the room prevents you from getting to this door.");
        } catch (MovingThroughLockedDoorException ex) {
            System.out.println("The door is locked! You need a key to open the door!");
        }
        return false;
    }
    
    public void updateFire() {
        if (game.getPlayer().getStepCount() % 5 == 0) {
            System.out.println("You feel the fire inside the buidling getting worse...");
            for (Room room : game.getRooms()) {
                room.updateFire();
            }
        }

    }

    private void processTakeItem(Command command) {
        try {
            game.getPlayer().takeItem(command);
            System.out.println("You pick up the " + game.getPlayer().getInventory().getName() + ".");
        } catch (NoSecondWordGivenException ex) {
            System.out.println("Take what?");
        } catch (PlayerInventoryFullException ex) {
            System.out.println("You are already carrying a " + game.getPlayer().getInventory().getName());
        } catch (NoSuchItemInRoomException ex) {
            System.out.println("The room contains no such thing.");
        }
    }

    private void processDropItem(Command command) {
        try {
            game.getPlayer().dropItem();
            System.out.print("You drop the ");
            String nameOfDroppedItem = "";
            for (int i = 0; i < game.getPlayer().getCurrentRoom().getItems().size(); i++) {
                if (i == game.getPlayer().getCurrentRoom().getItems().size() - 1) {
                    nameOfDroppedItem = game.getPlayer().getCurrentRoom().getItems().get(i).getName();
                }
            }
            System.out.println(nameOfDroppedItem + ".");
        } catch (NoItemToDropException ex) {
            System.out.println("You do not carry anything to drop.");
        }
    }

    private void processInspectInventory() {
        if (game.getPlayer().getInventory() != null) {
            System.out.println("You are carrying a " + game.getPlayer().getInventory().getName() + ".");
            System.out.println(game.getPlayer().getInventory().getDescription());
        } else {
            System.out.println("You are not carrying anything.");
        }
    }

    private void processSearchRoom() {
        System.out.println(game.getPlayer().getCurrentRoom().getItemsString());
    }

    private void processUseItem() {
        try {
            System.out.println(game.getPlayer().useItem());
        } catch (UseWithEmptyInventoryException ex) {
            System.out.println("You do not carry anything to use");
        } catch (UseNonUseableItemException ex) {
            System.out.println("There is nothing interesting to use this item for.");
        }
    }
}
