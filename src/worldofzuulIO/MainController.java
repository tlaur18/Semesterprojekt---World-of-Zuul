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
            Parser parser = new Parser();
            txtAreaOutput.appendText("\n");
            String userInput = txtFieldInput.getText();
            Command command = parser.getCommand(userInput);
            textIO.processCommand(command);
            lblCurrentRoom.setText(textIO.getGame().getPlayer().getCurrentRoom().getName());
            txtFieldInput.clear();
        }
    }

    @FXML
    private void btnStartEventHandler(ActionEvent event) {
        lblCurrentRoom.setVisible(true);
        btnStart.setVisible(false);
        txtAreaOutput.setVisible(true);
        txtFieldInput.setVisible(true);
        textIO.printWelcome();
    }

    @FXML
    private void btnNorthEventHandler(ActionEvent event) {
    }

    @FXML
    private void btnWestEventHandler(ActionEvent event) {
    }

    @FXML
    private void btnSouthEventHandler(ActionEvent event) {
    }

    @FXML
    private void btnEastEventHandler(ActionEvent event) {
    }

}
