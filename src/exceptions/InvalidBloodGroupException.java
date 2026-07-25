package exceptions;

// Thrown when user enters a blood group that doesn't exist
public class InvalidBloodGroupException extends Exception {

    public InvalidBloodGroupException(String message) {
        super(message);
    }
}