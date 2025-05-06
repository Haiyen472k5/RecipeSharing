package org.example.recipes.Exception;

public class UsernameExistException extends AppException {
    public UsernameExistException() {
        super();
    }

    public UsernameExistException(String message) {
        super(message);
    }

    public UsernameExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameExistException(Throwable cause) {
        super(cause);
    }
}
