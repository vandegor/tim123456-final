package ejb.impl;

import ejb.InvoiceRepositoryStateless;
import exception.BadRequestException;
import exception.ConflictException;
import javafx.util.Pair;
import model.Invoice;
import model.InvoiceLineItem;
import model.Product;
import model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.math.MathContext;

@Stateless
public class InvoiceRepositoryStatelessBean implements InvoiceRepositoryStateless {

    @PersistenceContext(unitName = "postgres")
    private EntityManager entityManager;


    @Override
    public Product createProduct(String name, double unitPrice, double taxRate) throws BadRequestException, ConflictException {
        Product product = new Product ();
        product.setName (name);
        product.setUnitPrice (BigDecimal.valueOf (unitPrice));
        product.setTaxRate (new BigDecimal (taxRate, MathContext.DECIMAL32));
        entityManager.persist (product);
        return product;
    }

    @Override
    public Product getProduct(int productId) throws BadRequestException, ConflictException {
        return getReferanceThrowIfNull (Product.class, new Pair ("productId", productId));
    }

    @Override
    public Product addProductToInvoice(int invoiceId, Product product, int quantity) throws BadRequestException, ConflictException {
        Invoice invoice = getReferanceThrowIfNull (Invoice.class, new Pair ("invoiceId", invoiceId));
        for (InvoiceLineItem invoiceLineItem : invoice.getInvoiceLineItems ()) {
            if (invoiceLineItem.getProduct ().getId ().equals (product.getId ()))
                throw new ConflictException ("product already exist on invoice");
        }
        invoice.addInvoiceLineItem (product, quantity);
        return product;
    }

    @Override
    public User addUser(String firstName, String lastName) throws BadRequestException, ConflictException {
        User user = new User ();
        user.setFirstName (firstName);
        user.setLastName (lastName);
        entityManager.persist (user);
        return user;
    }

    @Override
    public Product addProduct(int invoiceId, String name, double unitPrice, double taxRate, int quantity) throws BadRequestException, ConflictException {
        Invoice invoice = getReferanceThrowIfNull (Invoice.class, new Pair ("invoiceId", invoiceId));
        Product product = createProduct (name, unitPrice, taxRate);
        invoice.addInvoiceLineItem (product, quantity);
        return product;
    }


    @Override
    public Product removeProduct(int invoiceId, int lineNumber) throws BadRequestException, ConflictException {
        Invoice invoice = getReferanceThrowIfNull (Invoice.class, new Pair ("invoiceId", invoiceId));
        InvoiceLineItem invoiceLineItem = invoice.removeInvoiceLineItem (lineNumber);
        if (invoiceLineItem == null)
            throw new BadRequestException ("cannot find invoiceLineItem by lineNumber: " + lineNumber);
        Product product = invoiceLineItem.getProduct ();
        return product;
    }

    @Override
    public Invoice showInvoice(int invoiceId) throws BadRequestException, ConflictException {
        Invoice invoice = getReferanceThrowIfNull (Invoice.class, new Pair ("invoiceId", invoiceId));
        return invoice;
    }

    @Override
    public Invoice createInvoice(int userId) throws BadRequestException, ConflictException {
        User user = getReferanceThrowIfNull (User.class, new Pair ("userId", userId));
        Invoice invoice = new Invoice ();
        invoice.setUser (user);
        entityManager.persist (invoice);
        return invoice;
    }

    @Override
    public Invoice closeInvoice(int invoiceId) throws BadRequestException, ConflictException {
        Invoice invoice = getReferanceThrowIfNull (Invoice.class, new Pair ("invoiceId", invoiceId));
        invoice.setClosed (true);
        return invoice;
    }

    private <T> T getReferanceThrowIfNull(Class<T> clazz, Pair entry) throws BadRequestException {
        T t = entityManager.getReference (clazz, entry.getValue ());
        if (t == null)
            throw new BadRequestException ("cannot find " + clazz.getName () + " by " + entry.getKey ().toString () + ": " + entry.getValue ().toString ());
        return t;
    }
}
