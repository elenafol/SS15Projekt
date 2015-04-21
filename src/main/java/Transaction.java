import java.util.HashSet;

/**
 * Created by d055218 on 16/04/15.
 */
public class Transaction {

    int transactionId;
    int tnStart;

    HashSet<Operation> create = new HashSet<Operation>();
    HashSet<Operation> read = new HashSet<Operation>();
    HashSet<Operation> write = new HashSet<Operation>();
    HashSet<Operation> delete = new HashSet<Operation>();
}
