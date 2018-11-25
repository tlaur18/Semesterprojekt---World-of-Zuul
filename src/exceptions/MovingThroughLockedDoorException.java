package exceptions;

public class MovingThroughLockedDoorException extends Exception {

    public MovingThroughLockedDoorException() {
        super("Attempted to go through a locked door.");
    }
}
