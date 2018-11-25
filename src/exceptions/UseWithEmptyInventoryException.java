package exceptions;

public class UseWithEmptyInventoryException extends Exception {

    public UseWithEmptyInventoryException() {
        super("Attempted to use an item with an empty inventory.");
    }
}
