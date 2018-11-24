package worldofzuul.exceptions;

public class PlayerInventoryFullException extends Exception {

    public PlayerInventoryFullException() {
        super("Attempted to take item while invenory was full.");
    }
}
