/**
 *
 * @author Alexander Nguyen, Jacob Wowk, Morten K. Jensen and Thomas S. Laursen
 * @version 2018.12.14
 * 
 */

package worldofzuul.presentation;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;

public class printWithPacingEventHandler implements EventHandler<ActionEvent> {

    private TextArea txtAreaOutput;
    private char[] charsToPrint;
    private int index;

    public printWithPacingEventHandler(String textToPrint, TextArea txtAreaOutput) {
        charsToPrint = textToPrint.toCharArray();
        index = 0;
        this.txtAreaOutput = txtAreaOutput;
    }

    @Override
    public void handle(ActionEvent event) {
        txtAreaOutput.appendText(Character.toString(charsToPrint[index]));
        index++;
    }

}
