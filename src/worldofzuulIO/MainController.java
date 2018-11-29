package worldofzuulIO;

import exceptions.PlayerDiedException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import worldofzuul.Command;
import worldofzuul.Game;
import worldofzuul.Item;
import worldofzuul.Parser;

public class MainController implements Initializable {

    private TextIO textIO;

    @FXML
    private BorderPane root;
    @FXML
    private TextArea txtAreaOutput;
    @FXML
    private Button btnStart;
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
    private Pane paneRoom;
    @FXML
    private ImageView imgInventory;
    @FXML
    private Button btnUse;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textIO = new TextIO(new Game(), txtAreaOutput);
    }

    @FXML
    private void btnStartEventHandler(ActionEvent event) {
        lblCurrentRoom.setVisible(true);
        btnStart.setVisible(false);
        btnUse.setDisable(false);
        btnDrop.setDisable(false);
        btnInspect.setDisable(false);
        btnHelp.setDisable(false);
        lblInventoryHeadline.setDisable(false);
        imgBackground.setVisible(true);
        timon.setVisible(true);
        printDirectionButtons();
        textIO.printWelcome();
        printItems();
    }

    @FXML
    private void btnNorthEventHandler(ActionEvent event) {
        removeItems();
        processCommand("go north");
        printDirectionButtons();
        printItems();
    }

    @FXML
    private void btnWestEventHandler(ActionEvent event) {
        removeItems();
        processCommand("go west");
        printDirectionButtons();
        printItems();
    }

    @FXML
    private void btnSouthEventHandler(ActionEvent event) {
        removeItems();
        processCommand("go south");
        printDirectionButtons();
        printItems();
    }

    @FXML
    private void btnEastEventHandler(ActionEvent event) {
        removeItems();
        processCommand("go east");
        printDirectionButtons();
        printItems();
    }

    @FXML
    private void btnUseEventHandler(ActionEvent event) {
        processCommand("use " + (textIO.getGame().getPlayer().getInventory() != null ? textIO.getGame().getPlayer().getInventory().getName() : ""));
        if (textIO.getGame().getPlayer().getInventory() == null) {
            imgInventory.setImage(null);
        }
    }

    @FXML
    private void btnDropEventHandler(ActionEvent event) {
        removeItems();
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

    private void processCommand(String inputLine) {
        try {
            Parser parser = new Parser();
            txtAreaOutput.appendText("\n");
            Command command = parser.getCommand(inputLine);
            textIO.processCommand(command);
            lblCurrentRoom.setText(textIO.getGame().getPlayer().getCurrentRoom().getName());
        } catch (PlayerDiedException ex) {
            txtAreaOutput.appendText("\nYou died...");
            disableGame();

            Label lblDead = new Label();
            lblDead.setText("You died. Would you like to try again?");

            Button btnYes = new Button();
            btnYes.setText("Yes");

            Button btnNo = new Button();
            btnNo.setText("No");
            btnNo.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    System.exit(0);
                }
            });

            HBox hBox = new HBox();
            hBox.getChildren().add(btnYes);
            hBox.getChildren().add(btnNo);
            hBox.setAlignment(Pos.CENTER);
            hBox.setPadding(new Insets(10, 10, 10, 10));
            hBox.setSpacing(10);

            VBox vBox = new VBox();
            vBox.getChildren().add(lblDead);
            vBox.getChildren().add(hBox);
            vBox.setAlignment(Pos.CENTER);

            Scene scene = new Scene(vBox);

            Stage deadStage = new Stage();
            deadStage.setScene(scene);
            deadStage.setHeight(150);
            deadStage.setWidth(300);
            deadStage.setTitle("Try again?");
            deadStage.show();

            btnYes.setOnAction(new EventHandler<ActionEvent>() {
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

                    deadStage.close();
                }
            });
        }
    }

    private void printDirectionButtons() {
        btnNorth.setVisible(false);
        btnWest.setVisible(false);
        btnSouth.setVisible(false);
        btnEast.setVisible(false);

        for (String exitString : textIO.getGame().getPlayer().getCurrentRoom().getExits().keySet()) {
            switch (exitString) {
                case "north":
                    btnNorth.setVisible(true);
                    btnNorth.setText(textIO.getGame().getPlayer().getCurrentRoom().getExit("north").getName());
                    break;
                case "west":
                    btnWest.setVisible(true);
                    btnWest.setText(textIO.getGame().getPlayer().getCurrentRoom().getExit("west").getName());
                    break;
                case "south":
                    btnSouth.setVisible(true);
                    btnSouth.setText(textIO.getGame().getPlayer().getCurrentRoom().getExit("south").getName());
                    break;
                case "east":
                    btnEast.setVisible(true);
                    btnEast.setText(textIO.getGame().getPlayer().getCurrentRoom().getExit("east").getName());
                    break;
            }
        }
    }

    private void printItems() {
        for (Item item : textIO.getGame().getPlayer().getCurrentRoom().getItems()) {
            ImageView img = item.getImage();
            paneRoom.getChildren().add(img);

            img.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    if (textIO.getGame().getPlayer().getInventory() == null) {
                        imgInventory.setImage(img.getImage());
                        paneRoom.getChildren().remove(img);
                    }
                    processCommand("take " + item.getName());
                }
            });
        }
    }

    private void removeItems() {
        for (Item item : textIO.getGame().getPlayer().getCurrentRoom().getItems()) {
            ImageView img = item.getImage();
            paneRoom.getChildren().remove(img);
        }
    }

    private void disableGame() {
        root.setDisable(true);
    }
}
