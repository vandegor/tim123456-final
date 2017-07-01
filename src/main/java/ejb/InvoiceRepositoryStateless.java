package ejb;

import exception.BadRequestException;
import exception.ConflictException;
import model.Invoice;
import model.Product;
import model.User;

import javax.ejb.Local;

@Local
public interface InvoiceRepositoryStateless {

    User addUser(String firstName, String lastName) throws BadRequestException, ConflictException;

    Product createProduct(String name, double unitPrice, double taxRate) throws BadRequestException, ConflictException;

    Product getProduct(int productId) throws BadRequestException, ConflictException;

    Product addProductToInvoice(int invoiceId, Product product, int quantity) throws BadRequestException, ConflictException;

    Product addProduct(int invoiceId, String name, double unitPrice, double taxRate, int quantity) throws BadRequestException, ConflictException;

    Product removeProduct(int invoiceId, int lineNumber) throws BadRequestException, ConflictException;

    Invoice showInvoice(int invoiceId) throws BadRequestException, ConflictException;

    Invoice createInvoice(int userId) throws BadRequestException, ConflictException;

    Invoice closeInvoice(int invoiceId) throws BadRequestException, ConflictException;
}
