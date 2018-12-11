package exceptions;

import worldofzuul.Game;
import worldofzuulIO.TextUI;

public class PlayerWinException extends Exception {

    private TextUI textIO;

    public PlayerWinException() {
        super("You won the game!");
    }
}
