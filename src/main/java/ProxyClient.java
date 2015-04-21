import java.util.HashMap;


public class ProxyClient {

    static HashMap<Long, Item> DB = new HashMap<Long, Item>();
    static final HashMap<Integer, Transaction> transactions = new HashMap<Integer, Transaction>();
    static int tnc;

    static Object lock = new Object();


    private Transaction t = new Transaction();

    public ProxyClient(Transaction t) {
        this.t = t;
    }

    // Called by mobile client to get tnStart
    public int getTransactionId() {
        return tnc;
    }


    //Called by mobile client
    public boolean writeChanges() {
        int midTn = tnc;
        boolean valid = true;

        valid = checkConcurrentTransactions(t, midTn);

        if (valid) {
            synchronized (lock) { // This is just to represent the critical phase
                int tnFinish = tnc;
                valid = checkConcurrentTransactions(t, tnFinish);
            }

            //No comes the writePhase
            if (valid) {
                Server.writeChangesToDB(t);
                tnc++;
                t.transactionId = tnc;
            }
        }

        if (valid) {
            cleanUp();
        } else {
            backup();
        }

        return valid;
    }

    private void backup() {

    }

    private void cleanUp() {

    }

    private boolean checkConcurrentTransactions(Transaction t, int midTn) {
        // "First" validation
        for (int i = t.tnStart + 1; i <= midTn; i++) {
            for (Operation operation : t.read) {
                Transaction concurrentT = transactions.get(i);
                for (Operation operation1 : concurrentT.write) { //TODO: What about DELETE?
                    if (operation.ref == operation1.ref)
                        return false;
                }
            }
        }
        return true;
    }


}
