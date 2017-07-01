package helper;

import exception.BadRequestException;
import interfaces.SessionKey;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

public class ContextHelper {
    public static <T> T lookup(HttpServletRequest httpServletRequest, Class<T> clazz, String name) throws BadRequestException {
        String sessionKey = clazz.getAnnotation (SessionKey.class).value ();
        Object o = httpServletRequest.getSession ().getAttribute (sessionKey);
        try {
            if (o == null) {
                InitialContext initialContext = new InitialContext ();
                o = initialContext.lookup (name);
                httpServletRequest.getSession ().setAttribute (sessionKey, o);
            }
        } catch (NamingException e) {
            throw new BadRequestException (e);
        }
        return (T) o;
    }
}
