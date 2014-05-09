package nl.neersel.webflow.exception;

/**
 * Created by robert on 9-5-14.
 */
public class InvalidMoveOperationException extends Exception {
    public InvalidMoveOperationException(String message, Exception exception) {
        super(message, exception);
    }
}
