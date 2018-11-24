package worldofzuul.exceptions;

public class MovingThroughFireException extends Exception {

    public MovingThroughFireException() {
        super("Attempted to walk throug an exit blocked by fire.");
    }
}
