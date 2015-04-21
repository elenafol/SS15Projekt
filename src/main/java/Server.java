/**
 * Created by d055218 on 16/04/15.
 */
public class Server {
    static void writeChangesToDB(Transaction t) {
        for (Operation operation : t.create) {
            Item item = operation.ref;
            ProxyClient.DB.put(item.id, item);
            //put Item in DB
        }

        for (Operation operation : t.delete) {
            Item item = operation.ref;
            //delete item from DB
            ProxyClient.DB.remove(item.id);
        }
    }


}
