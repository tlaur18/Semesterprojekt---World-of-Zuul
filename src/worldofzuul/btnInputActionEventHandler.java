package worldofzuul;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import worldofzuulIO.TextIO;

public class btnInputActionEventHandler implements EventHandler<ActionEvent> {

    private TextField txtField;
    private Parser parser;
    private TextIO textIO;
    private Label label;
    private TextArea txtArea;

    public btnInputActionEventHandler(TextIO textIO, TextField txtField, Label label, TextArea txtArea) {
        this.textIO = textIO;
        this.txtField = txtField;
        this.label = label;
        this.txtArea = txtArea;
        parser = new Parser();
    }
    
    @Override
    public void handle(ActionEvent event) {
        txtArea.appendText("\n");
        String userInput = txtField.getText();
        Command command = parser.getCommand(userInput);
        textIO.processCommand(command);
        label.setText(textIO.getGame().getPlayer().getCurrentRoom().getName());
        txtField.clear();
    }

}
