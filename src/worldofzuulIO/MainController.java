package worldofzuulIO;

import java.io.File;
import exceptions.NameInputException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import worldofzuul.Command;
import worldofzuul.Fire;
import worldofzuul.Game;
import items.Item;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import worldofzuul.Parser;
import worldofzuul.Room;

public class MainController implements Initializable {

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


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textUI = new TextUI(new Game(), txtAreaOutput);
    }

    @FXML
    private void btnStartEventHandler(ActionEvent event) throws IOException{
        //        btnStart.setVisible(false);
        //        btnHighscore.setVisible(false);


    }

    @FXML
    private void btnNorthEventHandler(ActionEvent event) {
        if (processCommand("go north")) {
            redrawRoom();
        }
    }

    @FXML
    private void btnWestEventHandler(ActionEvent event) {
        if (processCommand("go west")) {
            redrawRoom();
        }
    }

    @FXML
    private void btnSouthEventHandler(ActionEvent event) {
        if (processCommand("go south")) {
            redrawRoom();
        }
    }

    @FXML
    private void btnEastEventHandler(ActionEvent event) {
        if (processCommand("go east")) {
            redrawRoom();
        }
    }

    @FXML
    private void btnUseEventHandler(ActionEvent event) {
        processCommand("use " + (textUI.getGame().getPlayer().getInventory() != null ? textUI.getGame().getPlayer().getInventory().getName() : ""));

        if (textUI.getGame().getPlayer().getInventory() == null) {
            imgInventory.setImage(null);
        } else if (!(textUI.getGame().getPlayer().getInventory().getImage().equals(imgInventory))) {
            imgInventory.setImage(textUI.getGame().getPlayer().getInventory().getImage().getImage());
        }

        if (textUI.getGame().getPlayer().getCurrentRoom().getFire() == null) {
            removeFire();
        }

        drawHealthBar();
    }

    @FXML
    private void btnDropEventHandler(ActionEvent event) {
        removeItems(textUI.getGame().getPlayer().getCurrentRoom());
        processCommand("drop");
        printItems();
        imgInventory.setImage(null);
    }

    @FXML
    private void btnInspectEventHandler(ActionEvent event) {
        processCommand("inspect");
    }

    @FXML
    private void btnHelpEventHandler(ActionEvent event) {
        processCommand("help");
    }

    private void drawHealthBar() {
        greenbar.setWidth(textUI.getGame().getPlayer().getHealth() * 1.5);
        healthText.setText(Integer.toString(textUI.getGame().getPlayer().getHealth()) + " " + "HP");
    }

    private void stepCounterText() {
        stepCounterText.setText("Step counter: " + Integer.toString(textUI.getGame().getPlayer().getStepCount()));
    }

    private boolean processCommand(String inputLine) {
        boolean changedRoom = false;
        Parser parser = new Parser();
        txtAreaOutput.appendText("\n");
        Command command = parser.getCommand(inputLine);
        changedRoom = textUI.processCommand(command);
        lblCurrentRoom.setText(textUI.getGame().getPlayer().getCurrentRoom().getName());

        if (textUI.getGame().getPlayer().isDead()) {
            highscore();
            redrawRoom();
            disableGame();
            drawDeadStage();
        }

        if (textUI.getGame().getPlayer().hasWon()) {
            highscore();
            redrawRoom();
            disableGame();
            drawWinStage();
        }

        return changedRoom;
    }

    private void redrawRoom() {
        removeItems(textUI.getGame().getPlayer().getPreviousRoom());
        removeFire();
        printDirectionButtons();
        printItems();
        printFire();
        setBackground();
        drawHealthBar();
        stepCounterText();
    }

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

    private void removeItems(Room whereToRemoveItemsFrom) {
        for (Item item : whereToRemoveItemsFrom.getItems()) {
            ImageView img = item.getImage();
            paneRoom.getChildren().remove(img);
        }
    }

    private void disableGame() {
        //root.setDisable(true);
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

    private void setBackground() {
        ImageView img = textUI.getGame().getPlayer().getCurrentRoom().getImage();
        imgBackground.setImage(img.getImage());
    }

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

    private void removeFire() {
        ArrayList<Node> nodesToRemove = new ArrayList<>();

        for (Node node : paneRoom.getChildren()) {
            if (node instanceof ImageView) {
                if (((ImageView) node).getImage().equals(Fire.IMAGE_FIRE)) {
                    nodesToRemove.add(node);
                }
            }
        }

        paneRoom.getChildren().removeAll(nodesToRemove);
    }

    private void drawDeadStage() {
        timon.setVisible(false);
        deadTimon.setVisible(true);

        isDead.setVisible(true);

        isDeadBtnNo.setVisible(true);
        isDeadBtnNo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.exit(0);
            }
        });

        isDeadBtnYes.setVisible(true);
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

    private void drawWinStage() {
        hasWon.setVisible(true);

        hasWonScore.setText("Score: " + textUI.getGame().getPlayer().getPlayerScore());
        hasWonScore.setVisible(true);

        hasWonBtnOk.setDisable(false);
        hasWonBtnOk.setVisible(true);
        hasWonBtnOk.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    @FXML
    private void btnHighscore(ActionEvent event) {
        TextArea txtAreaIntro = new TextArea();
        txtAreaIntro.setEditable(false);
        txtAreaIntro.setFont(new Font("Calibri", 18));
        VBox highscoreRoot = new VBox();
        highscoreRoot.setAlignment(Pos.CENTER);
        highscoreRoot.setPadding(new Insets(10, 10, 10, 10));
        highscoreRoot.setSpacing(50);
        highscoreRoot.getChildren().add(txtAreaIntro);

        Scene scene = root.getScene();
        scene.setRoot(highscoreRoot);
        textUI.printHighscore(txtAreaIntro);
    }

    public void highscore() {
        textUI.getGame().getPlayer().setPlayerScore();
        textUI.getGame().saveHighscore();
    }
}
