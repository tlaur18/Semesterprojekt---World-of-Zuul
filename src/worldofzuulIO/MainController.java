package worldofzuulIO;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import worldofzuul.Command;
import worldofzuul.Game;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textIO = new TextIO(new Game(), txtAreaOutput);
    }

    @FXML
    private void btnStartEventHandler(ActionEvent event) {
        lblCurrentRoom.setVisible(true);
        btnStart.setVisible(false);
        txtAreaOutput.setVisible(true);
        imgPhone.setVisible(true);
        btnTake.setVisible(true);
        btnDrop.setVisible(true);
        btnInspect.setVisible(true);
        btnHelp.setVisible(true);
        printDirectionButtons();
        textIO.printWelcome();
    }

    @FXML
    private void btnNorthEventHandler(ActionEvent event) {
        processCommand("go north");
        printDirectionButtons();
    }

    @FXML
    private void btnWestEventHandler(ActionEvent event) {
        processCommand("go west");
        printDirectionButtons();
    }

    @FXML
    private void btnSouthEventHandler(ActionEvent event) {
        processCommand("go south");
        printDirectionButtons();
    }

    @FXML
    private void btnEastEventHandler(ActionEvent event) {
        processCommand("go east");
        printDirectionButtons();
    }

    @FXML
    private void btnTakeEventHandler(ActionEvent event) {
    }

    @FXML
    private void btnDropEventHandler(ActionEvent event) {
    }

    @FXML
    private void btnInspectEventHandler(ActionEvent event) {
    }

    @FXML
    private void btnHelpEventHandler(ActionEvent event) {
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
                    break;
                case "west":
                    btnWest.setVisible(true);
                    break;
                case "south":
                    btnSouth.setVisible(true);
                    break;
                case "east":
                    btnEast.setVisible(true);
                    break;
            }
        }
    }
}
