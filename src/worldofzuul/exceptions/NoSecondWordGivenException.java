package worldofzuul.exceptions;

public class NoSecondWordGivenException extends Exception {

    public NoSecondWordGivenException() {
        super("No second word was given when one was needed.");
    }

}
