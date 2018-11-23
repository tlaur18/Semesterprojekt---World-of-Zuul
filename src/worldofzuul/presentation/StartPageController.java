/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul.presentation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import worldofzuul.domain.Game;

/**
 * FXML Controller class
 *
 * @author Morten K. Jensen
 */
public class StartPageController implements Initializable {

    @FXML
    private Button start1;
    @FXML
    private Button start2;
    @FXML
    private Button start3;
    @FXML
    private Button start4;
    @FXML
    private TextArea titel;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Game game = new Game();
            game.getRooms();
        } catch (InterruptedException ex) {
            Logger.getLogger(StartPageController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void setScene(Scene scene1) {
    }

    @FXML
    private void start1(ActionEvent event) throws IOException {
        Parent root1 = FXMLLoader.load(getClass().getResource("WelcomeTextXML.fxml"));
        Scene scene2 = new Scene(root1);
        Stage nextstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        nextstage.setScene(scene2);
        nextstage.show();
    }

    @FXML
    private void start2(ActionEvent event) {
    }

    @FXML
    private void start3(ActionEvent event) {
    }

    @FXML
    private void start4(ActionEvent event) {
    }

}
