package nam.ruslan.shippingmanager.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String detailMessage) {
        super(detailMessage);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
