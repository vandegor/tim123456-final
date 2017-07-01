package model.xml;

import java.math.BigDecimal;
import java.util.List;

public class InvoiceXML {

    Integer id;
    List<InvoiceLineItemXML> invoiceLineItemXMLS;
    BigDecimal netValue = BigDecimal.ZERO;
    BigDecimal grossValue = BigDecimal.ZERO;
    BigDecimal taxValue = BigDecimal.ZERO;
    Boolean closed = false;
    UserXML userXML;
}
