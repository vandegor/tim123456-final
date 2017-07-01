package servlet;

import exception.BadRequestException;
import exception.ConflictException;
import helper.ContextHelper;
import helper.ParamHelper;
import interfaces.HttpGET;
import interfaces.HttpPOST;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class Do {
    HttpServletRequest req;
    HttpServletResponse resp;
    ParamHelper parameters;
    Class beanInterface;
    String beanName;
    Object bean;

    public Do(HttpServletRequest req, HttpServletResponse resp, Object bean) {
        this.req = req;
        this.resp = resp;
        this.bean = bean;
        this.parameters = new ParamHelper (req.getParameterMap ());
    }

    public Do(HttpServletRequest req, HttpServletResponse resp, Class beanInterface, String beanName) {
        this.req = req;
        this.resp = resp;
        this.beanInterface = beanInterface;
        this.beanName = beanName;
        this.parameters = new ParamHelper (req.getParameterMap ());
    }

    public void tryDoId() throws IOException {
        try {
            doIt ();
        } catch (BadRequestException e) {
            e.printStackTrace ();
            resp.sendError (HttpServletResponse.SC_BAD_REQUEST, e.getMessage ());
        } catch (ConflictException e) {
            e.printStackTrace ();
            resp.sendError (HttpServletResponse.SC_CONFLICT, e.getMessage ());
        }
    }


    private void doIt() throws BadRequestException, ConflictException, IOException {
        try {
            this.bean = ContextHelper.lookup (req, beanInterface, beanName);
            for (Method method : beanInterface.getDeclaredMethods ()) {
                if (parameters.getString ("actionType").equals (method.getName ())) {
                    httpMethodcheck (method);
                    Object o = method.invoke (bean, parameters);
                    resp.getWriter ().write ((o == null ? "" : o).toString ());
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace ();
            throw new BadRequestException (e);
        } catch (InvocationTargetException e) {
            if (e.getCause () instanceof BadRequestException) {
                throw (BadRequestException) e.getCause ();
            } else if (e.getCause () instanceof ConflictException) {
                throw (ConflictException) e.getCause ();
            } else {
                throw new BadRequestException (e);
            }
        }
    }

    private void httpMethodcheck(Method method) throws BadRequestException {
        for (Annotation annotation : method.getAnnotations ()) {
            if ((annotation instanceof HttpPOST || annotation instanceof HttpGET)
                    && annotation.annotationType ().getSimpleName ().contains (req.getMethod ())) {
                return;
            }
        }
        throw new BadRequestException ("Method " + method.getName () + " can not be used by http " + req.getMethod () + " method type");
    }
}
