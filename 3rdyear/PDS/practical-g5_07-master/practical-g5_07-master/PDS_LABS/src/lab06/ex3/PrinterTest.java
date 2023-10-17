package lab06.ex3;

import java.util.ArrayList;
import java.util.List;

public class PrinterTest {
    
    private static void pause(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AdvancedPrinterInterface p = new AdvancedPrinter();

        List<Document> docs = new ArrayList<Document>();
        docs.add(new Document("text1.txt"));
        docs.add(new Document("text2.txt"));
        docs.add(new Document("text3.txt"));

        p.print(docs.get(0));   // print first document only
        pause(2000);            // wait for a while

        p.print(docs);
        p.showQueuedJobs();
        pause(4000);            // wait for a while

        p.print(docs);
        p.cancelJob(6);
        p.showQueuedJobs();
        pause(4000);            // wait for a while

        p.print(docs);
        p.cancelAll();
        p.showQueuedJobs();

        pause(2000);            // wait for a while
    }
}
