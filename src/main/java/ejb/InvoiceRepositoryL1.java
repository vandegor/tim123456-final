package ejb;

import exception.BadRequestException;
import exception.ConflictException;
import helper.ParamHelper;
import interfaces.HttpGET;
import interfaces.SessionKey;

import javax.ejb.Local;

@Local
@SessionKey(value = "InvoiceRepositoryL1")
public interface InvoiceRepositoryL1 {

    @HttpGET
    String addProduct(ParamHelper paramHelper) throws BadRequestException, ConflictException;

    @HttpGET
    String removeProduct(ParamHelper paramHelper) throws BadRequestException, ConflictException;

    @HttpGET
    String showInvoice(ParamHelper paramHelper) throws BadRequestException, ConflictException;

    @HttpGET
    String closeInvoice(ParamHelper paramHelper) throws BadRequestException, ConflictException;


}
