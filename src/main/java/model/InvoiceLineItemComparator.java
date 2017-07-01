package model;

import java.util.Comparator;

/**
 * Created by Adam on 2017-06-18.
 */
public class InvoiceLineItemComparator implements Comparator<InvoiceLineItem> {

    @Override
    public int compare(InvoiceLineItem o1, InvoiceLineItem o2) {
        if (o1.getLineNumber () == null) {
            if (o2.getLineNumber () == null) {
                return 0;
            } else {
                return 1;
            }
        } else {
            if (o2.getLineNumber () == null) {
                return -1;
            } else {
                return o1.getLineNumber ().compareTo (o2.getLineNumber ());
            }
        }
    }
}
