package lab06.ex3;

import java.util.ArrayList;
import java.util.List;

public class Adapter implements AdvancedPrinterInterface{ //this class will adapt the basicPrinter to the AdvancedPrinter interface
    private BasicPrinter adaptee = new BasicPrinter();
    
    @Override
    public int print(Document doc) { //Se for true return 0, else return -1
        if(adaptee.print(doc.getStringArray())) return 0;
        return -1;
    }

    @Override
    public List<Integer> print(List<Document> docs) {
        List<Integer> r = new ArrayList<Integer>();
        for(int i=0; i<docs.size(); i++){
            r.add(print(docs.get(i))); //calls the method above
        }
        return null;
    }

    @Override
    public void showQueuedJobs() {
        System.out.println("BasicPrinter, does not have Jobs functionality");
    }

    @Override
    public boolean cancelJob(int jobId) {
        System.out.println("BasicPrinter, does not have Jobs functionality");
        return false;
    }

    @Override
    public void cancelAll() {
        System.out.println("BasicPrinter, does not have Jobs functionality");
        
    }
}

