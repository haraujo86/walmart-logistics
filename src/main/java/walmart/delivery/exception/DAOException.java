package walmart.delivery.exception;

/**
 * Exception used to report errors to access database
 * 
 * @author haraujo
 * 
 */
public class DAOException extends Exception {

    private static final long serialVersionUID = -4369141623368640241L;

    public DAOException() {
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

}