/*
@author Alexander Nguyen, Jacob Wowk, Morten K. Jensen and Thomas S. Laursen
* @version 2018.12.14

Controller Class for creating and controlling the JavaFXML elements, 
and running methods from the Domain layer.
*/

package worldofzuulIO;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import worldofzuul.Command;
import worldofzuul.Fire;
import items.Item;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import worldofzuul.Parser;
import worldofzuul.Room;
import worldofzuul.Smoke;

public class MainController implements Initializable {

    /*
    Important attribute for setting the TextUI which is used to access the 
    Domain layer.
    */
    private TextUI textUI;

    @FXML
    private BorderPane root;
    @FXML
    private TextArea txtAreaOutput;
    @FXML
    private Button btnNorth;
    @FXML
    private Button btnWest;
    @FXML
    private Button btnSouth;
    @FXML
    private Button btnEast;
    @FXML
    private Label lblCurrentRoom;
    @FXML
    private ImageView imgPhone;
    @FXML
    private Button btnDrop;
    @FXML
    private Button btnInspect;
    @FXML
    private Button btnHelp;
    @FXML
    private Label lblInventoryHeadline;
    @FXML
    private ImageView imgBackground;
    @FXML
    private ImageView timon;
    @FXML
    private ImageView deadTimon;
    @FXML
    private Pane paneRoom;
    @FXML
    private ImageView imgInventory;
    @FXML
    private Button btnUse;
    @FXML
    private Rectangle greenbar;
    @FXML
    private Rectangle redbar;
    @FXML
    private Label healthText;
    @FXML
    private Label stepCounterText;
    @FXML
    private Label lblScore;
    @FXML
    private ImageView hasWon;
    @FXML
    private Button hasWonBtnOk;
    @FXML
    private Label hasWonScore;
    @FXML
    private ImageView isDead;
    @FXML
    private Button isDeadBtnYes;
    @FXML
    private Button isDeadBtnNo;
    @FXML
    private ImageView imgBunny;
    @FXML
    private TextArea txtAreaNPC;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Makes sure the TxtAreaOutput scrolls down from the start.
        txtAreaOutput.appendText("\n");
        txtAreaOutput.appendText("\n");
        txtAreaOutput.appendText("\n");
        txtAreaOutput.appendText("\n");
    }
    /*
    Sets the textUI attribut for later access to the TextUI
    Prints the items for the rooms.
    */
    public void setTextUI(TextUI textUI) {
        this.textUI = textUI;
        textUI.setOutput(txtAreaOutput);
        textUI.setTextAreaHelp(txtAreaNPC);
        printItems();
    }
    /*
    Uses the buttons places north to set the proccessCommand to "go north"
    redraws the room according to the new room.
    */
    @FXML
    private void btnNorthEventHandler(ActionEvent event) {
        if (processCommand("go north")) {
            redrawRoom();
        }
    }
    /*
    Uses the buttons places north to set the proccessCommand to "go west"
    redraws the room according to the new room.
    */
    @FXML
    private void btnWestEventHandler(ActionEvent event) {
        if (processCommand("go west")) {
            redrawRoom();
        }
    }
    /*
    Uses the buttons places north to set the proccessCommand to "go south"
    redraws the room according to the new room.
    */
    @FXML
    private void btnSouthEventHandler(ActionEvent event) {
        if (processCommand("go south")) {
            redrawRoom();
        }
    }
    /*
    Uses the buttons places north to set the proccessCommand to "go east"
    redraws the room according to the new room.
    */
    @FXML
    private void btnEastEventHandler(ActionEvent event) {
        if (processCommand("go east")) {
            redrawRoom();
        }
    }
    
    /*
    Connects the use button to the proccessCommand "use" and checks if the 
    inventory is empty.
    If the inventory is not empty it finds the item name and places it in the 
    process command string.
    if the inventory is empty after the use, the image inventory is set to null
    else if the image is not equal to the image from the inventory, it updates
    the image.
    Then it updates the fire images, health bar and highscore.
    */
    @FXML
    private void btnUseEventHandler(ActionEvent event) {
        processCommand("use " + (textUI.getGame().getPlayer().getInventory() != null ? textUI.getGame().getPlayer().getInventory().getName() : ""));

        if (textUI.getGame().getPlayer().getInventory() == null) {
            imgInventory.setImage(null);
        } else if (!(textUI.getGame().getPlayer().getInventory().getImage().equals(imgInventory))) {
            imgInventory.setImage(textUI.getGame().getPlayer().getInventory().getImage().getImage());
        }

        updateFireImgs();
        drawHealthBar();
        highscoreUpdater();
        
    }
    
    /*
    Removes the item from the player and places it in the current room
    using the preocessCommand "drop"
    Prints the items in the rooms again, according to the new items dropped in 
    the room.
    Sets the Inventory Image to null.
    */
    @FXML
    private void btnDropEventHandler(ActionEvent event) {
        removeItems(textUI.getGame().getPlayer().getCurrentRoom());
        processCommand("drop");
        printItems();
        imgInventory.setImage(null);
    }

    /*
    Connects the button with the processCommand "inspect"
    */
    @FXML
    private void btnInspectEventHandler(ActionEvent event) {
        processCommand("inspect");
    }
    
    /*
    Shows the TextArea next to the NPC.
    places the textArea on front.
    Prints the help strings for the NPC
    */
    @FXML
    private void btnHelpEventHandler(ActionEvent event) {
        txtAreaNPC.setVisible(true);
        txtAreaNPC.toFront();
        textUI.printHelp();
    }
    
    /*
    Returns a boolean to tell whether the room changed or not.
    Uses a parser to parse the commands to a command.
    Changes the text of the current room label to the new room name
    Updates Highscore everytime the room is changed.
    if the player died, it updates and saves the highscore, disables the game,
    redraws the room, draws the dead stage, and returns a false value
    if the player won, it updates and saves the highscore, disavbles the game,
    and draws the win stage, and returns false.
    */
    private boolean processCommand(String inputLine) {
        boolean changedRoom = false;
        Parser parser = new Parser();
        txtAreaOutput.appendText("\n");
        Command command = parser.getCommand(inputLine);
        changedRoom = textUI.processCommand(command);
        lblCurrentRoom.setText(textUI.getGame().getPlayer().getCurrentRoom().getName());
        highscoreUpdater();

        if (textUI.getGame().getPlayer().isDead()) {
            highscoreUpdater();
            textUI.getGame().saveHighscore();
            disableGame();
            redrawRoom();
            drawDeadStage();
            return false;
        }

        if (textUI.getGame().getPlayer().hasWon()) {
            highscoreUpdater();
            highscore();
            disableGame();
            redrawRoom();
            drawWinStage();
            return false;
        }

        return changedRoom;
    }
    /*
    removes the Items, fire, and smoke, and print them all again, to get an 
    updated view.
    Prints the buttons, sets the background, draws the healthbar and stepcounter
    It hides the NPC's textArea.
    */
    private void redrawRoom() {
        removeItems(textUI.getGame().getPlayer().getPreviousRoom());
        removeFire();
        removeSmoke();
        printItems();
        printFire();
        printSmoke();
        printDirectionButtons();
        setBackground();
        drawHealthBar();
        stepCounterText();
        txtAreaNPC.setVisible(false);
    }
    /*
    Hides all direction buttons, and uses a switch case to redraw the useable
    ones.
    */
    private void printDirectionButtons() {
        btnNorth.setVisible(false);
        btnWest.setVisible(false);
        btnSouth.setVisible(false);
        btnEast.setVisible(false);

        for (String exitString : textUI.getGame().getPlayer().getCurrentRoom().getExits().keySet()) {
            switch (exitString) {
                case "north":
                    btnNorth.toFront();
                    btnNorth.setVisible(true);
                    btnNorth.setText(textUI.getGame().getPlayer().getCurrentRoom().getExit("north").getName());
                    break;
                case "west":
                    btnWest.toFront();
                    btnWest.setVisible(true);
                    btnWest.setText(textUI.getGame().getPlayer().getCurrentRoom().getExit("west").getName());
                    break;
                case "south":
                    btnSouth.toFront();
                    btnSouth.setVisible(true);
                    btnSouth.setText(textUI.getGame().getPlayer().getCurrentRoom().getExit("south").getName());
                    break;
                case "east":
                    btnEast.toFront();
                    btnEast.setVisible(true);
                    btnEast.setText(textUI.getGame().getPlayer().getCurrentRoom().getExit("east").getName());
                    break;
            }
        }
    }
    /*
    Uses an for each loop to get all the items and their images, for the current
    room.
    When the image is clicked and the inventory is empty, the iamge of the item
    is places in the Inventory, and the processCommand gets the "take" command.
    
    */
    private void printItems() {
        for (Item item : textUI.getGame().getPlayer().getCurrentRoom().getItems()) {
            ImageView img = item.getImage();
            paneRoom.getChildren().add(img);

            img.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    if (textUI.getGame().getPlayer().getInventory() == null) {
                        imgInventory.setImage(img.getImage());
                        paneRoom.getChildren().remove(img);
                    }
                    processCommand("take " + item.getName());
                }
            });
        }
    }
    
    /*
    Makes the user unable to click on the item after the game is lost or won
    */
    private void disableItems() {
        for (Item item : textUI.getGame().getPlayer().getCurrentRoom().getItems()) {
            ImageView img = item.getImage();
            img.setDisable(true);
        }
    }
    /*
    Removes the items for a given room.
    Used to redraw the room, when the player changes room.
    */
    private void removeItems(Room whereToRemoveItemsFrom) {
        for (Item item : whereToRemoveItemsFrom.getItems()) {
            ImageView img = item.getImage();
            paneRoom.getChildren().remove(img);
        }
    }

    /*
    Disables the images and buttons.
    Used when the game is won or lost.
    */
    private void disableGame() {
        btnNorth.setDisable(true);
        btnWest.setDisable(true);
        btnSouth.setDisable(true);
        btnEast.setDisable(true);
        btnUse.setDisable(true);
        btnDrop.setDisable(true);
        btnInspect.setDisable(true);
        btnHelp.setDisable(true);

        btnNorth.setVisible(false);
        btnWest.setVisible(false);
        btnSouth.setVisible(false);
        btnEast.setVisible(false);

    }
    
    /*
    Sets the background image.
    */
    private void setBackground() {
        ImageView img = textUI.getGame().getPlayer().getCurrentRoom().getImage();
        imgBackground.setImage(img.getImage());
    }

    /*
    Checks if the current room contains fire.
    if the room contains fire, it places fire images acoording to the lvl of 
    the fire.
    */
    private void printFire() {
        Fire fire = textUI.getGame().getPlayer().getCurrentRoom().getFire();
        if (fire != null) {

            for (int i = 0; i < fire.getLvl() * 3; i++) {
                ImageView imgFire = new ImageView(Fire.IMAGE_FIRE);
                imgFire.fitHeightProperty().set(100);
                imgFire.fitWidthProperty().set(60);
                imgFire.setTranslateX(80 + Math.random() * 480);
                imgFire.setTranslateY(30 + Math.random() * 330);
                paneRoom.getChildren().add(imgFire);
            }
        }
    }

    /*
    removes the nodes with the fire from the room.
    */
    private void removeFire() {
        ArrayList<Node> nodesToRemove = new ArrayList<>();

        //Alle imageViews af ild registreres og gemmes i nodesToRemove
        for (Node node : paneRoom.getChildren()) {
            if (node instanceof ImageView) {
                if (((ImageView) node).getImage().equals(Fire.IMAGE_FIRE)) {
                    nodesToRemove.add(node);
                }
            }
        }

        paneRoom.getChildren().removeAll(nodesToRemove);

    }

    //Method to control wich ImageViews are removes when the fire is gone
    private void updateFireImgs() {
        ArrayList<Node> nodesToRemove = new ArrayList<>();
        
        //All imageViews of fire registres and saves in nodesToRemove
        for (Node node : paneRoom.getChildren()) {
            if (node instanceof ImageView) {
                if (((ImageView) node).getImage().equals(Fire.IMAGE_FIRE)) {
                    nodesToRemove.add(node);
                }
            }
        }

        /*
        Removes fire according to the extinguished fire in the current room, 
        and updates the players progress if the player manage to completely 
        remove the fire.
        
        */
        Fire fireInCurrentRoom = textUI.getGame().getPlayer().getCurrentRoom().getFire();
        if (fireInCurrentRoom == null) {
            if (!nodesToRemove.isEmpty()) {
                textUI.getGame().getPlayer().raiseProgress();
            }
            paneRoom.getChildren().removeAll(nodesToRemove);
        } else {
            for (int i = 0; i < nodesToRemove.size() - fireInCurrentRoom.getLvl() * 3; i++) {
                paneRoom.getChildren().remove(nodesToRemove.get(i));
            }
        }
    }
    /*
    Prints the imageViews of the smokes if the room contains smoke.
    */
    private void printSmoke() {
        Smoke smoke = textUI.getGame().getPlayer().getCurrentRoom().getSmoke();
        if (smoke != null) {

            for (int i = 0; i < smoke.getLvl() * 5; i++) {
                ImageView imgSmoke = new ImageView(Smoke.IMAGE_SMOKE);
                imgSmoke.fitHeightProperty().set(80);
                imgSmoke.fitWidthProperty().set(120);
                imgSmoke.setTranslateX(80 + Math.random() * 480);
                imgSmoke.setTranslateY(30 + Math.random() * 330);
                paneRoom.getChildren().add(imgSmoke);
            }
        }
    }
    /*
    Makes an ArrayList and removes the smoke nodes.
    This method is used to remove smoke when the room changes.
    */
    private void removeSmoke() {
        ArrayList<Node> nodesToRemove = new ArrayList<>();

        //Alle imageViews af ild registreres og gemmes i nodesToRemove
        for (Node node : paneRoom.getChildren()) {
            if (node instanceof ImageView) {
                if (((ImageView) node).getImage().equals(Smoke.IMAGE_SMOKE)) {
                    nodesToRemove.add(node);
                }
            }
        }

        paneRoom.getChildren().removeAll(nodesToRemove);
    }
    /*
    Draws the healthbar, accordint to the players health multiplied with 1.5, 
    to set the width of the green bar.
    The Health text gets the players health and places it with a string.
    places the red and green bar at the front together with the health text.
    */
    private void drawHealthBar() {
        greenbar.setWidth(textUI.getGame().getPlayer().getHealth() * 1.5);
        healthText.setText(Integer.toString(textUI.getGame().getPlayer().getHealth()) + " " + "HP");
        redbar.toFront();
        greenbar.toFront();
        healthText.toFront();
    }
    /*
    Set the label with Stepcounter text to the stepcounter value from the player.
    */
    private void stepCounterText() {
        stepCounterText.setText("Step counter: " + Integer.toString(textUI.getGame().getPlayer().getStepCount()));
    }
    /*
    Disables the Items, and changes the caracter to the deead caracter. 
    Creates the button for the player to know he is dead, and asks the player
    to try agian, with yes and no buttons.
    if the Player clicks yes, a new stage is set, and start is runned again.
    */
    private void drawDeadStage() {
        disableItems();
        
        timon.setVisible(false);
        deadTimon.setVisible(true);

        isDead.setVisible(true);
        isDead.toFront();

        isDeadBtnNo.setVisible(true);
        isDeadBtnNo.toFront();
        isDeadBtnNo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.exit(0);
            }
        });

        isDeadBtnYes.setVisible(true);
        isDeadBtnYes.toFront();
        isDeadBtnYes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Stage primaryStage = (Stage) root.getScene().getWindow();
                primaryStage.close();

                Start start = new Start();
                try {
                    start.start(new Stage());
                } catch (Exception ex1) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        });
    }
    /*
    Draws the stage when the game is won. 
    Prints the score to show the player.
    Makes an "ok" button for the player, and when its pressed the player is
    redirectet to the start page.
    */
    private void drawWinStage() {
        hasWon.setVisible(true);
        hasWon.toFront();

        
        hasWonScore.setFont(Font.font("Calibri", FontWeight.BOLD, 13));
        hasWonScore.setText("Score: " + textUI.getGame().getPlayer().getPlayerScore());
        hasWonScore.setVisible(true);
        hasWonScore.toFront();

        hasWonBtnOk.setDisable(false);
        hasWonBtnOk.setVisible(true);
        hasWonBtnOk.toFront();
        hasWonBtnOk.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Stage primaryStage = (Stage) root.getScene().getWindow();
                primaryStage.close();

                Start start = new Start();
                try {
                    start.start(new Stage());
                } catch (Exception ex1) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        });
        
        txtAreaNPC.clear();
        txtAreaNPC.setVisible(true);
        txtAreaNPC.setPrefWidth(150);
        txtAreaNPC.appendText("Congratulations!");
        txtAreaNPC.appendText("\nYou have successfully"
                + "\ncompleted Fire Escape");
    }

    /*
    Sets the player score and wich is calculated from the stepCount as a bonus
    This method is only run when the player Win the game.
    Saves the highscore from a method in game that used dataAccess
    */
    public void highscore() {
        textUI.getGame().getPlayer().setPlayerScore();
        textUI.getGame().saveHighscore();
    }
    
    /*
    Updates the highscore as the game evolves, and the player uses items.
    Updates the current score for the player to see.
    */
    public void highscoreUpdater() {
        textUI.getGame().highscoreUpdater(textUI.getGame().getPlayer());
        lblScore.setText("Score: " + textUI.getGame().getPlayer().getPlayerScore());
    }
}