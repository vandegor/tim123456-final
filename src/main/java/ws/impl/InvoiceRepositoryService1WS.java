
package ws.impl;

import ejb.InvoiceRepositoryStateless;
import exception.BadRequestException;
import exception.ConflictException;
import model.xml.ProductXML;
import ws.InvoiceRepositoryService1;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

@WebService(portName = "InvoiceRepositoryService1Service",
        serviceName = "InvoiceRepositoryService1",
        targetNamespace = "http://ws/",
        endpointInterface = "ws.InvoiceRepositoryService1")
@Stateless
public class InvoiceRepositoryService1WS implements InvoiceRepositoryService1 {

    @EJB
    InvoiceRepositoryStateless invoiceRepositoryStateless;

    @Override
    public String addUser(String firstName, String lastName) throws BadRequestException, ConflictException {
        return invoiceRepositoryStateless.addUser (firstName, lastName).toString ();
    }

    @Override
    public String createProduct(String name, double unitPrice, double taxRate) throws BadRequestException, ConflictException {
        return invoiceRepositoryStateless.createProduct (name, unitPrice, taxRate).toString ();
    }

    @Override
    public ProductXML getProduct(int productId) throws BadRequestException, ConflictException {
        return new ProductXML (invoiceRepositoryStateless.getProduct (productId));
    }
}
