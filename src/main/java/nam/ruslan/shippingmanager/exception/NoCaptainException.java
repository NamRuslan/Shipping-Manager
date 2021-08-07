package nam.ruslan.shippingmanager.exception;

public class NoCaptainException extends RuntimeException{
    public NoCaptainException(String message) {
        super(message);
    }

    public NoCaptainException(String message, Throwable cause) {
        super(message, cause);
    }
}
