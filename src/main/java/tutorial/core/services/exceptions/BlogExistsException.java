package tutorial.core.services.exceptions;

/**
 * Created by Radek on 2015-08-30.
 */
public class BlogExistsException extends RuntimeException {
    public BlogExistsException() {
    }

    public BlogExistsException(String message) {
        super(message);
    }

    public BlogExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public BlogExistsException(Throwable cause) {
        super(cause);
    }
}
