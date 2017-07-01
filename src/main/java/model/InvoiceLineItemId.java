package model;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

public class InvoiceLineItemId implements Serializable {
    Integer lineNumber;
    Invoice invoice;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InvoiceLineItemId)) return false;

        InvoiceLineItemId that = (InvoiceLineItemId) o;

        if (!lineNumber.equals (that.lineNumber)) return false;
        return invoice.equals (that.invoice);
    }

    @Override
    public int hashCode() {
        int result = lineNumber.hashCode ();
        result = 31 * result + invoice.hashCode ();
        return result;
    }

    @Override
    public String toString() {
        return "InvoiceLineItemId{" +
                "lineNumber=" + lineNumber +
                ", invoice=" + invoice +
                '}';
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
    }

    public InvoiceLineItemId(Integer lineNumber, Invoice invoice) {
        this.lineNumber = lineNumber;
        this.invoice = invoice;
    }

    public InvoiceLineItemId() {
    }
}
