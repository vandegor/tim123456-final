package model;

import model.xml.ProductXML;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Entity(name = "_Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "_ProductSeq")
    @SequenceGenerator(name = "_ProductSeq", sequenceName = "_ProductSeq", allocationSize = 1)
    Integer id;
    @Column(nullable = false)
    BigDecimal unitPrice = BigDecimal.ZERO;
    @Column(nullable = false)
    BigDecimal taxRate = BigDecimal.ZERO;
    @Column(nullable = false)
    String name = "";
    @OneToMany(mappedBy = "product")
    List<InvoiceLineItem> invoiceLineItems = new ArrayList<InvoiceLineItem> ();

    public Product() {
    }

    public Product(ProductXML productXML) {
        if (productXML == null) return;
        this.id = productXML.getId ();
        this.unitPrice = productXML.getUnitPrice ();
        this.taxRate = productXML.getTaxRate ();
        this.name = productXML.getName ();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<InvoiceLineItem> getInvoiceLineItems() {
        if (invoiceLineItems == null)
            invoiceLineItems = new ArrayList<InvoiceLineItem> ();
        return invoiceLineItems;
    }

    public void setInvoiceLineItems(List<InvoiceLineItem> invoiceLineItems) {
        this.invoiceLineItems = invoiceLineItems;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    @Override
    public String toString() {
        return "Product{" +
                " id = " + id +
                ", name = '" + name + '\'' +
                ", unitPrice = " + unitPrice +
                ", taxRate = " + taxRate +
                '}';
    }
}
