/**
 *
 * PrintWithPacinEventHandler is used when the printing of text needs to be with delays between each letter.
 * Used with a TimeLine.
 * Contains a 'handle'-method which is execued e certain amount of times with a certain delay in between.
 * Used in TextUI.java when printing the welcome text.
 * Inspiration drawn from following site:
 * https://stackoverflow.com/questions/9966136/javafx-periodic-background-task/9966213#9966213
 *
 * @author Alexander Nguyen, Jacob Wowk, Morten K. Jensen, Thomas S. Laursen
 * @version 2018.12.14
 *
 */
package worldofzuulIO;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;

public class printWithPacingEventHandler implements EventHandler<ActionEvent> {

    private TextArea txtAreaOutput;
    private char[] charsToPrint;
    private int index;

    /*
    * The contructor takes a String and a TextArea as arguments.
    * The String is split up as a Char[] and saved in 'charsToPrint'.
    * 'index' is initialized. Used to keep track of amount of executed cycles.
    * TextArea is where the text is to be printed.
    */
    public printWithPacingEventHandler(String textToPrint, TextArea txtAreaOutput) {
        charsToPrint = textToPrint.toCharArray();
        index = 0;
        this.txtAreaOutput = txtAreaOutput;
    }

    /*
    * This method is cycled through a certain amount of times.
    * The cycleCount is determined when creating the TimeLine.
    * A delay of a certain amount fo time is between each cycle.
    * The duration of this delay is determined when creating the TimeLine.
    */
    @Override
    public void handle(ActionEvent event) {
        //Prints the char number 'index'.
        txtAreaOutput.appendText(Character.toString(charsToPrint[index]));
        
        //Raises the index by 1, making it print the follwing char next cycle.
        index++;
    }

}
