/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul.presentation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

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
    private Button buttonStart;
    @FXML
    private TextArea TextAreaStart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TextAreaStart.appendText("\t BIP BIP BIP! There is a loud noise that woke you up, \n \t you notice the smell and the thin \n \t layer of smoke in you room.\n");
        TextAreaStart.appendText("\t The first thing you do is to take your cellphone and call for emergency,\n \t the number is 1-1-2.");
        TextAreaStart.appendText("112: \n- This is 1-1-2. What is your emergency? \n");
        TextAreaStart.appendText("\n");
        TextAreaStart.appendText("You:\n- There is smoke in the room and I am all alone in the house.\n");
        TextAreaStart.appendText("\n");
        TextAreaStart.appendText("112: \n- Okay, just stay calm and lets get you to safety.\n");
        TextAreaStart.appendText("- It doesn't help to panic.\n\n");
        TextAreaStart.appendText("You:\n -I will try my best with your help.\n\n");
        TextAreaStart.appendText("112: \n- You need to get to safety and thats your primary objective.\n");
        TextAreaStart.appendText("- You will encoutner some obstacles, and you will need to figure a way out of the house\n");
        TextAreaStart.appendText("\n- To start Press the \"Start \" button in the bottom right corner");
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
    @FXML
    private void buttonStart(ActionEvent event) throws IOException{
        Parent root1 = FXMLLoader.load(getClass().getResource("GamePage.fxml"));
        Scene scene2 = new Scene(root1);
        Stage nextstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        nextstage.setScene(scene2);
        nextstage.show();
    }
    
}
