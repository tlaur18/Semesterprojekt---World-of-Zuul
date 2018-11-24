package worldofzuul.exceptions;

public class NoExitException extends Exception{
    public NoExitException() {
        super("Attempted to walk through a non-existing exit.");
    }
}
