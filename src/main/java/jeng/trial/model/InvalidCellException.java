package jeng.trial.model;

/**
 *
 * Note: I use Runtime to avoid forced catching. This can be opinionated.
 *
 * Created by abelsr on 11/06/2016.
 */
public class InvalidCellException extends RuntimeException {

    public InvalidCellException() {
    }

    public InvalidCellException(String message) {
        super(message);
    }

    public InvalidCellException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidCellException(Throwable cause) {
        super(cause);
    }
}
