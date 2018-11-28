package exceptions;

public class PlayerDiedException extends Exception {
    public PlayerDiedException() {
        super("Player is dead.");
    }
}
