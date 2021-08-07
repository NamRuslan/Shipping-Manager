package nam.ruslan.shippingmanager.exception;

public class PortIsFullException extends RuntimeException{
    public PortIsFullException(String message) {
        super(message);
    }

    public PortIsFullException(String message, Throwable cause) {
        super(message, cause);
    }
}
