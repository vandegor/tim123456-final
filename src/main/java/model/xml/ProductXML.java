package model.xml;


import model.Product;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;

@XmlType
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ProductXML {

    private Integer id;
    private BigDecimal unitPrice = BigDecimal.ZERO;
    private BigDecimal taxRate = BigDecimal.ZERO;
    private String name = "";

    public ProductXML() {
        this (null);
    }

    public ProductXML(Product product) {
        if (product == null) return;
        this.id = product.getId ();
        this.unitPrice = product.getUnitPrice ();
        this.taxRate = product.getTaxRate ();
        this.name = product.getName ();
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

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
