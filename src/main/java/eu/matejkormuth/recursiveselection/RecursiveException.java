package eu.matejkormuth.recursiveselection;

public class RecursiveException extends Exception {
    public RecursiveException() {
    }

    public RecursiveException(String message) {
        super(message);
    }

    public RecursiveException(String message, Throwable cause) {
        super(message, cause);
    }

    public RecursiveException(Throwable cause) {
        super(cause);
    }

    public RecursiveException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
