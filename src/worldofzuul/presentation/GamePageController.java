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
import worldofzuul.domain.Player;
import worldofzuul.domain.Room;

/**
 * FXML Controller class
 *
 * @author Firefighter 007
 */
public class GamePageController implements Initializable {
    private Room room;
    private Room bedroom, hallway, sistersRoom, livingRoom, wc, wc2, outside, window,
            office, kitchen, entrance, conservatory, basement, garage, masterBedroom;
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
            createRooms();
////        room.createItems();
////        room.createFire();
        Player player = new Player(bedroom);
        TextAreaTop.appendText(player.getCurrentRoom().getLongDescription());
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
     public void createRooms() {
        bedroom = new Room("in your smokefilled bedroom", 0, false);
        hallway = new Room("in the hallway with your sisters room, the door to the toilet and the staircase to downstairs", 0, false);
        sistersRoom = new Room("in your sister's room", 0, false);
        livingRoom = new Room("in the living room", 0, false);
        wc = new Room("on the toilet", 0, false);
        wc2 = new Room("on the toilet", 0, false);
        outside = new Room("outside", 0, true);
        window = new Room("jumping out of the window! \nYou took a fatal hit to your head", 0, false);
        office = new Room("in the office", 0, false);
        kitchen = new Room("in the kitchen", 0, false);
        entrance = new Room("in the entrace", 0, false);
        conservatory = new Room("in the conservatory", 0, false);
        basement = new Room("in the basement", 0, false);
        garage = new Room("in the garage", 0, false);
        masterBedroom = new Room("in the master bedroom", 0, false);

        bedroom.setExit("hallway", hallway);
        bedroom.setExit("window", window);

        hallway.setExit("bedroom", bedroom);
        hallway.setExit("sister-room", sistersRoom);
        hallway.setExit("downstairs", livingRoom);
        hallway.setExit("toilet", wc);

        sistersRoom.setExit("hallway", hallway);

        wc.setExit("hallway", hallway);

        livingRoom.setExit("upstairs", hallway);
        livingRoom.setExit("basement", basement);
        livingRoom.setExit("kitchen", kitchen);
        livingRoom.setExit("office", office);

        basement.setExit("livingroom", livingRoom);
        basement.setExit("garage", garage);

        garage.setExit("basement", basement);

        kitchen.setExit("livingroom", livingRoom);
        kitchen.setExit("entrance", entrance);
        kitchen.setExit("conservatory", conservatory);

        conservatory.setExit("kitchen", kitchen);

        entrance.setExit("kitchen", kitchen);
        entrance.setExit("outside", outside);

        office.setExit("livingroom", livingRoom);
        office.setExit("master-bedroom", masterBedroom);

        masterBedroom.setExit("office", office);
        masterBedroom.setExit("toilet", wc2);

        wc2.setExit("master-bedroom", masterBedroom);
    }
    
}
