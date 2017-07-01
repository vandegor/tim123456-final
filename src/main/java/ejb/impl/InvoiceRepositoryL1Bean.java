package ejb.impl;

import ejb.InvoiceRepositoryL1;
import exception.BadRequestException;
import exception.ConflictException;
import helper.ParamHelper;
import model.Invoice;
import model.Product;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import java.math.BigDecimal;
import java.math.MathContext;


@Stateful
public class InvoiceRepositoryL1Bean implements InvoiceRepositoryL1 {

    private Invoice invoice;

    @PostConstruct
    public void postConstruct() {
        System.out.print ("postConstruct");
        invoice = new Invoice ();
    }


    @Override
    public String addProduct(ParamHelper paramHelper) throws BadRequestException, ConflictException {
        if (invoice.isClosed ()) throw new ConflictException ("invoice is closed");
        Product product = new Product ();
        product.setName (paramHelper.getString ("name"));
        product.setUnitPrice (new BigDecimal (paramHelper.getInt ("unitPrice")));
        product.setTaxRate (new BigDecimal (paramHelper.getFloat ("taxRate"), MathContext.DECIMAL32));
        invoice.addInvoiceLineItem (product, paramHelper.getInt ("quantity"));
        return product.toString ();
    }

    @Override
    public String removeProduct(ParamHelper paramHelper) throws BadRequestException, ConflictException {
        if (invoice.isClosed ()) throw new ConflictException ("invoice is closed");
        return invoice.removeInvoiceLineItem (paramHelper.getInt ("lineNumber")).getProduct ().toString ();
    }

    @Override
    public String showInvoice(ParamHelper paramHelper) throws BadRequestException, ConflictException {
        return invoice.toString ();
    }


    @Override
    public String closeInvoice(ParamHelper paramHelper) throws BadRequestException, ConflictException {
        invoice.setClosed (true);
        return invoice.toString ();
    }

}
