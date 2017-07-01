package exception;

import javax.ejb.ApplicationException;
import javax.xml.ws.WebFault;

@ApplicationException(rollback = true)
@WebFault
public class BadRequestException extends Exception {

    public BadRequestException(String message) {
        super (message);
    }

    public BadRequestException(String message, Throwable cause) {
        super (message, cause);
    }

    public BadRequestException(Throwable cause) {
        super (cause);
    }

    public BadRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super (message, cause, enableSuppression, writableStackTrace);
    }
}
