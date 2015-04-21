/**
 * Created by d055218 on 16/04/15.
 */
public class HttpEndpoint {


    //@GET
    public void submitTransaction(Transaction t){
        //Request comes in
        // get Transaction from request
        ProxyClient proxyClient = new ProxyClient(t);
        proxyClient.writeChanges();
    }

}
