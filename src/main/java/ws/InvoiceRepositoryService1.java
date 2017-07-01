package ws;

import exception.BadRequestException;
import exception.ConflictException;
import model.xml.ProductXML;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;


@WebService(targetNamespace = "http://ws/")
public interface InvoiceRepositoryService1 {
    @WebMethod
    @WebResult
    String addUser(
            @WebParam(name = "firstName") String firstName,
            @WebParam(name = "lastName") String lastName
    ) throws BadRequestException, ConflictException;

    @WebMethod
    @WebResult
    String createProduct(
            @WebParam(name = "name") String name,
            @WebParam(name = "unitPrice") double unitPrice,
            @WebParam(name = "taxRate") double taxRate
    ) throws BadRequestException, ConflictException;

    @WebMethod
    @WebResult
    ProductXML getProduct(
            @WebParam(name = "productId") int productId
    ) throws BadRequestException, ConflictException;
}
