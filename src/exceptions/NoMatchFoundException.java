package exceptions;

// Thrown when no compatible donor found for a recipient's blood group
public class NoMatchFoundException extends Exception {

    public NoMatchFoundException(String message) {
        super(message);
    }
}