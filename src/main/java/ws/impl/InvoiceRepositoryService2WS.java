package ws.impl;


import ejb.InvoiceRepositoryStateless;
import exception.BadRequestException;
import exception.ConflictException;
import model.Product;
import ws.InvoiceRepositoryService1;
import ws.InvoiceRepositoryService2;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

@WebService(portName = "InvoiceRepositoryService2Service",
        serviceName = "InvoiceRepositoryService2",
        targetNamespace = "http://ws/",
        endpointInterface = "ws.InvoiceRepositoryService2")
@Stateless
public class InvoiceRepositoryService2WS implements InvoiceRepositoryService2 {

    @EJB
    InvoiceRepositoryStateless invoiceRepositoryStateless;

    InvoiceRepositoryService1 invoiceRepositoryService1;

    @PostConstruct
    private void postConstruct() {
        try {
            URL url = new URL ("http://localhost:8080/InvoiceRepositoryService1/InvoiceRepositoryService1WS?wsdl");
            QName qname = new QName ("http://ws/", "InvoiceRepositoryService1");
            Service service = Service.create (url, qname);
            invoiceRepositoryService1 = service.getPort (InvoiceRepositoryService1.class);
        } catch (MalformedURLException e) {
            e.printStackTrace ();
        }
    }

    @Override
    public String addProductToInvoice(int invoiceId, int productId, int quantity) throws BadRequestException, ConflictException {
        Product product = new Product (invoiceRepositoryService1.getProduct (productId));
        return invoiceRepositoryStateless.addProductToInvoice (invoiceId, product, quantity).toString ();
    }


    @Override
    public String removeProduct(int invoiceId, int lineNumber) throws BadRequestException, ConflictException {
        return invoiceRepositoryStateless.removeProduct (invoiceId, lineNumber).toString ();
    }

    @Override
    public String showInvoice(int invoiceId) throws BadRequestException, ConflictException {
        return invoiceRepositoryStateless.showInvoice (invoiceId).toString ();
    }

    @Override
    public String createInvoice(int userId) throws BadRequestException, ConflictException {
        return invoiceRepositoryStateless.createInvoice (userId).toString ();
    }

    @Override
    public String closeInvoice(int invoiceId) throws BadRequestException, ConflictException {
        return invoiceRepositoryStateless.closeInvoice (invoiceId).toString ();
    }

}
