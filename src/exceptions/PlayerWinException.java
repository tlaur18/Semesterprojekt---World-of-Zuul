package exceptions;

import worldofzuul.Game;
import worldofzuulIO.TextIO;

public class PlayerWinException extends Exception {

    private TextIO textIO;

    public PlayerWinException() {
        super("You won the game!");
    }
}
