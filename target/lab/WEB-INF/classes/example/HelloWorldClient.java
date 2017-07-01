package example;/**
 * Created by Adam on 2017-06-19.
 */
public class HelloWorldClient {
  public static void main(String[] argv) {
    ws.InvoiceRepositoryService1 service = new InvoiceRepositoryService1Service ().getPort ();
    //invoke business method
    service.addProductToInvoice ();
  }
}
