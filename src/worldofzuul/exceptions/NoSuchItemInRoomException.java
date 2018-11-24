package worldofzuul.exceptions;

public class NoSuchItemInRoomException extends Exception{
    public NoSuchItemInRoomException(){
        super("Attempted to pick up non-existing item");
    }
}
