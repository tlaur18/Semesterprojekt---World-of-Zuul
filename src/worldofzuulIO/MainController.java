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
    private TextField txtFieldInput;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textIO = new TextIO(new Game(), txtAreaOutput);
    }

    @FXML
    private void txtFieldInputEventHandler(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            processCommand(txtFieldInput.getText());
        }
    }

    @FXML
    private void btnStartEventHandler(ActionEvent event) {
        lblCurrentRoom.setVisible(true);
        btnStart.setVisible(false);
        txtAreaOutput.setVisible(true);
        txtFieldInput.setVisible(true);
        printButtons();
        textIO.printWelcome();
    }

    @FXML
    private void btnNorthEventHandler(ActionEvent event) {
        processCommand("go north");
        printButtons();
    }

    @FXML
    private void btnWestEventHandler(ActionEvent event) {
        processCommand("go west");
        printButtons();
    }

    @FXML
    private void btnSouthEventHandler(ActionEvent event) {
        processCommand("go south");
        printButtons();
    }

    @FXML
    private void btnEastEventHandler(ActionEvent event) {
        processCommand("go east");
        printButtons();
    }
    
    private void processCommand(String inputLine) {
        Parser parser = new Parser();
        txtAreaOutput.appendText("\n");
        Command command = parser.getCommand(inputLine);
        textIO.processCommand(command);
        lblCurrentRoom.setText(textIO.getGame().getPlayer().getCurrentRoom().getName());
        txtFieldInput.clear();
    }

    private void printButtons() {
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
