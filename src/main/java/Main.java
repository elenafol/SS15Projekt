import java.util.HashSet;

/**
 * Created by d055218 on 16/04/15.
 */
public class Main {

    static int tnc;


    //On Mobile Client
    public void tBegin() {


        HashSet<Operation> create = new HashSet<Operation>();
        HashSet<Operation> read = new HashSet<Operation>();
        HashSet<Operation> write = new HashSet<Operation>();
        HashSet<Operation> delete = new HashSet<Operation>();
        int startTn = 1; // == tnc getTnFromServer()


        // Do changes on Dataset
        create.add(new Operation());
        delete.add(new Operation());


        // ==> Transfer to server


        //tEnd()


        long tn; //Will only be assigned if valid
        long finishTn = tnc; //==tnc //getTncFromServer()
        boolean valid = true;

        synchronized (this) {

            //This section will check if we possible have conflicts
            for (int i = startTn + 1; i <= finishTn; i++) {
                for (Operation operation : read) {
                    //Check if read operation conflicts with any write of T2 or T3
                    //TODO Why only write/read? What about DELETE
//                if(conflicts)
                    valid = false;
                }
            }


            //No comes the writePhase
            if (valid) {
                //Write changes data set
                tnc++;
                tn = tnc;
            }

        } //Critical phase end

        if (valid) {
            //cleanup //TODO: What's cleanup?
        } else {
            //backup(); //TODO: What's backup?
        }


    }

    public void tEnd() {


    }


    static class Operation {

    }


    }



