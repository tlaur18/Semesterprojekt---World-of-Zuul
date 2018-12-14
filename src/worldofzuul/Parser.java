/**
 *
 * The Parser is used to recognize which commands are received from the user.
 * Given a string, the parser is able to return a Command constructed from this String.
 *
 * @author Alexander Nguyen, Jacob Wowk, Morten K. Jensen, Thomas S. Laursen
 * @version 2018.12.14
 *
 */
package worldofzuul;

import java.util.Scanner;

public class Parser {

    private CommandWords commands;
    
    /*
    * The constructer initializes the parser's CommandWords attribute by creating a new one.
    * The CommandWords is used to get the CommandWord of the user input.
    */
    public Parser() {
        commands = new CommandWords();
    }

    /*
    * The 'getCommand'-method takes a String as parameter.
    * This method is called when in the 'processCommand'-method in MainController.java
    * and is used to get the command to be processed in TextUI.java.    
    * The content of the String-parameter depends on which button the user presses.
    */
    public Command getCommand(String inputLine) {
        String word1 = null;
        String word2 = null;

        //A Scanner is used to extract the two first words of the inputLine.
        Scanner tokenizer = new Scanner(inputLine);
        if (tokenizer.hasNext()) {
            word1 = tokenizer.next();
            if (tokenizer.hasNext()) {
                word2 = tokenizer.next();
            }
        }

        //A new Command constructed by the two words are returned.
        return new Command(commands.getCommandWord(word1), word2);
    }
}
