package exceptions;

public class NoItemToDropException extends Exception {
    public NoItemToDropException() {
        super("Attempted to drop item with empty inventory.");
    }
}
