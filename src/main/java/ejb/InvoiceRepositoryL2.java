package ejb;


import exception.BadRequestException;
import exception.ConflictException;
import helper.ParamHelper;
import interfaces.HttpGET;
import interfaces.HttpPOST;
import interfaces.SessionKey;

import javax.ejb.Local;

@Local
@SessionKey(value = "InvoiceRepositoryL2")
public interface InvoiceRepositoryL2 {
    @HttpPOST
    String addUser(ParamHelper paramHelper) throws BadRequestException, ConflictException;

    @HttpGET
    String addProduct(ParamHelper paramHelper) throws BadRequestException, ConflictException;

    @HttpGET
    String removeProduct(ParamHelper paramHelper) throws BadRequestException, ConflictException;

    @HttpGET
    String showInvoice(ParamHelper paramHelper) throws BadRequestException, ConflictException;

    @HttpPOST
    String createInvoice(ParamHelper paramHelper) throws BadRequestException, ConflictException;

    @HttpGET
    String closeInvoice(ParamHelper paramHelper) throws BadRequestException, ConflictException;

}
