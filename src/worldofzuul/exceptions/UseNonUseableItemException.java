package worldofzuul.exceptions;

public class UseNonUseableItemException extends Exception{
    public UseNonUseableItemException() {
        super("Attempted to use an item of the NonUseableitem class.");
    }
}
