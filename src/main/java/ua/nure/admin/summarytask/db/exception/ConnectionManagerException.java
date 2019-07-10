package ua.nure.admin.summarytask.db.exception;

public class ConnectionManagerException extends RuntimeException {
    public ConnectionManagerException() {
    }

    public ConnectionManagerException(String message) {
        super(message);
    }
}
