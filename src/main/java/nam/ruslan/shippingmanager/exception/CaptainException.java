package nam.ruslan.shippingmanager.exception;

public class CaptainException extends RuntimeException{
    public CaptainException(String message) {
        super(message);
    }

    public CaptainException(String message, Throwable cause) {
        super(message, cause);
    }
}
