package ejb.impl;

import ejb.InvoiceRepositoryL2;
import ejb.InvoiceRepositoryStateless;
import exception.BadRequestException;
import exception.ConflictException;
import helper.ParamHelper;
import javafx.util.Pair;
import model.Invoice;
import model.Product;
import model.User;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateful
public class InvoiceRepositoryL2Bean implements InvoiceRepositoryL2 {

    @EJB
    InvoiceRepositoryStateless invoiceRepositoryStateless;

    @PersistenceContext(unitName = "postgres")
    private EntityManager entityManager;


    @Override
    public String addUser(ParamHelper paramHelper) throws BadRequestException, ConflictException {
        User user = invoiceRepositoryStateless.addUser (paramHelper.getString ("firstName"), paramHelper.getString ("lastName"));
        /*UserXML user = new UserXML ();
        user.setFirstName (paramHelper.getString ("firstName"));
        user.setLastName (paramHelper.getString ("lastName"));
        entityManager.persist (user);*/
        return user.toString ();
    }

    @Override
    public String addProduct(ParamHelper paramHelper) throws BadRequestException, ConflictException {
        Product product = invoiceRepositoryStateless.addProduct (paramHelper.getInt ("invoiceId"),
                paramHelper.getString ("name"), paramHelper.getDouble ("unitPrice"),
                paramHelper.getDouble ("taxRate"), paramHelper.getInt ("quantity"));
        /*InvoiceXML invoice = getReferanceThrowIfNull (InvoiceXML.class, paramHelper.geEntryInt ("invoiceId"));
        if (invoice.isClosed ()) throw new ConflictException ("invoice is closed");
        ProductXML product = new ProductXML ();
        product.setName (paramHelper.getString ("name"));
        product.setUnitPrice (BigDecimal.valueOf (paramHelper.getDouble ("unitPrice")));
        product.setTaxRate (new BigDecimal (paramHelper.getDouble ("taxRate"), MathContext.DECIMAL32));
        entityManager.persist (product);
        invoice.addInvoiceLineItem (product, paramHelper.getInt ("quantity"));*/
        return product.toString ();
    }


    @Override
    public String removeProduct(ParamHelper paramHelper) throws BadRequestException, ConflictException {
        Product product = invoiceRepositoryStateless.removeProduct (paramHelper.getInt ("invoiceId"), paramHelper.getInt ("lineNumber"));

        /*InvoiceXML invoice = getReferanceThrowIfNull (InvoiceXML.class, paramHelper.geEntryInt ("invoiceId"));
        if (invoice.isClosed ()) throw new ConflictException ("invoice is closed");
        InvoiceLineItemXML invoiceLineItem = invoice.removeInvoiceLineItem (paramHelper.getInt ("lineNumber"));
        if (invoiceLineItem == null)
            throw new BadRequestException ("cannot find invoiceLineItem by lineNumber: " + paramHelper.getInt ("lineNumber"));
        ProductXML product = invoiceLineItem.getProduct ();*/
        return product.toString ();
    }

    @Override
    public String showInvoice(ParamHelper paramHelper) throws BadRequestException, ConflictException {
        Invoice invoice = invoiceRepositoryStateless.showInvoice (paramHelper.getInt ("invoiceId"));
        //InvoiceXML invoice = getReferanceThrowIfNull (InvoiceXML.class, paramHelper.geEntryInt ("invoiceId"));
        return invoice.toString ();
    }

    @Override
    public String createInvoice(ParamHelper paramHelper) throws BadRequestException, ConflictException {
        Invoice invoice = invoiceRepositoryStateless.createInvoice (paramHelper.getInt ("userId"));
        /*UserXML user = getReferanceThrowIfNull (UserXML.class, paramHelper.geEntryInt ("userId"));
        InvoiceXML invoice = new InvoiceXML ();
        invoice.setUser (user);
        entityManager.persist (invoice);*/
        return invoice.toString ();
    }

    @Override
    public String closeInvoice(ParamHelper paramHelper) throws BadRequestException, ConflictException {
        Invoice invoice = invoiceRepositoryStateless.closeInvoice (paramHelper.getInt ("invoiceId"));
        /*InvoiceXML invoice = getReferanceThrowIfNull (InvoiceXML.class, paramHelper.geEntryInt ("invoiceId"));
        invoice.setClosed (true);*/
        return invoice.toString ();
    }

    private <T> T getReferanceThrowIfNull(Class<T> clazz, Pair entry) throws BadRequestException {
        T t = entityManager.getReference (clazz, entry.getValue ());
        if (t == null)
            throw new BadRequestException ("cannot find " + clazz.getName () + " by " + entry.getKey ().toString () + ": " + entry.getValue ().toString ());
        return t;
    }
}
