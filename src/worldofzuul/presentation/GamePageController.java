/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul.presentation;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Firefighter 007
 */
public class GamePageController implements Initializable {

    @FXML
    private TextArea TextAreaTop;
    @FXML
    private TextArea TextAreaStart;
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

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TextAreaTop.appendText("- You are now in your bedroom");
    }    

    @FXML
    private void buttonTake(ActionEvent event) {
        TextAreaStart.setText("This is the new start page");
    }

    @FXML
    private void buttonHelp(ActionEvent event) {
        TextAreaTop.setText("Im not helping you! Figure it out yourself");
    }

    @FXML
    private void buttonDrop(ActionEvent event) {
    }

    @FXML
    private void buttonInspect(ActionEvent event) {
    }
    
}
