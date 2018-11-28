package worldofzuulIO;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import worldofzuul.Command;
import worldofzuul.Game;
import worldofzuul.Item;
import worldofzuul.Parser;

public class MainController implements Initializable {

    TextIO textIO;

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
    private Button btnTake;
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
    private Pane paneRoom;
    @FXML
    private ImageView imgInventory;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textIO = new TextIO(new Game(), txtAreaOutput);
    }

    @FXML
    private void btnStartEventHandler(ActionEvent event) {
        lblCurrentRoom.setVisible(true);
        btnStart.setVisible(false);
        btnTake.setDisable(false);
        btnDrop.setDisable(false);
        btnInspect.setDisable(false);
        btnHelp.setDisable(false);
        lblInventoryHeadline.setDisable(false);
        imgBackground.setVisible(true);
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
    private void btnTakeEventHandler(ActionEvent event) {

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
        Parser parser = new Parser();
        txtAreaOutput.appendText("\n");
        Command command = parser.getCommand(inputLine);
        textIO.processCommand(command);
        lblCurrentRoom.setText(textIO.getGame().getPlayer().getCurrentRoom().getName());
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
}
