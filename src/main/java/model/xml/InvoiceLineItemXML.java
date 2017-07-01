package model.xml;

import java.math.BigDecimal;

public class InvoiceLineItemXML {
    Integer lineNumber;
    ProductXML product;
    Integer quantity = 0;
    BigDecimal netValue = BigDecimal.ZERO;
    BigDecimal grossValue = BigDecimal.ZERO;
    BigDecimal taxValue = BigDecimal.ZERO;
}
