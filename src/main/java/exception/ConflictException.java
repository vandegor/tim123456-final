package exception;

import javax.ejb.ApplicationException;
import javax.xml.ws.WebFault;

@ApplicationException(rollback = true)
@WebFault
public class ConflictException extends Exception {
    public ConflictException() {
    }

    public ConflictException(String message) {
        super (message);
    }

    public ConflictException(String message, Throwable cause) {
        super (message, cause);
    }

    public ConflictException(Throwable cause) {
        super (cause);
    }

    public ConflictException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super (message, cause, enableSuppression, writableStackTrace);
    }
}
