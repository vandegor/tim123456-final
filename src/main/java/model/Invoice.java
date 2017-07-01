package model;

import exception.ConflictException;
import interfaces.Recalculate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity(name = "_Invoice")
public class Invoice implements Recalculate {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "_InvoiceSeq")
    @SequenceGenerator(name = "_InvoiceSeq", sequenceName = "_InvoiceSeq", allocationSize = 1)
    Integer id;
    @OneToMany(mappedBy = "invoice", orphanRemoval = true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<InvoiceLineItem> invoiceLineItems;
    @Column(nullable = false)
    BigDecimal netValue = BigDecimal.ZERO;
    @Column(nullable = false)
    BigDecimal grossValue = BigDecimal.ZERO;
    @Column(nullable = false)
    BigDecimal taxValue = BigDecimal.ZERO;
    @Column(nullable = false)
    Boolean closed = false;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    @Transient
    boolean recursive = false;
    @Transient
    boolean recalculate = true;

    public Invoice() {
        if (invoiceLineItems == null) {
            invoiceLineItems = new ArrayList<InvoiceLineItem> ();
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<InvoiceLineItem> getInvoiceLineItems() {
        return invoiceLineItems;
    }

    public void setInvoiceLineItems(List<InvoiceLineItem> invoiceLineItems) {
        this.invoiceLineItems = invoiceLineItems;
    }

    public BigDecimal getNetValue() {
        return netValue;
    }

    public void setNetValue(BigDecimal netValue) {
        this.netValue = netValue;
    }

    public BigDecimal getGrossValue() {
        return grossValue;
    }

    public void setGrossValue(BigDecimal grossValue) {
        this.grossValue = grossValue;
    }

    public BigDecimal getTaxValue() {
        return taxValue;
    }

    public void setTaxValue(BigDecimal taxValue) {
        this.taxValue = taxValue;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void recalculate() {
        setNetValue (BigDecimal.ZERO);
        setTaxValue (BigDecimal.ZERO);
        setGrossValue (BigDecimal.ZERO);
        for (int i = 0; i < getInvoiceLineItems ().size (); i++) {
            InvoiceLineItem item = getInvoiceLineItems ().get (i);
            if (recursive) {
                item.recalculate ();
            }
            setNetValue (netValue.add (item.getNetValue ()));
            setTaxValue (taxValue.add (item.getTaxValue ()));
            setGrossValue (grossValue.add (item.getGrossValue ()));
        }
    }


    public void addInvoiceLineItem(Product product) throws ConflictException {
        addInvoiceLineItem (product, 1);
    }

    public void addInvoiceLineItem(Product product, int quantity) throws ConflictException {
        if (closed) throw new ConflictException ("invoice is closed");
        Collections.sort (invoiceLineItems, new InvoiceLineItemComparator ());
        int lineNumber = 0;
        if (getInvoiceLineItems ().size () > 0) {
            lineNumber = getInvoiceLineItems ().get (getInvoiceLineItems ().size () - 1).getLineNumber () + 1;
        }
        InvoiceLineItem invoiceLineItem = new InvoiceLineItem (lineNumber, this, product, quantity);
        recalculate ();
    }

    public InvoiceLineItem removeInvoiceLineItem(int lineItemNumber) throws ConflictException {
        if (closed) throw new ConflictException ("invoice is closed");
        for (InvoiceLineItem invoiceLineItem : invoiceLineItems) {
            if (invoiceLineItem.getLineNumber ().equals (lineItemNumber)) {
                invoiceLineItems.remove (invoiceLineItem);
                return invoiceLineItem;
            }
        }
        recalculate ();
        return null;
    }

    public boolean isRecursive() {
        return recursive;
    }

    public void setRecursive(boolean recursive) {
        this.recursive = recursive;
    }

    public boolean isRecalculate() {
        return recalculate;
    }

    public void setRecalculate(boolean recalculate) {
        this.recalculate = recalculate;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                " invoiceId = " + id +
                ",\nnetValue = " + netValue +
                ",\ngrossValue = " + grossValue +
                ",\ntaxValue = " + taxValue +
                ",\nuser = " + user +
                ",\ninvoiceLineItems =\n " + invoiceLineItems +
                "}";
    }
}
