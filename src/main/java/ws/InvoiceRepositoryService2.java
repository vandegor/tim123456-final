package ws;

import exception.BadRequestException;
import exception.ConflictException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;


@WebService(targetNamespace = "http://ws/")
public interface InvoiceRepositoryService2 {
    @WebMethod
    @WebResult
    String addProductToInvoice(
            @WebParam(name = "invoiceId") int invoiceId,
            @WebParam(name = "productId") int productId,
            @WebParam(name = "quantity") int quantity
    ) throws BadRequestException, ConflictException;

    @WebMethod
    @WebResult
    String removeProduct(
            @WebParam(name = "invoiceId") int invoiceId,
            @WebParam(name = "lineNumber") int lineNumber
    ) throws BadRequestException, ConflictException;

    @WebMethod
    @WebResult
    String showInvoice(
            @WebParam(name = "invoiceId") int invoiceId
    ) throws BadRequestException, ConflictException;

    @WebMethod
    @WebResult
    String createInvoice(
            @WebParam(name = "userId") int userId
    ) throws BadRequestException, ConflictException;

    @WebMethod
    @WebResult
    String closeInvoice(
            @WebParam(name = "invoiceId") int invoiceId
    ) throws BadRequestException, ConflictException;
}
