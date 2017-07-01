package model;

import interfaces.Recalculate;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = ("_InvoiceLineItem"))
@IdClass(value = InvoiceLineItemId.class)
public class InvoiceLineItem implements Recalculate {
    @Id
    Integer lineNumber;
    @Id
    @ManyToOne
    @JoinColumn(name = "invoice_id")
    Invoice invoice;
    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;
    @Column(nullable = false)
    Integer quantity = 0;
    @Column(nullable = false)
    BigDecimal netValue = BigDecimal.ZERO;
    @Column(nullable = false)
    BigDecimal grossValue = BigDecimal.ZERO;
    @Column(nullable = false)
    BigDecimal taxValue = BigDecimal.ZERO;
    @Transient
    boolean recursive = false;
    @Transient
    boolean recalculate = true;

    public InvoiceLineItem() {
        this (null);
    }

    public InvoiceLineItem(Invoice invoice) {
        this (invoice, null);
    }

    public InvoiceLineItem(Invoice invoice, Product product) {
        this (invoice, product, 1);
    }

    public InvoiceLineItem(Invoice invoice, Product product, int quantity) {
        this (null, invoice, product, quantity);
    }

    public InvoiceLineItem(Integer lineNumber, Invoice invoice, Product product, Integer quantity) {
        setLineNumber (lineNumber);
        setInvoice (invoice);
        setProduct (product, quantity);
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
        if (invoice != null) {
            this.invoice.getInvoiceLineItems ().add (this);
        }
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.setProduct (product, 1);
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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

    public void setProduct(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        recalculate ();
    }

    public void recalculate() {
        if (product == null) {
            setNetValue (BigDecimal.ZERO);
            setTaxValue (BigDecimal.ZERO);
            setGrossValue (BigDecimal.ZERO);
        } else {
            BigDecimal unitPrice = product.getUnitPrice ();
            BigDecimal quantity = BigDecimal.valueOf (getQuantity ());
            BigDecimal taxRate = product.getTaxRate ();
            setNetValue (unitPrice.multiply (quantity));
            setTaxValue (getNetValue ().multiply (taxRate));
            setGrossValue (getNetValue ().add (getTaxValue ()));
        }
    }

    @Override
    public boolean isRecursive() {
        return recursive;
    }

    @Override
    public void setRecursive(boolean recursive) {
        this.recursive = recursive;
    }

    @Override
    public boolean isRecalculate() {
        return recalculate;
    }

    @Override
    public void setRecalculate(boolean recalculate) {
        this.recalculate = recalculate;
    }

    @Override
    public String toString() {
        return "InvoiceLineItem{" +
                "lineNumber = " + lineNumber +
                ", quantity = " + quantity +
                ", netValue = " + netValue +
                ", grossValue = " + grossValue +
                ", taxValue = " + taxValue +
                ", product = " + product +
                "}\n";
    }
}
