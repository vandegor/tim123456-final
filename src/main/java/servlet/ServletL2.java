package servlet;

import ejb.InvoiceRepositoryL2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletL2", urlPatterns = {"/ServletL2"})
public class ServletL2 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        tryDo (req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        tryDo (req, resp);
    }

    private void tryDo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Do doIt = new Do (req, resp, InvoiceRepositoryL2.class, "java:module/InvoiceRepositoryL2Bean");
        doIt.tryDoId ();
    }

}
