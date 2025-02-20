package engulfy.error;

/**
 * Custom exception class for errors related to the Engulfy application.
 * This class extends {@link Exception} and is used to signal specific errors in the application.
 */
public class EngulfyError extends Exception {
    /**
     * Constructs an EngulfyError with the specified error message.
     *
     * @param message the detail message describing the error
     */
    public EngulfyError(String message) {
        super(message);
        assert message != null : "Error message cannot be null";
    }
}
