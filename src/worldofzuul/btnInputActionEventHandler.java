package worldofzuul;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import worldofzuulIO.TextIO;

public class btnInputActionEventHandler implements EventHandler<ActionEvent> {

    private TextIO textIO;
    private Label lblCurrentRoom;
    private Button btnStart;

    public btnInputActionEventHandler(TextIO textIO, Label lblCurrentRoom, Button btnStart) {
        this.textIO = textIO;
        this.lblCurrentRoom = lblCurrentRoom;
        this.btnStart = btnStart;
    }

    @Override
    public void handle(ActionEvent event) {
        try {
            textIO.play();
        } catch (InterruptedException ex) {
            Logger.getLogger(btnInputActionEventHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        lblCurrentRoom.setVisible(true);
        btnStart.setVisible(false);
        
    }

}
