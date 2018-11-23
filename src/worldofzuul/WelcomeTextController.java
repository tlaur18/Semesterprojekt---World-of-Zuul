/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

import static javafx.util.Duration.seconds;

/**
 * FXML Controller class
 *
 * @author Morten K. Jensen
 */
public class WelcomeTextController implements Initializable {

    @FXML
    private TextArea TextAreaTop;
    @FXML
    private Button takeButton;
    @FXML
    private Button helpButton;
    @FXML
    private Button dropButton;
    @FXML
    private Button inspectButton;
    @FXML
    private ImageView inventoryImage;
    @FXML
    private TextArea TextAreaStart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void buttonTake(ActionEvent event) throws IOException, InterruptedException {
        
    }

    @FXML
    private void buttonHelp(ActionEvent event) {
    }

    @FXML
    private void buttonDrop(ActionEvent event) {
    }

    @FXML
    private void buttonInspect(ActionEvent event) {
    }

}
